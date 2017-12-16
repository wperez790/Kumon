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
import java.util.List;
import org.kumon.model.Asistencia;
import org.kumon.persist.interfaces.IAsistencia;

/**
 *
 * @author walt
 */
public class DaoAsistenciaImpl extends Conexion implements IAsistencia{

    @Override
    public void registrar(Asistencia asistencia) throws Exception {
        this.conectar();
        try{
           
           PreparedStatement st=this.conexion.prepareStatement("INSERT INTO Asistencia(idPersona,fecha,horaEntrada)"
                   + " Values(?,?,?)");
           st.setString(1, asistencia.getIdPersona());
           st.setDate(2, asistencia.getFecha());
           st.setString(3, asistencia.getHoraEntrada());
           st.executeUpdate();
           }
        catch(Exception e){
            e.printStackTrace();
        }
    
    
    }

    @Override
    public List obtenerTodas() throws Exception {
         List lista = new ArrayList();
        Asistencia asistencia;
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Asistencia;");
            /* Recorre el Result set y setea el pago con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                asistencia = new Asistencia();
                asistencia.setIdPersona(rs.getString("idPersona"));
                asistencia.setFecha(rs.getDate("fecha"));
                asistencia.setHoraEntrada(rs.getString("horaEntrada"));
                asistencia.setHoraSalida(rs.getString("horaSalida"));
                lista.add(asistencia);
            }
            /**/
        } catch (Exception e) {
            throw new Exception("Metodo: obtenerTodos() error");
        }
        this.cerrar();

        return lista;
    }

    public void eliminarById(String idPersona) throws Exception {
        this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Asistencia"
                    + " WHERE idPersona = " + idPersona +" ;");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrar();
    }
}
