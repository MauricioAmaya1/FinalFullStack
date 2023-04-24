package com.proyecto.proyecto.model.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersRequest {

    @NotBlank(message = "Nombre invalido: No puede ser vacio")
    @Size(min = 3, message = "Nombre invalido: Tiene que tener minimo 3 caracteres")
    private String first_name;
    @NotBlank(message = "Apellido invalido: No puede ser vacio")
    @Size(min = 3, message = "Apellido invalido: Tiene que tener minimo 3 caracteres")
    private String last_name;
    @Email(message = "Email invalido: Revise la informacion")
    @NotBlank(message = "Email invalido: No puede ser vacio")
    private String email;
    @NotBlank(message = "Password invalida: No puede ser vacio")
    @Size(min = 3, message = "Password invalida: Tiene que tener minimo 3 caracteres")
    private String password;

}
