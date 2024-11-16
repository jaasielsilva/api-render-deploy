package com.api.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private String status;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private LocalDateTime ultimaAcao;
    private LocalDateTime ultimoAcesso;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Método que define a data de criação automaticamente
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);  // Truncando para remover milissegundos
    }

    // Método para atualizar a última ação
    public void atualizarUltimaAcao() {
        this.ultimaAcao = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);  // Truncando para remover milissegundos
    }

    // Método para atualizar o último acesso
    public void atualizarUltimoAcesso() {
        this.ultimoAcesso = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);  // Truncando para remover milissegundos
    }
}
