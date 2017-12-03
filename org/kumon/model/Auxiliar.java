/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Auxiliar extends Persona {

    List<Alumno> alumnosList;
    private String idAuxiliar;

 

    public String getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(String idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }

    public List<Alumno> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumno> alumnosList) {
        this.alumnosList = alumnosList;
    }

}
