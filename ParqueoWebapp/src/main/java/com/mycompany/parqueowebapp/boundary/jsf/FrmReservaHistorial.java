package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Area;
import com.mycompany.parqueowebapp.app.entity.ReservaHistorial;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.ReservaHistorialBean;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author daniloues
 */
@Named
@ViewScoped
public class FrmReservaHistorial extends frmAbstract<ReservaHistorial> implements Serializable {
    
    
    /*
    EN ESTE FRM SE DEBE REGRESAR EL REGISTRO ACTUAL QUE SE CREA CUANDO SE INICIALIZA, USAR NUEVAMENTE DE EJEMPLO EL FrmArea,
    MODIFICAR ACORDE
    */
    
    
    
    
    @Inject
    ReservaHistorialBean rhBean;

    @Inject
    FacesContext fc;

    TreeNode raiz;
    TreeNode nodoSeleccionado;

    /*
    ESTE METODO SE ENCARGA DE SELECCIONAR EL NODO DEL ARBOL SEGUN EL ELEMENTO SELECCIONADO
    */
    public void seleccionarNodoListener(NodeSelectEvent nse) {
        this.registro = (ReservaHistorial) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        
        /*
        ESTE ES ES UN COPIA PEGA DE FrmArea USAR DE BASE PARA GUIARSE COMO CREAR ESTE METODO, DEJO ABAJO COMENTADO
        COMO ES EN FrmArea PARA HACERLO CON RESPECTO A RESERVAHISTORIAL Y TIPORESERVASECUENCIA
        */
//        if (this.registro != null && this.registro.getIdArea() != null && this.frmEspacio != null) {
//            this.frmEspacio.setIdArea(this.registro.getIdArea());
//        }
    }
    
    @Override
    public AbstractDataAccess<ReservaHistorial> getDataAccess() {
        return this.rhBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(ReservaHistorial object) {
        if (object != null && object.getIdReservaHistorial() != null) {
            return object.getIdReservaHistorial().toString();
        }
        return null;
    }

    @Override
    public ReservaHistorial getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdReservaHistorial().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }


    @Override
    public void instanciarRegistro() {
        this.registro = new ReservaHistorial();
    }

    @Override
    public LazyDataModel<ReservaHistorial> getModelo() {
        return super.getModelo();
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode raiz) {
        this.raiz = raiz;
    }
    
    public TreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }
}
