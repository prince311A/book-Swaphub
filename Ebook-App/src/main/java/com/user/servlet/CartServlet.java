package com.user.servlet;

import java.io.IOException;
import java.io.NotActiveException;
import java.sql.Connection;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;
import com.dao.CartDAOImpl;
import com.entiy.BookDetails;
import com.entiy.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/cartServlet")

public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	try {
		int bid = Integer.parseInt(	req.getParameter("bid"));
		int uid = Integer.parseInt(req.getParameter("uid"));
		
		BookDaoImpl dao= new BookDaoImpl(DBConnect.getConn());
		BookDetails b = dao.getBookById(bid);
		
		Cart c = new Cart();
		c.setBid(bid);
		c.setUid(uid);
		c.setBookname(b.getBookName());
		c.setAuthor(b.getAuthor());
		c.setPrice(Double.parseDouble(b.getPrice()));
		c.setTotalprice(Double.parseDouble(b.getPrice()));
		
		CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());
		Boolean f = dao2.addCart(c);
		
		HttpSession session = req.getSession();
		
		if(f) {
			session.setAttribute("addcart", "Book Added To Cart Successfully");
			resp.sendRedirect("all_new_book.jsp");
//			System.out.println("Add Cart Success");
		}else {
//			System.out.println("Not Added to Cart");
			session.setAttribute("failed", "Something Went Wrong On Server");
			resp.sendRedirect("all_new_book.jsp");
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

}
