package com.back.back.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.service.impl.CategoriaServiceImpl;
import com.proyecto.proyecto.service.impl.CiudadServiceImpl;
import com.proyecto.proyecto.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductoServiceImplTest {

    @Autowired
    ProductoServiceImpl productoService;
    @Autowired
    CiudadServiceImpl ciudadService;
    @Autowired
    CategoriaServiceImpl categoriaService;

    @Test
    @Order((1))
    void agregarProducto() throws BadRequestException {
        var productoToAdd = new ProductoDTO();
        List<ImagenDTO> listImagen = new ArrayList<>();
        productoToAdd.setId(null);
        productoToAdd.setTitulo("Departamento Blanco");
        productoToAdd.setListImagen(listImagen);
        var productoAdded = productoService.agregarProducto(productoToAdd);
        Assertions.assertNotNull(productoAdded.getId());
    }

    @Test
    @Order((2))
    void actualizarProducto() throws BadRequestException {
        var productoToUpdate = new ProductoDTO();
        List<ImagenDTO> listImagen = new ArrayList<>();
        productoToUpdate.setId(1L);
        productoToUpdate.setTitulo("Hotel Lujoso");
        productoToUpdate.setListImagen(listImagen);
        var productoUpdated = productoService.actualizarProducto(productoToUpdate);
        Assertions.assertEquals("Hotel Lujoso",productoUpdated.getTitulo());
    }

    @Test
    @Order((3))
    void buscarProducto() throws ResourceNotFoundException {
        var idToFind = 1L;
        var productoFound = productoService.buscarProducto(idToFind);
        //Assertions.assertEquals(idToFind, productoFound.get().getId());
    }

    @Test
    @Order((4))
    void listaProducto() throws ResourceNotFoundException {
        var listaProducto = productoService.listaProducto();
        Assertions.assertNotEquals(0,listaProducto.size());
    }

    @Test
    @Order((5))
    void eliminarProducto() throws BadRequestException, ResourceNotFoundException {
        var productoToDelete = new ProductoDTO();
        List<ImagenDTO> listImagen = new ArrayList<>();
        productoToDelete.setId(2L);
        productoToDelete.setTitulo("Hotel Feo");
        productoToDelete.setListImagen(listImagen);
        var productoAdded = productoService.agregarProducto(productoToDelete);
        var idToDelete = 2L;
        productoService.eliminarProducto(idToDelete);
        Assertions.assertEquals(1,productoService.listaProducto().size());
    }

//    @Test
//    @Order((6))
//    void findAllByCiudadId() throws BadRequestException {
//        var productoToAdd = new ProductoDTO();
//        var productoToAdd2 = new ProductoDTO();
//        List<ImagenDTO> listImagen = new ArrayList<>();
//        List<ImagenDTO> listImagen2 = new ArrayList<>();
//        CiudadDTO ciudadDTO = new CiudadDTO();
//        ciudadDTO.setId(null);
//        ciudadDTO.setNombre_ciudad("Cordoba");
//        ciudadService.agregarCiudad(ciudadDTO);
//        productoToAdd.setId(null);
//        productoToAdd.setTitulo("Hotel Cordobes1");
//        productoToAdd.setListImagen(listImagen);
//        productoToAdd.setCiudad(ciudadDTO);
//        productoService.agregarProducto(productoToAdd);
//        productoToAdd2.setId(null);
//        productoToAdd2.setTitulo("Hotel Cordobes2");
//        productoToAdd2.setListImagen(listImagen2);
//        productoToAdd2.setCiudad(ciudadDTO);
//        productoService.agregarProducto(productoToAdd2);
//        Assertions.assertEquals(2,productoService.findAllByCiudadId(1L).get().size());
//    }
//
//    @Test
//    @Order((7))
//    void findAllByCategoriaId() throws BadRequestException {
//        var productoToAdd = new ProductoDTO();
//        var productoToAdd2 = new ProductoDTO();
//        List<ImagenDTO> listImagen = new ArrayList<>();
//        List<ImagenDTO> listImagen2 = new ArrayList<>();
//        CategoriaDTO categoriaDTO = new CategoriaDTO();
//        categoriaDTO.setId(null);
//        categoriaDTO.setTitulo("Hotel");
//        categoriaDTO.setDescripcion("Hoteles lindos");
//        categoriaDTO.setUrlImagen("hotel.png");
//        categoriaService.agregarCategoria(categoriaDTO);
//        productoToAdd.setId(null);
//        productoToAdd.setTitulo("Hotel Cordobes1");
//        productoToAdd.setListImagen(listImagen);
//        productoToAdd.setCategoria(categoriaDTO);
//        productoService.agregarProducto(productoToAdd);
//        productoToAdd2.setId(null);
//        productoToAdd2.setTitulo("Hotel Cordobes2");
//        productoToAdd2.setListImagen(listImagen2);
//        productoToAdd2.setCategoria(categoriaDTO);
//        productoService.agregarProducto(productoToAdd2);
//        Assertions.assertEquals(2,productoService.findAllByCiudadId(1L).get().size());
//    }

//    @Test
//    @Order((8))
//    void getRandomProducts() {
//    }
}