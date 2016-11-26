/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class ValidacionPago {

    private String nickProveedor;
    private boolean pago;

    public ValidacionPago(String nickProveedor) {
        this.nickProveedor = nickProveedor;
        this.pago = false;
    }

    public String getNickProveedor() {
        return nickProveedor;
    }

    public void setNickProveedor(String nickProveedor) {
        this.nickProveedor = nickProveedor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

}
