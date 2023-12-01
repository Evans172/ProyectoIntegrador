//Completo
package com.example.demo.entity;

import java.sql.Blob;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
@Table(name="PROYECCIONES")
public class Proyeccion {

	
	private static final long serialVersionUID = 2000567672298399538L; //

	@Id
	@Column(name = "PROYECCION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProyeccion")
    @SequenceGenerator(name = "seqProyeccion", allocationSize = 1, sequenceName = "SEQ_PROYECCION")
    @Builder.Default
    private Long id=0L;
	
	@NotNull
	@Column(name = "NOMBRE_PROYECCION")
    private String nombreProyeccion;
	
	@NotNull
	@Column(name = "DESCRIPCION")
    private String descripcion;
	
	@NotNull
	@Column(name = "EJE_ESTRATEGICO")
    private String ejeEstrategico;
	
	@NotNull
	@Column(name = "OBJETIVO")
    private String objetivo;
	
	@NotNull
	@Column(name = "ZONA_INTERVENCION")
    private String zonaIntervencion;
	
	@Null
	@Column(name = "ARCHIVO_ADJUNTO")
    private byte[] archivoAdjunto;
	
	@NotNull
	@Column(name = "FECHA_INICIO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
    private Date fechaInicio;
	
	@NotNull
	@Column(name = "FECHA_TERMINO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
    private Date fechaTermino;

	//!ManyToOne's
	
	@ManyToOne											//*Verificado
	@JoinColumn(name = "ESTADO_PROYECCION_ID", nullable = false)
    private EstadoProyeccion estadoProyeccion;
	
	@ManyToOne											//*Verificado
	@JoinColumn(name = "TIPO_PROYECCION_ID", nullable = false)
    private TipoProyeccion tipoProyeccion;
	
	//*Se borró escuela
	
	@ManyToOne 											//Verificado
	@JoinColumn(name = "CURSO_DOCENTE_ID", nullable = true)
	private CursoDocente cursoDocente;

	//*Se borró ciclo

	//!OneToMany's
	
	@OneToMany(											//*Verificado
		mappedBy = "proyeccionId",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonIgnore
	private Set<Actividad> actividades;

	//*Aquí se borró grupo

}