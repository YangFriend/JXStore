<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="javax.servlet.http.Cookie"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>本站公告</title>
    
    
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
	
	#or td{
		padding: 8px 0px;
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
                        <h2>所有公告</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
	
	<div style="width: 80%;  margin:100px auto; ">  
	
		<table id="or" width="100%" border="1" rules="all" align="center" >
		 <c:forEach items="${bulletinList}" var="bulletin" varStatus="step">
		  <tr>
		    <td align="center" width="40">${step.index +1}</td>
		    <td align="center"><a target="_blank" href="action/bulletin-detail?id=${bulletin.id}" >《${bulletin.title}》</a></td>
		    <td align="center"><a target="_blank" href="action/bulletin-detail?id=${bulletin.id}" >${fn:substring(bulletin.content, 0, 20)}......*(点击查看)*</a> </td>
		    <td align="center" >by: ${bulletin.releaseDate} 发布</td>
		  </tr>
		  </c:forEach>
		  <tr>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		  </tr>
		 
		</table>
	</div>  

	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
