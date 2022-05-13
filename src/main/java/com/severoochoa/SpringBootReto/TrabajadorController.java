/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author inmag
 */
@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {
    @Autowired
    TrabajadorService servicioTrabajador;
    @PostMapping("/crearTrab")
    public ResponseEntity crearTrabajador(Long idtrab){
        
       servicioTrabajador.informacionTrabajador(idtrab);
       return ResponseEntity.ok().build();
    }
    
}
