package com.codigo.LilianaSalazar.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaRequest {

    private String razonsocial;
    private String tipoDocumento;
    private String numeroDocumento;
    private String condicion;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private boolean esAgenteRetencion;
    private int estado;
    private String usuaCrea;
    private String dateCreate;
    private String usuaModif;
    private String dateModif;
    private String usuaDelet;
    private String dateDelet;
}