/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author SCN
 */
@WebService
public interface ReservaWS {
    
    @WebMethod
    public void agregarCarroWS (String cliente);
    
    @WebMethod
    public List<Reserva> obtenerTodasReservasWS ();
    
    @WebMethod
    public List<Reserva> obteneReservasClienteWS(String nikc);
    
    @WebMethod
    public List<Servicio> obtenerServiciosReservaWS (int numero);
    
    @WebMethod
    public List<Promocion> obtenerPromocionesReservaWS(int numero);
    
    @WebMethod
    public Reserva obtenerReservaWS(int numero);
    
    @WebMethod
    public boolean cambiarEstadoWS(Reserva reserva, String estado);
    
    @WebMethod
    public boolean crearReservaWS(List<String> promociones, List<String> servicios, String cliente);
    
    
    @WebMethod
    public List<Reserva> obtenerResevasProveedor(String nickNameProveedor);
}
