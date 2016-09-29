package uy.edu.cure.servidor.central.webapp.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

// http://localhost:8080/servidor-central-webapp/rest/api
@Path("/")
public class TestRestController {
	@GET
	@Produces("text/plain")
	public String getClichedMessage() {
		// Return some cliched textual content
		return "Hello World";
	}
}
