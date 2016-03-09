/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;
import java.util.Date;

public class Creditcard {
    
    private String name;
    private int cardNumber;
    private int customerNumber;
    private Date expiryDate;
    
    public Creditcard(){    
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getCardNumber(){
        return cardNumber;
    }
    
    public void setCardNumber(int cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public int getCustomerNumber(){
        return customerNumber;
    }
    
    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
    
    public Date getExpiryDate(){
        return expiryDate;
    }
}
