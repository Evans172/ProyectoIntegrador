package com.example.demo.entity;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="CURSOS_DOCENTES")
public class CursoDocente {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "CURSO_DOCENTE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurso_Docente")
    @SequenceGenerator(name = "seqCurso_Docente", allocationSize = 1, sequenceName = "SEQ_CURSO_DOCENTE")
    @Builder.Default
    private Long id = 0L;

    //!ManyToOne's
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "CURSO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Curso curso;
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "DOCENTE_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Docente docente;


    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "cursoDocenteAsignado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CursoDocenteAlumno> cursoDocenteAlumnos;

    @OneToMany(                                         //*Verificado
        mappedBy = "cursoDocente",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Proyeccion> proyecciones;

}
