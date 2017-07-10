<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="javax.servlet.http.Cookie"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    
    <title>${pageInf.title} </title>
    
    
    <link href="img/logo.png" type=image/x-icon rel="shortcut icon" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    

	<style type="text/css">
		#pe{
			width: 400px;
			height: 200px;
			margin-left: auto;
  			margin-right:auto;
  			margin-top:80px ;
  			margin-bottom:150px;
  			
		}
	
	</style>
	
  </head>
  
  <body>
  	<jsp:include page="partpage/admin-head.jsp" />
  	
	<script type="text/javascript">
  	$(document).ready(function(){

	  	  ;
	}); 

	</script>
	
	<div id="pe" >  
		<div id="tailNav"> 
           	<table border="0">
			  <tr>
			    <td>Nav:</td>
			    <td><a href="action/manager-checkoutOrder">所有订单</a></td>
			    <td><a href="action/manager-users">管理用户</a></td>
			    <td><a href="action/manager-goods">管理商品</a></td>
			    <td><a href="action/manager-bulletin">管理公告</a></td>
			    <td>&nbsp;-&nbsp;</td>
			   	<td><a href="action/manager-logout">安全退出</a></td>
			   	<td>&nbsp;-&nbsp;</td>
			   	<td><a href="action/goods-turnIndex">主站</a></td>
			  </tr>
			  
			</table>
		</div>
	</div>
	
	

	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>