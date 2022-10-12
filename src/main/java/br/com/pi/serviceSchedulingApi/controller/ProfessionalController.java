package br.com.pi.serviceSchedulingApi.controller;

import br.com.pi.serviceSchedulingApi.dto.LoginDTO;
import br.com.pi.serviceSchedulingApi.dto.ProfessionalDTO;
import br.com.pi.serviceSchedulingApi.dto.TokenDTO;
import br.com.pi.serviceSchedulingApi.exception.PasswordInvalidException;
import br.com.pi.serviceSchedulingApi.model.Professional;
import br.com.pi.serviceSchedulingApi.service.jwt.JwtService;
import br.com.pi.serviceSchedulingApi.service.professional.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/professional")
public class ProfessionalController {

    private ProfessionalService professionalService;
    private JwtService jwtService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService, JwtService jwtService) {
        this.professionalService = professionalService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalDTO>> getAllProfessionals() {
        List<ProfessionalDTO> professionals = ProfessionalDTO.convertList(professionalService.getAllProfessionals());
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> getProfessionalById(@PathVariable Long id) {
        ProfessionalDTO professional = new ProfessionalDTO(professionalService.getProfessionalById(id));
        return ResponseEntity.ok(professional);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProfessionalDTO>> getProfessionalByFirstName(@PathVariable String name) {
        List<ProfessionalDTO> professionals = ProfessionalDTO.convertList(professionalService.getProfessionalByFirstName(name));
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/speciality/{specialty}")
    public ResponseEntity<List<ProfessionalDTO>> getProfessionalBySpecialty(@PathVariable String specialty) {
        List<ProfessionalDTO> professionals = ProfessionalDTO.convertList(professionalService.getProfessionalBySpecialty(specialty));
        return ResponseEntity.ok(professionals);
    }

    @PostMapping
    public ResponseEntity<ProfessionalDTO> createProfessional(@RequestBody ProfessionalDTO professionalDTO) {
        ProfessionalDTO professional = new ProfessionalDTO(professionalService.registerProfessional(professionalDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(professional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> updateProfessional(@PathVariable Long id, @RequestBody ProfessionalDTO professionalDTO) {
        ProfessionalDTO professional = new ProfessionalDTO(professionalService.updateProfessional(id, professionalDTO));
        return ResponseEntity.ok(professional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public TokenDTO auth(@RequestBody LoginDTO loginDTO) {
        try {
            Professional professional = Professional.builder()
                    .email(loginDTO.getEmail())
                    .pass(loginDTO.getPass())
                    .build();

            UserDetails userAuth = professionalService.auth(professional);
            String token = jwtService.generateToken(userAuth);
            return new TokenDTO(userAuth, token);

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
