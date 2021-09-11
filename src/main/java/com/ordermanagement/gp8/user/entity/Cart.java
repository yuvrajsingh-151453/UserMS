package com.ordermanagement.gp8.user.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ordermanagement.gp8.user.utility.Userpk;

@Entity
@Table(name = "cart_table")
public class Cart {
	
	@EmbeddedId
	 Userpk userpk;

	Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	 public Userpk getUserpk() {
			return userpk;
		}

		public void setUserpk(Userpk userpk) {
			this.userpk = userpk;
		}

		
	
}
