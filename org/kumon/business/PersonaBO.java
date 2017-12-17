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
import org.kumon.model.Deuda;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;

/**
 *
 * @author Walter
 */
public class PersonaBO {

    private DaoPersonaImpl personaDB;
    private DeudaBO deudasBO;
    private AlumnoBO alumnosBO;
    private PagosBO pagosBO;
    private AsistenciaBO asistenciaBO;
    private AuxiliarBO auxiliarBO;

    public PersonaBO() {
        personaDB = Contexto.construirDaoPersonaImpl();
        deudasBO = Contexto.construirDeudaBO();
        pagosBO = Contexto.construirPagosBO();
        alumnosBO = Contexto.construirAlumnoBO();
        asistenciaBO = Contexto.construirAsistenciaBO();
        auxiliarBO = Contexto.construirAuxiliarBO();
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

    public boolean modificar(Persona persona) throws Exception {
        return personaDB.modificar(persona);
    }

    public void eliminar(Persona persona) throws Exception {
        List<String> lista = deudasBO.getIdDeudaByIdPersona(persona.getIdPersona());
        Integer idDeuda;
        for (int i = 0; i < lista.size(); i++) {
            idDeuda = Integer.parseInt(lista.get(i));
            pagosBO.anularPagoByIdDeuda(idDeuda);
        }
        deudasBO.anularDeudaByIdPersona(persona.getIdPersona());
        asistenciaBO.eliminarById(persona.getIdPersona());
        alumnosBO.eliminar(persona.getIdPersona());
        auxiliarBO.eliminar(persona.getIdPersona());
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
        deudasBO.anularDeudaByIdPersona(dni.toString());
        personaDB.setInactivo(personaDB.buscarById(dni));
    }

    public void setActivoById(Integer dni) throws Exception {
        personaDB.setActivo(personaDB.buscarById(dni));
    }

    public boolean eliminar(Integer dni) throws Exception {
        boolean ok = true;
        try {
            List<String> lista = deudasBO.getIdDeudaByIdPersona(dni.toString());
            Integer idDeuda;
            for (int i = 0; i < lista.size(); i++) {
                idDeuda = Integer.parseInt(lista.get(i));
                pagosBO.anularPagoByIdDeuda(idDeuda);
            }
            deudasBO.anularDeudaByIdPersona(dni.toString());
            asistenciaBO.eliminarById(dni.toString());
            alumnosBO.eliminar(dni.toString());
            auxiliarBO.eliminar(dni.toString());
            personaDB.eliminar(personaDB.buscarById(dni));
        } catch (Exception exception) {
            exception.printStackTrace();
            ok = false;
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
