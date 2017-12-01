/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Libro;

/**
 *
 * @author walt
 */
public interface ILibro {
    
    public void nuevoLibro(Libro libro) throws Exception;
    public void modificarStockLibro(int cantidad, int idLibro) throws Exception;
    public void modificarLibro(Libro libro) throws Exception;
    public void borrarLibro(int idLibro) throws Exception;
    public List obtenerTodos() throws Exception;
}
