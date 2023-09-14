/*
 * File:   secuencia_dinamica_test.c
 * Author: daniloues
 *
 * Created on Aug 26, 2023, 12:50:33 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <CUnit/Basic.h>
#include "../Secuencia_dinamica.h"
/*
 * CUnit Test Suite
 */

int init_suite(void) {
    return 0;
}

int clean_suite(void) {
    return 0;
}

void testNueva_secuencia_dinamica() {
    int tamanio_dato = sizeof(int);
    SECUENCIA_DINAMICA* result = nueva_secuencia_dinamica(tamanio_dato);
    CU_ASSERT_TRUE(result != NULL);
}

void testNuevo_nodo() {
    int tamanio_dato = sizeof(int);
    NODO* result = nuevo_nodo(tamanio_dato);
    CU_ASSERT_TRUE(result != NULL);
}

void testPush_secuencia_dinamica() {
    int dato1 = 1;
    SECUENCIA_DINAMICA* cut = nueva_secuencia_dinamica(sizeof(dato1));
    CU_ASSERT_TRUE(cut != NULL);
    NODO* cutNode = nuevo_nodo(sizeof(dato1));
    int esperado = 1;
    push_secuencia_dinamica(cut,cutNode,&dato1);
    //EL AMPERSAND REPRESENTA QUE SE HACE REFERENCIA A LA DIRECCION DE MEMORIA
    int resultado = ancho_secuencia_dinamica(cut);
    CU_ASSERT_EQUAL(resultado,esperado);
}

void testGet_at_secuencia_dinamica() {
    int tamanio_dato = sizeof(int);
    SECUENCIA_DINAMICA* cut = nueva_secuencia_dinamica(sizeof(tamanio_dato));
    CU_ASSERT_TRUE(cut != NULL);
    NODO* cutNode = nuevo_nodo(sizeof(tamanio_dato));
    int dato2 = 25;
    int esperado = dato2;
    push_secuencia_dinamica(cut,cutNode ,&dato2);
    int resultado = *((int *) get_at_secuencia_dinamica(cut,0));
    CU_ASSERT_EQUAL(resultado,esperado);
    
}

void testSet_at_secuencia_dinamica() {
    int tamanio_dato = sizeof(int);
    SECUENCIA_DINAMICA* cut = nueva_secuencia_dinamica(sizeof(tamanio_dato));
    CU_ASSERT_TRUE(cut != NULL);
    NODO* cutNode = nuevo_nodo(sizeof(tamanio_dato));
    int dato = 25;
    int dato2 = 30;
    int esperado = dato2;
    push_secuencia_dinamica(cut,cutNode ,&dato);
    set_at_secuencia_dinamica(cut,&dato2,0);
    int resultado = *((int *) get_at_secuencia_dinamica(cut,0));
    CU_ASSERT_EQUAL(resultado,esperado);
    
}

void testInsert_at_secuencia_dinamica() {
    int tamanio_dato = sizeof(int);
    SECUENCIA_DINAMICA* cut = nueva_secuencia_dinamica(sizeof(tamanio_dato));
    CU_ASSERT_TRUE(cut != NULL);
    NODO* cutNode = nuevo_nodo(sizeof(tamanio_dato));
    int dato = 25;
    int dato2 = 30;
    int esperado = dato2;
    int anterior_esperado = dato;
    push_secuencia_dinamica(cut,cutNode ,&dato);
    insert_at_secuencia_dinamica(cut,&dato2,0);
    int resultado = *((int *) get_at_secuencia_dinamica(cut,0));
    CU_ASSERT_EQUAL(resultado,esperado);
    resultado = *((int *) get_at_secuencia_dinamica(cut,1));
    CU_ASSERT_EQUAL(resultado,anterior_esperado);
    
}

void testDelete_at_secuencia_dinamica() {
    int tamanio_dato = sizeof(int);
    SECUENCIA_DINAMICA* cut = nueva_secuencia_dinamica(sizeof(tamanio_dato));
    NODO* cutNode = nuevo_nodo(sizeof(tamanio_dato));
    NODO* cutNode2 = nuevo_nodo(sizeof(tamanio_dato));
    NODO* cutNode3 = nuevo_nodo(sizeof(tamanio_dato));
    int dato = 25;
    int dato2 = 30;
    int dato3 = 35;
    push_secuencia_dinamica(cut,cutNode ,&dato);
    push_secuencia_dinamica(cut,cutNode2 ,&dato2);
    push_secuencia_dinamica(cut,cutNode3 ,&dato3);
    int esperado = dato;
    int resultado = *((int *) get_at_secuencia_dinamica(cut,0));
    CU_ASSERT_EQUAL(resultado, esperado);
    delete_at_secuencia_dinamica(cut, 0);
    resultado = *((int *) get_at_secuencia_dinamica(cut,0));
    esperado = dato2;
    CU_ASSERT_EQUAL(resultado,esperado);
}

int main() {
    CU_pSuite pSuite = NULL;

    /* Initialize the CUnit test registry */
    if (CUE_SUCCESS != CU_initialize_registry())
        return CU_get_error();

    /* Add a suite to the registry */
    pSuite = CU_add_suite("secuencia_dinamica_test", init_suite, clean_suite);
    if (NULL == pSuite) {
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Add the tests to the suite */
    if ((NULL == CU_add_test(pSuite, "testNueva_secuencia_dinamica", testNueva_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testPush_secuencia_dinamica", testPush_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testNuevo_nodo", testNuevo_nodo)) ||
            (NULL == CU_add_test(pSuite, "testGet_at_secuencia_dinamica", testGet_at_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testSet_at_secuencia_dinamica", testSet_at_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testInsert_at_secuencia_dinamica", testInsert_at_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testDelete_at_secuencia_dinamica", testDelete_at_secuencia_dinamica)))
    {
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Run all tests using the CUnit Basic interface */
    CU_basic_set_mode(CU_BRM_VERBOSE);
    CU_basic_run_tests();
    CU_cleanup_registry();
    return CU_get_error();
}
