/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author inmag
 */
public interface NominaRepository extends JpaRepository<Nomina,Long>{
    //primer requisito
    @Override
    <S extends Nomina> S save (S s);
    

    //tercer requisito
    @Override
    List<Nomina> findAll();
    
    //segundo requisito
    Optional<Nomina> findByIdnomina(Long idnomina);
}
