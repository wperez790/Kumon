/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import org.kumon.model.Asistencia;

/**
 *
 * @author walt
 */
public interface IAsistencia {
    public void registrar(Asistencia asistencia) throws Exception;
}
