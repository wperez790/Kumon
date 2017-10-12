/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class DatosController implements Initializable {

    @FXML
    private ImageView imagenPersona;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApellido;
    @FXML
    private Label labelDocumento;
    @FXML
    private Label labelDomicilio;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelTelefono;
    @FXML
    private Label labelSexo;
    @FXML
    private Label labelNacimiento;
    @FXML
    private Label labelUsuario;
  

    //AUXILIARES
    PersonaBO personaBO = new PersonaBO();
     private static Stage primaryStage = new Stage();
    @FXML
    private Label labelInfoAdicional;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Label labelEstadoCuenta;
    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona persona = Contexto.getPersona();
        labelNombre.setText(persona.getNombre());
        labelApellido.setText(persona.getApellido());
        labelDocumento.setText(persona.getDni().toString());
        labelDomicilio.setText(persona.getDomicilio());
        labelEmail.setText(persona.getEmail());
        labelTelefono.setText(persona.getTelefono());
        labelSexo.setText(persona.getSexo());
        labelUsuario.setText(persona.getUser());
        Image img = new Image("/org/kumon/presentation/img/fotosPersonas/"+persona.getNombreImg());
        imagenPersona.setImage(img);
        labelInfoAdicional.setText(persona.getInfo());
        labelNacimiento.setText(persona.getFechaNacimiento().toString());
        String estado= personaBO.getTextCuentaActiva(persona.getActivo());
        labelEstadoCuenta.setText(estado);
        
    }    

    public void init() {
         try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Datos.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

   

    
}
