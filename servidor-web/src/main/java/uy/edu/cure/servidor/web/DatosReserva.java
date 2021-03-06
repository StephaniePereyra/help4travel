/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author JuanD
 */

@ManagedBean
@ViewScoped
public class DatosReserva {

    int cantidad = 1;
    String nickName = "";
    Reserva carrito;
    boolean mayorCero = true;
    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;
    private ServicioWSImplService servicioWSImplService;
    private ServicioWS portServicio;
    private PromocionWSImplService promocionWSImplService;
    private PromocionWS portPromocion;
    private Converter convertidor;

    public DatosReserva() throws MalformedURLException {
        try {
            convertidor = new Converter();
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));

            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        portServicio = servicioWSImplService.getServicioWSImplPort();
        portPromocion = promocionWSImplService.getPromocionWSImplPort();

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

    @PostConstruct
    public void init() {
        setNickName(datosSesion.getNickName());
    }

    public String agregarServicio(String servicio, String proveedor) {
        if (datosSesion.isLoged()) {

          //  List<Servicio> servicios = new ArrayList<>();
            Servicio servicioObj = new Servicio();
           // Reserva carro = new Reserva();
           // List<uy.edu.cure.servidor.central.soap.client.Servicio> aux = portUsuario.obtenerServiciosCarroWS(this.nickName);
            /*for (int i = 0; i < aux.size(); i++) {
                servicios.add(convertidor.convertirServicio(aux.get(i)));
            }*/
            servicioObj = convertidor.convertirServicio(portServicio.obtenerServicioWS(servicio, proveedor));
            //carro = convertidor.convertirReserva(portUsuario.obtenerCarritoClienteWS(this.nickName));
            if (this.cantidad > 0) {
                if (portUsuario.carroContieneServicioWS(nickName, servicioObj.getNombre(), servicioObj.getProveedor().getNickName())) {
                    int posicion = portUsuario.obtenerPosicionServicioEnCarro(nickName, servicioObj.getNombre(), servicioObj.getProveedor().getNickName());
                    int cantidadServicio = portUsuario.obtenerCarritoClienteWS(this.nickName).getCantidadServicios().get(posicion) + this.cantidad;
                    portUsuario.modCantidadServicioCarro(nickName, posicion, cantidadServicio);
                } else {
                    portUsuario.agregarServicioWS(this.nickName, servicio, proveedor, this.cantidad);
                }
                for (int i = 1; i <= this.cantidad; i++) {
                    double precio = portUsuario.obtenerCarritoClienteWS(this.nickName).getPrecio() + portServicio.obtenerServicioWS(servicio, proveedor).getPrecio();
                    portUsuario.setPrecioCarroWS(this.nickName, precio);
                }

                this.mayorCero = true;
            } else {
                this.mayorCero = false;
            }
            return "";

        } else {

            return "LogIn.xhtml";
        }
    }

    public String agregarPromocion(String promocion, String proveedor) {

        if (datosSesion.isLoged()) {
           // List<Promocion> promociones = new ArrayList<>();
            Promocion promocionObj = new Promocion();
            //Reserva carro = new Reserva();
            //List<uy.edu.cure.servidor.central.soap.client.Promocion> aux = portUsuario.obtenerPromocionesCarroWS(this.nickName);
           /* for (int i = 0; i < aux.size(); i++) {
                promociones.add(convertidor.convertirPromocion(aux.get(i)));
            }*/
            promocionObj = convertidor.convertirPromocion(portPromocion.obtenerPromocionWS(promocion, proveedor));
            //carro = convertidor.convertirReserva(portUsuario.obtenerCarritoClienteWS(this.nickName));
            if (this.cantidad > 0) {
                if (portUsuario.carroContienePromocionWS(nickName, promocionObj.getNombre(), promocionObj.getProveedor().getNickName())) {
                    int posicion = portUsuario.obtenerPosicionPromoEnCarro(nickName, promocionObj.getNombre(), promocionObj.getProveedor().getNickName());
                    int cantidadPromocion = portUsuario.obtenerCarritoClienteWS(this.nickName).getCantidadPromociones().get(posicion) + this.cantidad;
                    portUsuario.modCantidadPromoCarro(nickName, posicion, cantidadPromocion);
                } else {
                    portUsuario.agregarPromocionWS(this.nickName, promocion, proveedor, this.cantidad);
                }

                for (int i = 1; i <= this.cantidad; i++) {
                    double precio = portUsuario.obtenerCarritoClienteWS(this.nickName).getPrecio() + portPromocion.obtenerPromocionWS(promocion, proveedor).getPrecioTotal();
                    portUsuario.setPrecioCarroWS(this.nickName, precio);
                }
                this.mayorCero = true;
            } else {
                this.mayorCero = false;
            }
            return "";

        } else {

            return "LogIn";
        }

    }

}
