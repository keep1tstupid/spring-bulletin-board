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
import java.util.UUID;
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

    private String bucketName = "spring-bucket-test-lolka";

    private boolean checkFileExtension(String fileName) {
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            String[] allowedExt = {".jpg", ".jpeg", ".png", ".gif"};
            for (String ext:allowedExt) {
                if(fileName.endsWith(ext)) {
                    return true;
                }
            }
        }
        return false;
    }

    // method to add new file and link it to appropriate item
    @PostMapping("/api/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            //@RequestParam("file")
            MultipartFile file,
            @RequestParam Long id) throws ServletException {

        UUID uuid = UUID.randomUUID();
        String message = "";
        String fileName = file.getOriginalFilename();
        String storageFileName = uuid + fileName;

        // if format of file is ok
        if (checkFileExtension(fileName)) {

            //go and try to save in cloud
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
                Attachment current = storageService.store(file, storageFileName);
                message = "Saved in DB successfully: " + fileName + " Assigned id: " +
                        current.getId();

                // update item with attachment Id
                Optional<Item> targetItem = itemRepository.findById(id);
                targetItem.ifPresent(item -> {
                    item.setAttachmentId(current.getId());
                    itemRepository.save(item);
                });

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not save the file: " + file.getOriginalFilename() + "!" + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Uploading failed during format check";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }

    // get all files
    @GetMapping("/api/files")
    public ResponseEntity<List<ResponseAttachment>> getListFiles() {
        List<ResponseAttachment> files = storageService.getAllFiles().map(dbFile -> {
            String fileUrl = "https://storage.googleapis.com/" + bucketName + "/" + dbFile.getUrl();

            return new ResponseAttachment(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileUrl,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    // get certain file --- not in use
    @GetMapping("/api/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Attachment fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
