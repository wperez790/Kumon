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
public class Familiar extends Persona{
    
    private List<Alumno> alumnosList = new ArrayList<Alumno>();
    
    private String idFamiliar;

    public List<Alumno> getHijosList() {
        return alumnosList;
    }

    public void setHijosList(List<Alumno> hijosList) {
        this.alumnosList = hijosList;
    }

    public String getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(String idFamiliar) {
        this.idFamiliar = idFamiliar;
    }
    
    

    public Familiar(List<Alumno> hijosList) {
        this.alumnosList = hijosList;
    }

    public Familiar() {
    }


   
}
