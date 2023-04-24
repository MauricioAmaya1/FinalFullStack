package com.proyecto.proyecto.service;

import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.model.Producto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDTO agregarProducto(ProductoDTO productoDTO) throws BadRequestException;

    ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws BadRequestException;
    ProductoDTO buscarProducto(Long id) throws ResourceNotFoundException;
    List<ProductoDTO> listaProducto() throws ResourceNotFoundException;
    void eliminarProducto(Long id) throws ResourceNotFoundException;

    List<ProductoDTO> findAllByCiudadId(Long id) throws ResourceNotFoundException;
    List<ProductoDTO> findAllByCategoriaId(Long id)throws ResourceNotFoundException;
    List<ProductoDTO> getRandomProducts() throws ResourceNotFoundException;

    List<ProductoDTO> findAllByRangeDate(Date fechaInicio, Date fechaFinal) throws ResourceNotFoundException;

    List<ProductoDTO> findAllByRangeDateAndCity(Date fechaInicio, Date fechaFinal,Long ciudadId) throws ResourceNotFoundException;

}