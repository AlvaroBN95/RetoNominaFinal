/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/nomina")
public class NominaController {
    @Autowired
    NominaService service;
    
    @PostMapping("/leerarchivo")
    public void procesaFichero(HttpServletRequest request) throws IOException {
        service.getFileContent(request);
    }
    
    @GetMapping("/empresa/{idemp}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("idemp")Long idemp){
        Empresa empresa = service.getEmpresaById(idemp);
        if(empresa == null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(empresa);
        }
    }
    
    @GetMapping("/trabajador/{idtrab}")
    public ResponseEntity<Trabajador> getTrabajadorById(@PathVariable("idtrab")Long idtrab){
        Trabajador trabajador = service.getTrabajadorById(idtrab);
        if(trabajador == null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(trabajador);
        }
    }
    
}
