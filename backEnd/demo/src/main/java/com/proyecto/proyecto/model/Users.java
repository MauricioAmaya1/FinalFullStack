package com.proyecto.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.proyecto.model.Reserva;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "student_email_unique", columnNames = "email"))
public class Users{
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email", nullable = false)
    private String email;

    @ToString.Exclude
    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> listReserva = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles = new ArrayList<>();

}
