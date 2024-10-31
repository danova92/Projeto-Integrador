package com.pi.ProjetoIntegrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quarto {
    
    private Integer id;
    private int numero;
    private Cliente cliente;
        
}
