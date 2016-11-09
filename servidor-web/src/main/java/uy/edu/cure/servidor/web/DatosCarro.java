/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Oculta;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author SCN
 */
@ManagedBean
@ViewScoped
public class DatosCarro implements Serializable {

    @ManagedProperty(value = "#{datosSesion}")
    DatosSesion datosSesion;
    @Jeringa(value = "reservacontroller")
    private ReservaControllerImpl reservacontroller;
    private List<Servicio> servicios;
    private List<Promocion> promociones;
    private boolean carritoEmpty;
    private String nickSession;
    private double totalCarro;
    private List<Integer> cantidadServicios;
    private List<Integer> cantidadPromos;
    private List<Oculta> oculto;
    private String redirect;
    private Cliente clienteAux;

    public DatosCarro() {
    }

    @PostConstruct
    public void cargarArray() {
        if (datosSesion.isLoged()) {
            servicios = new ArrayList();
            promociones = new ArrayList();
            cantidadServicios = new ArrayList();
            cantidadPromos = new ArrayList();
            oculto = new ArrayList();
            Oculta ocultoObj = new Oculta();
            oculto.add(ocultoObj);
            nickSession = datosSesion.getNickName();
            clienteAux = restGetCliente();
            servicios = clienteAux.getCarrito().getServicios();
            promociones = clienteAux.getCarrito().getPromociones();
            cantidadServicios = clienteAux.getCarrito().getCantidadServicios();
            cantidadPromos = clienteAux.getCarrito().getCantidadPromociones();
            totalCarro = clienteAux.getCarrito().getPrecio();

            if (servicios.isEmpty() && promociones.isEmpty()) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("CarroEmpty.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("LogIn.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarServicio(Servicio servicio) {

        int index = servicios.indexOf(servicio);
        servicios.remove(servicio);   
        totalCarro = totalCarro - (servicio.getPrecio() * clienteAux.getCarrito().getCantidadServicios().get(index));
        clienteAux.getCarrito().setPrecio(totalCarro);
        clienteAux.getCarrito().getCantidadServicios().remove(index);
        clienteAux.getCarrito().getServicios().remove(servicio);

        if (servicios.isEmpty() && promociones.isEmpty()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("CarroEmpty.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarPromo(Promocion promocion) {

        int index = promociones.indexOf(promocion);
        promociones.remove(promocion);  
        totalCarro = totalCarro - (promocion.getPrecioTotal() * clienteAux.getCarrito().getCantidadPromociones().get(index));
        clienteAux.getCarrito().setPrecio(totalCarro);
        clienteAux.getCarrito().getCantidadPromociones().remove(index);
        clienteAux.getCarrito().getPromociones().remove(promocion);

        if (servicios.isEmpty() && promociones.isEmpty()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("CarroEmpty.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int cantidadServ(Servicio servicio) {
        int index = servicios.indexOf(servicio);          
        return clienteAux.getCarrito().getCantidadServicios().get(index);
    }

    public int cantidadPromo(Promocion promocion) {
        int index = promociones.indexOf(promocion);
        return clienteAux.getCarrito().getCantidadPromociones().get(index);
    }

    public String confirmarCarro() {
        reservacontroller.agregarCarro(clienteAux);
        return "/index";
    }
    
    private Cliente restGetCliente(){
        
        Cliente clienteAuxiliar= new Cliente();          
        String url = "http://localhost:8080/servidor-central-webapp/rest/api/ObtenerCliente/" + nickSession;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.addHeader("content-type", "application/json");
        try {
            HttpResponse response = client.execute(get);
            ObjectMapper map = new ObjectMapper();
            clienteAuxiliar = map.readValue(response.getEntity().getContent(), Cliente.class);

        } catch (IOException ex) {
            Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clienteAuxiliar;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public boolean isCarritoEmpty() {
        return carritoEmpty;
    }

    public void setCarritoEmpty(boolean carritoEmpty) {
        this.carritoEmpty = carritoEmpty;
    }

    public String getNickSession() {
        return nickSession;
    }

    public void setNickSession(String nickSession) {
        this.nickSession = nickSession;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public double getTotalCarro() {
        return totalCarro;
    }

    public void setTotalCarro(double totalCarro) {
        this.totalCarro = totalCarro;
    }

    public List<Integer> getCantidadServicios() {
        return cantidadServicios;
    }

    public void setCantidadServicios(List<Integer> cantidadServicios) {
        this.cantidadServicios = cantidadServicios;
    }

    public List<Integer> getCantidadPromos() {
        return cantidadPromos;
    }

    public void setCantidadPromos(List<Integer> cantidadPromos) {
        this.cantidadPromos = cantidadPromos;
    }

    public List<Oculta> getOculto() {
        return oculto;
    }

    public void setOculto(List<Oculta> oculto) {
        this.oculto = oculto;
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    public Cliente getClienteAux() {
        return clienteAux;
    }

    public void setClienteAux(Cliente clienteAux) {
        this.clienteAux = clienteAux;
    }
    
    
}
