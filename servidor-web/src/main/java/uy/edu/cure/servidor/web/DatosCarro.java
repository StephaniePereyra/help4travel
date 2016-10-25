/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    @ManagedProperty(value = "#{datosUser}")
    private DatosUser datosuser;
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

    public DatosCarro() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public List<Servicio> getServicios() {
        return servicios;
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

    public DatosUser getDatosuser() {
        return datosuser;
    }

    public void setDatosuser(DatosUser datosuser) {
        this.datosuser = datosuser;
    }

    public UsuarioControllerImpl getUsuariocontroller() {
        return usuariocontroller;
    }

    public void setUsuariocontroller(UsuarioControllerImpl usuariocontroller) {
        this.usuariocontroller = usuariocontroller;
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

    @PostConstruct
    public void cargarArray() {

        servicios = new ArrayList();
        promociones = new ArrayList();
        cantidadServicios = new ArrayList();
        cantidadPromos = new ArrayList();
        oculto = new ArrayList();
        Oculta o = new Oculta();
        oculto.add(o);
        nickSession = datosuser.getNickName();
        servicios = usuariocontroller.obtenerCliente(nickSession).getCarrito().getServicios();
        promociones = usuariocontroller.obtenerCliente(nickSession).getCarrito().getPromociones();
        cantidadServicios = usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios();
        cantidadPromos = usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones();
        totalCarro = usuariocontroller.obtenerCliente(nickSession).getCarrito().getPrecio();
        if (servicios.isEmpty() && promociones.isEmpty()) {
            setCarritoEmpty(true);
        }
    }

    public void eliminarServicio(Servicio s) {

        int index = servicios.indexOf(s);
        servicios.remove(s);
        totalCarro = totalCarro - (s.getPrecio() * usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().get(index));
        usuariocontroller.obtenerCliente(nickSession).getCarrito().setPrecio(totalCarro);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().remove(index);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getServicios().remove(s);
    }

    public void eliminarPromo(Promocion p) {

        int index = promociones.indexOf(p);
        promociones.remove(p);
        totalCarro = totalCarro - (p.getPrecioTotal() * usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().get(index));
        usuariocontroller.obtenerCliente(nickSession).getCarrito().setPrecio(totalCarro);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().remove(index);
        usuariocontroller.obtenerCliente(nickSession).getCarrito().getPromociones().remove(index);
    }

    public int cantidadServ(Servicio s) {
        int index = servicios.indexOf(s);
        return usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadServicios().get(index);
    }

    public int cantidadPromo(Promocion p) {
        int index = promociones.indexOf(p);
        return usuariocontroller.obtenerCliente(nickSession).getCarrito().getCantidadPromociones().get(index);
    }

    public String confirmarCarro() {
        reservacontroller.agregarCarro(usuariocontroller.obtenerCliente(this.nickSession));
        return "/index";
    }
}
