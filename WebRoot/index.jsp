<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html >
  <head>
    <base href="<%=basePath%>">
    
  </head>
  <!--    -->
  <% 
  	request.getRequestDispatcher("/action/goods-turnIndex").forward(request, response); 
  %>
  <body>
  <!-- 
   <a href="store/goods-turnIndex"> 点此转向 </a>
   -->
 
	
  </body>
</html>


