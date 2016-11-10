package uy.edu.cure.servidor.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

@ManagedBean
@SessionScoped
public class App {
    

	public static void main(String[] args) {
            
            System.out.println("Hello World");
            
            
            LogicaBuscador logicaBuscador = new LogicaBuscador();
            logicaBuscador.niceHarcoding();
            /*
            CategoriaControllerImpl categoriaController = new CategoriaControllerImpl();
            UsuarioControllerImpl usuarioController = new UsuarioControllerImpl();
            PaisControllerImpl paisController = new PaisControllerImpl();
            CiudadControllerImpl ciudadController = new CiudadControllerImpl();
            ServicioControllerImpl servicioController = new ServicioControllerImpl();
            Iterator<Pais> iteratorPaises = paisController.obtenerTodosPaises().iterator();
            while(iteratorPaises.hasNext()) {
                Pais pais = iteratorPaises.next();
                System.out.println(pais.getNombre());
            }
            Iterator<Ciudad> iteratorCiudades = ciudadController.obtenerTodosCiudades().iterator();
            while(iteratorCiudades.hasNext()) {
                Ciudad ciudad = iteratorCiudades.next();
                System.out.println(ciudad.getNombre());
            }
            Iterator<Proveedor> iteratorProveedores = usuarioController.obtenerProveedores().iterator();
            while(iteratorProveedores.hasNext()) {
                Proveedor proveedor = iteratorProveedores.next();
                System.out.println(proveedor.getNickName());
            }
            Iterator<Categoria> iteratorCategorias = categoriaController.obtenerTodosCategorias().iterator();
            while(iteratorCategorias.hasNext()) {
                Categoria categoria = iteratorCategorias.next();
                System.out.println(categoria.getNombre());
            }
            Iterator<Servicio> iteratorServicios = servicioController.obtenerTodosServicios().iterator();
            while(iteratorServicios.hasNext()) {
                Servicio servicio = iteratorServicios.next();
                System.out.println(servicio.getNombre());
            }
            */
            logicaBuscador.setWanted("the");
            logicaBuscador.actionBuscar();
            Iterator<Filtrado> iteratorFiltrado = logicaBuscador.getServiciosFiltrados().iterator();
            System.out.println("---");
            while(iteratorFiltrado.hasNext()) {
                Filtrado filtrado = iteratorFiltrado.next();
                System.out.println(filtrado.getNombre()+" - "+filtrado.getPrecio());
            }
            logicaBuscador.setTipoDeBusqueda(1);
            logicaBuscador.ordenar();
            iteratorFiltrado = logicaBuscador.getServiciosFiltrados().iterator();
            System.out.println("---");
            while(iteratorFiltrado.hasNext()) {
                Filtrado filtrado = iteratorFiltrado.next();
                System.out.println(filtrado.getNombre()+" - "+filtrado.getPrecio());
            }
            logicaBuscador.setTipoDeBusqueda(2);
            logicaBuscador.ordenar();
            iteratorFiltrado = logicaBuscador.getServiciosFiltrados().iterator();
            System.out.println("---");
            while(iteratorFiltrado.hasNext()) {
                Filtrado filtrado = iteratorFiltrado.next();
                System.out.println(filtrado.getNombre()+" - "+filtrado.getPrecio());
            }
	} 
}
