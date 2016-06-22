package com.blip.dao;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * Abstract Class responsible for manage the connection with a REST DAO services. 
 * For the connection between the API and DAO was chosen the REST HTTP 
 * and this class implement this connection.
 * 
 * @author leomarques
 *
 */
public abstract class DAOConnectionsConfig {

	protected static final String PACKAGES = "com.blip.dao";
	protected static final String HTTP_REST = "http://localhost:8080/";

	protected HttpServer inicializeServer(){
		
		ResourceConfig config = new ResourceConfig().packages(PACKAGES);
        URI uri = URI.create(HTTP_REST);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
}
