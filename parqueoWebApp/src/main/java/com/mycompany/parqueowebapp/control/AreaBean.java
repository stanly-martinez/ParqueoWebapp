/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.Area;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author daniloues
 */

@Stateless
@Local
public class AreaBean extends AbstractDataAccess<Area> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    public List<Area> findByIdPadre(final Integer idAreaPadre, int primero, int tamanio) {
        if (primero >= 0 && tamanio > 0) {
            if (em != null) {
                if (idAreaPadre != null) {
                    Query q = em.createNamedQuery("Area.findByIdPadre");
                    q.setParameter("idAreaPadre", idAreaPadre);
                    q.setFirstResult(primero);
                    q.setMaxResults(tamanio);
                    return q.getResultList();
                } else{
                    Query q = em.createNamedQuery("Area.findRaices");
                    q.setFirstResult(primero);
                    q.setMaxResults(tamanio);
                    return q.getResultList();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }
    
    public List<String> findNombresPadres(){
        if(em !=null){
            Query q = em.createNamedQuery("Area.findNombresPadres");
            return q.getResultList();
            
        }
        return Collections.EMPTY_LIST;
    }
    

    @Override
    public String entityQuery() {
        return ("Area");
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public AreaBean() {
        super(Area.class);
    }

}
