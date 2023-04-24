package com.proyecto.proyecto.controller;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import com.proyecto.proyecto.service.impl.CategoriaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaServiceImpl categoriaService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> registrarCategoria (@RequestBody @Valid CategoriaDTO categoriaDTO){
        CategoriaDTO categoria = categoriaService.agregarCategoria(categoriaDTO);
        return new ResponseEntity<>("Categoria guardada con ID: " + categoria.getId() ,null, HttpStatus.CREATED);
    }


    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> actualizarCategoria (@RequestBody @Valid CategoriaDTO categoria){
        categoriaService.actualizarCategoria(categoria);
        return new ResponseEntity<>("Categoria actualizada con ID: " + categoria.getId(),null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws ResourceNotFoundException {
        List<CategoriaDTO> categoriaList = categoriaService.listaCategoria();
        return new ResponseEntity<>(categoriaList,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId (@PathVariable Long id){
        CategoriaDTO categoria = categoriaService.buscarCategoria(id);
        return new ResponseEntity<>(categoria,null, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>("Se elimino la categoria con ID: " + id,null, HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<List<Integer>> listaCantidadCategorias(){
        var categoriaList = categoriaService.listaCantidadCategorias();
        return new ResponseEntity<>(categoriaList, null, HttpStatus.OK);
    }
}
