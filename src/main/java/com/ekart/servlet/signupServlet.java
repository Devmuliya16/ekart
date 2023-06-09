package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekart.connection.DbCon;
import com.ekart.dao.UserDao;
import com.ekart.model.User;


@WebServlet("/user-signup")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public signupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserDao udao = new UserDao(DbCon.getConnection());
			User user = udao.userSignup(name,email, password);
			
			if(user!=null) {
				request.getSession().setAttribute("Auth", user);
				response.sendRedirect("./index.jsp");
			}else {
				out.print("This email id is already registered");  
		        RequestDispatcher rd=request.getRequestDispatcher("/signup.jsp");  
		        rd.include(request, response);   
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
