/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Reserva;

/**
 *
 * @author juan
 */
public class ReservaService implements ReservaServiceInterface {

    private static List<Reserva> reservas = new ArrayList<Reserva>();

    public ReservaService() {
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public void eliminarReserva(int numero) {
        Iterator<Reserva> iteratorReserva = reservas.iterator();
        while (iteratorReserva.hasNext()) {
            Reserva reservaAuxiliar = iteratorReserva.next();
            if (reservaAuxiliar.getNumero() == (numero)) {
                iteratorReserva.remove();
            }
        }
    }

    @Override
    public boolean existeReserva(int numero) {
        Iterator<Reserva> iteratorReserva = reservas.iterator();
        while (iteratorReserva.hasNext()) {
            Reserva reservaAuxiliar = iteratorReserva.next();
            if (reservaAuxiliar.getNumero() == (numero)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Reserva obtenerReserva(int numero) {
        Iterator<Reserva> iteratorReserva = reservas.iterator();
        while (iteratorReserva.hasNext()) {
            Reserva reservaAuxiliar = iteratorReserva.next();
            if (reservaAuxiliar.getNumero() == (numero)) {
                return reservaAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Reserva> obtenerTodasReservas() {
        return reservas;
    }

    @Override
    public void vaciarPersistencia() {
        reservas.clear();
    }
}
