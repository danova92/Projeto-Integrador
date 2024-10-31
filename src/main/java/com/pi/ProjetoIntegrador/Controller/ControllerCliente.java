package com.pi.ProjetoIntegrador.Controller;

import com.pi.ProjetoIntegrador.model.Cliente;
import com.pi.ProjetoIntegrador.model.Quarto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerCliente {

    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Quarto> listaQuartos = new ArrayList<>();

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
        if (cliente.getId() != 0) {
            for (Cliente c : listaClientes) {
                if (c.getId() == cliente.getId()) {
                    c.setNome(cliente.getNome());
                    c.setCpf(cliente.getCpf());
                    c.setRg(cliente.getRg());
                    c.setEmail(cliente.getEmail());
                    break;
                }
            }
        } else {
            cliente.setId(listaClientes.size() + 1);
            cliente.setStatus(true);
            listaClientes.add(cliente);
        }
        return "redirect:/listagem-clientes";
    }

    @GetMapping("/listagem-clientes")
    public String mostraClientes(Model model) {
        model.addAttribute("clientes", listaClientes);
        return "listagem";
    }

    @GetMapping("/exibir-cliente")
    public String mostraDetalhes(Model model, @RequestParam int id) {
        Cliente clienteEncontrado = null;
        List<Quarto> quartosCliente = new ArrayList<>();

        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                clienteEncontrado = c;
                break;
            }
        }

        for (Quarto q : listaQuartos) {
            if (Objects.equals(q.getCliente().getId(), clienteEncontrado.getId())) {
                quartosCliente.add(q);
            }
        }

        model.addAttribute("cliente", clienteEncontrado);
        model.addAttribute("quarto", new Quarto());
        model.addAttribute("quartos", quartosCliente);

        return "detalhes";
    }

    @GetMapping("/excluir-cliente")
    public String excluirCliente(@RequestParam Integer id) {
        listaClientes.removeIf(f -> f.getId() == id);
        return "redirect:/listagem-clientes";
    }

    @GetMapping("/alterar-cliente")
    public String alterarCliente(Model model, @RequestParam int id) {
        Cliente clienteEncontrado = null;
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                clienteEncontrado = c;
                break;
            }
        }
        model.addAttribute("cliente", clienteEncontrado);
        return "cadastro";
    }

    @PostMapping("/guardar-quarto")
    public String processarFormularioQuarto(Model model, @ModelAttribute Quarto quarto, @RequestParam int clienteId) {
       
        Cliente cliente = listaClientes.stream()
                .filter(c -> c.getId() == clienteId)
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            quarto.setId(listaQuartos.size() + 1);
            quarto.setCliente(cliente);
            listaQuartos.add(quarto);

        }

        return "redirect:/listagem-clientes";
    }

    @GetMapping("/excluir-quarto")
    public String excluirQuarto(Model model, @RequestParam String id) {
        Integer idQuarto = Integer.parseInt(id);
        for (Quarto q : listaQuartos) {
            if (q.getId() == idQuarto) {
                listaQuartos.remove(q);
                break;
            }
        }
        return "redirect:/listagem-clientes";
    }
}
