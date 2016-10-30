/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author juan
 */
@ManagedBean
@ViewScoped
public class VerReserva {

    @ManagedProperty(value = "#{datosSesion}")
    DatosSesion datosSesion;
    @Jeringa(value = "reservacontroller")
    private ReservaControllerImpl reservaController;
    private String nombre;
    private String proveedor;
    private List<Reserva> reservas = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();
    private List<Promocion> promociones = new ArrayList<>();
    private List<Integer> cantReservas = new ArrayList<>();
    private List<Integer> cantServicios = new ArrayList<>();
    private Integer nroReserva;

    public VerReserva() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void listaReservas() {
        reservas = new ArrayList<>();
        setNombre(datosSesion.getNickName());
        for (int i = 0; i < reservaController.obtenerTodasReservas().size(); i++) {
            if ((reservaController.obtenerTodasReservas().get(i).getCliente().getNickName()).equals(nombre)) {
                reservas.add(reservaController.obtenerTodasReservas().get(i));
            }
        }
        if (!reservas.isEmpty()) {
            for (int i = 0; i < reservas.size(); i++) {
                cantReservas.add(reservas.get(i).getNumero());
            }
        }
    }

    public void serviciosPromos() {
        if (nroReserva != null) {
            servicios = reservaController.obtenerReserva(nroReserva).getServicios();
            promociones = reservaController.obtenerReserva(nroReserva).getPromociones();
            if(!servicios.isEmpty()){
                cantServicios = reservaController.obtenerReserva(nroReserva).getCantidadServicios();
            }
        }
    }
    
      public int cantidadServ(Servicio servicio) {
        int index = servicios.indexOf(servicio);
        return reservaController.obtenerReserva(nroReserva).getCantidadServicios().get(index);
    }

    public int cantidadPromo(Promocion promocion) {
        int index = promociones.indexOf(promocion);
        return reservaController.obtenerReserva(nroReserva).getCantidadPromociones().get(index);
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    public String getEstado() {
        String estado = "";
        Iterator<Reserva> iteratorReserva = reservas.iterator();
        while(iteratorReserva.hasNext()) {
            Reserva reservaAux = iteratorReserva.next();
            if (reservaAux.getNumero() == nroReserva) {
                estado = reservaAux.getEstado();
            }
        }
        return estado;
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
    
    public void actionCancelarReserva() {
        Iterator<Reserva> iteratorReserva = reservas.iterator();
        while(iteratorReserva.hasNext()) {
            Reserva reservaAux = iteratorReserva.next();
            if (reservaAux.getNumero() == nroReserva) {
                reservaAux.setEstado("Cancelada");
            }
        }
    }

}
