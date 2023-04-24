package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.service.impl.ReservaServiceImpl;
import com.proyecto.proyecto.service.impl.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("rolController")
@RequestMapping("/roles")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

//    @PreAuthorize(value = "hasRole('ROLE_MOD')")
    @PostMapping
    public ResponseEntity<String> registrarRol (@RequestBody Rol rol){
        var rolSaved = rolService.addRol(rol);
        return new ResponseEntity<>("Rol guardada con ID: " + rolSaved.getId(), null, HttpStatus.CREATED);
    }

}
