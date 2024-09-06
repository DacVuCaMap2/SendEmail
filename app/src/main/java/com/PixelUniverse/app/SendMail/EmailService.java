package com.PixelUniverse.app.SendMail;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    ResponseEntity<?> sendEmailToConfirmOrder();
}
