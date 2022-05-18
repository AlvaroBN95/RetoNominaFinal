/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author inmag
 */

@Entity
@Table(name="nomina")
public class Nomina {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idnomina;
    
    public Long getIdNomina() {
        return idnomina;
    }

    public void setId(Long idNomina) {
        this.idnomina = idNomina;
    }
    
    
    @Column(name="idtrab")
    private Long idtrab;
    
    @Column(name="domicilio")
    private String domicilio;
    
    @Column(name="grupoprofesional")
    private int grupoprofesional;
    
    @Column(name="grupocotizacion")
    private int grupocotizacion;
    
    @Column(name="nivelcotizacion")
    private int nivelcotizacion;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="convenio")
    private String convenio;
    
    @Column(name="salariobase")
    private double salariobase;
    
    @Column(name="gratificaciones")
    private double gratificaciones;
    
    @Column(name="pagasextra")
    private double pagasextra;
    
    @Column(name="pagasextrapro")
    private double pagasextrapro;
    
    @Column(name="totaldevengado")
    private double totaldevengado;
    
    @Column(name="porcentajecctrab")
    private double porcentajecctrab;

    @Column(name="cctrab")
    private double cctrab;
    
    @Column(name="porcentajefptrab")
    private double porcentajefptrab;
    
    @Column(name="fptrab")
    private double fptrab;
    
    @Column(name="porcentajehefm")
    private double porcentajehefm;
    
    @Column(name="horasextrafm")
    private double horasextrafm;
    
     @Column(name="horasextra")
    private double horasextra;
    
    @Column(name="porcentajeirpf")
    private double porcentajeirpf;
    
    @Column(name="irpf")
    private double irpf;
    
    @Column(name="totaldeducir")
    private double totaldeducir;
    
    @Column(name="totalliquido")
    private double totalliquido;
    
    @Column(name="remuneracionemp")
    private double remuneracionemp;
    
    @Column(name="prorratapagaextra")
    private double prorratapagaextra;
    
    @Column(name="baseinctemp")
    private double baseinctemp;
    
    @Column(name="devengadossemp")
    private double devengadossemp;
    
    @Column(name="porcentajeempss")
    private double porcentajeempss;
    
    @Column(name="ssemp")
    private double ssemp;
    
    @Column(name="basecp")
    private double basecp;
    
    @Column(name="porcentajedesemp")
    private double porcentajedesemp;
    
    @Column(name="desemp")
    private double desemp;
    
    @Column(name="porcentajefpemp")
    private double porcentajefpemp;
    
    @Column(name="fpemp")
    private double fpemp;
    
    @Column(name="porcentajefogasa")
    private double porcentajefogasa;
    
    @Column(name="fogasa")
    private double fogasa;
    
    @Column(name="porcentajecothe")
    private double porcentajecothe;
    
    @Column(name="cothe")
    private double cothe;
    
    @Column(name="totalaporemp")
    private double totalaporemp;
    
    @Column(name="baseretenirpf")
    private double baseretenirpf;
    
    @Column(name="fechainicio")
    private String fechainicio;
    
    @Column(name="fechafin")
    private String fechafin;
    
}
