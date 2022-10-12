package br.com.pi.serviceSchedulingApi.service.user;

import br.com.pi.serviceSchedulingApi.dto.UserDTO;
import br.com.pi.serviceSchedulingApi.exception.NotFoundException;
import br.com.pi.serviceSchedulingApi.exception.PasswordInvalidException;
import br.com.pi.serviceSchedulingApi.model.UserApp;
import br.com.pi.serviceSchedulingApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserApp registerUser(UserDTO userDTO) {
        UserApp userEntity = UserApp.builder()
                            .email(userDTO.getEmail())
                            .phone(userDTO.getPhone())
                            .firstName(userDTO.getFirstName())
                            .lastName(userDTO.getLastName())
                            .pass(userDTO.getPass())
                            .cpf(userDTO.getCpf())
                            .build();

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserApp getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), "User"));
    }

    @Override
    public UserApp updateUser(Long id, UserDTO userDTO) {

        UserApp userEntity = userRepository.findById(id).get();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPass(userDTO.getPass());
        userEntity.setCpf(userDTO.getCpf());

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails auth(UserApp user) {
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        boolean passwordCorrect = Objects.equals(user.getPass(), userDetails.getPassword());

        if(passwordCorrect){
            return userDetails;
        }

        throw new PasswordInvalidException();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username invalid"));
        String[] roles = {"User"};

        return User
                .builder()
                .username(user.getEmail())
                .password(user.getPass())
                .roles(roles)
                .build();
    }
}
