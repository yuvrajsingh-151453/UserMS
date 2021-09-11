package com.ordermanagement.gp8.user.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ordermanagement.gp8.user.utility.Userpk;

@Entity
@Table(name = "wishlist_table")
public class Wishlist {
	
	@EmbeddedId
	private Userpk userId;

	public Userpk getUserId() {
		return userId;
	}

	public void setUserId(Userpk userId) {
		this.userId = userId;
	}

}
