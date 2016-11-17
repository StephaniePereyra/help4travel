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
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.PromocionControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;

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
    private PromocionWSImplService promocionWSImplService;
    private PromocionWS portPromocion;
    private Converter convertidor;

    public VerInfoPromocion() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        try {            
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerInfoPromocion.class.getName()).log(Level.SEVERE, null, ex);
        }
        PromocionWS portPromocion = promocionWSImplService.getPromocionWSImplPort();
        convertidor = new Converter();
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
        List<uy.edu.cure.servidor.central.soap.client.Promocion> auxiliar = portPromocion.obtenerTodasPromociones();
        for(int i=0;i<auxiliar.size();i++){
            promociones.add(convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(nombre, proveedor)));
        }
        Iterator<Promocion> iteratorPromocion = promociones.iterator();
        while (iteratorPromocion.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromocion.next();
            Filtrado filtrado = new Filtrado("Promocion", promocionAuxiliar.getNombre(), promocionAuxiliar.getProveedor().getNickName());
            listaPromocionesAux.add(filtrado);
        }
        return listaPromocionesAux;
    }
    
}
