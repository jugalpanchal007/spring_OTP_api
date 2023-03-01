package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer>{
	  Optional<Otp> findByPhoneAndCodeAndExpirationTimeAfter(String phone, String code, LocalDateTime expirationTime);
	  

}
