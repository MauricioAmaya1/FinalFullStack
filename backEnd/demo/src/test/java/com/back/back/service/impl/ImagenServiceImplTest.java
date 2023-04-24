package com.back.back.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.service.impl.ImagenServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImagenServiceImplTest {

    @Autowired
    ImagenServiceImpl imagenService;

    @Test
    @Order((1))
    void agregarImagen() throws BadRequestException {
        var imagenToAdd = new ImagenDTO();
        imagenToAdd.setId(null);
        imagenToAdd.setTitulo("depto1");
        imagenToAdd.setUrl("depto1.com");
        var imagenAdded = imagenService.agregarImagen(imagenToAdd);
        Assertions.assertNotNull(imagenAdded.getId());
    }

    @Test
    @Order((2))
    void actualizarImagen() throws BadRequestException {
        var ImagenToUpdate = new ImagenDTO();
        ImagenToUpdate.setId(1L);
        ImagenToUpdate.setTitulo("hotel1");
        ImagenToUpdate.setUrl("hotel1.png");
        var imagenUpdated = imagenService.actualizarImagen(ImagenToUpdate);
        Assertions.assertEquals("hotel1",imagenUpdated.getTitulo());
    }

    @Test
    @Order((3))
    void buscarImagen() throws ResourceNotFoundException {
        var idToFind = 1L;
        var imagenFound = imagenService.buscarImagen(idToFind);
        //Assertions.assertEquals(idToFind, imagenFound.get().getId());
    }

    @Test
    @Order((4))
    void listaImagen() throws ResourceNotFoundException {
        var listaImagen = imagenService.listaImagen();
        Assertions.assertNotEquals(0,listaImagen.size());
    }

    @Test
    @Order((5))
    void eliminarImagen() throws BadRequestException, ResourceNotFoundException {
        var ImagenToDelete = new ImagenDTO();
        ImagenToDelete.setId(null);
        ImagenToDelete.setTitulo("Hoteles");
        ImagenToDelete.setUrl("hotel.png");
        var imagenAdded = imagenService.agregarImagen(ImagenToDelete);
        var idToDelete = 2L;
        imagenService.eliminarImagen(idToDelete);
        Assertions.assertEquals(1,imagenService.listaImagen().size());

    }
}