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
@WebServlet("/updateprofile")
public class UpdateProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
try {
	int id = Integer.parseInt(req.getParameter("id"));
	String name = req.getParameter("fname");
	String email = req.getParameter("email");
	String phno = req.getParameter("phno");
	String password = req.getParameter("password");
	
	User us=new User() ;
	us.setId(id);
	us.setName(name);
	us.setEmail(email);
	us.setPhno(phno);
	
	UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
	boolean f = dao.checkPassword(password,id);
	HttpSession session = req.getSession();
	if(f) {
		boolean f2= dao.updateProfile(us);
		if(f2) {
			session.setAttribute("succMsg","Profile Updated Successfully" );
			resp.sendRedirect("edit_profile.jsp");
		}
		else {
			session.setAttribute("failedMsg","Something Wrong On Server" );
			resp.sendRedirect("edit_profile.jsp");
		}
	}else {
		session.setAttribute("failedMsg","Incoreect Password" );
		resp.sendRedirect("edit_profile.jsp");

	}
	
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
	}

}
