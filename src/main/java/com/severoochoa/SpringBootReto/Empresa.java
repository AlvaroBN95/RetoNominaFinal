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
@Table(name="empresa")
public class Empresa {
    @Id
    @Column (name="idemp")
    private Long idemp;
    
    @Column(name="cif")
    private String cif;
    
    @Column(name="domicilio")
    private String domicilio;
    
    @Column(name="ccc")
    private String ccc;
    
    @Column(name="nomemp")
    private String nomemp;

    public Long getIdEmp() {
        return idemp;
    }

    public void setIdEmp(Long idEmp) {
        this.idemp = idEmp;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
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
        return nomemp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomemp = nomEmp;
    }
}
