/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author juan
 */
public interface ReservaController {

    public int crearReserva(List<Promocion> promociones, List<Servicio> servicios, Cliente cliente);

    public boolean eliminarReserva(int numero);

    public boolean existeReserva(int numero);

    public Reserva obtenerReserva(int numero);

    public List<Reserva> obtenerTodasReservas();
    
    public boolean cambiarEstado(Reserva reserva, String estado);

    public void vaciarPersistencia();
}
