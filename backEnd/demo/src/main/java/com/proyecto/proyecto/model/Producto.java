package com.proyecto.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Ciudad ciudad;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(
            name = "productos_caracteristicas",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value={"producto"})
    private List<Imagen> listImagen = new ArrayList<>();

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Reserva> listReserva = new ArrayList<>();

    @Column
    private String review;
    @Column
    private Integer puntuacion;
    @Column
    private Integer estrellas;
    @Column
    private String tituloDescripcion;
    @Column(length = 1000000)
    private String descripcion;
    @Column(length = 500000)
    private String politicaLugar;
    @Column(length = 500000)
    private String politicaSaludSeguridad;
    @Column(length = 500000)
    private String politicaCancelacion;
    @Column
    private Double latitud;
    @Column
    private Double longitud;
    @Column
    private Double altura;
    @Column
    private String distanciaCentro;

}
