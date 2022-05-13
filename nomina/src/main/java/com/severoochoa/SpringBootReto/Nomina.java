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
    private Long idNomina;
    
    public Long getIdNomina() {
        return idNomina;
    }

    public void setId(Long idNomina) {
        this.idNomina = idNomina;
    }
    
    @Column(name="idTrab")
    private Long idTrab;
    
    @Column(name="domicilio")
    private String domicilio;
    
    @Column(name="grupoProfesional")
    private int grupoProfesional;

    public Long getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(Long idTrab) {
        this.idTrab = idTrab;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getGrupoProfesional() {
        return grupoProfesional;
    }

    public void setGrupoProfesional(int grupoProfesional) {
        this.grupoProfesional = grupoProfesional;
    }
}
