package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.DTO.AuthenticationRequest;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody AuthenticationRequest request) throws BadRequestException{
        var userAuthenticated = authService.authenticate(request);
        return new ResponseEntity<>(userAuthenticated,null,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UsersRequest user) throws BadRequestException {
        var msj = authService.addUser(user);
        return new ResponseEntity<>(msj,null, HttpStatus.CREATED);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid UsersRequest admin) throws Exception {
        var msj = authService.addAdmin(admin);
        return new ResponseEntity<>(msj ,null, HttpStatus.CREATED);
    }

}
