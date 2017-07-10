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
	
	<title>${goods.name} - 详情</title>
    
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
	
	 <div class="product-big-title-area">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-12">
	                    <div class="product-bit-title text-center">
	                        <h2>${goods.name}</h2>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    
    
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="single-sidebar">
                        <h2 class="sidebar-title">搜索</h2>
                        <form action="">
                            <input type="text" placeholder="暂不可用..." readonly="readonly">
                            <input type="submit" value="Search" readonly="readonly">
                        </form>
                    </div>
                    
                    
                </div>
                
                <div class="col-md-8">
                    <div class="product-content-right">
                        <div class="product-breadcroumb">
                            <a href="action/goods-turnIndex">主页</a>
                            <a href="action/goods-all">全部游戏</a>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="product-images">
                                    <div class="product-main-img">
                                        <img src="jsp/goods/img/${goods.image}" alt="">
                                    </div>
                                    
                               		<div class="product-gallery">
                                        <img src="img/product-thumb-1.jpg" alt="暂无">
                                        <img src="img/product-thumb-2.jpg" alt="暂无">
                                        <img src="img/product-thumb-3.+" alt="暂无">
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6">
                                <div class="product-inner">
                                
                                    <h5 class="product-name">游戏名称: ${goods.name}</h5>
      							
                                    <div class="product-inner-price">
                                    	<ins>售价: ￥${goods.price}</ins> <br /> <br />
                       					<ins>库存: ${goods.surplus}</ins>
                                    </div>    
                                    
                                    <form action="action/cart-add" class="cart">
                                        <div class="quantity">
                                        	<input type="hidden" name="goodsId" value="${goods.id}">
                                            <input type="number" size="2" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1" max="3">
                                        </div>
                                        <button class="add_to_cart_button" type="submit">添加到购物车</button>
                                    </form>   
                                    
                                    <div class="product-inner-category">
                                    	<p> <span style="color:red"> 注:</span> ${goods.status eq 0? "因购买人数火爆,每个用户,每单最多仅售三个": "该商品已被下架,无法购买"}</p>
                                    </div> 
                                    
                                    <div role="tabpanel">
                                        <ul class="product-tab" role="tablist">
                                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">介绍</a></li>
                                            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">其他</a></li>
                                        </ul>
                                        
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                <h2>游戏介绍</h2>  
                                                <p>&nbsp;&nbsp;&nbsp;&nbsp;${goods.info}</p>

                                                
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="profile">
                                                <h2>其他信息</h2>
                                                <div class="submit-review">
                                                   <ul>
                                                   	<li><p>游戏名称: ${goods.name}</p></li>
                                                   	<li><p>游戏类型: ${goods.gameType}</p></li>
                                                   	<li><p>上架日期: ${goods.addedDate}</p></li>
                                                   	<li><p>销售状态: 正常</p></li>
                                                   	<c:if test="${goods.status}== 1">
														<li><p style="color:red">销售状态: 下架</p></li>
													</c:if>
                                                   </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>                    
                </div>
            </div>
        </div>
    </div>
	
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
