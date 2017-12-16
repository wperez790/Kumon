/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.Calendar;
import java.util.List;
import org.kumon.model.Tarea;

/**
 *
 * @author walt
 */
public interface ITarea {
    public boolean registrar(Tarea tarea) throws Exception;
    public boolean modificar(Tarea tarea) throws Exception;
    public boolean eliminar(Integer idTarea) throws Exception;
    public List<Tarea> buscarByDate(Calendar fecha) throws Exception;
}
