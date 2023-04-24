package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsersController {

    private final UsersServiceImpl usersService;


    @PutMapping
    public ResponseEntity<String> actualizarUsers (@RequestBody UserDTO userDTO) throws BadRequestException {
        usersService.actualizarUser(userDTO);
        return new ResponseEntity<>("User actualizada con ID: " + userDTO.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> buscarUsersPorId (@PathVariable Long id) throws ResourceNotFoundException {
        UserDTO users = usersService.buscarUser(id).get();
        return new ResponseEntity<>(users,null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listarUsers()throws ResourceNotFoundException{
        List<UserDTO> usersList = usersService.listaUser();
        return new ResponseEntity<>(usersList,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsers(@PathVariable Long id) throws ResourceNotFoundException {
        usersService.eliminarUser(id);
        return new ResponseEntity<>("Se elimino la users con ID: " + id,null, HttpStatus.OK);
    }

}
