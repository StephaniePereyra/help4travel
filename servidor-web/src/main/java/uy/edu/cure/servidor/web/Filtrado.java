/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class Filtrado {

    private String tipo;
    private String nombre;
    private String proveedor;
    private ServicioWSImplService servicioWSImplService;
    private ServicioWS portServicio;
    private PromocionWSImplService promocionWSImplService;
    private PromocionWS portPromocion;
    
    private Converter convertidor;
    
    public Filtrado(String tipo, String nombre, String proveedor) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Filtrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServicio = servicioWSImplService.getServicioWSImplPort();
        try {            
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Filtrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        portPromocion = promocionWSImplService.getPromocionWSImplPort();
        convertidor = new Converter();
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
            return convertidor.convertirServicio(portServicio.obtenerServicioWS(nombre, proveedor)).getPrecio();
        }
        return convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(nombre, proveedor)).getPrecioTotal();
    }

    public String getServicioDescripcion() {
        String descripcion = "";
        if (tipo.equals("Servicio")) {
            descripcion = convertidor.convertirServicio(portServicio.obtenerServicioWS(nombre, proveedor)).getDescripcion();
        }
        return descripcion;
    }

    public List<String> getServicioCategorias() {
        List<String> categorias = new ArrayList<>();
        Servicio servicioAux = convertidor.convertirServicio(portServicio.obtenerServicioWS(nombre, proveedor));
        if (tipo.equals("Servicio")) {
            Iterator<Categoria> iteratorCategorias = servicioAux.getCategorias().iterator();
            while (iteratorCategorias.hasNext()) {
                Categoria categoriaAux = iteratorCategorias.next();
                categorias.add(categoriaAux.getNombre());
            }
        }
        return categorias;
    }

    public String getServicioLineCategorias() {
        String categorias = "";
        Servicio servicioAux = convertidor.convertirServicio(portServicio.obtenerServicioWS(nombre, proveedor));
        if (tipo.equals("Servicio")) {
            Iterator<Categoria> iteratorCategorias = servicioAux.getCategorias().iterator();
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
            Servicio servicio = convertidor.convertirServicio(portServicio.obtenerServicioWS(nombre, proveedor));
            if (servicio.getDestino() != null) {
                destinos = servicio.getOrigen().getNombre() + " - " + servicio.getDestino().getNombre();
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
            descuento = convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(nombre, proveedor)).getDescuento();
        }
        return descuento;
    }

    public List<String> getPromocionServicios() {
        List<String> servicios = new ArrayList<>();
        Promocion promocionAux = convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(nombre, proveedor));
        if (tipo.equals("Promocion")) {
            Iterator<Servicio> iteratorServicios = promocionAux.getServicios().iterator();
            while (iteratorServicios.hasNext()) {
                Servicio servicioAux = iteratorServicios.next();
                servicios.add(servicioAux.getNombre());
            }
        }
        return servicios;
    }

    public String getPromocionLineServicios() {
        String servicios = "";
        Promocion promocionAux = convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(nombre, proveedor));
        if (tipo.equals("Promocion")) {
            Iterator<Servicio> iteratorServicios = promocionAux.getServicios().iterator();
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
