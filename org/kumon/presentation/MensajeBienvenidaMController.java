/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class MensajeBienvenidaMController implements Initializable {

    @FXML
    private Label labelBienvenido;
    @FXML
    private Label labelNombre;
    //Aux
    private List lista;
    private final PersonaBO personaBO;
    Notifications notificacion;

    //
    public MensajeBienvenidaMController() {
        lista = new ArrayList();
        personaBO = Contexto.construirPersonaBO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nombre = Contexto.getPersona().getNombre();
        String[] nombreCortado;
        nombreCortado = nombre.split("\\s+");
        labelNombre.setText(nombreCortado[0]);

        try {
            lista = personaBO.buscarByDate();
        } catch (Exception ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!lista.isEmpty()){
        String text = "";
        Image img = new Image("/org/kumon/presentation/img/cumpleaños.png");
        notificacion = Notifications.create();
        notificacion.title("Aviso de Cumpleaños");
        for (int i = 0; i < lista.size(); i++) {
            Persona p = (Persona) lista.get(i);
            text += "Hoy cumple " + p.getEdad() + " años\n" + p.getNombre() + " " + p.getApellido() /*+ "\nDni: " + p.getDni()*/ + "\n\n";
        }
        notificacion.text(text);
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(10));
        notificacion.position(Pos.BOTTOM_RIGHT);
        notificacion.darkStyle();
        notificacion.show();
        }
    }

    public void init() {

        try {

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MensajeBienvenidaMController.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
