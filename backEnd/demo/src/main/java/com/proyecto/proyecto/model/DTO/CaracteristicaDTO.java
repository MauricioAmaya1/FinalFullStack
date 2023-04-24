package com.proyecto.proyecto.model.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CaracteristicaDTO {

    private Long id;
    @NotBlank(message = "Nombre invalido: No puede ser vacio")
    @Size(min = 3,max = 30, message = "Nombre invalido:Tiene que tener entre 3 - 30 caracteres")
    private String nombre;
    @NotBlank(message = "Icono invalido: No puede ser vacio")
    @Size(min = 10, message = "Icono invalido: Tiene que tener como minimo 10 caracteres")
    private String icono;
}
