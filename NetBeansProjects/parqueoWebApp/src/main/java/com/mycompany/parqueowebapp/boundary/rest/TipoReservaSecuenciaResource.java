/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.rest;

import com.mycompany.parqueowebapp.app.entity.TipoReservaSecuencia;
import com.mycompany.parqueowebapp.control.TipoReservaSecuenciaBean;
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
@Path("tipo_reserva_secuencia")
public class TipoReservaSecuenciaResource implements Serializable {
    
    // RECORDATORIO QUE LA VALIDACION DEPENDE DE OTRAS TABLAS, UNICAMENTE SE HA CREADO LA VALIDACION BASICA DE LA TABLA Y FECHAS
    
    @Inject
    TipoReservaSecuenciaBean trsBean;
    
    @Inject
    wsNotificarCambios wsNC;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoReservaSecuencia> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "page_size")
            @DefaultValue(value = "100000") int pageSize) {

        return trsBean.findRange(first, pageSize);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(
            @PathParam("id")
            final Integer idTipoReservaSecuencia) {
        if (idTipoReservaSecuencia != null) {
            TipoReservaSecuencia findById = trsBean.findById(idTipoReservaSecuencia);
            if (findById != null) {
                return Response.status(Response.Status.OK).entity(findById).build();
            }
            return Response.status(Response.Status.NOT_FOUND).header("not-found", idTipoReservaSecuencia).build();
        }
        return Response.status(422).
                header("missing-parameter", "id").
                build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoReservaSecuencia registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReservaSecuencia() != null && registro.getNombre() != null && registro.getIdTipoReserva() != null && registro.getIndicaFin() != null && registro.getIdTipoReservaSecuenciaPadre() != null) {
            try {
                trsBean.create(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReservaSecuencia()
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
    public Response replace(TipoReservaSecuencia registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReservaSecuencia() != null && registro.getNombre() != null) {
            try {
                trsBean.modify(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReservaSecuencia()
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
    public Response delete(TipoReservaSecuencia registro,
            @Context UriInfo info
    ) {
        if (registro != null && registro.getIdTipoReservaSecuencia() != null && registro.getNombre() != null) {
            try {
                trsBean.delete(registro);
                URI requestUri = info.getAbsolutePath();
                wsNC.actualizarTabla("La tabla ha cambiado");
                return Response.status(Response.Status.CREATED).header("location", requestUri.toString()
                        + "/" + registro.getIdTipoReservaSecuencia()
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
