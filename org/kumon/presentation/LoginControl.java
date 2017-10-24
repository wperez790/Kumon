/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kumon.business.Bitshifter;
import org.kumon.business.EscribirLeerArchivo;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.persist.DaoPersonaImpl;

/**
 *
 * @author Walter
 */
public class LoginControl implements Initializable {

    @FXML
    private AnchorPane rootLogin;
    @FXML
    private GridPane login;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXCheckBox checkBoxGuardarUsuario;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnRecuperarUsuario;
    @FXML
    private JFXCheckBox checkBoxGuardarContraseña;

    @FXML
    private void btnLoginAction(ActionEvent e) throws Exception {
        this.ingresar();

    }
    //AUX
    private DaoPersonaImpl personaDB = Contexto.construirDaoPersonaImpl();
    private Notifications error;
    private PersonaBO personaBO;
    String userEncriptado = "";
    String userDesencriptado;
    String passEncriptado = "";
    String passDesencriptado;

    //
    public LoginControl() throws Exception {
        personaBO = Contexto.construirPersonaBO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setOnKeyTyped(e -> {
            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    ingresar();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
        pass.setOnKeyTyped(e -> {

            if ((int) e.getCharacter().charAt(0) == 13) {
                try {
                    ingresar();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

        });
        try {
            String u = EscribirLeerArchivo.leer(true); //true indica leer Usuario
            if (u!=null) {
                user.setText(u);
                pass.setText(EscribirLeerArchivo.leer(false));//Cualquier valor diferente de false retorna el user
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkBoxGuardarContraseña.setSelected(true);
        checkBoxGuardarUsuario.setSelected(true);

    }

    public void init() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Log in");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean ingresar() throws Exception {
        //Verifica Checkboxes para ver si hay que almacenar datos de loggeo

        if (checkBoxGuardarContraseña.isSelected()) {
            passEncriptado = Bitshifter.desencriptar(pass.getText());
        }
        if (checkBoxGuardarUsuario.isSelected()) {
            userEncriptado = Bitshifter.encriptar(user.getText());
            EscribirLeerArchivo.escribir(user.getText(),true);
            EscribirLeerArchivo.escribir(pass.getText(),false);
        }

        //Setea el nombre que saldra en el mensaje de bienvenida
        try {
            Contexto.setPersona(personaDB.obtenerPersonaByUser(user.getText()));
            Contexto.setUser(Contexto.persona);
        } catch (Exception ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        if (personaBO.comprobarUsuario(user.getText(), pass.getText())) {

            if (personaDB.obtenerPersonaByUser(user.getText()).getSexo().equalsIgnoreCase("M")) {

                cargarMensajeBienvenidaMasculino();

            } else {

                cargarMensajeBienvenidaFemenino();
            }
            return true;
        } else {
            error = Notifications.create();
            error.title("Inicio de Sesion");
            error.darkStyle();
            error.text("Error al Iniciar Sesion");
            error.hideAfter(Duration.seconds(3));
            error.position(Pos.BOTTOM_RIGHT);
            error.showError();
            return false;
        }

    }

    public void cargarMensajeBienvenidaMasculino() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MensajeBienvenidaM.fxml"));
            rootLogin.getChildren().addAll(pane);
            fadeTransition(pane);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void cargarMensajeBienvenidaFemenino() {

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MensajeBienvenidaF.fxml"));
            rootLogin.getChildren().addAll(pane);
            fadeTransition(pane);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void fadeTransition(AnchorPane pane) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        /*FadeTransition fadeOut = new FadeTransition(Duration.seconds(2),pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
         */
        fadeIn.play();
        fadeIn.setOnFinished(e -> {
            try {
                Stage s = (Stage) user.getScene().getWindow();
                s.close();
                Contexto.abrirContenedorPrincipal();
                Contexto.abrirMenu();
            } catch (Exception ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        /*fadeOut.play();*/
 /*fadeOut.setOnFinished(e->{
                
                try {
                Stage s = (Stage) user.getScene().getWindow();
                s.close();
                Contexto.abrirMenu();
                } catch (Exception ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                });*/
    }
}
