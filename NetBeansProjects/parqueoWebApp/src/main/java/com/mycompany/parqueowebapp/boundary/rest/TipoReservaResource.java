/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.rest;

import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import com.mycompany.parqueowebapp.control.TipoReservaBean;
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

/**
 *
 * @author daniloues
 */
@Path("tipo_reserva")
public class TipoReservaResource implements Serializable {

    @Inject
    TipoReservaBean trBean;
    
    @Inject
    wsNotificarCambios wsNC;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoReserva> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "100000") int pageSize) {

        return trBean.findRange(first, pageSize);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(
            @PathParam("id")
            final Integer idTipoReserva) {
        if (idTipoReserva != null) {
            TipoReserva findById = trBean.findById(idTipoReserva);
            if (findById != null) {
                return Response.status(Response.Status.OK).entity(findById).build();
            }
            return Response.status(Response.Status.NOT_FOUND).header("not-found", idTipoReserva).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoReserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReserva() != null && registro.getNombre() != null) {
            try {
                trBean.create(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReserva()
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
    public Response replace(TipoReserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReserva() != null && registro.getNombre() != null) {
            try {
                trBean.modify(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReserva()
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
    public Response delete(TipoReserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReserva() != null && registro.getNombre() != null) {
            try {
                trBean.delete(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReserva()
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
