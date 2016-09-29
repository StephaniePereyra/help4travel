/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Pais;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public interface PaisControllerInterface {

    public boolean crearPais(String nombre);

    public boolean existePais(String nombre);

    public Pais obtenerPais(String nombre);

    public List<Pais> obtenerTodosPaises();

    public void vaciarPersistenciaPais();

}
