/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Reserva;

/**
 *
 * @author juan
 */
public interface ReservaServiceInterface {

    public void guardarReserva(Reserva reserva);

    public void eliminarReserva(int numero);

    public boolean existeReserva(int numero);

    public Reserva obtenerReserva(int numero);

    public List<Reserva> obtenerTodasReservas();

    public void vaciarPersistencia();

}
