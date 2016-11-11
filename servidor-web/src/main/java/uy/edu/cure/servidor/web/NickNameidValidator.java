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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */
@FacesValidator("nickNameidValidator")
public class NickNameidValidator implements Validator {

    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS port;

    public NickNameidValidator() {
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        port = usuarioWSImplService.getUsuarioWSImplPort();

    }

    @Override
    public void validate(FacesContext faceContent, UIComponent uiComponent, Object tobject) throws ValidatorException {
       String nick = tobject.toString();
       if(port.existeClienteWS(nick) || port.existeProveedorWS(nick)){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
           "Nick no disponible",null));
       }
    }
    

}
