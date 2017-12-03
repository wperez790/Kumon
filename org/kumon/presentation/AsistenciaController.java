/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.AsistenciaBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Asistencia;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class AsistenciaController implements Initializable {

    @FXML
    private JFXTextField textFieldId;
    @FXML
    private JFXButton btnPresente;

    /**
     * Initializes the controller class.
     */
    //AUXILIARES
    Asistencia asistencia;
    Notifications notificacion;
    AsistenciaBO asistenciaBO;
    PersonaBO personaBO;
    Persona persona;
    ///////////////////////////////////
    @FXML
    private JFXButton btnVer;

    public AsistenciaController() {
        asistenciaBO = Contexto.construirAsistenciaBO();
        personaBO = Contexto.construirPersonaBO();
        asistencia = new Asistencia();
        persona = new Persona();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        textFieldId.setOnKeyTyped(e -> {
            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    presente();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void presenteBtnAction(ActionEvent event) throws Exception {
        presente();
    }

    public void init() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Asistencia.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon Asistencia");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void presente() throws Exception {
        /*Funcion encargada de guardar en la base de datos el presente de un alumno*/
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        //Inicializo el objeto asistencia auxiliar.
        asistencia.setIdPersona(textFieldId.getText());
        persona = personaBO.buscarById(Integer.parseInt(asistencia.getIdPersona()));
        asistencia.setFecha(new java.sql.Date(date.getTime()));
        asistencia.setHoraEntrada(asistenciaBO.getHoraExacta(cal));
        ////////////////
        //Registro en la Base de datos
        asistenciaBO.registrar(asistencia);
        ///////////////
        Image img = new Image("/org/kumon/presentation/img/ok.png");
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text("Registrado Presente: "+persona.getNombre()+" "+persona.getApellido());
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        notificacion.show();
    }

    @FXML
    private void btnVerAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/AsistenciasTabla.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

}
