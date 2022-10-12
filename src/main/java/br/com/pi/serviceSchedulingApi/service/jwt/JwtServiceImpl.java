package br.com.pi.serviceSchedulingApi.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value(("${security.jwt.key}"))
    private String key;

    private Date generateDate() {
        Long expString = Long.valueOf(expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusHours(expString);
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Date date = generateDate();
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
