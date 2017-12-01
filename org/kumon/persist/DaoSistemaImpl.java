/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.kumon.persist.interfaces.ISistema;

/**
 *
 * @author walt
 */
public class DaoSistemaImpl extends Conexion implements ISistema {

    @Override
    public double obtenerPrecioPorMateria() throws Exception {
        Double precio= null;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT precioPorMateria FROM Sistema");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                precio = rs.getDouble("precioPorMateria");
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return precio;
    }

    @Override
    public void modificarPrecioPorMateria(Double precioNuevo) throws Exception {
        this.conectar();
        try {
                PreparedStatement st = this.conexion.prepareStatement("UPDATE Sistema SET precioPorMateria = ? ;");
                st.setDouble(1, precioNuevo);
                st.executeUpdate();
    
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.cerrar();
    }
    
}
