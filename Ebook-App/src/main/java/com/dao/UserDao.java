package com.dao;

import com.entiy.User;

public interface UserDao {
	public boolean userRegister(User user);
	public User login(String email,String password);
	public boolean checkPassword(String password ,int id);
	public boolean updateProfile(User user);
	
	public boolean checkUser(String email);
	public boolean updateAddress(User user);
}
