package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Area;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.AreaBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author daniloues
 */
@Named
@ViewScoped
public class FrmArea extends frmAbstract<Area> implements Serializable {

    @Inject
    FrmEspacio frmEspacio;

    @Inject
    AreaBean aBean;

    @Inject
    FacesContext fc;
    
    TreeNode raiz;
    TreeNode nodoSeleccionado;

    @PostConstruct
    
    @Override
    public void inicializar() {
        super.inicializar();
        this.raiz = new DefaultTreeNode("Areas", null);
        List<Area> lista = aBean.findByIdPadre(null, 0, 1000000);
        if (lista != null && !lista.isEmpty()) {
            for (Area next : lista) {
                if (next.getIdAreaPadre() == null) {
                    this.generarArbol(raiz, next);
                }
            }

        }
    }

    public void generarArbol(TreeNode padre, Area actual) {
        DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);
        List<Area> hijos = this.aBean.findByIdPadre(actual.getIdArea(), 0, 10000000);
        for (Area hijo : hijos) {
            generarArbol(nuevoPadre, hijo);
        }
    }

    @Override
    public AbstractDataAccess<Area> getDataAccess() {
        return aBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(Area object) {
        if (object != null && object.getIdArea() != null) {
            return object.getIdArea().toString();
        }
        return null;
    }

    @Override
    public Area getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream().filter(r -> r.getIdArea().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        Area padre = this.registro;
        this.registro = new Area();
        this.registro.setIdAreaPadre(padre);
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

    public void seleccionarNodoListener(NodeSelectEvent nse) {
        this.registro = (Area) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        if (this.registro != null && this.registro.getIdArea() != null && this.frmEspacio != null) {
            this.frmEspacio.setIdArea(this.registro.getIdArea());
        }
    }

    public FrmEspacio getFrmEspacio() {
        return frmEspacio;
    }

}
