package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.gerenciamento.controller.ProdutoController;

public class ProdutoQuery {

    private static String retorno;

    public String UserQuery() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbappguia", "netbeans", "netbeans");
        String sql = "SELECT online FROM logado";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        ResultSet resultado;
        resultado = statement.getResultSet();
        while (resultado.next()) {
            retorno = resultado.getString(1);
        }
        return retorno;
    }

    public void UserAtual(String tagusuario) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbappguia", "netbeans", "netbeans");
            String sql = "UPDATE logado SET online = '" + tagusuario + "' WHERE sessionid = 1;";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.execute();
        } catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(ProdutoController.class.getName()).log(null);
        }
    }

    public void InserirLogin(Long produtoId, String tagusuario) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbappguia", "netbeans", "netbeans");
            String sql = "UPDATE produto SET login = '" + tagusuario + "' WHERE prodid =  '"+ produtoId + "';";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.execute();
        } catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(ProdutoController.class.getName()).log(null);
        }
    }
}
