package com.example.demo.entity;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="CURSOS_DOCENTES_SEMESTRES")
public class CursoDocenteSemestre {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "CURSO_DOCENTE_SEMESTRE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurso_Docente_Semestre")
    @SequenceGenerator(name = "seqCurso_Docente_Semestre", allocationSize = 1, sequenceName = "SEQ_CURSO_DOCENTE_SEMESTRE")
    @Builder.Default
    private Long id = 0L;
    
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cursoDocenteSemestre")
    //@JsonIgnore
    //private Set<Proyeccion> proyecciones;

    @ManyToOne
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "SEMESTRE_ID", nullable = false)
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "DOCENTE_ID", nullable = false)
    private Docente docente;

    

}
