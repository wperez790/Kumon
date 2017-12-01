/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Alumno extends Persona{
   
    private List<Asignatura> listaAsignaturas;
    private String idAuxiliar;
    private String idAlumno;
    private String idOrientadora;
    private List<Familiar> listaFamiliares ;

    public Alumno(List<Asignatura> listaAsignaturas, List<Familiar> listaFamiliares) {
        this.listaAsignaturas = listaAsignaturas;
        this.listaFamiliares = listaFamiliares;
    }
 
    public List<Familiar> getListaFamiliares() {
        return listaFamiliares;
    }

    public void setListaFamiliares(List<Familiar> listaFamiliares) {
        this.listaFamiliares = listaFamiliares;
    }
    
    
    public String getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(String idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }

    public String getIdOrientadora() {
        return idOrientadora;
    }

    public void setIdOrientadora(String idOrientadora) {
        this.idOrientadora = idOrientadora;
    }
    

    
    public List<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    
    
    
    
}
