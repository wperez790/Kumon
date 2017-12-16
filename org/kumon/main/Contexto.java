/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.main;

import java.util.Calendar;
import java.util.List;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import org.kumon.business.AdministradorBO;
import org.kumon.business.AlumnoBO;
import org.kumon.business.AsignaturaBO;
import org.kumon.business.AsistenciaBO;
import org.kumon.business.AuxiliarBO;
import org.kumon.business.DeudaBO;
import org.kumon.business.FamiliarBO;
import org.kumon.business.LibroBO;
import org.kumon.business.PagosBO;
import org.kumon.business.PersonaBO;
import org.kumon.business.PrestamoBO;
import org.kumon.business.SistemaBO;
import org.kumon.business.TareaBO;
import org.kumon.model.Libro;
import org.kumon.model.Persona;
import org.kumon.model.Tarea;
import org.kumon.persist.DaoAdministradorImpl;
import org.kumon.persist.DaoAlumnoImpl;
import org.kumon.persist.DaoAsignaturaImpl;
import org.kumon.persist.DaoAsistenciaImpl;
import org.kumon.persist.DaoAuxiliarImpl;
import org.kumon.persist.DaoDeudaImpl;
import org.kumon.persist.DaoFamiliarImpl;
import org.kumon.persist.DaoLibroImpl;
import org.kumon.persist.DaoPagosImpl;
import org.kumon.persist.DaoPersonaImpl;
import org.kumon.persist.DaoPrestamoImpl;
import org.kumon.persist.DaoSistemaImpl;
import org.kumon.persist.DaoTareaImpl;
import org.kumon.presentation.AsistenciaController;
import org.kumon.presentation.ComprobarAdminController;
import org.kumon.presentation.ConfiguracionController;
import org.kumon.presentation.ContenedorPrincipalController;
import org.kumon.presentation.DatosConTextfieldController;
import org.kumon.presentation.DatosController;
import org.kumon.presentation.DeseaEliminarController;
import org.kumon.presentation.InputPagoController;
import org.kumon.presentation.LoginControl;
import org.kumon.presentation.MensajeBienvenidaFController;
import org.kumon.presentation.MensajeBienvenidaMController;
import org.kumon.presentation.MenuPrincipalController;
import org.kumon.presentation.ModificarLibroController;
import org.kumon.presentation.PlanillaABMAlumnoController;
import org.kumon.presentation.PlanillaABMController;
import org.kumon.presentation.PlanillaABMFamiliarController;
import org.kumon.presentation.SeleccionABM1Controller;
import org.kumon.presentation.SeleccionABM2Controller;
import org.kumon.presentation.VacacionesController;
import org.kumon.presentation.VerLibroController;

/*import static sun.security.jgss.GSSUtil.login;*/
/**
 *
 * @author Walter
 */
public class Contexto {

    private static DaoPersonaImpl personaDB;
    private static DaoFamiliarImpl familiarDB;
    private static DaoLibroImpl libroDB;
    private static DaoPrestamoImpl prestamoDB;
    private static DaoDeudaImpl deudaDB;
    private static DaoPagosImpl pagosDB;
    private static DaoAlumnoImpl alumnosDB;
    private static DaoAuxiliarImpl auxiliarDB;
    private static DaoSistemaImpl sistemaDB;
    private static DaoAdministradorImpl administradorDB;
    private static DaoAsistenciaImpl asistenciaDB;
    private static DaoAsignaturaImpl asignaturaDB;
    private static DaoTareaImpl tareaDB;
    static private Contexto cx;
    public static Persona persona;
    public static Persona user;
    public static SplitPane splitPane;
    public static int tipoUser;
    public static boolean modificar;
    public static boolean baja;
    public static Stage primaryStage;
    public static Libro libro;
    public static boolean prestamosNoDevueltos = true;
    public static Integer tipoDeuda = 0;
    public static Double precioPorMateria;
    public static boolean SeteoPago = false;
    public static String idDeuda;
    public static Double monto;
    public static boolean pagos;
    public static boolean prestamosVencidos;
    public static Integer day;
    public static Calendar fecha;
    public static List<Tarea> lista;

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

    public static PrestamoBO construirPrestamoBO() {
        return new PrestamoBO();
    }

    public static LibroBO construirLibroBO() {
        return new LibroBO();
    }

    public static DeudaBO construirDeudaBO() {
        return new DeudaBO();
    }

    public static PagosBO construirPagosBO() {
        return new PagosBO();
    }

    public static AlumnoBO construirAlumnoBO() {
        return new AlumnoBO();
    }

    public static FamiliarBO construirFamiliarBO() {
        return new FamiliarBO();
    }

    public static AuxiliarBO construirAuxiliarBO() {
        return new AuxiliarBO();
    }

    public static SistemaBO construirSistemaBO() {
        return new SistemaBO();
    }

    public static AdministradorBO construirAdminBO() {
        return new AdministradorBO();
    }

    public static AsistenciaBO construirAsistenciaBO() {
        return new AsistenciaBO();
    }

    public static AsignaturaBO construirAsignarutraBO() {
        return new AsignaturaBO();
    }

    public static TareaBO construirTareaBO() {
        return new TareaBO();
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

    /**
     * Metodo para contruir la clase DAO de los Prestamos
     *
     * @return Devuelve el objeto prestamosDB
     */
    public static DaoPrestamoImpl construirDaoPrestamoImpl() {
        if (prestamoDB == null) {
            prestamoDB = new DaoPrestamoImpl();
        }
        return prestamoDB;
    }

    /**
     * Metodo para contruir la clase DAO de las Deudas
     *
     * @return Devuelve el objeto deudaDB
     */
    public static DaoDeudaImpl construirDaoDeudaImpl() {
        if (deudaDB == null) {
            deudaDB = new DaoDeudaImpl();
        }
        return deudaDB;
    }

    /**
     * Metodo para contruir la clase DAO de los Libros
     *
     * @return Devuelve el objeto libroDB
     */
    public static DaoLibroImpl construirDaoLibroImpl() {
        if (libroDB == null) {
            libroDB = new DaoLibroImpl();
        }
        return libroDB;
    }

    /**
     * Metodo para contruir la clase DAO de los Pagos
     *
     * @return Devuelve el objeto pagosDB
     */
    public static DaoPagosImpl construirDaoPagosImpl() {
        if (pagosDB == null) {
            pagosDB = new DaoPagosImpl();
        }
        return pagosDB;
    }

    /**
     * Metodo para contruir la clase DAO de los Alumnos
     *
     * @return Devuelve el objeto alumnosDB
     */
    public static DaoAlumnoImpl construirDaoAlumnoImpl() {
        if (alumnosDB == null) {
            alumnosDB = new DaoAlumnoImpl();
        }
        return alumnosDB;
    }

    /**/
    /**
     * Metodo para contruir la clase DAO de los Familiar
     *
     * @return Devuelve el objeto familiarDB
     */
    public static DaoFamiliarImpl construirDaoFamiliarImpl() {
        if (familiarDB == null) {
            familiarDB = new DaoFamiliarImpl();
        }
        return familiarDB;
    }

    /**/
    /**
     * Metodo para contruir la clase DAO de los Auxiliares
     *
     * @return Devuelve el objeto auxiliarDB
     */
    public static DaoAuxiliarImpl construirDaoAuxiliarImpl() {
        if (auxiliarDB == null) {
            auxiliarDB = new DaoAuxiliarImpl();
        }
        return auxiliarDB;
    }

    /**/
 /**/
    /**
     * Metodo para contruir la clase DAO de Sistema
     *
     * @return Devuelve el objeto sistemaDB
     */
    public static DaoSistemaImpl construirDaoSistemaImpl() {
        if (sistemaDB == null) {
            sistemaDB = new DaoSistemaImpl();
        }
        return sistemaDB;
    }

    /**
     * Metodo para contruir la clase DAO de los Administradores
     *
     * @return Devuelve el objeto adminsDB
     */
    public static DaoAdministradorImpl construirDaoAdministradorImpl() {
        if (administradorDB == null) {
            administradorDB = new DaoAdministradorImpl();
        }
        return administradorDB;
    }

    /**/
    /**
     * Metodo para contruir la clase DAO de las Asistencias
     *
     * @return Devuelve el objeto asistenciaDB
     */
    public static DaoAsistenciaImpl construirDaoAsistenciaImpl() {
        if (asistenciaDB == null) {
            asistenciaDB = new DaoAsistenciaImpl();
        }
        return asistenciaDB;
    }

    /**/
    /**
     * Metodo para contruir la clase DAO de las Asignaturas
     *
     * @return Devuelve el objeto asignaturaDB
     */
    public static DaoAsignaturaImpl construirDaoAsignaturaImpl() {
        if (asignaturaDB == null) {
            asignaturaDB = new DaoAsignaturaImpl();
        }
        return asignaturaDB;
    }

    /**/
    /**
     * Metodo para contruir la clase DAO de las Tareas para el Planificador
     *
     * @return Devuelve el objeto tareaDB
     */
    public static DaoTareaImpl construirDaoTareaImpl() {
        if (tareaDB == null) {
            tareaDB = new DaoTareaImpl();
        }
        return tareaDB;
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

    public static void abrirVerLibro() {
        VerLibroController verLibro = new VerLibroController();
        verLibro.init();
    }

    public static void abrirModificarLibro() {
        ModificarLibroController modificarLibro = new ModificarLibroController();
        modificarLibro.init();
    }

    public static void abrirConfiguracion() {
        ConfiguracionController configuracion = new ConfiguracionController();
        configuracion.init();
    }

    public static void abrirInputPago() {
        InputPagoController input = new InputPagoController();
        input.init();
    }

    public static void abrirVacaciones() {
        VacacionesController vacaciones = new VacacionesController();
        vacaciones.init();
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

    public static void abrirPlanillaABMAlumno() throws Exception {
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
