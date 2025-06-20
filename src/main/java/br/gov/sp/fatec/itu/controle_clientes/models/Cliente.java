package br.gov.sp.fatec.itu.controle_clientes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
  private String cpf;
  private String nome;
  private String email;
  private Double limiteDeCredito;
  private String dataNascimento;
}
