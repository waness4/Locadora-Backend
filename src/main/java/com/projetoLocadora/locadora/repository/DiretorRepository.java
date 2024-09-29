package com.projetoLocadora.locadora.repository;

import org.springframework.stereotype.Repository;

import com.projetoLocadora.locadora.model.Diretor;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, UUID>{
    
}
