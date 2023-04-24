package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Caracteristica;
import com.proyecto.proyecto.model.Caracteristica;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.model.DTO.CaracteristicaDTO;
import com.proyecto.proyecto.repository.CaracteristicaRepository;
import com.proyecto.proyecto.service.CaracteristicaService;
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
public class CaracteristicaServiceImpl implements CaracteristicaService{
    private final CaracteristicaRepository caracteristicaRepository;
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(CaracteristicaServiceImpl.class);
    @Override
    public CaracteristicaDTO agregarCaracteristica(CaracteristicaDTO caracteristicaDTO){
        Caracteristica caracteristica = Caracteristica.builder()
                .nombre(caracteristicaDTO.getNombre())
                .icono(caracteristicaDTO.getIcono())
                .build();
        return mapper.convertValue(caracteristicaRepository.save(caracteristica), CaracteristicaDTO.class);
    }
    @Override
    public CaracteristicaDTO actualizarCaracteristica(CaracteristicaDTO caracteristicaDTO){
        LOGGER.info("Se inicio una operacion de actualizado de caracteristica con ID: " + caracteristicaDTO.getId());
        caracteristicaRepository.findById(caracteristicaDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("La caracteristica con ID: " + caracteristicaDTO.getId() + " no existe"));
        return mapper.convertValue(caracteristicaRepository.save(mapper.convertValue(caracteristicaDTO, Caracteristica.class)), CaracteristicaDTO.class);
    }
    @Override
    public CaracteristicaDTO buscarCaracteristica(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de caracteristica con ID " + id);
        return caracteristicaRepository.findById(id)
                .map(category -> mapper.convertValue(category, CaracteristicaDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("La caracteristica con ID: " + id + " no existe"));
    }
    @Override
    public List<CaracteristicaDTO> listaCaracteristica() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de caracteristicas ");
        var list = caracteristicaRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de caracteristicas se encuentra vacia");
        }
        return list.stream().map(caracteristica -> mapper.convertValue(caracteristica, CaracteristicaDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarCaracteristica(Long id){
        caracteristicaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La caracteristica con ID: " + id + " no existe"));
        caracteristicaRepository.deleteById(id);
    }
}
