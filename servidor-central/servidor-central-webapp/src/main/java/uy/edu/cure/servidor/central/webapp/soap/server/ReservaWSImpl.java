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
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.ReservaWS")
public class ReservaWSImpl implements ReservaWS {

    @Override
    public void agregarCarroWS(String cliente) {
        ReservaControllerImpl reservacontroller = new ReservaControllerImpl();
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        Cliente clienteAux;
        clienteAux = usuariocontroller.obtenerCliente(cliente);
        reservacontroller.agregarCarro(clienteAux);
    }

    @Override
    public List<Reserva> obtenerTodasReservasWS() {
        ReservaControllerImpl reservacontroller = new ReservaControllerImpl();
        List<Reserva> reservasAux;
        reservasAux = reservacontroller.obtenerTodasReservas();
        return reservasAux;
    }


}
