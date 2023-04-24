package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.model.Producto;
import com.proyecto.proyecto.service.impl.ProductoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {
    private final ProductoServiceImpl productoService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarProducto (@RequestBody @Valid ProductoDTO productoDTO){
        ProductoDTO producto = productoService.agregarProducto(productoDTO);
        return new ResponseEntity<>("Producto guardado con ID: " + producto.getId(), null, HttpStatus.CREATED);
    }


    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarProducto (@RequestBody @Valid  ProductoDTO productoDTO){
        productoService.actualizarProducto(productoDTO);
        return new ResponseEntity<>("Producto actualizado con titulo: " + productoDTO.getTitulo(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() throws ResourceNotFoundException {
        List<ProductoDTO> productoList = productoService.listaProducto();
        return new ResponseEntity<>(productoList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId (@PathVariable Long id){
        ProductoDTO productoDTO = productoService.buscarProducto(id);
        return new ResponseEntity<>(productoDTO,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>("Se elimino el producto con ID: " + id, null, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<List<ProductoDTO>> findAllByCiudadId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productoService.findAllByCiudadId(id));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> findAllByCategoriaId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productoService.findAllByCategoriaId(id));
    }

    @GetMapping("/random")
    public ResponseEntity<List<ProductoDTO>> getRandomProducts() throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.getRandomProducts());
    }

    @GetMapping("/date")
    public ResponseEntity<List<ProductoDTO>> findAllByRangeDate(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal)
            throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.findAllByRangeDate(fechaInicio, fechaFinal));
    }

    @GetMapping("/dateCiudad")
    public ResponseEntity<List<ProductoDTO>> findAllByRangeDateAndCity
            (   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal,
                @RequestParam Long ciudadId)
            throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.findAllByRangeDateAndCity(fechaInicio, fechaFinal,ciudadId));
    }



}