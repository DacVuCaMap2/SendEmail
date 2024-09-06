package com.PixelUniverse.app.Controller;

import com.PixelUniverse.app.Request.Email.RequestEmailConfirmOrder;
import com.PixelUniverse.app.SendMail.EmailConfirmOrder;
import com.PixelUniverse.app.SendMail.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @GetMapping("/send")
    public ResponseEntity<?> sendEmail(){
        return emailService.sendEmailToConfirmOrder();
    }
}
