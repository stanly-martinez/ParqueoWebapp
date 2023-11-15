package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.Area;
import com.mycompany.parqueowebapp.app.entity.Espacio;
import com.mycompany.parqueowebapp.app.entity.Reserva;
import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import com.mycompany.parqueowebapp.control.AreaBean;
import com.mycompany.parqueowebapp.control.EspacioBean;
import com.mycompany.parqueowebapp.control.EspacioCaracteristicaBean;
import com.mycompany.parqueowebapp.control.comparadorFechas;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.parqueowebapp.control.ReservaBean;
import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import com.mycompany.parqueowebapp.control.TipoReservaBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

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
    @Inject
    TipoReservaBean trBean;
    @Inject
    AreaBean aBean;
    @Inject
    FrmEspacio frmEspacio;
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

    //SE NECESITARA AGREGAR LAS RESERVAS EXITOSAS AL HISTORIAL
    @Inject
    FrmReservaHistorial frmRH;
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
    
    @Inject
    comparadorFechas valFechas;

    // VARIABLES
    int idAreaSeleccionada;
    Date TemporalDate;
    List<Espacio> espaciosDisponibles;
    List<TipoReserva> listaTipoReserva;
    String pathEspacio;
    TreeNode raiz;
    TreeNode nodoSeleccionado;
    // NO CORREGIR ERROR DE NOMBRE, ASI ESTA EN EL XHTML DEL INGENIERO XD
    List<String> caractaristicasDisponibles;
    List<Integer> caracteristicasSeleccionadas;
    List<String> caracteristicasDisponiblesAsItems;


    /*
    ESTE METODO DEBE ENCARGARSE DE SETTEAR caracteristicasSeleccionadas CUANDO UNA CHECKBOX SEA
    SELECCIONADA Y QUIZA caractaristicasDisponibles ES DE ANALIZAR EL CODIGO
     */
    public void refinarBusquedaNodo() {

    }

    /*
    ESTE METODO SE ENCARGA DE SELECCIONAR EL NODO DEL ARBOL SEGUN EL ELEMENTO SELECCIONADO
     */
    public void seleccionarNodoListener(NodeSelectEvent nse) {
        this.registro = (Reserva) nse.getTreeNode().getData();
        this.seleccionarRegistro();
        /*
        ESTE ES ES UN COPIA PEGA DE FrmArea USAR DE BASE PARA GUIARSE COMO CREAR ESTE METODO, DEJO ABAJO COMENTADO
        COMO ES EN FrmArea PARA HACERLO CON RESPECTO A RESERVAHISTORIAL Y TIPORESERVASECUENCIA
         */
//        if (this.registro != null && this.registro.getIdArea() != null && this.frmEspacio != null) {
//            this.frmEspacio.setIdArea(this.registro.getIdArea());
//        }
    }

    /* 
    VER MIN 11.47 DE LA GRABACION DEL INGENIERO DEL PARCIAL, ESTE METODO PARECE QUE REGRESA UN STRING QUE CONCATENA 
    EL NOMBRE DEL ESPACIO Y EL PATH DEL AREA HASTA DICHO ESPACIO. TENTATIVAMENTE REGRESA UN STRING
     */
    public String generarPathArea(long idEspacio) {

        Espacio espacio = eBean.findById(idEspacio);

        if (espacio != null) {
            Area areaPadre = espacio.getIdArea().getIdAreaPadre();
            Area area = espacio.getIdArea();
            if (areaPadre != null) {
                return "Espacio: " + espacio.getNombre() + " Areas:" + areaPadre.getNombre() + "/" + area.getNombre();
            } else if (area != null) {
                return "Espacio: " + espacio.getNombre() + " Areas: " + area.getNombre();
            }
        }
        return "";
    }

    @Override
    public List<Reserva> cargarDatos(int primero, int tamanio) {
        listaTipoReserva = trBean.findAll();
        return this.rBean.findRange(primero, tamanio);
    }

    /*
    DEVUELVE UNA LISTA DE TIPO RESERVA, ES DE ANALIZAR SI SE DEVUELVEN TODAS O CUAL SERIA EL CONTEXTO
     */
 /*
    ESTE METODO CAMBIA LA COLUMNA DESDE CUANDO ES LLAMADO, ESTE DATO ES DE TIPO 'DATE', EN EL PROYECTO
    PARQUEOWEBAPP.CONTROL HAY UN ARCHIVO LLAMADO 'COMPARADORFECHAS' CON UN METODO QUE VALIDA QUE LAS FECHAS RECIBIDAS SEAN
    POSIBLES BAJO LOS ESTANDARES DE JAVA.UTIL.DATE
     */
    public void cambiarFechaDesde() {
        /*
        CODIGO QUE ASIGNA LA FECHA DESDE
         */
    }

    /*
    ESTE METODO SE ASEGURA QUE LA FECHA 'DESDE' NO SEA MAYOR A LA 'HASTA'. DE PREFERENCIA HACER LA COMPARACION EN UN METODO
    EN LA CLASE 'COMPARADORFECHAS' PARA QUE TODOS LO UTILICEMOS PARA COMPARAR FECHAS
     */
    private void ocultarPanelEspacio() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent panelEspacio = context.getViewRoot().findComponent("form:pnlEspacio"); // Reemplaza "form" con el ID de tu formulario
        if (panelEspacio != null) {
            panelEspacio.setRendered(false);
        }
    }

    public void validate(FacesContext context, UIComponent component, Object value) {
        Date fechaHasta = (Date) value;
        Date fechaDesde = registro.getDesde();
        
        if (fechaHasta != null && fechaDesde != null && fechaHasta.before(fechaDesde)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "La fecha no validas La fecha No pued ser menor que la inicial", null);
            
            registro.setHasta(null);
            throw new ValidatorException(message);
        }
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
        if (object != null && object.getIdReserva() != null) {
            return object.getIdReserva().toString();
        }
        return null;

    }

    @Override
    public Reserva getObjetoPorId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
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

    public ReservaBean getrBean() {
        return rBean;
    }

    public EspacioBean geteBean() {
        return eBean;
    }
    
    
    public FrmReservaHistorial getFrmReservaHistorial() {
        return frmRH;
    }

    public void setFrmReservaHistorial(FrmReservaHistorial frmRH) {
        this.frmRH = frmRH;
    }

    public TipoEspacioBean getTeBean() {
        return teBean;
    }

    public EspacioCaracteristicaBean getTerBean() {
        return terBean;
    }

    public int getIdAreaSeleccionada() {
        return idAreaSeleccionada;
    }

    public List<TipoReserva> getListaTipoReserva() {
        return listaTipoReserva;
    }

    public void setListaTipoReserva(List<TipoReserva> listaTipoReserva) {
        this.listaTipoReserva = listaTipoReserva;
    }

    public String getPathEspacio() {
        return pathEspacio;
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

    public List<Espacio> getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(List<Espacio> espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public List<String> getCaractaristicasDisponibles() {
        return caractaristicasDisponibles;
    }

    public void setCaractaristicasDisponibles(List<String> caractaristicasDisponibles) {
        this.caractaristicasDisponibles = caractaristicasDisponibles;
    }

    public List<Integer> getCaracteristicasSeleccionadas() {
        return caracteristicasSeleccionadas;
    }

    public void setCaracteristicasSeleccionadas(List<Integer> caracteristicasSeleccionadas) {
        this.caracteristicasSeleccionadas = caracteristicasSeleccionadas;
    }

    public List<String> getCaracteristicasDisponiblesAsItems() {
        return caracteristicasDisponiblesAsItems;
    }

    public void setCaracteristicasDisponiblesAsItems(List<String> caracteristicasDisponiblesAsItems) {
        this.caracteristicasDisponiblesAsItems = caracteristicasDisponiblesAsItems;
    }

}
