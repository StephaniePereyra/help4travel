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
public class VerInfoPromocion {
    
    @Jeringa(value = "promocioncontroller")
    private PromocionControllerImpl promocionController;
    private String nombre;
    private String proveedor;
    
    public VerInfoPromocion() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getThatPromo() {
        FacesContext facec = FacesContext.getCurrentInstance();
        Map<String,String> params = facec.getExternalContext().getRequestParameterMap();
        nombre =  params.get("nombrePromocion"); 
        proveedor =  params.get("proveedorPromocion");
        return "InfoPromocion";
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
        return promocionController.obtenerPromocion(nombre, proveedor).getPrecioTotal();
    }
    
    public int getDescuento() {
        return promocionController.obtenerPromocion(nombre, proveedor).getDescuento();
    }
    
    public List<String> getServicios() {
        List<String> servicios = new ArrayList<>();
        Iterator<Servicio> iteratorServicios = promocionController.obtenerPromocion(nombre, proveedor).getServicios().iterator();
        while(iteratorServicios.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicios.next();
            servicios.add(servicioAuxiliar.getNombre());
        }
        return servicios;
    }
    
    public List<Filtrado> listadoPromociones() {
        List<Promocion> promociones = new ArrayList<>();
        List<Filtrado> listaPromocionesAux = new ArrayList<>();
        promociones = promocionController.obtenerTodasPromociones();
        Iterator<Promocion> iteratorPromocion = promociones.iterator();
        while (iteratorPromocion.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromocion.next();
            Filtrado filtrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName());
            listaPromocionesAux.add(filtrado);
        }
        return listaPromocionesAux;
    }
    
}
