/**
 * 
 */
package com.blip.dao;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.blip.entity.Purchase;

/**
 * Interface of services that must be implemented in DAO scope. This is apart of 
 * the API implementation but how I thought that the communication with my API and DAO is with REST services 
 * I created this interface to show to other team what services I'm using. 
 * I builded this part to test my REST server too.
 * 
 * 
 * @author leomarques
 *
 */
public interface DAOResource {
	
	
    public String getIt();
	
	public Response findAll();
	
	public Response findAllValidByCurrentTime(final @PathParam("currentTime") String currentTime);
	
	public Response getById(final @PathParam("id") String id);

	public Response getYieldPurchase(final @PathParam("id") String id);
	
	public Response savePurchase(final Purchase Purchase);
 
	public abstract Response deletePurchase(final @PathParam("id") String id);

 
	
	
}
