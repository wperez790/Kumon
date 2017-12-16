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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
public class VerificarTareasController implements Initializable {

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
    private Tarea tarea;
    private final TareaBO tareaBO;
    Notifications notificacion;
    //

    public VerificarTareasController() {
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        for (int i = 0; i < Contexto.lista.size(); i++) {

            switch (i) {
                case 0:
                    tfTarea1.setText(Contexto.lista.get(i).getInfo());
                    LocalTime time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora1.getEditor().setText(time.format(dtf));
                    break;
                case 1:
                    tfTarea2.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora2.getEditor().setText(time.format(dtf));
                    break;
                case 2:
                    tfTarea3.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora3.getEditor().setText(time.format(dtf));
                    break;
                case 3:
                    tfTarea4.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora4.getEditor().setText(time.format(dtf));
                    break;
                case 4:
                    tfTarea5.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora5.getEditor().setText(time.format(dtf));
                    break;
                case 5:
                    tfTarea6.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora6.getEditor().setText(time.format(dtf));
                    break;
                case 6:
                    tfTarea7.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora7.getEditor().setText(time.format(dtf));
                    break;
                case 7:
                    tfTarea8.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora8.getEditor().setText(time.format(dtf));
                    break;
                case 8:
                    tfTarea9.setText(Contexto.lista.get(i).getInfo());
                    time = LocalTime.parse(Contexto.lista.get(i).getHora());
                    tpHora9.getEditor().setText(time.format(dtf));
                    break;

            }
        }

    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Planificador.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    @FXML
    private void btnModificarAction(ActionEvent event) throws Exception {
        //Si el TextField no esta vacio setea las modificaciones que corresponden.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        int size = Contexto.lista.size();
        if (!tfTarea1.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setHora(tpHora1.getEditor().getText());
            tarea.setInfo(tfTarea1.getText());
            tarea.setIdTarea(Contexto.lista.get(0).getIdTarea());
            verificarModificacion();
        }
        if (!tfTarea2.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea2.getText());
            if (size < 2) {
                tarea.setHora(tpHora2.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora2.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(1).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea3.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea3.getText());
            if (size < 3) {
                tarea.setHora(tpHora3.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora3.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(2).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea4.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea4.getText());
            if (size < 4) {
                tarea.setHora(tpHora4.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora4.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(3).getIdTarea());
                verificarModificacion();

            }
        }
        if (!tfTarea5.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea5.getText());
            if (size < 5) {
                tarea.setHora(tpHora5.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora5.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(4).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea6.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea6.getText());
            if (size < 6) {
                tarea.setHora(tpHora6.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora6.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(5).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea7.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea7.getText());
            if (size < 7) {
                tarea.setHora(tpHora7.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora7.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(6).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea8.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea8.getText());
            if (size < 8) {
                tarea.setHora(tpHora8.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora8.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(7).getIdTarea());
                verificarModificacion();
            }
        }
        if (!tfTarea9.getText().isEmpty()) {
            java.sql.Date sqlDate = new java.sql.Date(Contexto.fecha.getTimeInMillis());
            tarea.setDia(sqlDate);
            tarea.setInfo(tfTarea9.getText());
            if (size < 9) {
                tarea.setHora(tpHora9.getValue().format(dtf));
                tareaBO.registrar(tarea);
            } else {
                tarea.setHora(tpHora9.getEditor().getText());
                tarea.setIdTarea(Contexto.lista.get(8).getIdTarea());
                verificarModificacion();
            }
        }
    }

    private void verificarModificacion() throws Exception {
        boolean ok = tareaBO.modificar(tarea);
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
