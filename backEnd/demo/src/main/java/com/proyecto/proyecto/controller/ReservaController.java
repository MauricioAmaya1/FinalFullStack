package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.model.Reserva;
import com.proyecto.proyecto.service.impl.ReservaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("reservaController")
@RequestMapping("/reservas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaServiceImpl reservaService;

    // @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<String> registrarReserva (@RequestBody @Valid ReservaDTO reservaDTO){
        ReservaDTO reserva = reservaService.agregarReserva(reservaDTO);
        return new ResponseEntity<>("Reserva guardada con ID: " + reserva.getId(), null, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarReserva (@RequestBody @Valid ReservaDTO reservaDTO){
        reservaService.actualizarReserva(reservaDTO);
        return new ResponseEntity<>("Reserva actualizada con ID: " + reservaDTO.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReserva() throws ResourceNotFoundException {
        List<ReservaDTO> reservaList = reservaService.listaReserva();
        return new ResponseEntity<>(reservaList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId (@PathVariable Long id){
        ReservaDTO reservaDTO = reservaService.buscarReserva(id);
        return new ResponseEntity<>(reservaDTO,null, HttpStatus.OK);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<ReservaDTO>> listarReservaPorProductoId(@PathVariable Long id){
        var reservaList = reservaService.findAllByProductoId(id);
        return new ResponseEntity<>(reservaList,null, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservaDTO>> listarReservaPorUserId(@PathVariable Long id) throws ResourceNotFoundException {
        var reservaList = reservaService.findAllByUserId(id);
        return new ResponseEntity<>(reservaList,null, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>("Se elimino la reserva con ID: " + id, null, HttpStatus.OK);
    }


}
