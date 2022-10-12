package br.com.pi.serviceSchedulingApi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String pass;
    private String cpf;

//    @OneToMany(mappedBy = "escritorio")
//    private List<Estacao> estacoes;
}
