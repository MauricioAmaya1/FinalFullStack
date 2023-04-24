package com.back.back.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CiudadDTO;
import com.proyecto.proyecto.service.impl.CiudadServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CiudadServiceImplTest {
    @Autowired
    private CiudadServiceImpl ciudadService;


    @Test
    @Order((1))
    void agregarCiudad() throws BadRequestException {
        var ciudadToAdd = new CiudadDTO();
        ciudadToAdd.setId(null);
        ciudadToAdd.setNombre_ciudad("Buenos Aires");
        ciudadToAdd.setNombre_pais("Argentina");
        var ciudadAdded = ciudadService.agregarCiudad(ciudadToAdd);
        Assertions.assertNotNull(ciudadAdded.getId());
    }

    @Test
    @Order((2))
    void actualizarCiudad() throws BadRequestException {
        var ciudadToUpdate = new CiudadDTO();
        ciudadToUpdate.setId(1L);
        ciudadToUpdate.setNombre_ciudad("New York");
        ciudadToUpdate.setNombre_pais("Usa");
        var ciudadUpdated = ciudadService.actualizarCiudad(ciudadToUpdate);
        Assertions.assertEquals("New York",ciudadUpdated.getNombre_ciudad());
    }

    @Test
    @Order((3))
    void buscarCiudad() throws ResourceNotFoundException {
        var idToFind = 1L;
        var ciudadFound = ciudadService.buscarCiudad(idToFind);
        //Assertions.assertEquals(idToFind, ciudadFound.get().getId());
    }

    @Test
    @Order((4))
    void listaCiudad() throws ResourceNotFoundException {
        var listaCiudad = ciudadService.listaCiudad();
        Assertions.assertNotEquals(0,listaCiudad.size());
    }

    @Test
    @Order((5))
    void eliminarCiudad() throws ResourceNotFoundException, BadRequestException {
        var ciudadToDelete = new CiudadDTO();
        ciudadToDelete.setId(null);
        ciudadToDelete.setNombre_pais("Mexico");
        ciudadToDelete.setNombre_ciudad("Ciudad de mexico");
        var ciudadAdded = ciudadService.agregarCiudad(ciudadToDelete);
        var idToDelete = 2L;
        ciudadService.eliminarCiudad(idToDelete);
        Assertions.assertEquals(1,ciudadService.listaCiudad().size());
    }
}