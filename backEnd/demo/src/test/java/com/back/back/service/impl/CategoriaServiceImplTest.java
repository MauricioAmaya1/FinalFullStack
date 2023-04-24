package com.back.back.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import com.proyecto.proyecto.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaServiceImplTest {

    @Autowired
    CategoriaServiceImpl categoriaService;


//    @Test
//    @Order(1)
//    void guardarCategoria() throws BadRequestException {
//
//        Categoria categoria = new Categoria(1L,"depto","es un departamento","www.google.com/images");
//        Categoria categoriaAGuardar = categoriaService.agregarCategoria(categoria);
//        Assertions.assertNotNull(categoriaAGuardar);
//
//    }
//
//
//    @Test
//    @Order(2)
//    void buscarCategoria() throws BadRequestException {
//
//        Long idABuscar = 1L;
//        Optional<Categoria> categoria = categoriaService.buscarCategoria(idABuscar);
//        Assertions.assertNotNull(categoria.get());
//
//    }
//
//    @Test
//    @Order(3)
//    void buscarTodosCategorias() {
//        List<Categoria> categoria= categoriaService.listaCategoria();
//        Integer cantidadEsperada = 1;
//        Assertions.assertEquals(cantidadEsperada, categoria.size());
//    }
//
//
//
//    @Test
//    @Order(4)
//    void actualizarCategoria() throws BadRequestException {
//
//        Categoria categoriaActualizar = new Categoria(1L,"Casa","es una casa","www.google.com/");
//        categoriaService.actualizarCategoria(categoriaActualizar);
//        Optional<Categoria> categoriaActualizado= categoriaService.buscarCategoria(categoriaActualizar.getId());
//        assertEquals("Casa",categoriaActualizado.get().getTitulo());
//
//    }
//
//
//    @Test
//    @Order(5)
//    void eliminarCategoria() throws ResourceNotFoundException {
//
//        Long idAEliminar=1L;
//        categoriaService.eliminarCategoria(idAEliminar);
//        Optional<Categoria> odontologoEliminado = categoriaService.buscarCategoria(idAEliminar);
//        Assertions.assertFalse(odontologoEliminado.isPresent());
//
//    }

    @Test
    @Order((1))
    void agregarCategoria() throws BadRequestException {
        var categoriaToAdd = new CategoriaDTO();
        categoriaToAdd.setId(null);
        categoriaToAdd.setTitulo("Departamentos");
        categoriaToAdd.setDescripcion("Deptos hermosos");
        categoriaToAdd.setUrlImagen("depto.png");
        var categoriaAdded = categoriaService.agregarCategoria(categoriaToAdd);
        Assertions.assertNotNull(categoriaAdded.getId());
    }

    @Test
    @Order((2))
    void actualizarCategoria() throws BadRequestException {
        var categoriaToUpdate = new CategoriaDTO();
        categoriaToUpdate.setId(1L);
        categoriaToUpdate.setTitulo("Hoteles");
        categoriaToUpdate.setDescripcion("Hoteles lindos");
        categoriaToUpdate.setUrlImagen("hotel.png");
        var categoriaUpdated = categoriaService.actualizarCategoria(categoriaToUpdate);
        Assertions.assertEquals("Hoteles",categoriaUpdated.getTitulo());
    }

    @Test
    @Order((3))
    void buscarCategoria() throws ResourceNotFoundException {
        var idToFind = 1L;
        var categoriaFound = categoriaService.buscarCategoria(idToFind);
        //Assertions.assertEquals(idToFind, categoriaFound.get().getId());
    }


    @Test
    @Order((4))
    void listaCategoria() throws ResourceNotFoundException {
        var listaCategoria = categoriaService.listaCategoria();
        Assertions.assertNotEquals(0,listaCategoria.size());
    }

    @Test
    @Order((5))
    void eliminarCategoria() throws BadRequestException, ResourceNotFoundException {
        var categoriaToDelete = new CategoriaDTO();
        categoriaToDelete.setId(null);
        categoriaToDelete.setTitulo("Hoteles");
        categoriaToDelete.setDescripcion("Hoteles lindos");
        categoriaToDelete.setUrlImagen("hotel.png");
        var categoriaAdded = categoriaService.agregarCategoria(categoriaToDelete);
        var idToDelete = 2L;
        categoriaService.eliminarCategoria(idToDelete);
        Assertions.assertEquals(1,categoriaService.listaCategoria().size());
    }
}