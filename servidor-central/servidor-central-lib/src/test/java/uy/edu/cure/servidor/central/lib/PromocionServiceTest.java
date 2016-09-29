/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class PromocionServiceTest {

    public PromocionServiceTest() {
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
     * Test of guardarPromocion method, of class PromocionService.
     */
    @Test
    public void testGuardarPromocionNull() {
        System.out.println("guardarPromocionNull");
        Promocion promocion = null;
        PromocionService instance = new PromocionService();
        boolean expResult = false;
        boolean result = instance.guardarPromocion(promocion);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of guardarPromocion method, of class PromocionService.
     */
    @Test
    public void testGuardarPromocionTrue() {
        System.out.println("guardarPromocionNullTrue");
        Promocion promocion = new Promocion("nombre", 10, 100, null);
        PromocionService instance = new PromocionService();
        boolean expResult = true;
        boolean result = instance.guardarPromocion(promocion);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of existePromocion method, of class PromocionService.
     */
    @Test
    public void testExistePromocionNull() {
        System.out.println("existePromocionNull");
        String nombre = "";
        String nickNameProveedor = "";
        PromocionService instance = new PromocionService();
        boolean expResult = false;
        boolean result = instance.existePromocion(nombre, nickNameProveedor);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of existePromocion method, of class PromocionService.
     */
    @Test
    public void testExistePromocionTrue() {
        System.out.println("existePromocionTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        boolean expResult = true;
        boolean result = instance.existePromocion("nombre", "nickname");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of existePromocion method, of class PromocionService.
     */
    @Test
    public void testExistePromocionTrueFalse() {
        System.out.println("existePromocionTrueFalse");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        boolean expResult = false;
        boolean result = instance.existePromocion("nombre", "nickname2");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of existePromocion method, of class PromocionService.
     */
    @Test
    public void testExistePromocionFalseTrue() {
        System.out.println("existePromocionFalseTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        boolean expResult = false;
        boolean result = instance.existePromocion("nombre2", "nickname2");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionService.
     */
    @Test
    public void testObtenerPromocionNull() {
        System.out.println("obtenerPromocionNull");
        String nombre = "";
        String nickNameProveedor = "";
        PromocionService instance = new PromocionService();
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion(nombre, nickNameProveedor);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionService.
     */
    @Test
    public void testObtenerPromocionTrue() {
        System.out.println("obtenerPromocionTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        Promocion expResult = promocion;
        Promocion result = instance.obtenerPromocion("nombre", "nickname");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionService.
     */
    @Test
    public void testObtenerPromocionTrueFalse() {
        System.out.println("obtenerPromocionTrueFlase");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion("nombre", "nickname2");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionService.
     */
    @Test
    public void testObtenerPromocionFalseTrue() {
        System.out.println("obtenerPromocionFalseTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionService instance = new PromocionService();
        instance.guardarPromocion(promocion);
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion("nombre2", "nickname");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerTodasPromociones method, of class PromocionService.
     */
    @Test
    public void testObtenerTodasPromociones() {
        System.out.println("obtenerTodasPromociones");
        PromocionService instance = new PromocionService();
        int expResult = 0;
        int result = instance.obtenerTodasPromociones().size();
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

}
