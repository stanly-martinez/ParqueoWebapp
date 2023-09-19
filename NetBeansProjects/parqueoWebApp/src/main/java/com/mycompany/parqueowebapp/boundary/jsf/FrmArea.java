
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Area;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.AreaBean;
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
public class FrmArea implements Serializable {
    
    @Inject
    AreaBean aBean;
    
    List<Area> listaRegistros;
    
    Area registro = null;
    
    
    LazyDataModel<Area> modelo;
    
    public FrmArea(){
        
    }
    
    @PostConstruct
    public void inicializar(){
        inicializarRegistros();
    }
    
    public void inicializarRegistros(){
        
        this.listaRegistros = aBean.findAll();    
        
        
    }
    
    public void btnSeleccionarHandler(ActionEvent ae){
        Integer id = (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if(id != null){
            this.registro = aBean.findById(id);
        }
    }
    
    public void btnNuevoHandler(ActionEvent ae){
        this.registro = new Area();
    }
    
    public void btnCancelarHandler(ActionEvent ae){
        this.registro = null;
    }
    
    public void btnGuardarHandler(ActionEvent ae){
        this.aBean.create(registro);
        this.listaRegistros = aBean.findRange(0, 1000000);
        this.registro = null;
    }
    
    public void btnModificarHandler(ActionEvent ae){
        Area modify = this.aBean.modify(registro);
        if(modify!=null){
            this.listaRegistros = aBean.findRange(0, 10000000);
            this.registro = null;
        }
    }
    
    public void btnMasActivosHandler(ActionEvent ae){
        int mayorValor = aBean.idAreaMasEspaciosActivos();
        this.registro = aBean.findById(mayorValor);
    }
    public void btnEliminarHandler(ActionEvent ae){
        this.aBean = null;
    }
    
    
    public List<Area> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<Area> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public Area getRegistro() {
        return registro;
    }

    public void setRegistro(Area registro) {
        this.registro = registro;
    }
    
    
}
