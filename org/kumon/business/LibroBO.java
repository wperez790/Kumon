/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.List;
import java.util.Map;
import javafx.beans.property.StringProperty;
import org.kumon.main.Contexto;
import org.kumon.model.Libro;
import org.kumon.persist.DaoLibroImpl;

/**
 *
 * @author walt
 */
public class LibroBO {
    
    private DaoLibroImpl libroDB = Contexto.construirDaoLibroImpl();
    
    public List obtenerTodos() throws Exception {
        return libroDB.obtenerTodos();
    }
    
    public void modificarLibro(Libro libro) throws Exception {
        libroDB.modificarLibro(libro);
    }
    
    public void modificarStockLibro(int cantidad, int idLibro) throws Exception {
        if(cantidad>=0){
        libroDB.modificarStockLibro(cantidad, idLibro);
        }
        else throw new Exception("Cantidad menor a 0");
    }
    
    public void nuevoLibro(Libro libro) throws Exception {
        libroDB.nuevoLibro(libro);
    }

    public void borrarLibro(int idLibro) throws Exception {
        libroDB.borrarLibro(idLibro);
    }

    public Libro getLibroByID(Integer id) throws Exception {
       return libroDB.getLibroByID(id);
    }

  
}
