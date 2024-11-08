
package com.pi.ProjetoIntegrador.service;

import com.pi.ProjetoIntegrador.model.Quarto;
import com.pi.ProjetoIntegrador.repository.QuartoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartoService {
    
    @Autowired
    QuartoRepository quartoRepository;

    public List<Quarto> listarTodos() {
        return quartoRepository.findAll();
    }

    public Quarto buscarPorId(Integer id) {
        return quartoRepository.findById(id).orElseThrow();
    }

    public void excluir(Integer id) {
        Quarto quartoEncontrado = buscarPorId(id);
        quartoRepository.deleteById(quartoEncontrado.getId());
    }

    public Quarto criarQuartos(Quarto quarto) {
        quarto.setId(null);
        quartoRepository.save(quarto);
        return quarto;
    }

    public List<Quarto> buscarPorIdClienteId(Integer clienteId) {
        return quartoRepository.findByClienteId(clienteId);
    }
    
}
