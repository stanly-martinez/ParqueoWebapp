package com.mycompany.parqueowebapp.websocket;

import com.mycompany.parqueowebapp.control.wsManejadorSesion;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import java.io.IOException;
import java.io.Serializable;

@Named
@ApplicationScoped
@ServerEndpoint(value = "/wsnotificar_cambios")
public class wsNotificarCambios implements Serializable{
    
    
    // SERA INYECTADO EN EL ABSTRACT
    @Inject
    wsManejadorSesion wsMS;
    
    @OnOpen
    public void conecto(Session s) {
        wsMS.agregar(s);
        System.out.println("Conecto");

    }

    @OnClose
    public void cerrar(Session s) {
        wsMS.eliminar(s);
    }

    public void actualizarTabla(String Message) throws IOException {
        try {
            for (Session sesion : wsMS.getSesiones()) {
                if (sesion.isOpen()) {
                    sesion.getBasicRemote().sendText(Message);
                }
            }
        } catch (IOException e) {
        }
    }

}
