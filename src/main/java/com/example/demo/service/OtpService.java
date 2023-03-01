package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.OtpRepository;
import com.example.demo.model.Otp;

@Service
public class OtpService {
	
    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public Otp generateOtp(String phone ) {
        String code = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);

        Otp otp = new Otp();
      //  otp.setEmail(email);
        otp.setPhone(phone);
        otp.setCode(code);
        otp.setExpirationTime(expirationTime);

        return otpRepository.save(otp);
    }

    public boolean verifyOtp(String phone, String code) {
        Optional<Otp> optionalOtp = otpRepository.findByPhoneAndCodeAndExpirationTimeAfter(phone, code, LocalDateTime.now());
       
        if (optionalOtp.isPresent()) {
            otpRepository.delete(optionalOtp.get());
            return true;
        } else {
            return false;
        }
    }
}
