package com.proyecto.proyecto.model.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.proyecto.model.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ImagenDTO {
    private Long id;
    @NotBlank(message = "Titulo invalido: No puede ser vacio")
    @Size(min = 3, max = 50, message = "Titulo invalido: Tiene que tener entre 3 - 50 caracteres")
    private String titulo;
    @NotBlank(message = "Url invalida: No puede ser vacio")
    @Size(min = 10, message = "Url invalida: Tiene que tener minimo 10 caracteres")
    private String url;
    private Producto producto;
}
