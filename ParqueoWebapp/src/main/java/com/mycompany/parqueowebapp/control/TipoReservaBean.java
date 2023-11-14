/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import jakarta.ejb.EJB;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author daniloues
 */

@Stateless
@LocalBean

public class TipoReservaBean extends AbstractDataAccess<TipoReserva> implements Serializable{
    
    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;
    public TipoReserva hallarTipoReserva(Object id){
        Query q=em.createNamedQuery("TipoReserva.findByIdTipoReserva");
        q.setParameter("idTipoReserva",id);
        return (TipoReserva) q.getSingleResult();
        
    }
    public void borrarRegistro(Object id){
    
        TypedQuery<TipoReserva> q = em.createNamedQuery("TipoReserva.deteleById", tipoDato);
        q.setParameter("idTipoReserva", id);
        q.executeUpdate();
    }
    
    
    @Override
    public String entityQuery(){
        return ("TipoReserva");
    }
    public EntityManager getEntityManager(){
        return em;
}
    public TipoReservaBean(){
        super(TipoReserva.class);
    }
    
    
    
    
}
