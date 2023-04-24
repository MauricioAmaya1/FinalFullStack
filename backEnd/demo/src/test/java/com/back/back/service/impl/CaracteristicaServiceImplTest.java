package com.back.back.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.service.impl.CaracteristicaServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaracteristicaServiceImplTest {
    @Autowired
    CaracteristicaServiceImpl caracteristicaService;

    @Test
    @Order((1))
    void agregarCaracteristica() throws BadRequestException {
        var caracteristicaToAdd = new CaracteristicaDTO();
        caracteristicaToAdd.setId(null);
        caracteristicaToAdd.setNombre("wifi");
        caracteristicaToAdd.setIcono("wifi.png");
        var caracteristicaAdded = caracteristicaService.agregarCaracteristica(caracteristicaToAdd);
        Assertions.assertNotNull(caracteristicaAdded.getId());
    }

    @Test
    @Order((2))
    void actualizarCaracteristica() throws BadRequestException {
        var caracteristicaToUpdate = new CaracteristicaDTO();
        caracteristicaToUpdate.setId(1L);
        caracteristicaToUpdate.setNombre("perros");
        caracteristicaToUpdate.setIcono("perros.png");
        var caracteristicaUpdated = caracteristicaService.actualizarCaracteristica(caracteristicaToUpdate);
        Assertions.assertEquals("perros",caracteristicaUpdated.getNombre());
    }

    @Test
    @Order((3))
    void buscarCaracteristica() throws ResourceNotFoundException {
        var idToFind = 1L;
        var caracteristicaFound = caracteristicaService.buscarCaracteristica(idToFind);
        //Assertions.assertEquals(idToFind, caracteristicaFound.get().getId());
    }

    @Test
    @Order((4))
    void listaCaracteristica() throws ResourceNotFoundException {
        var listaCaracteristicas = caracteristicaService.listaCaracteristica();
        Assertions.assertNotEquals(0,listaCaracteristicas.size());
    }

    @Test
    @Order((5))
    void eliminarCaracteristica() throws ResourceNotFoundException, BadRequestException {
        var caracteristicaToDelete = new CaracteristicaDTO();
        caracteristicaToDelete.setId(null);
        caracteristicaToDelete.setNombre("wifi");
        caracteristicaToDelete.setIcono("wifi.png");
        var caracteristicaAdded = caracteristicaService.agregarCaracteristica(caracteristicaToDelete);
        var idToDelete = 2L;
        caracteristicaService.eliminarCaracteristica(idToDelete);
        Assertions.assertEquals(1,caracteristicaService.listaCaracteristica().size());
    }
}