
package com.pi.ProjetoIntegrador.Controller;

import com.pi.ProjetoIntegrador.service.QuartoService;
import com.pi.ProjetoIntegrador.model.Quarto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quarto")
public class QuartoAPIController {
    
    @Autowired
    QuartoService quartoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Quarto> criar(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.criarQuartos(quarto);
        return new ResponseEntity<>(novoQuarto, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        quartoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{clienteId}")
    public ResponseEntity<List<Quarto>> listarQuartos(@PathVariable Integer clienteId) {
        List<Quarto> lista = quartoService.buscarPorIdClienteId(clienteId);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
