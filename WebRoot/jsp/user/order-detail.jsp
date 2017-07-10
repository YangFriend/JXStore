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
    <title>订单 - ${ogdList[0].id}</title>
    
    
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
	#rowDiv{
		margin:30px 1px 30px 100px;
	}
	
	table tr{
		text-align:center
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
                        <h2>订单 - ${ogdList[0].id} 的详情</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<div style="width: 80%;  margin:100px auto; ">  
	 	<div id="rowDiv">
	 	  <c:forEach items="${ogdList}" var="ogd" varStatus="step">
			<table width="300" border="1">
			  <tr>
			    <th colspan="3" scope="col"><div align="center"><a href="action/goods-detail?goodsId=${ogd.goodsId}"> ${ogd.goodsname }</a> X ${ogd.num } </div></th>
			  </tr>
			  <tr>
			    <td><strong>账号</strong></td>
			    <td><strong>密码</strong></td>
			    <td><strong>平台</strong></td>
			  </tr>
			   <c:forEach items="${ogd.accounts}" var="account">
				  <tr>
				    <td>${account.accountName}</td>
				    <td>${account.password}</td>
				    <td>${account.ter}</td>
				  </tr>
			  </c:forEach>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
		</c:forEach>
		</div>

		
	</div>  

	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
