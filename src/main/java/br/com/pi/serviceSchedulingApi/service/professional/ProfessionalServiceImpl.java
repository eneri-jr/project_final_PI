package br.com.pi.serviceSchedulingApi.service.professional;

import br.com.pi.serviceSchedulingApi.dto.ProfessionalDTO;
import br.com.pi.serviceSchedulingApi.exception.NotFoundException;
import br.com.pi.serviceSchedulingApi.exception.PasswordInvalidException;
import br.com.pi.serviceSchedulingApi.model.Professional;
import br.com.pi.serviceSchedulingApi.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfessionalServiceImpl implements ProfessionalService, UserDetailsService {

    private ProfessionalRepository professionalRepository;

    @Autowired
    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Professional registerProfessional(ProfessionalDTO professionalDTO) {
        Professional professionalEntity = Professional.builder()
                .email(professionalDTO.getEmail())
                .phone(professionalDTO.getPhone())
                .firstName(professionalDTO.getFirstName())
                .lastName(professionalDTO.getLastName())
                .pass(professionalDTO.getPass())
                .cpf(professionalDTO.getCpf())
                .specialty(professionalDTO.getSpeciality())
                .build();

        return professionalRepository.save(professionalEntity);
    }

    @Override
    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    @Override
    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), "Professional"));
    }

    @Override
    public List<Professional> getProfessionalByFirstName(String name) {
        return professionalRepository.findByFirstName(name);
    }

    @Override
    public List<Professional> getProfessionalBySpecialty(String specialty) {
        return professionalRepository.findBySpecialty(specialty);
    }

    @Override
    public UserDetails auth(Professional professional) {
        UserDetails userDetails = loadUserByUsername(professional.getEmail());
        boolean passwordCorrect = Objects.equals(professional.getPass(), userDetails.getPassword());

        if(passwordCorrect){
            return userDetails;
        }

        throw new PasswordInvalidException();
    }

    @Override
    public Professional updateProfessional(Long id, ProfessionalDTO professionalDTO) {

        Professional professionalEntity = professionalRepository.findById(id).get();
        professionalEntity.setEmail(professionalDTO.getEmail());
        professionalEntity.setPhone(professionalDTO.getPhone());
        professionalEntity.setFirstName(professionalDTO.getFirstName());
        professionalEntity.setLastName(professionalDTO.getLastName());
        professionalEntity.setPass(professionalDTO.getPass());
        professionalEntity.setCpf(professionalDTO.getCpf());

        return professionalRepository.save(professionalEntity);
    }

    @Override
    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Professional professional = professionalRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username invalid"));
        String[] roles = {"Professional"};

        return User
                .builder()
                .username(professional.getEmail())
                .password(professional.getPass())
                .roles(roles)
                .build();
    }
}
