/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.kumon.business.AdministradorBO;
import org.kumon.model.Administrador;
import org.kumon.model.Auxiliar;

/**
 *
 * @author walt
 */
public class DaoAdministradorImpl extends Conexion{

    public void agregarAdmin(String idPersona) throws Exception {
         this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Administradores(idAdmin)"
                    + " Values(?)");
            st.setString(1, idPersona);
            
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
    }
    
    public List getAll() throws Exception {
        List lista = new ArrayList();
        Administrador admin;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombrePersona, apellido, idAdmin"
                    + " FROM Administradores inner join Personas "
                    + " WHERE idPersona = idAdmin;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                admin = new Administrador();
                admin.setIdAdmin(rs.getString("idAdmin"));
                admin.setNombre(rs.getString("nombrePersona"));
                admin.setApellido(rs.getString("apellido"));
                lista.add(admin);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;

    }
    
}
