package br.com.pi.serviceSchedulingApi.dto;

import br.com.pi.serviceSchedulingApi.model.UserApp;
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
public class UserDTO {

    private long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String pass;
    private String cpf;

    public UserDTO(UserApp user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.pass = user.getPass();
        this.cpf = user.getCpf();
    }

    public static List<UserDTO> convertList(List<UserApp> users) {
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
