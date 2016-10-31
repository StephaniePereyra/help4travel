/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author SCN
 */
@FacesValidator("imgPerfilValidator")
public class imgPerfilValidator implements Validator {

    @Override
    public void validate(FacesContext facesC, UIComponent uicomponent, Object tobject) throws ValidatorException {
        Part imagen = (Part)tobject;
        String tipo = imagen.getContentType();
        String formato1 = "image/png";
        String formato2 = "image/jpeg";
        String formato3 = "image/jpg";
        if(!formato1.equals(tipo) && !formato2.equals(tipo) && !formato3.equals(tipo)){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
           "La imagen de perfil debe ser .png , .jpg o .jpeg",null));
    }
  }      
}
