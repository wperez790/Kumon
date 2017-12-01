/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.kumon.model.Auxiliar;
import org.kumon.persist.interfaces.IAuxiliar;

/**
 *
 * @author Walter
 */
public class DaoAuxiliarImpl extends Conexion implements IAuxiliar{

    @Override
    public List getAll() throws Exception {
        List lista = new ArrayList();
        Auxiliar auxiliar;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombrePersona, apellido, idAuxiliar, idAdmin"
                    + "FROM Auxiliares a inner join Administradores ad inner join Personas p "
                    + "WHERE p.idPersona = a.idAuxiliar AND p.idPersona = ad.idAdmin;");
            /* Recorre el Result set y setea el prestamo con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                auxiliar = new Auxiliar();
                auxiliar.setIdAuxiliar(rs.getString("idAuxiliar"));
                auxiliar.setIdAdmin(rs.getString("idAdmin"));
                auxiliar.setNombre(rs.getString("nombrePersona"));
                auxiliar.setApellido(rs.getString("apellido"));
                lista.add(auxiliar);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;

    }

}
