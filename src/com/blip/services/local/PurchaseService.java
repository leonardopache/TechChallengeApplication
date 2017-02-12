/**
 * 
 */
package com.blip.services.local;

import java.util.Calendar;
import java.util.List;

import com.blip.entity.Purchase;

/**
 * This interface provides a contract about the services are available for the
 * others back-end services. If are using a web application container we can
 * annotate this interface as an EJB and inject this interface to use their
 * services, is possible to use a Web Service with a WSDL file or a REST call.
 * Depends only the environment that are the others back-end services.
 * 
 * @author leomarques
 * 
 */
public interface PurchaseService {
	
	public List<Purchase> findAllPurchases() throws Exception;
	
	public List<Purchase> findAllValidByCurrentTime(Calendar currentTime) throws Exception;
	
	public List<Purchase> findPurchasesByIDsPurchase(List<Long> purchasesID) throws Exception;
	
	public double getYieldsPurchase(Purchase purchase) throws Exception;
	
	public String getTextualFromObject(Object object) throws Exception;
	
	public Purchase getPurchaseById(Long id) throws Exception;
	
	public Purchase save(Purchase purchase) throws Exception;

	public void remove(Long id) throws Exception;
	
}
