/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.cure.servidor.central.dto.Categoria;

/**
 *
 * @author Stephanie
 */
public class CategoriaControllerImplTest {

    public CategoriaControllerImplTest() {
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
     * Test of darAltaCategoria method, of class CategoriaControllerImpl.
     */
    @Test
    public void testDarAltaCategoriaTrue() {
        System.out.println("darAltaCategoria");
        boolean expResult = true;
        boolean result = true;
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        Categoria categoria = new Categoria("Peluqueria");
        instance.guardarCategoria(categoria);
        result = instance.darAltaCategoria("Pedicuria", "Peluqueria");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    @Test
    public void testDarAltaCategoriaFalse() {
        System.out.println("darAltaCategoria");
        boolean expResult = false;
        boolean result = false;
        boolean prueba = false;
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        prueba = instance.darAltaCategoria("Pedicuria", "");
        result = instance.darAltaCategoria("Pedicuria", "");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of guardarCategoria method, of class CategoriaControllerImpl.
     */
    @Test
    public void testGuardarCategoriaTrue() {
        System.out.println("guardarCategoria true");
        boolean result = true;
        boolean expResult = true;
        Categoria categoria = new Categoria("Fitness");
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        instance.guardarCategoria(categoria);
        result = instance.existeCategoria("Fitness");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of existeCategoria method, of class CategoriaControllerImpl.
     */
    @Test
    public void testExisteCategoria() {
        System.out.println("existeCategoria");
        boolean result = true;
        boolean expResult = true;
        Categoria categoria = new Categoria("Atencion medica");
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        instance.guardarCategoria(categoria);
        result = instance.existeCategoria("Atencion medica");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of obtenerCategoria method, of class CategoriaControllerImpl.
     */
    @Test
    public void testObtenerCategoriaTrue() {
        System.out.println("obtenerCategoria True");
        Categoria expResult = null;
        Categoria result = null;
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        Categoria categoria = new Categoria("Vuelo");
        expResult = categoria;
        instance.guardarCategoria(categoria);
        result = instance.obtenerCategoria("Vuelo");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    @Test
    public void testObtenerCategoriaFalse() {
        System.out.println("obtenerCategoria false");
        Categoria expResult = null;
        Categoria result = null;
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        result = instance.obtenerCategoria("Vuelo");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of obtenerTodosCategorias method, of class CategoriaControllerImpl.
     */
    @Test
    public void testObtenerTodosCategorias() {
        System.out.println("obtenerTodosCategorias");
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        List<Categoria> expResult = new ArrayList<Categoria>();
        Categoria categoria1 = new Categoria("Viaje");
        Categoria categoria2 = new Categoria("Tipo Locomocion");
        Categoria categoria3 = new Categoria("Aerea");
        Categoria categoria4 = new Categoria("Terrestre");
        Categoria categoria5 = new Categoria("Maritima");
        expResult.add(categoria1);
        expResult.add(categoria2);
        expResult.add(categoria3);
        expResult.add(categoria4);
        expResult.add(categoria5);
        instance.guardarCategoria(categoria1);
        instance.guardarCategoria(categoria2);
        instance.guardarCategoria(categoria3);
        instance.guardarCategoria(categoria4);
        instance.guardarCategoria(categoria5);
        List<Categoria> result = instance.obtenerTodosCategorias();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    @Test
    public void testVaciarPersistenciaCategoriaController() {
        System.out.println("vaciarPersistenciaCategorias");
        CategoriaControllerImpl instance = new CategoriaControllerImpl();
        boolean expResult = true;
        boolean result = false;
        Categoria categoria1 = new Categoria("Viaje");
        Categoria categoria2 = new Categoria("Tipo Locomocion");
        Categoria categoria3 = new Categoria("Aerea");
        Categoria categoria4 = new Categoria("Terrestre");
        Categoria categoria5 = new Categoria("Maritima");
        instance.guardarCategoria(categoria1);
        instance.guardarCategoria(categoria2);
        instance.guardarCategoria(categoria3);
        instance.guardarCategoria(categoria4);
        instance.guardarCategoria(categoria5);
        instance.vaciarPersistenciaCategoria();
        List<Categoria> categorias = instance.obtenerTodosCategorias();
        if (categorias.isEmpty()) {
            result = true;
        }
        assertEquals(expResult, result);
    }
}
