package uy.edu.cure.servidor.central.webapp.rest.api;

import entities.NewClass;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

// http://localhost:8080/servidor-central-webapp/rest/api
@Path("/")
public class TestRestController {
	@GET
	@Produces("text/plain")
	public String getClichedMessage() {
		//NewClass.main();
		return "Hello World";
	}
}
