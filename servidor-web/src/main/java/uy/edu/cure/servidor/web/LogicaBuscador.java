/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.*;
import uy.edu.cure.servidor.central.lib.*;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
@ManagedBean
@SessionScoped
public class LogicaBuscador {

    @Jeringa(value = "promocioncontroller")
    private PromocionControllerImpl promocionController;
    @Jeringa(value = "serviciocontroller")
    private ServicioControllerImpl servicioController;
    private int tipoDeBusqueda;
    private String wanted;
    private List<Filtrado> serviciosFiltrados;

    public LogicaBuscador() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
        Iterator<Servicio> iteratorServicios = servicioController.obtenerTodosServicios().iterator();
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
        Iterator<Promocion> iteratorPromociones = promocionController.obtenerTodasPromociones().iterator();
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
            public int compare(Filtrado f1, Filtrado f2) {
                if (tipoDeBusqueda == 1) {
                    return new Double(f1.getPrecio()).compareTo(f2.getPrecio());
                }
                return f1.getNombre().compareTo(f2.getNombre());
            }
        });
        return "ResultadoBusqueda.xhtml";
    }
    
    public void niceHarcoding() {
        CategoriaControllerImpl categoriaController = new CategoriaControllerImpl();
        UsuarioControllerImpl usuarioController = new UsuarioControllerImpl();
        PaisControllerImpl paisController = new PaisControllerImpl();
        CiudadControllerImpl ciudadController = new CiudadControllerImpl();
        // -- Paises --
        paisController.crearPais("Argentina");
        paisController.crearPais("Brasil");
        paisController.crearPais("Uruguay");
        // -- Ciudades --
        ciudadController.crearCiudad("Buenos Aires", "Argentina");
        ciudadController.crearCiudad("Cordoba", "Argentina");
        ciudadController.crearCiudad("Rosario", "Argentina");
        ciudadController.crearCiudad("Santa Fe", "Argentina");
        ciudadController.crearCiudad("Brasilia", "Brasil");
        ciudadController.crearCiudad("Fortaleza", "Brasil");
        ciudadController.crearCiudad("Rio de Janeiro", "Brasil");
        ciudadController.crearCiudad("Sao Paulo", "Brasil");
        ciudadController.crearCiudad("Canelones", "Uruguay");
        ciudadController.crearCiudad("Maldonado", "Uruguay");
        ciudadController.crearCiudad("Montevideo", "Uruguay");
        ciudadController.crearCiudad("Rocha", "Uruguay");
        // -- Clientes --
        usuarioController.crearCliente("Cliente1", "nombre1", "apellido1", "correo@correo1", 10, 10, 1995, "", "password1", "password1");
        usuarioController.crearCliente("Cliente2", "nombre2", "apellido2", "correo@correo2", 29, 2, 1996, "", "password2", "password2");
        // -- Proveedores --
        usuarioController.crearProveedor("Proveedor1", "nombre3", "apellido3",
                "correo@correo3", 22, 10, 2000, "empresa1", "link1", "", "password3", "password3");
        usuarioController.crearProveedor("Proveedor2", "nombre4", "apellido4",
                "correo@correo4", 4, 5, 1999, "empresa2", "link2", "", "password4", "password4");
        usuarioController.crearProveedor("Proveedor3", "nombre5", "apellido5",
                "correo@correo5", 16, 6, 1997, "empresa3", "link3", "", "password5", "password5");
        usuarioController.crearProveedor("Proveedor4", "nombre6", "apellido6",
                "correo@correo6", 11, 9, 1997, "empresa4", "link4", "", "password6", "password6");
        // -- Categorias --
        categoriaController.darAltaCategoria("Vuelos", "");
        categoriaController.darAltaCategoria("Empresa", "Vuelos");
        categoriaController.darAltaCategoria("Iberian", "Empresa");
        categoriaController.darAltaCategoria("AmericaAirlines", "Empresa");
        categoriaController.darAltaCategoria("Tipo", "Vuelos");
        categoriaController.darAltaCategoria("LowCost", "Tipo");
        categoriaController.darAltaCategoria("Autos", "");
        categoriaController.darAltaCategoria("Ubicacion", "Autos");
        categoriaController.darAltaCategoria("Playa", "Ubicacion");
        categoriaController.darAltaCategoria("Hoteleria", "");
        categoriaController.darAltaCategoria("3 *", "Hoteleria");
        categoriaController.darAltaCategoria("4 *", "Hoteleria");
        // -- Servicios --
        List<String> categorias = new ArrayList<String>();
        categorias.add("3 *");
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("imagen1");
        servicioController.crearServicio("Motel", "Rise, from the blood of your heroes", 100, "Rio de Janeiro", "<null>", categorias, imagenes, "Proveedor1");
        categorias.clear();categorias.add("4 *");
        servicioController.crearServicio("Hotel", "You were the ones who refused to surrender", 150, "Montevideo", "<null>", categorias, imagenes, "Proveedor2");
        categorias.clear();categorias.add("Playa");
        servicioController.crearServicio("RentaCar", "The three, rather die than to flee, know that your memory", 75, "Buenos Aires", "<null>", categorias, imagenes, "Proveedor3");
        categorias.clear();categorias.add("Iberian");categorias.add("LowCost");
        servicioController.crearServicio("Fly", "Will be sung for a century", 250, "Canelones", "Buenos Aires", categorias, imagenes, "Proveedor3");
        categorias.clear();categorias.add("Iberian");
        servicioController.crearServicio("FlyTwo", "Three took the blow, while impressing their foe", 300, "Canelones", "Buenos Aires", categorias, imagenes, "Proveedor3");
        categorias.clear();categorias.add("Playa");
        servicioController.crearServicio("Driven", "Throwing dice with their lives as they’re paying the price", 70, "Montevideo", "<null>", categorias, imagenes, "Proveedor2");
        categorias.clear();categorias.add("AmericaAirlines");
        servicioController.crearServicio("Sky", "Sent to raise hell, hear the toll of the bell", 230, "Brasilia", "Maldonado", categorias, imagenes, "Proveedor4");
        categorias.clear();categorias.add("AmericaAirlines"); 
        servicioController.crearServicio("SkyThree", "It is calling for you as the Wehrmacht devised", 210, "Cordoba", "Sao Paulo", categorias, imagenes, "Proveedor4");
        categorias.clear();categorias.add("AmericaAirlines");categorias.add("LowCost");
        servicioController.crearServicio("SkyTwo", "Sent over seas to be cast into fire", 195, "Fortaleza", "Rosario", categorias, imagenes, "Proveedor4");
        categorias.clear();categorias.add("AmericaAirlines");categorias.add("LowCost");
        servicioController.crearServicio("Sky4", "Fought for a purpose with pride and desire", 200, "Canelones", "Santa Fe", categorias, imagenes, "Proveedor4");
        // -- Promociones --
        imagenes.clear();imagenes.add("Hotel");imagenes.add("Driven");
        promocionController.crearPromocion("Hotel+Driven", 20, "Proveedor2", imagenes);
        imagenes.clear();imagenes.add("Fly");imagenes.add("RentaCar");
        promocionController.crearPromocion("Vuelo+Auto", 25, "Proveedor3", imagenes);
        imagenes.clear();imagenes.add("Sky");imagenes.add("Sky4");
        promocionController.crearPromocion("TwoViajes", 10, "Proveedor4", imagenes);
    }

}
