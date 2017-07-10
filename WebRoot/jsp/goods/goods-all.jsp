<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<title>全部游戏</title>
    
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
		$(".nav.navbar-nav").find("[href*='action/goods-all?pageNow']").parent().addClass("active");
		//ajax/cartAjax-addSingle?goodsId=${goods.id}&num=1
		$("a.add_to_cart_button").click(function(){
			if($(this).text() == "添加成功!")
	    		return false;
	    	$.get(event.target.href,
	    			function(data,status){$(".product-count").text(data);}
	    		);
	    	$(this).text("添加成功!")
	    	return false;
		
		});
       }); 
	</script>
    
  <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>全部游戏</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
            
	            <c:forEach items="${goodsList}" var="goods">
	                <div class="col-md-4 col-sm-6">
	                    <div class="single-shop-product">
	                        <div class="product-upper">
	                        	<a href="action/goods-detail?goodsId=${goods.id}">
	                            <img src="jsp/goods/img/${goods.image}" alt="">
	                        </div>
	                        <h2><a href="action/goods-detail?goodsId=${goods.id}">${goods.name} </a></h2>
	                        <div class="product-carousel-price">
	                            <ins>￥${goods.price}</ins> 
	                        </div>  
	                        
	                        <div class="product-option-shop">
	                            <a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="ajax/cartAjax-addSingle?goodsId=${goods.id}&num=1">添加到购物车</a>
	                        </div>                       
	                    </div>
	                </div>
	   			 </c:forEach>
	   			 
	   			 
	   			 
            
            <div class="row">
                <div class="col-md-12">
                    <div class="product-pagination text-center">
                        <nav>
                          <ul class="pagination">
                           <c:if test="${pageNow-1 > 0 }" >
                            <li>
		                       <a href="action/goods-all?pageNow=${pageNow-1}" aria-label="Previous">
		                        <span aria-hidden="true">&laquo;</span>
		                       </a>
                          	</li>
                            </c:if>
                             <c:if test="${pageNow-1 > 0 }" >
                            	<li><a href="action/goods-all?pageNow=${pageNow-1}">${pageNow-1}</a></li>
                            </c:if>
                            	<li><a href="#" style="color: black;" onclick="return false">  ${pageNow} </a> </li>
                            <c:if test="${pageNow+1 <= pageCount }" >
                            	<li><a href="action/goods-all?pageNow=${pageNow+1}">${pageNow+1}</a></li>
                            </c:if>
                             <c:if test="${pageNow+2 < pageCount }" >
                            	<li><a href="action/goods-all?pageNow=${pageNow+2}">${pageNow+2}</a></li>
                            </c:if>
                              <c:if test="${pageNow+3 < pageCount }" >
                            	<li><a href="action/goods-all?pageNow=${pageNow+3}">${pageNow+3}</a></li>
                            </c:if>
                            <c:if test="${pageNow+1 <= pageCount }" >
								<li><a href="action/goods-all?pageNow=${pageNow+1}" aria-label="Next">
		                         <span aria-hidden="true">&raquo;</span>
		                    </a></li>
							</c:if>
                          </ul>
                        </nav>                        
                    </div>
                </div>
            </div>
        </div>
    </div>

	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
