package com.example.bb.repository;

import com.example.bb.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {}