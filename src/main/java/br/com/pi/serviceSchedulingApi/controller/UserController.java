package br.com.pi.serviceSchedulingApi.controller;

import br.com.pi.serviceSchedulingApi.dto.LoginDTO;
import br.com.pi.serviceSchedulingApi.dto.TokenDTO;
import br.com.pi.serviceSchedulingApi.dto.UserDTO;
import br.com.pi.serviceSchedulingApi.exception.PasswordInvalidException;
import br.com.pi.serviceSchedulingApi.model.UserApp;
import br.com.pi.serviceSchedulingApi.service.jwt.JwtService;
import br.com.pi.serviceSchedulingApi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = UserDTO.convertList(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = new UserDTO(userService.getUserById(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = new UserDTO(userService.registerUser(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateDesk(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = new UserDTO(userService.updateUser(id, userDTO));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDesk(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public TokenDTO auth(@RequestBody LoginDTO loginDTO) {
        try {
            UserApp user = UserApp.builder()
                    .email(loginDTO.getEmail())
                    .pass(loginDTO.getPass())
                    .build();

            UserDetails userAuth = userService.auth(user);
            String token = jwtService.generateToken(userAuth);
            return new TokenDTO(userAuth, token);

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
