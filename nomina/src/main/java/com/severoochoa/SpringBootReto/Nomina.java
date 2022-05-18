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

    public Long getIdnomina() {
        return idnomina;
    }

    public void setIdnomina(Long idnomina) {
        this.idnomina = idnomina;
    }

    public Long getIdtrab() {
        return idtrab;
    }

    public void setIdtrab(Long idtrab) {
        this.idtrab = idtrab;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public double getGratificaciones() {
        return gratificaciones;
    }

    public void setGratificaciones(double gratificaciones) {
        this.gratificaciones = gratificaciones;
    }

    public double getPagasextra() {
        return pagasextra;
    }

    public void setPagasextra(double pagasextra) {
        this.pagasextra = pagasextra;
    }

    public double getPagasextrapro() {
        return pagasextrapro;
    }

    public void setPagasextrapro(double pagasextrapro) {
        this.pagasextrapro = pagasextrapro;
    }

    public double getTotaldevengado() {
        return totaldevengado;
    }

    public void setTotaldevengado(double totaldevengado) {
        this.totaldevengado = totaldevengado;
    }

    public double getPorcentajecctrab() {
        return porcentajecctrab;
    }

    public void setPorcentajecctrab(double porcentajecctrab) {
        this.porcentajecctrab = porcentajecctrab;
    }

    public double getCctrab() {
        return cctrab;
    }

    public void setCctrab(double cctrab) {
        this.cctrab = cctrab;
    }

    public double getPorcentajefptrab() {
        return porcentajefptrab;
    }

    public void setPorcentajefptrab(double porcentajefptrab) {
        this.porcentajefptrab = porcentajefptrab;
    }

    public double getFptrab() {
        return fptrab;
    }

    public void setFptrab(double fptrab) {
        this.fptrab = fptrab;
    }

    public double getPorcentajehefm() {
        return porcentajehefm;
    }

    public void setPorcentajehefm(double porcentajehefm) {
        this.porcentajehefm = porcentajehefm;
    }

    public double getHorasextrafm() {
        return horasextrafm;
    }

    public void setHorasextrafm(double horasextrafm) {
        this.horasextrafm = horasextrafm;
    }

    public double getHorasextra() {
        return horasextra;
    }

    public void setHorasextra(double horasextra) {
        this.horasextra = horasextra;
    }

    public double getPorcentajeirpf() {
        return porcentajeirpf;
    }

    public void setPorcentajeirpf(double porcentajeirpf) {
        this.porcentajeirpf = porcentajeirpf;
    }

    public double getIrpf() {
        return irpf;
    }

    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }

    public double getTotaldeducir() {
        return totaldeducir;
    }

    public void setTotaldeducir(double totaldeducir) {
        this.totaldeducir = totaldeducir;
    }

    public double getTotalliquido() {
        return totalliquido;
    }

    public void setTotalliquido(double totalliquido) {
        this.totalliquido = totalliquido;
    }

    public double getRemuneracionemp() {
        return remuneracionemp;
    }

    public void setRemuneracionemp(double remuneracionemp) {
        this.remuneracionemp = remuneracionemp;
    }

    public double getProrratapagaextra() {
        return prorratapagaextra;
    }

    public void setProrratapagaextra(double prorratapagaextra) {
        this.prorratapagaextra = prorratapagaextra;
    }

    public double getBaseinctemp() {
        return baseinctemp;
    }

    public void setBaseinctemp(double baseinctemp) {
        this.baseinctemp = baseinctemp;
    }

    public double getDevengadossemp() {
        return devengadossemp;
    }

    public void setDevengadossemp(double devengadossemp) {
        this.devengadossemp = devengadossemp;
    }

    public double getPorcentajeempss() {
        return porcentajeempss;
    }

    public void setPorcentajeempss(double porcentajeempss) {
        this.porcentajeempss = porcentajeempss;
    }

    public double getSsemp() {
        return ssemp;
    }

    public void setSsemp(double ssemp) {
        this.ssemp = ssemp;
    }

    public double getBasecp() {
        return basecp;
    }

    public void setBasecp(double basecp) {
        this.basecp = basecp;
    }

    public double getPorcentajedesemp() {
        return porcentajedesemp;
    }

    public void setPorcentajedesemp(double porcentajedesemp) {
        this.porcentajedesemp = porcentajedesemp;
    }

    public double getDesemp() {
        return desemp;
    }

    public void setDesemp(double desemp) {
        this.desemp = desemp;
    }

    public double getPorcentajefpemp() {
        return porcentajefpemp;
    }

    public void setPorcentajefpemp(double porcentajefpemp) {
        this.porcentajefpemp = porcentajefpemp;
    }

    public double getFpemp() {
        return fpemp;
    }

    public void setFpemp(double fpemp) {
        this.fpemp = fpemp;
    }

    public double getPorcentajefogasa() {
        return porcentajefogasa;
    }

    public void setPorcentajefogasa(double porcentajefogasa) {
        this.porcentajefogasa = porcentajefogasa;
    }

    public double getFogasa() {
        return fogasa;
    }

    public void setFogasa(double fogasa) {
        this.fogasa = fogasa;
    }

    public double getPorcentajecothe() {
        return porcentajecothe;
    }

    public void setPorcentajecothe(double porcentajecothe) {
        this.porcentajecothe = porcentajecothe;
    }

    public double getCothe() {
        return cothe;
    }

    public void setCothe(double cothe) {
        this.cothe = cothe;
    }

    public double getTotalaporemp() {
        return totalaporemp;
    }

    public void setTotalaporemp(double totalaporemp) {
        this.totalaporemp = totalaporemp;
    }

    public double getBaseretenirpf() {
        return baseretenirpf;
    }

    public void setBaseretenirpf(double baseretenirpf) {
        this.baseretenirpf = baseretenirpf;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }
    
    
}
