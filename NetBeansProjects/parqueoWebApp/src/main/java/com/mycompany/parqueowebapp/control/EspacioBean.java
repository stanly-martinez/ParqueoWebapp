/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.Espacio;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collections;
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

    public List<Espacio> activosLista() {
        TypedQuery q = em.createNamedQuery("Espacio.findByActivo", Espacio.class);
        q.setParameter("activo", true);
        return q.getResultList();
    }

    public List<Espacio> findByIdArea(final Integer idArea, int primero, int tamanio) {
        if (idArea != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("Espacio.findByIdArea");
                q.setParameter("idArea", idArea);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();
            }
        }
        return Collections.EMPTY_LIST;
    }

    public int countByIdArea(final Integer idArea) {
        if (idArea != null) {
            if (em != null) {
                Query q = em.createNamedQuery("Espacio.countByIdArea");
                q.setParameter("idArea", idArea);
                return ((Long) (q.getSingleResult())).intValue();
            }
        }
        return 0;
    }

    @Override
    public String entityQuery() {
        return ("Espacio");
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public EspacioBean() {
        super(Espacio.class);
    }

}
