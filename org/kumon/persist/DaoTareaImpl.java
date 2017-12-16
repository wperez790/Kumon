/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.kumon.model.Tarea;
import org.kumon.persist.interfaces.ITarea;

/**
 *
 * @author walt
 */
public class DaoTareaImpl extends Conexion implements ITarea {

    @Override
    public boolean registrar(Tarea tarea) throws Exception {
        boolean ok = true;
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Tareas(dia,hora,info)"
                    + " Values(?,?,?)");
            st.setDate(1, tarea.getDia());
            st.setString(2, tarea.getHora());
            st.setString(3, tarea.getInfo());
            st.executeUpdate();

        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        this.cerrar();
        return ok;
    }

    @Override
    public boolean modificar(Tarea tarea) throws Exception {
        boolean ok = true;
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Tareas SET dia = ?, hora = ?,info = ?"
                    + " WHERE idTarea = " + tarea.getIdTarea() + ";");
            st.setDate(1, tarea.getDia());
            st.setString(2, tarea.getHora());
            st.setString(3, tarea.getInfo());
            st.executeUpdate();
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        this.cerrar();
        return ok;
    }

    @Override
    public boolean eliminar(Integer idTarea) throws Exception {
        boolean ok = true;
        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Tareas"
                    + " WHERE idTarea = " + idTarea + ";");
            st.executeUpdate();
        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        }
        this.cerrar();
        return ok;
    }

    @Override
    public List<Tarea> buscarByDate(Calendar fecha) throws Exception {
        List lista = new ArrayList();
        Tarea tarea;
        int month = fecha.get(Calendar.MONTH) + 1;
        int day = fecha.get(Calendar.DAY_OF_MONTH);
        int year = fecha.get(Calendar.YEAR);
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idTarea, dia, hora, info"
                    + " FROM Tareas where DAYOFMONTH(dia) = " + day + " and MONTH(dia)= " + month + " and YEAR(dia)= "+ year +" order by hora");
            /* Recorre el Result set y setea los datos que necesito en una tarea auxiliar*/
            while (rs.next()) {
                tarea = new Tarea();
                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setDia(rs.getDate("dia"));
                tarea.setHora(rs.getString("hora"));
                tarea.setInfo(rs.getString("info"));
                lista.add(tarea);

            }
            /**/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
