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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.dto.ValidacionPago;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author JuanD
 */
@ManagedBean
@ViewScoped
public class ProveedorMovil {

    @ManagedProperty(value = "#{datosSesion}")
    private DatosSesion datosSesion;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;
    private ReservaWSImplService reservaWSImplService;
    private ReservaWS portReserva;
    private Converter convertidor;
    private String nickName;
    private Proveedor proveedor;
    private List<Servicio> servicios;
    private List<Promocion> promociones;
    private List<Reserva> reservas;
    private boolean vacioServicio;
    private boolean vacioPromocion;
    private boolean vacioReserva;
    
    
    public ProveedorMovil() throws MalformedURLException {
        try {
            convertidor = new Converter();
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        portReserva = reservaWSImplService.getReservaWSImplPort();
        promociones = new ArrayList();
        servicios = new ArrayList();
        reservas = new ArrayList();

    }

    @PostConstruct
    public void verServicioPromocion() {
        
            setNickName(datosSesion.getNickName());
            proveedor = convertidor.convertirProveedor(portUsuario.obtenerProveedorWS(nickName));
            this.servicios = proveedor.getServicios();
            
            List<uy.edu.cure.servidor.central.soap.client.Promocion> auxiliar = portUsuario.obtenerPormoProveedor(nickName);
            
            for(int i=0;i<auxiliar.size();i++){
                promociones.add(convertidor.convertirPromocion(auxiliar.get(i)));
            }
            
            List<uy.edu.cure.servidor.central.soap.client.Reserva> auxReservas = portReserva.obtenerResevasProveedor(nickName);
            
            for(int i=0;i<auxReservas.size();i++){
                reservas.add(convertidor.convertirReserva(auxReservas.get(i)));
            }
            
            
            if(servicios.isEmpty()){
                vacioServicio = true;
            }
            
            if(promociones.isEmpty()){
                vacioPromocion = true;
            }
            
            if(reservas.isEmpty()){
                vacioReserva = true;
            }
            
            
    }

    public List<Servicio> obtenerServicios(Reserva reserva) {
        List<Servicio> serviciosAux = new ArrayList<Servicio>();
        for (int i = 0; i < reserva.getServicios().size(); i++) {
            if ((reserva.getServicios().get(i).getProveedor().getNickName()).equals(nickName)) {
                serviciosAux.add(reserva.getServicios().get(i));
            }
        }
        return serviciosAux;
    }

    public List<Promocion> obtenerPromociones(Reserva reserva) {
        List<Promocion> promocionesAux = new ArrayList<Promocion>();
        for (int i = 0; i < reserva.getPromociones().size(); i++) {
            if ((reserva.getPromociones().get(i).getProveedor().getNickName()).equals(nickName)) {
                promocionesAux.add(reserva.getPromociones().get(i));
            }
        }
        return promocionesAux;
    }
    
    public void recibePago(int numeroReserva) {
        portReserva.recibirPagoWS(numeroReserva, nickName);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public boolean isVacioServicio() {
        return vacioServicio;
    }

    public void setVacioServicio(boolean vacioServicio) {
        this.vacioServicio = vacioServicio;
    }

    public boolean isVacioPromocion() {
        return vacioPromocion;
    }

    public void setVacioPromocion(boolean vacioPromocion) {
        this.vacioPromocion = vacioPromocion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {

        this.reservas = reservas;
    }

    public boolean isVacioReserva() {
        return vacioReserva;
    }

    public void setVacioReserva(boolean vacioReserva) {
        this.vacioReserva = vacioReserva;
    }

    
    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

}
