package br.senai.sc.livros.model.dao.conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection connectDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/conecteLivros";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }
}
