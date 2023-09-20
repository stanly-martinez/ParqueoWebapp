#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include "./secuencia_dinamica.h"

TUPLA_DINAMICA* nueva_tupla_dinamica(int tamanio_dato, void* nuevo_dato) {
    TUPLA_DINAMICA *salida = malloc(sizeof (TUPLA_DINAMICA));
    if (salida != NULL) {
        salida->dato = malloc(tamanio_dato);
        salida->siguiente = NULL;
        if(salida->dato != NULL){
            void* agregar_dato = memcpy(salida->dato, nuevo_dato, tamanio_dato);
        } else{
            free(salida);
            salida = NULL;
        }
    }
    return salida;
}

SECUENCIA_DINAMICA* crear_secuencia_dinamica(void* arreglo_inicial, int ancho_inicial, int tamanio_dato) {
    SECUENCIA_DINAMICA *salida = malloc(sizeof (SECUENCIA_DINAMICA));
    if (salida != NULL) {
        salida->tamanio_dato = tamanio_dato;
        salida->ancho = 0;
        TUPLA_DINAMICA* nodo_actual;
        TUPLA_DINAMICA* nodo_anterior;
        nodo_anterior = nodo_actual;
        for (int i = 0; i < ancho_inicial; i++) {
            int* int_arreglo_inicial = &((int*)arreglo_inicial)[i];
            if (i == 0) {
                nodo_actual = nueva_tupla_dinamica(tamanio_dato, int_arreglo_inicial);
                salida->ancho++;
                salida->head = nodo_actual;
            } else if (i == ancho_inicial - 1) {
                nodo_actual = nueva_tupla_dinamica(tamanio_dato, int_arreglo_inicial);
                salida->ancho++;
                nodo_anterior->siguiente = nodo_actual;
                salida->tail = nodo_actual;
            } else if (i == 0 && i == ancho_inicial - 1) {
                nodo_actual = nueva_tupla_dinamica(tamanio_dato, int_arreglo_inicial);
                salida->ancho++;
                salida->head = nodo_actual;
                salida->tail = nodo_actual;
            } else {
                nodo_actual = nueva_tupla_dinamica(tamanio_dato, int_arreglo_inicial);
                salida->ancho++;
                nodo_anterior->siguiente = nodo_actual;
            }
            nodo_anterior = nodo_actual;
        }
        
    }
    return salida;
}

int ancho_secuencia_dinamica(SECUENCIA_DINAMICA* secuencia){
    if(secuencia!= NULL){
        return secuencia->ancho;
    }
    return -1;
}

void* get_at_secuencia_dinamica(SECUENCIA_DINAMICA* secuencia, int posicion){
    
    TUPLA_DINAMICA* nodo_actual = secuencia->head;
    TUPLA_DINAMICA* nodo_anterior;
    
    for(int i = 0; i <= posicion; i++){
        
        if(i == posicion){
            return nodo_actual->dato;
        }
        nodo_anterior = nodo_actual;
        nodo_actual = nodo_actual->siguiente;
    }
    
}

void set_at_secuencia_dinamica(SECUENCIA_DINAMICA*secuencia, int posicion, void* nuevo_dato){
    
    TUPLA_DINAMICA* nodo_actual = secuencia->head;
    TUPLA_DINAMICA* nodo_anterior;
    
    for(int i = 0; i <= posicion; i++){
        
        if(i == posicion){
            memcpy(nodo_actual->dato, nuevo_dato, secuencia->tamanio_dato);
        }
        nodo_anterior = nodo_actual;
        nodo_actual = nodo_actual->siguiente;
    }
}

void delete_at_secuencia_dinamica(SECUENCIA_DINAMICA* secuencia, int posicion){
    TUPLA_DINAMICA* nodo_actual = secuencia->head;
    TUPLA_DINAMICA* nodo_anterior;
    
    for(int i = 0; i <= posicion; i++){
        
        if(i == posicion){
            memcpy(nodo_actual->dato, nuevo_dato, secuencia->tamanio_dato);
        }
        nodo_anterior = nodo_actual;
        nodo_actual = nodo_actual->siguiente;
    }
}