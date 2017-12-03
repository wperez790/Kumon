/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import com.mysql.jdbc.SQLError;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        personaBO.registrar(alumno);
        alumnoDB.registrar(alumno);
        Deuda deuda = new Deuda();
        SistemaBO sistemaBO = Contexto.construirSistemaBO();
        Contexto.precioPorMateria = sistemaBO.obtenerPrecioPorMateria();
        
        Calendar cal = new GregorianCalendar();
        Date fch = new java.sql.Date(cal.getTimeInMillis());
        for (int j = 1; j <= 12; j++) {
            for (int i = 0; i < alumno.getListaAsignaturas().size(); i++) {
                deuda.setIdAlumno(alumno.getDni().toString());
                deuda.setIdAsignatura(alumno.getListaAsignaturas().get(i).getIdAsignatura());
                deuda.setMonto(Contexto.precioPorMateria);
                deuda.setMontoAdeudado(Contexto.precioPorMateria);
                deuda.setNombreAlumno(alumno.getNombre()); //VER SI CONVIENE PONER NOMBRE Y APELLIDO ACA
                cal.setTimeInMillis(fch.getTime());
                cal.add(Calendar.DATE, j*31);
                java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
                deuda.setVencimiento(date);
                deudaBO.registrarDeuda(deuda);
            }
        }
    }

}
