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
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="本站售商品 均为虚无缥缈 谨防受骗">
    <title>${pageInf.title} </title>
    
    
    <link href="img/logo.png" type=image/x-icon rel="shortcut icon" />
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">

	<style type="text/css">
  		#panel{
  			/* margin: 100px 100px 150px 100px; min-width:330; max-width: 600px;*/
  			width: 600px;
  			margin-left: auto;
  			margin-right:auto;
  			margin-top:100px ;
  			margin-bottom:150px;
  		}
  		#title{
  			margin-bottom:80px;
  		}
  		
  		#text{
  			min-height: 150px;
  			width: 600px;
  		}
  		
	</style>
  </head>
  
  <body>
	<jsp:include page="partpage/admin-head.jsp" />
	
    <div style="margin-left: auto; margin-right: auto;">
		<div id="panel" >
			<div>  
				<form action="action/manager-releaseBulletin" method="post">
					<label>标题:</label><input name="title" type="text" style="width:400px;"> </input> 
					<div id = "text" >
						<label>内容:</label>
						<textarea name="content" rows="10" cols="80" wrap="soft"></textarea>
					</div>
					<hr >
					<div style=" text-align: right;"><input type="submit" value="发布"> </div>
				</form>
			</div>  
		</div>
 	</div>
	
	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
