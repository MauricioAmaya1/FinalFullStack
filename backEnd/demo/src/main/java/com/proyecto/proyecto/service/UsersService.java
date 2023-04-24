package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UsersService {

    UserDTO actualizarUser(UserDTO userDTO) throws BadRequestException;
    Optional<UserDTO> buscarUser(Long id) throws ResourceNotFoundException;
    List<UserDTO> listaUser() throws ResourceNotFoundException;
    void eliminarUser(Long id) throws ResourceNotFoundException;

}
