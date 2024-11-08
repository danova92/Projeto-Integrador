package com.pi.ProjetoIntegrador.repository;

import com.pi.ProjetoIntegrador.model.Quarto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    
    List<Quarto> findByClienteId(Integer clienteId);
    
    
}
