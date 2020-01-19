package com.codegym.login.repository;

import com.codegym.login.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifitcationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
