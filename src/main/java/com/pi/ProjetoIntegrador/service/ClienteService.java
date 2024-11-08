package com.pi.ProjetoIntegrador.service;

import com.pi.ProjetoIntegrador.model.Cliente;
import com.pi.ProjetoIntegrador.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;
    
    public Cliente buscarPorId(Integer id){
        return clienteRepository.findById(id).orElseThrow();
    }
        
    public Cliente criarCliente(Cliente cliente){
        cliente.setId(null);
        clienteRepository.save(cliente);
        return cliente;
    }
    
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }
    
    public Cliente atualizar(Integer id, Cliente cliente){
        Cliente clienteEncontrado = buscarPorId(id);
        clienteEncontrado.setNome(cliente.getNome());
        clienteEncontrado.setCpf(cliente.getCpf());
        clienteEncontrado.setRg(cliente.getRg());
        clienteEncontrado.setEmail(cliente.getEmail());
        clienteRepository.save(clienteEncontrado);
        return clienteEncontrado;
    }
    
    public void excluir(Integer id){
        Cliente clienteEncontrado = buscarPorId(id);
        clienteRepository.deleteById(clienteEncontrado.getId());
    }

    
}
