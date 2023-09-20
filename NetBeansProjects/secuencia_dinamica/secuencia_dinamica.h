
#ifndef SECUENCIA_DINAMICA_H
#define SECUENCIA_DINAMICA_H

#ifdef __cplusplus
extern "C" {
#endif

    /**
     * Define el dato que contendra las secuencias.
     * Cada tupla consta de un dato (de cualquier tipo) y un puntero a la siguiente
     * tupla en la secuencia.
     */
    typedef struct tupla_dinamica {
        void* dato;
        struct tupla_dinamica* siguiente;
    } TUPLA_DINAMICA;

    /**
     * Define la estructura de datos.
     * Esta consta de un puntero al primer elemento
     * el ancho o cantidad de elementos en la secuencia
     * el tamaño del tipo de datos almacenado
     * y un puntero al ultimo elemento en la estructura.
     */
    typedef struct secuencia_dinamica {
        TUPLA_DINAMICA* head;
        int ancho;
        int tamanio_dato;
        TUPLA_DINAMICA* tail;
    } SECUENCIA_DINAMICA;


    /**
     * Crear una nueva secuencia dinamica a partir de una referencia a un conjunto iterable de datos (arreglo), la cantidad de datos y el tamanio de cada dato
     * @param Referencia a un arreglo de datos
     * @param  cantidad de datos en el arreglo
     * @param tamanio de los datos a usar
     * @return Una nueva secuencia dinamica
     */
    SECUENCIA_DINAMICA* crear_secuencia_dinamica(void*, int, int);
    /**
     * Para cada elemento de la secuencia invoca a un callback pasado como argumento.
     * @param La secuencia dinamica a recorrer.
     * @param Funcion callback que retorna void y recibe un elemento como parametro. Este elemento sera del tipo que posea la estructura.
     */
    void iterar_secuencia_dinamica(SECUENCIA_DINAMICA*, void(*)(void*));
    /**
     * Devuelve el valor de la cantidad de registros agregados a la estructura.
     * @param La secuencia con elementos
     * @return el ancho de la secuencia.
     */
    int ancho_secuencia_dinamica(SECUENCIA_DINAMICA*);
    /**
     * Obtiene el elemento en la posicion especificada. El orden es base 0.
     * No valida si se encuentra fuera de rango
     * @param Secuencia que desea consultarse
     * @param la posicion (base 0) del elemento a consultar
     * @return el elemento en la posicion indicada
     */
    void* get_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int);
    /**
     * Modifica el valor almacenado en una posicion. No valida si esta fuera de rango.
     * @param la secuencia a modificar
     * @param la posicion del elemento a modificar
     * @param el nuevo elemento para sustituir al anterior
     */
    void set_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int, void*);
    /**
     * Inserta un elemento en la posicion espeficada, desplazando los elementos
     * restantes a la derecha. No valida la posicion fuera de rango
     * @param la secuencia a modificar
     * @param la posicion donde se insertara el nuevo elemento
     * @param el nuevo valor a insertar.
     */
    void insert_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int, void*);
    /**
     * Elimina un elemento de la secuencia de acuerdo a su posicion, desplazando
     * los elementos restantes hacia la izquierda. No valida la posicion fuera
     * de rango
     * @param Secuencia a modificar
     * @param Posicion del elemento a eliminar.
     */
    void delete_at_secuencia_dinamica(SECUENCIA_DINAMICA*, int);
     /**
     * Retorna el numero de carnet del estudiante
     * @return
     */
    char* get_carnet();


#endif /* SECUENCIA_DINAMICA_H */
