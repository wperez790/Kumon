/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.List;
import org.kumon.main.Contexto;
import org.kumon.model.Pago;
import org.kumon.persist.DaoPagosImpl;

/**
 *
 * @author walt
 */
public class PagosBO {
    
        private DeudaBO deudaBO = Contexto.construirDeudaBO();
        private DaoPagosImpl pagosDB = Contexto.construirDaoPagosImpl();
        
        public void registrarPago(Pago pagos) throws Exception{
            pagosDB.registrarPago(pagos);
            deudaBO.modificarMontoAdeudado(pagos);
        }
        
        public void anularPago(Integer idPago) throws Exception {
            deudaBO.restaurarMontoAdeudado(idPago);
            pagosDB.anularPago(idPago);
            
        }
        public List obtenerTodos() throws Exception{
           return pagosDB.obtenerTodos();
        }
    
}
