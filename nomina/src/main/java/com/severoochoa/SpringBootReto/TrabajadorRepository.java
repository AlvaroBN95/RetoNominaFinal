/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author inmag
 */
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,Long>{
    @Override
    <S extends Trabajador> S save (S s);
    
    @Override
    List<Trabajador> findAll();
    
    Optional<Trabajador> findByIdtrab(Long idtrab);
}
