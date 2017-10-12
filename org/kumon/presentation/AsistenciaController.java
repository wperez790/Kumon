/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kumon.model.Asistencia;
import org.kumon.persist.DaoAsistenciaImpl;

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
    Asistencia asistencia = new Asistencia();
    DaoAsistenciaImpl asistenciaDB = new DaoAsistenciaImpl();
    ///////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       textFieldId.setOnKeyTyped(e -> {
            if ((int) e.getCharacter().charAt(0)== 13) {
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
        //Inicializo el objeto asistencia auxiliar.
        asistencia.setIdPersona(textFieldId.getText());
        asistencia.setFecha((java.sql.Date) date);
        asistencia.setHoraEntrada((java.sql.Date) date);
        ////////////////
        //Registro en la Base de datos
        asistenciaDB.registrar(asistencia);
        ///////////////
    }
    
}
