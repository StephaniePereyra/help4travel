/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Reserva;

/**
 *
 * @author SCN
 */
@WebService
public interface ReservaWS {
    
    @WebMethod
    public void agregarCarroWS (Cliente cliente);
    
    @WebMethod
    public List<Reserva> obtenerTodasReservasWS ();
}
