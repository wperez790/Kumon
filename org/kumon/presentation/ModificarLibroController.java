/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.LibroBO;
import org.kumon.main.Contexto;
import org.kumon.model.Libro;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class ModificarLibroController implements Initializable {

    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldAutor;
    @FXML
    private JFXTextField textFieldEditorial;
    @FXML
    private JFXTextField textFieldStock;
    @FXML
    private JFXTextArea textAreaDescripcion;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCancelar;
    //AUX
    LibroBO libroBO = new LibroBO();
    Stage primaryStage = new Stage();
    Notifications notificacion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Libro l = Contexto.libro;
        textFieldAutor.setText(l.getAutor());
        textFieldNombre.setText(l.getNombre());
        textAreaDescripcion.setText(l.getDescripcion());
        textFieldEditorial.setText(l.getEditorial());
        textFieldStock.setText(l.getStock().toString());
    }

    @FXML
    private void btnModificarAction(ActionEvent event) throws Exception {
        Libro libro = new Libro();
        libro.setAutor(textFieldAutor.getText());
        libro.setDescripcion(textAreaDescripcion.getText());
        libro.setEditorial(textFieldEditorial.getText());
        libro.setStock(Integer.parseInt(textFieldStock.getText()));
        libro.setNombre(textFieldNombre.getText());
        libro.setIdLibro(Contexto.libro.getIdLibro());
        libroBO.modificarLibro(libro);
        Image img = new Image("/org/kumon/presentation/img/ok.png");
        notificacion = Notifications.create();
        notificacion.title("Resultado de la Operacion");
        notificacion.text("Registrado con Exito");
        notificacion.graphic(new ImageView(img));
        notificacion.hideAfter(Duration.seconds(3));
        notificacion.position(Pos.CENTER);
        notificacion.darkStyle();
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
        notificacion.show();
    }

    @FXML
    private void btnCancelarAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/PrestamoLibros.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

    public void init() {
       
     try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/ModificarLibro.fxml"));
            Contexto.splitPane.getItems().set(0, root);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
