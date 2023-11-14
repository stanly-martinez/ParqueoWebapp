/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.jsf;

import com.mycompany.parqueowebapp.app.entity.TipoReserva;
import com.mycompany.parqueowebapp.control.TipoReservaBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;


@FacesConverter(value = "tipoReservaConverter", managed = true)
@RequestScoped
public class tipoReservaConverter implements Converter<TipoReserva> {

    @EJB
    private TipoReservaBean tipoReservaBean;

    @PostConstruct
    public void init() {
        // Aquí podrías inicializar recursos si es necesario
    }

    @Override
    public TipoReserva getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                int id = Integer.parseInt(value);
                return tipoReservaBean.hallarTipoReserva(id); // Reemplaza este método con el de tu bean que busca por ID
            } catch (NumberFormatException e) {
                // Manejo de error si la conversión falla
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoReserva value) {
        if (value instanceof TipoReserva) {
            return String.valueOf(((TipoReserva) value).getIdTipoReserva());
        }
        return null;
    }
}
