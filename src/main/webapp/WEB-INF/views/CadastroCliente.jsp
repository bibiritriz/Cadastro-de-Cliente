<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
    />
    <link href="resources/style.css" type="text/css" rel="stylesheet" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&display=swap"
      rel="stylesheet"
    />
    <link rel="icon" href="" type="image/x-icon" />
    <title>Cadastro de Cliente</title>
  </head>
  <body>
    <div class="container">
      <form action="CadastroCliente" method="post" class="form">
        <h1>Cadastro de Cliente</h1>
        <div class="input">
          <label for="cpf"><i class="bi bi-card-text"></i>Cpf</label>
          <input type="text" id="cpf" name="cpf" required />
        </div>
        <div class="input">
          <label for="nome"><i class="bi bi-pencil-fill"></i>Nome</label>
          <input type="text" id="id" name="nome" />
        </div>
        <div class="input">
          <label for="email"><i class="bi bi-envelope-fill"></i>E-mail</label>
          <input type="text" id="email" name="email" />
        </div>
        <div class="row">
          <div class="input">
            <label for="limiteDeCredito"
              ><i class="bi bi-cash-stack"></i>Limite de Crédito</label
            >
            <input type="number" id="limiteDeCredito" name="limiteDeCredito" />
          </div>
          <div class="input">
            <label for="dataNascimento"
              ><i class="bi bi-calendar-date"></i>Data de Nascimento</label
            >
            <input
              type="date"
              id="dataNascimento"
              name="dataNascimento"
              required
            />
          </div>
        </div>
        <div class="botoes">
          <div>
            <button type="submit" class="salvar" name="botao" value="salvar">
              Salvar
            </button>
          </div>
          <div>
            <button type="submit" class="excluir" name="botao" value="excluir">
              Excluir
            </button>
          </div>
          <div>
            <button
              type="submit"
              class="atualizar"
              name="botao"
              value="atualizar"
            >
              Atualizar
            </button>
          </div>
        </div>
      </form>
      <div class="mensagem-container">
        <c:if test="${not empty mensagemErro}">
          <div class="erro">
            <c:out value="${mensagemErro}" />
          </div>
        </c:if>
        <c:if test="${not empty mensagemSucesso}">
          <div class="sucesso">
            <c:out value="${mensagemSucesso}" />
          </div>
        </c:if>
      </div>
    </div>
  </body>
</html>
