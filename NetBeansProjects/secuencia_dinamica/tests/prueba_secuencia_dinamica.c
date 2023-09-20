#include <stdio.h>
#include <stdlib.h>
#include <CUnit/Basic.h>
#include "../secuencia_dinamica.h"

/*
 * CUnit Test Suite
 */

FILE* tmp;

void imprimir(void*);

void _prueba_imprimir(void*, FILE*);

void imprimir(void *dato) {
    fprintf(stdout, "%01x\n", *((int*) dato));
    fprintf(tmp, "%01x\n", *((int*) dato));
}

int init_suite(void) {
    tmp = tmpfile();
    return 0;
}

int clean_suite(void) {
    fclose(tmp);
    return 0;
}

void testCrear_secuencia_dinamica() {
    int arreglod[5] = {1, 2, 3, 4, 5};
    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
    CU_ASSERT_TRUE(seqd->ancho == 5);
}

//void testIterar_secuencia_dinamica() {
//    int arreglod[5] = {1, 2, 3, 4, 5};
//    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
//    iterar_secuencia_dinamica(seqd, imprimir);
//    const int tmp_length = ftell(tmp);
//    rewind(tmp);
//    CU_ASSERT_EQUAL(tmp_length, (sizeof (arreglod) / sizeof (arreglod[0]))*2);
//}
//
void testAncho() {
    int arreglod[5] = {1, 2, 3, 4, 5};
    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
    CU_ASSERT_TRUE(ancho_secuencia_dinamica(seqd) == 5);
}

void testGet_at_secuencia_dinamica() {
    int arreglod[5] = {1, 2, 3, 4, 5};
    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
    int posicion_buscada = 2;
    int encontrado = *((int*) get_at_secuencia_dinamica(seqd, posicion_buscada));
    CU_ASSERT_TRUE(arreglod[posicion_buscada] == encontrado);
}

void testSet_at_secuencia_dinamica() {
    int arreglod[5] = {1, 2, 3, 4, 5};
    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
    int posicion_buscada = 2;
    int nuevo = 7;
    set_at_secuencia_dinamica(seqd, posicion_buscada, &nuevo);
    int encontrado = *((int*) get_at_secuencia_dinamica(seqd, posicion_buscada));
    CU_ASSERT_TRUE(nuevo == encontrado);
}

//void testInsert_at_secuencia_dinamica() {
//    int arreglod[5] = {1, 2, 3, 4, 5};
//    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
//    int posicion_buscada = 2;
//    int nuevo = 7;
//
//    insert_at_secuencia_dinamica(seqd, posicion_buscada, &nuevo);
//    CU_ASSERT_TRUE(ancho_secuencia_dinamica(seqd) == 6);
//    int encontrado = *((int*) get_at_secuencia_dinamica(seqd, posicion_buscada));
//    CU_ASSERT_TRUE(encontrado == nuevo);
//    posicion_buscada++;
//    encontrado = *((int*) get_at_secuencia_dinamica(seqd, posicion_buscada));
//    CU_ASSERT_TRUE(arreglod[posicion_buscada - 1] == encontrado);
//
//}
//
//void testDelete_at_secuencia_dinamica() {
//    int arreglod[5] = {1, 2, 3, 4, 5};
//    SECUENCIA_DINAMICA* seqd = crear_secuencia_dinamica(arreglod, 5, sizeof (int));
//    int posicion_buscada = 2;
//
//    delete_at_secuencia_dinamica(seqd, posicion_buscada);
//    CU_ASSERT_TRUE(ancho_secuencia_dinamica(seqd) == 4);
//}
//
//void testGet_carnet() {
//    printf("\n===Carnet===\n%s\n============\n", get_carnet());
//}

int main() {
    CU_pSuite pSuite = NULL;

    /* Initialize the CUnit test registry */
    if (CUE_SUCCESS != CU_initialize_registry())
        return CU_get_error();

    /* Add a suite to the registry */
    pSuite = CU_add_suite("prueba_secuencia_dinamica", init_suite, clean_suite);
    if (NULL == pSuite) {
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Add the tests to the suite */
    if (
//            (NULL == CU_add_test(pSuite, "testGet_carnet", testGet_carnet)) ||
            (NULL == CU_add_test(pSuite, "testCrear_secuencia_dinamica", testCrear_secuencia_dinamica)) ||
//            (NULL == CU_add_test(pSuite, "testIterar_secuencia_dinamica", testIterar_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testAncho", testAncho)) ||
            (NULL == CU_add_test(pSuite, "testGet_at_secuencia_dinamica", testGet_at_secuencia_dinamica)) ||
            (NULL == CU_add_test(pSuite, "testSet_at_secuencia_dinamica", testSet_at_secuencia_dinamica))){
//            (NULL == CU_add_test(pSuite, "testInsert_at_secuencia_dinamica", testInsert_at_secuencia_dinamica)) ||
//            (NULL == CU_add_test(pSuite, "testDelete_at_secuencia_dinamica", testDelete_at_secuencia_dinamica)))
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Run all tests using the CUnit Basic interface */
    CU_basic_set_mode(CU_BRM_VERBOSE);
    CU_basic_run_tests();
    CU_cleanup_registry();
    return CU_get_error();
}