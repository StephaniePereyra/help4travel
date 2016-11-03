/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

/**
 *
 * @author SCN
 */
public class MensajeDatosUsuario {

    public String retornoMensajeUsuario(int retorno) {
        String mensaje = "";
        if (retorno == 1) {
            mensaje = "El Nick-Name ingresado ya existe";
        }
        if (retorno == 2) {
            mensaje = "Las password ingresadas no coinciden";
        }
        if (retorno == 3) {
            mensaje = "La direccion de correo ingresada es invalida";
        }
        if (retorno == 5) {
            mensaje = "La fecha de nacimiento ingresada es invalida";
        }
        if (retorno == 6) {
            mensaje = "La imagen de perfil debe ser .png , .jpg o .jpeg";
        }
        if (retorno == -1) {
            mensaje = "Cliente creado";
        }

        return mensaje;
    }

}
