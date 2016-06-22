/**
 * 
 */
package com.blip.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;

import com.blip.dao.DAOConnectionsConfig;
import com.blip.entity.Purchase;
import com.blip.services.local.PurchaseService;
import com.google.gson.Gson;


/**
 * Implamentation of all methodos disponible on interface {@link PurchaseService}. 
 * 
 * @author leomarques
 *
 */
public class PurchaseServiceImpl extends DAOConnectionsConfig implements PurchaseService {

	private HttpServer server;
	private WebTarget target;
	
	public PurchaseServiceImpl(){
		this.server = super.inicializeServer();
		target = ClientBuilder.newClient().target(HTTP_REST);
	}
	
	public PurchaseServiceImpl(HttpServer server){
		this.server = server;
		target = ClientBuilder.newClient().target(HTTP_REST);
	}
	
	@Override
	public List<Purchase> findAllPurchases() throws Exception {
		try {
			String content = target.path("/purchase/all").request().get(String.class);
			Purchase[] purchases = new Gson().fromJson(content, Purchase[].class);
			return Arrays.asList(purchases);
			
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public List<Purchase> findAllValidByCurrentTime(Calendar currentTime) throws Exception{
		try {
			String content = target.path("/purchase/valid").path("/"+currentTime.getTimeInMillis()).request().get(String.class);
			Purchase[] purchases = new Gson().fromJson(content, Purchase[].class);
			return Arrays.asList(purchases);
			
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public List<Purchase> findPurchasesByIDsPurchase(List<Long> purchasesID) throws Exception{
		try {
			// TODO Auto-generated method stub
			return null;
			
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public double getYieldsPurchase(Purchase purchase) throws Exception{
		try {
			String content = target.path("/purchase/yields").path("/"+purchase.getId()).request().get(String.class);
			return new Gson().fromJson(content, double.class);
			
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public String getTextualFromObject(Object object) throws Exception{
		try {
			return new Gson().toJson(object);
			
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public Purchase getPurchaseById(Long id) throws Exception{
		try {
			String content = target.path("/purchase/"+id).request().get(String.class);
			return new Gson().fromJson(content, Purchase.class);
		
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	@Override
	public Purchase save(Purchase purchase) throws Exception{
		try {
			String content = target.path("/purchase/save").request(MediaType.APPLICATION_JSON)
					.header("Content-Type", "application/json")
					.post(Entity.json(purchase), String.class);
			
			if(content != null)
				return this.getPurchaseById(Long.valueOf(new Gson().fromJson(content, Long.class)));
			else
				return null;
		
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

	//TODO verify why error 405 - Method Not Allowed
	@Override
	public void remove(Long id)  throws Exception{
		try {
			Response content = target.path("/purchase/delete/"+id).request().get(Response.class);
			if(content.getStatus() != 200)
				throw new Exception("Erro delete service");
		}catch(RuntimeException e){
			server.stop();
			throw new Exception("Erro comunication with HTTP Service");
		}
	}

}
