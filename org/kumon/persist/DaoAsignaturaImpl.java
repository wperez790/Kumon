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
import org.kumon.model.Asignatura;

/**
 *
 * @author Walter
 */
public class DaoAsignaturaImpl extends Conexion {

    public boolean registrar(List<Asignatura> listaAsignaturas, String idAlumno) throws Exception {
        boolean ok = true;
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            this.conectar();
            try {
                PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Asignaturas(idAsignatura, nombre, idAlumno, nivel)"
                        + " Values(?,?,?,?)");
                st.setInt(1, listaAsignaturas.get(i).getIdAsignatura());
                st.setString(2, listaAsignaturas.get(i).getNombre());
                st.setString(3, idAlumno);
                st.setString(4, listaAsignaturas.get(i).getNivel());
                st.executeUpdate();

            } catch (Exception e) {
                ok = false;
                e.printStackTrace();
            }
            this.cerrar();
        }
        return ok;
    }
    
    public List obtenerAsignaturasById(String idAlumno) throws Exception{
         Asignatura asignatura ;
         List lista = new ArrayList();
        try {
            this.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idAsignatura, nombre, idAlumno, nivel "
                    + " FROM Asignaturas "
                    + " WHERE  idAlumno = "+ idAlumno+";");
                   /* Recorre el Result set y setea el pago con los datos y los agrega a la lista de retorno*/
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setIdAsignatura(rs.getInt("idAsignatura"));
                asignatura.setNombre(rs.getString("nombre"));
                asignatura.setIdAlumno(rs.getString("idAlumno"));
                asignatura.setNivel(rs.getString("nivel"));
                lista.add(asignatura);
            }
            /**/
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
        return lista;
    
    }
    

}
