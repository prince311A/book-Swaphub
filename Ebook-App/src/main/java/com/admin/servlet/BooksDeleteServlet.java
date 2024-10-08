package com.admin.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")

public class BooksDeleteServlet extends  HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			BookDaoImpl dao = new BookDaoImpl(DBConnect.getConn());
		
			boolean f = dao.deleteBooks(id);
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("succMsg","Book Deleted Successfully" );
				resp.sendRedirect("admin/all_books.jsp");
				
			}else {
				session.setAttribute("failedMsg","Something Went Wrong on Server" );
				resp.sendRedirect("admin/all_books.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
	}
	

}
