package com.example.bb;

import com.example.bb.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AttachmentRepositoryTest {
    @Autowired
    AttachmentRepository attachmentRepository;
}
