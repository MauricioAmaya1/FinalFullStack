package com.proyecto.proyecto.model.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CategoriaDTO {

    private Long id;
    @NotBlank(message = "Titulo invalido: No puede ser vacio")
    @Size(min = 3, max = 30, message = "Titulo invalido: Tiene que tener entre 3 - 30 characters")
    private String titulo;
    @NotBlank(message = "Descripcion invalida: No puede ser vacia")
    @Size(min = 10, message = "Descripcion invalida: Tiene que tener  10 caracteres como minimo")
    private String descripcion;
    @NotBlank(message = "Url invalida: No puede ser vacio")
    @Size(min = 10, message = "Url invalida: Tiene que tener  10 caracteres como minimo")
    private String urlImagen;

}
