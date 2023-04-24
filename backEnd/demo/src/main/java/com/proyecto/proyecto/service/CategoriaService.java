package com.proyecto.proyecto.service;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) throws BadRequestException;

    CategoriaDTO actualizarCategoria(CategoriaDTO categoria) throws BadRequestException;
    CategoriaDTO buscarCategoria(Long id) throws ResourceNotFoundException;
    List<CategoriaDTO> listaCategoria() throws ResourceNotFoundException;
    void eliminarCategoria(Long id) throws ResourceNotFoundException;

    List<Integer> listaCantidadCategorias() throws ResourceNotFoundException;
}
