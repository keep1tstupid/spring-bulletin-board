package com.example.bb.web;

import com.example.bb.domain.Attachment;
import com.example.bb.domain.Item;
import com.example.bb.message.ResponseAttachment;
import com.example.bb.message.ResponseMessage;
import com.example.bb.repository.AttachmentRepository;
import com.example.bb.repository.ItemRepository;
import com.example.bb.service.AttachmentStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8081")
public class AttachmentController {

    @Autowired
    private AttachmentStorageService storageService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            //@RequestParam("file")
            MultipartFile file,
                    @RequestParam Long id) {
        String message = "";
        try {
            Attachment current = storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename() + " Assigned id: " +
                    current.getId();

            //message = "Uploaded the file successfully: " + file.getOriginalFilename();

            String cwd = Paths.get("").toAbsolutePath().toString();
            File newFile = new File(cwd + "/files/" + file.getOriginalFilename());
            newFile.createNewFile();

            Optional<Item> targetItem = itemRepository.findById(id);
            targetItem.ifPresent(item -> {
                item.setAttachmentId(current.getId());
                itemRepository.save(item);
            });

            FileOutputStream fout = new FileOutputStream(newFile);
            fout.write(file.getBytes());
            fout.close();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseAttachment>> getListFiles() {
        List<ResponseAttachment> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseAttachment(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Attachment fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
