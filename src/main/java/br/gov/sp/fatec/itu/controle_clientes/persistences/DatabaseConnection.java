package br.gov.sp.fatec.itu.controle_clientes.persistences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseConnection {
  private Connection c;
  public Connection getConnection() throws ClassNotFoundException, SQLException {
    String hostName = "localhost";
    String dbName = "Clientes";
    String user = "bia";
    String senha = "@Bea10022006!";
    Class.forName("net.sourceforge.jtds.jdbc.Driver");
    c = DriverManager.getConnection(
        String.format("jdbc:jtds:sqlserver://%s:60871;databaseName=%s;user=%s;password=%s;",
            hostName, dbName, user, senha));
    return c;
  }
}
