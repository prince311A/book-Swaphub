package com.dao;

import java.util.List;

import com.entiy.BookOrder;

public interface BookOrderDAO {
	public int getOrderNo();
	public boolean saveOrder(List<BookOrder> blList);
	public List<BookOrder> getOrderedBook(String email);
	public List<BookOrder> getAllOrderedBookAdmin();

}
