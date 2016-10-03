/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public interface ServicioService {

    public void guardarServicio(Servicio servicio);

    public boolean existeServicio(String nombreServicio, String nickNameProveedor);

    public Servicio obtenerServicio(String nombreServicio, String nickNameProveedor);

    public List<Servicio> obtenerTodosServicios();

    public int cantidadServicios();

    public void vaciarPersistenciaServicio();

}
