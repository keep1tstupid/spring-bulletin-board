//package com.example.bb.web;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Paths;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//public class FileUploadController {
////    @RequestMapping(value = "/upload", method = RequestMethod.POST,
////            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//
//    @PostMapping("/upload")
//    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        // get file info
//        InputStream inputStream = file.getInputStream();
//        String originalName = file.getOriginalFilename();
//        String name = file.getName();
//        String contentType = file.getContentType();
//        long size = file.getSize();
//
//        String cwd = Paths.get("").toAbsolutePath().toString();
//
//        File newFile = new File(cwd + "/files/" + originalName);
//        newFile.createNewFile();
//
//        FileOutputStream fout = new FileOutputStream(newFile);
//        fout.write(file.getBytes());
//        fout.close();
//
//        return contentType;
//    }
//}
