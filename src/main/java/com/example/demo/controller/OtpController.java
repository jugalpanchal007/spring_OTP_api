package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Otp;
import com.example.demo.service.OtpService;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String phone) {
        Otp otp = otpService.generateOtp(phone);

        // send the OTP to the user via email, SMS, etc.
        // for demo purposes, just print it to the console
        System.out.println("Generated OTP for email " +  phone + ": " + otp.getCode());

        return ResponseEntity.ok("OTP generated successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phone, @RequestParam String code) {
        boolean isVerified = otpService.verifyOtp(phone, code);

        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP or OTP has expired");
        }
    }
}
