package br.com.pi.serviceSchedulingApi.repository;

import br.com.pi.serviceSchedulingApi.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    List<Professional> findByFirstName(String firstName);
    List<Professional> findBySpecialty(String specialty);
    Optional<Professional> findByEmail(String email);
}
