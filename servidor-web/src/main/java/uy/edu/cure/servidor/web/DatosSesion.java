/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */
@ManagedBean
@SessionScoped
public class DatosSesion {


    private String passWord, nickName,mnsjError;
    private boolean mostrarError,loged;
    private Cliente usuario;

    public DatosSesion() {
        mostrarError = false;
        nickName = "";
        passWord = "";
    }

    public String logIn() {

        UsuarioWSImplService usuarioWSImplService = null;
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioWS port = usuarioWSImplService.getUsuarioWSImplPort();
 
        String retorno;
        if (port.logInClienteWS(nickName, passWord)) {
            loged = true;
            retorno = "index.xhtml";
        } else {
            mostrarError = true;
            loged = false;
            mnsjError = "*NickName o Password incorrectas*";
            retorno = "";
        }
        return retorno;
    }
    
    public String logOut(){
        loged = false;
        nickName = "";
        usuario = null;
        return "LogIn.xhtml";
    }
    
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isMostrarError() {
        return mostrarError;
    }

    public void setMostrarError(boolean mostrarError) {
        this.mostrarError = mostrarError;
    }

    public String getMnsjError() {
        return mnsjError;
    }

    public void setMnsjError(String mnsjError) {
        this.mnsjError = mnsjError;
    }

    public boolean isLoged() {
        return loged;
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }
    
}
