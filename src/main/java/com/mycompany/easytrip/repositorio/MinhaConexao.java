package com.mycompany.easytrip.repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MinhaConexao {
    
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("easyTrip");
    
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    
    public static void finalizar(EntityManager em){
        if (em.isOpen())
            em.close();
    }
    /*private static final String URL = "jdbc:mysql://localhost:3306/easytrip";
    private static final String USUARIO = "root";
    private static final String SENHA = "Password123@@";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }*/
}
