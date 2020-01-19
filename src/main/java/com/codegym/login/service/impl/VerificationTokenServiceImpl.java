package com.codegym.login.service.impl;

import com.codegym.login.model.VerificationToken;
import com.codegym.login.repository.VerificationTokenRepository;
import com.codegym.login.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void save(VerificationToken token) {
        verificationTokenRepository.save(token);
    }
}
