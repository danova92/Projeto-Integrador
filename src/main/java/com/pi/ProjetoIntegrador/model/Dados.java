package com.pi.ProjetoIntegrador.model;

import java.util.ArrayList;
import java.util.List;


public class Dados {
    
    private static List<Cliente> listaClientes = new ArrayList();
    private static List<Quarto> listaQuartos = new ArrayList();
    
    public static void adicionarCliente(Cliente cliente){
        cliente.setId(listaClientes.size() + 1);
        cliente.setStatus(true);
        listaClientes.add(cliente);
    }
    
    public static List<Cliente> listarClientes(){
        return listaClientes;
    }
    
    public static void excluirCliente(Integer id){
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                listaClientes.remove(c);
                break;
            }
        }
    }
    
    public static Cliente obtemCliente(Integer id){
        Cliente clienteEncontrado = new Cliente();
        for(Cliente c : listaClientes){
            if(c.getId() == id){
                clienteEncontrado = c;
                break;
            }
        }
        return clienteEncontrado;
    }
    
    public static void atualizarCliente(Cliente cliente){
        for(Cliente c : listaClientes){
            if(c.getId() == cliente.getId()){
                c.setNome(cliente.getNome());
                c.setCpf(cliente.getCpf());
                c.setRg(cliente.getRg());
                c.setEmail(cliente.getEmail());
                break;
            }
        }
    }
    
    public static void adicionarQuarto(Quarto quarto , Cliente cliente){
        quarto.setId(listaQuartos.size() + 1);
        quarto.setCliente(cliente);
        listaQuartos.add(quarto);
    }
    
    public static void excluirQuarto(Integer id){
        for (Quarto q : listaQuartos) {
            if (q.getId() == id) {
                listaQuartos.remove(q);
                break;
            }
        }
    }
    
    public static List<Quarto> listaQuartos(Integer idCliente){
        List<Quarto> quartoEncontrado = new ArrayList<>();
        for(Quarto q : listaQuartos){
            if(q.getCliente().getId() == idCliente){
                quartoEncontrado.add(q);
            }
        }
        return quartoEncontrado;
    }
}
