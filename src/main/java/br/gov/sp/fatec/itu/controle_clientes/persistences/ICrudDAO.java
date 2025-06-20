package br.gov.sp.fatec.itu.controle_clientes.persistences;

import java.sql.SQLException;
import java.util.List;

public interface ICrudDAO<T> {
  public void inserir(T objeto) throws SQLException, ClassNotFoundException;
  
  public void atualizar(T objeto) throws SQLException, ClassNotFoundException;
  
  public void excluir(T objeto) throws SQLException, ClassNotFoundException;
  
  public T buscar(T objeto) throws SQLException, ClassNotFoundException;
  
  public List<T> listar() throws SQLException, ClassNotFoundException;
}
