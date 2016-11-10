/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.ReservaWS")
public class ReservaWSImpl implements ReservaWS {

    @Override
    public void agregarCarroWS(Cliente cliente) {
        ReservaControllerImpl reservacontroller = new ReservaControllerImpl();
        reservacontroller.agregarCarro(cliente);
    }

    @Override
    public List<Reserva> obtenerTodasReservasWS() {
        ReservaControllerImpl reservacontroller = new ReservaControllerImpl();
        List<Reserva> reservasAux;
        reservasAux = reservacontroller.obtenerTodasReservas();
        return reservasAux;
    }


}
