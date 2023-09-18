
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Espacio;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.EspacioBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author daniloues
 */
@Named
@ViewScoped
public class FrmEspacio implements Serializable {
    
    @Inject
    EspacioBean eBean;
    
    List<Espacio> listaRegistros;
    
    Espacio registro = null;
    
    LazyDataModel<Espacio> modelo;
    
    public FrmEspacio(){
        
    }
    
    @PostConstruct
    public void inicializar(){
        inicializarRegistros();
    }
    
    public void inicializarRegistros(){
        
        this.listaRegistros = eBean.findRange(0, 10000);    
        
        
    }
    
    public void btnSeleccionarHandler(ActionEvent ae){
        Integer id = (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if(id != null){
            this.registro = eBean.findById(id);
        }
    }
    
    public void btnNuevoHandler(ActionEvent ae){
        this.registro = new Espacio();
    }
    
    public void btnCancelarHandler(ActionEvent ae){
        this.registro = null;
    }
    
    public void btnGuardarHandler(ActionEvent ae){
        this.eBean.create(registro);
        this.listaRegistros = eBean.findRange(0, 1000000);
        this.registro = null;
    }
    
    public void btnModificarHandler(ActionEvent ae){
        Espacio modify = this.eBean.modify(registro);
        if(modify!=null){
            this.listaRegistros = eBean.findRange(0, 10000000);
            this.registro = null;
        }
    }
    
    public void btnActivosHandler(ActionEvent ae){
        this.listaRegistros = eBean.activosLista();
    }
    
    public void btnEliminarHandler(ActionEvent ae){
        this.eBean = null;
    }
    
    
    public List<Espacio> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<Espacio> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public Espacio getRegistro() {
        return registro;
    }

    public void setRegistro(Espacio registro) {
        this.registro = registro;
    }
    
    
}
