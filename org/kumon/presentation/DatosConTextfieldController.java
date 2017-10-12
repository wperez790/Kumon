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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class DatosConTextfieldController implements Initializable {

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
    //AUXILIARES
    Persona persona1 = new Persona();
    private static Stage primaryStage = new Stage();
    private Notifications notificacion;
    private Notifications error2;
    DaoPersonaImpl personaDB = new DaoPersonaImpl();
    PersonaBO personaBO = new PersonaBO();
    @FXML
    private JFXTextArea textAreaInfoAdicional;
    @FXML
    private JFXCheckBox checkBoxCuentaActiva;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona persona = Contexto.getPersona();
        textFieldNombre.setText(persona.getNombre());
        textFieldApellido.setText(persona.getApellido());
        textFieldDocumento.setText(persona.getDni().toString());
        textFieldDomicilio.setText(persona.getDomicilio());
        textFieldMail.setText(persona.getEmail());
        textFieldTelefono.setText(persona.getTelefono());
        comboBoxSexo.getItems().addAll("Masculino", "Femenino");
        comboBoxSexo.getSelectionModel().selectFirst();
        datePickerFecha.setValue(persona.getFechaNacimiento().toLocalDate());
        textFieldNombreImg.setText(persona.getNombreImg());
        Image img = new Image("/org/kumon/presentation/img/fotosPersonas/"+persona.getNombreImg());
        imagenPersona.setImage(img);
        textAreaInfoAdicional.setText(persona.getInfo());
        boolean estado = personaBO.getBooleanEstadoCuenta(persona.getActivo());
        checkBoxCuentaActiva.setSelected(estado);
    }    

    @FXML
    private void btnGuardarAction(ActionEvent event) throws Exception {
        
         boolean ok = false;
        if(checkBoxCuentaActiva.isSelected())
            persona1.setActivo(1);
        else
            persona1.setActivo(0);
        persona1.setNombre(textFieldNombre.getText());
        //Si dni es INT////////////////////////////////
        try{
        persona1.setDni(Integer.parseInt(textFieldDocumento.getText()));
        }
        catch(Exception e){
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
        persona1.setInfo(textAreaInfoAdicional.getText());
        persona1.setNombreImg(textFieldNombreImg.getText());
        persona1.setApellido(textFieldApellido.getText());
        persona1.setIdPersona(textFieldDocumento.getText());
        persona1.setDomicilio(textFieldDomicilio.getText());
        persona1.setTelefono(textFieldTelefono.getText());
        persona1.setEmail(textFieldMail.getText());
        if(Contexto.tipoUser == 2)
        persona1.setTipoUser(2);
        if(Contexto.tipoUser == 1)
        persona1.setTipoUser(1);
        ///////////////////////////////////////////////////////////////
        persona1.setSexo(comboBoxSexo.getValue());
        
        //FECHAS Verificacion de Fecha no null y calculo de Edad.
        java.sql.Date date = java.sql.Date.valueOf(datePickerFecha.getValue());
        persona1.setFechaNacimiento(date);
        if(persona1.getFechaNacimiento()==null){
            error2 = Notifications.create();
            error2.title("Error de Parametros");
            error2.darkStyle();
            error2.text("Fecha no puede estar Vacía");
            error2.hideAfter(Duration.seconds(3));
            error2.position(Pos.BOTTOM_RIGHT);
            error2.showError();
            
        }
        else
            persona1.setEdad(personaBO.calcularEdad(datePickerFecha));
        
         //Si todo esta ok: REGISTRA
         ok= true;//Dejo este OK para agregar funcionalidades mas adelante.
        if(ok==true){
            textFieldDocumento.setUnFocusColor(Color.GREEN);
            personaDB.modificar(persona1);
            Image img = new Image("/org/kumon/presentation/img/ok.png");
            notificacion = Notifications.create();
            notificacion.title("Resultado de la Operacion");
            notificacion.text("Registrado con Exito");
            notificacion.graphic(new ImageView(img));
            notificacion.hideAfter(Duration.seconds(3));
            notificacion.position(Pos.CENTER);
            notificacion.darkStyle();
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
            notificacion.show();
        
        
        }
        //
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }
    public void init(){
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/DatosConTextField.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
}
