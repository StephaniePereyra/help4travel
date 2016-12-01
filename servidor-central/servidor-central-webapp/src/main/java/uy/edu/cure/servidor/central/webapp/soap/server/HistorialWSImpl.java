/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import uy.edu.cure.servidor.central.dto.Historial;
import uy.edu.cure.servidor.central.lib.HistorialControllerImpl;

/**
 *
 * @author juan
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.HistorialWS")
public class HistorialWSImpl implements HistorialWS {

    @Override
    public void crearHistorial(String ipAdd, String userAgent, String url) {
        HistorialControllerImpl estadisticaController = new HistorialControllerImpl();
        Historial estadistica = estadisticaController.crearEstadistica(ipAdd, userAgent, url);
        if (estadistica != null) {
            try {
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory("jpaDS");
                EntityManager em = (EntityManager) emf.createEntityManager();

                //Comienza transaccion
                em.getTransaction().begin();

                //Persisto los items
                em.persist(estadistica);

                em.getTransaction().commit();
                //Fin Transaccion

            } catch (Exception e) {

            }

        }

    }
}
