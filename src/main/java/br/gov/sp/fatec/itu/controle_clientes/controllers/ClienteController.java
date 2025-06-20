package br.gov.sp.fatec.itu.controle_clientes.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.gov.sp.fatec.itu.controle_clientes.models.Cliente;
import br.gov.sp.fatec.itu.controle_clientes.persistences.ClienteDAO;
import br.gov.sp.fatec.itu.controle_clientes.persistences.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/formulario")
public class ClienteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void getCliente(HttpServletRequest requisicao, HttpServletResponse resposta)
      throws ServletException, IOException {
    String acao = requisicao.getParameter("acao");
    String cpf = requisicao.getParameter("cpf");
    String erro = "";

    Cliente cliente = new Cliente();
    List<Cliente> listaDeClientes = new ArrayList<>();

    try {
      if (!cpf.isBlank() || cpf == null) {
        DatabaseConnection realizarConexao = new DatabaseConnection();
        ClienteDAO clienteDao = new ClienteDAO(realizarConexao);
        cliente.setCpf(cpf);

        if (acao.equals("excluir")) {
          clienteDao.excluir(cliente);
          listaDeClientes = clienteDao.listar();
          cliente = null;
        } else if (acao.equals("editar")) {
          cliente = clienteDao.buscar(cliente);
        }
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      requisicao.setAttribute("erro", erro);
      requisicao.setAttribute("cliente", cliente);
      requisicao.setAttribute("listaDeClientes", listaDeClientes);
    }
  }
}
