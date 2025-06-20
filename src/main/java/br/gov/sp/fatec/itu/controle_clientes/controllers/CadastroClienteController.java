package br.gov.sp.fatec.itu.controle_clientes.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.gov.sp.fatec.itu.controle_clientes.models.Cliente;
import br.gov.sp.fatec.itu.controle_clientes.persistences.ClienteDAO;
import jakarta.servlet.ServletException;


@Controller
public class CadastroClienteController {
  @Autowired
  private ClienteDAO clienteDao;

  @RequestMapping(method = RequestMethod.GET, name = "Cadastro de Cliente",
      value = "/CadastroCliente")
  public ModelAndView getCliente(@RequestParam Map<String, String> parametros, ModelMap model)
      throws ServletException, IOException {
    return new ModelAndView("CadastroCliente");
  }

  @RequestMapping(method = RequestMethod.POST, name = "Cadastro de Cliente",
      value = "/CadastroCliente")
  public ModelAndView postCliente(@RequestParam Map<String, String> parametros,
      RedirectAttributes redirectAttributes) {
    String cpf = parametros.get("cpf");
    String nome = parametros.get("nome");
    String email = parametros.get("email");
    String dataNascimento = parametros.get("dataNascimento");
    Double limiteDeCredito = 0.0;
    String credito = parametros.get("limiteDeCredito");

    if (credito != null && !credito.isEmpty())
      limiteDeCredito = Double.parseDouble(credito);

    String comando = parametros.get("botao");

    Cliente cliente = new Cliente();

    cliente.setCpf(cpf);
    cliente.setNome(nome);
    cliente.setEmail(email);
    cliente.setDataNascimento(dataNascimento);
    cliente.setLimiteDeCredito(limiteDeCredito);

    String mensagemSucesso = null;
    String mensagemErro = null;

    try {
      if (comando.equalsIgnoreCase("salvar")) {
        clienteDao.inserir(cliente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente salvo!");
      } else if (comando.equalsIgnoreCase("excluir")) {
        clienteDao.excluir(cliente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente excluído!");
      } else if (comando.equalsIgnoreCase("atualizar")) {
        clienteDao.atualizar(cliente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente atualizado!");
      }
    } catch (SQLException | ClassNotFoundException erro) {
      redirectAttributes.addFlashAttribute("mensagemErro", erro.getMessage());
    }

    return new ModelAndView("redirect:/CadastroCliente");
  }
}
