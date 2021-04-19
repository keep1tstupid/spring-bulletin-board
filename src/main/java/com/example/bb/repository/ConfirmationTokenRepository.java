package com.example.bb.repository;

import com.example.bb.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {}