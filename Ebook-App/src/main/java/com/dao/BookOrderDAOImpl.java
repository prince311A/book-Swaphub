package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entiy.BookOrder;
import com.oracle.wls.shaded.org.apache.bcel.generic.Select;

public class BookOrderDAOImpl implements BookOrderDAO {

	private Connection conn;

	public BookOrderDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	
	public int getOrderNo() {
		int i = 1;
		try {

			String sql = "select * from book_order";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}
	public boolean saveOrder(List<BookOrder> blist) {
	//	List<BookOrder> blist = new ArrayList<>();
		boolean f = false;
		try {
			String sql = "insert into book_order(order_id,user_name,email,address,phone,book_name,author,price,paytyp) values(?,?,?,?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for(BookOrder b:blist) {
				ps.setString(1, b.getOrderid());
				ps.setString(2, b.getUsername());
				ps.setString(3, b.getEmail());
				ps.setString(4,b.getFullAddress());
				ps.setString(5, b.getPhno());
				ps.setString(6, b.getBookname());
				ps.setString(7, b.getAuthor());
				ps.setString(8, b.getPrice());
				ps.setString(9, b.getPaymentType());
				ps.addBatch();
			}
		int []count =	ps.executeBatch();
		conn.commit();
		f=true;
		conn.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}

	
	public List<BookOrder> getOrderedBook(String email) {
		List<BookOrder> list = new ArrayList<BookOrder>();
		BookOrder order = null;
		try {
			String sql = "select * from book_order where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				order  = new BookOrder();
				order.setId(rs.getInt(1));
				order.setOrderid(rs.getString(2));
				order.setUsername(rs.getString(3));
				order.setEmail(rs.getString(4));
				order.setFullAddress(rs.getString(5));
				order.setPhno(rs.getString(6));
				order.setBookname(rs.getString(7));
				order.setAuthor(rs.getString(8));
				order.setPrice(rs.getString(9));
				order.setPaymentType(rs.getString(10));
				list.add(order);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	
	public List<BookOrder> getAllOrderedBookAdmin() {
		List<BookOrder> list = new ArrayList<BookOrder>();
		BookOrder order = null;
		try {
			String sql = "select * from book_order";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				order  = new BookOrder();
				order.setId(rs.getInt(1));
				order.setOrderid(rs.getString(2));
				order.setUsername(rs.getString(3));
				order.setEmail(rs.getString(4));
				order.setFullAddress(rs.getString(5));
				order.setPhno(rs.getString(6));
				order.setBookname(rs.getString(7));
				order.setAuthor(rs.getString(8));
				order.setPrice(rs.getString(9));
				order.setPaymentType(rs.getString(10));
				list.add(order);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	

}
