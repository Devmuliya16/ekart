<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ekart.model.User" %>
    
    
<%
User auth = (User) request.getSession().getAttribute("Auth");
if(auth!=null){
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Shopify - Login</title>
<%@include file="includes/header.jsp" %>
</head>
<body>


<form class="w-1/2 m-auto mt-16" action="./user-login" method="post">

<span class="text-[50px]"><img src="product-image/shopify.png" class="h-8 mr-3 rounded-full bg-white inline-block" alt="Flowbite Logo" />Shopify</span>
	<div class="text-[20px]">Login</div>
  <div class="mb-6 ">
    <label for="email" class="block mb-2 text-sm font-medium text-gray-900 ">Your email</label>
    <input type="email" name="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="name@flowbite.com" required>
  </div>
  <div class="mb-6">
    <label for="password" class="block mb-2 text-sm font-medium text-gray-900 ">Your password</label>
    <input type="password" name="password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " required>
  </div>
  
  <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
  <a href="signup.jsp">Signup here</a>
</form>


</body>
<%@include file="includes/footer.jsp" %>
</html>