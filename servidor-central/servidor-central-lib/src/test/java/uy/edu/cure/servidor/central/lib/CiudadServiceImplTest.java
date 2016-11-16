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
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.dto.Pais;

/**
 *
 * @author SCN
 */
public class CiudadServiceImplTest {

    public CiudadServiceImplTest() {
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
     * Test of guardarCiudad method, of class CiudadServiceImpl.
     */
    @Test
    public void testGuardarCiudadNull() {
        System.out.println("guardarCiudadNull");
        Ciudad ciudad = null;
        CiudadServiceImpl instance = new CiudadServiceImpl();
        boolean expResult = false;
        boolean result = instance.guardarCiudad(ciudad);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testGuardarCiudad() {
        System.out.println("guardarCiudad");
        Pais uru = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", uru.getNombre());
        CiudadServiceImpl instance = new CiudadServiceImpl();
        boolean expResult = true;
        boolean result = instance.guardarCiudad(ciudad);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    /**
     * Test of existeCiudad method, of class CiudadServiceImpl.
     */
    @Test
    public void testExisteCiudadFalse() {
        System.out.println("existeCiudadFalse");
        String nombre = "Maldonado";
        CiudadServiceImpl instance = new CiudadServiceImpl();
        boolean expResult = false;
        boolean result = instance.existeCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testExisteCiudad() {
        System.out.println("existeCiudad");
        String nombre = "Maldonado";
        CiudadServiceImpl instance = new CiudadServiceImpl();
        Pais uru = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", uru.getNombre());
        instance.guardarCiudad(ciudad);
        boolean expResult = true;
        boolean result = instance.existeCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    /**
     * Test of obtenerCiudad method, of class CiudadServiceImpl.
     */
    @Test
    public void testObtenerCiudadNull() {
        System.out.println("obtenerCiudadNull");
        String nombre = "Maldonado";
        CiudadServiceImpl instance = new CiudadServiceImpl();
        Ciudad expResult = null;
        Ciudad result = instance.obtenerCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testObtenerCiudad() {
        System.out.println("obtenerCiudad");
        String nombre = "Maldonado";
        CiudadServiceImpl instance = new CiudadServiceImpl();
        Pais uru = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", uru.getNombre());
        instance.guardarCiudad(ciudad);
        String expResult = "Maldonado";
        Ciudad result = instance.obtenerCiudad(nombre);
        assertEquals(expResult, result.getNombre());
        instance.vaciarPersistenciaCiudad();
    }

    /**
     * Test of obtenerTodosCiudades method, of class CiudadServiceImpl.
     */
    @Test
    public void testObtenerTodosCiudadesEmpty() {
        System.out.println("obtenerTodosCiudadesEmpty");
        CiudadServiceImpl instance = new CiudadServiceImpl();
        int expResult = 0;
        List<Ciudad> result = instance.obtenerTodosCiudades();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testObtenerTodosCiudades() {
        System.out.println("obtenerTodosCiudadesEmpty");
        CiudadServiceImpl instance = new CiudadServiceImpl();
        Pais uru = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", uru.getNombre());
        instance.guardarCiudad(ciudad);
        int expResult = 1;
        List<Ciudad> result = instance.obtenerTodosCiudades();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaCiudad();
    }

    /**
     * Test of vaciarPersistenciaCiudad method, of class CiudadServiceImpl.
     */
    @Test
    public void testVaciarPersistenciaCiudad() {
        System.out.println("vaciarPersistenciaCiudad");
        CiudadServiceImpl instance = new CiudadServiceImpl();
        Pais uru = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", uru.getNombre());
        instance.guardarCiudad(ciudad);
        instance.vaciarPersistenciaCiudad();
        int expResult = 0;
        List<Ciudad> result = instance.obtenerTodosCiudades();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaCiudad();
    }

}
