/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;
import java.util.Date;

public class Review {
  
    public int productReviewNumber;
    public String productReview;
    public int productNumber;
    public Date dateReviewed;
    public String reviewerUsername;
    public int customerNumber;
    
    public Review(){
    }
    
    public int getProductReviewNumber(){
        return productReviewNumber;
    }
    
    public void setProductReviewNumber(int productReviewNumber){
        this.productReviewNumber = productReviewNumber;
    }
    
    public String getProductReview(){
        return productReview;
    }
    
    public void setProductReview(String productReview){
        this.productReview = productReview;
    }
    
    public int getProductNumber(){
        return productNumber;
    }
    
    public void setProductNumber(int productNumber){
        this.productNumber = productNumber;
    }
    
    public Date getDateReviewed(){
        return dateReviewed;
    }
    
    public void setDateReviewed(Date dateReviewed){
        this.dateReviewed = dateReviewed;
    }
    
    public String getReviewerUsername(){
        return reviewerUsername;
    }
    
    public void setReviewerUsername(String reviewerUsername){
        this.reviewerUsername = reviewerUsername;
    }
    
    public int getCustomerNumber(){
        return customerNumber;
    }
    
    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
}
