/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Pais;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ServicioController;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.PromocionControllerImpl;
import uy.edu.cure.servidor.central.lib.UsuarioController;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author JuanD
 */
@ManagedBean
@ViewScoped
public class DatosReserva {

    private Servicio servicio;
    private Promocion promocion;
    int cantidad = 1;
    String nickName = "";
    Reserva carrito;
    boolean mayorCero = true;
    @Jeringa(value = "usuariocontroller")
    private UsuarioController usuariocontroller;
    @Jeringa(value = "serviciocontroller")
    private ServicioControllerImpl serviciocontroller;
    @Jeringa(value = "promocioncontroller")
    private PromocionControllerImpl promocioncontroller;
    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;

    public DatosReserva() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isMayorCero() {
        return mayorCero;
    }

    public void setMayorCero(boolean mayorCero) {
        this.mayorCero = mayorCero;
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }
  

    public void init() {
        setNickName(datosSesion.getNickName());
    }

    public String agregarServicio(String servicio, String proveedor) {
        if (usuariocontroller.existeCliente(nickName)) {
            if (this.cantidad > 0) {
                Cliente cliente = usuariocontroller.obtenerCliente(this.nickName);
                this.setServicio(serviciocontroller.obtenerServicio(servicio, proveedor));
                if (cliente.getCarrito().getServicios().contains(this.servicio)) {
                    int posicion = cliente.getCarrito().getServicios().indexOf(this.servicio);
                    int cantidadServicio = cliente.getCarrito().getCantidadServicios().get(posicion) + this.cantidad;
                    cliente.getCarrito().getCantidadServicios().add(posicion, cantidadServicio);
                } else {
                    cliente.getCarrito().setServicio(this.servicio);
                }
                for (int i = 1; i <= this.cantidad; i++) {
                    cliente.getCarrito().setPrecio(cliente.getCarrito().getPrecio() + this.servicio.getPrecio());
                }

                cliente.getCarrito().getCantidadServicios().add(this.cantidad);
                this.mayorCero = true;
            }
            else{
                this.mayorCero = false;
            }
            return "";

        } else {

            return "LogIn";
        }
    }

    public String agregarPromocion(String promocion, String proveedor) {

        if (usuariocontroller.existeCliente(nickName)) {
            if (this.cantidad > 0) {
                Cliente cliente = usuariocontroller.obtenerCliente(this.nickName);
                this.setPromocion(promocioncontroller.obtenerPromocion(promocion, proveedor));
                if (cliente.getCarrito().getPromociones().contains(this.promocion)) {
                    int posicion = cliente.getCarrito().getPromociones().indexOf(this.promocion);
                    int cantidadPromocion = cliente.getCarrito().getCantidadPromociones().get(posicion) + this.cantidad;
                    cliente.getCarrito().getCantidadPromociones().add(posicion, cantidadPromocion);
                } else {
                    cliente.getCarrito().setPromocion(this.promocion);
                }

                for (int i = 1; i <= this.cantidad; i++) {
                    cliente.getCarrito().setPrecio(cliente.getCarrito().getPrecio() + this.promocion.getPrecioTotal() * this.promocion.getDescuento() / 100);
                }
                cliente.getCarrito().getCantidadPromociones().add(this.cantidad);
                this.mayorCero = true;
            }
            else{
                this.mayorCero = false;
            }
            return "";

        } else {

            return "LogIn";
        }

    }

}
