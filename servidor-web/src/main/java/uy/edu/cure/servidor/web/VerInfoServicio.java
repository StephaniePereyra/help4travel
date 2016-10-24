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
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class VerInfoServicio {

    @Jeringa(value = "serviciocontroller")
    private ServicioControllerImpl servicioController;
    private String nombre;
    private String proveedor;

    public VerInfoServicio() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getThatService() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        nombre =  params.get("nombreServicio"); 
        proveedor =  params.get("proveedorServicio");
        return "InfoServicio.xhtml";
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

    public String getDescripcion() {
        return servicioController.obtenerServicio(nombre, proveedor).getDescripcion();
    }

    public double getPrecio() {
        return servicioController.obtenerServicio(nombre, proveedor).getPrecio();
    }

    public String getDestinos() {
        Servicio servicio = servicioController.obtenerServicio(nombre, proveedor);
        if (servicio.getDestino() == null) {
            return servicio.getOrigen().getNombre();
        }
        return servicio.getOrigen().getNombre() + " - " + servicio.getDestino().getNombre();
    }

    public List<String> getCategorias() {
        List<String> categorias = new ArrayList<>();
        Servicio servicio = servicioController.obtenerServicio(nombre, proveedor);
        Iterator<Categoria> iteratorCategorias = servicio.getCategorias().iterator();
        while (iteratorCategorias.hasNext()) {
            Categoria categoria = iteratorCategorias.next();
            categorias.add(categoria.getNombre());
        }
        return categorias;
    }
    
    public List<Filtrado> listadoServicios() {
        List<Servicio> servicios = new ArrayList<>();
        List<Filtrado> listaServiciosAux = new ArrayList<>();
        servicios = servicioController.obtenerTodosServicios();
        Iterator<Servicio> iteratorServicio = servicios.iterator();
        while (iteratorServicio.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicio.next();
            Filtrado filtrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName(), 0);
            listaServiciosAux.add(filtrado);
        }
        return listaServiciosAux;
    }

}
