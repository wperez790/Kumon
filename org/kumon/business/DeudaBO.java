/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Deuda;
import org.kumon.model.Pago;
import org.kumon.persist.DaoDeudaImpl;
import org.kumon.persist.DaoPagosImpl;

/**
 *
 * @author walt
 */
public class DeudaBO {
  private DaoDeudaImpl deudaDB = Contexto.construirDaoDeudaImpl();   
  private DaoPagosImpl pagosDB = Contexto.construirDaoPagosImpl();   
  
  
    public boolean registrarDeuda(Deuda deuda) throws Exception{
        return(deudaDB.registrarDeuda(deuda));
    }
    public void anularDeuda(Integer idDeuda) throws Exception{
        deudaDB.anularDeuda(idDeuda);
    }
    public List obtenerDeudasVencidas() throws Exception {
        return deudaDB.obtenerDeudasVencidas();
    }
    public List obtenerDeudasAVencer() throws Exception {
        return deudaDB.obtenerDeudasAVencer();
    }
    public List obtenerTodos() throws Exception {
        return deudaDB.obtenerTodos();
    }
    public void modificarMontoAdeudado(Pago pagos) throws Exception {
        deudaDB.modificarMontoAdeudado(pagos);
    }

    public void restaurarMontoAdeudado(Integer idPago) throws Exception {
        Pago pago = pagosDB.obtenerPagoById(idPago);
        deudaDB.restaurarMontoAdeudado(pago);
    }

    public List getIdDeudaByIdPersona(String idPersona) throws Exception {
        return deudaDB.getIdDeudaByIdPersona(idPersona);
    }

    public void anularDeudaByIdPersona(String idPersona) throws Exception {
        deudaDB.anularDeudaByIdPersona(idPersona);
    }
}
