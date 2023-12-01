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
@Table(name="GRUPOS_ALUMNOS")
public class GrupoAlumno {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "GRUPO_ALUMNO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGrupo_Alumno")
    @SequenceGenerator(name = "seqGrupo_Alumno", allocationSize = 1, sequenceName = "SEQ_GRUPO_ALUMNO")
    @Builder.Default
    private Long id = 0L;

    //!ManyToOne's
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "GRUPO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Grupo grupoRelacionado;
    
    @ManyToOne(fetch = FetchType.EAGER)                 //*Verificado
    @JoinColumn(name = "ALUMNO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Alumno alumnoRelacionado;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "grupoAlumno",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<ActividadGrupoAlumno> ActividadesGruposAlumnos;

}
