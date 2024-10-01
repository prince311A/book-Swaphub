package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.UserDAOImpl;
import com.entiy.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	 boolean specialChar(String str){
	        for(int i =0;i<str.length();i++){
	            int x = (int)str.charAt(i);
	            if(x>=33 && x<=47|| x>=58 && x<=64){
	             return true;
	            }
	        }
	        return false;
	    }
	     boolean integer(String str){
	        for(int i =0;i<str.length();i++){
	            int x = (int)str.charAt(i);
	            if(x>=48 && x<=57){
	                return true;
	            }
	        }
	        return false;
	    }
	     boolean smallLetter(String str){
	        for(int i =0;i<str.length();i++){
	            int x = (int)str.charAt(i);
	            if(x>=97 && x<=122){
	                return true;
	            }
	        }
	        return false;
	    }
	     boolean capitalLetter(String str){
	        for(int i =0;i<str.length();i++){
	            int x = (int)str.charAt(i);
	            if(x>=65 && x<=90){
	                return true;
	            }
	        }
	        return false;
	    }
	    public boolean validPassword(String str){
	       if(str.length()<8 || str.length()>15){
	           System.out.println("Password Length Should be between 8-15");
	           return false;
	       }
	       if(str.contains(" ")){
	           System.out.println("Password should not contain blank space ");
	           return false;
	       }
	       if(!specialChar(str)){
	           System.out.println("Password should contain a special character");
	           return false;
	       }
	       if(!integer(str)){
	           System.out.println("Password should contain an integers 0-9");
	           return false;
	       }
	       if(!smallLetter(str)){
	           System.out.println("Password should contain a small letter a-z");
	           return false;
	       }
	       if(!capitalLetter(str)){
	           System.out.println("Password should contain a letter capital letter A-Z");
	           return false;
	       }
	       else{
	           System.out.println("Valid Password");
	           return true;
	       }

	    }
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("fname");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			String check = req.getParameter("check");
//			System.out.println(name+" "+email+" "+phNo+" "+password+" "+check);
					
			User us = new User();
			us.setName(name);
			us.setEmail(email);
			us.setPhno(phno);
			us.setPassword(password);
			
			HttpSession session = req.getSession();
			if (check != null) {
				if(validPassword(password)) {
					UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
					boolean f2 =	dao.checkUser(email);
					if(f2) {
						Boolean f = dao.userRegister(us);
						if (f) {
//							System.out.println("User Registered Successfully");
							session.setAttribute("succMsg","Registered Successfully" );
							resp.sendRedirect("register.jsp");
						} else {
//							System.out.println("Something went Wrong");
							session.setAttribute("failedMsg","Something Wrong On Server" );
							resp.sendRedirect("register.jsp");
						}
					}
					else {
						
						session.setAttribute("failedMsg","User Already Exist" );
						resp.sendRedirect("register.jsp");
					}
					
				}
				else {
					session.setAttribute("failedMsg","Password Must Contain Length of 8-15,Integer,Capital Letter,Small Letter And Special Character");
					resp.sendRedirect("register.jsp");	
				}
			}
			
			else {
//				System.out.println("Please Check Terms & Conditions");
				session.setAttribute("failedMsg","Please Check Terms & Conditions" );
				resp.sendRedirect("register.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
