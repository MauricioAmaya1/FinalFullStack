package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica,Long> {
}
