package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.model.Cliente;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String email);

}
