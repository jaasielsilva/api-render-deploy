package com.api.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Método para buscar um usuário pelo nome de usuário
    User findByUsername(String username);

    // Método para contar a quantidade de usuários por papel (role)
    long countByRole(String role);

    // Método para buscar o último usuário cadastrado
    User findTopByOrderByCreatedAtDesc();

    // Método para buscar usuários criados após uma data específica
    List<User> findByCreatedAtAfter(LocalDateTime date);

    
}
