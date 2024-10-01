package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.DB.DBConnect;
import com.dao.BookOrderDAOImpl;
import com.dao.CartDAOImpl;
import com.entiy.BookDetails;
import com.entiy.BookOrder;
import com.entiy.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.websocket.SendResult;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String State = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentype = req.getParameter("paymenttype");
			String fulladdress = address + "," + landmark + "," + city + "," + State + "," + pincode;

//			System.out.println(name+" "+email+" "+phno+" "+fulladdress+" "+paymentype);

			int userid = Integer.parseInt(req.getParameter("userid"));

			CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
			List<Cart> list = dao.getBookByUser(userid);
			// code if there is no book in cart then it should not be added

			if (list.isEmpty()) {
				session.setAttribute("additem", "Please Add Item");
				resp.sendRedirect("cart.jsp");

			} else {
				BookOrderDAOImpl dao1 = new BookOrderDAOImpl(DBConnect.getConn());
				int x = dao1.getOrderNo();
				BookOrder order = null;
				ArrayList<BookOrder> ord = new ArrayList<BookOrder>();

//				Random r = new Random();
			

				for (Cart val : list) {
//					System.out.println(val.getBid()+" "+val.getBookname()+" "+val.getAuthor()+" "+val.getPrice() );
					order = new BookOrder();
					x++;
					order.setOrderid("BOOK-ORD-00" + x);
					order.setUsername(name);
					order.setEmail(email);
					order.setPhno(phno);
					order.setFullAddress(fulladdress);
					order.setBookname(val.getBookname());
					order.setAuthor(val.getAuthor());
					order.setPrice(val.getPrice() + "");
					order.setPaymentType(paymentype);
					ord.add(order);

				}

				if ("noselect".equals(paymentype)) {
					session.setAttribute("msg", "Please Select Payment Mode");
					resp.sendRedirect("cart.jsp");

				} else {
					boolean f = dao1.saveOrder(ord);
					if (f) {
//						System.out.println("Order Success");
						resp.sendRedirect("order_success.jsp");
					} else {
						session.setAttribute("failed", "Something Went Wrong On Server");
						resp.sendRedirect("cart.jsp");
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
