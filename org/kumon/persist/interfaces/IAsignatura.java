/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Asignatura;

/**
 *
 * @author Walter
 */
public interface IAsignatura {
    
    public void registrar(Asignatura asignatura) throws Exception;
    public void modificar(Asignatura asignatura) throws Exception;
    public void eliminar(Asignatura asignatura) throws Exception;
    public List<Asignatura> listar() throws Exception;
    
}
