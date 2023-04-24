package com.proyecto.proyecto.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.Reserva;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    @Autowired
    ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(ImagenServiceImpl.class);

    @Override
    public UserDTO actualizarUser(UserDTO userDTO) throws BadRequestException {
        LOGGER.info("Se inicio una operacion de actualizado de User con ID= " + userDTO.getId());
        Users user = mapper.convertValue(userDTO, Users.class);
        Optional<Users> userBuscado = usersRepository.findById(user.getId());
        if (userBuscado.isPresent()){
            user.setPassword(userBuscado.get().getPassword());
            Users userUpdated = usersRepository.save(user);
            return mapper.convertValue(userUpdated,UserDTO.class);
        } else {
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
    }

    @Override
    public Optional<UserDTO> buscarUser(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de user con ID " + id);
        Optional<Users> userBuscado = usersRepository.findById(id);
        if (userBuscado.isPresent()){
            UserDTO userDTO= mapper.convertValue(userBuscado.get(),UserDTO.class);
            return Optional.of(userDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la Reserva con ID: " + id);
        }
    }

    @Override
    public List<UserDTO> listaUser() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de usuarios ");
        List<Users> userList = usersRepository.findAll();
        List<UserDTO> listReturn = new ArrayList<>();
        if (userList.isEmpty()){
            throw new ResourceNotFoundException("La lista de Reservaes se encuentra vacia");
        }
        for (Users user:userList) {
            UserDTO userDTO = mapper.convertValue(user, UserDTO.class);
            listReturn.add(userDTO);
        }
        return listReturn;
    }

    @Override
    public void eliminarUser(Long id) throws ResourceNotFoundException {
        var userAEliminar = usersRepository.findById(id);
        if (userAEliminar.isPresent()){
            usersRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de user con id " + id);
        }
        else {
            throw new ResourceNotFoundException("La Reserva a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito el id= "+id);
        }
    }
}
