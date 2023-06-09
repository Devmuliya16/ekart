package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekart.connection.DbCon;
import com.ekart.dao.OrderDao;
import com.ekart.model.Cart;
import com.ekart.model.Order;
import com.ekart.model.User;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/check-out")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckoutServlet() {
        super();
    }	   
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try(PrintWriter out = response.getWriter()){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = new Date();
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				User auth = (User) request.getSession().getAttribute("Auth");
				if(cart_list != null && auth!=null) {
					for(Cart c:cart_list) {
						Order order = new Order();
						order.setId(c.getId());
						order.setUid(auth.getId());
						order.setQunatity(c.getQuentity());
						order.setDate(formatter.format(date));
						
						OrderDao oDao = new OrderDao(DbCon.getConnection());
						boolean result = oDao.insertOrder(order);
						if(!result) break;
					}
					cart_list.clear();
					response.sendRedirect("orders.jsp");
				}else {
					if(auth==null) {
						response.sendRedirect("login.jsp");
					}
					response.sendRedirect("cart.jsp");
				}
			} catch (ClassNotFoundException|SQLException e) {
				
				e.printStackTrace();
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
