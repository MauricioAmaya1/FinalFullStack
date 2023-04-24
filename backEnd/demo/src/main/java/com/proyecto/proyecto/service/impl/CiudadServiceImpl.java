package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Ciudad;
import com.proyecto.proyecto.model.DTO.CiudadDTO;
import com.proyecto.proyecto.repository.CiudadRepository;
import com.proyecto.proyecto.service.CiudadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CiudadServiceImpl implements CiudadService {
    private final CiudadRepository ciudadRepository;
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(CiudadServiceImpl.class);
    @Override
    public CiudadDTO agregarCiudad(CiudadDTO ciudadDTO){
        Ciudad ciudad = Ciudad.builder()
                .nombre_ciudad(ciudadDTO.getNombre_ciudad())
                .nombre_pais(ciudadDTO.getNombre_pais())
                .build();
        return mapper.convertValue(ciudadRepository.save(ciudad), CiudadDTO.class);
    }
    @Override
    public CiudadDTO actualizarCiudad(CiudadDTO ciudadDTO){
        LOGGER.info("Se inicio una operacion de actualizado de ciudad con ID= " + ciudadDTO.getId());
        ciudadRepository.findById(ciudadDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("La ciudad con ID: " + ciudadDTO.getId() + " no existe"));
        return mapper.convertValue(ciudadRepository.save(mapper.convertValue(ciudadDTO, Ciudad.class)), CiudadDTO.class);
    }
    @Override
    public CiudadDTO buscarCiudad(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de ciudad con ID " + id);
        return ciudadRepository.findById(id)
                .map(city -> mapper.convertValue(city, CiudadDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("La ciudad con ID: " + id + " no existe"));
    }
    @Override
    public List<CiudadDTO> listaCiudad() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de ciudades ");
        var list = ciudadRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de ciudades se encuentra vacia");
        }
        return list.stream().map(city -> mapper.convertValue(city, CiudadDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarCiudad(Long id){
        ciudadRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La ciudad con ID: " + id + " no existe"));
        ciudadRepository.deleteById(id);
    }
}
