/**
 * 
 */
package com.blip.dao;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blip.entity.Purchase;
import com.google.gson.Gson;


/**
 * Implementations of services {@link DAOResource} that must be implemented in DAO scope. 
 * 
 * @author leomarques
 *
 */
@Path("/purchase")
public class DAOResourceImpl implements DAOResource {

	PurchaseDAO dao = new PurchaseDAO();
	
	@Override
	@GET
	@Path("/verify")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "REST Service is alive!!";
    }

	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		return Response.status(200).entity(new Gson().toJson(dao.findAll())).build();
	}
	
	@Override
	@GET
	@Path("/valid/{currentTime}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllValidByCurrentTime(@PathParam("currentTime") String currentTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(currentTime));
		List<Purchase> list = dao.findAllValidByCurrentTime(calendar);
		return Response.status(200).entity(new Gson().toJson(list)).build();
	}

	@Override
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) {
		return Response.status(200).entity(new Gson().toJson(dao.get(Long.valueOf(id)))).build();
	}


	@Override
	@GET
	@Path("/yields/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getYieldPurchase(@PathParam("id") String id) {
		return Response.status(200).entity(new Gson().toJson(dao.getYieldPurchase(Long.valueOf(id)))).build();
	}
	
	@Override
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePurchase(Purchase purchase) {
		if(purchase.getId() != null)
			return Response.status(201).entity(new Gson().toJson(dao.update(purchase))).build();
		else
			return Response.status(201).entity(new Gson().toJson(dao.add(purchase))).build();
	}

	@Override
	@DELETE
	@Path("/delete/{id}")
	public Response deletePurchase(@PathParam("id") String id) {
		dao.delete(Long.valueOf(id));
		return Response.status(200).build();
	}

}
