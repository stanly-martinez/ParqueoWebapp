package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Reserva;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import com.mycompany.parqueowebapp.control.EspacioBean;
import com.mycompany.parqueowebapp.control.EspacioCaracteristicaBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.ReservaBean;
import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.model.LazyDataModel;


@Named
@ViewScoped
public class FrmReserva extends frmAbstract<Reserva> implements Serializable {
    
    /*
    
    ************************************************************************************
    
    BUENAS A TODOS, AQUI ES DONDE SE MANEJARAN LA MAYORIA DE ACCIONES Y TODO DEBE REGRESAR INFORMACION A ESTE FORMULARIO.
    ALGUNOS DETALLES SOBRE EL ESTADO ACTUAL DE ESTE PROYECTO:
    
    - SE HAN DEJADO SEÑALADAS ALGUNAS INYECCIONES SIN EMBARGO PUEDEN SER CAMBIADAS SEGUN NECESIDAD, DE PREFERENCIA SERIA INYECTAR BEANS CUANDO SE NECESITE UNICAMENTE LOS VALORES DE BD (ENTIDAD-BEAN) Y EL FORMULARIO CUANDO
    SE DESEE OBTENER EL MODELO DE LA TABLA (frm-ENTIDAD). Ejemplo:
    
    * Si necesito los nombres de los espacios se inyectaría eBean para utilizar el método eBean.findNombresByIdArea(), método que ha sido definido en EspacioBean y que utiliza un JPQL Query para traer esa información. 
    (Tal y como se ha hecho en el metodo asignarArea presente)
    
    * Si necesito mostrar una tabla con las entidades completas de espacios se inyectaría el frmEspacio para utilizar el getModelo(), método que ha sido definido en frmEspacio y que pueden ver su ejecución en Area.xhtml
    que es el ejemplo con el que el ingeniero nos enseño este uso.
    
    HAY MUCHAS PARTES QUE NO HAN SIDO COMENTADAS ASI QUE PREGUNTENME SI ALGO SE VE RARO xd
    
    */
    
    
    // SE INYECTA EL BEAN DE RESERVA DONDE SE GUARDARAN Y MANEJARA EL CRUD DE LA TABLA 'RESERVA'
    
    @Inject
    ReservaBean rBean;
    
    // SE INYECTA EL FACESCONTEXT A UTILIZAR
    
    @Inject
    FacesContext fc;

    // SE INYECTA PARA OBTENER LAS AREAS (EN EL FORMATO DEL INGENIERO SERIA BUENA IDEA IR A TRAER EL ARBOL DIRECTAMENTE DEL FORMULARIO YA HECHO)
    @Inject
    FrmArea frmArea;
    
    // SE INYECTA PARA OBTENER LOS ESPACIOS RELACIONADOS A CADA AREA (EN EL FORMATO DEL INGENIERO CREO QUE SERIA EL FORMULARIO EN LUGAR DEL BEAN)
    
    @Inject
    EspacioBean eBean;

    // SE NECESITARA AGREGAR LAS RESERVAS EXITOSAS AL HISTORIAL (COMENTADO PORQUE DICHO FRM NO EXISTE AUN)
    //@Inject
    //FrmReservaHistorial frmRH;
    
    // SE NECESITARA INFORMAR SOBRE LOS TIPOS DE RESERVA
    @Inject
    FrmTipoReserva frmTR;
    
    
    // SE DEBE LLEVAR LA SECUENCIA DE LAS RESERVAS, NO ESTOY SEGURO SI SERIA TRAER LA TABLA O EL DATO, SE DEJARA A DISCRECION
//    @Inject
//    Frm o Bean de TipoReservaSecuencia
    
    // SE NECESITARA AGREGAR LOS POSIBLES TIPOS DE ESPACIOS
    @Inject
    TipoEspacioBean teBean;
    
    // SE NECESITARA IR A TRAER LAS CARACTERISTICAS DE LOS ESPACIOS
    @Inject
    EspacioCaracteristicaBean terBean;

    //OBTENDREMOS EL AREA QUE ESTA SIENDO SELECCIONADA
    int idAreaSeleccionada;

    List<String> espacios;

    //
    public void asignarArea() {
        if (idAreaSeleccionada >= 2) {
            try {
                espacios = eBean.findNombresByIdArea(idAreaSeleccionada);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<String> getEspacios() {
        return espacios;
    }

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
        if (object != null && object.getIdTipoReserva() != null) {
            return object.getIdTipoReserva().toString();
        }
        return null;

    }

    @Override
    public Reserva getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
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
        return idAreaSeleccionada;
    }

    public void setAreaSeleccionada(int idAreaSeleccionada) {
        this.idAreaSeleccionada = idAreaSeleccionada;
    }

}
