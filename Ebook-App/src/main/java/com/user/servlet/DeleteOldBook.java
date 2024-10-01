package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete_old_book")
public class DeleteOldBook extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			int bid = Integer.parseInt(req.getParameter("bid"));
			BookDaoImpl dao = new BookDaoImpl(DBConnect.getConn());
		
			boolean f = dao.oldBookDelete(email, "Old",bid);
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("succMsg"," Old Book Deleted Successfully" );
				resp.sendRedirect("old_book.jsp");
				
			}else {
				session.setAttribute("failedMsg","Something Went Wrong on Server" );
				resp.sendRedirect("old_book.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
	}
	

}
