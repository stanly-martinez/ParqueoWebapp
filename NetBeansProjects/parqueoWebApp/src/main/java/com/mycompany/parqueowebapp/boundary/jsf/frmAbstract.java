/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.io.Serializable;

public abstract class frmAbstract<T> implements Serializable {
    
    public abstract AbstractDataAccess<T> getDataAccess();
    
    T registro = null;
    
    List<T> listaRegistros;
    
    @PostConstruct
    public void inicializar(){
        inicializarRegistros();
    }
    
    public void inicializarRegistros(){
        
        this.listaRegistros = this.getDataAccess().findRange(0, 1000000);    
    }
    public void btnSeleccionarHandler(ActionEvent ae){
        Integer id = (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if(id != null){
            this.registro = this.getDataAccess().findById(id);
        }
    }
    
    //public void btnNuevoHandler(ActionEvent ae){
    //    this.registro = new TipoReserva();
    //}
    
    public void btnGuardarHandler(ActionEvent ae){
    //    this.trBean.create(registro);
    }
    
    public void btnCancelarHandler(ActionEvent ae){
        this.registro = null;
    }
    // NO FUNCIONA AUN
    public void btnEliminarHandler(ActionEvent ae){
    //    this.trBean.delete(registro);
     //   this.listaRegistros = trBean.findRange(0, 100000);
        this.registro = null;
    }
    
    public void btnModificarHandler(ActionEvent ae){
     //   TipoReserva modify = this.trBean.modify(registro);
    //    if(modify != null){
            //registro exitoso, actualizar registro
   //         this.listaRegistros = trBean.findRange(0, 100000);
            this.registro = null;
        }
    //}
}
