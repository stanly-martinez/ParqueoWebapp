/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;

/**
 *
 * @author daniloues
 */
// LEER SOBRE EXPRESSION LANGUAGE GLASSFISH
public abstract class AbstractDataAccess<T> {

    final Class tipoDato;

    public AbstractDataAccess(Class tipoDato) {
        this.tipoDato = tipoDato;
    }

    public abstract EntityManager getEntityManager();

    /**
     * \
     * Almacena un registro en la base de datos
     *
     * @param registro Entidad a guardar
     * @throws IllegalStateException Si ocurre un error en el repositorio
     * @throws IllegalArgumentException Si el parametro es nulo
     */
    public void create(T registro) throws IllegalStateException, IllegalArgumentException {

        if (registro != null) {
            try {
                EntityManager em = getEntityManager();
                if (em != null) {
                    em.persist(registro);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public List<T> findRange(int first, int pageSize) { // se planea paginar al pedir el primer registro y luego pedir una cantidad de registros
        EntityManager em = null;
        try {
            em = getEntityManager();

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (first >= 0 && pageSize > 0) {
            if (em != null) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery cq = cb.createQuery(tipoDato);
                Root<T> raiz = cq.from(tipoDato);
                cq.select(raiz);
                TypedQuery q = em.createQuery(cq);
                q.setFirstResult(first);
                q.setMaxResults(pageSize);

                return q.getResultList();

            }
            throw new IllegalStateException();

        }

        return (Collections.EMPTY_LIST);
    }

    public T modify(T registro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (registro != null) {
            if (em != null) {
                try {
                    return em.merge(registro);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else {
                throw new IllegalStateException();
            }
        }
        return null;
    }

    public void delete(T registro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (registro != null) {
            if (em != null) {
                try {
                    em.remove(registro);
                    return;
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();

        } else {
            throw new IllegalArgumentException();
        }
    }

    public int count() { // se planea paginar al pedir el primer registro y luego pedir una cantidad de registros
        EntityManager em = null;
        try {
            em = getEntityManager();

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(tipoDato);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(raiz);
            TypedQuery q = em.createQuery(cq);

            return q.getMaxResults();

        }
        throw new IllegalStateException();
    }

    public T findById(Object id) { // se planea paginar al pedir el primer registro y luego pedir una cantidad de registros
        EntityManager em = null;
        if (id != null) {
            try {
                em = getEntityManager();

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            if (em != null) {
                try {
                    return (T)em.find(tipoDato, id);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }
}
