package com.api.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")  // Nome da tabela no banco de dados
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Nome da permissão (por exemplo: "ADMIN", "USER", etc.)

    // Outros campos relacionados à permissão, como descrição
    private String description;
}
