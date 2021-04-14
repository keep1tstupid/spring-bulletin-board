package com.example.bb.web;

import com.example.bb.domain.Attachment;
import com.example.bb.domain.Item;
import com.example.bb.message.ResponseAttachment;
import com.example.bb.message.ResponseMessage;
import com.example.bb.repository.AttachmentRepository;
import com.example.bb.repository.ItemRepository;
import com.example.bb.service.AttachmentStorageService;

import com.google.cloud.storage.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class AttachmentController {

    @Autowired
    private AttachmentStorageService storageService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private Storage storage;

    private boolean checkFileExtension(String fileName) throws ServletException {
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            String[] allowedExt = {".jpg", ".jpeg", ".png", ".gif"};
            for (String ext:allowedExt) {
                if(fileName.endsWith(ext)) {
                    return true;
                }
            }
            throw new ServletException("file must be an image");
        }
        return false;
    }

    // method to add new file and link it to appropriate item
    @PostMapping("/api/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            //@RequestParam("file")
            MultipartFile file,
            @RequestParam Long id) throws ServletException {

        String message = "";
        String fileName = file.getOriginalFilename();
        String bucketName = "spring-bucket-test-lolka";

        // if format of file is ok
        if (checkFileExtension(fileName)) {

            //go and try to save in cloud
            String storageFileName = fileName;
            try {
                BlobInfo blobInfo = storage.create(
                        BlobInfo.newBuilder(bucketName, storageFileName).setContentType("image/jpeg").build(),
                        file.getBytes()
                );
                System.out.println(blobInfo.getMediaLink());
            } catch (Exception e) {
                System.out.println(e);
                message = "Problem occurred during saving to the cloud";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }

            // if uploaded to cloud, go and save in db
            try {
                Attachment current = storageService.store(file);
                message = "Saved in DB successfully: " + fileName + " Assigned id: " +
                        current.getId();

                // saving locally
//                String cwd = Paths.get("").toAbsolutePath().toString();
//                File newFile = new File(cwd + "/files/" + file.getOriginalFilename());
//                newFile.createNewFile();

                // update item with attachment Id
                Optional<Item> targetItem = itemRepository.findById(id);
                targetItem.ifPresent(item -> {
                    item.setAttachmentId(current.getId());
                    itemRepository.save(item);
                });

//                FileOutputStream fout = new FileOutputStream(newFile);
//                fout.write(file.getBytes());
//                fout.close();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not save the file: " + file.getOriginalFilename() + "!" + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Uploading failed during format check";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }

//        try {
//            Attachment current = storageService.store(file);
//            message = "Uploaded the file successfully: " + fileName + " Assigned id: " +
//                    current.getId();
//
//            String cwd = Paths.get("").toAbsolutePath().toString();
//            File newFile = new File(cwd + "/files/" + file.getOriginalFilename());
//            newFile.createNewFile();
//
//            Optional<Item> targetItem = itemRepository.findById(id);
//            targetItem.ifPresent(item -> {
//                item.setAttachmentId(current.getId());
//                itemRepository.save(item);
//            });
//
//            FileOutputStream fout = new FileOutputStream(newFile);
//            fout.write(file.getBytes());
//            fout.close();
//
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage();
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//        }
//    }

    // get all files
    @GetMapping("/api/files")
    public ResponseEntity<List<ResponseAttachment>> getListFiles() {
        List<ResponseAttachment> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseAttachment(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    // get certain file
    @GetMapping("/api/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Attachment fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
