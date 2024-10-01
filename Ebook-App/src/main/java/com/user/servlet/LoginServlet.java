package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.UserDAOImpl;
import com.entiy.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Login")

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String Password  = req.getParameter("password");
	
		if("admin@gmail.com".equals(email) && "admin".equals(Password)) {
			User us = new User();
			us.setName("Admin");
			session.setAttribute("userobj", us);
			resp.sendRedirect("admin/home.jsp");
		}
		else {
			User us =dao.login(email, Password);
			if(us!=null) {
				session.setAttribute("userobj", us);
				resp.sendRedirect("index.jsp");
			}
			else {
				session.setAttribute("failedMsg", "Email and Password Invalid");
				resp.sendRedirect("login.jsp");
			}
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

}
