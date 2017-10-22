/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class BajaController implements Initializable {

    @FXML
    private AnchorPane rootSeleccion1;
    @FXML
    private JFXButton btnInactivo;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelDni;
    
    //AUXILIARES
    PersonaBO personaBO = new PersonaBO();
    private Notifications notificacion;
    @FXML
    private Label labelEstado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona persona = Contexto.getPersona();
        labelNombre.setText(persona.getNombre()+" "+persona.getApellido());
        labelDni.setText(persona.getDni().toString());
        labelEstado.setText(personaBO.getTextCuentaActiva(persona.getActivo()));
    }    

    @FXML
    private void btnInactivoAction(ActionEvent event) throws Exception {
        personaBO.setInactivoById(Contexto.getPersona().getDni());
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

    @FXML
    private void btnEliminarAction(ActionEvent event) throws Exception {
        Contexto.abrirDeseaEliminar();
        
    }


    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionABM1.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }
    
}
