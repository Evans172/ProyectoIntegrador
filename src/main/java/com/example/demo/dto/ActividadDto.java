package com.example.demo.dto;

import com.example.demo.entity.Proyeccion;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class ActividadDto {

    private long id;
    private String nombreActividad;
    private Date fechaInicio;
    private Date fechaTermino;
    private Blob informe;
    private long proyeccionId;
}
