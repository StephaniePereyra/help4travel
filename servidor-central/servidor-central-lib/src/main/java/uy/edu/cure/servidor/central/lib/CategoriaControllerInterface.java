/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Categoria;

/**
 *
 * @author guido
 */
public interface CategoriaControllerInterface {

    public boolean darAltaCategoria(String nombre, String nombrePadre);

    public void guardarCategoria(Categoria categoria);

    public boolean existeCategoria(String nombre);

    public Categoria obtenerCategoria(String nombre);

    public List<Categoria> obtenerTodosCategorias();

    public void vaciarPersistenciaCategoria();

}
