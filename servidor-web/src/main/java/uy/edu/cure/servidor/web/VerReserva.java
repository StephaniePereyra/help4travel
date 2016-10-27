/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.dto.Usuario;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author juan
 */
@ManagedBean
@SessionScoped
public class VerReserva {

    @ManagedProperty(value = "#{datosUser}")
    private DatosSesion datosSesion;  
    @Jeringa(value = "serviciocontroller")
    private ServicioControllerImpl servicioController;
    @Jeringa(value = "usuariocontroller")
    private UsuarioControllerImpl usuarioController;
    private String nombre;
    private String proveedor;
    private List<Reserva> reservas = new ArrayList<>();
    private List <Servicio> servicios = new ArrayList<>();
    private List <Promocion> promociones = new ArrayList<>();
    private List <Integer> cantReservas = new ArrayList<>();
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
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        
        for(int i=0;i<reservaController.obtenerTodasReservas().size();i++){
            if((reservaController.obtenerTodasReservas().get(i).getCliente().getNickName()).equals(nombre)){
                reservas.add(reservaController.obtenerTodasReservas().get(i));
            }
        }
        if(!reservas.isEmpty()){
            for (int i = 0; i<reservas.size();i++){
                cantReservas.add(i);
            }
            
        }
        
    }
    
    public void serviciosPromos(){
        servicios=reservas.get(nroReserva).getServicios();
        promociones=reservas.get(nroReserva).getPromociones();
    }

    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }


    public ServicioControllerImpl getServicioController() {
        return servicioController;
    }

    public void setServicioController(ServicioControllerImpl servicioController) {
        this.servicioController = servicioController;
    }

    public UsuarioControllerImpl getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioControllerImpl usuarioController) {
        this.usuarioController = usuarioController;
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
    

}
