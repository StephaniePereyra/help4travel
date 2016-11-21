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
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.ServicioWS")
public class ServicioWSImpl implements ServicioWS {

    @Override
    public Servicio obtenerServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        Servicio servicioAux;
        servicioAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor);
        return servicioAux;
    }

    @Override
    public String crearServicioWS(String nombreServicio, String descripcion, double precio, String ciudadOrigen, String ciudadDestino, List<String> categorias, List<String> imagenes, String nickNameProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        String retorno;
        retorno = serviciocontroller.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        return retorno;
    }

    @Override
    public List<Servicio> obtenerTodosServiciosWS() {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        List<Servicio> serviciosAux;
        serviciosAux = serviciocontroller.obtenerTodosServicios();
        return serviciosAux;
    }

    @Override
    public List<String> obtenerImagenesServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        List<String> imagenesAux;
        imagenesAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor).getImagenes();
        return imagenesAux;
    }

    @Override
    public List<Categoria> obtenerCategoriasServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();     
        List<Categoria> categoriasAux;
        categoriasAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor).getCategorias();
        return categoriasAux;
    }
    
    @Override
    public String verificarPrecio(String precio){
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        return servicioController.verificarPrecio(precio);
    }

}
