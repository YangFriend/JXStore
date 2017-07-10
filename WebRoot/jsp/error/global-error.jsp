<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
<base href="<%=basePath%>">
    <meta charset="utf-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="本站售商品 均为虚无缥缈 谨防受骗">
	
	<title>访问错误</title>
    
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
  
	 <div class="product-big-title-area">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-12">
	                    <div class="product-bit-title text-center">
	                        <h2>访问错误</h2>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    
    <div style="width: 90%; height: 500px; margin-left: auto; margin-right: auto;">
    <br />
	<center>
		<h1>无法处理你请求的资源.</h1>
		<s:fielderror fieldName="errorMessage"></s:fielderror>
		<h1>:( </h1><a href="action/goods-turnIndex">点此返回主站</a>
   		
	</center>
    </div>
    
    <%--<s:debug> </s:debug>--%>
    <div class="footer-top-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
        </div>
    </div> 
  </body>
</html>

