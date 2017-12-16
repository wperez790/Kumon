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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.kumon.business.AsignaturaBO;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Asignatura;
import org.kumon.model.Persona;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class DatosController implements Initializable {

    @FXML
    private ImageView imagenPersona;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApellido;
    @FXML
    private Label labelDocumento;
    @FXML
    private Label labelDomicilio;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelTelefono;
    @FXML
    private Label labelSexo;
    @FXML
    private Label labelNacimiento;
    private Label labelUsuario;
    @FXML
    private Label labelInfoAdicional;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Label labelEstadoCuenta;
    @FXML
    private JFXTextField tFNivelMatematica;
    @FXML
    private JFXTextField tFNivelLengua;
    @FXML
    private JFXTextField tFNivelIngles;
    @FXML
    private Label lbMatematica;
    @FXML
    private Label lbLengua;
    @FXML
    private Label lbIngles;
    @FXML
    private Label lbNivel;
    @FXML
    private Label labelEdad;
    
    
    //AUXILIARES
    PersonaBO personaBO;
    private static Stage primaryStage;
    AsignaturaBO asignaturaBO;
    List lista;
    //

    public DatosController() {
        personaBO = Contexto.construirPersonaBO();
        primaryStage = new Stage();
        asignaturaBO = Contexto.construirAsignarutraBO();
        lista = new ArrayList();
    }

    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona persona = Contexto.getPersona();
        try {
            lista = asignaturaBO.obtenerAsignaturasById(persona.getIdPersona());
        } catch (Exception ex) {
            Logger.getLogger(DatosConTextfieldController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!lista.isEmpty()) {
            String nM = null;
            String nL = null;
            String nI = null;
            Asignatura asignatura;
            for (int i = 0; i < lista.size(); i++) {
                asignatura = (Asignatura) lista.get(i);
                if (asignatura.getIdAsignatura() == 1) {
                    nM = asignatura.getNivel();
                }
                if (asignatura.getIdAsignatura() == 2) {
                    nL = asignatura.getNivel();
                }
                if (asignatura.getIdAsignatura() == 3) {
                    nI = asignatura.getNivel();
                }
            }
            tFNivelIngles.setText(nI);
            tFNivelLengua.setText(nL);
            tFNivelMatematica.setText(nM);
        } else {
            tFNivelIngles.setVisible(false);
            tFNivelLengua.setVisible(false);
            tFNivelMatematica.setVisible(false);
            lbIngles.setVisible(false);
            lbLengua.setVisible(false);
            lbMatematica.setVisible(false);
            lbNivel.setVisible(false);
        }
        labelNombre.setText(persona.getNombre());
        labelApellido.setText(persona.getApellido());
        labelDocumento.setText(persona.getDni().toString());
        labelDomicilio.setText(persona.getDomicilio());
        labelEmail.setText(persona.getEmail());
        labelTelefono.setText(persona.getTelefono());
        labelSexo.setText(persona.getSexo());
        labelEdad.setText(persona.getEdad()+"");
        try {
            Image img = new Image("/org/kumon/presentation/img/fotosPersonas/" + persona.getNombreImg());
            imagenPersona.setImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        labelInfoAdicional.setText(persona.getInfo());
        labelNacimiento.setText(persona.getFechaNacimiento().toString());
        String estado = personaBO.getTextCuentaActiva(persona.getActivo());
        labelEstadoCuenta.setText(estado);

    }

    public void init() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Datos.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kumon");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/SeleccionPersona.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }

}
