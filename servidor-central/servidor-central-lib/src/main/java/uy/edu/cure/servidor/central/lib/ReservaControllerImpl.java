/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author juan
 */
public class ReservaControllerImpl implements ReservaController {

    @Jeringa(value = "reservaservice")
    ReservaService reservaService;

    public ReservaControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crearReserva(List<Promocion> promociones, List<Servicio> servicios, Cliente cliente) {

        double precio = 0;
        if ((promociones.isEmpty()) && (servicios.isEmpty())) {
            return false; // no hay promo ni servicios     
        } else {
            if (!servicios.isEmpty()) {
                int size = servicios.size();
                for (int x = 0; x < servicios.size(); x++) {
                    precio = precio + (servicios.get(x).getPrecio());
                }
            }

            if (!promociones.isEmpty()) {
                int size = promociones.size();
                for (int x = 0; x < promociones.size(); x++) {
                    precio = precio + (promociones.get(x).getPrecioTotal());
                }
                if (!servicios.isEmpty()) {
                    Date fechaCreacion = new Date();
                    Reserva reserva = new Reserva(fechaCreacion, precio, "registrada", promociones, servicios);
                    cliente.setReservas(reserva);
                    reserva.setCliente(cliente);
                    reservaService.guardarReserva(reserva);
                    return true; //creada                            
                }
            }
            Date fechaCreacion = new Date();
            Reserva reserva = new Reserva(fechaCreacion, precio, "registrada", promociones, servicios);
            cliente.setReservas(reserva);
            reserva.setCliente(cliente);
            reservaService.guardarReserva(reserva);
            return true; //creada 
        }

    }

    @Override
    public boolean eliminarReserva(int numero) {
        if (reservaService.existeReserva(numero)) {
            reservaService.eliminarReserva(numero);
            return true;
        }
        return false;
    }

    @Override
    public boolean existeReserva(int numero) {
        return reservaService.existeReserva(numero);
    }

    @Override
    public Reserva obtenerReserva(int numero) {
        return reservaService.obtenerReserva(numero);
    }

    @Override
    public List<Reserva> obtenerTodasReservas() {
        return reservaService.obtenerTodasReservas();
    }

    @Override
    public void vaciarPersistencia() {
        reservaService.vaciarPersistencia();
    }

    @Override
    public boolean cambiarEstado(Reserva reserva, String estado) {
        if (estado.equals("Cancelar")) {
            reserva.getCliente().getReservas().remove(reserva);
            this.eliminarReserva(reserva.getNumero());
            return true;
        }

        if (reserva.getEstado().equals(estado)) {
            return true;
        } else if (reserva.getEstado().equals("registrada")) {
            reserva.getCliente().getReservas().remove(reserva);
            reserva.setEstado(estado);
            reserva.getCliente().getReservas().add(reserva);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void agregarCarro(Cliente cliente) {
        Reserva reserva = new Reserva();
        int cantidadPromocion, cantidadServicio, posicionPromocion, posicionServicio;
        reserva.setNumero(cliente.getCarrito().getNumero());
        reserva.setCliente(cliente);
        Date fecha = new Date();
        reserva.setFechaCreacion(fecha);

        reserva.setPrecio(cliente.getCarrito().getPrecio());
        reserva.setEstado("Registrada");

        List<Promocion> promociones = new ArrayList<Promocion>();
        List<Servicio> servicios = new ArrayList<Servicio>();
        List<Integer> numeroPromocion = new ArrayList<Integer>();
        List<Integer> numeroServidor = new ArrayList<Integer>();

        for (posicionPromocion = 0; posicionPromocion < cliente.getCarrito().getPromociones().size(); posicionPromocion++) {
            promociones.add(cliente.getCarrito().getPromociones().get(posicionPromocion));
        }

        for (posicionServicio = 0; posicionServicio < cliente.getCarrito().getServicios().size(); posicionServicio++) {
            servicios.add(cliente.getCarrito().getServicios().get(posicionServicio));
        }

        for (cantidadPromocion = 0; cantidadPromocion < cliente.getCarrito().getCantidadPromociones().size(); cantidadPromocion++) {
            numeroPromocion.add(cliente.getCarrito().getCantidadPromociones().get(cantidadPromocion));
        }

        for (cantidadServicio = 0; cantidadServicio < cliente.getCarrito().getServicios().size(); cantidadServicio++) {
            numeroServidor.add(cliente.getCarrito().getCantidadServicios().get(cantidadServicio));
        }

        reserva.setPromociones((ArrayList<Promocion>) promociones);
        reserva.setServicios((ArrayList<Servicio>) servicios);
        reserva.setCantidadPromociones(numeroPromocion);
        reserva.setCantidadServicios(numeroServidor);

        reservaService.guardarReserva(reserva);

        cliente.getCarrito().getServicios().clear();
        cliente.getCarrito().getPromociones().clear();
        cliente.getCarrito().getCantidadPromociones().clear();
        cliente.getCarrito().getCantidadServicios().clear();
        cliente.getCarrito().setEstado("");
        cliente.getCarrito().setPrecio(0.0);

        cliente.setCarrito(new Reserva());

    }

}
