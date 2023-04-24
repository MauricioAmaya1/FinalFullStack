package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CiudadDTO;
import com.proyecto.proyecto.service.impl.CiudadServiceImpl;
import com.proyecto.proyecto.service.impl.CiudadServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ciudades")
@CrossOrigin("*")
public class CiudadController {

    private final CiudadServiceImpl ciudadService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarCiudad (@RequestBody @Valid CiudadDTO ciudadDTO){
        CiudadDTO ciudad = ciudadService.agregarCiudad(ciudadDTO);
        return new ResponseEntity<>("Ciudad guardada con ID: " + ciudad.getId() ,null, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarCiudad (@RequestBody @Valid CiudadDTO ciudad)  {
        ciudadService.actualizarCiudad(ciudad);
        return new ResponseEntity<>("Ciudad actualizada con ID: " + ciudad.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CiudadDTO>> listarCiudad() throws ResourceNotFoundException {
        List<CiudadDTO> ciudadList = ciudadService.listaCiudad();
        return new ResponseEntity<>(ciudadList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> buscarCiudadPorId (@PathVariable Long id){
        CiudadDTO ciudad = ciudadService.buscarCiudad(id);
        return new ResponseEntity<>(ciudad,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id){
        ciudadService.eliminarCiudad(id);
        return new ResponseEntity<>("Se elimino la ciudad con ID: " + id, null, HttpStatus.OK);
    }
}
