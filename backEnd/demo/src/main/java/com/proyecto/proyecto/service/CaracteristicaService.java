package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import java.util.List;
import java.util.Optional;

public interface CaracteristicaService {

    CaracteristicaDTO agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws BadRequestException;
    CaracteristicaDTO actualizarCaracteristica(CaracteristicaDTO caracteristica) throws BadRequestException;
    CaracteristicaDTO buscarCaracteristica(Long id) throws ResourceNotFoundException;
    List<CaracteristicaDTO> listaCaracteristica() throws ResourceNotFoundException;
    void eliminarCaracteristica(Long id) throws ResourceNotFoundException;

}
