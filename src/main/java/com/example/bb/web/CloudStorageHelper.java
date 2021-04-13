//package com.example.bb.web;
//
//import com.google.cloud.storage.BlobInfo;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletException;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//
//public class CloudStorageHelper {
//    @SuppressWarnings("deprecation")
//
//    @RequestMapping(method = RequestMethod.POST, value = "/imageUpload112")
//    public String uploadFile(@RequestParam("fileseee") MultipartFile fileStream)
//        throws IOException, ServletException {
//
//        String bucketName="spring-bucket-test-lolka";
//        checkFileExtension(fileStream.getName());
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
//        DateTime dt=DateTime.now(DateTimeZone.UTC);
//        String dtString=dt.toString(dtf);
//
//        final String fileName=fileStream.getName()+dtString;
//
//        File file = convertMultiPartToFile(fileStream);
//
//        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucketName,fileName)
//                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(),Role.READER))))
//                        .build()

//                //file.openStream()
//        );
//        System.out.println(blobInfo.getMediaLink());
//        return blobInfo.getMediaLink();
//    }
//
//
//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//
//
////    private void checkFileExtension(String fileName) throws ServletException {
////        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
////            String[]allowedExt = {".jpg", ".jpeg", ".png", ".gif"};
////            for (String ext:allowedExt) {
////                if(fileName.endsWith(ext)) {
////                    return;
////                }
////            }
////            throw new ServletException("file must be an image");
////        }
////    }
//}
