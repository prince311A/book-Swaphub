package com.admin.servlet;

import java.io.File;
import java.io.IOException;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;
import com.entiy.BookDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/add_books")
@MultipartConfig
public class BooksAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String bname = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("price");
			String btype = req.getParameter("btype");
			String bstatus = req.getParameter("bstatus");
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();
			
			BookDetails b = new BookDetails(bname,author,price,btype,bstatus,fileName,"admin");
			BookDaoImpl dao = new BookDaoImpl(DBConnect.getConn());
			
	
		
			
			boolean f= dao.addBooks(b);
			
			HttpSession session = req.getSession();
			if(f) {
				String path =	getServletContext().getRealPath("")+"book";
				File file = new File(path);
				part.write(path+File.separator+fileName);
				
				session.setAttribute("succMsg","Book Added Successfully" );
				resp.sendRedirect("admin/add_books.jsp");
			}else {
				session.setAttribute("failedMsg","Something Went Wrong on Server" );
				resp.sendRedirect("admin/add_books.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
