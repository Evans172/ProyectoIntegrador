package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
@Table(name="PERSONAS")
public class Persona {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "PERSONA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersona")
    @SequenceGenerator(name = "seqPersona", allocationSize = 1, sequenceName = "SEQ_PERSONA")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
    private String nombre;
	
	@Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
	
	@Column(name = "DNI")
    private String dni;

    //!OneToOne's
    @OneToOne(                                          //*Verificado
        mappedBy = "personaDocente",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Docente docente;
    
    @OneToOne(                                          //*Verificado
        mappedBy = "personaAlumno",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Alumno alumno;
    
    @OneToOne(                                          //*Verificado
        mappedBy = "personaUsuario",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Usuario usuario;

}
