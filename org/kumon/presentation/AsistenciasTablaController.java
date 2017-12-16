/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
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
public class AsistenciasTablaController implements Initializable {

    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXTreeTableView<Assistance> tableAsistencias;

    //AUX
    AsistenciaBO asistenciaBO;
    PersonaBO personaBO;
    Persona persona;

    public AsistenciasTablaController() {
        asistenciaBO = Contexto.construirAsistenciaBO();
        personaBO = Contexto.construirPersonaBO();
        persona = new Persona();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Seteo de la columna idPersona*/
        JFXTreeTableColumn<AsistenciasTablaController.Assistance, String> idPersona = new JFXTreeTableColumn<>("IdPago");
        idPersona.setPrefWidth(320);
        idPersona.setCellValueFactory((param) -> {
            return param.getValue().getValue().idPersona;
        });
        /**/
 /*Seteo de la columna Nombre*/
        JFXTreeTableColumn<AsistenciasTablaController.Assistance, String> nombrePersona = new JFXTreeTableColumn<>("Nombre");
        nombrePersona.setPrefWidth(320);
        nombrePersona.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombrePersona;
        });
        /**/
 /*Seteo de la columna Fecha*/
        JFXTreeTableColumn<AsistenciasTablaController.Assistance, String> fecha = new JFXTreeTableColumn<>("Fecha");
        fecha.setPrefWidth(320);
        fecha.setCellValueFactory((param) -> {
            return param.getValue().getValue().fecha;
        });
        /**/
 /*Seteo de la columna horaEntrada*/
        JFXTreeTableColumn<AsistenciasTablaController.Assistance, String> horaEntrada = new JFXTreeTableColumn<>("Hora entrada");
        horaEntrada.setPrefWidth(320);
        horaEntrada.setCellValueFactory((param) -> {
            return param.getValue().getValue().horaEntrada;
        });
        /**/

 /*Seteo los elementos en la tabla*/
        ObservableList<AsistenciasTablaController.Assistance> asistenciasOList = FXCollections.observableArrayList();
        List list;
        try {
            Asistencia asist;
            list = asistenciaBO.obtenerTodas();
            for (int i = 0; i < list.size(); i++) {
                asist = (Asistencia) list.get(i);
                persona = personaBO.buscarById(Integer.parseInt(asist.getIdPersona())); //Se puede optimizar poniendo esta consulta dentro de DaoAsistenciaImpl
                /*Carga en una ObservableList los Libros*/
                asistenciasOList.add(new AsistenciasTablaController.Assistance(asist.getIdPersona(), persona.getNombre() + " " + persona.getApellido(), asist.getFecha(), asist.getHoraEntrada()));

            }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<AsistenciasTablaController.Assistance> root = new RecursiveTreeItem<AsistenciasTablaController.Assistance>(asistenciasOList, RecursiveTreeObject::getChildren);
        tableAsistencias.getColumns().setAll(idPersona, nombrePersona, fecha, horaEntrada);
        tableAsistencias.setRoot(root);
        tableAsistencias.setShowRoot(false);

        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableAsistencias.setPredicate(new Predicate<TreeItem<AsistenciasTablaController.Assistance>>() {
                    @Override
                    public boolean test(TreeItem<AsistenciasTablaController.Assistance> t) {
                        Boolean flag = t.getValue().nombrePersona.getValue().contains(newValue) || t.getValue().fecha.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        /**/
    }

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/MenuPrincipal.fxml"));
        Contexto.splitPane.getItems().set(0, pane);
    }
/*Clase auxiliar para el manejo de la tabla*/
    private static class Assistance extends RecursiveTreeObject<Assistance> {

        StringProperty idPersona;
        StringProperty nombrePersona;
        StringProperty fecha;
        StringProperty horaEntrada;

        public Assistance(String idPersona, String nombrePersona, Date fecha, String horaEntrada) {
            this.idPersona = new SimpleStringProperty(idPersona);
            this.nombrePersona = new SimpleStringProperty(nombrePersona);
            this.fecha = new SimpleStringProperty(fecha.toString());
            this.horaEntrada = new SimpleStringProperty(horaEntrada);
        }
    }

}
