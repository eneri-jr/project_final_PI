package br.com.pi.serviceSchedulingApi.service.professional;

import br.com.pi.serviceSchedulingApi.dto.ProfessionalDTO;
import br.com.pi.serviceSchedulingApi.model.Professional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ProfessionalService {
    Professional registerProfessional(ProfessionalDTO professionalDTO);

    List<Professional> getAllProfessionals();

    Professional getProfessionalById(Long id);

    Professional updateProfessional(Long id, ProfessionalDTO professionalDTO);

    List<Professional> getProfessionalByFirstName(String name);

    List<Professional> getProfessionalBySpecialty(String specialty);

    UserDetails auth(Professional professional);

    void deleteProfessional(Long id);
}
