/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.jsf;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@FacesConverter("tipoReservaConverter")
public class tipoReservaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
        if (obj != null && !"".equals(obj)) {
            String id;
            try {
                id = this.getId(getClazz(ctx, component), obj);
                if (id == null) {
                    id = "";
                }
                id = id.trim();
                component.getAttributes().put(id, getClazz(ctx, component).cast(obj));
                return id;
            } catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace(); // seu log aqui
            }
        }
        return null;
    }

    private Class<?> getClazz(FacesContext facesContext, UIComponent component) {
        return component.getValueExpression("value").getType(facesContext.getELContext());
    }

    public String getId(Class<?> clazz, Object obj) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        List<Class<?>> hierarquiaDeClasses = this.getHierarquiaDeClasses(clazz);

        for (Class<?> classeDaHierarquia : hierarquiaDeClasses) {
            for (Field field : classeDaHierarquia.getDeclaredFields()) {
                if ((field.getAnnotation(Id.class)) != null || field.getAnnotation(EmbeddedId.class) != null) {
                    Field privateField = classeDaHierarquia.getDeclaredField(field.getName());
                    privateField.setAccessible(true);
                    if (privateField.get(clazz.cast(obj)) != null) {
                        return (String) field.getType().cast(privateField.get(clazz.cast(obj))).toString();
                    }
                }
            }
        }
        return null;
    }

    public List<Class<?>> getHierarquiaDeClasses(Class<?> clazz) {

        List<Class<?>> hierarquiaDeClasses = new ArrayList<>();
        Class<?> classeNaHierarquia = clazz;
        while (classeNaHierarquia != Object.class) {
            hierarquiaDeClasses.add(classeNaHierarquia);
            classeNaHierarquia = classeNaHierarquia.getSuperclass();

        }
        return hierarquiaDeClasses;
    }
}
