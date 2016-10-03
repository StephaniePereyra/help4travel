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
public interface ServicioController {

    public String crearServicio(String nombreServicio, String descripcion, double precio, String ciudadOrigen, String ciudadDestino, List<String> categorias, List<String> imagenes, String nickNameProveedor);

    public boolean existeServicio(String nombreServicio, String nickNameProveedor);

    public Servicio obtenerServicio(String nombreServicio, String nickNameProveedor);

    public List<Servicio> obtenerTodosServicios();

    public int cantidadServicios();

    public void vaciarPersistenciaServicio();

    public String verificarPrecio(String precio);
}
