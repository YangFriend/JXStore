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
    <title>公告-${bulletin.id}</title>
    
    
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
 			text-align: center; 
  		}
  		#title{
  			margin-bottom:80px;
  		}
  		
  		#text{
  			min-height: 150px;
  			text-align: left;
  		}
  		
	</style>
  </head>
  
  <body>
	<jsp:include page="/jsp/partpage/head.jsp" />

	<script type="text/javascript">
  	$(document).ready(function(){

	  	  
	}); 

	</script>
	
	<div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>公告-${bulletin.id} </h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
      
	<div style="margin:50px 250px;">
        <a href="action/bulletin-all">所有公告 </a> <span>/ 公告详情</span>
    </div>
    
    <div style="text-align: center;margin-left: auto; margin-right: auto;">
		<div id="panel" >
			<div>
				<div id = "title"><h3>《${bulletin.title}》</h3></div>
				<div id = "text" >
					<p style="color: red; line-height:30px;">&nbsp;&nbsp;&nbsp;&nbsp;${ bulletin.content}</p>
				</div>
				<hr >
				<div style=" text-align: right;"><p> ${bulletin.releaseDate}</p></div>
			</div>  
		</div>
 	</div>
	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
