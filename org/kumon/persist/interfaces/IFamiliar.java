/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.persist.interfaces;

import java.util.List;
import org.kumon.model.Familiar;

/**
 *
 * @author Walter
 */
public interface IFamiliar {
    
    public void registrar(Familiar familiar) throws Exception;
    public void modificar(Familiar familiar) throws Exception;
    public void eliminar(Familiar familiar) throws Exception;
    public List<Familiar> listar() throws Exception;
    
    
    
}
