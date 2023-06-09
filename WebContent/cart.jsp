<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ekart.dao.ProductDao" %>
<%@page import="com.ekart.connection.DbCon" %>
<%@page import="com.ekart.model.*" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopify - cart</title>
<%@include file="includes/header.jsp" %>
</head>

<%
User auth = (User) request.getSession().getAttribute("Auth");
if(auth!=null){
	request.setAttribute("Auth", auth);
}else{
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0);
    response.setStatus(302);
	response.sendRedirect("login.jsp");
}
	ArrayList <Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	List<Cart> cartProduct = null;
	if(cart_list!=null){
		ProductDao pDao = new ProductDao(DbCon.getConnection());
		cartProduct = pDao.getCartProducts(cart_list);
		request.setAttribute("cart_list",cart_list);
		double total = pDao.getTotalCartPrice(cart_list);
		request.setAttribute("total", total);
	}
%>

<body>
<%@include file="includes/navigation.jsp" %>
	
<div class="relative overflow-x-auto shadow-md sm:rounded-lg px-8">
	<div class="text-[30px] flex flex-row justify-between items-center">
		Cart
		<div>
		<span class="text-[20px]">total: ${total}/-</span>
		<a class="text-sm px-2 bg-blue-600 hover:bg-blue-800 text-white p-2 rounded-lg" href="check-out">Checkout</a>
		</div>
	</div>
    <table class="w-full text-sm text-left text-gray-500 ">
        <thead class="text-xs sm:w-1/2 text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">
                    Product name
                </th>
                <th scope="col" class="px-6 py-3">
                    Category
                </th>
                <th scope="col" class="px-6 py-3">
                    Price
                </th>
                <th scope="col" class="px-6 py-3">
                    Action
                </th>
                <th scope="col" class="px-6 py-3">
                    Discard
                </th>
            </tr>
        </thead>
        <tbody>
        <% if(cart_list!=null){
        	for(Cart c:cartProduct){%>
        		 <tr class="bg-white border-b ">
                 <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap ">
                    <%=c.getName() %>
                 </th>
                 <td class="px-6 py-4">
                    <%=c.getCategory() %>
                 </td>
                 <td class="px-6 py-4">
                     <%=c.getPrice() %>
                 </td>
               
                 <td class="px-6 py-4">
                    <form action="" method="post flex flex-row items-center justify-evenly w-fit">
               
                    
                    	<a href="edit-quentity?action=inc&id=<%=c.getId() %>" class="bg-blue-300 rounded-lg p-2">+</a>
                    	<input class="p-2 w-20 outline-none bg-gray-100" type="text" name="quentity" value=<%=c.getQuentity() %> readonly>
                    	<a href="edit-quentity?action=dec&id=<%=c.getId() %>" class="bg-blue-300 rounded-lg p-2">-</a>
                    	<a href="buy-now?quentity=<%=c.getQuentity()%>&id=<%=c.getId() %>" class="text-sm px-2 m-2 bg-blue-500 hover:bg-blue-600 text-white rounded-lg">Buy</a>
                    </form>
                 </td>
                 <td class="px-6 py-4">
                    <a class="bg-red-600 p-2 rounded-lg text-white hover:bg-red-800" href="remove-item?id=<%=c.getId()%>">Remove</a>
                 </td>
             </tr>
        	<%}}%>
        </tbody>
    </table>
</div>
	
</body>
<%@include file="includes/footer.jsp" %>
</html>