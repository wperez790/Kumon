/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import org.kumon.business.PersonaBO;
import org.kumon.main.Contexto;
import org.kumon.model.Persona;
import org.kumon.persist.DaoPersonaImpl;

/**
 * FXML Controller class
 *
 * @author walt
 */
public class SeleccionPersonaController implements Initializable {

    @FXML
    private JFXTextField textFieldBuscar;
    @FXML
    private ImageView btnBuscar1;
    @FXML
    private JFXButton btnSeleccionar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTreeTableView<Person> tablePersonas;

     //AUX
    private final PersonaBO personaBO = new PersonaBO();
    private final DaoPersonaImpl personaDB = Contexto.construirDaoPersonaImpl();
    @FXML
    private JFXButton btnBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Seteo de la columna Nombre y Apellido*/
        JFXTreeTableColumn<Person,String> nombre = new JFXTreeTableColumn<>("Nombre y Apellido");
        nombre.setPrefWidth(300);
        nombre.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombre;
        });
        /**/
        
        /*Seteo de la columna Dni*/
        JFXTreeTableColumn<Person,String> dni = new JFXTreeTableColumn<>("Dni");
        dni.setPrefWidth(210);
        dni.setCellValueFactory((param) -> {
            return param.getValue().getValue().dni;
        });
        /**/
        /*Seteo de elementos en la Tabla*/
        ObservableList<Person> personas= FXCollections.observableArrayList();
        try {
            for(Map.Entry<Integer, String> entry: personaDB.obtenerTodos().entrySet()){
                personas.add(new Person(entry.getKey(), entry.getValue())); /*Carga en una ObservableList las Personas por dni,nombre*/
                }
        } catch (Exception ex) {
            Logger.getLogger(SeleccionPersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<Person> root = new RecursiveTreeItem<Person>(personas, RecursiveTreeObject::getChildren);
        tablePersonas.getColumns().setAll(dni,nombre);
        tablePersonas.setRoot(root);
        tablePersonas.setShowRoot(false);
        
        /*Filtro de busqueda*/
        textFieldBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tablePersonas.setPredicate(new Predicate<TreeItem<Person>>() {
                    @Override
                    public boolean test(TreeItem<Person> t) {
                        Boolean flag = t.getValue().dni.getValue().contains(newValue) ||  t.getValue().nombre.getValue().contains(newValue);
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
    /*Clase auxiliar Persona para setear datos en la tabla*/
    private static class Person extends RecursiveTreeObject<Person> {
        StringProperty dni;
        StringProperty nombre;
        public Person(Integer dni, String nombre) {
            this.dni = new SimpleStringProperty(""+dni+"");
            this.nombre = new SimpleStringProperty(nombre);
        }
    }
    /**/
    
    /*Selecciona la hoja de Datos para abrir*/
    @FXML   
    private void btnSeleccionar(ActionEvent event) throws IOException, Exception {
        Person p =  tablePersonas.getSelectionModel().getSelectedItem().getValue();
        String dni = p.dni.getValue();
        Parent pane;
        Persona p1 = personaDB.buscarById(Integer.parseInt(dni));
        Contexto.setPersona(p1);
        /*Selecciono si es para baja, modificacion o vista de datos*/
        if(Contexto.baja){
            pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Baja.fxml"));
            
        }else if(Contexto.modificar){
            pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/DatosConTextfield.fxml"));
        }
        else{
        pane = FXMLLoader.load(getClass().getResource("/org/kumon/presentation/Datos.fxml"));
        }
        Contexto.splitPane.getItems().set(0, pane);
    }
    
}
