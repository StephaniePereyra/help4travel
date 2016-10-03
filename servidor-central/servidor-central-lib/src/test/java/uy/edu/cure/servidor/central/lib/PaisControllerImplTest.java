/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.cure.servidor.central.dto.Pais;

/**
 *
 * @author SCN
 */
public class PaisControllerImplTest {

    public PaisControllerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of crearPais method, of class PaisControllerImpl.
     */
    @Test
    public void testCrearPaisFalse() {
        System.out.println("crearPaisFalse");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        instance.crearPais("Uruguay");
        boolean expResult = false;
        boolean result = instance.crearPais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testCrearPais() {
        System.out.println("crearPais");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        boolean expResult = true;
        boolean result = instance.crearPais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of existePais method, of class PaisControllerImpl.
     */
    @Test
    public void testExistePaisFalse() {
        System.out.println("existePaisFalse");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        boolean expResult = false;
        boolean result = instance.existePais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testExistePais() {
        System.out.println("existePais");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        instance.crearPais("Uruguay");
        boolean expResult = true;
        boolean result = instance.existePais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerPais method, of class PaisControllerImpl.
     */
    @Test
    public void testObtenerPaisNull() {
        System.out.println("obtenerPaisNull");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        Pais expResult = null;
        Pais result = instance.obtenerPais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testObtenerPais() {
        System.out.println("obtenerPais");
        String nombre = "Uruguay";
        PaisControllerImpl instance = new PaisControllerImpl();
        instance.crearPais("Uruguay");
        String expResult = "Uruguay";
        Pais result = instance.obtenerPais(nombre);
        assertEquals(expResult, result.getNombre());
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerTodosPaises method, of class PaisControllerImpl.
     */
    @Test
    public void testObtenerTodosPaisesEmpty() {
        System.out.println("obtenerTodosPaises");
        PaisControllerImpl instance = new PaisControllerImpl();
        int expResult = 0;
        List<Pais> result = instance.obtenerTodosPaises();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testObtenerTodosPaises() {
        System.out.println("obtenerTodosPaises");
        PaisControllerImpl instance = new PaisControllerImpl();
        instance.crearPais("Uruguay");
        int expResult = 1;
        List<Pais> result = instance.obtenerTodosPaises();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of vaciarPersistenciaPais method, of class PaisControllerImpl.
     */
    @Test
    public void testVaciarPersistenciaPais() {
        System.out.println("vaciarPersistenciaPais");
        PaisControllerImpl instance = new PaisControllerImpl();
        instance.crearPais("Uruguay");
        instance.vaciarPersistenciaPais();
        int expResult = 0;
        int result = instance.obtenerTodosPaises().size();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

}
