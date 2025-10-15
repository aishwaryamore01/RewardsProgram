package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(nullable = false)
   private Long customerId;

   
   @Column(nullable = false)
   private String customerName;

   @Column(nullable = false)
   private LocalDate transactionDate;

   @Column(nullable = false)
   private Double amount;
 
   
    public Transaction(Long id, Long customerId, String customerName, LocalDate transactionDate, Double amount) {
	super();
	this.id = id;
	this.customerId = customerId;
	this.customerName = customerName;
	this.transactionDate = transactionDate;
	this.amount = amount;
}

	public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getCustomerId() {
	return customerId;
}


public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public LocalDate getTransactionDate() {
	return transactionDate;
}

public void setTransactionDate(LocalDate transactionDate) {
	this.transactionDate = transactionDate;
}

public Double getAmount() {
	return amount;
}

public void setAmount(Double amount) {
	this.amount = amount;
}

	public Transaction() {
    }
   

}