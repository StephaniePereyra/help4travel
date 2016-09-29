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
import uy.edu.cure.servidor.central.dto.*;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class PromocionControllerTest {
    
    public PromocionControllerTest() {
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
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionNombreVacio() {
        System.out.println("crearPromocionNombrevacio");
        String nombre = "";
        int descuento = 0;
        String nickProveedor = "";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        String expResult = "Nombre no puede quedar en blanco";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionNoProveedor() {
        System.out.println("crearPromocionNoProveedor");
        String nombre = "nombrePromo";
        int descuento = 0;
        String nickProveedor = "";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        String expResult = "No existe proveedor";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionExistePromocion() {
        System.out.println("crearPromocionExistePromocion");
        String nombre = "nombrePromo";
        int descuento = 0;
        String nickProveedor = "nickName";
        List<String> servicios = new ArrayList<String>();
        PromocionController instanceController = new PromocionController();
        PromocionService instanceService = new PromocionService();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        Promocion promo = new Promocion(nombre, descuento, 0, proveedor);
        instanceService.guardarPromocion(promo);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Ya existe una promocion con ese nombre";
        String result = instanceController.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instanceController.obtenerTodasPromociones().clear();
        usuarioService.vaciarPersistenciaP();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionDescuentoMenor() {
        System.out.println("crearPromocionDescuentoMenor");
        String nombre = "nombrePromo";
        int descuento = -1;
        String nickProveedor = "nickName";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Descuento invalido";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
        usuarioService.vaciarPersistenciaP();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionDescuentoMayor() {
        System.out.println("crearPromocionDescuentoMayor");
        String nombre = "nombrePromo";
        int descuento = 101;
        String nickProveedor = "nickName";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Descuento invalido";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
        usuarioService.vaciarPersistenciaP();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionNoServicios() {
        System.out.println("crearPromocionNoServicios");
        String nombre = "nombrePromo";
        int descuento = 25;
        String nickProveedor = "nickName";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Debe agregar servicios";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
        usuarioService.vaciarPersistenciaP();
    }

    /**
     * Test of crearPromocion method, of class PromocionController.
     */
    @Test
    public void testCrearPromocionTrue() {
        System.out.println("crearPromocionTrue");
        String nombre = "nombrePromo";
        int descuento = 25;
        String nickProveedor = "nickName";
        List<String> servicios = new ArrayList<String>();
        PromocionController instance = new PromocionController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        ServicioService servicioService = new ServicioService();
        Servicio servicio = new Servicio("servicio", "descripcion", 100, null, null, proveedor);
        servicioService.guardarServicio(servicio);
        servicios.add("servicio");
        String expResult = "OK";
        String result = instance.crearPromocion(nombre, descuento, nickProveedor, servicios);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
        usuarioService.vaciarPersistenciaP();
        servicioService.vaciarPersistenciaServicio();
    }

    /**
     * Test of existePromocion method, of class PromocionController.
     */
    @Test
    public void testExistePromocion() {
        System.out.println("existePromocion");
        String nombre = "";
        String nickNameProveedor = "";
        PromocionController instance = new PromocionController();
        boolean expResult = false;
        boolean result = instance.existePromocion(nombre, nickNameProveedor);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerPromocion method, of class PromocionController.
     */
    @Test
    public void testObtenerPromocion() {
        System.out.println("obtenerPromocion");
        String Nombre = "";
        String nickNameProveedor = "";
        PromocionController instance = new PromocionController();
        Promocion expResult = null;
        Promocion result = instance.obtenerPromocion(Nombre, nickNameProveedor);
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }

    /**
     * Test of obtenerTodasPromociones method, of class PromocionController.
     */
    @Test
    public void testObtenerTodasPromociones() {
        System.out.println("obtenerTodasPromociones");
        PromocionController instance = new PromocionController();
        int expResult = 0;
        int result = instance.obtenerTodasPromociones().size();
        assertEquals(expResult, result);
        instance.obtenerTodasPromociones().clear();
    }
    
}
