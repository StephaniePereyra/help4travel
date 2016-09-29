/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;

/**
 *
 * @author SCN
 */
public class UsuarioServiceTest {

    public UsuarioServiceTest() {
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
     * Test of guardarCliente method, of class UsuarioService.
     */
    @Test
    public void testGuardarClienteNull() {
        System.out.println("guardarClienteNull");
        Cliente cliente = null;
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        boolean result = instance.guardarCliente(cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    @Test
    public void testGuardarCliente() {
        System.out.println("guardarCliente");
        Date d = new Date();
        Cliente cliente = new Cliente("UserNameX", "NombreX", "ApellidoX", "correoX@gmailcom", d, "rutaX");
        UsuarioService instance = new UsuarioService();
        boolean expResult = true;
        boolean result = instance.guardarCliente(cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    /**
     * Test of guardarProveedor method, of class UsuarioService.
     */
    @Test
    public void testGuardarProveedorNull() {
        System.out.println("guardarProveedorNull");
        Proveedor proveedor = null;
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        boolean result = instance.guardarProveedor(proveedor);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    @Test
    public void testGuardarProveedor() {
        System.out.println("guardarProveedorNull");
        Date f = new Date();
        Proveedor proveedor = new Proveedor("UserNameZ", "NombreZ", "ApellidoZ", "correoZ@gmailcom", f, "rutaZ", "EmpresaZ", "www.empresaZ.com");
        UsuarioService instance = new UsuarioService();
        boolean expResult = true;
        boolean result = instance.guardarProveedor(proveedor);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    /**
     * Test of existeCliente method, of class UsuarioService.
     */
    @Test
    public void testExisteClienteFalse() {
        System.out.println("existeClienteFalse");
        String nickName = "Username";
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        boolean result = instance.existeCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    @Test
    public void testExisteCliente() {
        System.out.println("existeCliente");
        String nickName = "Username";
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Cliente cliente = new Cliente("Username", "nombre", "apellido", "correo@correo", d, "ruta");
        instance.guardarCliente(cliente);
        boolean expResult = true;
        boolean result = instance.existeCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    /**
     * Test of existeProveedor method, of class UsuarioService.
     */
    @Test
    public void testExisteProveedorFalse() {
        System.out.println("existeProveedorFalse");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        boolean result = instance.existeProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    @Test
    public void testExisteProveedor() {
        System.out.println("existeProveedor");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Proveedor proveedor = new Proveedor("UserName", "nombre", "apellido", "correo@correo", d, "empresa", "linkempresa", "ruta");
        instance.guardarProveedor(proveedor);
        boolean expResult = true;
        boolean result = instance.existeProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    /**
     * Test of obtenerCliente method, of class UsuarioService.
     */
    @Test
    public void testObtenerClienteNull() {
        System.out.println("obtenerClienteNull");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        Cliente expResult = null;
        Cliente result = instance.obtenerCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    @Test
    public void testObtenerCliente() {
        System.out.println("obtenerCliente");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Cliente cliente = new Cliente("UserName", "nombre", "apellido", "correo@correo", d, "ruta");
        instance.guardarCliente(cliente);
        String expResult = "UserName";
        String result = instance.obtenerCliente(nickName).getNickName();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }

    /**
     * Test of obtenerProveedor method, of class UsuarioService.
     */
    @Test
    public void testObtenerProveedorNull() {
        System.out.println("obtenerProveedorNull");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        Proveedor expResult = null;
        Proveedor result = instance.obtenerProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    @Test
    public void testObtenerProveedor() {
        System.out.println("obtenerProveedor");
        String nickName = "UserName";
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Proveedor proveedor = new Proveedor("UserName", "nombre", "apellido", "correo@correo", d, "empresa", "linkempresa", "ruta");
        instance.guardarProveedor(proveedor);
        String expResult = nickName;
        String result = instance.obtenerProveedor(nickName).getNickName();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    /**
     * Test of existeCorreo method, of class UsuarioService.
     */
    @Test
    public void testExisteCorreoFalse() {
        System.out.println("existeCorreoFalse");
        String correo = "correo@correo.com";
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        boolean result = instance.existeCorreo(correo);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
        instance.vaciarPersistenciaC();
    }

    @Test
    public void testExisteCorreo() {
        System.out.println("existeCorreo");
        String correo = "correo@correo.com";
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Proveedor proveedor = new Proveedor("UserName", "nombre", "apellido", "correo@correo.com", d, "empresa", "linkempresa", "ruta");
        instance.guardarProveedor(proveedor);
        boolean expResult = true;
        boolean result = instance.existeCorreo(correo);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
        instance.vaciarPersistenciaC();
    }

    /**
     * Test of obtenerProveedores method, of class UsuarioService.
     */
    @Test
    public void testObtenerProveedoresEmpty() {
        System.out.println("obtenerProveedoresEmpty");
        UsuarioService instance = new UsuarioService();
        int expResult = 0;
        List<Proveedor> result = instance.obtenerProveedores();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaP();
    }

    @Test
    public void testObtenerProveedores() {
        System.out.println("obtenerProveedores");
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Proveedor proveedor = new Proveedor("UserName", "nombre", "apellido", "correo@correo.com", d, "empresa", "linkempresa", "ruta");
        instance.guardarProveedor(proveedor);
        int expResult = 1;
        List<Proveedor> result = instance.obtenerProveedores();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaP();
    }

    /**
     * Test of obtenerCientes method, of class UsuarioService.
     */
    @Test
    public void testObtenerCientesEmpty() {
        System.out.println("obtenerCientesEmpty");
        UsuarioService instance = new UsuarioService();
        int expResult = 0;
        List<Cliente> result = instance.obtenerCientes();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaC();

    }

    @Test
    public void testObtenerCientes() {
        System.out.println("obtenerCientes");
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Cliente cliente = new Cliente("UserName", "nombre", "apellido", "correo@correo", d, "ruta");
        instance.guardarCliente(cliente);
        int expResult = 1;
        List<Cliente> result = instance.obtenerCientes();
        assertEquals(expResult, result.size());
        instance.vaciarPersistenciaC();

    }

    @Test
    public void testvaciarPeristenciaP() {
        System.out.println("vaciarPeristenciaP");
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Proveedor proveedor = new Proveedor("UserName", "nombre", "apellido", "correo@correo.com", d, "empresa", "linkempresa", "ruta");
        instance.guardarProveedor(proveedor);
        instance.vaciarPersistenciaP();
        int expResult = 0;
        int result = instance.obtenerProveedores().size();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaP();
    }

    @Test
    public void testvaciarPeristenciaC() {
        System.out.println("vaciarPeristenciaC");
        UsuarioService instance = new UsuarioService();
        Date d = new Date();
        Cliente cliente = new Cliente("UserName", "nombre", "apellido", "correo@correo", d, "ruta");
        instance.guardarCliente(cliente);
        instance.vaciarPersistenciaC();
        int expResult = 0;
        int result = instance.obtenerCientes().size();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaC();
    }
}
