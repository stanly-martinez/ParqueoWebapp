package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Reserva;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.ReservaBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.stream.Collectors;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author daniloues
 */
@Named
@ViewScoped
public class FrmReserva extends frmAbstract<Reserva> implements Serializable {

    @Inject
    ReservaBean rBean;
    
    @Inject
    FacesContext fc;
    
    // SE NECESITARAN LOS ESPACIOS DISPONIBLES PERO SE BUSCARAN EN BASE AL AREA SELECCIONADA
    @Inject
    FrmArea frmArea;
    
    // SE NECESITARA AGREGAR LAS RESERVAS EXITOSAS AL HISTORIAL
    //@Inject
    //FrmReservaHistorial frmRH;
    
    // SE NECESITARA INFORMAR SOBRE LOS TIPOS DE RESERVA
    @Inject
    FrmTipoReserva frmTR;
    
    //OBTENDREMOS EL AREA QUE ESTA SIENDO SELECCIONADA
    
    // SE INICIARA ESTE FORMULARIO CON LAS LISTAS DE AREA, SUBAREAS Y ESPACIOS
    int areaSeleccionada = 0;
    
    
    
    @Override
    public AbstractDataAccess<Reserva> getDataAccess() {
        return this.rBean;
        
    }


    
    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(Reserva object) {
        if(object!=null && object.getIdTipoReserva()!=null){
            return object.getIdTipoReserva().toString();
        }
        return null;
        
    }

    @Override
    public Reserva getObjetoPorId(String id) {
        if(id!=null && this.modelo!= null && this.modelo.getWrappedData()!=null){
            return this.modelo.getWrappedData().stream().filter(r->r.getIdTipoReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Reserva();
        
    }
    
    @Override
    public LazyDataModel<Reserva> getModelo() {
        return super.getModelo();
    }

    public FrmArea getFrmArea() {
        return frmArea;
    }

    public FrmTipoReserva getFrmTR() {
        return frmTR;
    }

    public int getAreaSeleccionada() {
        return areaSeleccionada;
    }

    public void setAreaSeleccionada(int areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }

    
    
    
    
    
    
}
