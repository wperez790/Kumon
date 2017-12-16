/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Asignatura;
import org.kumon.persist.DaoAsignaturaImpl;

/**
 *
 * @author Walter
 */
public class AsignaturaBO {

    private DaoAsignaturaImpl asignaturaDB = Contexto.construirDaoAsignaturaImpl();

    public boolean registrar(List<Asignatura> listaAsignaturas, String idAlumno) throws Exception {

        return asignaturaDB.registrar(listaAsignaturas, idAlumno);

    }

    public List obtenerAsignaturasById(String idAlumno) throws Exception {
        return asignaturaDB.obtenerAsignaturasById(idAlumno);
    }

}
