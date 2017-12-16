/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist;

import java.sql.PreparedStatement;
import org.kumon.model.Familiar;

/**
 *
 * @author Walter
 */
public class DaoFamiliarImpl extends Conexion {

    public void registrar(Familiar familiar) throws Exception {
        this.conectar();
        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Familiar(idFamiliar,relacion,idAlumno)"
                    + " Values(?)");
            st.setString(1, familiar.getIdFamiliar());
            st.setString(2, familiar.getRelacion());
            st.setString(3, familiar.getIdAlumno());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cerrar();
    }

}
