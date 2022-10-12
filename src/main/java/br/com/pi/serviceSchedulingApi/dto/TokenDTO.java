package br.com.pi.serviceSchedulingApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private UserDetails user;
    private String token;
}
