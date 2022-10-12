package br.com.pi.serviceSchedulingApi.repository;

import br.com.pi.serviceSchedulingApi.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
}
