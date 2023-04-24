package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad,Long> {
}
