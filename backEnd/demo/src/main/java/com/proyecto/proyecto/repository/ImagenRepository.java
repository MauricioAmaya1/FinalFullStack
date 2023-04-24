package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImagenRepository extends JpaRepository<Imagen,Long> {
}
