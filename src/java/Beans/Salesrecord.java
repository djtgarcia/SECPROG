/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;
import java.util.Date;

public class Salesrecord {

    private int salesRecordNumber;
    private int customerNumber;
    private int productNumber;
    private float productPrice;
    private int quantity;
    private Date purchaseDate;
    private int transactionNumber;
    
    public Salesrecord(){
    }
    
    public int getSalesRecordNumber(){
        return salesRecordNumber;
    }
    
    public void setSalesRecordNumber(int salesRecordNumber){
        this.salesRecordNumber = salesRecordNumber;
    }

    public int getCustomerNumber(){
        return customerNumber;
    }
    
    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
    
    public int getProductNumber(){
        return productNumber;
    }
    
    public void setProductNumber(int productNumber){
        this.productNumber = productNumber;
    }
    
    public float getProductPrice(){
        return productPrice;
    }
    
    public void setProductPrice(float productPrice){
        this.productPrice = productPrice;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public Date getPurchaseDate(){
        return purchaseDate;
    }
    
    public void setPurchaseDate(Date purchaseDate){
        this.purchaseDate = purchaseDate;
    }
    
    public int getTransactionNumber(){
        return transactionNumber;
    }
    
    public void setTransactionNumber(int transactionNumber){
        this.transactionNumber = transactionNumber;
    }
}

