package org.asco_devs.sistema_sareasp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Entity(name = "Tareas")
//lombok
@Data//generar los setters y getters
@NoArgsConstructor//el constructor vacio
@AllArgsConstructor//el constructor lleno
@ToString//el metodo sobrecargado ToString
@EqualsAndHashCode//el metodo para trabajar con hashCode - id interno para la clase

public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTarea")
    private Integer idTarea;
    private String descripcion;
    private Date fechaLimite;
    private String estado;
}

