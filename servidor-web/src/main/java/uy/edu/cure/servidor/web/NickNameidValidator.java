/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author SCN
 */
@FacesValidator("nickNameidValidator")
public class NickNameidValidator implements Validator {

@Jeringa(value="usuariocontroller")    
private UsuarioControllerImpl usuariocontroller;

public NickNameidValidator(){
    try {
      JeringaInjector.getInstance().inyectar(this);
  } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
  }  
}

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
       String nick = t.toString();
       if(usuariocontroller.existeCliente(nick) || usuariocontroller.existeProveedor(nick)){
           throw new ValidatorException(new FacesMessage (FacesMessage.SEVERITY_ERROR,
           "Nick no disponible",null));
       }
    }
    

}
