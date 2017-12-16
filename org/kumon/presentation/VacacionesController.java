/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import org.kumon.business.AuxiliarBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class VacacionesController implements Initializable {

    @FXML
    private JFXButton btnSetVacaciones;
    @FXML
    private Label labelidPersona;
    @FXML
    private JFXDatePicker datePickerRegreso;
    //AUXILIARES
    Notifications notificacion;
    private final PersonaBO personaBO;
    private final AuxiliarBO auxiliarBO;
    private static Stage primaryStage;
    //

    public VacacionesController() {
        personaBO = Contexto.construirPersonaBO();
        auxiliarBO = Contexto.construirAuxiliarBO();
        primaryStage = new Stage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona persona = Contexto.getPersona();
        labelidPersona.setText(persona.getIdPersona());
    }

    @FXML
    private void btnSetVacacionesAction(ActionEvent event) throws Exception {

        java.sql.Date date = java.sql.Date.valueOf(datePickerRegreso.getValue());
        if (auxiliarBO.setVacaciones(date, Contexto.getPersona().getIdPersona())) {
            Image img = new Image("/org/kumon/presentation/img/ok.png");
            notificar(img);
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            notificar(img);
        }
    }

    private void notificar(Image img) {
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

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Vacaciones.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon Vacaciones");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
