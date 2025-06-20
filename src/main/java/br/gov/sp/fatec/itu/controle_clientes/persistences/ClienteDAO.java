package br.gov.sp.fatec.itu.controle_clientes.persistences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.gov.sp.fatec.itu.controle_clientes.models.Cliente;

@Repository
public class ClienteDAO implements ICrudDAO<Cliente> {
  @Autowired
  private DatabaseConnection realizarConexao;

  public ClienteDAO(DatabaseConnection realizarConexao) {
    this.realizarConexao = realizarConexao;
  }

  @Override
  public void inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
    Connection conexao = realizarConexao.getConnection();

    String consulta = "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?)";
    PreparedStatement comando = conexao.prepareStatement(consulta);
    
    comando.setString(1, cliente.getCpf());
    comando.setString(2, cliente.getNome());
    comando.setString(3, cliente.getEmail());
    comando.setDouble(4, cliente.getLimiteDeCredito());
    comando.setString(5, cliente.getDataNascimento());
    
    comando.executeUpdate();
    
    comando.close();
    conexao.close();
  }

  @Override
  public void atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
    Connection conexao = realizarConexao.getConnection();

    String consulta =
        "UPDATE CLIENTE SET NOME = ?, EMAIL = ?, LIMITE_DE_CREDITO = ?, DATA_NASC = ? WHERE CPF = ?";
    PreparedStatement comando = conexao.prepareStatement(consulta);

    comando.setString(1, cliente.getNome());
    comando.setString(2, cliente.getEmail());
    comando.setDouble(3, cliente.getLimiteDeCredito());
    comando.setString(4, cliente.getDataNascimento());
    comando.setString(5, cliente.getCpf());
    
    comando.executeUpdate();
    
    comando.close();
    conexao.close();
  }

  @Override
  public void excluir(Cliente cliente) throws SQLException, ClassNotFoundException {
    Connection conexao = realizarConexao.getConnection();

    String consulta = "DELETE FROM CLIENTE WHERE CPF = ?";
    PreparedStatement comando = conexao.prepareStatement(consulta);

    comando.setString(1, cliente.getCpf());
    
    comando.executeUpdate();
    
    comando.close();
    conexao.close();
  }

  @Override
  public Cliente buscar(Cliente cliente) throws SQLException, ClassNotFoundException {
    Connection conexao = realizarConexao.getConnection();

    String consulta = "SELECT NOME, EMAIL, LIMITE_DE_CREDITO, DATA_NASC WHERE CPF = ?";
    PreparedStatement comando = conexao.prepareStatement(consulta);

    comando.setString(1, cliente.getCpf());
    ResultSet resultado = comando.executeQuery();

    if (resultado.next()) {
      cliente.setNome(resultado.getString("NOME"));
      cliente.setEmail(resultado.getString("EMAIL"));
      cliente.setLimiteDeCredito(resultado.getDouble("LIMITE_DE_CREDITO"));
      cliente.setDataNascimento(resultado.getString("DATA_NASC"));
    }

    resultado.close();
    comando.close();
    conexao.close();

    return cliente;
  }

  @Override
  public List<Cliente> listar() throws SQLException, ClassNotFoundException {
    Connection conexao = realizarConexao.getConnection();
    
    String consulta = "SELECT CPF, NOME, EMAIL, LIMITE_DE_CREDITO, DATA_NASC FROM CLIENTE";
    PreparedStatement comando = conexao.prepareStatement(consulta);
    ResultSet resultado = comando.executeQuery();

    List<Cliente> lista = new ArrayList<>();

    while (resultado.next()) {
      Cliente cliente = new Cliente();
      cliente.setNome(resultado.getString("CPF"));
      cliente.setNome(resultado.getString("NOME"));
      cliente.setEmail(resultado.getString("EMAIL"));
      cliente.setLimiteDeCredito(resultado.getDouble("LIMITE_DE_CREDITO"));
      cliente.setDataNascimento(resultado.getString("DATA_NASC"));
      lista.add(cliente);
    }

    resultado.close();
    comando.close();
    conexao.close();

    return lista;
  }
}
