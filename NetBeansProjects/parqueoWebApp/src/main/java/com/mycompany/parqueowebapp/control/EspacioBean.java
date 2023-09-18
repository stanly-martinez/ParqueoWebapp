/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.Espacio;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author daniloues
 */
@Stateless
@Local
public class EspacioBean extends AbstractDataAccess<Espacio> implements Serializable {
    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;
    
    
    public List<Espacio> activosLista(){
        TypedQuery q = em.createNamedQuery("Espacio.findByActivo", Espacio.class);
        q.setParameter("activo", true);
        return q.getResultList();
    }
    
    
    @Override
    public String entityQuery(){
        return ("Espacio");
    }
    public EntityManager getEntityManager(){
        return em;
}
    public EspacioBean(){
        super(Espacio.class);
    }
    
}
