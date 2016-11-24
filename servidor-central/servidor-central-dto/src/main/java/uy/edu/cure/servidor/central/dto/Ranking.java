/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.dto;

/**
 *
 * @author juan
 */
public class Ranking implements Comparable<Ranking> {

    private String servicio;
    private String proveedor;
    private int visitas;

    public Ranking(String servicio, String proveedor) {
        this.servicio = servicio;
        this.proveedor = proveedor;
        this.visitas = 1;
    }

    @Override
    public int compareTo(Ranking t) {
        if (visitas < t.visitas) {
            return -1;
        }
        if (visitas > t.visitas) {
            return 1;
        }
        return 0;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

}
