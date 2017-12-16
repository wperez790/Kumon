/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.kumon.model.Alumno;
import org.kumon.model.Familiar;
import org.kumon.model.RelacionFamiliarAlumno;
import org.kumon.persist.interfaces.IAlumno;

/**
 *
 * @author Walter
 */
public class DaoAlumnoImpl extends Conexion implements IAlumno {

    @Override
    public boolean registrar(Alumno alumno) throws Exception {
        boolean ok=true;
        try {
            this.conectar();
            //TABLA ALUMNOS
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Alumnos(idAlumno,idAuxiliar, idOrientador) Values(?,?,?)");
            st.setString(1, alumno.getIdPersona());
            st.setString(2, alumno.getIdAuxiliar());    //Si tiene Auxiliar carga auxiliar como encargada del alumno sino es porq tiene orientadora a cargo.
            st.setString(3, alumno.getIdOrientadora());
            st.executeUpdate();
        } catch (Exception e) {
            ok=false;
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return ok;
    }

    @Override
    public void modificar(Alumno alumno) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(String idAlumno) throws Exception {
         this.conectar();
        try {

            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM Alumnos"
                    + " WHERE idAlumno = '" + idAlumno + "';");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Alumno> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
