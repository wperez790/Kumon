/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import java.util.ArrayList;
import java.util.List;
import org.kumon.model.Auxiliar;

/**
 *
 * @author Walter
 */
public class AuxiliarBO {
    List<Auxiliar> auxiliaresList = new ArrayList<Auxiliar>();
    
    public Auxiliar getAuxiliarbyUser(String user){
        for(Auxiliar a : auxiliaresList)
        {
            if(a.getUser().equals(user))
            {
                return a;
            }
        }
        return null;
    }
    
}
