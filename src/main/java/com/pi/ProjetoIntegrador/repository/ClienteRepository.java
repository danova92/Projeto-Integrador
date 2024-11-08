package com.pi.ProjetoIntegrador.repository;

import com.pi.ProjetoIntegrador.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    
    
}
