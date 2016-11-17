/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Categoria;

/**
 *
 * @author SCN
 */
@WebService
public interface CategoriaWS {
    
    @WebMethod
    public boolean crearCategoriaWS (String nombre, String nombrePadre);
    
    @WebMethod
    public List<Categoria> obtenerHijosWS (String nombre);
    
    @WebMethod
    public List<Categoria> obtenerTodasCategorias ();
    
    @WebMethod
    public boolean existeCategoriaWS (String nombre);
    
    @WebMethod
    public Categoria obtenerCategoria (String nombre);
}
