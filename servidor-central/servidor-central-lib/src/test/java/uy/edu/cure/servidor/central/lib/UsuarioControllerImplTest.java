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
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;

/**
 *
 * @author SCN
 */
public class UsuarioControllerImplTest {

    public UsuarioControllerImplTest() {
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
     * Test of crearCliente method, of class UsuarioControllerImpl.
     */
    @Test
    public void testCrearCliente() {
        System.out.println("crearCliente");
        String nickName = "ClienteX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoX@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = -1;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteExiste() {
        System.out.println("crearClienteExiste");
        String nickName = "ClienteX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoX@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteX", "Nombre", "Apellido", "correo@correo.com", 1, 2, 1995, "ruta");
        int expResult = 1;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteCorreoExiste() {
        System.out.println("crearClienteCorreoExiste");
        String nickName = "ClienteXXx";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoX@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteXX", "Nombre", "Apellido", "correoX@correo.com", 1, 2, 1995, "ruta");
        int expResult = 2;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteCorreoInv1() {
        System.out.println("crearClientecorreoInv1");
        String nickName = "ClienteXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXcorreo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteCorreoInv2() {
        System.out.println("crearClientecorreoInv2");
        String nickName = "ClienteXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "@Xcorreo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteCorreoInv3() {
        System.out.println("crearClientecorreoInv3");
        String nickName = "ClienteXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "@";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteCorreoInv4() {
        System.out.println("crearClientecorreoInv4");
        String nickName = "ClienteXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correo@";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv1() {
        System.out.println("crearClientefechaInv1");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = -1;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv2() {
        System.out.println("crearClientefechaInv2");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 2018;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv3() {
        System.out.println("crearClientefechaInv3");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 1;
        int mes = -1;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv4() {
        System.out.println("crearClientefechaInv4");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 1;
        int mes = 14;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv5() {
        System.out.println("crearClientefechaInv5");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = -1;
        int mes = 1;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv6() {
        System.out.println("crearClientefechaInv6");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 32;
        int mes = 1;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv7() {
        System.out.println("crearClientefechaInv7");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = -1;
        int mes = 4;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv8() {
        System.out.println("crearClientefechaInv8");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 32;
        int mes = 4;
        int anio = 1995;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv9() {
        System.out.println("crearClientefechaInv9");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = -1;
        int mes = 2;
        int anio = 2012;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClienteFechaInv10() {
        System.out.println("crearClientefechaInv10");
        String nickName = "ClienteXXX";
        String nombre = "NombreX";
        String apellido = "ApellidoX";
        String correo = "correoXXX@correo.com";
        int dia = 30;
        int mes = 2;
        int anio = 2012;
        String imagenPerfil = "reutaX";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    /**
     * Test of crearProveedor method, of class UsuarioControllerImpl.
     */
    @Test
    public void testCrearProveedor() {
        System.out.println("crearProveedor");
        String nickName = "UserNameZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZ@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = -1;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorExistente() {
        System.out.println("crearProveedorExiste");
        String nickName = "UserNameZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZ@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("UserNameZ", "nombre", "apellido", "correo@correo.com", 1, 2, 1950, "nombreEmpresa", "linkEmpresa", "imagenPerfil");
        int expResult = 1;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorCorreoExiste() {
        System.out.println("crearProveedorcorreoExiste");
        String nickName = "UserNameZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZ@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("UserNameZZZ", "nombre", "apellido", "correoZ@correo.com", 1, 2, 1950, "nombreEmpresa", "linkEmpresa", "imagenPerfil");
        int expResult = 2;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorCorreoInv1() {
        System.out.println("crearProveedorCorreoInv1");
        String nickName = "UserNameZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZcorreo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorCorreoInv2() {
        System.out.println("crearProveedorCorreoInv2");
        String nickName = "UserNameZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorCorreoInv3() {
        System.out.println("crearProveedorCorreoInv3");
        String nickName = "UserNameZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "@";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorCorreoInv4() {
        System.out.println("crearProveedorCorreoInv4");
        String nickName = "UserNameZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correo@";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorFechaInv1() {
        System.out.println("crearProveedorFechaInv1");
        String nickName = "UserNameZZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZZZ@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = -1;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorFechaInv2() {
        System.out.println("crearProveedorFechaInv2");
        String nickName = "UserNameZZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZZZ@correo.com";
        int dia = 1;
        int mes = 2;
        int anio = 2018;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorFechaInv3() {
        System.out.println("crearProveedorFechaInv3");
        String nickName = "UserNameZZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZZZ@correo.com";
        int dia = 1;
        int mes = -1;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testCrearProveedorFechaInv4() {
        System.out.println("crearProveedorFechaInv4");
        String nickName = "UserNameZZZ";
        String nombre = "NombreZ";
        String apellido = "ApellidoZ";
        String correo = "correoZZZ@correo.com";
        int dia = 1;
        int mes = 14;
        int anio = 1995;
        String nombreEmpresa = "EmpresaZ";
        String linkEmpresa = "www.emprezaZ.com";
        String imagenPerfil = "rutaZ";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 4;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    /**
     * Test of existeCliente method, of class UsuarioControllerImpl.
     */
    @Test
    public void testExisteClienteFalse() {
        System.out.println("existeClienteFalse");
        String nickName = "ClienteH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.existeCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testExisteCliente() {
        System.out.println("existeCliente");
        String nickName = "ClienteH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta");
        boolean expResult = true;
        boolean result = instance.existeCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    /**
     * Test of existeProveedor method, of class UsuarioControllerImpl.
     */
    @Test
    public void testExisteProveedorFalse() {
        System.out.println("existeProveedorFalse");
        String nickName = "ProveedorH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.existeProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testExisteProveedor() {
        System.out.println("existeProveedor");
        String nickName = "ProveedorH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta");
        boolean expResult = true;
        boolean result = instance.existeProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerCliente method, of class UsuarioControllerImpl.
     */
    @Test
    public void testObtenerClienteNull() {
        System.out.println("obtenerClienteNull");
        String nickName = "ClienteH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        Cliente expResult = null;
        Cliente result = instance.obtenerCliente(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testObtenerCliente() {
        System.out.println("obtenerCliente");
        String nickName = "ClienteH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta");
        String expResult = "ClienteH";
        String result = instance.obtenerCliente(nickName).getNickName();
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    /**
     * Test of obtenerProveedor method, of class UsuarioControllerImpl.
     */
    @Test
    public void testObtenerProveedorNull() {
        System.out.println("obtenerProveedorNull");
        String nickName = "ProveedorH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        Proveedor expResult = null;
        Proveedor result = instance.obtenerProveedor(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testObtenerProveedor() {
        System.out.println("obtenerProveedor");
        String nickName = "ProveedorH";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta");
        String expResult = "ProveedorH";
        String result = instance.obtenerProveedor(nickName).getNickName();
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerProveedores method, of class UsuarioControllerImpl.
     */
    @Test
    public void testObtenerProveedoresEmpty() {
        System.out.println("obtenerProveedoresEmpty");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 0;
        List<Proveedor> result = instance.obtenerProveedores();
        assertEquals(expResult, result.size());
        instance.vaciarPeristenciaP();
    }

    @Test
    public void testObtenerProveedores() {
        System.out.println("obtenerProveedores");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta");
        int expResult = 1;
        List<Proveedor> result = instance.obtenerProveedores();
        assertEquals(expResult, result.size());
        instance.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerCientes method, of class UsuarioControllerImpl.
     */
    @Test
    public void testObtenerCientesEmpty() {
        System.out.println("obtenerCientesEmpty");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 0;
        List<Cliente> result = instance.obtenerCientes();
        assertEquals(expResult, result.size());
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testObtenerCientes() {
        System.out.println("obtenerCientes");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta");
        int expResult = 1;
        List<Cliente> result = instance.obtenerCientes();
        assertEquals(expResult, result.size());
        instance.vaciarPeristenciaC();
    }

    @Test
    public void vaciarPeristenciaP() {
        System.out.println("vaciarPeristenciaP");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta");
        instance.vaciarPeristenciaP();
        int expResult = 0;
        int result = instance.obtenerProveedores().size();
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void vaciarPeristenciaC() {
        System.out.println("vaciarPeristenciaC");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta");
        instance.vaciarPeristenciaC();
        int expResult = 0;
        int result = instance.obtenerCientes().size();
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

}
