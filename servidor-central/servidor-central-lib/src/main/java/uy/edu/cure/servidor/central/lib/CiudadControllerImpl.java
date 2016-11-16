/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class CiudadControllerImpl implements CiudadController {

    @Jeringa(value = "ciudadservice")
    private CiudadService ciudadService;
    @Jeringa(value = "paisservice")
    private PaisService paisService;

    public CiudadControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crearCiudad(String nombreCiudad, String nombrePais) {
        if (paisService.existePais(nombrePais) && !ciudadService.existeCiudad(nombreCiudad)) {
            Ciudad ciudad = new Ciudad(nombreCiudad, nombrePais);
            ciudadService.guardarCiudad(ciudad);
            paisService.obtenerPais(nombrePais).setCiudades(ciudad);
            return true;
        }
        return false;
    }

    @Override
    public boolean existeCiudad(String nombre) {
        return ciudadService.existeCiudad(nombre);
    }

    @Override
    public Ciudad obtenerCiudad(String nombre) {
        if (ciudadService.existeCiudad(nombre)) {
            return ciudadService.obtenerCiudad(nombre);
        }
        return null;
    }

    @Override
    public List<Ciudad> obtenerTodosCiudades() {
        return ciudadService.obtenerTodosCiudades();
    }

    @Override
    public void vaciarPersistenciaCiudad() {
        ciudadService.vaciarPersistenciaCiudad();
    }

}
