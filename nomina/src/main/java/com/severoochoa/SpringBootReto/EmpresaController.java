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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author inmag
 */
@RestController
@RequestMapping("/empresa")

//sin uso
public class EmpresaController {
    @Autowired
    EmpresaService servicioEmpresa;
    
    @GetMapping("/lista")
    public ResponseEntity<String> listarEmpresas(){   
        List<Empresa> listaEmpresas = servicioEmpresa.dimeEmpresas();
        String resultado = "";
        for (Empresa e : listaEmpresas){
            resultado += e.getNomEmp() + "\n";
        }
        return ResponseEntity.ok(resultado);
        
    }
    
    @GetMapping("/{idEmp}")
    public ResponseEntity<String> dimeEmpresaPorId(@PathVariable("idEmp")Long idEmp){
        Empresa empresa = servicioEmpresa.dimeEmpresaPorId(idEmp);
        
        if(empresa == null){
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.ok(empresa.getNomEmp());
        }
    }
    
    
    
   
}
