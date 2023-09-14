#include "./Secuencia_dinamica.h"
#include "stdlib.h"
#include <stdio.h>
SECUENCIA_DINAMICA* nueva_secuencia_dinamica(int tamanio_dato) {
    SECUENCIA_DINAMICA *salida = malloc(sizeof (SECUENCIA_DINAMICA));
    if (salida != NULL) {
        salida->head = NULL;
        salida->tamanio_dato = tamanio_dato;
        salida->tamanio = 0;
        salida->tail = NULL;
    }
    return salida;
}

/**
 * Crea un nodo con 'tamanio_dato' int que contiene un espacio de dato 'tamanio dato' y guarda un puntero apuntando al siguiente nodo en la secuencia
 * @param tamanio_dato Tamanio del dato a guardar
 * @return regresa el nodo que sera guardado en la secuencia
 */
NODO* nuevo_nodo(int tamanio_dato) {
    NODO *salida = malloc(sizeof (NODO));
    if (salida != NULL) {
        salida->tamanio_dato = tamanio_dato;
        salida->dato = malloc(tamanio_dato);
        salida->siguiente_puntero = NULL;
        if (salida->dato == NULL) {
            free(salida);
            salida = NULL;
        }
    }
    return salida;

}

int ancho_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia) {
    if (secuencia != NULL) {
        return secuencia->tamanio;
    }
    return -1;
}

void push_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia, void *dato) {
    if (secuencia != NULL) {
        NODO* nuevo = nuevo_nodo(secuencia->tamanio_dato);
        if (nuevo != NULL) {
            void* resultado = memcpy(nuevo->dato, dato, secuencia->tamanio_dato);
            if (resultado != NULL) {
                if (secuencia->tamanio == 0) {
                    // HEAD APUNTA AL PRIMER NODO
                    secuencia->head = nodo;
                    secuencia->tamanio++;
                    secuencia->tail = nodo;
                } else {
                    NODO* ultimo = secuencia->tail;
                    ultimo->siguiente_puntero = nuevo;
                    secuencia->tail = nuevo;
                    secuencia->tamanio++;
                }
            }
        }


    }
}

/**
 * Busca en todos los nodos relacionados la posicion que se ha pedido
 * @param secuencia RECIBE UNA SECUENCIA EN LA CUAL SE BUSCARA EL DATO
 * @param posicion SE RECIBE LA POSICION EN LA QUE SE DEBE BUSCAR EL DATO
 * @return SE REGRESA EL VALOR DEL DATO
 */
void* get_at_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia, int posicion) {
    if (secuencia != NULL && posicion < secuencia->tamanio) {
        // SE DEBE INICIALIZAR EL NODO ACTUAL E IGUALARLO AL NODO AL QUE APUNTA HEAD **PD: HEAD DEBE APUNTAR AL PRIMER NODO
        NODO* actual = secuencia->head;
        // EL BUCLE FOR RECORRERA LA CANTIDA DE NODOS SEGUN LA POSICION
        for (int i_nodo = 0; i_nodo <= posicion; i_nodo++) {
            // SI EL NODO ACTUAL HA LLEGADO A LA POSICION NECESARIA SE REGRESA EL PUNTERO QUE APUNTA AL DATO DE ESTE NODO
            if (i_nodo == posicion) {
                return actual->dato;
                // SI EL NODO ACTUAL NO HA LLEGADO A LA POSICION NECESARIA SE ACTUALIZA EL NODO ACTUAL. CAMBIAR EL ACTUAL POR EL NODO AL QUE APUNTA EL VALOR 'SIGUIENTE PUNTERO'
            } else {
                actual = actual->siguiente_puntero;
            }

        }
    }
    return NULL;
}

/**
 * Modifica el valor del dato en la posicion proporcionada de la estructura dinamica
 * @param secuencia, recibe una secuencia en la cual buscar el valor a modificar
 * @param dato, recibe un dato que sera mdoficiado en la posicion proporcionada
 * @param posicion, recibe la posicion en la que el dato recibido sera intercambiado
 */
void set_at_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia, void *dato, int posicion) {
    if (secuencia != NULL && posicion < secuencia->tamanio) {
        NODO* actual = secuencia->head;
        for (int i_nodo = 0; i_nodo <= posicion; i_nodo++) {
            if (i_nodo == posicion) {
                void* resultado = memcpy(actual->dato, dato, secuencia->tamanio_dato);
            } else {
                actual = &actual->siguiente_puntero;
            }
        }
    }
    return NULL;
}

void insert_at_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia, void *dato, int posicion) {
    if (secuencia != NULL && posicion < secuencia->tamanio) {
        NODO* insertar = nuevo_nodo(secuencia->tamanio_dato);
        NODO* actual = secuencia->head;
        NODO* anterior = NULL;
        for (int i_nodo = 0; i_nodo <= posicion; i_nodo++) {
            if (i_nodo == posicion && posicion == 0) {
                void* resultado = memcpy(insertar->dato, dato, secuencia->tamanio_dato);
                if (resultado != NULL) {
                    insertar->siguiente_puntero = actual;
                    secuencia->head = insertar;
                    secuencia->tamanio++;
                }

            } else if (i_nodo == posicion) {
                void* resultado = memcpy(insertar->dato, dato, secuencia->tamanio_dato);
                if (resultado != NULL) {
                    insertar->siguiente_puntero = actual;
                    anterior->siguiente_puntero = insertar;
                    secuencia->tamanio++;
                }

            } else {
                anterior = actual;
                actual = &actual->siguiente_puntero;
            }
        }
    }
    return NULL;
}

void delete_at_secuencia_dinamica(SECUENCIA_DINAMICA *secuencia, int posicion) {
    if (secuencia != NULL && posicion < secuencia->tamanio) {
        NODO* actual = secuencia->head;
        NODO* anterior = NULL;
        for (int i_nodo = 0; i_nodo <= posicion; i_nodo++) {
            // CASO SE ESTA BORRANDO EL PRIMER NODO
            if (i_nodo == posicion && posicion == 0) {
                // CASO EL PRIMER NODO ES TAMBIEN EL UNICO NODO
                if (posicion + 1 == secuencia->tamanio) {
                    secuencia->head = NULL;
                    secuencia->tail = NULL;
                    secuencia->tamanio--;
                    free(actual);
                } else {
                    secuencia->head = actual->siguiente_puntero;
                    secuencia->tamanio--;
                    free(actual);
                }
                /*if (actual != NULL) {
                    // LA MEMORIA NO FUE BORRADA EXITOSAMENTE, SE REVIERTEN LOS PUNTEROS
                    if (posicion + 1 == secuencia->tamanio) {
                        secuencia->head = actual;
                        secuencia->tamanio++;
                        secuencia->tail = actual;
                    } else {
                        secuencia->head = actual;
                        secuencia->tamanio++;
                    }
                 }
                 */

            }                // CASO SE ESTA BORRANDO CUALQUIER NODO MENOS EL PRIMERO
            else if (i_nodo == posicion) {
                if (posicion + 1 == secuencia->tamanio) {
                    // SE ESTA BORRANDO EL ULTIMO
                    secuencia->tail = anterior;
                    anterior->siguiente_puntero = NULL;
                    secuencia->tamanio--;
                    free(actual);
                } else {
                    anterior->siguiente_puntero = actual->siguiente_puntero;
                    secuencia->tamanio--;
                    free(actual);
                    actual == NULL;
                }
                /*if (actual != NULL) {
                    // LA MEMORIA NO FUE BORRADA EXITOSAMENTE, SE REVIERTEN LOS PUNTEROS
                    if (posicion + 1 == secuencia->tamanio) {
                        secuencia->tail = actual;
                        anterior->siguiente_puntero = actual;
                        secuencia->tamanio++;
                    } else {
                        anterior->siguiente_puntero = actual;
                        secuencia->tamanio++;
                    }
                } */
            } else {
                anterior = actual;
                actual = &actual->siguiente_puntero;
            }
        }
    }
    return NULL;
}