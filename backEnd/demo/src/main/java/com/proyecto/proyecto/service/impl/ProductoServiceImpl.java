package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.*;
import com.proyecto.proyecto.model.DTO.ProductoDTO;
import com.proyecto.proyecto.repository.CaracteristicaRepository;
import com.proyecto.proyecto.repository.ImagenRepository;
import com.proyecto.proyecto.repository.ProductoRepository;
import com.proyecto.proyecto.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ImagenRepository imagenRepository;
    private final CaracteristicaRepository caracteristicaRepository;
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(ImagenServiceImpl.class);
    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO){

        Producto producto = Producto
                .builder()
                .titulo(productoDTO.getTitulo())
                .categoria(mapper.convertValue(productoDTO.getCategoria(), Categoria.class))
                .ciudad(mapper.convertValue(productoDTO.getCiudad(), Ciudad.class))
                .caracteristicas(
                        productoDTO.getCaracteristicas().stream().map(caracteristicaDTO -> mapper.convertValue(caracteristicaDTO, Caracteristica.class)).collect(Collectors.toList())
                )
                .listImagen(new ArrayList<>())
                .review(productoDTO.getReview())
                .puntuacion(productoDTO.getPuntuacion())
                .estrellas(productoDTO.getEstrellas())
                .tituloDescripcion(productoDTO.getTituloDescripcion())
                .descripcion(productoDTO.getDescripcion())
                .politicaLugar(productoDTO.getPoliticaLugar())
                .politicaSaludSeguridad(productoDTO.getPoliticaSaludSeguridad())
                .politicaCancelacion(productoDTO.getPoliticaCancelacion())
                .latitud(productoDTO.getLatitud())
                .longitud(productoDTO.getLongitud())
                .altura(productoDTO.getAltura())
                .distanciaCentro(productoDTO.getDistanciaCentro())
                .build();
        Producto productoSaved = productoRepository.save(producto);

        productoSaved.setListImagen(productoDTO.getListImagen().stream().map(imagenDTO -> {
            imagenDTO.setProducto(productoSaved);
            Imagen img = mapper.convertValue(imagenDTO, Imagen.class);
            return imagenRepository.save(img);
        }).collect(Collectors.toList()));
        return mapper.convertValue(productoSaved, ProductoDTO.class);
    }
    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO){
        LOGGER.info("Se inicio una operacion de actualizado de producto con ID= " + productoDTO.getId());
        productoRepository.findById(productoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("El producto con ID: " + productoDTO.getId() + " no existe"));
        var producto = productoRepository.save(mapper.convertValue(productoDTO, Producto.class));
        return mapper.convertValue(producto, ProductoDTO.class);
    }
    @Override
    public ProductoDTO buscarProducto(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de Producto con ID " + id);
        return productoRepository.findById(id)
                .map(producto -> mapper.convertValue(producto, ProductoDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("El producto con ID: " + id + " no existe"));
    }
    @Override
    public List<ProductoDTO> listaProducto() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos ");
        var list = productoRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarProducto(Long id){
        productoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto con ID: " + id + " no existe"));
        productoRepository.deleteById(id);
    }
    public List<ProductoDTO> getRandomProducts() throws ResourceNotFoundException{
        LOGGER.info("Getting random productos");
        var list = productoRepository.getRandomProducts();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<ProductoDTO> findAllByCiudadId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos por Ciudad ID: " + id);
        var list = productoRepository.findAllByCiudadId(id);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
    public List<ProductoDTO> findAllByCategoriaId(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio una operacion de listado de productos por Categoria ID: " + id);
        var list = productoRepository.findAllByCategoriaId(id);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<ProductoDTO> findAllByRangeDate(Date fechaInicio, Date fechaFinal) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos desde: " + fechaInicio + " hasta " +  fechaFinal);
        var list = productoRepository.findAllByRangeDate(fechaInicio, fechaFinal);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<ProductoDTO> findAllByRangeDateAndCity(Date fechaInicio, Date fechaFinal, Long productoId) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de productos desde: " + fechaInicio + " hasta " +  fechaFinal + " y producto ID: " + productoId);
        var list = productoRepository.findAllByRangeDateAndCity(fechaInicio, fechaFinal, productoId);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        return list.stream().map(producto -> mapper.convertValue(producto, ProductoDTO.class)).collect(Collectors.toList());
    }
}
