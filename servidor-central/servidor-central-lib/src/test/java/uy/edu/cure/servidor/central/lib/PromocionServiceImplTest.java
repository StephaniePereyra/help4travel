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
public class PromocionServiceImplTest {

    public PromocionServiceImplTest() {
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
     * Test of guardarPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testGuardarPromocionNull() {
        System.out.println("guardarPromocionNull");
        Promocion promocion = null;
        PromocionServiceImpl instance = new PromocionServiceImpl();
        boolean expResult = false;
        boolean result = instance.guardarPromocion(promocion);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of guardarPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testGuardarPromocionTrue() {
        System.out.println("guardarPromocionNullTrue");
        Promocion promocion = new Promocion("nombre", 10, 100, null);
        PromocionServiceImpl instance = new PromocionServiceImpl();
        boolean expResult = true;
        boolean result = instance.guardarPromocion(promocion);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testObtenerPromocionNull() {
        System.out.println("obtenerPromocionNull");
        String nombre = "";
        String nickNameProveedor = "";
        PromocionServiceImpl instance = new PromocionServiceImpl();
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion(nombre, nickNameProveedor);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testObtenerPromocionTrue() {
        System.out.println("obtenerPromocionTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen", "Password");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionServiceImpl instance = new PromocionServiceImpl();
        instance.guardarPromocion(promocion);
        Promocion expResult = promocion;
        Promocion result = instance.obtenerPromocion("nombre", "nickname");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testObtenerPromocionTrueFalse() {
        System.out.println("obtenerPromocionTrueFlase");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen", "Password");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionServiceImpl instance = new PromocionServiceImpl();
        instance.guardarPromocion(promocion);
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion("nombre", "nickname2");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionServiceImpl.
     */
    @Test
    public void testObtenerPromocionFalseTrue() {
        System.out.println("obtenerPromocionFalseTrue");
        Proveedor proveedor = new Proveedor("nickname", "nombre", "apellido", "correo", null, "nombreEmpresa", "linkEmpresa", "Imagen", "Password");
        Promocion promocion = new Promocion("nombre", 10, 100, proveedor);
        PromocionServiceImpl instance = new PromocionServiceImpl();
        instance.guardarPromocion(promocion);
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion("nombre2", "nickname");
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerTodasPromociones method, of class PromocionServiceImpl.
     */
    @Test
    public void testObtenerTodasPromociones() {
        System.out.println("obtenerTodasPromociones");
        PromocionServiceImpl instance = new PromocionServiceImpl();
        int expResult = 0;
        int result = instance.obtenerTodasPromociones().size();
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

}
