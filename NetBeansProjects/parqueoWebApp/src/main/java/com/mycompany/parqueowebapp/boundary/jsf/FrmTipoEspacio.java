/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.TipoEspacio;
import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author daniloues
 */
@Named
@ViewScoped
public class FrmTipoEspacio implements Serializable {
    @Inject
    TipoEspacioBean teBean;
    
    List<TipoEspacio> listaRegistros;
    
    public FrmTipoEspacio(){
        
    }
    
    @PostConstruct
    public void inicializarRegistros(){
        
        this.listaRegistros=teBean.findRange(0,100000000);
    }
    
}
