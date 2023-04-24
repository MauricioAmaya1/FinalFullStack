package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import java.util.List;
import java.util.Optional;

public interface ImagenService {
    ImagenDTO agregarImagen(ImagenDTO imagenDTO) throws BadRequestException;

    ImagenDTO actualizarImagen(ImagenDTO imagen) throws BadRequestException;
    ImagenDTO buscarImagen(Long id) throws ResourceNotFoundException;
    List<ImagenDTO> listaImagen() throws ResourceNotFoundException;
    void eliminarImagen(Long id) throws ResourceNotFoundException;
}
