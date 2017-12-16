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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.kumon.business.AuxiliarBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Asistencia;
import org.kumon.model.Auxiliar;
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
    Notifications notificacion;
    private final AsistenciaBO asistenciaBO;
    private final PersonaBO personaBO;
    private final AuxiliarBO auxiliarBO;
    private final Asistencia asistencia;
    private Persona persona;
    private List lista;
    ///////////////////////////////////
    @FXML
    private JFXButton btnVer;

    public AsistenciaController() {
        asistenciaBO = Contexto.construirAsistenciaBO();
        personaBO = Contexto.construirPersonaBO();
        auxiliarBO = Contexto.construirAuxiliarBO();
        asistencia = new Asistencia();
        persona = new Persona();
        lista = new ArrayList();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            presenteEmpleadosVacaciones();
        } catch (Exception ex) {
            Logger.getLogger(AsistenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void presenteEmpleadosVacaciones() throws Exception {
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        lista = auxiliarBO.getPersonalVacaciones();
        for (int i = 0; i < lista.size(); i++) {
            Auxiliar aux = (Auxiliar) lista.get(i);
            asistencia.setIdPersona(aux.getIdAuxiliar());
            asistencia.setFecha(new java.sql.Date(date.getTime()));
            asistencia.setHoraEntrada(asistenciaBO.getHoraExacta(cal));
            asistenciaBO.registrar(asistencia);
        }
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

    /*Funcion encargada de guardar en la base de datos el presente de un alumno*/
    private void presente() throws Exception {

        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        //Seteo el objeto asistencia auxiliar.
        asistencia.setIdPersona(textFieldId.getText());
        try {
            persona = personaBO.buscarById(Integer.parseInt(asistencia.getIdPersona()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        asistencia.setFecha(new java.sql.Date(date.getTime()));
        asistencia.setHoraEntrada(asistenciaBO.getHoraExacta(cal));
        ////////////////
        //Registro en la Base de datos
        if (persona.getIdPersona() != null) {
            asistenciaBO.registrar(asistencia);
            ///////////////
            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Registrado Presente: " + persona.getNombre() + " " + persona.getApellido();
            notificar(img, mensaje);
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            String mensaje = "No se encontro la Persona";
            notificar(img, mensaje);
        }
    }

    private void notificar(Image img, String mensaje) {
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text(mensaje);
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
