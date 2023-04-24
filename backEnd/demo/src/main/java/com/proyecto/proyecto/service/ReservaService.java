package com.proyecto.proyecto.service;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.model.Producto;
import com.proyecto.proyecto.model.Reserva;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    ReservaDTO agregarReserva(ReservaDTO Reserva) throws BadRequestException, ResourceNotFoundException;
    ReservaDTO actualizarReserva(ReservaDTO Reserva) throws BadRequestException;
    ReservaDTO buscarReserva(Long id) throws ResourceNotFoundException;
    List<ReservaDTO> listaReserva() throws ResourceNotFoundException;
    void eliminarReserva(Long id) throws ResourceNotFoundException;
    List<ReservaDTO> findAllByProductoId(Long id) throws ResourceNotFoundException;
    List<ReservaDTO> findAllByUserId(Long id) throws ResourceNotFoundException;
}
