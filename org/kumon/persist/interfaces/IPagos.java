/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Pago;

/**
 *
 * @author walt
 */
public interface IPagos {

    public void registrarPago(Pago pagos) throws Exception;

    public void anularPago(Integer idPago) throws Exception;
    
    public List obtenerTodos() throws Exception;
}
