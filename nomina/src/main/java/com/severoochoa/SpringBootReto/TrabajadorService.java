/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author inmag
 */
@Service
public class TrabajadorService {
    @Autowired
    TrabajadorRepository repositorioTrabajador;
    public void informacionTrabajador(Long idTrab){
        //crear un objeto User
        Trabajador trab= new Trabajador();
        trab.setIdtrab(idTrab);
        repositorioTrabajador.save(trab);
    }
}
