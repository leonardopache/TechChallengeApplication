package com.blip.test;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.blip.entity.Details;
import com.blip.entity.Purchase;
import com.blip.services.PurchaseServiceImpl;
import com.blip.services.local.PurchaseService;
/**
 * Test implementation of all services disponible in {@link PurchaseService}.
 * 
 * @author leomarques
 *
 */
public class ClientTest extends AbstractTestSuit {
	
	@Test
	public void serviceFindAllPurchaseTest(){
		try {
			List<Purchase> purchases = new PurchaseServiceImpl(server).findAllPurchases();
			Assert.assertEquals(1, purchases.size());
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void serviceFindAllValidPurchaseTest(){
		try {
			List<Purchase> purchases = new PurchaseServiceImpl(server).findAllValidByCurrentTime(Calendar.getInstance());
			Assert.assertEquals(1, purchases.size());
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void serviceYieldsPurchaseTest(){
		try {
			Purchase purchase = new PurchaseServiceImpl(server).getPurchaseById(1l);
			double value = new PurchaseServiceImpl(server).getYieldsPurchase(purchase);
			Assert.assertEquals(400d, value);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void servicePurchaseByIdTest(){
		try {
			Purchase purchase = new PurchaseServiceImpl(server).getPurchaseById(1l);
			Assert.assertNotNull(purchase);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void purchaseCRUDTest(){
		// product expiration after 5 days 
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		
		// create object to persist
		Details detail = new Details(25l,"furniture", "Table + four chairs ", 1, 200d);
		Purchase purchase = new Purchase().addDetails(detail);
		purchase.setExpires(calendar);
		
		try {
			purchase = new PurchaseServiceImpl().save(purchase);
			Long purchaseId = purchase.getId();
			Assert.assertNotNull(purchase.getId());
			
			purchase.getPurchaseDetails().get(0).setQuantity(2);
			purchase = new PurchaseServiceImpl().save(purchase);
			Assert.assertEquals(purchaseId, purchase.getId());
			
			purchase = new PurchaseServiceImpl().getPurchaseById(purchaseId);
			Assert.assertNotNull(purchase);
			
//			new PurchaseServiceImpl().remove(purchase.getId());
//			Assert.assertNull(new PurchaseServiceImpl().getPurchaseById(purchaseId));
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		
	}
}
