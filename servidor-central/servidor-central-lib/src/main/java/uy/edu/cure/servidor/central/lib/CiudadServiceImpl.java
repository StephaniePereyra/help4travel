/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Ciudad;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class CiudadServiceImpl implements CiudadService {

    private static List<Ciudad> ciudades = new ArrayList<Ciudad>();

    public CiudadServiceImpl() {
    }

    @Override
    public boolean guardarCiudad(Ciudad ciudad) {
        if (ciudad != null) {
            ciudades.add(ciudad);
            return true;
        }
        return false;
    }

    @Override
    public boolean existeCiudad(String nombre) {
        Iterator<Ciudad> iteratorCiudades = ciudades.iterator();
        while (iteratorCiudades.hasNext()) {
            Ciudad ciudadAuxiliar = iteratorCiudades.next();
            if (ciudadAuxiliar.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Ciudad obtenerCiudad(String nombre) {
        Iterator<Ciudad> iteratorCiudades = ciudades.iterator();
        while (iteratorCiudades.hasNext()) {
            Ciudad ciudadAuxiliar = iteratorCiudades.next();
            if (ciudadAuxiliar.getNombre().equals(nombre)) {
                return ciudadAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Ciudad> obtenerTodosCiudades() {
        return ciudades;
    }

    @Override
    public void vaciarPersistenciaCiudad() {
        ciudades.clear();
    }

}
