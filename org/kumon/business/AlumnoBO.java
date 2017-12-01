/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import com.mysql.jdbc.SQLError;
import java.util.Date;
import org.kumon.main.Contexto;
import org.kumon.model.Alumno;
import org.kumon.model.Deuda;
import org.kumon.model.Persona;
import org.kumon.persist.DaoAlumnoImpl;

/**
 *
 * @author Walter
 */
public class AlumnoBO {

    private DaoAlumnoImpl alumnoDB = Contexto.construirDaoAlumnoImpl();
    private PersonaBO personaBO = Contexto.construirPersonaBO();
    private DeudaBO deudaBO = Contexto.construirDeudaBO();

    public void registrar(Alumno alumno) throws Exception {
        alumnoDB.registrar(alumno);
        personaBO.registrar(alumno);
        Deuda deuda = new Deuda();
        SistemaBO sistemaBO = Contexto.construirSistemaBO();
        Contexto.precioPorMateria = sistemaBO.obtenerPrecioPorMateria();
        for (int j = 1; j <= 12; j++) {
            for (int i = 0; i < alumno.getListaAsignaturas().size(); i++) {
                deuda.setIdAlumno(alumno.getDni().toString());
                deuda.setIdAsignatura(alumno.getListaAsignaturas().get(i).getIdAsignatura());
                deuda.setMonto(Contexto.precioPorMateria);
                deuda.setMontoAdeudado(Contexto.precioPorMateria);
                deuda.setNombreAlumno(alumno.getNombre()); //VER SI CONVIENE PONER NOMBRE Y APELLIDO ACA
                Date fecha = new Date();
                java.sql.Date date = new java.sql.Date(fecha.getTime() + i*(31l * 24l * 60l * 60l));
                deuda.setVencimiento(date);
                deudaBO.registrarDeuda(deuda);
            }
        }
    }

}
