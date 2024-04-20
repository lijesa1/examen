package com.codigo.LilianaSalazar.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social", nullable = false, length = 255)
    private String razonSocial;

    @Column(name = "tipo_documento", nullable = false, length = 1)
    private String tipoDocumento;

    @NotBlank(message = "Numero de documento es obligatorio")
    @Column(name = "numero_documento", nullable = false, length = 11)
    private String numeroDocumento;

    @Column(name = "condicion", nullable = false, length = 10)
    private String condicion;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "distrito", nullable = false, length = 255)
    private String distrito;

    @Column(name = "provincia", nullable = false, length = 255)
    private String provincia;

    @Column(name = "departamento", nullable = false, length = 255)
    private String departamento;

    @Column(name = "es_agente_retencion")
    private boolean esAgenteRetencion;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usua_cre", length = 50)
    private String usuaCrea;

    @Column(name = "date_create")
    private Date date_create;

    @Column(name = "usua_modif", length = 50)
    private String usuaModif;

    @Column(name = "date_modif")
    private Date dateModif;

    @Column(name = "usua_delet", length = 50)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Date dateDelet;
}
