<%@page import="com.ekart.connection.DbCon" %>
<%@ page import="com.ekart.dao.ProductDao" %>
<%@page import="com.ekart.model.*" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("Auth");
if(auth!=null){
	request.setAttribute("Auth", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList <Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list!=null){
	request.setAttribute("cart_list",cart_list);
}
%>
 
<!DOCTYPE html>
<html>
<head>
<title>Ekart - home</title>
</head>

<!-- Prevent access if user is not logged in
<%
//if(session.getAttribute("Auth")==null)
//	response.sendRedirect("./login.jsp");
%> -->

<%@include file="includes/header.jsp" %>
<% DbCon.getConnection(); %>
<body>
<%@include file="includes/navigation.jsp" %>

<section class="text-gray-600 body-font w-fit m-auto">
  <div class="container px-5 py-24 mx-auto">
    <div class="flex flex-wrap -m-4  justify-center">
      <% if(!products.isEmpty()){
    	 for(Product p:products){%>
    		  <div class="lg:w-1/4 m-2 md:w-1/2 p-4 w-full border-1 rounded-lg hover:bg-blue-100 bg-gray-100">
        <a class="block relative h-48 rounded overflow-hidden">
          <img alt="ecommerce" class="object-cover object-center w-full h-full block" src=<%=p.getImage()%>>
        </a>
        <div class="mt-4">
          <h3 class="text-gray-500 text-xs tracking-widest title-font mb-1"><%=p.getCategory() %></h3>
          <h2 class="text-gray-900 title-font text-lg font-medium"><%=p.getName() %></h2>
          <p class="mt-1"><%=p.getPrice() %></p>
        </div>
        <% if(auth!=null){%>
       		 <a type="button" href="Add-cart?id=<%=p.getId()%>" class="text-white bg-blue-700 hover:bg-blue-800 font-medium rounded-lg text-sm my-2 p-2 focus:outline-none">Add to cart</a>        	
        <% }%>
      </div>
    	 <%}
      } %>
    </div>
  </div>
</section>


</body>
<%@include file="includes/footer.jsp" %>
</html>