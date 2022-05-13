/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author inmag
 */
@Entity
@Table(name="empresa")
public class Empresa {
    @Id
    @GeneratedValue
    @Column (name="idemp")
    private Long idEmp;
    
    @Column(name="cif")
    private String cif;
    
    @Column(name="domicilio")
    private String domicilio;
    
    @Column(name="ccc")
    private String ccc;
    
    @Column(name="nomemp")
    private String nomEmp;

    public Long getidemp() {
        return idEmp;
    }

    public void setidemp(Long idEmp) {
        this.idEmp = idEmp;
    }

    public String getcif() {
        return cif;
    }

    public void setcif(String cif) {
        this.cif = cif;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }
}
