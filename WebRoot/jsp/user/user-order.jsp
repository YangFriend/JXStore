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
    <title>我的订单</title>
    
    
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
	#or th{
		text-align:center;
		padding: 6px 0px;
	}
	#or td{
		padding: 6px 0px;
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
                        <h2>订单历史</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
	
	<div style="width: 80%;  margin:100px auto; ">  
	
		<table id="or" width="100%" border="1" rules="all" align="center" >
		<!-- <caption style="font-size: 35px;">我的订单</caption> -->
		  <tr>
		    <th width="16%" align="center" scope="col">订单号</th>
		    <th align="center" scope="col">下单日期</th>
		    <th align="center" scope="col">总计金额</th>
		    <th align="center" valign="middle" scope="col">实付金额</th>
		    <th scope="col">游戏</th>
		    <th scope="col">账号</th>
		  </tr>
		  <c:forEach items="${orderList}" var="order">
		  <tr>
		    <td align="center">${order.id }</td>
		    <td align="center">${order.orderDate}</td>
		    <td align="center">${order.allValue}</td>
		    <td align="center" >${order.actPrice}</td>
		    <td align="center"><a href="action/user-orderDetail?orderId=${order.id }" >共${order.goodsNum}个 </a> </td>
		    <td align="center"><a href="action/user-orderDetail?orderId=${order.id }"  >共${order.accountNum}个 </a> </td>
		  </tr>
		 </c:forEach>
		  <tr>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center"><a href="#" >&nbsp;</a> </td>
		    <td align="center"><a href="#" >&nbsp;</a> </td>
		  </tr>
		    <tr>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center"><a href="#" >&nbsp;</a> </td>
		    <td align="center"><a href="#" >&nbsp;</a> </td>
		  </tr>
		 
		</table>
	</div>  

	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
