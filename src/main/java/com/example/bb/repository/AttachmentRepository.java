package com.example.bb.repository;

import com.example.bb.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
