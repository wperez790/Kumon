/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import org.kumon.main.Contexto;
import org.kumon.persist.DaoSistemaImpl;

/**
 *
 * @author walt
 */
public class SistemaBO {
    DaoSistemaImpl sistemaDB = Contexto.construirDaoSistemaImpl();
    public double obtenerPrecioPorMateria() throws Exception {
        return sistemaDB.obtenerPrecioPorMateria();
    }
    public void modificarPrecioPorMateria(Double precioNuevo) throws Exception {
        sistemaDB.modificarPrecioPorMateria(precioNuevo);
    }
}
