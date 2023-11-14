/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author daniloues
 */
@Named
public class comparadorFechas {
    public static boolean fechaValida(Date date) {
            // Convertir a la zona local
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = date.toInstant().atZone(zoneId).toLocalDateTime();

            int anio = localDateTime.getYear();
            int mes = localDateTime.getMonthValue();
            int dia = localDateTime.getDayOfMonth();
            int hora = localDateTime.getHour();
            int minuto = localDateTime.getMinute();
            int segundo = localDateTime.getSecond();
            int milisegundo = localDateTime.getNano() / 1_000_000;

            // BASADO EN LA CONVENCION DE JAVA.UTIL.DATE
            boolean valido = anio >= 1900 && anio <= 9999
                    && mes >= 0 && mes <= 11
                    && hora >= 0 && hora <= 23
                    && minuto >= 0 && minuto <= 59
                    && segundo >= 0 && segundo <= 59
                    && milisegundo >= 0 && milisegundo <= 999;
            // SE DIVIDE LOS MESES EN 2 GRUPOS PARA BUSQUEDA BINARIA
            if (valido && mes <= 5) {
                //EL MES ES IMPAR (0 a 5) (31 DIAS) 
                if ((mes % 2) == 0 && dia >= 1 && dia <= 31) {
                    return valido;
                } // EL MES ES PAR (0 a 5) (30 DIAS, EXCEPCION FEBRERO)
                else if ((mes % 2) == 1 && dia >= 1 && dia <= 30 && mes != 1) {
                    return valido;
                } // FEBRERO
                else if (mes == 1) {
                    //BISIESTO
                    if (anio % 4 == 0 && anio % 100 != 0 || anio % 400 == 0) {
                        return (dia >= 1 && dia <= 29);

                    } // NO BISIESTO
                    else {
                        return (dia >= 1 && dia <= 28);
                    }

                }

            } else if (valido && mes >= 6) {
                //EL MES ES IMPAR (6 a 11) (30 DIAS, EXCEPTO JULIO) 
                if ((mes % 2) == 0 && dia >= 1 && dia <= 30 && mes != 6) {
                    return valido;
                } // EL MES ES PAR (6 a 11) (31 DIAS)
                else if ((mes % 2) == 1 && dia >= 1 && dia <= 31) {
                    return valido;
                } else if (mes == 6) {
                    return (dia >= 1 && dia <= 31);
                }
            }
            return false;
        }
    
    
    //Premisa DESDE --> HASTA
    public static boolean ValidarRangoFechas (Date desde, Date hasta){
        //Verficacion de fechas validas
        if(fechaValida(desde) && fechaValida(hasta)){
            //Convertir a hora local
            LocalDateTime fechaDesde = desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fechaHasta = hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            //Verificar DESDE --> HASTA
            return !fechaHasta.isBefore(fechaDesde);
        } else {
            return false;
        }
    }
}
