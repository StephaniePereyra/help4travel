/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp;

import org.junit.Test;
import uy.edu.cure.servidor.central.webapp.rest.api.TestRestController;
/**
 *
 * @author guido
 */
public class TestRestControllerTest {
    
    @Test
    
    public void getClichedMessageMessageTest() {
        String actual = new TestRestController().getClichedMessage();
    }
}
