/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class DeseaEliminarController implements Initializable {
//AUXILIARES

    PersonaBO personaBO = new PersonaBO();
    private static Stage primaryStage = new Stage();
    private Notifications notificacion;
    private Notifications ok;
    @FXML
    private JFXButton cancelarButton;
    @FXML
    private JFXButton eliminarButton;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelDni;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText(Contexto.getPersona().getNombre() + " " + Contexto.getPersona().getApellido());
        labelDni.setText(Contexto.getPersona().getDni().toString());
    }

    public void init() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/DeseaEliminar.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void cancelarButtonAction(ActionEvent event) {
        primaryStage.close();
    }

    @FXML
    private void eliminarButtonAction(ActionEvent event) throws Exception {
        personaBO.eliminar(Contexto.getPersona().getDni());
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
        primaryStage.close();
        notificacion.show();
        
    }
}
