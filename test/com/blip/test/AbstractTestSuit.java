package com.blip.test;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;

import com.blip.dao.PurchaseDAO;
import com.blip.test.restserver.Server;
/**
 * Abstract Class to "start" and "stop" a REST service during the test phase.
 * 
 * @author leomarques
 *
 */
public class AbstractTestSuit {

	protected HttpServer server;
	protected PurchaseDAO dao = new PurchaseDAO();
	
	public AbstractTestSuit() {
		super();
	}

	@Before
	public void before() {
		server = Server.inicializaServidor();
	    System.out.println("[TestSuit] Server is up!!");
	}

	@After
	public void after() {
		server.stop();
		System.out.println("[TestSuit] Server is down!!");
	}

}