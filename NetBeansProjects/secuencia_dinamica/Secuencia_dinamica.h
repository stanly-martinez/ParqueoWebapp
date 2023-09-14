/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cFiles/file.h to edit this template
 */

/* 
 * File:   Secuencia_dinamica.h
 * Author: daniloues
 *
 * Created on August 25, 2023, 4:16 PM
 */

#ifndef SECUENCIA_DINAMICA_H
#define SECUENCIA_DINAMICA_H

#ifdef __cplusplus
extern "C" {
#endif




#ifdef __cplusplus
}
#endif

#endif /* SECUENCIA_DINAMICA_H */

// RECORDATORIO IMPORTANTE EL CODIGO AUN NECESITA SER REVISADO POR POSIBLES MEMORY LEAKS Y/O EXCEPCIONES NO CONTROLADAS, ADEMAS DE COMPLETAR LA DOCUMENTACION FALTANTE

typedef struct secuencia_dinamica{
    int tamanio_dato;
    int tamanio;
    void* head;
    void* tail;
    
} SECUENCIA_DINAMICA;

SECUENCIA_DINAMICA* nueva_secuencia_dinamica(int);

typedef struct nodo{
    int tamanio_dato;
    void* dato;
    void* siguiente_puntero;
    
} NODO; 
NODO* nuevo_nodo(int);


// EN LA VERSION ACTUAL ES NECESARIO PASAR UN NODO, CAMBIAR A QUE EL NODO SE AUTOGENERE
void push_secuencia_dinamica(SECUENCIA_DINAMICA*, void*);

int ancho_secuencia_dinamica(SECUENCIA_DINAMICA*);

void* get_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int);

void set_at_secuencia_dinamica(SECUENCIA_DINAMICA*, void*, int);

void insert_at_secuencia_dinamica(SECUENCIA_DINAMICA*, void*, int);

void delete_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int);