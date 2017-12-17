/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.kumon.model.Auxiliar;
import org.kumon.persist.interfaces.IAuxiliar;

/**
 *
 * @author Walter
 */
public class DaoAuxiliarImpl extends Conexion implements IAuxiliar {

    @Override
    public List getAll() throws Exception {
        List lista = new ArrayList();
        Auxiliar auxiliar;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombrePersona, apellido, idAuxiliar"
                    + " FROM Auxiliares inner join Personas "
                    + " WHERE idPersona = idAuxiliar;");
            /* Recorre el Result set, setea los Auxiliares con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                auxiliar = new Auxiliar();
                auxiliar.setIdAuxiliar(rs.getString("idAuxiliar"));
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

    public void agregarAuxiliar(String idPersona) throws Exception {
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Auxiliares(idAuxiliar)"
                    + " Values(?)");
            st.setString(1, idPersona);

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
    }

    public boolean setVacaciones(Date date, String id) throws Exception {
        boolean ok = true;
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Auxiliares SET fechaRegreso = ? , vacaciones = 1 "
                    + "WHERE idAuxiliar = " + id + ";");
            st.setDate(1, date);
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        this.cerrar();
        return ok;
    }

    public void setRetornoVacaciones(String id) throws Exception {
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Auxiliares SET vacaciones = 0 "
                    + "WHERE idAuxiliar = " + id + ";");
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();

    }

    public List getPersonalVacaciones() throws Exception {
        List lista = new ArrayList();
        Auxiliar auxiliar;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idAuxiliar"
                    + " FROM Auxiliares "
                    + " WHERE vacaciones = 1;");
            /* Recorre el Result set, setea los Auxiliares con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                auxiliar = new Auxiliar();
                auxiliar.setIdAuxiliar(rs.getString("idAuxiliar"));
                lista.add(auxiliar);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;
    }

    public void eliminar(String idPersona) throws Exception {
        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Auxiliares"
                    + " WHERE idAuxiliar = " + idPersona + ";");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
    }


}
