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

/**
 *
 * @author SCN
 */
public class CiudadControllerImplTest {

    public CiudadControllerImplTest() {
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
     * Test of crearCiudad method, of class CiudadControllerImpl.
     */
    @Test
    public void testCrearCiudad() {
        System.out.println("crearCiudad");
        String nombreCiudad = "Maldonado";
        String nombrePais = "Uruguay";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        boolean expResult = true;
        boolean result = instance.crearCiudad(nombreCiudad, nombrePais);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

    @Test
    public void testCrearCiudadPaisNoExiste() {
        System.out.println("crearCiudadPaisNoExiste");
        String nombreCiudad = "Maldonado";
        String nombrePais = "Uruguay";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        boolean expResult = false;
        boolean result = instance.crearCiudad(nombreCiudad, nombrePais);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testCrearCiudadYaExistente() {
        System.out.println("crearCiudadYaExistente");
        String nombreCiudad = "Maldonado";
        String nombrePais = "Uruguay";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        boolean expResult = false;
        instance.crearCiudad(nombreCiudad, nombrePais);
        boolean result = instance.crearCiudad(nombreCiudad, nombrePais);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

    /**
     * Test of existeCiudad method, of class CiudadControllerImpl.
     */
    @Test
    public void testExisteCiudadFalse() {
        System.out.println("existeCiudadFalse");
        String nombre = "Maldonado";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        boolean expResult = false;
        boolean result = instance.existeCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testExisteCiudad() {
        System.out.println("existeCiudad");
        String nombre = "Maldonado";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        instance.crearCiudad(nombre, "Uruguay");
        boolean expResult = true;
        boolean result = instance.existeCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerCiudad method, of class CiudadControllerImpl.
     */
    @Test
    public void testObtenerCiudadNull() {
        System.out.println("obtenerCiudadNull");
        String nombre = "Maldonado";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        Ciudad expResult = null;
        Ciudad result = instance.obtenerCiudad(nombre);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testObtenerCiudad() {
        System.out.println("obtenerCiudad");
        String nombre = "Maldonado";
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        instance.crearCiudad(nombre, "Uruguay");
        String expResult = "Maldonado";
        Ciudad result = instance.obtenerCiudad(nombre);
        assertEquals(expResult, result.getNombre());
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

    /**
     * Test of obtenerTodosCiudades method, of class CiudadControllerImpl.
     */
    @Test
    public void testObtenerTodosCiudadesEmpty() {
        System.out.println("obtenerTodosCiudadesEmpty");
        CiudadControllerImpl instance = new CiudadControllerImpl();
        int expResult = 0;
        List<Ciudad> result = instance.obtenerTodosCiudades();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaCiudad();
    }

    @Test
    public void testObtenerTodosCiudades() {
        System.out.println("obtenerTodosCiudades");
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        instance.crearCiudad("Maldonado", "Uruguay");
        int expResult = 1;
        List<Ciudad> result = instance.obtenerTodosCiudades();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

    /**
     * Test of vaciarPersistenciaCiudad method, of class CiudadControllerImpl.
     */
    @Test
    public void testVaciarPersistenciaCiudad() {
        System.out.println("vaciarPersistenciaCiudad");
        CiudadControllerImpl instance = new CiudadControllerImpl();
        PaisControllerImpl p = new PaisControllerImpl();
        p.crearPais("Uruguay");
        instance.crearCiudad("Maldonado", "Uruguay");
        instance.vaciarPersistenciaCiudad();
        int expResult = 0;
        int result = instance.obtenerTodosCiudades().size();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCiudad();
        p.vaciarPersistenciaPais();
    }

}
