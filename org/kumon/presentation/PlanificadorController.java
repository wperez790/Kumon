/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import org.kumon.business.TareaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Tarea;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class PlanificadorController implements Initializable {

    @FXML
    private ImageView imgHoy;
    @FXML
    private ImageView imgHoyMas1;
    @FXML
    private ImageView imgHoyMas2;
    @FXML
    private ImageView imgHoyMas3;
    @FXML
    private ImageView imgHoyMas4;
    @FXML
    private ImageView imgHoyMas5;
    @FXML
    private ImageView imgHoyMas6;
    @FXML
    private ImageView imgHoyMas7;
    @FXML
    private Label label0;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    //AUXILIAR
    private TareaBO tareaBO;
    private List<Tarea> lista;
    private List<Tarea> listaNotificacion;
    Notifications notificacion;
    //
    @FXML
    private JFXButton btnBack;

    public PlanificadorController() {
        tareaBO = Contexto.construirTareaBO();
        lista = new ArrayList();
        listaNotificacion = new ArrayList();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializo las imagenes de los dias de la semana
        Integer[] day = tareaBO.getDaysOfMonth();

        try {
            Image img = new Image("/org/kumon/presentation/img/dias/" + day[0] + ".png");
            imgHoy.setImage(img);
            Image img1 = new Image("/org/kumon/presentation/img/dias/" + day[1] + ".png");
            imgHoyMas1.setImage(img1);
            Image img2 = new Image("/org/kumon/presentation/img/dias/" + day[2] + ".png");
            imgHoyMas2.setImage(img2);
            Image img3 = new Image("/org/kumon/presentation/img/dias/" + day[3] + ".png");
            imgHoyMas3.setImage(img3);
            Image img4 = new Image("/org/kumon/presentation/img/dias/" + day[4] + ".png");
            imgHoyMas4.setImage(img4);
            Image img5 = new Image("/org/kumon/presentation/img/dias/" + day[5] + ".png");
            imgHoyMas5.setImage(img5);
            Image img6 = new Image("/org/kumon/presentation/img/dias/" + day[6] + ".png");
            imgHoyMas6.setImage(img6);
            Image img7 = new Image("/org/kumon/presentation/img/dias/" + day[7] + ".png");
            imgHoyMas7.setImage(img7);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] daysNames;
        daysNames = tareaBO.getDaysOfWeekSpanish();
        label0.setText(daysNames[0]);
        label1.setText(daysNames[1]);
        label2.setText(daysNames[2]);
        label3.setText(daysNames[3]);
        label4.setText(daysNames[4]);
        label5.setText(daysNames[5]);
        label6.setText(daysNames[6]);
        label7.setText(daysNames[7]);
        
        Calendar cal = Calendar.getInstance();
        try{
            listaNotificacion = tareaBO.buscarByDate(cal);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(!listaNotificacion.isEmpty()){
        String text = "";
        Image img = new Image("/org/kumon/presentation/img/task.png");
        notificacion = Notifications.create();
        notificacion.title("Aviso de Tareas");
        text = "Hoy asigno las siguientes tareas: \n\n";
        for (int i = 0; i < listaNotificacion.size(); i++) {
            text += listaNotificacion.get(i).getInfo()+" a las: "+listaNotificacion.get(i).getHora()+"\n\n";
        }
        notificacion.text(text);
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(10));
        notificacion.position(Pos.BOTTOM_RIGHT);
        notificacion.darkStyle();
        notificacion.show();
        }
    }

    @FXML
    private void hoyAction(ActionEvent event) throws Exception {
        Calendar fecha = Calendar.getInstance();
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }

    }

    @FXML
    private void hoyMas1Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 1);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas2Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 2);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas3Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 3);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas4Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 4);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas5Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 5);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas6Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 6);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void hoyMas7Action(ActionEvent event) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, 7);
        Contexto.day = fecha.get(Calendar.DAY_OF_MONTH);
        Contexto.fecha= fecha;
        lista = tareaBO.buscarByDate(fecha);
        if (lista.isEmpty()) {
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/RegistrarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        } else {
            Contexto.lista = lista;
            Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/VerificarTareas.fxml"));
            Contexto.splitPane.getItems().set(0, pane);
        }
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

}
