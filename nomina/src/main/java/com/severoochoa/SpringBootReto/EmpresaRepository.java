/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

/**
 *
 * @author inmag
 */
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long>{
    @Override
    <S extends Empresa> S save (S s);
     
    @Override
    List<Empresa> findAll();
     
    @Override
    Optional<Empresa> findById (Long id);
    
    Optional<Empresa> findByIdemp(Long idemp);
}

