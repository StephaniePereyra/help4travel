/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.PromocionControllerImpl;
import uy.edu.cure.servidor.central.lib.ReservaControllerImpl;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
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
     @Override
    public List<Reserva> obteneReservasClienteWS(String nikc) {
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        List<Reserva> reservas = new ArrayList<>();
         for (int i = 0; i < reservaController.obtenerTodasReservas().size(); i++) {
            if ((reservaController.obtenerTodasReservas().get(i).getCliente().getNickName()).equals(nikc)) {
                reservas.add(reservaController.obtenerTodasReservas().get(i));
            }
        }
        return reservas;
    }

    @Override
    public List<Servicio> obtenerServiciosReservaWS(int numero) {
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        List<Servicio> serviciosAux;
        serviciosAux = reservaController.obtenerReserva(numero).getServicios();
        return serviciosAux;
    }

    @Override
    public List<Promocion> obtenerPromocionesReservaWS(int numero) {
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        List<Promocion> promocionesAux;
        promocionesAux = reservaController.obtenerReserva(numero).getPromociones();
        return promocionesAux;
    }

    @Override
    public Reserva obtenerReservaWS(int numero) {
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        return reservaController.obtenerReserva(numero);
    }

    @Override
    public boolean cambiarEstadoWS(Reserva reserva, String estado) {
        ReservaControllerImpl reservaController = new ReservaControllerImpl();
        return reservaController.cambiarEstado(reserva, estado);
    }
    
    @Override
    public boolean crearReservaWS(List<String> promociones, List<String> servicios, String cliente) {
        String Separador = " // ";
        String promocionAux;
        String servicioAux;
        String proveedorAux;
        List<Servicio> serviciosAux = new ArrayList();
        List<Promocion> promocionesAux = new ArrayList();
        Cliente clienteAux;
        boolean respuesta;

        UsuarioControllerImpl usuarioController = new UsuarioControllerImpl();
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        PromocionControllerImpl promocionController = new PromocionControllerImpl();
        ReservaControllerImpl reservaController = new ReservaControllerImpl();

        clienteAux = usuarioController.obtenerCliente(cliente);

        for (int i = 0; i < servicios.size(); i++) {
            String aux = servicios.get(i);
            String[] arrayServicioAux = aux.split(Separador);
            servicioAux = arrayServicioAux[0];
            proveedorAux = arrayServicioAux[1];
            serviciosAux.add(servicioController.obtenerServicio(servicioAux, proveedorAux));
        }

        for (int i = 0; i < promociones.size(); i++) {
            String aux = promociones.get(i);
            String[] arrayPromocionAux = aux.split(Separador);
            promocionAux = arrayPromocionAux[0];
            proveedorAux = arrayPromocionAux[1];
            promocionesAux.add(promocionController.obtenerPromocion(promocionAux, proveedorAux));
        }
        
        respuesta = reservaController.crearReserva(promocionesAux, serviciosAux, clienteAux);

        return respuesta;
    }

}
