/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDate;
import java.time.Period;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;
import org.kumon.persist.interfaces.IPersona;

/**
 *
 * @author Walter
 */
public class PersonaBO {

    private DaoPersonaImpl personaDB = Contexto.construirDaoPersonaImpl();

    public PersonaBO() {

    }

    public boolean comprobarUsuario(String user, String pass) throws Exception {
        Persona aux;
        aux = personaDB.obtenerPersonaByUser(user);

        if (aux == null) {
            personaDB.cerrar();
            return false;
        }
        if (aux.getUser().equals(user) && aux.getPass().equals(pass)) {

            personaDB.cerrar();
            return true;
        }
        return false;
    }

    public Integer calcularEdad(JFXDatePicker datePickerFecha) {
        /* Calcula la Edad sacando el periodo entre la fecha actual y la de nacimiento*/
        Period periodo = Period.between(datePickerFecha.getValue(), LocalDate.now());
        return periodo.getYears();
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String getTextTipoUser(Integer tipoUser) {
       String retorno;
        switch(tipoUser){
            case 1: retorno= "Administrador";
            break;
            case 2: retorno= "Auxiliar";
            break;
            case 3: retorno= "Alumno";
            break;
            case 4: retorno= "Familiar";
            break;
            default: retorno= "NaN";
            break;
        }
        return retorno;
    }

    public void setInactivoById(Integer dni) throws Exception {
        personaDB.setInactivo(personaDB.buscarById(dni));
    }
    public void setActivoById(Integer dni) throws Exception {
        personaDB.setActivo(personaDB.buscarById(dni));
    }

    public void eliminar(Integer dni) throws Exception {
        personaDB.eliminar(personaDB.buscarById(dni));
    }

    public String getTextCuentaActiva(int estado) {
       if(estado == 0)
           return "Inactivo";
       else
           return "Activo";
    }

    public boolean getBooleanEstadoCuenta(int estado) {
        if(estado == 0)
            return false;
        else
            return true;
    }
}
