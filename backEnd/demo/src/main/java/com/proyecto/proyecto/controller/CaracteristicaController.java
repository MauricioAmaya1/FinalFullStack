package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.service.impl.CaracteristicaServiceImpl;
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
@RequestMapping("/caracteristicas")
@CrossOrigin("*")
public class CaracteristicaController {

    private final CaracteristicaServiceImpl caracteristicaService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> agregarCaracteristica (@RequestBody @Valid CaracteristicaDTO caracteristicaDTO){
        CaracteristicaDTO caracteristica = caracteristicaService.agregarCaracteristica(caracteristicaDTO);
        return new ResponseEntity<>("Caracteristica guardada con ID: " + caracteristica.getId() ,null, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarCaracteristica (@RequestBody @Valid CaracteristicaDTO caracteristica){
        caracteristicaService.actualizarCaracteristica(caracteristica);
        return new ResponseEntity<>("Caracteristica actualizada con ID: " + caracteristica.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaDTO> buscarCaracteristicaPorId (@PathVariable Long id){
        CaracteristicaDTO caracteristica = caracteristicaService.buscarCaracteristica(id);
        return new ResponseEntity<>(caracteristica,null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicaDTO>> listarCaracteristica()throws ResourceNotFoundException{
        List<CaracteristicaDTO> caracteristicaList = caracteristicaService.listaCaracteristica();
        return new ResponseEntity<>(caracteristicaList,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable Long id){
        caracteristicaService.eliminarCaracteristica(id);
        return new ResponseEntity<>("Se elimino la caracteristica con ID: " + id,null, HttpStatus.OK);
    }

}
