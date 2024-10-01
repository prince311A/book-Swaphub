package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/removebook")
public class RemoveBookCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bid = Integer.parseInt(req.getParameter("bid"));
		int uid = Integer.parseInt(req.getParameter("uid"));
		int cid = Integer.parseInt(req.getParameter("cid"));
		try {
			CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
			Boolean f = dao.deleteBook(bid,uid,cid);
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("succMsg", "Book Remove From Cart");
				resp.sendRedirect("cart.jsp");
				
			}else {
				session.setAttribute("failed", "Something Went Wrong On Server");
				resp.sendRedirect("cart.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
