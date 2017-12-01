/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
import org.kumon.business.FamiliarBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Familiar;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class PlanillaABMFamiliarController implements Initializable {

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
    private JFXTextArea textAreaInfoAdicional;
    @FXML
    private JFXTextField textFieldRelacion;

    //auxiliares
    Persona persona = new Persona();
    Familiar familiar = new Familiar();
    private Notifications notificacion;
    private Notifications error2;
    private static Stage primaryStage = new Stage();
    PersonaBO personaBO = Contexto.construirPersonaBO();
    FamiliarBO familiarBO = Contexto.construirFamiliarBO();
    //
    @FXML
    private JFXTextField textFieldDocumentoAlumno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxSexo.getItems().addAll("Masculino", "Femenino");
    }

    @FXML
    private void btnGuardarAction(ActionEvent event) throws Exception {

        //CODIGO PARA REGISTRAR FAMILIAR
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
            ok=false;
        }
           if(textFieldDocumento.getText().isEmpty() || textFieldDocumentoAlumno.getText().isEmpty()){
            textFieldDocumento.setUnFocusColor(RED);   
            textFieldDocumentoAlumno.setUnFocusColor(RED);   
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Documento Vacío");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            ok=false;
        }
        /////////////////////////////////////////////
        familiar.setIdAlumno(textFieldDocumentoAlumno.getText());
        familiar.setRelacion(textFieldRelacion.getText());
        familiar.setIdFamiliar(textFieldDocumento.getText());
        persona.setApellido(textFieldApellido.getText());
        persona.setIdPersona(textFieldDocumento.getText());
        persona.setDomicilio(textFieldDomicilio.getText());
        persona.setTelefono(textFieldTelefono.getText());
        persona.setTipoUser(3);
        persona.setInfo(textAreaInfoAdicional.getText());
        persona.setSexo((String) comboBoxSexo.getValue());
        persona.setEmail(textFieldMail.getText());
        persona.setNombreImg(textFieldNombreImg.getText());
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
            ok=false;

        } else {
            persona.setEdad(personaBO.calcularEdad(datePickerFecha));
            
        }
        ////////////////////////////////////////////////////////////////////

        //Si todo esta ok: REGISTRA
        if (ok == true) {
            
            textFieldDocumento.setUnFocusColor(Color.GREEN);
            familiarBO.registrar(familiar, persona);
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
        //

    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PlanillaABMFamiliar.fxml"));
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
