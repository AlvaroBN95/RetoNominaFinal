/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author inmag
 */
@Entity
@Table(name="trabajador")
public class Trabajador {
    @Id
    @Column (name="idtrab")
    private Long idtrab;
    
   @Column(name="idemp")
    private Long idemp;

    public Long getIdtrab() {
        return idtrab;
    }

    public void setIdtrab(Long idtrab) {
        this.idtrab = idtrab;
    }

    public Long getIdemp() {
        return idemp;
    }

    public void setIdemp(Long idemp) {
        this.idemp = idemp;
    }

    public String getApe1emp() {
        return ape1emp;
    }

    public void setApe1emp(String ape1emp) {
        this.ape1emp = ape1emp;
    }

    public String getApe2emp() {
        return ape2emp;
    }

    public void setApe2emp(String ape2emp) {
        this.ape2emp = ape2emp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNaf() {
        return naf;
    }

    public void setNaf(String naf) {
        this.naf = naf;
    }

    public String getFechaantiguedad() {
        return fechaantiguedad;
    }

    public void setFechaantiguedad(String fechaantiguedad) {
        this.fechaantiguedad = fechaantiguedad;
    }

    public int getGrupoprofesional() {
        return grupoprofesional;
    }

    public void setGrupoprofesional(int grupoprofesional) {
        this.grupoprofesional = grupoprofesional;
    }

    public int getGrupocotizacion() {
        return grupocotizacion;
    }

    public void setGrupocotizacion(int grupocotizacion) {
        this.grupocotizacion = grupocotizacion;
    }

    public int getNivelcotizacion() {
        return nivelcotizacion;
    }

    public void setNivelcotizacion(int nivelcotizacion) {
        this.nivelcotizacion = nivelcotizacion;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Column(name="nomtrab")
    private String nomtrab;

    public String getNomtrab() {
        return nomtrab;
    }

    public void setNomtrab(String nomtrab) {
        this.nomtrab = nomtrab;
    }
    
    @Column(name="ape1trab")
    private String ape1emp;
    
    @Column(name="ape2trab")
    private String ape2emp;
    
    @Column(name="dni")
    private String dni;
    
    @Column(name="naf")
    private String naf;
    
    @Column(name="fechaantiguedad")
    private String fechaantiguedad;
    
    @Column(name="grupoprofesional")
    private int grupoprofesional;
    
    @Column(name="grupocotizacion")
    private int grupocotizacion;
    
    @Column(name="nivelcotizacion")
    private int nivelcotizacion;
    
    @Column(name="letra")
    private String letra;
    
    @Column(name="convenio")
    private String convenio;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="tipocontrato")
    private String tipocontrato;

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }
    
}
