package br.com.pi.serviceSchedulingApi.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
}
