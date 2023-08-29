/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.app.entity.TipoEspacio;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
/**
 *
 * @author daniloues
 */
public class TipoEspacioBeanTest {
    
    public TipoEspacioBeanTest() {
    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("TIPO1");
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em=mockEM;
        cut.create(nuevo);
        fail("The test case is a prototype.");
    }
    
}
