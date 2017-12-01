/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

/**
 *
 * @author walt
 */
public interface ISistema {
    
    public double obtenerPrecioPorMateria() throws Exception;
    public void modificarPrecioPorMateria(Double precioNuevo) throws Exception;
}
