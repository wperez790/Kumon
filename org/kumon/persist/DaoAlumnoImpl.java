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
    public void registrar(Alumno alumno, RelacionFamiliarAlumno relacionFA) throws Exception {
        
        try{
           this.conectar();
           //TABLA ALUMNOS
           PreparedStatement st=this.conexion.prepareStatement("INSERT INTO Alumnos(idPersona, idAuxiliar, idOrientadora) Values(?,?,?)");
           st.setString(1, alumno.getIdPersona());
           st.setString(2, alumno.getIdAuxiliar());
           st.setString(3, alumno.getIdOrientadora());
           //TABLA PERSONAS
           st=this.conexion.prepareStatement("INSERT INTO Personas(nombre,apellido,dni,telefono,fechaNacimiento,domicilio,user,pass,idAcademia)"
                   + "VALUES(?,?,?,?,?,?,?,?,?)");
           st.setString(1, alumno.getNombre());
           st.setString(2, alumno.getApellido());
           st.setInt(3, alumno.getDni());
           st.setString(4, alumno.getTelefono());
           st.setDate(5, (Date) alumno.getFechaNacimiento());
           st.setString(6, alumno.getDomicilio());
           /*st.setString(7, alumno.getUser());
           st.setString(8, alumno.getPass());*/
           //TABLA RelacionFamiliarAlumno
           for(Familiar f: alumno.getListaFamiliares()){
           st=this.conexion.prepareStatement("INSERT INTO RelacionFamiliarAlumno(idFamiliar,idAlumno,relacion) "
                   + "VALUES(?,?,?)");
            st.setString(1, f.getIdFamiliar());
            st.setString(2, alumno.getIdPersona());
            st.setString(3, relacionFA.getRelacion());
           }    
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
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

 
