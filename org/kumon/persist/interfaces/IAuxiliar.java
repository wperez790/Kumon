/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Auxiliar;

/**
 *
 * @author Walter
 */
public interface IAuxiliar {
    
    public void registrar(Auxiliar auxiliar) throws Exception;
    public void modificar(Auxiliar auxiliar) throws Exception;
    public void eliminar(Auxiliar auxiliar) throws Exception;
    public List<Auxiliar> listar() throws Exception;
    
    
      
    
}
