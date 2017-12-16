/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;
import org.kumon.persist.DaoAlumnoImpl;
import org.kumon.persist.DaoAsistenciaImpl;
import org.kumon.persist.DaoDeudaImpl;
import org.kumon.persist.DaoPagosImpl;
import org.kumon.persist.DaoPersonaImpl;

/**
 *
 * @author Walter
 */
public class PersonaBO {

    private DaoPersonaImpl personaDB = Contexto.construirDaoPersonaImpl();
    private DaoDeudaImpl deudasDB = Contexto.construirDaoDeudaImpl();
    private DaoAlumnoImpl alumnosDB = Contexto.construirDaoAlumnoImpl();
    private DaoPagosImpl pagosDB = Contexto.construirDaoPagosImpl();
    private DaoAsistenciaImpl asistenciaDB = Contexto.construirDaoAsistenciaImpl();

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

    /* Calcula la Edad sacando el periodo entre la fecha actual y la de nacimiento*/
    public Integer calcularEdad(JFXDatePicker datePickerFecha) {

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
        switch (tipoUser) {
            case 1:
                retorno = "Administrador";
                break;
            case 2:
                retorno = "Auxiliar";
                break;
            case 3:
                retorno = "Alumno";
                break;
            case 4:
                retorno = "Familiar";
                break;
            default:
                retorno = "NaN";
                break;
        }
        return retorno;
    }

    public boolean registrar(Persona persona) throws Exception {
        return (personaDB.registrar(persona));
    }

    public void modificar(Persona persona) throws Exception {
        personaDB.modificar(persona);
    }

    public void eliminar(Persona persona) throws Exception {
        List lista = deudasDB.getIdDeudaByIdPersona(persona.getIdPersona());
        for (int i = 0; i < lista.size(); i++) {
            pagosDB.anularPagoByIdDeuda(lista.get(i).toString());
        }
        deudasDB.anularDeudaByIdPersona(persona.getIdPersona());
        asistenciaDB.eliminarById(persona.getIdPersona());
        alumnosDB.eliminar(persona.getIdPersona());
        personaDB.eliminar(persona);

    }

    public List buscarByDate() throws Exception {
        Calendar cal = Calendar.getInstance();
        return personaDB.buscarByDate(cal);
    }

    public Persona obtenerPersonaByUser(String user) throws Exception {
        return personaDB.obtenerPersonaByUser(user);
    }

    public void setInactivoById(Integer dni) throws Exception {
        deudasDB.anularDeudaByIdPersona(dni.toString());
        personaDB.setInactivo(personaDB.buscarById(dni));
    }

    public void setActivoById(Integer dni) throws Exception {
        personaDB.setActivo(personaDB.buscarById(dni));
    }

    public boolean eliminar(Integer dni) throws Exception {
        boolean ok = true;
        try {
            List lista = deudasDB.getIdDeudaByIdPersona(dni.toString());
            for (int i = 0; i < lista.size(); i++) {
                pagosDB.anularPagoByIdDeuda(lista.get(i).toString());
            }
            deudasDB.anularDeudaByIdPersona(dni.toString());
            asistenciaDB.eliminarById(dni.toString());
            alumnosDB.eliminar(dni.toString());
            personaDB.eliminar(personaDB.buscarById(dni));
        } catch (Exception exception) {
            exception.printStackTrace();
            ok= false;
        }
return ok;
    }

    public Map<Integer, String> obtenerTodos() throws Exception {
        return personaDB.obtenerTodos();
    }

    public Persona buscarById(Integer id) throws Exception {
        final Persona buscarById = personaDB.buscarById(id);
        if (buscarById != null) {
            return buscarById;
        } else {
            return new Persona();
        }
    }

    public boolean comprobarUser(String text) throws Exception {
        return personaDB.comprobarUser(text);
    }

    public String getTextCuentaActiva(int estado) {
        if (estado == 0) {
            return "Inactivo";
        } else {
            return "Activo";
        }
    }

    public boolean getBooleanEstadoCuenta(int estado) {
        if (estado == 0) {
            return false;
        } else {
            return true;
        }
    }

}
