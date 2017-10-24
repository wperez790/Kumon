/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.main;

import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import org.kumon.business.PersonaBO;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;
import org.kumon.presentation.AsistenciaController;
import org.kumon.presentation.ComprobarAdminController;
import org.kumon.presentation.ContenedorPrincipalController;
import org.kumon.presentation.DatosConTextfieldController;
import org.kumon.presentation.DatosController;
import org.kumon.presentation.DeseaEliminarController;
import org.kumon.presentation.LoginControl;
import org.kumon.presentation.MensajeBienvenidaFController;
import org.kumon.presentation.MensajeBienvenidaMController;
import org.kumon.presentation.MenuPrincipalController;
import org.kumon.presentation.PlanillaABMAlumnoController;
import org.kumon.presentation.PlanillaABMController;
import org.kumon.presentation.PlanillaABMFamiliarController;
import org.kumon.presentation.SeleccionABM1Controller;
import org.kumon.presentation.SeleccionABM2Controller;

/*import static sun.security.jgss.GSSUtil.login;*/

/**
 *
 * @author Walter
 */
public class Contexto {

    private static DaoPersonaImpl personaDB;
    static private Contexto cx;
    public static Persona persona;
    public static Persona user;
    public static SplitPane splitPane;
    public static int tipoUser;
    public static boolean modificar;
    public static boolean baja;
    public static Stage primaryStage;

    private Contexto() {
        try {
            /*          iniciar(); */
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


     
    public static Contexto getInstance() {
        if (cx == null) {
            cx = new Contexto();
        }
        return cx;
    }

    /**
     * Metodo para contruir la clase de negocios de las Personas
     *
     * @return Devuelve el objeto PersonaBO
     */
    public static PersonaBO construirPersonaBO() {
        return new PersonaBO();
    }

    /**
     * Metodo para contruir la clase DAO de las Personas
     *
     * @return Devuelve el objeto personaDB
     */
    public static DaoPersonaImpl construirDaoPersonaImpl() {
        if (personaDB == null) {
            personaDB = new DaoPersonaImpl();
        }
        return personaDB;

    }

    //Llamado a la funcion para abrir la ventana de login
    public static void abrirLogin() throws Exception {
        LoginControl login = new LoginControl();
        login.init();
    }

    //Llamado a la funcion para abrir la ventana de MenuPrincipal
    public static void abrirMenu() throws Exception {
        MenuPrincipalController menu = new MenuPrincipalController();
        menu.init();
    }

    public static void abrirMensajeBienvenidaM() throws Exception {
        MensajeBienvenidaMController mensajeM = new MensajeBienvenidaMController();
        mensajeM.init();
    }

    public static void abrirMensajeBienvenidaF() throws Exception {
        MensajeBienvenidaFController mensajeF = new MensajeBienvenidaFController();
        mensajeF.init();
    }

    public static void abrirPlanillaABM() throws Exception {
        PlanillaABMController planilla = new PlanillaABMController();
        planilla.init();
    }

    public static void abrirSeleccionABM1() {
        SeleccionABM1Controller seleccionTarea = new SeleccionABM1Controller();
        seleccionTarea.init();
    }

    public static void abrirSeleccionABM2() {
        SeleccionABM2Controller seleccionTipoPersona = new SeleccionABM2Controller();
        seleccionTipoPersona.init();
    }

    public static Persona getPersona() {
        return persona;
    }

    public static void setPersona(Persona persona) {
        Contexto.persona = persona;
    }

    public static Persona getUser() {
        return user;
    }

    public static void setUser(Persona user) {
        Contexto.user = user;
    }

    public static boolean isModificar() {
        return modificar;
    }

    public static void setModificar(boolean modificar) {
        Contexto.modificar = modificar;
    }

    public static boolean isBaja() {
        return baja;
    }

    public static void setBaja(boolean baja) {
        Contexto.baja = baja;
    }

    public static void abrirComprobarAdmin() {
        ComprobarAdminController comprobarAdmin = new ComprobarAdminController();
        comprobarAdmin.init();
    }

    public static void abrirContenedorPrincipal() {
        ContenedorPrincipalController contenedor = new ContenedorPrincipalController();
        contenedor.init();
    }

    public static void abrirPlanillaABMfamiliar() {
        PlanillaABMFamiliarController planilla = new PlanillaABMFamiliarController();
        planilla.init();
    }

    public static void abrirAsistencia() {
        AsistenciaController asistencia = new AsistenciaController();
        asistencia.init();
    }

    public static void abrirPlanillaABMAlumno() {
        PlanillaABMAlumnoController planilla = new PlanillaABMAlumnoController();
        planilla.init();
    }

    public static void abrirPlanillaModificar() {
        DatosConTextfieldController planilla = new DatosConTextfieldController();
        planilla.init();
    }

    public static void abrirPlanillaDatos() {
        DatosController planilla = new DatosController();
        planilla.init();
    }

    public static void abrirDeseaEliminar() {
        DeseaEliminarController deseaEliminar = new DeseaEliminarController();
        deseaEliminar.init();
    }

}
