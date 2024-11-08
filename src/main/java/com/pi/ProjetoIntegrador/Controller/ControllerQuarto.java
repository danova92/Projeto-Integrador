package com.pi.ProjetoIntegrador.Controller;

import com.pi.ProjetoIntegrador.service.ClienteService;
import com.pi.ProjetoIntegrador.service.QuartoService;
import com.pi.ProjetoIntegrador.model.Cliente;
import com.pi.ProjetoIntegrador.model.Quarto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerQuarto {

    @Autowired

    QuartoService quartoService;

    @Autowired

    ClienteService clienteService;

    @PostMapping("/guardar-quarto")
    public String processarFormularioQuarto(Model model, @ModelAttribute Quarto quarto, @RequestParam Integer clienteId) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        quarto.setCliente(cliente);
        quartoService.criarQuartos(quarto);
        return "redirect:/listagem-clientes";
    }

    @GetMapping("/excluir-quarto")
    public String excluirQuarto(Model model, @RequestParam String id) {
        Integer idQuarto = Integer.parseInt(id);
        quartoService.excluir(idQuarto);
        return "redirect:/listagem-clientes";
    }

}
