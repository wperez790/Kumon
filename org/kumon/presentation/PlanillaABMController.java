/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import org.kumon.business.AdministradorBO;
import org.kumon.business.AuxiliarBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class PlanillaABMController implements Initializable {

    @FXML
    private AnchorPane planilla;
    @FXML
    private ImageView imagenPersona;
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
    private JFXTextField textFieldUsuario;
    @FXML
    private JFXDatePicker datePickerFecha;
    @FXML
    private JFXComboBox<String> comboBoxSexo;
    @FXML
    private JFXPasswordField passwordField1;
    @FXML
    private JFXPasswordField passwordField2;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField textFieldNombreImg;
    @FXML
    private JFXTextField textFieldMail;

    //auxiliares
    Persona persona;
    private Notifications notificacion;
    private Notifications error2;
    private static Stage primaryStage;
    private PersonaBO personaBO;
    private AuxiliarBO auxiliarBO;
    private AdministradorBO adminsBO;

    public PlanillaABMController() {
        persona = new Persona();
        primaryStage = new Stage();
        personaBO = Contexto.construirPersonaBO();
        auxiliarBO = Contexto.construirAuxiliarBO();
    }

    //

    /*   public void btnCargarImagenAction(ActionEvent e) throws Exception{
    persona.setNombreImg(textFieldNombreImg.getText());
    Contexto.setPersona(persona);
    imagenPersona= new ImageView(new Image(getClass().getResourceAsStream("/img/fotosPersonas/"+Contexto.getPersona().getNombreImg()),200, 150,true, true)
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBoxSexo.getItems().addAll("Masculino", "Femenino");

    }

    @FXML
    private void btnGuardarAction(ActionEvent event) throws Exception {

        boolean ok = true;

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
            ok = false;

        }
        /////////////////////////////////////////////
        persona.setApellido(textFieldApellido.getText());
        persona.setIdPersona(textFieldDocumento.getText());
        persona.setDomicilio(textFieldDomicilio.getText());
        persona.setTelefono(textFieldTelefono.getText());
        persona.setEmail(textFieldMail.getText());
        persona.setNombreImg(textFieldNombreImg.getText());
        persona.setTipoUser(Contexto.tipoUser);

        //Si repitio bien el PASSWORD
        if (passwordField1.getText().equals(passwordField2.getText())) {
            persona.setPass(passwordField1.getText());
        } else {
            notificacion = Notifications.create();
            notificacion.title("Error de Parametros");
            notificacion.darkStyle();
            notificacion.text("Contraseña No Coincide");
            notificacion.hideAfter(Duration.seconds(3));
            notificacion.position(Pos.BOTTOM_RIGHT);
            notificacion.showError();
            passwordField1.setUnFocusColor(RED);
            passwordField2.setUnFocusColor(RED);
            ok = false;
        }
        ///////////////////////////////////////////////////////////////
        if (!comboBoxSexo.getValue().isEmpty()) {
            persona.setSexo(comboBoxSexo.getValue());
        } else {
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Debe Seleccionar Sexo");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            ok = false;

        }

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
            ok = false;

        } else {
            persona.setEdad(personaBO.calcularEdad(datePickerFecha));
        }
        //
        //Comprobar que el usuario no exista
        if (comprobarUsuario()) {
            persona.setUser(textFieldUsuario.getText());
        } else {
            textFieldUsuario.setUnFocusColor(RED);
            notificacion = Notifications.create();
            notificacion.title("Error de Parametros");
            notificacion.darkStyle();
            notificacion.text("El Usuario ya existe");
            notificacion.hideAfter(Duration.seconds(3));
            notificacion.position(Pos.BOTTOM_RIGHT);
            notificacion.showError();
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
        /////////////////////////////////////////////////////////////////////

        //Si todo esta ok: REGISTRA
        if (ok == true) {
            passwordField1.setUnFocusColor(Color.GREEN);
            passwordField2.setUnFocusColor(Color.GREEN);
            textFieldDocumento.setUnFocusColor(Color.GREEN);
            personaBO.registrar(persona);
            if (Contexto.tipoUser == 2) {
                auxiliarBO.agregarAuxiliar(persona.getIdPersona());
            }
            if (Contexto.tipoUser == 1) {
                adminsBO.agregarAdmin(persona.getIdPersona());
            }
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


    public boolean comprobarUsuario() throws Exception {
        return  personaBO.comprobarUser(textFieldUsuario.getText()); 

    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABM.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) {

        primaryStage.close();
    }

}
