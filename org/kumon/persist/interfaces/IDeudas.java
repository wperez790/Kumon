/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Deuda;

/**
 *
 * @author walt
 */
public interface IDeudas {
    
     public boolean registrarDeuda(Deuda deuda) throws Exception;
     public void anularDeuda(Integer idDeuda) throws Exception;
     public List obtenerDeudasVencidas() throws Exception;
     public List obtenerDeudasAVencer() throws Exception;
     public List obtenerTodos()throws Exception;
}
