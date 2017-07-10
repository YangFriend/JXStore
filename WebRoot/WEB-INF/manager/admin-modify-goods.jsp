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
	
	#paenl{
		margin:10px 1px 30px 100px;
		min-height: 700px;
		min-width: 700px;
	}
	

	#introduction p{
		margin: 0 0;
		font-size:30px;
    	font-weight: 900;
	}

	.info{
		margin:0px 0px 20px 0px;
	}
	.info label{
		width:100px;
		font-size: 20px;
		text-align: right;
		
	}
	.info input{
		height: 30px;
		width: 200px;
		border: 1px solid #d1dee2;
		display: inline;
	}
	
	#sub{
		position:relative;
		top:-50px;
		margin:0px 0px 0px 615px;
	}
	
	span{
		color: red
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
	
	<div style="width: 80%;  margin:100px auto; ">  
		<div style="margin: 80px 65px 1px;" >
			<c:if test="${!empty goods.id }">
				<p>商品ID: <span style="color:red">${goods.id }</span></p>
			</c:if>
		</div>
		
	<form action="action/manager-updateProcess" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="goodsDto.id" value="${goods.id }" >
		<div id="paenl">
		   <div class="col-sm-6">
               <div class="product-images">
                   <div class="product-main-img">
                       <img style="height:350px" src="/JX/jsp/goods/img/${goods.image}" alt="无">
                   </div>
                   
               </div>
               
               <div id="introduction">
	        		<p>介绍:</p>
	        		<textarea name="goodsDto.info" rows="10" cols="80" maxlength="500" wrap="soft" >${goods.info}</textarea>
	        	  <div id="sub"> 
	            		<input type="submit" value="提交">
	            	 </div>
	       		</div>
               
           </div>

           <div class="col-sm-6">
	            <div class="product-inner">
	            	<div class="info"> 
	            		<label > 游戏名称:  </label > <input name="goodsDto.name" value="${goods.name}" type="text" maxlength="10" >
	            	 </div>
	            	 
	            	<div class="info"> 
	            		<label >类型:  </label > <input name="goodsDto.gameType" value="${goods.gameType}"  type="text" maxlength="10" >
	            	 </div>
	            	 <div class="info"> 
	            		<label >价格:  </label > <input name="goodsDto.price" value="${goods.price}" type="number"  min="1" max="9999999" >
	            	 </div>
	            	  <div class="info"> 
	            		<label >状态:  </label > 
	            		<select name="goodsDto.status" >
						 <c:choose>
						    <c:when test="${goods.status == 0}">
						      	<option value="0" selected="selected">上架</option>
						 		<option value="1">下架</option>
						    </c:when>
						     <c:otherwise>
						     	<option value="0">上架</option>
						 		<option value="1" selected="selected">下架</option>
						     </c:otherwise>
						</c:choose>
						
						</select>
	            	 </div>
	            	  <div class="info"> 
	            		<label >游戏图片:  </label > <input name="goodsDto.image" type="file"  >
	            		<p>&nbsp;&nbsp;建议 分辨率:<span>340*370</span> 大小:<span>1MB </span>以内.</p> 
	            	 </div>
	            	 <div style="margin:20px 0px 20px 0px;"> 
	            			 <span>${pageInf.mesg}</span>
	            	 </div>
               </div>
               
            </div>
        </div>
    </form> 
        
	</div>  

	
	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
