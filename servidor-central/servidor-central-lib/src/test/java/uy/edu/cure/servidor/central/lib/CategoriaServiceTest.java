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
public class CategoriaServiceTest {

    public CategoriaServiceTest() {
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
     * Test of guardarCategoria method, of class CategoriaService.
     */
    @Test
    public void testGuardarCategoria() {
        System.out.println("guardarCategoria");
        Categoria categoria = new Categoria("Vuelo");
        CategoriaService instance = new CategoriaService();
        instance.guardarCategoria(categoria);
        assertEquals(categoria, instance.obtenerCategoria("Vuelo"));
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of existeCategoria method, of class CategoriaService.
     */
    @Test
    public void testExisteCategoria_String() {
        System.out.println("existeCategoria");
        Categoria categoria = new Categoria("Auto");
        CategoriaService instance = new CategoriaService();
        instance.guardarCategoria(categoria);
        boolean expResult = true;
        boolean result = instance.existeCategoria("Auto");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of obtenerCategoria method, of class CategoriaService.
     */
    @Test
    public void testObtenerCategoria() {
        System.out.println("obtenerCategoria");
        Categoria categoria = new Categoria("Moto");
        CategoriaService instance = new CategoriaService();
        instance.guardarCategoria(categoria);
        Categoria expResult = categoria;
        Categoria result = instance.obtenerCategoria("Moto");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    /**
     * Test of obtenerTodosCategorias method, of class CategoriaService.
     */
    @Test
    public void testObtenerTodosCategorias() {
        System.out.println("obtenerTodosCategorias");
        Categoria categoria1 = new Categoria("Viaje");
        Categoria categoria2 = new Categoria("Hospedaje");
        CategoriaService instance = new CategoriaService();
        instance.guardarCategoria(categoria1);
        instance.guardarCategoria(categoria2);
        List<Categoria> expResult = new ArrayList<Categoria>();
        List<Categoria> result = new ArrayList<Categoria>();
        expResult.add(categoria1);
        expResult.add(categoria2);
        result = instance.obtenerTodosCategorias();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaCategoria();
    }

    @Test
    public void testVaciarPersistenciaCategoriaService() {
        System.out.println("vaciarPersistenciaCategorias");
        CategoriaService instance = new CategoriaService();
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
