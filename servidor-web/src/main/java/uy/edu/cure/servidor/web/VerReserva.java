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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;

/**
 *
 * @author juan
 */
@ManagedBean
@ViewScoped
public class VerReserva {

    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;
    private String nombre;
    private String proveedor;
    private String estado;
    private List<Reserva> reservas = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();
    private List<Promocion> promociones = new ArrayList<>();
    private List<Integer> cantReservas = new ArrayList<>();
    private List<Integer> cantServicios = new ArrayList<>();
    private Integer nroReserva;
    private Converter convertidor;
    private ReservaWSImplService reservaWSImplService;
    private ReservaWS portReserva;

    public VerReserva() {
      
    }

    @PostConstruct
    public void listaReservas() {
        setNombre(datosSesion.getNickName());
        convertidor = new Converter();
        reservas = new ArrayList<>();
        try {
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(DatosProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portReserva = reservaWSImplService.getReservaWSImplPort();
        List<uy.edu.cure.servidor.central.soap.client.Reserva> auxiliar = portReserva.obteneReservasClienteWS(nombre);

        for (int i = 0; i < auxiliar.size(); i++) {
            reservas.add(i, convertidor.convertirReserva(auxiliar.get(i)));
        }

        if (!reservas.isEmpty()) {
            for (int i = 0; i < reservas.size(); i++) {
                cantReservas.add(reservas.get(i).getNumero());
            }
        }
        
    }

    public void serviciosPromos() {
        convertidor = new Converter();
        reservas = new ArrayList<>();
        try {
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(DatosProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portReserva = reservaWSImplService.getReservaWSImplPort();

        if (nroReserva != null) {
            for (int i = 0; i < portReserva.obtenerServiciosReservaWS(nroReserva).size(); i++) {
                servicios.add(convertidor.convertirServicio(portReserva.obtenerServiciosReservaWS(nroReserva).get(i)));
            }

            for (int i = 0; i < portReserva.obtenerPromocionesReservaWS(nroReserva).size(); i++) {
                promociones.add(convertidor.convertirPromocion(portReserva.obtenerPromocionesReservaWS(nroReserva).get(i)));
            }

            if (!servicios.isEmpty()) {
                cantServicios = portReserva.obtenerReservaWS(nroReserva).getCantidadServicios();
            }
        }
    }

    public int cantidadServ(Servicio servicio) {
        int index = servicios.indexOf(servicio);
        return portReserva.obtenerReservaWS(nroReserva).getCantidadServicios().get(index);
    }

    public int cantidadPromo(Promocion promocion) {
        int index = promociones.indexOf(promocion);
        return portReserva.obtenerReservaWS(nroReserva).getCantidadPromociones().get(index);
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public Integer getNroReserva() {
        return nroReserva;
    }

    public void setNroReserva(Integer nroReserva) {
        this.nroReserva = nroReserva;
    }

    public List<Integer> getCantReservas() {
        return cantReservas;
    }

    public void setCantReservas(List<Integer> cantReservas) {
        this.cantReservas = cantReservas;
    }

    public List<Integer> getCantServicios() {
        return cantServicios;
    }

    public void setCantServicios(List<Integer> cantServicios) {
        this.cantServicios = cantServicios;
    }

    public String getEstado() {
        List<uy.edu.cure.servidor.central.soap.client.Reserva> reservasAux = portReserva.obtenerTodasReservasWS();
        Iterator<uy.edu.cure.servidor.central.soap.client.Reserva> iteratorReserva = reservasAux.iterator();
        while (iteratorReserva.hasNext()) {
            uy.edu.cure.servidor.central.soap.client.Reserva reservaAux = iteratorReserva.next();
            if (reservaAux.getNumero() == nroReserva) {
                this.setEstado(reservaAux.getEstado());
            }
        }
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void actionCancelarReserva() {
        portReserva.cambiarEstadoWS(nroReserva, "Cancelada");
    }
}
