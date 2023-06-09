package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ekart.model.Cart;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/Add-cart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartlist = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuentity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list ==null) {
				cartlist.add(cm);
				session.setAttribute("cart-list",cartlist);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				out.print("successfully added to the cart");
				rd.include(request, response);
			}else {
				Boolean flag=false;
				for(Cart c:cart_list) {
					if(id==c.getId()) {
						c.setQuentity(c.getQuentity()+1);
						flag=true;
						break;
					}	
				}
				if(!flag)
				cart_list.add(cm);
				session.setAttribute("cart-list", cart_list);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				out.print("successfully added to the cart");
				rd.include(request, response);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
