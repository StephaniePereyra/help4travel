/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

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
import uy.edu.cure.servidor.central.dto.Oculta;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
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
    @Jeringa(value = "usuariocontroller")
    private UsuarioControllerImpl usuariocontroller;
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

    public DatosCarro() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
            servicios = usuariocontroller.obtenerCliente(nickSession).getCarrito().getServicios();
            promociones = usuariocontroller.obtenerCliente(nickSession).getCarrito().getPromociones();
            cantidadServicios = usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios();
            cantidadPromos = usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones();
            totalCarro = usuariocontroller.obtenerCliente(nickSession).getCarrito().getPrecio();
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
        totalCarro = totalCarro - (servicio.getPrecio() * usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().get(index));
        usuariocontroller.obtenerCliente(nickSession).getCarrito().setPrecio(totalCarro);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().remove(index);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getServicios().remove(servicio);
    }

    public void eliminarPromo(Promocion promocion) {

        int index = promociones.indexOf(promocion);
        promociones.remove(promocion);
        totalCarro = totalCarro - (promocion.getPrecioTotal() * usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().get(index));
        usuariocontroller.obtenerCliente(nickSession).getCarrito().setPrecio(totalCarro);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().remove(index);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getPromociones().remove(promocion);
    }

    public int cantidadServ(Servicio servicio) {
        int index = servicios.indexOf(servicio);
        return usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().get(index);
    }

    public int cantidadPromo(Promocion promocion) {
        int index = promociones.indexOf(promocion);
        return usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().get(index);
    }

    public String confirmarCarro() {
        reservacontroller.agregarCarro(usuariocontroller.obtenerCliente(this.nickSession));
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
