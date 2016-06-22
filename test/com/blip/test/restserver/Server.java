/**
 * 
 */
package com.blip.test.restserver;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Implementation of a simple REST server with Glassfish Grizzly and Jersey(JAX-RS) .
 * 
 * @author leomarques
 *
 */
public class Server {

	public static void main(String[] args) throws IOException {
		HttpServer server = inicializaServidor();
        System.out.println("Server started!");
        System.in.read();
        server.stop();
	}

	public static HttpServer inicializaServidor() {
		ResourceConfig config = new ResourceConfig().packages("com.blip.dao");
        URI uri = URI.create("http://localhost:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
	
}
