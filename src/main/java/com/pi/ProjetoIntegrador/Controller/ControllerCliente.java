package com.pi.ProjetoIntegrador.Controller;

import com.pi.ProjetoIntegrador.model.Cliente;
import com.pi.ProjetoIntegrador.model.Quarto;
import com.pi.ProjetoIntegrador.service.ClienteService;
import com.pi.ProjetoIntegrador.service.QuartoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerCliente {

    @Autowired
    ClienteService clienteService;

    @Autowired
    QuartoService quartoService;

    @GetMapping("/tela-inicio")
    public String mostraIndex() {
        return "bemVindo";
    }

    @GetMapping("/inserir-cliente")
    public String mostraCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro";
    }

    @PostMapping("/guardar-cliente")
    public String processarFormulario(Model model, @ModelAttribute Cliente cliente) {
        if (cliente.getId() != null) {
            clienteService.atualizar(cliente.getId(), cliente);
        } else {
            clienteService.criarCliente(cliente);
        }
        return "redirect:/listagem-clientes";
    }

    @GetMapping("/listagem-clientes")
    public String mostraClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "listagem";
    }

    @GetMapping("/exibir-cliente")
    public String mostraDetalhes(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        Cliente clienteEncontrado = new Cliente();
        clienteEncontrado = clienteService.buscarPorId(idCliente);

        List<Quarto> quartoEncontrado = new ArrayList();
        quartoEncontrado = quartoService.buscarPorIdClienteId(idCliente);
        model.addAttribute("cliente", clienteEncontrado);
        model.addAttribute("quartos", quartoEncontrado);
        model.addAttribute("quarto", new Quarto());

        return "detalhes";
    }

    @GetMapping("/excluir-cliente")
    public String excluirCliente(@RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        clienteService.excluir(idCliente);
        return "redirect:/listagem-clientes";
    }

    @GetMapping("/alterar-cliente")
    public String alterarCliente(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        model.addAttribute("cliente", clienteService.buscarPorId(idCliente));
        return "cadastro";
    }

}
