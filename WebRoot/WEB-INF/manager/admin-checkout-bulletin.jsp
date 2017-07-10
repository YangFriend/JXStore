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
	#or th{
		text-align:center;
		padding: 6px 0px;
	}
	#or td{
		padding: 6px 0px;
	}  
	.but{
		margin:20px 0px 20px 0px;
		background-color: #638cc7;
	    border: none;
	    color: white;
	    padding: 12px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	}
	
	.delButton{
		background-color: #638cc7;
	    border: none;
	    color: white;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	}
	
	</style>
  </head>
  
  <body>
	<jsp:include page="partpage/admin-head.jsp" />

	<script type="text/javascript">
  	$(document).ready(function(){
  		
  		$(".delButton").click(function(){
			var bulletId = $(this).attr("name");
			console.log(bulletId);
			$.post("ajax/managerAjax-deleteBulletin",{bulletinId: bulletId},
					function(data){
						console.log(data);
						$(this).parent().parent().remove();
				});
			$(this).parent().parent().remove();
  		});
  		
	  	  
	}); 
	</script>
	
	<div style="width: 80%;  margin:100px auto; ">  
	
		<table id="or" width="100%" border="1" rules="all" align="center" >
		
		  <tr>
		    <th width="16%" align="center" scope="col">ID</th>
		    <th align="center" scope="col">标题</th>
		    <th align="center" scope="col">内容</th>
		    <th align="center" scope="col">发布日期</th>
		    <th align="center" scope="col" >管理</th>
		  </tr>
		  <c:forEach items="${bulletinList}" var="bulletin" varStatus="step">
			  <tr>
			    <td align="center" width="40">${bulletin.id}</td>
			    <td align="center"><a  target="_blank" href="action/bulletin-detail?id=${bulletin.id}" >《${bulletin.title}》</a></td>
			    <td align="center">${fn:substring(bulletin.content, 0, 20)}******</td>
			    <td align="center" >${bulletin.releaseDate} </td>
			    <td align="center"> <input type="button" class="delButton" name="${bulletin.id}"  value="删除"></input></td>
			  </tr>
		  </c:forEach>
		  <tr>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		    <td align="center" >&nbsp;</td>
		  </tr>
		</table>
		<a href="action/manager-turnRelBulletin" class="but" > 发布新公告</a>
	
	</div>  
	
	
	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
