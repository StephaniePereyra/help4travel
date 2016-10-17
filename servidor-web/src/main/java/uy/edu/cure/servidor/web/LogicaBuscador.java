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
            if (servicioAuxiliar.getNombre().equals(wanted)) {
                Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName(), servicioAuxiliar.getPrecio());
                serviciosFiltrados.add(servicioFiltrado);
            } else // Busqueda por contenido de descripcion del servicio
             if (servicioAuxiliar.getDescripcion().contains(wanted)) {
                    Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName(), servicioAuxiliar.getPrecio());
                    serviciosFiltrados.add(servicioFiltrado);
                } else {
                    Iterator<Categoria> iteratorCategorias = servicioAuxiliar.getCategorias().iterator();
                    while (iteratorCategorias.hasNext()) {
                        Categoria categoriaAuxiliar = iteratorCategorias.next();
                        // Busqueda por categorias del servicio
                        if (categoriaAuxiliar.getNombre().equals(wanted)) {
                            Filtrado servicioFiltrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName(), servicioAuxiliar.getPrecio());
                            serviciosFiltrados.add(servicioFiltrado);
                        }
                    }
                }
        }
        Iterator<Promocion> iteratorPromociones = promocionController.obtenerTodasPromociones().iterator();
        while (iteratorPromociones.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromociones.next();
            // Busqueda por nombre de promocion
            if (promocionAuxiliar.getNombre().equals(wanted)) {
                Filtrado servicioFiltrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName(), promocionAuxiliar.getPrecioTotal());
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
                    Filtrado servicioFiltrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName(), promocionAuxiliar.getPrecioTotal());
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
        paisController.crearPais("Pais1");
        ciudadController.crearCiudad("Ciudad1", "Pais1");
        ciudadController.crearCiudad("Ciudad2", "Pais1");
        usuarioController.crearProveedor("Proveedor1", "nombre1", "apellido1",
                "correo@correo1", 10, 10, 2000, "empresa1", "link1", "ruta", "password", "password");
        usuarioController.crearProveedor("Proveedor2", "nombre2", "apellido2",
                "correo@correo2", 10, 10, 2000, "empresa2", "link2", "ruta2", "password2", "password2");
        usuarioController.crearProveedor("Proveedor3", "nombre3", "apellido3",
                "correo@correo3", 10, 10, 2000, "empresa3", "link3", "ruta3", "password3", "password3");
        categoriaController.darAltaCategoria("categoria1", "");
        categoriaController.darAltaCategoria("categoria2", "");
        categoriaController.darAltaCategoria("categoria3", "");
        categoriaController.darAltaCategoria("categoria1.1", "categoria1");
        categoriaController.darAltaCategoria("categoria2.1", "categoria2");
        List<String> categorias = new ArrayList<String>();
        categorias.add("categoria1.1");
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("imagen1");
        servicioController.crearServicio("bservicio1", "Rise, from the blood of your heroes", 20, "Ciudad1", "<null>", categorias, imagenes, "Proveedor1");
        categorias.clear();categorias.add("categoria2.1");
        servicioController.crearServicio("aservicio2", "You were the ones who refused to surrender", 10, "Ciudad2", "<null>", categorias, imagenes, "Proveedor2");
        categorias.add("categoria3");
        servicioController.crearServicio("dservicio3", "The three, rather die than to flee, know that your memory", 40, "Ciudad1", "Ciudad2", categorias, imagenes, "Proveedor3");
        categorias.clear();categorias.add("categoria1.1");categorias.add("categoria3");
        servicioController.crearServicio("cservicio4", "Will be sung for a century", 30, "Ciudad2", "Ciudad1", categorias, imagenes, "Proveedor1");
        imagenes.clear();imagenes.add("bservicio1");imagenes.add("cservicio4");
        promocionController.crearPromocion("bpromocion1", 20, "Proveedor1", imagenes);
    }

}
