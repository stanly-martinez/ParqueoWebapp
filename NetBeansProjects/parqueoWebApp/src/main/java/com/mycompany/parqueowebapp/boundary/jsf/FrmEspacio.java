package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Area;
import com.mycompany.parqueowebapp.app.entity.Espacio;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.EspacioBean;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author daniloues
 */
import java.util.Collections;
import org.primefaces.model.LazyDataModel;

@Named
@Dependent
public class FrmEspacio extends frmAbstract<Espacio> implements Serializable {

    @Inject
    EspacioBean eBean;

    @Inject
    FacesContext fc;
    
    Integer idArea;

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    @Override
    public List<Espacio> cargarDatos(int primero, int tamanio) {
        if (this.idArea != null) {
            return this.eBean.findByIdArea(this.idArea, primero, tamanio);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int contar() {
        if (this.idArea != null) {
            return this.eBean.countByIdArea(this.idArea);
        }
        return 0;
    }

    @Override
    public AbstractDataAccess<Espacio> getDataAccess() {
        return eBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public String getIdPorObjeto(Espacio object) {
        if (object != null && object.getIdEspacio() != null) {
            return object.getIdEspacio().toString();
        }
        return null;
    }

    @Override
    public Espacio getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdEspacio().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Espacio();
        if (this.idArea != null) {
            this.registro.setIdArea(new Area(idArea));
        }
        this.registro.setActivo(true);
    }

    @Override
    public LazyDataModel<Espacio> getModelo() {
        return super.getModelo();
    }
}
