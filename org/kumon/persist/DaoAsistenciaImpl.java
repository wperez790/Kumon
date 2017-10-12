/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
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
           st.setDate(3, asistencia.getHoraEntrada());
           }
        catch(Exception e){
            e.printStackTrace();
        }
    
    
    }
}
