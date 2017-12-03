/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.AdministradorBO;
import org.kumon.business.AlumnoBO;
import org.kumon.business.AuxiliarBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Administrador;
import org.kumon.model.Alumno;
import org.kumon.model.Asignatura;
import org.kumon.model.Auxiliar;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class PlanillaABMAlumnoController implements Initializable {

    @FXML
    private AnchorPane planilla;
    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldApellido;
    @FXML
    private JFXTextField textFieldDocumento;
    @FXML
    private JFXTextField textFieldDomicilio;
    @FXML
    private JFXTextField textFieldTelefono;
    @FXML
    private ImageView imagenPersona;
    @FXML
    private JFXDatePicker datePickerFecha;
    @FXML
    private JFXComboBox<String> comboBoxSexo;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField textFieldNombreImg;
    @FXML
    private JFXTextField textFieldMail;
    @FXML
    private JFXButton btnCargarFamiliar;
    @FXML
    private JFXCheckBox checkBoxMat;
    @FXML
    private JFXCheckBox checkBoxLengua;
    @FXML
    private JFXCheckBox checkBoxIngles;
    @FXML
    private JFXTextArea textAreaInfoAdicional;
    @FXML
    private JFXComboBox<String> comboBoxAuxiliar;

    //AUXILIARES
    private static Stage primaryStage = new Stage();
    private Notifications notificacion;
    private Notifications error2;
    Persona persona;
    Alumno alumno;
    PersonaBO personaBO;
    AlumnoBO alumnoBO;
    AuxiliarBO auxiliarBO;
    AdministradorBO adminBO;
    List<Asignatura> listaAsignaturas;
    Asignatura asignatura;
    List lista;
    List lista2;

    public PlanillaABMAlumnoController() throws Exception {
        persona = new Persona();
        alumno = new Alumno();
        personaBO = Contexto.construirPersonaBO();
        alumnoBO = Contexto.construirAlumnoBO();
        auxiliarBO = Contexto.construirAuxiliarBO();
        adminBO = Contexto.construirAdminBO();
        lista = auxiliarBO.getAll();
        lista2 = adminBO.getAll();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBoxSexo.getItems().addAll("Masculino", "Femenino");
        for (int i = 0; i < lista.size(); i++) {
            Auxiliar aux = (Auxiliar) lista.get(i);
            comboBoxAuxiliar.getItems().addAll(aux.getNombre() + " " + aux.getApellido());
        }
        for (int i = 0; i < lista2.size(); i++) {
            Administrador aux = (Administrador) lista2.get(i);
            comboBoxAuxiliar.getItems().addAll(aux.getNombre() + " " + aux.getApellido());
        }
    }

    @FXML
    private void btnGuardarAction(ActionEvent event) throws Exception {
        listaAsignaturas = new ArrayList();
        boolean ok = cargarDatos();
        //SETEO ALUMNO//
        if (checkBoxIngles.isSelected()) {
            asignatura = new Asignatura();
            asignatura.setIdAsignatura(3);
            asignatura.setNivel("0");
            asignatura.setNombre("Ingles");
            listaAsignaturas.add(asignatura);
        }
        if (checkBoxLengua.isSelected()) {
            asignatura = new Asignatura();
            asignatura.setIdAsignatura(2);
            asignatura.setNivel("0");
            asignatura.setNombre("Lengua");
            listaAsignaturas.add(asignatura);
        }
        if (checkBoxMat.isSelected()) {
            asignatura = new Asignatura();
            asignatura.setIdAsignatura(1);
            asignatura.setNivel("0");
            asignatura.setNombre("Matematica");
            listaAsignaturas.add(asignatura);
        }
        alumno.setListaAsignaturas(listaAsignaturas);
/*Verificar cual Auxiliar  u Orientadora se eligio para su tutoria*/
        for (int i = 0; i < lista.size(); i++) {
            Auxiliar aux = (Auxiliar) lista.get(i);
            if (comboBoxAuxiliar.getSelectionModel().getSelectedItem().equalsIgnoreCase((aux.getNombre() + " " + aux.getApellido()))) {
                alumno.setIdAuxiliar(aux.getIdAuxiliar());

            }
        }        
        for (int i = 0; i < lista2.size(); i++) {
            Administrador aux = (Administrador) lista2.get(i);
            if (comboBoxAuxiliar.getSelectionModel().getSelectedItem().equalsIgnoreCase((aux.getNombre() + " " + aux.getApellido()))) {
                alumno.setIdOrientadora(aux.getIdAdmin());

            }
        }
/**/
        //Si todo esta ok: REGISTRA
        if (ok == true) {
            textFieldDocumento.setUnFocusColor(Color.GREEN);

            alumnoBO.registrar(alumno);
            Image img = new Image("/org/kumon/presentation/img/ok.png");
            notificacion = Notifications.create();
            notificacion.title("Resultado de la Operacion");
            notificacion.text("Registrado con Exito");
            notificacion.graphic(new ImageView(img));
            notificacion.hideAfter(Duration.seconds(3));
            notificacion.position(Pos.CENTER);
            notificacion.darkStyle();
            primaryStage.close();
            notificacion.show();

        }
        //
    }

    private boolean cargarDatos() {
        boolean ok = true;
        alumno.setActivo(1);
        alumno.setNombre(textFieldNombre.getText());
        //Si dni es INT////////////////////////////////
        try {
            alumno.setDni(Integer.parseInt(textFieldDocumento.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            textFieldDocumento.setUnFocusColor(RED);
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Documento acepta solo valores numéricos");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            ok = false;
        }
        if (textFieldDocumento.getText().isEmpty()) {
            textFieldDocumento.setUnFocusColor(RED);
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Documento Vacío");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            ok = false;
        }
        /////////////////////////////////////////////
        alumno.setIdAlumno(textFieldDocumento.getText());
        alumno.setIdPersona(textFieldDocumento.getText());
        alumno.setInfo(textAreaInfoAdicional.getText());
        alumno.setNombreImg(textFieldNombreImg.getText());
        alumno.setApellido(textFieldApellido.getText());
        alumno.setDomicilio(textFieldDomicilio.getText());
        alumno.setTelefono(textFieldTelefono.getText());
        alumno.setEmail(textFieldMail.getText());
        alumno.setTipoUser(Contexto.tipoUser);
        ///////////////////////////////////////////////////////////////
        alumno.setSexo(comboBoxSexo.getValue());
        //FECHAS Verificacion de Fecha no null y calculo de Edad.
        java.sql.Date date = java.sql.Date.valueOf(datePickerFecha.getValue());
        alumno.setFechaNacimiento(date);
        if (alumno.getFechaNacimiento() == null || datePickerFecha.getValue()== null) {
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Fecha no puede estar Vacía");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            ok = false;

        } else {
            alumno.setEdad(personaBO.calcularEdad(datePickerFecha));

        }
        return ok;
    }

    @FXML
    private void btnCargarFamiliarAction(ActionEvent event) throws IOException {
        Contexto.abrirPlanillaABMfamiliar();
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) {
        primaryStage.close();
    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABMAlumno.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
