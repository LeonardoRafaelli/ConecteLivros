package br.senai.sc.livros.model.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
    private final String url = "jdbc:mysql://localhost:3306/conecteLivros";
    private final String username = "root";
    private final String password = "root";

    public Connection connectDB() {
        try{
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            throw new RuntimeException("Erro na conexão!\nMessage: " + e.getMessage() + "\nCause: " + e.getCause());
        }
    }
}
