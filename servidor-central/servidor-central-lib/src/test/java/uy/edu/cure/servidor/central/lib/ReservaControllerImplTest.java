/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Pais;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author juan
 */
public class ReservaControllerImplTest {

    public ReservaControllerImplTest() {
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
     * Test of eliminarReserva method, of class ReservaControllerImpl.
     */
    @Test
    public void testEliminarReserva() {
        /*
         * Resultado 1/2
         */
        System.out.println("eliminarReserva");
        int numero = 0;
        ReservaControllerImpl instance = new ReservaControllerImpl();
        boolean expResult = false;
        boolean result = instance.eliminarReserva(numero);
        assertEquals(expResult, result);
        /*
         * Resultado 2/2
         */
        System.out.println("crearReserva");
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Date date = new Date();
        Cliente cliente = new Cliente("nickName", "nombre", "apellido", "correo@correo", date, "imagenPerfil", "Password");
        assertEquals(expResult, result);
        Pais pais = new Pais("nombrePais");
        Ciudad origen = new Ciudad("ciudad1", pais.getNombre());
        Ciudad destino = new Ciudad("ciudad2", pais.getNombre());
        Proveedor proveedor = new Proveedor("nickName", "nombre", "apellido", "correo@proveedor", date, "nombreEmpresa", "linkEmpresa", "imagenPerfil", "Password");
        Servicio servicio = new Servicio("servicioNombre", "esto es unadescripcion", 40, origen, destino, proveedor);
        servicios.add(servicio);
        instance.crearReserva(promociones, servicios, cliente);
        expResult = true;
        result = instance.eliminarReserva(instance.obtenerTodasReservas().get(0).getNumero());
        assertEquals(expResult, result);
        instance.vaciarPersistencia();

    }

    /**
     * Test of crearReserva method, of class ReservaControllerImpl.
     */
    @Test
    public void testCrearReserva() {
        /*
         * Resultado 1/5
         */
        System.out.println("crearReserva");
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        ReservaControllerImpl instance = new ReservaControllerImpl();
        Date date = new Date();
        Cliente cliente = new Cliente("nickName", "nombre", "apellido", "correo@correo", date, "imagenPerfil", "Password");
        boolean  expResult = false;
        boolean result = instance.crearReserva(promociones, servicios, cliente);
        assertEquals(expResult, result);
        /*
         * Resultado 2/5
         */
        Pais pais = new Pais("nombrePais");
        Ciudad origen = new Ciudad("ciudad1", pais.getNombre());
        Ciudad destino = new Ciudad("ciudad2", pais.getNombre());
        Proveedor proveedor = new Proveedor("nickName", "nombre", "apellido", "correo@proveedor", date, "nombreEmpresa", "linkEmpresa", "imagenPerfil", "Password");
        Servicio servicio = new Servicio("servicioNombre", "esto es unadescripcion", 40, origen, destino, proveedor);
        servicios.add(servicio);
        expResult = true;
        result = instance.crearReserva(promociones, servicios, cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
        /*
         * Resultado 3/5
         */
        Promocion promocion = new Promocion("promo1", 10, 5000, proveedor);
        promociones.add(promocion);
        expResult = true;
        result = instance.crearReserva(promociones, servicios, cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
        instance.vaciarPersistencia();
        /*
         * Resultado 4/5 
         */
        servicios = new ArrayList<Servicio>();
        promociones.add(promocion);
        expResult = true;
        result = instance.crearReserva(promociones, servicios, cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
        /*
         * Resultado 5/5 
         */
        Proveedor proveedor1 = new Proveedor("nickName1", "nombre1", "apellido1", "correo@proveedor", date, "nombreEmpresa", "linkEmpresa", "imagenPerfil", "Password");
        promocion = new Promocion("promo1", 10, 5000, proveedor1);
        promociones.add(promocion);
        expResult = true;
        result = instance.crearReserva(promociones, servicios, cliente);
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
    }

    /**
     * Test of existeReserva method, of class ReservaControllerImpl.
     */
    @Test
    public void testExisteReserva() {
        System.out.println("existeReserva");
        int numero = 0;
        ReservaControllerImpl instance = new ReservaControllerImpl();
        boolean expResult = false;
        boolean result = instance.existeReserva(numero);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerReserva method, of class ReservaControllerImpl.
     */
    @Test
    public void testObtenerReserva() {
        System.out.println("obtenerReserva");
        ReservaControllerImpl instance = new ReservaControllerImpl();
        Reserva expResult = null;
        Reserva result = instance.obtenerReserva(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerTodasReservas method, of class ReservaControllerImpl.
     */
    @Test
    public void testObtenerTodasReservas() {
        /*
         * Resultado 1/2 
         */
        System.out.println("obtenerTodasReservas");
        ReservaControllerImpl instance = new ReservaControllerImpl();
        List<Reserva> result = instance.obtenerTodasReservas();
        assertEquals(0, result.size());
        /*
         * Resultado 2/2 
         */
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Date date = new Date();
        Cliente cliente = new Cliente("nickName", "nombre", "apellido", "correo@correo", date, "imagenPerfil", "Password");
        Pais pais = new Pais("nombrePais");
        Ciudad origen = new Ciudad("ciudad1", pais.getNombre());
        Ciudad destino = new Ciudad("ciudad2", pais.getNombre());
        Proveedor proveedor = new Proveedor("nickName", "nombre", "apellido", "correo@proveedor", date, "nombreEmpresa", "linkEmpresa", "imagenPerfil", "Password");
        Servicio servicio = new Servicio("servicioNombre", "esto es unadescripcion", 40, origen, destino, proveedor);
        servicios.add(servicio);
        instance.crearReserva(promociones, servicios, cliente);
        result = instance.obtenerTodasReservas();
        assertEquals(1, result.size());
        instance.vaciarPersistencia();
    }

    /**
     * Test of cambiarEstado method, of class ReservaControllerImpl.
     */
    @Test
    public void cambiarEstado() {
        System.out.println("cambiarEstado");
        /*
         * Resultado 1/4 
         */
        ReservaControllerImpl instance = new ReservaControllerImpl();
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Date date = new Date();
        Cliente cliente = new Cliente("nickName", "nombre", "apellido", "correo@correo", date, "imagenPerfil", "Password");
        Pais pais = new Pais("nombrePais");
        Ciudad origen = new Ciudad("ciudad1", pais.getNombre());
        Ciudad destino = new Ciudad("ciudad2", pais.getNombre());
        Proveedor proveedor = new Proveedor("nickName", "nombre", "apellido", "correo@proveedor", date, "nombreEmpresa", "linkEmpresa", "imagenPerfil", "Password");
        Servicio servicio = new Servicio("servicioNombre", "esto es unadescripcion", 40, origen, destino, proveedor);
        servicios.add(servicio);
        instance.crearReserva(promociones, servicios, cliente);
        boolean expResult = true;
        boolean result = instance.cambiarEstado(instance.obtenerTodasReservas().get(0), "Pagar");
        assertEquals(expResult, result);
        /*
         * Resultado 2/4
         */
        instance.crearReserva(promociones, servicios, cliente);
        expResult = true;
        result = instance.cambiarEstado(instance.obtenerTodasReservas().get(1), "Cancelar");
        assertEquals(expResult, result);
        /*
         * Resultado 3/4 
         */
        expResult = true;
        result = instance.cambiarEstado(instance.obtenerTodasReservas().get(0), "Pagar");
        assertEquals(expResult, result);
        /*
         * Resultado 4/4
         */
        expResult = false;
        result = instance.cambiarEstado(instance.obtenerTodasReservas().get(0), "Facturar");
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
    }
    
    @Test
    public void testAgregarCarro(){
        System.out.println("agregarCarro");
        boolean expResult,result;
        
        List<Integer> cantidadpromociones = new ArrayList<Integer>();        
        List<Integer> cantidadservicios = new ArrayList<Integer>();
        cantidadpromociones.add(2);
        cantidadpromociones.add(1);
        cantidadservicios.add(3);
        cantidadservicios.add(1);
        Servicio servicio1 = new Servicio();
        Servicio servicio2 = new Servicio();
        Promocion promocion1 = new Promocion();
        Promocion promocion2 = new Promocion();
        Reserva reserva = new Reserva();
        reserva.setServicio(servicio1);
        reserva.setServicio(servicio2);
        reserva.setPromocion(promocion1);
        reserva.setPromocion(promocion2);
        reserva.setCantidadPromociones(cantidadpromociones);
        reserva.setCantidadServicios(cantidadservicios);
        int numeroReserva;
        numeroReserva = reserva.getNumero();
        Date fecha = new Date();
        Cliente cliente = new Cliente("Juan","Pedro","Gomez","juan@correo.com",fecha,"imagen.png","1234");
        cliente.setCarrito(reserva);
        ReservaControllerImpl instance = new ReservaControllerImpl();
        instance.agregarCarro(cliente);
        result = instance.existeReserva(numeroReserva);
        expResult = true;
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
    }
}
