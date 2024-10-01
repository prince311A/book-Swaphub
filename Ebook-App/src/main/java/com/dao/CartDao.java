package com.dao;

import java.util.List;

import com.entiy.Cart;

public interface CartDao {

	public boolean addCart(Cart c);
	public List<Cart> getBookByUser(int userid);
	public boolean deleteBook(int bid,int uid,int cid);
	
	
}
