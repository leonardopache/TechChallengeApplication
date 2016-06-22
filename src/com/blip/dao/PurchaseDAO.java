/**
 * 
 */
package com.blip.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.blip.entity.Details;
import com.blip.entity.Purchase;

/**
 * This is the implementation of symbolic DataBase to execute the test cases.
 * 
 * @author leomarques
 *
 */
public class PurchaseDAO {
	private static Map<Long, Purchase> dataBase = new HashMap<Long, Purchase>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Details videogame = new Details(1l,"eletronic", "PS4", 1, 350d);
		Details sweater = new Details(2l, "clotes", "Sweater", 2, 25d);
		Purchase purchase = new Purchase()
								.addDetails(videogame)
								.addDetails(sweater);
		purchase.setExpires(Calendar.getInstance());
		purchase.getExpires().clear();
		purchase.getExpires().set(2016, 06, 01);
		purchase.getExpires().getTime();
		purchase.setId(1l);
		dataBase.put(1l, purchase);
	}
	
	public Long add(Purchase purchase) {
		long id = contador.incrementAndGet();
		purchase.setId(id);
		dataBase.put(id, purchase);
		return purchase.getId();
	}
	
	public Long update(Purchase purchase){
		dataBase.remove(purchase.getId());
		dataBase.put(purchase.getId(), purchase);
		return purchase.getId();
	}
	
	public Purchase get(Long id) {
		return dataBase.get(id);
	}
	
	public Purchase delete(long id) {
		return dataBase.remove(id);
	}

	public List<Purchase> findAll() {
		List<Purchase> list = new ArrayList<Purchase>(dataBase.values());
		return list;
	}
	
	public List<Purchase> findAllValidByCurrentTime(Calendar currentTime){
		List<Purchase> list = new ArrayList<Purchase>();
		for (Purchase purchase : dataBase.values()) {
			if(purchase.getExpires().after(currentTime))
				list.add(purchase);
		}
		return list;
	}

	public double getYieldPurchase(Long purchaseId) {
		Purchase purchase = this.get(purchaseId);
		double yield = 0d;
		for (Details details : purchase.getPurchaseDetails()) {
			yield += (details.getValue() * details.getQuantity());
		}
		return yield;
	}
}
