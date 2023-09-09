
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.TipoReservaBean;
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
public class FrmTipoReserva implements Serializable {
    
    @Inject
    TipoReservaBean trBean;
    
    List<TipoReserva> listaRegistros;
    
    TipoReserva registro = null;
    
    LazyDataModel<TipoReserva> modelo;
    
    public FrmTipoReserva(){
        
    }
    
    @PostConstruct
    public void inicializar(){
        inicializarRegistros();
    }
    
    public void inicializarRegistros(){
        
        this.listaRegistros = trBean.findRange(0, 10000);    
        
        
    }
    
    public void btnSeleccionarHnadler(ActionEvent ae){
        Integer id = (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if(id != null){
            this.registro = (TipoReserva) trBean.findById(id);
        }
    }
    
    public void btnNuevoHandler(){
        this.registro = new TipoReserva();
    }
    
    public void btnGuardarHandler(){
        this.trBean.create(registro);
    }
    public List<TipoReserva> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<TipoReserva> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }
    
    
}
