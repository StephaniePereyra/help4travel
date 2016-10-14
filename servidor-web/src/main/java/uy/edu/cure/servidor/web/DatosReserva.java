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
    int cantidad;
    String nickName = "";
    Reserva carrito;
    String nombre;
    @Jeringa(value = "usuariocontroller")
    private UsuarioController usuariocontroller;
    @ManagedProperty(value = "#{datosUser}")
    private DatosUser datosuser;

    public DatosReserva() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        double precio = 0;
        Date fecha = null;
        Pais pais = new Pais("Uruguay");
        Ciudad ciudad = new Ciudad("Maldonado", pais);
        Proveedor proveedor = new Proveedor("Pepe", "Pedro", "Nose", "algo@correo.com", fecha, "Empresa", "www.empresa.com", "imagen", "password");
        Servicio servicioAux = new Servicio("Servicio1", "Algo", precio, ciudad, ciudad, proveedor);
        this.servicio = servicioAux;

    }

    public DatosUser getDatosuser() {
        return datosuser;
    }

    public void setDatosuser(DatosUser datosuser) {
        this.datosuser = datosuser;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @PostConstruct
    public void init() {
        setNickName(datosuser.getNickName());
    }

    public String agregarServicio() {

        if (usuariocontroller.existeCliente(nickName)) {
            Cliente cliente = usuariocontroller.obtenerCliente(this.nickName);

            if (cliente.getCarrito() == null) {
                Date fecha = null;
                double precio = 0;
                List<Servicio> servicios = new ArrayList<Servicio>();
                List<Promocion> promociones = new ArrayList<Promocion>();
                Reserva carro = new Reserva(fecha, precio, "carrito", promociones, servicios);
                cliente.setCarrito(carro);
                this.carrito = carro;
            }
            
                this.servicio.setCantidad(this.cantidad);
                this.carrito.setServicio(this.servicio);
                for(int i = 0;i <= this.cantidad; i++){
                    this.carrito.setPrecio(this.carrito.getPrecio() + this.servicio.getPrecio());
                }
                cliente.getCarrito().setServicio(this.servicio);
            
           
            this.nombre = usuariocontroller.obtenerCliente(this.nickName).getCarrito().getServicios().get(0).getNombre();
            return "";
        } else {
            this.nombre = "Nooooo";
            return "LogIn";
        }
    }

    public String agregarPromocion() {
    
        if (usuariocontroller.existeCliente(nickName)) {
            Cliente cliente = usuariocontroller.obtenerCliente(this.nickName);

            if (cliente.getCarrito() == null) {
                Date fecha = null;
                double precio = 0;
                List<Servicio> servicios = new ArrayList<Servicio>();
                List<Promocion> promociones = new ArrayList<Promocion>();
                Reserva carro = new Reserva(fecha, precio, "carrito", promociones, servicios);
                cliente.setCarrito(carro);
                this.carrito = carro;
            }
            
                this.promocion.setCantidad(this.cantidad);
                this.carrito.setPromocion(this.promocion);
                for(int i = 0;i <= this.cantidad; i++){
                    this.carrito.setPrecio(this.carrito.getPrecio() + this.promocion.getPrecioTotal() * this.promocion.getDescuento() * 100);
                }
                cliente.getCarrito().setPromocion(this.promocion);
            
           
            this.nombre = usuariocontroller.obtenerCliente(this.nickName).getCarrito().getEstado();
            return "";
        } else {
            this.nombre = "No Logueado";
            return "LogIn";
        }
    }    

}
