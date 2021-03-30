package com.example.bb.repository;

import com.example.bb.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
//    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
