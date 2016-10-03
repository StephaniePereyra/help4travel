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
import uy.edu.cure.servidor.central.dto.Pais;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author Stephanie
 */
public class ServicioServiceImplTest {

    public ServicioServiceImplTest() {
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
     * Test of guardarServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testGuardarServicioTrue() {
        System.out.println("guardarServicio");
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Servicio result = null;
        Servicio expResult = null;
        Pais pais = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        Proveedor proveedor = new Proveedor("Manolo", "Giuseppe", "Ragazzo", "giuseppe@aol.co.uk", date, " Tortugones Manolo", "www.tortugonesManolo.com", "guruceaga.png");
        Servicio servicio = new Servicio("Tortus bien ricos", "Persona muy capacitada", 100.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        expResult = servicio;
        result = instance.obtenerServicio("Tortus bien ricos", "Manolo");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of existeServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testExisteServicioTrue() {
        System.out.println("existeServicio");
        boolean expResult = true;
        Pais pais = new Pais("Estados Unidos");
        Ciudad ciudad = new Ciudad("California ", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        boolean result = instance.existeServicio("Le fitness", "Pepe");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of existeServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testExisteServicioFalse() {
        System.out.println("existeServicio false");
        boolean expResult = false;
        Pais pais = new Pais("United State");
        Ciudad ciudad = new Ciudad("Los Angeles", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        boolean result = instance.existeServicio("Au revoir mon amour", "Hasta luego, mi amor");
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of obtenerServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testObtenerServicioTrue() {
        System.out.println("obtenerServicio");
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Escocia", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        Servicio result = instance.obtenerServicio("Entrenamiento pre-temporada", "Charlie");
        Servicio expResult = servicio;
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of obtenerServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testObtenerServicioFalse() {
        System.out.println("obtenerServicio");
        Pais pais = new Pais("Brasil");
        Ciudad ciudad = new Ciudad("Porto Seguro", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        Servicio result = instance.obtenerServicio("Entrenamiento pre-campeonato", "Charlie");
        Servicio expResult = null;
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of obtenerTodosServicios method, of class ServicioServiceImpl.
     */
    @Test
    public void testObtenerTodosServicios() {
        System.out.println("obtenerTodosServicios");
        List<Servicio> expResult = new ArrayList<Servicio>();
        Pais pais = new Pais("Great Britain");
        Ciudad ciudad = new Ciudad("Ireland", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        Proveedor proveedor2 = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio2 = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        instance.guardarServicio(servicio2);
        expResult.add(servicio);
        expResult.add(servicio2);
        List<Servicio> result = instance.obtenerTodosServicios();
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of cantidadServicios method, of class ServicioServiceImpl.
     */
    @Test
    public void testCantidadServicios() {
        System.out.println("cantidadServicios");
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Irlanda", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        Proveedor proveedor2 = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio2 = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        instance.guardarServicio(servicio2);
        int result = instance.cantidadServicios();
        int expResult = 2;
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
    }

    /**
     * Test of vaciarPersistenciaServicio method, of class ServicioServiceImpl.
     */
    @Test
    public void testVaciarPersistenciaServicio() {
        System.out.println("vaciarPersistenciaServicio");
        ServicioServiceImpl instance = new ServicioServiceImpl();
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Irlanda", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        List<Servicio> expResult = new ArrayList<Servicio>();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instance.guardarServicio(servicio);
        instance.vaciarPersistenciaServicio();
        List<Servicio> result = instance.obtenerTodosServicios();
        assertEquals(expResult, result);
    }

}
