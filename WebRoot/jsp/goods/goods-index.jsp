<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="本站售商品 均为虚无缥缈 谨防受骗">
	
    <title>欢迎访问我的商城</title>
    
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
		    $("a.nav.navbar-nav [href='action/goods-turnIndex']").addClass("active");
		    $(".add-to-cart-link").click(function(event) {
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
	 <div class="slider-area">
        	<!-- Slider -->
			<div class="block-slider block-slider4">
				<ul class="" id="bxslider-home4">
					<li>
						<img src="jsp/goods/img/h4-slide_1.png" alt="Slide">
						<div class="caption-group">
							<h2 class="caption title">
								 <span class="primary">关于本站<strong>...</strong></span>
							</h2>
							<h4 class="caption subtitle">About this site...</h4>
							<a class="caption button-radius" href="action/bulletin-detail?id=1024"><span class="icon"></span>查看详情</a>
						</div>
					</li>
					<li><img src="jsp/goods/img/h4-slide_2.png" alt="Slide">
						<div class="caption-group">
							<h2 class="caption title">
								<span class="primary">什么？</span>
							</h2>
							<h4 class="caption subtitle">作为上流社会,公司的CEO,人生的赢家的你..</h4>
							<a class="caption button-radius" href="action/bulletin-detail?id=1025"><span class="icon"></span>查看详情</a>
						</div>
					</li>
					<li><img src="jsp/goods/img/h4-slide_3.png" alt="Slide">
						<div class="caption-group">
							<h2 class="caption title">
								<span class="primary">生活不易,诸事不顺</span>
							</h2>
							<h4 class="caption subtitle">女友发现了藏在国外的情人..</h4>
							<a class="caption button-radius" href="action/bulletin-detail?id=1025"><span class="icon"></span>查看详情</a>
						</div>
					</li>
					<li><img src="jsp/goods/img/h4-slide_4.png" alt="Slide">
						<div class="caption-group">
						  <h2 class="caption title">
								<span class="primary">油腻的师姐在哪里？</span>
							</h2>
							<h4 class="caption subtitle">屠龙宝刀，点击就送，极品装备，一秒刷爆..</h4>
							<a class="caption button-radius" href="action/bulletin-detail?id=1025"><span class="icon"></span>查看详情</a>
						</div>
					</li>
				</ul>
			</div>
			<!-- ./Slider -->
    </div> 
    <!-- End slider area -->
	
	  <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="latest-product">
                        <h2 class="section-title">最新上架</h2>
                        
                        <div class="product-carousel">
                        
                        	<c:forEach items="${latest}" var="latestGoods">
                        	      <div class="single-product">
		                                <div class="product-f-image">
		                                    <img src="jsp/goods/img/${latestGoods.image}" alt="">
		                                    <div class="product-hover">
		                                        <a href="ajax/cartAjax-addSingle?goodsId=${latestGoods.id}&num=1" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> 添加到购物车</a>
		                                        <a href="action/goods-detail?goodsId=${latestGoods.id}" class="view-details-link"><i class="fa fa-link"></i>查看详细信息</a>
		                                    </div>
		                                </div>
		                                <h2><a href="action/goods-detail?goodsId=${latestGoods.id}">${latestGoods.name}</a></h2>
		                                <div class="product-carousel-price">
		                                    <ins>￥${latestGoods.price}</ins>
		                                </div> 
		                            </div>
                        	</c:forEach>
                        	
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <!-- End main content area -->

 

	<jsp:include page="/jsp/partpage/tail.jsp" />
	
  </body>
</html>


