package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    private Long id;
    private String nombre;
    private String descripcion;
    private Date fechaTermino;
    private Integer idUser;
    private Boolean status;
}
