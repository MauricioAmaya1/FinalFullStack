package com.proyecto.proyecto.model.DTO;

import com.proyecto.proyecto.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String token;
    private List<Rol> roles;
}
