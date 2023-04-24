package com.proyecto.proyecto.model.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CiudadDTO {

    private Long id;

    @NotBlank(message = "Nombre de ciudad invalido: No puede ser vacio")
    @Size(min = 3, max = 50, message = "Nombre de ciudad invalido: Tiene que tener entre 3 - 30 caracteres")
    private String nombre_ciudad;

    @NotBlank(message = "Nombre de pais invalido: No puede ser vacio")
    @Size(min = 3, max = 30, message = "Nombre de pais invalido: Tiene que tener entre 3 - 30 caracteres")
    private String nombre_pais;
}
