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
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author juan
 */
public class ReservaServiceImplTest {

    public ReservaServiceImplTest() {
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
     * Test of guardarReserva method, of class ReservaServiceImpl.
     */
    @Test
    public void testGuardarReserva() {
        System.out.println("guardarReserva");
        Reserva reserva = null;
        ReservaServiceImpl instance = new ReservaServiceImpl();
        instance.guardarReserva(reserva);
        instance.vaciarPersistencia();
    }

    /**
     * Test of eliminarReserva method, of class ReservaServiceImpl.
     */
    @Test
    public void testEliminarReserva() {
        /*
         * Resultado 1/2 
         */
        System.out.println("eliminarReserva");
        ReservaServiceImpl instance = new ReservaServiceImpl();
        Date date = new Date();
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Reserva reserva = new Reserva(date, 89.10, "registrada", promociones, servicios);
        instance.guardarReserva(reserva);
        instance.eliminarReserva(reserva.getNumero());//elimino la reserva reccien creada
        /*
         * Resultado 2/2 
         */
        instance.eliminarReserva(21); //elimino una que no exite
    }

    /**
     * Test of existeReserva method, of class ReservaServiceImpl.
     */
    @Test
    public void testExisteReserva() {
        /*
         * Resultado 1/2 
         */
        System.out.println("existeReserva");
        ReservaServiceImpl instance = new ReservaServiceImpl();
        boolean expResult = false;
        boolean result = instance.existeReserva(10);
        assertEquals(expResult, result);// para el false
        /*
         * Resultado 2/2 
         */
        Date date = new Date();
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Reserva reserva = new Reserva(date, 125, "registrada", promociones, servicios);
        instance.guardarReserva(reserva);
        expResult = true;
        result = instance.existeReserva(reserva.getNumero());
        assertEquals(expResult, result);// para el true
        instance.vaciarPersistencia();
    }

    /**
     * Test of obtenerReserva method, of class ReservaServiceImpl.
     */
    @Test
    public void testObtenerReserva() {
        /*
         * Resultado 1/2 
         */
        System.out.println("obtenerReserva");
        ReservaServiceImpl instance = new ReservaServiceImpl();
        Reserva expResult = null;
        Reserva result = instance.obtenerReserva(10);
        assertEquals(expResult, result);
        /*
         * Resultado 2/2 
         */
        Date date = new Date();
        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        Reserva reserva = new Reserva(date, 150, "registrada", promociones, servicios);
        instance.guardarReserva(reserva);
        expResult = reserva;
        result = instance.obtenerReserva(reserva.getNumero());
        assertEquals(expResult, result);
        instance.vaciarPersistencia();
        /*
         * Resultado 3/3 
         */
        date = new Date();
        promociones = new ArrayList<Promocion>();
        servicios = new ArrayList<Servicio>();
        reserva = new Reserva(date, 150, "registrada", promociones, servicios);
        instance.guardarReserva(reserva);
        expResult = null;
        result = instance.obtenerReserva(551);
        assertEquals(expResult, result);
        instance.vaciarPersistencia();

    }

    /**
     * Test of obtenerTodasReservas method, of class ReservaServiceImpl.
     */
    @Test
    public void testObtenerTodasReservas() {
        System.out.println("obtenerTodasReservas");
        ReservaServiceImpl instance = new ReservaServiceImpl();
        List<Reserva> expResult = new ArrayList<>();
        List<Reserva> result = instance.obtenerTodasReservas();
        assertEquals(expResult, result);
    }

}
