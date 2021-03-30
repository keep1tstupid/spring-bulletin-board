package com.example.bb;

import com.example.bb.domain.Attachment;
import com.example.bb.repository.AttachmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AttachmentRepositoryTest {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Test
    public void addNewAttachmentTest() {
        Attachment attachment = new Attachment("Test attachment");
        attachmentRepository.save(attachment);
        assertThat(attachmentRepository.findById(attachment.getId()).isPresent());
    }

    @Test
    public void deleteAttachmentTest() {
        Attachment attachment = new Attachment("Test attachment");
        attachmentRepository.save(attachment);
        assertThat(attachmentRepository.findById(attachment.getId()).isPresent());
        attachmentRepository.delete(attachment);
        assertThat(attachmentRepository.findById(attachment.getId()).isEmpty());
    }

    @Test
    public void findAllAttachmentsTest() {
        List<Attachment> attachments = attachmentRepository.findAll();
        assertThat(attachments.size() != 0);
    }
}
