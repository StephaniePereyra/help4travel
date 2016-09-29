/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Ciudad;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public interface CiudadServiceInterface {

    public boolean guardarCiudad(Ciudad ciudad);

    public boolean existeCiudad(String nombre);

    public Ciudad obtenerCiudad(String nombre);

    public List<Ciudad> obtenerTodosCiudades();

    public void vaciarPersistenciaCiudad();

}
