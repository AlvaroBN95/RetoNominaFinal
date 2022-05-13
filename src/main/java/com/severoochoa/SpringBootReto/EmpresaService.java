/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author inmag
 */

@Service
public class EmpresaService {
    @Autowired(required=false)
    EmpresaRepository repositorioEmpresa;

     public List dimeEmpresas(){
        List<Empresa> listaEmpresas = repositorioEmpresa.findAll();
        return listaEmpresas;
    }
     
     public Empresa dimeEmpresaPorId(Long id){
        Optional<Empresa> empresa = repositorioEmpresa.findById(id);
        if (empresa.isPresent()){
            return empresa.get();
        } else {
            return null;
        }
 
    }
     
     }

