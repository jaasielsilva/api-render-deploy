package com.api.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.app.model.User;
import com.api.app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para salvar o usuário
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Método para obter o total de pessoas cadastradas
    public long getTotalPessoasCadastradas() {
        return userRepository.count(); // Retorna o total de usuários cadastrados
    }

    // Método para obter a última atividade
    public LocalDateTime getLastActivity() {
        User lastUser = userRepository.findTopByOrderByCreatedAtDesc();
        if (lastUser != null) {
            return lastUser.getCreatedAt(); // Retorna o campo createdAt do último usuário
        }
        return null; // Caso não exista usuários, retorna null
    }

    // Método para buscar novos usuários cadastrados após uma data específica (uma semana atrás)
    public List<User> getNewUsersSince(LocalDateTime date) {
        return userRepository.findByCreatedAtAfter(date); // Retorna lista de usuários após a data informada
    }
}
