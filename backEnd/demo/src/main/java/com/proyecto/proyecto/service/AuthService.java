package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.model.DTO.AuthenticationRequest;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.DTO.UsersRequest;

import java.util.Optional;

public interface AuthService {
    String addUser(UsersRequest user) throws BadRequestException;
    String addAdmin(UsersRequest user) throws BadRequestException;
    UserDTO authenticate(AuthenticationRequest request) throws BadRequestException;
}
