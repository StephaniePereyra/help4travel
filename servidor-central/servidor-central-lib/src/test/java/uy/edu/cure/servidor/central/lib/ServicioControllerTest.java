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
import uy.edu.cure.servidor.central.dto.*;

/**
 *
 * @author Stephanie
 */
public class ServicioControllerTest {

    public ServicioControllerTest() {
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
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioNombreVacio() {
        System.out.println("crearServicioNombreVacio");
        String nombreServicio = "";
        String descripcion = "";
        double precio = 0.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        String expResult = "Nombre servicio no puede quedar vacio";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioNoProveedor() {
        System.out.println("crearServicioNoProveedor");
        String nombreServicio = "nombreServicio";
        String descripcion = "";
        double precio = 0.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        String expResult = "No existe proveedor";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioExisteServicio() {
        System.out.println("crearServicioExisteServicio");
        String nombreServicio = "nombreServicio";
        String descripcion = "";
        double precio = 0.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        ServicioService servicioService = new ServicioService();
        Servicio servicio = new Servicio(nombreServicio, "descripcion", 10, null, null, proveedor);
        servicioService.guardarServicio(servicio);
        String expResult = "Ya existe servicio";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        servicioService.vaciarPersistenciaServicio();
        usuarioService.vaciarPersistenciaP();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioDescripcionVacio() {
        System.out.println("crearServicioDescripcionVacio");
        String nombreServicio = "nombreServicio";
        String descripcion = "";
        double precio = 0.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Descripcion no puede quedar vacio";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioPrecioMenor() {
        System.out.println("crearServicioPrecioMenor");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 0.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Precio debe ser mayor a 0";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioNoOrigen() {
        System.out.println("crearServicioNoOrigen");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "";
        String ciudadDestino = "";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        String expResult = "Ciudad de origen invalido";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioDestinoNull() {
        System.out.println("crearServicioDestinoNull");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = null;
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        ciudadService.guardarCiudad(ciudad);
        String expResult = "Elija ciudad de destino";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioNoDestino() {
        System.out.println("crearServicioNoDestino");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = "";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        ciudadService.guardarCiudad(ciudad);
        String expResult = "Ciudad de destino invalido";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
        ciudadService.vaciarPersistenciaCiudad();;
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioCiudadesIguales() {
        System.out.println("crearServicioCiudadesIguales");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = "ciudadOrigen";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        ciudadService.guardarCiudad(ciudad);
        String expResult = "Ciudad de origen debe ser diferente a ciudad de destino";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
        ciudadService.vaciarPersistenciaCiudad();;
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioNoCategorias() {
        System.out.println("crearServicioNoCategorias");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = "<null>";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        ciudadService.guardarCiudad(ciudad);
        String expResult = "Debe ingresar como minimo una categoria";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        usuarioService.vaciarPersistenciaP();
        ciudadService.vaciarPersistenciaCiudad();;
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioTrueCDNull() {
        System.out.println("crearServicioTrueCDNull");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = "<null>";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        categorias.add("categoria");
        List<String> imagenes = new ArrayList<String>();
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        ciudadService.guardarCiudad(ciudad);
        CategoriaService categoriaService = new CategoriaService();
        Categoria categoria = new Categoria("categoria");
        categoriaService.guardarCategoria(categoria);
        String expResult = "OK";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
        usuarioService.vaciarPersistenciaP();
        ciudadService.vaciarPersistenciaCiudad();
        categoriaService.vaciarPersistenciaCategoria();
    }
    
    /**
     * Test of crearServicio method, of class ServicioController.
     */
    @Test
    public void testCrearServicioTrueCD() {
        System.out.println("crearServicioTrueCD");
        String nombreServicio = "nombreServicio";
        String descripcion = "descripcion";
        double precio = 1.0;
        String ciudadOrigen = "ciudadOrigen";
        String ciudadDestino = "ciudadDestino";
        String nickNameProveedor = "nickNameProveedor";
        List<String> categorias = new ArrayList<String>();
        categorias.add("categoria");
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("imagen");
        ServicioController instance = new ServicioController();
        UsuarioService usuarioService = new UsuarioService();
        Proveedor proveedor = new Proveedor(nickNameProveedor, "nombre", "apellido", "correo@c", null, "empresa", "link", null);
        usuarioService.guardarProveedor(proveedor);
        CiudadService ciudadService = new CiudadService();
        Pais pais = new Pais("pais");
        Ciudad ciudad = new Ciudad(ciudadOrigen, pais);
        Ciudad ciudad2 = new Ciudad(ciudadDestino, pais);
        ciudadService.guardarCiudad(ciudad);
        ciudadService.guardarCiudad(ciudad2);
        CategoriaService categoriaService = new CategoriaService();
        Categoria categoria = new Categoria("categoria");
        categoriaService.guardarCategoria(categoria);
        String expResult = "OK";
        String result = instance.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        assertEquals(expResult, result);
        instance.vaciarPersistenciaServicio();
        usuarioService.vaciarPersistenciaP();
        ciudadService.vaciarPersistenciaCiudad();
        categoriaService.vaciarPersistenciaCategoria();
    }

    /**
     * Test of existeServicio method, of class ServicioController.
     */
    @Test
    public void testExisteServicioTrue() {
        System.out.println("existeServicio");
        boolean result = true;
        boolean expResult = true;
        Pais pais = new Pais("Estados Unidos");
        Ciudad ciudad = new Ciudad("California ", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceServicio = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instanceServicio.guardarServicio(servicio);
        result = instanceController.existeServicio("Le fitness", "Pepe");
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    /**
     * Test of existeServicio method, of class ServicioService.
     */
    @Test
    public void testExisteServicioFalse() {
        System.out.println("existeServicio false");
        boolean result = true;
        boolean expResult = false;
        Pais pais = new Pais("United State");
        Ciudad ciudad = new Ciudad("Los Angeles", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceServicio = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instanceServicio.guardarServicio(servicio);
        result = instanceController.existeServicio("Au revoir mon amour", "Hasta luego, mi amor");
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerServicio method, of class ServicioController.
     */
    @Test
    public void testObtenerServicioTrue() {
        System.out.println("obtenerServicio");
        Servicio result = null;
        Servicio expResult = null;
        Pais pais = new Pais("Canada");
        Ciudad ciudad = new Ciudad("Suiza", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioController instanceController = new ServicioController();
        ServicioService instanceService = new ServicioService();
        Proveedor proveedor = new Proveedor("Agustin", "Castillo", "Zalayeta", "elagu@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-tempo", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instanceService.guardarServicio(servicio);
        result = instanceController.obtenerServicio("Entrenamiento pre-tempo", "Agustin");
        expResult = servicio;
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerServicio method, of class ServicioService.
     */
    @Test
    public void testObtenerServicioFalse() {
        System.out.println("obtenerServicio false");
        Servicio result = null;
        Servicio expResult = null;
        Pais pais = new Pais("Brasil");
        Ciudad ciudad = new Ciudad("Porto Seguro", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceServicio = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instanceServicio.guardarServicio(servicio);
        result = instanceController.obtenerServicio("Entrenamiento pre-campeonato", "Charlie");
        expResult = null;
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    /**
     * Test of obtenerTodosServicios method, of class ServicioController.
     */
    @Test
    public void testObtenerTodosServicios() {
        System.out.println("obtenerTodosServicios");
        List<Servicio> result = new ArrayList<Servicio>();
        List<Servicio> expResult = new ArrayList<Servicio>();
        Pais pais = new Pais("Great Britain");
        Ciudad ciudad = new Ciudad("Ireland", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceServicio = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        Proveedor proveedor2 = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio2 = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instanceServicio.guardarServicio(servicio);
        instanceServicio.guardarServicio(servicio2);
        expResult.add(servicio);
        expResult.add(servicio2);
        result = instanceController.obtenerTodosServicios();
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    /**
     * Test of cantidadServicios method, of class ServicioController.
     */
    @Test
    public void testCantidadServiciosTrue() {
        System.out.println("cantidadServicios");
        int result = 0;
        int expResult = 0;
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Irlanda", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceService = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        Proveedor proveedor2 = new Proveedor("Pepe", "Bartolome", "Perez ", "elToloBienTurrix@aol.co.uk", date, " Postres helados", "www.postresHelados.com", "postercampeoes.png");
        Servicio servicio2 = new Servicio("Le fitness", "Aceptamos personas que crean ser mujeres", 750.00, ciudad, ciudad, proveedor);
        instanceService.guardarServicio(servicio);
        instanceService.guardarServicio(servicio2);
        result = instanceController.cantidadServicios();
        expResult = 2;
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }

    @Test
    public void testCantidadServiciosFalse() {
        System.out.println("cantidadServicios false");
        int cantidad = 0;
        List<Servicio> result = new ArrayList<Servicio>();
        List<Servicio> expResult = new ArrayList<Servicio>();
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Irlanda", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        ServicioService instanceService = new ServicioService();
        ServicioController instanceController = new ServicioController();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Proveedor proveedor2 = new Proveedor("Manolo", "Giuseppe", "Ragazzo", "giuseppe@aol.co.uk", date, " Tortugones Manolo", "www.tortugonesManolo.com", "guruceaga.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        Servicio servicio2 = new Servicio("Tortus bien ricos", "Persona muy capacitada", 100.00, ciudad, ciudad, proveedor);
        instanceService.guardarServicio(servicio);
        instanceService.guardarServicio(servicio2);
        expResult.add(servicio);
        result = instanceController.obtenerTodosServicios();
        assertEquals(expResult.size(), result.size() - 1);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }
    
    
    /**
     * Test of vaciarPersistenciaServicio method, of class ServicioController.
     */
    @Test
    public void testVaciarPersistenciaServicioTrue() {
        System.out.println("vaciarPersistenciaServicio");
        ServicioController instanceController = new ServicioController();
        ServicioService instanceService = new ServicioService();
        Pais pais = new Pais("Gran Bretaña");
        Ciudad ciudad = new Ciudad("Irlanda", pais);
        pais.setCiudades(ciudad);
        Date date = new Date();
        List<Servicio> result = new ArrayList<Servicio>();
        List<Servicio> expResult = new ArrayList<Servicio>();
        Proveedor proveedor = new Proveedor("Charlie", "Urretavizcaya ", "Martinuccio ", "elale@gmail.com", date, " Aprende a jugar al Futbol ", "www.futbolparatodos.com", "postercampeoes.png");
        Servicio servicio = new Servicio("Entrenamiento pre-temporada", "Estimulamos el potencial de todas las personas interesadas en jugar la futbol", 250.00, ciudad, ciudad, proveedor);
        instanceService.guardarServicio(servicio);
        instanceController.vaciarPersistenciaServicio();
        result = instanceController.obtenerTodosServicios();
        assertEquals(expResult, result);
        instanceController.vaciarPersistenciaServicio();
        CiudadController ciudadController = new CiudadController();
        ciudadController.vaciarPersistenciaCiudad();
        PaisController paisController = new PaisController();
        paisController.vaciarPersistenciaPais();
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.vaciarPeristenciaP();
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioVacio() {
        System.out.println("verificarPrecioVacio");
        String precio = "";
        ServicioController instance = new ServicioController();
        String expResult = "Precio no puede quedar vacio";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioMorePoints() {
        System.out.println("verificarPrecioMorePoints");
        String precio = "1.1.1";
        ServicioController instance = new ServicioController();
        String expResult = "Precio no valido";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioEndPoint() {
        System.out.println("verificarPrecioEndPoint");
        String precio = "1.1.";
        ServicioController instance = new ServicioController();
        String expResult = "Precio no valido";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioStartPoint() {
        System.out.println("verificarPrecioStartPoint");
        String precio = ".1.1";
        ServicioController instance = new ServicioController();
        String expResult = "Precio no valido";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioStartEndPoint() {
        System.out.println("verificarPrecioStartEndPoint");
        String precio = ".";
        ServicioController instance = new ServicioController();
        String expResult = "Precio no valido";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of verificarPrecio method, of class ServicioController.
     */
    @Test
    public void testVerificarPrecioTrue() {
        System.out.println("verificarPrecioTrue");
        String precio = "1.1";
        ServicioController instance = new ServicioController();
        String expResult = "OK";
        String result = instance.verificarPrecio(precio);
        assertEquals(expResult, result);
    }

}
