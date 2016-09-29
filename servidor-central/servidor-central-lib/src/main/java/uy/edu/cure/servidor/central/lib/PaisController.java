/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Pais;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class PaisController implements PaisControllerInterface {

    @Jeringa(value = "paisservice")
    private PaisServiceInterface paisService;

    public PaisController() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crearPais(String nombre) {
        if (!paisService.existePais(nombre)) {
            Pais pais = new Pais(nombre);
            paisService.guardarPais(pais);
            return true;
        }
        return false;
    }

    @Override
    public boolean existePais(String nombre) {
        return paisService.existePais(nombre);
    }

    @Override
    public Pais obtenerPais(String nombre) {
        return paisService.obtenerPais(nombre);
    }

    @Override
    public List<Pais> obtenerTodosPaises() {
        return paisService.obtenerTodosPaises();
    }

    @Override
    public void vaciarPersistenciaPais() {
        paisService.vaciarPersistenciaPais();
    }

}
