/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.TareaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Tarea;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class RegistrarTareasController implements Initializable {

    @FXML
    private JFXTextField tfTarea1;
    @FXML
    private JFXTextField tfTarea2;
    @FXML
    private JFXTextField tfTarea3;
    @FXML
    private JFXTextField tfTarea4;
    @FXML
    private JFXTextField tfTarea5;
    @FXML
    private JFXTextField tfTarea6;
    @FXML
    private JFXTextField tfTarea7;
    @FXML
    private JFXTextField tfTarea8;
    @FXML
    private JFXTextField tfTarea9;
    @FXML
    private JFXTimePicker tpHora2;
    @FXML
    private JFXTimePicker tpHora1;
    @FXML
    private JFXTimePicker tpHora3;
    @FXML
    private JFXTimePicker tpHora4;
    @FXML
    private JFXTimePicker tpHora5;
    @FXML
    private JFXTimePicker tpHora6;
    @FXML
    private JFXTimePicker tpHora7;
    @FXML
    private JFXTimePicker tpHora8;
    @FXML
    private JFXTimePicker tpHora9;
    @FXML
    private JFXButton btnBack;
    @FXML
    private ImageView imgDia;

    //AUX
    private TareaBO tareaBO;
    private Tarea tarea;
    Notifications notificacion;
    //

    public RegistrarTareasController() {
        tarea = new Tarea();
        tareaBO = Contexto.construirTareaBO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/org/kumon/presentation/img/dias/" + Contexto.day + ".png");
        imgDia.setImage(img);

    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Planificador.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnRegistrar(ActionEvent event) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        if (!tfTarea1.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora1.getValue().format(dtf));
            tarea.setInfo(tfTarea1.getText());
            verificarRegistro();
        }
        if (!tfTarea2.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora2.getValue().format(dtf));
            tarea.setInfo(tfTarea2.getText());
            verificarRegistro();
        }
        if (!tfTarea3.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora3.getValue().format(dtf));
            tarea.setInfo(tfTarea3.getText());
            verificarRegistro();
        }
        if (!tfTarea4.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora4.getValue().format(dtf));
            tarea.setInfo(tfTarea4.getText());
            verificarRegistro();
        }
        if (!tfTarea5.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora5.getValue().format(dtf));
            tarea.setInfo(tfTarea5.getText());
            verificarRegistro();
        }
        if (!tfTarea6.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora6.getValue().format(dtf));
            tarea.setInfo(tfTarea7.getText());
            verificarRegistro();
        }
        if (!tfTarea7.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora7.getValue().format(dtf));
            tarea.setInfo(tfTarea7.getText());
            verificarRegistro();
        }
        if (!tfTarea8.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora8.getValue().format(dtf));
            tarea.setInfo(tfTarea8.getText());
            verificarRegistro();
        }
        if (!tfTarea9.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora9.getValue().format(dtf));
            tarea.setInfo(tfTarea9.getText());
            verificarRegistro();
        }
    }

    private void verificarRegistro() throws Exception {
       boolean ok = tareaBO.registrar(tarea);
        if (ok) {
            Image img = new Image("/org/kumon/presentation/img/ok.png");
            String mensaje = "Registrado con Exito";
            notificar(mensaje, img);
        } else {
            Image img = new Image("/org/kumon/presentation/img/error.png");
            String mensaje = "Error en el Registro";
            notificar(mensaje, img);
        }
    }

    private void notificar(String mensaje, Image img) {
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text(mensaje);
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        notificacion.show();
    }

}
