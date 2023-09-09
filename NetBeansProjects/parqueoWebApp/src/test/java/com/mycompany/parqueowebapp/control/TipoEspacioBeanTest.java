/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.TipoEspacio;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author daniloues
 */
public class TipoEspacioBeanTest {

    EntityManager emGeneradorExcepcion;
    List<TipoEspacio> listaRegistros;

    public TipoEspacioBeanTest() {

        this.listaRegistros = new ArrayList<>();
        this.listaRegistros.add(new TipoEspacio(1));
        this.listaRegistros.get(0).setNombre("chepe");
        this.listaRegistros.add(new TipoEspacio(2));
        this.listaRegistros.add(new TipoEspacio(3));

        emGeneradorExcepcion = new EntityManager() {
            @Override
            public void persist(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T merge(T t) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T find(Class<T> type, Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T getReference(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void setFlushMode(FlushModeType fmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public FlushModeType getFlushMode() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void lock(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void detach(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public LockModeType getLockMode(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void setProperty(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Map<String, Object> getProperties() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(CriteriaUpdate cu) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createQuery(CriteriaDelete cd) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNamedQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string, Class type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Query createNativeQuery(String string, String string1) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createNamedStoredProcedureQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string, Class... types) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String string, String... strings) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void joinTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isJoinedToTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> T unwrap(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Object getDelegate() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void close() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isOpen() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityTransaction getTransaction() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityManagerFactory getEntityManagerFactory() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public CriteriaBuilder getCriteriaBuilder() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Metamodel getMetamodel() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> EntityGraph<T> createEntityGraph(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityGraph<?> createEntityGraph(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public EntityGraph<?> getEntityGraph(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        };

    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    @Test
    public void testFindRange() {
        System.out.println("findRange");
        int first = 0;
        int pageSize = 10;
        TipoEspacioBean cut = new TipoEspacioBean();
        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(tq.getResultList()).thenReturn(this.listaRegistros);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(mockCB);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCB.createQuery(TipoEspacio.class)).thenReturn(mockCQ);
        Mockito.when(em.createQuery(mockCQ)).thenReturn(tq);
        List<TipoEspacio> resultado = cut.findRange(-1, -1);
        assertTrue(resultado.isEmpty());
        cut.em = em;
        resultado = cut.findRange(first, pageSize);
        assertNotNull(resultado);
        assertEquals(listaRegistros.size(), resultado.size());
        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.findRange(first, pageSize);
        });
        //fail("Run you fools");
    }

    @Test
    public void testCreate() {

        System.out.println("create");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("TIPO1");
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockEM;
        cut.create(nuevo);
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.create(nuevo);
        });
        // fail("Run you fools");

    }

    @Test
    public void testModify() {

        System.out.println("create");
        TipoEspacioBean cut = new TipoEspacioBean();
        // TipoEspacio fail1 = null;
        // AGREGAR PRUEBA DE FALLO EN ILLEGAL STATE E ILLEGAL ARGUMENT
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        cut.em = mockEM;
        TipoEspacio registro = new TipoEspacio();
        TipoEspacio registro2 = new TipoEspacio();
        registro.setNombre("TIPO1");
        registro2.setNombre("TIPO2");
        cut.create(registro);
        Mockito.when(mockEM.merge(registro2)).thenReturn(registro2);
        TipoEspacio result = cut.modify(null);
        assertNull(result);
        cut.em = mockEM;
        result = cut.modify(registro2);
        assertEquals(registro2, result);
        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.modify(registro2);
        });
        // fail("Run you fools");

    }

    @Test
    public void testDelete() {
        int first = 0;
        int pageSize = 10;

        System.out.println("delete");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("chepe");
        nuevo.setIdTipoEspacio(1);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockEM;
        cut.create(nuevo);
        cut.delete(nuevo);
        assertThrows(IllegalArgumentException.class, () -> {
            cut.delete(null);
        });
        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.delete(nuevo);
        });
        // fail("Run you fools");
    }
    
    @Test
    public void testCount() {

        System.out.println("count");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("chepe");
        nuevo.setIdTipoEspacio(1);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockEM;
        cut.create(nuevo);
        
        // MOCKITO SIMULACION DE CRITERIA BUILD, CRITERIA QUERY Y TYPEDQUERY
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(tq.getMaxResults()).thenReturn(1);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCB.createQuery(TipoEspacio.class)).thenReturn(mockCQ);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(tq);
        // FIN DE SIMLUACION, INICIO DE METODO DE CONTEO
        int resultado = cut.count();
        assertEquals(1,resultado);
        cut.em = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.count();
        });
        // fail("Run you fools");
    }
}
