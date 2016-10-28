/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.*;
import uy.edu.cure.servidor.central.lib.*;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class Filtrado {
    
    @Jeringa(value = "promocioncontroller")
    private PromocionControllerImpl promocionController;
    @Jeringa(value = "serviciocontroller")
    private ServicioControllerImpl servicioController;
    private String tipo;
    private String nombre;
    private String proveedor;

    public Filtrado(String tipo, String nombre, String proveedor) {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        this.tipo = tipo;
        this.nombre = nombre;
        this.proveedor = proveedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public double getPrecio() {
        if (tipo.equals("Servicio")) {
            return servicioController.obtenerServicio(nombre, proveedor).getPrecio();
        }
        return promocionController.obtenerPromocion(nombre, proveedor).getPrecioTotal();
    }
    
    public String getServicioDescripcion() {
        String descripcion = "";
        if (tipo.equals("Servicio")) {
            descripcion = servicioController.obtenerServicio(nombre, proveedor).getDescripcion();
        }
        return descripcion;
    }
    
    public List<String> getServicioCategorias() {
        List<String> categorias = new ArrayList<>();
        if (tipo.equals("Servicio")){
            Iterator<Categoria> iteratorCategorias = servicioController.obtenerServicio(nombre, proveedor).getCategorias().iterator();
            while (iteratorCategorias.hasNext()) {
                Categoria categoriaAux = iteratorCategorias.next();
                categorias.add(categoriaAux.getNombre());
            }
        }
        return categorias;
    }
    
    public String getServicioLineCategorias() {
        String categorias = "";
        if (tipo.equals("Servicio")){
            Iterator<Categoria> iteratorCategorias = servicioController.obtenerServicio(nombre, proveedor).getCategorias().iterator();
            while (iteratorCategorias.hasNext()) {
                Categoria categoriaAux = iteratorCategorias.next();
                categorias = categorias + categoriaAux.getNombre();
                if (iteratorCategorias.hasNext()) {
                    categorias = categorias + " | ";
                }
            }
        }
        return categorias;
    }
    
    public String getServicioDestinos() {
        String destinos = "";
        if (tipo.equals("Servicio")) {
            Servicio servicio = servicioController.obtenerServicio(nombre, proveedor);
            if (servicio.getDestino() != null) {
                destinos = servicio.getOrigen().getNombre()+" - "+servicio.getDestino().getNombre();
            } else {
                destinos = servicio.getOrigen().getNombre();
            }
        }
        return destinos;
    }
    
    /*
    public List<String> getServiciosImagenes() {
        ...
    }
    */
    
    public int getPromocionDescuento() {
        int descuento = 0;
        if (tipo.equals("Promocion")) {
            descuento = promocionController.obtenerPromocion(nombre, proveedor).getDescuento();
        }
        return descuento;
    }
    
    public List<String> getPromocionServicios() {
        List<String> servicios = new ArrayList<>();
        if (tipo.equals("Promocion")) {
            Iterator<Servicio> iteratorServicios = promocionController.obtenerPromocion(nombre, proveedor).getServicios().iterator();
            while (iteratorServicios.hasNext()) {
                Servicio servicioAux = iteratorServicios.next();
                servicios.add(servicioAux.getNombre());
            }
        }
        return servicios;
    }
    
    public String getPromocionLineServicios() {
        String servicios = "";
        if (tipo.equals("Promocion")) {
            Iterator<Servicio> iteratorServicios = promocionController.obtenerPromocion(nombre, proveedor).getServicios().iterator();
            while (iteratorServicios.hasNext()) {
                Servicio servicioAux = iteratorServicios.next();
                servicios = servicios + servicioAux.getNombre();
                if (iteratorServicios.hasNext()) {
                    servicios = servicios + " | ";
                }
            }
        }
        return servicios;
    }

}
