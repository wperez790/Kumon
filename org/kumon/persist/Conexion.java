/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Walter
 */
public class Conexion {
    
     protected Connection conexion;

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/kumon";

    private final String USER = "root";
    private final String PASS = "root";

    public void conectar() throws Exception {
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cerrar() throws Exception{
        if (conexion!=null)
        {
            if(!conexion.isClosed())
            {
                conexion.close();
            }
        }
    }
    
}
