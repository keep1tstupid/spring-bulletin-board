package com.example.bb.service;

import com.example.bb.domain.Attachment;
import com.example.bb.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class AttachmentStorageService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Transactional
    public Attachment store(MultipartFile file, String storageFileName) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Attachment FileDB = new Attachment(
                fileName,
                file.getContentType(),
                file.getBytes(),
                storageFileName);
        return attachmentRepository.save(FileDB);
    }

    public Attachment getFile(Long id) {
        return attachmentRepository.findById(id).get();
    }

    public Stream<Attachment> getAllFiles() {
        return attachmentRepository.findAll().stream();
    }
}
