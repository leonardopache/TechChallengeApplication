/**
 * 
 */
package com.blip.entity;

/**
 * Entity Class that contains the descriptions of each item purchased. 
 * <i> ID is an incremental value generated by the DataBase. </i>
 *  
 * @author leomarques
 *
 */
public class Details {

	private Long id;
	private String productType;
	private String description;
	private Integer quantity;
	private Double value;
	
	public Details(){
	}
	
	public Details(Long id, String productType, String description, Integer quantity, Double value){
		this.id = id;
		this.productType = productType;
		this.description = description;
		this.quantity = quantity;
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
