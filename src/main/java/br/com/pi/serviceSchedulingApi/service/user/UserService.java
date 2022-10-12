package br.com.pi.serviceSchedulingApi.service.user;

import br.com.pi.serviceSchedulingApi.dto.UserDTO;
import br.com.pi.serviceSchedulingApi.model.UserApp;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserApp registerUser(UserDTO userDTO);

    List<UserApp> getAllUsers();

    UserApp getUserById(Long id);

    UserApp updateUser(Long id, UserDTO userDTO);

    UserDetails auth(UserApp user);

    void deleteUser(Long id);
}
