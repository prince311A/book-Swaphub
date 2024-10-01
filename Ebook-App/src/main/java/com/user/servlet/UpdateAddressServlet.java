package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.UserDAOImpl;
import com.entiy.User;
import com.oracle.wls.shaded.org.apache.xalan.xsltc.runtime.StringValueHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateaddress")
public class UpdateAddressServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String address = req.getParameter("address");
			String  landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			
			User us = new User();
			us.setId(id);
			us.setAddress(address);
			us.setLandmark(landmark);
			us.setCity(city);
			us.setState(state);
			us.setPincode(pincode);
			
			UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
			boolean f = dao.updateAddress(us);
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("succMsg","Address Updated Successfully" );
				resp.sendRedirect("user_address.jsp");
				
			}else {
				session.setAttribute("failedMsg","Something Wrong On Server" );
				resp.sendRedirect("user_address.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
				
		
	}
	

}
