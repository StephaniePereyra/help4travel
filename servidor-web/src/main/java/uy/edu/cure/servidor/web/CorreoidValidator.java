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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SCN
 */
@FacesValidator("correoidValidator")
public class CorreoidValidator implements Validator {

    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS port;

    public CorreoidValidator() {
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        port = usuarioWSImplService.getUsuarioWSImplPort();
    }

    @Override
    public void validate(FacesContext facecont, UIComponent uicomponent, Object tobject) throws ValidatorException {
       String correo = tobject.toString();
       if(port.existeCorreoWS(correo)){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
           "Correo no disponible",null));
       }
       if(!port.correoValidoWS(correo)){
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
           "Correo invalido",null));  
       }
    }
    
}
