/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.rest;

import com.mycompany.parqueowebapp.app.entity.Reserva;
import com.mycompany.parqueowebapp.control.ReservaBean;
import com.mycompany.parqueowebapp.control.comparadorFechas;
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
@Path("reserva")
public class ReservaResource implements Serializable {

    
    @Inject
    ReservaBean rBean;
    
    @Inject
    wsNotificarCambios wsNC;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Reserva> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "100000") int pageSize) {
        
        return rBean.findRange(first, pageSize);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(
            @PathParam("id")
            final Integer idReserva) {
        if (idReserva != null) {
            Reserva findById = rBean.findById(idReserva);
            if (findById != null) {
                return Response.status(Response.Status.OK).entity(findById).build();
            }
            return Response.status(Response.Status.NOT_FOUND).header("not-found", idReserva).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Reserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdReserva() != null && registro.getIdEspacio() != null && registro.getIdTipoReserva() != null && registro.getHasta() != null && registro.getDesde() != null
                && comparadorFechas.fechaValida(registro.getDesde()) && comparadorFechas.fechaValida(registro.getHasta())) {
            try {
                rBean.create(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdReserva()
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
    public Response replace(Reserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdReserva() != null && registro.getIdEspacio() != null && registro.getIdTipoReserva() != null && registro.getHasta() != null && registro.getDesde() != null
                && comparadorFechas.fechaValida(registro.getDesde()) && comparadorFechas.fechaValida(registro.getHasta())) {
            try {
                rBean.modify(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdReserva()
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
    public Response delete(Reserva registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdReserva() != null && registro.getIdEspacio() != null && registro.getIdTipoReserva() != null && registro.getHasta() != null && registro.getDesde() != null
                && comparadorFechas.fechaValida(registro.getDesde()) && comparadorFechas.fechaValida(registro.getHasta())) {
            try {
                rBean.delete(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdReserva()
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
