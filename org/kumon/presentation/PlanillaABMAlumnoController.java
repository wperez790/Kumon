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
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Alumno;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;

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

    //AUXILIARES
    private static Stage primaryStage = new Stage();
    private Notifications notificacion;
    private Notifications error2;
    Persona persona = new Persona();
    Alumno alumno = new Alumno();
    DaoPersonaImpl personaDB = new DaoPersonaImpl();
    PersonaBO personaBO = new PersonaBO();
    //

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxSexo.getItems().addAll("Masculino", "Femenino");
    }

    @FXML
    private void btnGuardarAction(ActionEvent event) throws Exception {

        boolean ok = false;

        persona.setActivo(1);
        persona.setNombre(textFieldNombre.getText());
        //Si dni es INT////////////////////////////////
        try {
            persona.setDni(Integer.parseInt(textFieldDocumento.getText()));
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

        }
        /////////////////////////////////////////////
        persona.setInfo(textAreaInfoAdicional.getText());
        persona.setNombreImg(textFieldNombreImg.getText());
        persona.setApellido(textFieldApellido.getText());
        persona.setIdPersona(textFieldDocumento.getText());
        persona.setDomicilio(textFieldDomicilio.getText());
        persona.setTelefono(textFieldTelefono.getText());
        persona.setEmail(textFieldMail.getText());

        persona.setTipoUser(Contexto.tipoUser);

        ///////////////////////////////////////////////////////////////
        persona.setSexo(comboBoxSexo.getValue());

        //FECHAS Verificacion de Fecha no null y calculo de Edad.
        java.sql.Date date = java.sql.Date.valueOf(datePickerFecha.getValue());
        persona.setFechaNacimiento(date);
        if (persona.getFechaNacimiento() == null) {
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Fecha no puede estar Vacía");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();

        } else {
            persona.setEdad(personaBO.calcularEdad(datePickerFecha));
            ok = true;
        }
        //Si todo esta ok: REGISTRA
        if (ok == true) {
            textFieldDocumento.setUnFocusColor(Color.GREEN);
            personaDB.registrar(persona);
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
