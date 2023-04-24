package com.proyecto.proyecto.model.DTO;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.model.Producto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.joda.time.LocalDate;

import java.util.Date;

@Data
public class ReservaDTO {

    private Long id;
    private String horaReserva;
    private Date fechaInicio;
    private Date fechaFinal;
    private Producto producto;
    private Users user;

}
