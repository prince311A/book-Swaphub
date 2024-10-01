package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.css.Rect;

import com.entiy.BookDetails;
import com.entiy.Cart;
//import com.mysql.cj.jdbc.BlobFromLocator;
import com.oracle.wls.shaded.org.apache.regexp.recompile;

public class CartDAOImpl implements CartDao {

	private Connection conn;
	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public boolean addCart(Cart c) {
		boolean f = false;
		try {
			String sql = "insert into cart(bid,uid,bookname,author,price,totalprice)values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,c.getBid());
			ps.setInt(2, c.getUid());
			ps.setString(3,c.getBookname());
			ps.setString(4,c.getAuthor());
			ps.setDouble(5, c.getPrice());
			ps.setDouble(6, c.getTotalprice());
			 int i = ps.executeUpdate();
			 if(i==1) {
				 return true;
			 }
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Cart> getBookByUser(int userid) {
		List<Cart> list = new ArrayList<Cart>();
		double totalprice=0;
		Cart c = null;
		try {
			
			String sql = "select * from cart where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Cart();
				c.setCid(rs.getInt(1));
				c.setBid(rs.getInt(2));
				c.setUid(rs.getInt(3));
				c.setBookname(rs.getString(4));
				c.setAuthor(rs.getString(5));
				c.setPrice(rs.getDouble(6));
				
				totalprice = totalprice+rs.getDouble(7);
				c.setTotalprice(totalprice);
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteBook(int bid,int uid,int cid) {
		boolean f = false;
		try {
			String sql = "delete from cart where bid=? and uid=? and cid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}
	
	

}
