<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ekart.model.*" %>
<%@page import="com.ekart.dao.OrderDao" %>
<%@page import="com.ekart.connection.*" %>
<%@page import="java.util.*"%>
<%
User auth = (User) request.getSession().getAttribute("Auth");
List<Order> orders=null;
if(auth!=null){
	request.setAttribute("Auth", auth);
	orders = new OrderDao(DbCon.getConnection()).userOrders(auth.getId());
}else{
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0);
	response.sendRedirect("login.jsp");
}
ArrayList <Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list!=null){
	request.setAttribute("cart_list",cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navigation.jsp" %>

<div class="relative overflow-x-auto shadow-md sm:rounded-lg px-8">
	<div class="text-[30px] flex flex-row justify-between items-center">
		Orders
	</div>
    <table class="w-full text-sm text-left text-gray-500 ">
        <thead class="text-xs sm:w-1/2 text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">
                    Date
                </th>
                <th scope="col" class="px-6 py-3">
                    Name
                </th>
                <th scope="col" class="px-6 py-3">
                    Category
                </th>
                <th scope="col" class="px-6 py-3">
                    Quantity
                </th>
                <th scope="col" class="px-6 py-3">
                    Price
                </th>
                 <th scope="col" class="px-6 py-3">
                    Cancel
                </th>
            </tr>
        </thead>
        <tbody>
        <% if(orders!=null){
        	for(Order o:orders){%>
        		 <tr class="bg-white border-b ">
                 <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap ">
                    <%=o.getDate() %>
                 </th>
                 <td class="px-6 py-4">
                    <%=o.getName() %>
                 </td>
                 <td class="px-6 py-4">
                     <%=o.getCategory() %>
                 </td>
                 <td class="px-6 py-4">
                    <%=o.getQunatity() %>
                 </td>
                  <td class="px-6 py-4">
                     <%=o.getPrice() %>
                 </td>
               
                 
                 <td class="px-6 py-4">
                    <a class="bg-red-600 p-2 rounded-lg text-white hover:bg-red-800" href="cancel-order?id=<%=o.getOrderId()%>">Cancel order</a>
                 </td>
             </tr>
        	<%}}%>
        </tbody>
    </table>
</div>
	

</body>
<%@include file="includes/footer.jsp" %>
</html>