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
    public void testCrearClienteExisteCliente() {
        System.out.println("testCrearClienteExisteCliente");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        String imagen = "ruta";
        int dia = 31;
        int mes = 10;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        int expResult = 1;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void testCrearClientePasswordNoEqual() {
        System.out.println("testCrearClientePasswordNoEqual");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "otraPassword";
        String nombre = "";
        String apellido = "";
        String correo = "";
        String imagen = "";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 2;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
    }

    @Test
    public void testCrearClienteCorreoNoValido() {
        System.out.println("testCrearClienteCorreoNoValido");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nombre = "";
        String apellido = "";
        String correo = "@";
        String imagen = "";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
    }

    @Test
    public void testCrearClienteExisteCorreo() {
        System.out.println("testCrearClienteExisteCorreo");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        String imagen = "ruta";
        int dia = 30;
        int mes = 11;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente("nickName2", nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        int expResult = 4;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }
    
    @Test
    public void testCrearClienteFechaNoValida() {
        System.out.println("testCrearClienteFechaNoValida");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        String imagen = "ruta";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 5;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }
    
    @Test
    public void testCrearCliente() {
        System.out.println("testCrearCliente");
        String nickName = "nickName";
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        String imagen = "ruta";
        int dia = 10;
        int mes = 10;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = -1;
        int result = instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagen, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    /**
     * Test of crearProveedor method, of class UsuarioControllerImpl.
     */
    @Test
    public void testCrearProveedorExisteNickName() {
        System.out.println("testcrearProveedorExisteNickName");
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nickName = "nickName";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 2;
        int anio = 1995;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "linkEmpresa";
        String imagenPerfil = "ruta";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        int expResult = 1;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }
    
    @Test
    public void testCrearProveedorPasswordNoEqual() {
        System.out.println("testcrearProveedorPasswordNoEqual");
        String passWord = "Password";
        String passWordConfirm = "otraPassword";
        String nickName = "";
        String nombre = "";
        String apellido = "";
        String correo = "";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        String nombreEmpresa = "";
        String linkEmpresa = "";
        String imagenPerfil = "";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 2;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCrearProveedorCorreoNoValido() {
        System.out.println("testcrearProveedorCorreoNoValido");
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nickName = "";
        String nombre = "";
        String apellido = "";
        String correo = "@";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        String nombreEmpresa = "";
        String linkEmpresa = "";
        String imagenPerfil = "";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 3;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCrearProveedorCorreoEnUso() {
        System.out.println("testcrearProveedorCorreoEnUso");
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nickName = "nickName";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 10;
        int mes = 10;
        int anio = 1995;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "linkEmpresa";
        String imagenPerfil = "ruta";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("nickName2", nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        int expResult = 4;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }
    
    @Test
    public void testCrearProveedorFechaNoValida() {
        System.out.println("testcrearProveedorFechaNoValida");
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nickName = "";
        String nombre = "";
        String apellido = "";
        String correo = "correo@correo";
        int dia = 0;
        int mes = 0;
        int anio = 0;
        String nombreEmpresa = "";
        String linkEmpresa = "";
        String imagenPerfil = "";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = 5;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCrearProveedor() {
        System.out.println("testcrearProveedor");
        String passWord = "Password";
        String passWordConfirm = "Password";
        String nickName = "nickName";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 10;
        int mes = 10;
        int anio = 1995;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "linkEmpresa";
        String imagenPerfil = "ruta";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        int expResult = -1;
        int result = instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWordConfirm);
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
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta", "Password", "Password");
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
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta", "Password", "Password");
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
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta", "Password", "Password");
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
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta", "Password", "Password");
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
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta", "Password", "Password");
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
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta", "Password", "Password");
        int expResult = 1;
        List<Cliente> result = instance.obtenerCientes();
        assertEquals(expResult, result.size());
        instance.vaciarPeristenciaC();
    }

    @Test
    public void vaciarPeristenciaP() {
        System.out.println("vaciarPeristenciaP");
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor("ProveedorH", "nombre", "apellido", "correo@correo", 7, 10, 1995, "empresa", "linkempresa", "ruta", "Password", "Password");
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
        instance.crearCliente("ClienteH", "nombre", "apellido", "correo@correo", 1, 2, 1995, "ruta", "Password", "Password");
        instance.vaciarPeristenciaC();
        int expResult = 0;
        int result = instance.obtenerCientes().size();
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void correoValidoCantArrobaMayor() {
        System.out.println("correoValidoCantArrobaMayor");
        String correo = "correo@correo@";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.correoValido(correo);
        assertEquals(expResult, result);
    }

    @Test
    public void correoValidoEmpiezaArroba() {
        System.out.println("correoValidoEmpiezaArroba");
        String correo = "@correo";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.correoValido(correo);
        assertEquals(expResult, result);
    }

    @Test
    public void correoValidoFinArroba() {
        System.out.println("correoValidoFinArroba");
        String correo = "correo@";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.correoValido(correo);
        assertEquals(expResult, result);
    }

    @Test
    public void correoValido() {
        System.out.println("correoValido");
        String correo = "correo@correo";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = true;
        boolean result = instance.correoValido(correo);
        assertEquals(expResult, result);
    }

    @Test
    public void LogInClienteNoCliente() {
        System.out.println("LogInClienteNoCliente");
        String nickName = "";
        String passWord = "";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.LogInCliente(nickName, passWord);
        assertEquals(expResult, result);
    }

    @Test
    public void LogInClientePasswordNoEqual() {
        System.out.println("LogInClientPasswordNoEqual");
        String nickName = "nickName";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil, passWord, passWord);
        boolean expResult = false;
        boolean result = instance.LogInCliente(nickName, "otraPassWord");
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void LogInCliente() {
        System.out.println("LogInClient");
        String nickName = "nickName";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil, passWord, passWord);
        boolean expResult = true;
        boolean result = instance.LogInCliente(nickName, passWord);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void LogInProveedorNoProveedor() {
        System.out.println("LogInProveedorNoProveedor");
        String nickName = "";
        String passWord = "";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.LogInProveedor(nickName, passWord);
        assertEquals(expResult, result);
    }

    @Test
    public void LogInProveedorPasswordNoEqual() {
        System.out.println("LogInProveedorPasswordNoEqual");
        String nickName = "nickname";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "link";
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWord);
        boolean expResult = false;
        boolean result = instance.LogInProveedor(nickName, "otraPassword");
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void LogInProveedor() {
        System.out.println("LogInProveedor");
        String nickName = "nickname";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "link";
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWord);
        boolean expResult = true;
        boolean result = instance.LogInProveedor(nickName, passWord);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }
    
    @Test
    public void validarFechaAnioMenor () {
        System.out.println("validarFechaAnioMenor");
        int dia = 0;
        int mes = 0;
        int anio = -1;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaAnioMayor () {
        System.out.println("validarFechaAnioMayor");
        int dia = 0;
        int mes = 0;
        int anio = 2018;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaMesMenor () {
        System.out.println("validarFechaMesMenor");
        int dia = 0;
        int mes = -1;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaMesMayor () {
        System.out.println("validarFechaMesMayor");
        int dia = 0;
        int mes = 13;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMenor1 () {
        System.out.println("validarFechaDiaMenor1");
        int dia = -1;
        int mes = 1;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMayor1 () {
        System.out.println("validarFechaDiaMayor1");
        int dia = 33;
        int mes = 3;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaOK1 () {
        System.out.println("validarFechaOK1");
        int dia = 10;
        int mes = 5;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = true;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
        mes = 7;
        result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
        mes = 8;
        result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
        mes = 10;
        result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
        mes = 12;
        result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMenor2 () {
        System.out.println("validarFechaDiaMenor2");
        int dia = -1;
        int mes = 4;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMayor2 () {
        System.out.println("validarFechaDiaMayor2");
        int dia = 33;
        int mes = 6;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaOK2 () {
        System.out.println("validarFechaOK2");
        int dia = 10;
        int mes = 9;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = true;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
        mes = 11;
        result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMenor3 () {
        System.out.println("validarFechaDiaMenor3");
        int dia = -1;
        int mes = 2;
        int anio = 2000;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMayor3 () {
        System.out.println("validarFechaDiaMayor3");
        int dia = 33;
        int mes = 2;
        int anio = 2000;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaOK3 () {
        System.out.println("validarFechaOK3");
        int dia = 10;
        int mes = 2;
        int anio = 2000;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = true;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMenor4 () {
        System.out.println("validarFechaDiaMenor4");
        int dia = -1;
        int mes = 2;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaDiaMayor4 () {
        System.out.println("validarFechaDiaMayor4");
        int dia = 33;
        int mes = 2;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }
    
    @Test
    public void validarFechaOK4 () {
        System.out.println("validarFechaOK4");
        int dia = 10;
        int mes = 2;
        int anio = 1995;
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = true;
        boolean result = instance.validarFecha(dia, mes, anio);
        assertEquals(expResult, result);
    }

    @Test
    public void existeNickNameCliente() {
        System.out.println("existeNickNameCliente");
        String nickName = "nickname";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, imagenPerfil, passWord, passWord);
        boolean expResult = true;
        boolean result = instance.existeNickName(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaC();
    }

    @Test
    public void existeNickNameProveedor() {
        System.out.println("existeNickNameProveedor");
        String nickName = "nickname";
        String nombre = "nombre";
        String apellido = "apellido";
        String correo = "correo@correo";
        int dia = 1;
        int mes = 1;
        int anio = 2015;
        String nombreEmpresa = "empresa";
        String linkEmpresa = "link";
        String imagenPerfil = "ruta";
        String passWord = "passWord";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        instance.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, imagenPerfil, passWord, passWord);
        boolean expResult = true;
        boolean result = instance.existeNickName(nickName);
        assertEquals(expResult, result);
        instance.vaciarPeristenciaP();
    }

    @Test
    public void existeNickName() {
        System.out.println("existeNickName");
        String nickName = "nickname";
        UsuarioControllerImpl instance = new UsuarioControllerImpl();
        boolean expResult = false;
        boolean result = instance.existeNickName(nickName);
        assertEquals(expResult, result);
    }

}
