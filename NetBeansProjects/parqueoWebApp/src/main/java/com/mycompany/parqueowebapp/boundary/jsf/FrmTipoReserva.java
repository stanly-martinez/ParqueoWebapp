
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.TipoReservaBean;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

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
    
    @PostConstruct
    public void inicicializar(){
        inicializarRegistros();
    }
    
    public void inicializarRegistros(){
        
        this.listaRegistros = trBean.findRange(0, 10000000);    
    }

    public List<TipoReserva> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<TipoReserva> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }
    
    
}
