package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ekart.connection.DbCon;
import com.ekart.dao.ProductDao;
import com.ekart.model.Product;
import com.ekart.model.User;


@WebServlet("/add-product")
public class addProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public addProductServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			User user = null;
			HttpSession session = request.getSession();
			user =(User) session.getAttribute("Auth");
			if(user==null) {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			    response.setDateHeader("Expires", 0);
				response.sendRedirect("login.jsp");
			}
			
			
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String category = request.getParameter("category");
			int price = Integer.parseInt(request.getParameter("price"));
			ProductDao pdao = new ProductDao(DbCon.getConnection());
			Product product = pdao.addProduct(category, name, price, image);
			if(product !=null) {
				out.print("added " +name+ " to product successfully");
			}
			else {
				out.print("some error accoured");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addproduct.jsp");
			rd.include(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
