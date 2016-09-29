package uy.edu.cure.servidor.central.webapp.soap.server;

import javax.jws.WebService;

// http://localhost:8080/servidor-central-webapp/HelloWSService?wsdl
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.HelloWS")
public class HelloWSImpl implements HelloWS {
	private String message = new String("Hello, ");

	@Override
	public String sayHello(String name) {
		return message + name + ".";
	}
}
