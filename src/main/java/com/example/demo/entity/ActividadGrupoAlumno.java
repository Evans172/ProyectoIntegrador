package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="ACTIVIDADES_ALUMNOS")
public class ActividadGrupoAlumno {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "ACTIVIDAD_ALUMNO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqActividad_Alumno")
    @SequenceGenerator(name = "seqActividad_Alumno", allocationSize = 1, sequenceName = "SEQ_ACTIVIDAD_ALUMNO")
    @Builder.Default
    private Long id = 0L;
    
    @Column(name = "ASISTENCIA")
	private int asistencia;

    //!ManyToOne's
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "ACTIVIDAD_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Actividad actividadRelacionada;
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "GRUPO_ALUMNO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private GrupoAlumno grupoAlumno;

}
