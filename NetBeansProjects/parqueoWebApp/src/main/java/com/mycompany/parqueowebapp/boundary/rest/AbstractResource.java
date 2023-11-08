package com.mycompany.parqueowebapp.boundary.rest;

import com.mycompany.parqueowebapp.control.AbstractDataAccess;
import com.mycompany.parqueowebapp.websocket.wsNotificarCambios;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractResource<T> implements Serializable {

    @Inject
    wsNotificarCambios wsNC;

    public abstract AbstractDataAccess<T> getDataAccess();

    public abstract boolean checkRegistro(T Registro);

    public abstract int getRegistroId(T registro);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<T> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "100000") int pageSize) {
        List<T> resultado = null;
        try{
        resultado = getDataAccess().findRange(first, pageSize);
        }
        catch(Exception ex){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return resultado;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(
            @PathParam("id")
            final Integer idT) {
        AbstractDataAccess<T> allBean = getDataAccess();
        if (idT != null) {
            T findById = allBean.findById(idT);
            if (findById != null) {
                return Response.status(Response.Status.OK).entity(findById).build();
            }
            return Response.status(Response.Status.NOT_FOUND).header("not-found", idT).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(T registro,
            @Context UriInfo info
    ) {
        AbstractDataAccess<T> allBean = getDataAccess();
        if (registro != null && checkRegistro(registro)) {
            try {
                allBean.create(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + getRegistroId(registro)
                ).build();
            } catch (Exception ex) {
                Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(500).header("create-exception", registro.toString()).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response replace(T registro,
            @Context UriInfo info
    ) {
        AbstractDataAccess<T> allBean = getDataAccess();
        if (registro != null && checkRegistro(registro)) {
            try {
                allBean.modify(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + getRegistroId(registro)
                ).build();
            } catch (Exception ex) {
                Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(500).header("create-exception", registro.toString()).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(T registro,
            @Context UriInfo info
    ) {
        AbstractDataAccess<T> allBean = getDataAccess();
        if (registro != null && checkRegistro(registro)) {
            try {
                allBean.delete(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + getRegistroId(registro)
                ).build();
            } catch (Exception ex) {
                Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(500).header("create-exception", registro.toString()).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

}
