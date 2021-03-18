package com.example.bb.service;

import com.example.bb.domain.Image;
import com.example.bb.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageStorageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(FileDB);
    }

    public Image getFile(Long id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }
}
