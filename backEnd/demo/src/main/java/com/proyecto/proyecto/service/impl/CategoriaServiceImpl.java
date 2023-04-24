package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Categoria;
import com.proyecto.proyecto.model.DTO.CategoriaDTO;
import com.proyecto.proyecto.repository.CategoriaRepository;
import com.proyecto.proyecto.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(CategoriaServiceImpl.class);

    @Override
    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = Categoria.builder()
                .titulo(categoriaDTO.getTitulo())
                .descripcion(categoriaDTO.getDescripcion())
                .urlImagen(categoriaDTO.getUrlImagen())
                .build();
        return mapper.convertValue(categoriaRepository.save(categoria), CategoriaDTO.class);
    }
    @Override
    public CategoriaDTO actualizarCategoria(CategoriaDTO categoriaDTO){
        LOGGER.info("Se inicio una operacion de actualizado de categoria con ID= " + categoriaDTO.getId());
        categoriaRepository.findById(categoriaDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con ID: " + categoriaDTO.getId() + " no existe"));
        return mapper.convertValue(categoriaRepository.save(mapper.convertValue(categoriaDTO, Categoria.class)), CategoriaDTO.class);
    }
    @Override
    public CategoriaDTO buscarCategoria(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de categoria con ID " + id);
        return categoriaRepository.findById(id)
                .map(category -> mapper.convertValue(category, CategoriaDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con ID: " + id + " no existe"));
    }
    @Override
    public List<CategoriaDTO> listaCategoria() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de categoriaas ");
        var list = categoriaRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de categorias se encuentra vacia");
        }
        return list.stream().map(category -> mapper.convertValue(category, CategoriaDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarCategoria(Long id){
        categoriaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La categoria con ID: " + id + " no existe"));
        categoriaRepository.deleteById(id);
    }
    @Override
    public List<Integer> listaCantidadCategorias(){
        LOGGER.info("Se inicio una operacion de listado de categorias ");
        return categoriaRepository.listaCantidadCategorias()
                .orElseThrow(()-> new ResourceNotFoundException("No hay categorias disponibles para contar"));

    }

}