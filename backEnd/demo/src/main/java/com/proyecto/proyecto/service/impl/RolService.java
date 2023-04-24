package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository rolRepository;
    public Rol addRol(Rol rol){
        return rolRepository.save(rol);
    }
}
