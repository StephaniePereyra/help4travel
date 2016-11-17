/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.CategoriaWS")
public class CategoriaWSImpl implements CategoriaWS {

    @Override
    public boolean crearCategoriaWS(String nombre, String nombrePadre) {
        CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
        boolean retorno;
        retorno = categoriacontroller.darAltaCategoria(nombre, nombrePadre);
        return retorno;
    }

    @Override
    public List<Categoria> obtenerHijosWS(String nombre) {
        CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
        List<Categoria> categoriasHijosAux;
        categoriasHijosAux = categoriacontroller.obtenerCategoria(nombre).getHijos();
        return categoriasHijosAux;
    }

    @Override
    public List<Categoria> obtenerTodasCategorias() {
        CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
        List<Categoria> categoriasAux;
        categoriasAux = categoriacontroller.obtenerTodosCategorias();
        return categoriasAux;
    }

    @Override
    public boolean existeCategoriaWS(String nombre) {
        CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
        boolean retorno;
        retorno = categoriacontroller.existeCategoria(nombre);
        return retorno;
    }

    @Override
    public Categoria obtenerCategoria(String nombre) {
       CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
       Categoria categoriaAux;
       categoriaAux = categoriacontroller.obtenerCategoria(nombre);
       return categoriaAux;
    }


}
