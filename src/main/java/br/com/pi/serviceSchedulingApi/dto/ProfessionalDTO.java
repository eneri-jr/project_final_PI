package br.com.pi.serviceSchedulingApi.dto;

import br.com.pi.serviceSchedulingApi.model.Professional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO {

    private long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String pass;
    private String cpf;
    private String speciality;

    public ProfessionalDTO(Professional professional) {
        this.id = professional.getId();
        this.email = professional.getEmail();
        this.phone = professional.getPhone();
        this.firstName = professional.getFirstName();
        this.lastName = professional.getLastName();
        this.pass = professional.getPass();
        this.cpf = professional.getCpf();
        this.speciality = professional.getSpecialty();
    }

    public static List<ProfessionalDTO> convertList(List<Professional> professionals) {
        return professionals.stream().map(ProfessionalDTO::new).collect(Collectors.toList());
    }
}
