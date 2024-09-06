package com.PixelUniverse.app.Controller;

import com.PixelUniverse.app.Request.Email.RequestEmailConfirmOrder;
import com.PixelUniverse.app.SendMail.EmailConfirmOrder;
import com.PixelUniverse.app.SendMail.EmailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailConfirmOrder emailConfirmOrder){
        return emailService.sendEmailToConfirmOrder(emailConfirmOrder);
    }
    @GetMapping("/test")
    public ResponseEntity<?> testText(){
        return ResponseEntity.ok().body("oke");
    }
}
