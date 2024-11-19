package com.api.app.repository;

import com.api.app.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados se necessário
}
