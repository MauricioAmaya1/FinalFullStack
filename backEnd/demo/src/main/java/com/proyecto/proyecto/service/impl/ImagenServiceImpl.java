package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Imagen;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.model.DTO.ImagenDTO;
import com.proyecto.proyecto.model.Imagen;
import com.proyecto.proyecto.repository.ImagenRepository;
import com.proyecto.proyecto.service.ImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagenServiceImpl implements ImagenService {

    private final ImagenRepository imagenRepository;
    private final ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(ImagenServiceImpl.class);
    @Override
    public ImagenDTO agregarImagen(ImagenDTO imagenDTO){
        Imagen imagen = Imagen.builder()
                .titulo(imagenDTO.getTitulo())
                .url(imagenDTO.getUrl())
                .build();
        return mapper.convertValue(imagenRepository.save(imagen), ImagenDTO.class);
    }
    @Override
    public ImagenDTO actualizarImagen(ImagenDTO imagenDTO){
        LOGGER.info("Se inicio una operacion de actualizado de imagen con ID: " + imagenDTO.getId());
        imagenRepository.findById(imagenDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("La imagen con ID: " + imagenDTO.getId() + " no existe"));
        return mapper.convertValue(imagenRepository.save(mapper.convertValue(imagenDTO, Imagen.class)), ImagenDTO.class);
    }
    @Override
    public ImagenDTO buscarImagen(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de imagen con ID " + id);
        return imagenRepository.findById(id)
                .map(category -> mapper.convertValue(category, ImagenDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("La imagen con ID: " + id + " no existe"));
    }
    @Override
    public List<ImagenDTO> listaImagen() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de imagenes");
        var list = imagenRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de imagenes se encuentra vacia");
        }
        return list.stream().map(imagen -> mapper.convertValue(imagen, ImagenDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarImagen(Long id){
        imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La imagen con ID: " + id + " no existe"));
        imagenRepository.deleteById(id);
    }
}
