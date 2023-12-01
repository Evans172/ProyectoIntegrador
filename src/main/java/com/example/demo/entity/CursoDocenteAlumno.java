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
@Table(name="CURSOS_DOCENTES_ALUMNOS")
public class CursoDocenteAlumno {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "CURSO_DOCENTE_ALUMNO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurso_Docente_Alumno")
    @SequenceGenerator(name = "seqCurso_Docente_Alumno", allocationSize = 1, sequenceName = "SEQ_CURSO_DOCENTE_ALUMNO")
    @Builder.Default
    private Long id = 0L;

    //!ManyToOne's

    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "ALUMNO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)                          //*Verificado
    @JoinColumn(name = "CURSO_DOCENTE_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private CursoDocente cursoDocenteAsignado;

}
