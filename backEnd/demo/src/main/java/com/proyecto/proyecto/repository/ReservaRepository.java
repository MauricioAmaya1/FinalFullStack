package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findAllByProductoId(Long id) throws ResourceNotFoundException;
    List<Reserva> findAllByUserId(Long id) throws ResourceNotFoundException;
}
