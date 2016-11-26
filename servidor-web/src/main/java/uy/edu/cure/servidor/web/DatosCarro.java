/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import uy.edu.cure.servidor.central.dto.Oculta;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */
@ManagedBean
@ViewScoped
public class DatosCarro implements Serializable {

    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;
    private List<Servicio> servicios;
    private List<Promocion> promociones;
    private boolean carritoEmpty;
    private String nickSession;
    private double totalCarro;
    private List<Integer> cantidadServicios;
    private List<Integer> cantidadPromos;
    private List<Oculta> oculto;
    private String redirect;
    private Converter convertidor;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;

    public DatosCarro() {
        servicios = new ArrayList();
        promociones = new ArrayList();
        cantidadServicios = new ArrayList();
        cantidadPromos = new ArrayList();
        oculto = new ArrayList();
        Oculta ocultoObj = new Oculta();
        oculto.add(ocultoObj);
        nickSession = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickName");
        convertidor = new Converter();
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(DatosProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        List<uy.edu.cure.servidor.central.soap.client.Servicio> serviciosCarroAux = portUsuario.obtenerServiciosCarroWS(nickSession);
        for (int i = 0; i < serviciosCarroAux.size(); i++) {
            servicios.add(convertidor.convertirServicio(serviciosCarroAux.get(i)));
        }
        List<uy.edu.cure.servidor.central.soap.client.Promocion> promosCarroAux = portUsuario.obtenerPromocionesCarroWS(nickSession);
        for (int i = 0; i < promosCarroAux.size(); i++) {
            promociones.add(convertidor.convertirPromocion(promosCarroAux.get(i)));
        }
        int size = portUsuario.obtenerCantServiciosCarroWS(nickSession).size();
        cantidadServicios = portUsuario.obtenerCantServiciosCarroWS(nickSession);
        cantidadPromos = portUsuario.obtenerCantPromosCarroWS(nickSession);
        totalCarro = portUsuario.obtenerCarritoClienteWS(nickSession).getPrecio();
        if (servicios.isEmpty() && promociones.isEmpty()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("CarroEmpty.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(DatosCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @PostConstruct
    private void isLoged() {
        if (!datosSesion.isLoged()) {
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
        totalCarro = totalCarro - (servicio.getPrecio() * portUsuario.obtenerClienteWS(nickSession).getCarrito().getCantidadServicios().get(index));
        portUsuario.setPrecioCarroWS(nickSession, totalCarro);
        portUsuario.eliminarCantidadServicioCarro(nickSession, index);
        portUsuario.eliminarServicioCarroWS(nickSession, servicio.getNombre(), servicio.getProveedor().getNickName());
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
        totalCarro = totalCarro - (promocion.getPrecioTotal() * portUsuario.obtenerCarritoClienteWS(nickSession).getCantidadPromociones().get(index));
        portUsuario.setPrecioCarroWS(nickSession, totalCarro);
        portUsuario.eliminarCantidadPromoCarro(nickSession, index);
        portUsuario.eliminarPromoCarroWS(nickSession, index);
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
        return portUsuario.obtenerCarritoClienteWS(nickSession).getCantidadServicios().get(index);

    }

    public int cantidadPromo(Promocion promocion) {
        int index = promociones.indexOf(promocion);
        return portUsuario.obtenerCarritoClienteWS(nickSession).getCantidadPromociones().get(index);
    }

    public String confirmarCarro() {
        ReservaWSImplService reservaWSImplService = null;
        try {
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReservaWS port = reservaWSImplService.getReservaWSImplPort();
        port.agregarCarroWS(nickSession);
        return "/index";
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

}
