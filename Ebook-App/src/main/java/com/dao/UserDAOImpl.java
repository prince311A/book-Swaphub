package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entiy.User;

public class UserDAOImpl implements UserDao{
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	public boolean userRegister(User user) {
		boolean f = false;
		try {
			String sql = "insert into user(name,email,phno,password) values(?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setString(4, user.getPassword());
			
			int x =ps.executeUpdate();
			if(x==1) {f = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		return f;
	}
	
	public User login(String email, String password) {
		User us =null;
		try {
			String sql= "select * from user where email = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);;
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				us = new User();
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				us.setEmail(rs.getString(3));
				us.setPhno(rs.getString(4));
				us.setPassword(rs.getString(5));
				us.setAddress(rs.getString(6));
				us.setLandmark(rs.getString(7));
				us.setCity(rs.getString(8));
				us.setState(rs.getString(9));
				us.setPincode(rs.getString(10));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return us;
	}

	
	public boolean checkPassword(String password,int id) {
		boolean f = false;
		try {
			String sql = "select * from user where id=? and password =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				f = true;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return f;
	}


	public boolean updateProfile(User user) {
		boolean f = false;
		try {
			String sql = "update user set name=?,email=?,phno=? where id=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhno());
		    ps.setInt(4,user.getId());
			
			int x =ps.executeUpdate();
			if(x==1) {f = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		return f;
	}

	
	public boolean checkUser(String email) {
		boolean f =true;
		try {
			String sql = "select * from user where email=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				f = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}

	
	public boolean updateAddress(User user) {
		boolean f = false;
		try {
			String sql = "update user set address=?,landmark=?,city=?,state=?,pincode=? where id=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, user.getAddress());
			ps.setString(2, user.getLandmark());
			ps.setString(3, user.getCity());
		    ps.setString(4,user.getState());
		    ps.setString(5, user.getPincode());
		    ps.setInt(6, user.getId());
		    
			
			int x =ps.executeUpdate();
			if(x==1) {f = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		return f;
	}
}
