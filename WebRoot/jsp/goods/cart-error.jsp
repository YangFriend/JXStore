<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<!-- cart - error -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="本站售商品 均为虚无缥缈 谨防受骗">
    <title>购物车</title>
    
    <link href="img/logo.png" type=image/x-icon rel="shortcut icon" />

    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">


    
  </head>
  
  <body>
	<jsp:include page="/jsp/partpage/head.jsp" />
	
	<script type="text/javascript">
  	$(document).ready(function(){
  		$(".nav.navbar-nav").find("[href='action/cart-checkout']").parent().addClass("active");
		
		
       }); 
	</script>

	<div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>购物车</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<div style="width: 90%; height: 500px; margin-left: auto; margin-right: auto;">
	<center>
       <h1>:( </h1>
       <h1>${info}</h1>
       </center>
    </div>
	
	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
