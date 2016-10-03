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
public class PaisServiceImplTest {

    public PaisServiceImplTest() {
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
     * Test of guardarPais method, of class PaisServiceImpl.
     */
    @Test
    public void testGuardarPaisNull() {
        System.out.println("guardarPaisNull");
        Pais pais = null;
        PaisServiceImpl instance = new PaisServiceImpl();
        boolean expResult = false;
        boolean result = instance.guardarPais(pais);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testGuardarPais() {
        System.out.println("guardarPais");
        Pais pais = new Pais("NombrePais");
        PaisServiceImpl instance = new PaisServiceImpl();
        boolean expResult = true;
        boolean result = instance.guardarPais(pais);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of existePais method, of class PaisServiceImpl.
     */
    @Test
    public void testExistePaisFalse() {
        System.out.println("existePaisFalse");
        String nombre = "NombrePais";
        PaisServiceImpl instance = new PaisServiceImpl();
        boolean expResult = false;
        boolean result = instance.existePais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testExistePais() {
        System.out.println("existePais");
        String nombre = "NombrePais";
        PaisServiceImpl instance = new PaisServiceImpl();
        Pais pais = new Pais("NombrePais");
        instance.guardarPais(pais);
        boolean expResult = true;
        boolean result = instance.existePais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerPais method, of class PaisServiceImpl.
     */
    @Test
    public void testObtenerPaisNull() {
        System.out.println("obtenerPaisNull");
        String nombre = "NombrePais";
        PaisServiceImpl instance = new PaisServiceImpl();
        Pais expResult = null;
        Pais result = instance.obtenerPais(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testObtenerPais() {
        System.out.println("obtenerPais");
        String nombre = "NombrePais";
        PaisServiceImpl instance = new PaisServiceImpl();
        Pais pais = new Pais("NombrePais");
        instance.guardarPais(pais);
        String expResult = "NombrePais";
        Pais result = instance.obtenerPais(nombre);
        assertEquals(expResult, result.getNombre());
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerTodosPaises method, of class PaisServiceImpl.
     */
    @Test
    public void testObtenerTodosPaisesEmpty() {
        System.out.println("obtenerTodosPaisesEmpty");
        PaisServiceImpl instance = new PaisServiceImpl();
        int expResult = 0;
        List<Pais> result = instance.obtenerTodosPaises();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaPais();
    }

    @Test
    public void testObtenerTodosPaises() {
        System.out.println("obtenerTodosPaises");
        PaisServiceImpl instance = new PaisServiceImpl();
        Pais pais = new Pais("NombrePais");
        instance.guardarPais(pais);
        int expResult = 1;
        List<Pais> result = instance.obtenerTodosPaises();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaPais();
    }

    /**
     * Test of vaciarPersistenciaPais method, of class PaisServiceImpl.
     */
    @Test
    public void testVaciarPersistenciaPais() {
        System.out.println("vaciarPersistenciaPais");
        PaisServiceImpl instance = new PaisServiceImpl();
        Pais pais = new Pais("NombrePais");
        instance.guardarPais(pais);
        instance.vaciarPersistenciaPais();
        int expResult = 0;
        int result = instance.obtenerTodosPaises().size();
        assertEquals(expResult, result);

    }

}
