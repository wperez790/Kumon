/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.Date;
import java.sql.PreparedStatement;
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
    public void registrar(Alumno alumno) throws Exception {

        try {
            this.conectar();
            //TABLA ALUMNOS
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Alumnos(idAlumno,idAuxiliar, idOrientador) Values(?,?,?)");
            st.setString(1, alumno.getIdPersona());
            st.setString(2, alumno.getIdAuxiliar());    //Si tiene Auxiliar carga auxiliar como encargada del alumno sino es porq tiene orientadora a cargo.
            st.setString(3, alumno.getIdOrientadora());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Alumno alumno) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Alumno alumno) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Alumno> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
