package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Reserva;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.ReservaBean;
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
public class FrmReserva implements Serializable {

    @Inject
    ReservaBean rBean;

    List<Reserva> listaRegistros;

    Reserva registro = null;

    LazyDataModel<Reserva> modelo;

    public FrmReserva() {

    }

    @PostConstruct
    public void inicializar() {
        inicializarRegistros();
    }

    public void inicializarRegistros() {

        this.listaRegistros = rBean.findRange(0, 10000);

    }

    public void btnSeleccionarHandler(ActionEvent ae) {
        Integer id = (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if (id != null) {
            this.registro = rBean.findById(id);
        }
    }

    public void btnNuevoHandler(ActionEvent ae) {
        this.registro = new Reserva();
    }

    public void btnCancelarHandler(ActionEvent ae) {
        this.registro = null;
    }

    public void btnGuardarHandler(ActionEvent ae) {
        this.rBean.create(registro);
        this.listaRegistros = rBean.findRange(0, 1000000);
        this.registro = null;
    }

    public void btnModificarHandler(ActionEvent ae) {
        Reserva modify = this.rBean.modify(registro);
        if (modify != null) {
            this.listaRegistros = rBean.findRange(0, 10000000);
            this.registro = null;
        }
    }

    public void btnEliminarHandler(ActionEvent ae) {

    }

    public void btnMayor2Handler(ActionEvent ae) {
        this.listaRegistros = this.rBean.mayor2();
    }

    public List<Reserva> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<Reserva> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public Reserva getRegistro() {
        return registro;
    }

    public void setRegistro(Reserva registro) {
        this.registro = registro;
    }

}
