/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kumon.business;

import org.kumon.main.Contexto;
import org.kumon.model.Familiar;
import org.kumon.model.Persona;
import org.kumon.persist.DaoFamiliarImpl;



/**
 *
 * @author Walter
 */
public class FamiliarBO{
    
    private DaoFamiliarImpl familiarDB = Contexto.construirDaoFamiliarImpl();
    private PersonaBO personaBO = Contexto.construirPersonaBO();

    public void registrar(Familiar familiar, Persona persona) throws Exception {
        familiarDB.registrar(familiar);
        personaBO.registrar(persona);
    }
    
}
