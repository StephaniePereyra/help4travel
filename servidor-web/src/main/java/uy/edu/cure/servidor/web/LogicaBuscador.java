/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.soap.client.CategoriaWS;
import uy.edu.cure.servidor.central.soap.client.CategoriaWSImplService;
import uy.edu.cure.servidor.central.soap.client.CiudadWS;
import uy.edu.cure.servidor.central.soap.client.CiudadWSImplService;
import uy.edu.cure.servidor.central.soap.client.FacturaWS;
import uy.edu.cure.servidor.central.soap.client.FacturaWSImplService;
import uy.edu.cure.servidor.central.soap.client.ItemsFactura;
import uy.edu.cure.servidor.central.soap.client.PaisWS;
import uy.edu.cure.servidor.central.soap.client.PaisWSImplService;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
@ManagedBean
@SessionScoped
public class LogicaBuscador {

    private int tipoDeBusqueda;
    private String wanted;
    private List<Filtrado> serviciosFiltrados;

    public LogicaBuscador() {
        serviciosFiltrados = new ArrayList<Filtrado>();
    }

    public int getTipoDeBusqueda() {
        return tipoDeBusqueda;
    }

    public void setTipoDeBusqueda(int tipoDeBusqueda) {
        this.tipoDeBusqueda = tipoDeBusqueda;
    }

    public String getWanted() {
        return wanted;
    }

    public void setWanted(String wanted) {
        this.wanted = wanted;
    }

    public List<Filtrado> getServiciosFiltrados() {
        return serviciosFiltrados;
    }

    public void setServiciosFiltrados(Filtrado servicioFiltrado) {
        serviciosFiltrados.add(servicioFiltrado);
    }

    public String cantidadServicios() {
        String resultado = "Se encontraron " + serviciosFiltrados.size() + " servicios";
        return resultado;
    }

    public String actionBuscar() {
        serviciosFiltrados.clear();
        ServicioWSImplService servicioWSImplService = null;
        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(LogicaBuscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServicioWS portServicio = servicioWSImplService.getServicioWSImplPort();
        Converter convertidor = new Converter();
        List<Servicio> serviciosAuxiliares = new ArrayList();
        List<uy.edu.cure.servidor.central.soap.client.Servicio> aux = portServicio.obtenerTodosServiciosWS();
        for(int i=0;i<aux.size();i++){
            serviciosAuxiliares.add(convertidor.convertirServicio(portServicio.obtenerTodosServiciosWS().get(i)));
        }
        Iterator<Servicio> iteratorServicios = serviciosAuxiliares.iterator();
        while (iteratorServicios.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicios.next();
            // Busqueda por nombre del servicio
            if (servicioAuxiliar.getNombre().toLowerCase().contains(wanted.toLowerCase())) {
                Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName());
                serviciosFiltrados.add(servicioFiltrado);
            } else // Busqueda por contenido de descripcion del servicio
             if (servicioAuxiliar.getDescripcion().toLowerCase().contains(wanted.toLowerCase())) {
                    Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName());
                    serviciosFiltrados.add(servicioFiltrado);
                } else {
                    Iterator<Categoria> iteratorCategorias = servicioAuxiliar.getCategorias().iterator();
                    while (iteratorCategorias.hasNext()) {
                        Categoria categoriaAuxiliar = iteratorCategorias.next();
                        // Busqueda por categorias del servicio
                        if (categoriaAuxiliar.getNombre().toLowerCase().contains(wanted.toLowerCase())) {
                            Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName());
                            serviciosFiltrados.add(servicioFiltrado);
                        }
                    }
                }
        }
        PromocionWSImplService promocionWSImplService = null;
        try {            
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(LogicaBuscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        PromocionWS portPromocion = promocionWSImplService.getPromocionWSImplPort();

        List<Promocion> promocionesAux = new ArrayList();
        List<uy.edu.cure.servidor.central.soap.client.Promocion> auxiliarPromos = portPromocion.obtenerTodasPromociones();
        for(int i=0;i<auxiliarPromos.size();i++){
            promocionesAux.add(convertidor.convertirPromocion(auxiliarPromos.get(i)));
        }
        Iterator<Promocion> iteratorPromociones = promocionesAux.iterator();
        while (iteratorPromociones.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromociones.next();
            // Busqueda por nombre de promocion
            if (promocionAuxiliar.getNombre().toLowerCase().contains(wanted.toLowerCase())) {
                Filtrado servicioFiltrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName());
                serviciosFiltrados.add(servicioFiltrado);
            } else {
                boolean tieneServicio = false;
                Iterator<Filtrado> iteratorServiciosFiltrados = serviciosFiltrados.iterator();
                while (iteratorServiciosFiltrados.hasNext()) {
                    Filtrado servicioFiltradoAuxiliar = iteratorServiciosFiltrados.next();
                    Iterator<Servicio> iteratorServiciosDePromocion = promocionAuxiliar.getServicios().iterator();
                    while (iteratorServiciosDePromocion.hasNext() && !tieneServicio) {
                        Servicio servicioDePromocionAuxiliar = iteratorServiciosDePromocion.next();
                        // Si algunos de los servicios filtrados esta en la promocion, esa promocion es agregada
                        if (servicioFiltradoAuxiliar.getNombre().equals(servicioDePromocionAuxiliar.getNombre())
                                && servicioFiltradoAuxiliar.getProveedor().equals(servicioDePromocionAuxiliar.getProveedor().getNickName())) {
                            tieneServicio = true;
                        }
                    }
                }
                if (tieneServicio) {
                    Filtrado servicioFiltrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName());
                    serviciosFiltrados.add(servicioFiltrado);
                }
            }
        }
        return "ResultadoBusqueda.xhtml";
    }

    public String ordenar() {
        Collections.sort(serviciosFiltrados, new Comparator<Filtrado>() {
            @Override
            public int compare(Filtrado filtrado1, Filtrado filtrado2) {
                if (tipoDeBusqueda == 1) {
                    return new Double(filtrado1.getPrecio()).compareTo(filtrado2.getPrecio());
                }
                return filtrado1.getNombre().compareTo(filtrado2.getNombre());
            }
        });
        return "ResultadoBusqueda.xhtml";
    }
    
    public void niceHarcoding() {
        
//Prueba jpa-----------------------------------------------------------------------------------------------
        
        FacturaWSImplService facturaWSImplService = null;
    
        try {
            facturaWSImplService = new FacturaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/FacturaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(LogicaBuscador.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacturaWS portFactura = facturaWSImplService.getFacturaWSImplPort();
        
        ItemsFactura item0 = new ItemsFactura();
        ItemsFactura item1= new ItemsFactura();
        ItemsFactura item2= new ItemsFactura();
        List<ItemsFactura> items = new ArrayList();
        
        item0.setNombreItem("Hotel0");
        item0.setNickProveedor("Proveedor0");
        item0.setCantidadItem(1);
        item0.setTipoItem("servicio");
                
        item1.setNombreItem("Hotel1");
        item1.setNickProveedor("Proveedor1");
        item1.setCantidadItem(2);
        item1.setTipoItem("servicio");
                
        item2.setNombreItem("Hotel2");
        item2.setNickProveedor("Proveedor2");
        item2.setCantidadItem(3);
        item2.setTipoItem("promocion");
        
        items.add(item0);
        items.add(item1);
        items.add(item2);
        
        portFactura.persistirFactura("ClienteX", 1, items);
        
        //Se realiza la query usar debugg para ver contenido
        Factura facturaAux;
        String url;
        url = "http://localhost:8080/servidor-central-webapp/rest/api/ObtenerFactura/traer/1";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse response = null; 
        String result = null;
        try {
            response = client.execute(request);
            result = getStringFromInputStream(response.getEntity().getContent());
            facturaAux = mapper.readValue(result, Factura.class);
        } catch (IOException ex) {
            Logger.getLogger(LogicaBuscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //-------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------
        
        UsuarioWSImplService usuarioWSImplService = null;
        PaisWSImplService paisWSImplService = null;
        CiudadWSImplService ciudadWSImplService = null;
        CategoriaWSImplService categoriaWSImplService = null;
        ServicioWSImplService servicioWSImplService = null;
        PromocionWSImplService promocionWSImplService = null;
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
            paisWSImplService = new PaisWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PaisWSImplService?wsdl"));
            ciudadWSImplService = new CiudadWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CiudadWSImplService?wsdl"));
            categoriaWSImplService = new CategoriaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CategoriaWSImplService?wsdl"));
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioWS portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        PaisWS portPais = paisWSImplService.getPaisWSImplPort();
        CiudadWS portCiudad = ciudadWSImplService.getCiudadWSImplPort();
        CategoriaWS portCategoria = categoriaWSImplService.getCategoriaWSImplPort();
        ServicioWS portServicio = servicioWSImplService.getServicioWSImplPort();
        PromocionWS portPromocion = promocionWSImplService.getPromocionWSImplPort();
        // -- Paises --
        portPais.crearPaisWS("Argentina");
        portPais.crearPaisWS("Brasil");
        portPais.crearPaisWS("Uruguay");
        // -- Ciudades --
        portCiudad.crearCiudadWS("Buenos Aires", "Argentina");
        portCiudad.crearCiudadWS("Cordoba", "Argentina");
        portCiudad.crearCiudadWS("Rosario", "Argentina");
        portCiudad.crearCiudadWS("Santa Fe", "Argentina");
        portCiudad.crearCiudadWS("Brasilia", "Brasil");
        portCiudad.crearCiudadWS("Fortaleza", "Brasil");
        portCiudad.crearCiudadWS("Rio de Janeiro", "Brasil");
        portCiudad.crearCiudadWS("Sao Paulo", "Brasil");
        portCiudad.crearCiudadWS("Canelones", "Uruguay");
        portCiudad.crearCiudadWS("Maldonado", "Uruguay");
        portCiudad.crearCiudadWS("Montevideo", "Uruguay");
        portCiudad.crearCiudadWS("Rocha", "Uruguay");
        // -- Clientes --

        portUsuario.crearClienteWS("Cliente1", "nombre1", "apellido1", "correo@correo1", 10, 10, 1995, "images/perfil/default.png", "password1", "password1");
        portUsuario.crearClienteWS("Cliente2", "nombre2", "apellido2", "correo@correo2", 29, 2, 1996, "images/perfil/default.png", "password2", "password2");    
        
        // -- Proveedores --
        
        portUsuario.crearProeveedorWS("Proveedor1", "nombre3", "apellido3",
                "correo@correo3", 22, 10, 2000, "empresa1", "http://www.starwoodhotels.com/whotels/index.html?language=en_US", "images/providers/1.jpg", "password3", "password3");
        portUsuario.crearProeveedorWS("Proveedor2", "nombre4", "apellido4",
                "correo@correo4", 4, 5, 1999, "empresa2", "http://www.lotushonoluluhotel.com/", "images/providers/2.jpg", "password4", "password4");
        portUsuario.crearProeveedorWS("Proveedor3", "nombre5", "apellido5",
                "correo@correo5", 16, 6, 1997, "empresa3", "http://roadstarinn.net/", "images/providers/3.png", "password5", "password5");
        portUsuario.crearProeveedorWS("Proveedor4", "nombre6", "apellido6",
                "correo@correo6", 11, 9, 1997, "empresa4", "http://lhabitationcerf.net76.net/", "images/providers/4.jpg", "password6", "password6");
        // -- Categorias --
        portCategoria.crearCategoriaWS("Vuelos", "");
        portCategoria.crearCategoriaWS("Empresa", "Vuelos");
        portCategoria.crearCategoriaWS("Iberian", "Empresa");
        portCategoria.crearCategoriaWS("AmericaAirlines", "Empresa");
        portCategoria.crearCategoriaWS("Tipo", "Vuelos");
        portCategoria.crearCategoriaWS("LowCost", "Tipo");
        portCategoria.crearCategoriaWS("Autos", "");
        portCategoria.crearCategoriaWS("Ubicacion", "Autos");
        portCategoria.crearCategoriaWS("Playa", "Ubicacion");
        portCategoria.crearCategoriaWS("Hoteleria", "");
        portCategoria.crearCategoriaWS("3 *", "Hoteleria");
        portCategoria.crearCategoriaWS("4 *", "Hoteleria");
        // -- Servicios --
        List<String> categorias = new ArrayList<String>();
        categorias.add("3 *");
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("images/services/motel.jpg");
        portServicio.crearServicioWS("Motel", "Rise, from the blood of your heroes", 100, "Rio de Janeiro", "<null>", categorias, imagenes, "Proveedor1");
        
        categorias.clear();categorias.add("4 *");
        imagenes.clear();imagenes.add("images/services/hotel1.jpg");imagenes.add("images/services/hotel2.jpg");
        portServicio.crearServicioWS("Hotel", "You were the ones who refused to surrender", 150, "Montevideo", "<null>", categorias, imagenes, "Proveedor2");
        
        categorias.clear();categorias.add("Playa");
        imagenes.clear();imagenes.add("images/services/auto1.jpg");imagenes.add("images/services/auto2.png");imagenes.add("images/services/auto3.jpg");
        portServicio.crearServicioWS("RentaCar", "The three, rather die than to flee, know that your memory", 75, "Buenos Aires", "<null>", categorias, imagenes, "Proveedor3");
        
        categorias.clear();categorias.add("Iberian");categorias.add("LowCost");
        imagenes.clear();imagenes.add("images/services/fly.jpg");
        portServicio.crearServicioWS("Fly", "Will be sung for a century", 250, "Canelones", "Buenos Aires", categorias, imagenes, "Proveedor3");
        
        categorias.clear();categorias.add("Iberian");
        imagenes.clear();imagenes.add("images/services/flytwo1.jpg");imagenes.add("images/services/flytwo2.jpg");
        portServicio.crearServicioWS("FlyTwo", "Three took the blow, while impressing their foe", 300, "Canelones", "Buenos Aires", categorias, imagenes, "Proveedor3");
        
        categorias.clear();categorias.add("Playa");
        imagenes.clear();imagenes.add("images/services/driven1.jpg");imagenes.add("images/services/driven2.jpg");imagenes.add("images/services/driven3.jpg");
        portServicio.crearServicioWS("Driven", "Throwing dice with their lives as they’re paying the price", 70, "Montevideo", "<null>", categorias, imagenes, "Proveedor2");
        
        categorias.clear();categorias.add("AmericaAirlines");
        imagenes.clear();imagenes.add("images/services/sky.jpg");
        portServicio.crearServicioWS("Sky", "Sent to raise hell, hear the toll of the bell", 230, "Brasilia", "Maldonado", categorias, imagenes, "Proveedor4");
        
        categorias.clear();categorias.add("AmericaAirlines");
        imagenes.clear();
        portServicio.crearServicioWS("SkyThree", "It is calling for you as the Wehrmacht devised", 210, "Cordoba", "Sao Paulo", categorias, imagenes, "Proveedor4");
        
        categorias.clear();categorias.add("AmericaAirlines");categorias.add("LowCost");
        imagenes.clear();imagenes.add("images/services/skytwo1.jpg");imagenes.add("images/services/skytwo2.jpg");
        portServicio.crearServicioWS("SkyTwo", "Sent over seas to be cast into fire", 195, "Fortaleza", "Rosario", categorias, imagenes, "Proveedor4");
        
        categorias.clear();categorias.add("AmericaAirlines");categorias.add("LowCost");
        imagenes.clear();imagenes.add("images/services/sky41.jpg");imagenes.add("images/services/sky42.jpg");imagenes.add("images/services/sky43.jpg");
        portServicio.crearServicioWS("Sky4", "Fought for a purpose with pride and desire", 200, "Canelones", "Santa Fe", categorias, imagenes, "Proveedor4");
        // -- Promociones --
        imagenes.clear();imagenes.add("Hotel");imagenes.add("Driven");
        portPromocion.crearPromocionWS("Hotel+Driven", 20, "Proveedor2", imagenes);
        imagenes.clear();imagenes.add("Fly");imagenes.add("RentaCar");
        portPromocion.crearPromocionWS("Vuelo+Auto", 25, "Proveedor3", imagenes);
        imagenes.clear();imagenes.add("Sky");imagenes.add("Sky4");
        portPromocion.crearPromocionWS("TwoViajes", 10, "Proveedor4", imagenes);
    }
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
