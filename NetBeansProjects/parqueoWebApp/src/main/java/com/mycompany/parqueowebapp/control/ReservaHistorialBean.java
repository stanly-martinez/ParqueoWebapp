/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.ReservaHistorial;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
/**
 *
 * @author daniloues
 */
@Stateless
@Local
public class ReservaHistorialBean extends AbstractDataAccess implements Serializable{
    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;
    
    @Override
    public EntityManager getEntityManager(){
        return em;
}
    public ReservaHistorialBean(){
        super(ReservaHistorial.class);
    }
    
}
