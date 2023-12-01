package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name="ACTIVIDADES_BENEFICIARIOS")
public class ActividadBeneficiario {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "ACTIVIDAD_BENEFICIARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqActividad_Beneficiario")
    @SequenceGenerator(name = "seqActividad_Beneficiario", allocationSize = 1, sequenceName = "SEQ_ACTIVIDAD_BENEFICIARO")
    @Builder.Default
    private Long id = 0L;

    @Null
    @Column(name = "ASISTENCIA")
	private int asistencia;

    //!ManyToOne's
    
    @ManyToOne                                             //*Verificado
    @JoinColumn(name = "BENEFICIARIO_ID", nullable = false)
    private Beneficiario beneficiario;
    
    @ManyToOne                                              //*Verificado
    @JoinColumn(name = "ACTIVIDAD_ID", nullable = false)
    private Actividad actividadVinculada;

}
