/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class ServicioServiceImpl implements ServicioService {

    private static List<Servicio> servicios = new ArrayList<Servicio>();

    public ServicioServiceImpl() {
    }

    @Override
    public void guardarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    @Override
    public boolean existeServicio(String nombreServicio, String nickNameProveedor) {
        Iterator<Servicio> iteratorServicio = servicios.iterator();
        while (iteratorServicio.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicio.next();
            if (servicioAuxiliar.getNombre().equals(nombreServicio) && (servicioAuxiliar.getProveedor().getNickName()).equals(nickNameProveedor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Servicio obtenerServicio(String nombreServicio, String nickNameProveedor) {
        Iterator<Servicio> iteratorServicio = servicios.iterator();
        while (iteratorServicio.hasNext()) {
            Servicio servicioAuxiliar = iteratorServicio.next();
            if (servicioAuxiliar.getNombre().equals(nombreServicio) && (servicioAuxiliar.getProveedor().getNickName()).equals(nickNameProveedor)) {
                return servicioAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Servicio> obtenerTodosServicios() {
        return servicios;
    }

    @Override
    public int cantidadServicios() {
        return servicios.size();
    }

    @Override
    public void vaciarPersistenciaServicio() {
        servicios.clear();
    }
}
