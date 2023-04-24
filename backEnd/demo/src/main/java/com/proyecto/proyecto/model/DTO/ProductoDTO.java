package com.proyecto.proyecto.model.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
public class ProductoDTO{

    private Long id;

    @NotBlank(message = "Titulo invalido: No puede ser vacio")
    @Size(min = 3, message = "Titulo invalido: Tiene que tener minimo 3 caracteres")
    private String titulo;

    private CategoriaDTO categoria;

    private CiudadDTO ciudad;

    @NotNull(message = "Lista invalida: No puede ser vacio")
    private List<CaracteristicaDTO> caracteristicas;

    @NotNull(message = "Lista invalida: No puede ser vacio")
    @JsonIgnoreProperties(value = {"producto"})
    private List<ImagenDTO> listImagen;

    @NotBlank(message = "Review invalida: No puede ser vacio")
    @Size(min = 3, message = "Review invalida: Tiene que tener minimo 3 caracteres")
    private String review;

    @Min(value = 1, message = "Puntuacion invalida: Tiene que tener minimo 1 caracter")
    private Integer puntuacion;

    @Min(value = 1, message = "Estrellas invalida: Tiene que tener minimo 1 caracter")
    private Integer estrellas;

    @NotBlank(message = "Titulo de la Descripcion invalido: No puede ser vacio")
    @Size(min = 5, message = "Titulo de la Descripcion invalido: Tiene que tener minimo 5 caracteres")
    private String tituloDescripcion;

    @NotBlank(message = "Descripcion invalida: No puede ser vacio")
    @Size(min = 30, message = "Descripcion invalida: Tiene que tener minimo 30 caracteres")
    private String descripcion;

    @NotBlank(message = "Politica de lugar invalida: No puede ser vacio")
    @Size(min = 10, message = "Politica de lugar invalida: Tiene que tener minimo 10 caracteres")
    private String politicaLugar;

    @NotBlank(message = "Politica de Salud invalida: No puede ser vacio")
    @Size(min = 10, message = "Politica de Salud invalida: Tiene que tener minimo 10 caracteres")
    private String politicaSaludSeguridad;

    @NotBlank(message = "Politica de Cancelacion invalida: No puede ser vacio")
    @Size(min = 10, message = "Politica de Cancelacion invalida: Tiene que tener minimo 10 caracteres")
    private String politicaCancelacion;

    @Min(value = -100, message = "Latitud invalida: Tiene que tener maximo 1 caracter")
    private Double latitud;

    @Min(value = -100, message = "Longitud invalida: Tiene que tener maximo 1 caracter")
    private Double longitud;

    @Min(value = -100, message = "Altura invalida: Tiene que tener minimo 1 caracter")
    private Double altura;

    @NotBlank(message = "Distancia al centro invalida: No puede ser vacio")
    @Size(min = 10, message = "Distancia al centro invalida: Tiene que tener minimo 10 caracteres")
    private String distanciaCentro;

}
