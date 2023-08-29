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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniloues
 */
@Stateless
@LocalBean
public class TipoEspacioBean implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;
    
    /**\
     * Almacena un registro en la base de datos
     * @param registro Entidad a guardar
     * @throws IllegalStateException Si ocurre un error en el repositorio
     * @throws IllegalArgumentException Si el parametro es nulo
     */
    
    
    
    public void create(TipoEspacio registro) throws IllegalStateException, IllegalArgumentException {
        if (registro != null) {

            try {
                em.persist(registro);
                return;
            } catch (Exception ex) {

                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

}
