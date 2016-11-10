/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.UsuarioWS")
public class UsuarioWSImpl implements UsuarioWS {

    @Override
    public Cliente obtenerClienteWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        Cliente clienteAux;
        usuariocontroller.crearCliente("NickPrueba", "NombrePrueba", "ApellidoPrueba", "correo@prueba", 1, 2, 1995, "rutaprueba", "pass", "pass");
        clienteAux = usuariocontroller.obtenerCliente(nickName);
        return clienteAux;
    }
}
