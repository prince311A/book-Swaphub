package com.admin.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;
import com.entiy.BookDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editbooks")
public class EditBooksServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	try {
		int id = Integer.parseInt(req.getParameter("id"));
		String bname = req.getParameter("bname");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		String bstatus = req.getParameter("bstatus");
		
		BookDetails bd = new BookDetails();
		bd.setBookId(id);
		bd.setBookName(bname);
		bd.setAuthor(author);
		bd.setPrice(price);
		bd.setStatus(bstatus);
		
		BookDaoImpl dao =new BookDaoImpl(DBConnect.getConn());
	boolean f =	dao.updateEditBooks(bd);
	HttpSession session = req.getSession();
	
	if(f) {
		session.setAttribute("succMsg","Book Updated Successfully" );
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
