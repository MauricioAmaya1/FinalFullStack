package com.proyecto.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre_ciudad;
    @Column
    private String nombre_pais;
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Producto> productosSet;
}
