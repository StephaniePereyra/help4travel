/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Pais;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class PaisService implements PaisServiceInterface {

    private static List<Pais> paises = new ArrayList<Pais>();

    public PaisService() {
    }

    @Override
    public boolean guardarPais(Pais pais) {
        if (pais != null) {
            paises.add(pais);
            return true;
        }
        return false;
    }

    @Override
    public boolean existePais(String nombre) {
        Iterator<Pais> iteratorPaises = paises.iterator();
        while (iteratorPaises.hasNext()) {
            Pais paisAuxiliar = iteratorPaises.next();
            if (paisAuxiliar.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Pais obtenerPais(String nombre) {
        Iterator<Pais> iteratorPaises = paises.iterator();
        while (iteratorPaises.hasNext()) {
            Pais paisAuxiliar = iteratorPaises.next();
            if (paisAuxiliar.getNombre().equals(nombre)) {
                return paisAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Pais> obtenerTodosPaises() {
        return paises;
    }

    @Override
    public void vaciarPersistenciaPais() {
        paises.clear();
    }

}
