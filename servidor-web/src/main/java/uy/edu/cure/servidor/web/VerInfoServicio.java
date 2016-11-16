/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

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
        FacesContext facec = FacesContext.getCurrentInstance();
        Map<String,String> params = facec.getExternalContext().getRequestParameterMap();
        nombre =  params.get("nombreServicio"); 
        proveedor =  params.get("proveedorServicio");
        return "InfoServicio";
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
    
    public List<String> getImagenes() {
        Servicio servicio = servicioController.obtenerServicio(nombre, proveedor);
        List<String> imagenes = servicio.getImagenes();
        return imagenes;
    }
     
    public List<Filtrado> listadoServicios() {
        
        ServicioWSImplService servicioWSImplService = null;
        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerInfoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServicioWS portServicio = servicioWSImplService.getServicioWSImplPort();

        List<Servicio> servicios = new ArrayList<>();
        List<Filtrado> listaServiciosAux = new ArrayList<>();
        
        Converter convertidor = new Converter();
        List<uy.edu.cure.servidor.central.soap.client.Servicio> aux = portServicio.obtenerTodosServiciosWS();
        for(int i=0;i<aux.size();i++){
            servicios.add(convertidor.convertirServicio(aux.get(i)));
        }
        
        Iterator<Servicio> iteratorServicio = servicios.iterator();
        while (iteratorServicio.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicio.next();
            Filtrado filtrado = new Filtrado("Servicio", servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName());
            listaServiciosAux.add(filtrado);
        }
        return listaServiciosAux;
    }

}
