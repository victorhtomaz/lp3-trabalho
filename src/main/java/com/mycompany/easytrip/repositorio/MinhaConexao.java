package com.mycompany.easytrip.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MinhaConexao {
    private static final String URL = "jdbc:mysql://localhost:3306/easytrip";
    private static final String USUARIO = "root";
    private static final String SENHA = "Password123@@";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
