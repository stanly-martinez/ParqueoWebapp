/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.TipoEspacio;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;

/**
 *
 * @author daniloues
 */
@Stateless
@LocalBean
public class TipoEspacioBean extends AbstractDataAccess<TipoEspacio> implements Serializable {
    
    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;
    
    
    @Override
    public String entityQuery() {
        return ("TipoEspacio");
    }
    
    public EntityManager getEntityManager(){
        return em;
}
    public TipoEspacioBean(){
        super(TipoEspacio.class);
    }
}
