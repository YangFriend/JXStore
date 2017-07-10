<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>购物车</title>
    
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
		.but{
			background-color: #638cc7;
		    border: none;
		    color: white;
		    padding: 12px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		}

	
	</style>

    
  </head>
  
  <body>
	<jsp:include page="/jsp/partpage/head.jsp" />
	
	<script type="text/javascript">
  	$(document).ready(function(){
		$(".nav.navbar-nav").find("[href='action/cart-checkout']").parent().addClass("active");
		
		
       }); 
	</script>

	<div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>购物车</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
         
                <div class="col-md-8" style="width: 90%;">
                    <div class="product-content-right">
                        <div class="woocommerce">
                            <form method="post" action="action/cart-updateCart">
                                <table cellspacing="0" class="shop_table cart">
                                    <thead>
                                        <tr>
                                            <th class="product-remove">&nbsp;</th>
                                            <th class="product-thumbnail">&nbsp;</th>
                                            <th class="product-name">游戏</th>
                                            <th class="product-price">价格</th>
                                            <th class="product-quantity">数量</th>
                                            <th class="product-subtotal">小计</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${cartDto.goodsL}" var="gl" varStatus="struts">
                                   			<input type="hidden" name="cartDto.goodsL[${struts.index}].id" value="${gl.id}">
                                   			<input type="hidden" name="cartDto.goodsL[${struts.index}].name" value="${gl.name}">
                                   			<input type="hidden" name="cartDto.goodsL[${struts.index}].price" value="${gl.price}">
                                   			<input type="hidden" name="cartDto.goodsL[${struts.index}].image" value="${gl.image}">
                               
	                                         <tr class="cart_item">
	                                            <td class="product-remove">
	                                                <a title="Remove this item" class="remove" href="action/cart-remove?goodsId=${gl.id}">×</a> 
	                                            </td>
	
	                                            <td class="product-thumbnail">
	                                                <a href="action/goods-detail?goodsId=${gl.id}"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="jsp/goods/img/${gl.image}"></a>
	                                            </td>
	
	                                            <td class="product-name">
	                                                <a href="action/goods-detail?goodsId=${gl.id}">${gl.name}</a> 
	                                            </td>
	
	                                            <td class="product-price">
	                                                <span class="amount">￥${gl.price}</span> 
	                                            </td>
	
	                                            <td class="product-quantity">
	                                                <div class="quantity buttons_added">
	                                                    <input type="number" name="cartDto.goodsL[${struts.index}].num" size="4" class="input-text qty text" title="Qty" value="${gl.num }" min="0" step="1">
	                                                </div>
	                                            </td>
	
	                                            <td class="product-subtotal">
	                                                <span class="amount">￥${gl.total}</span> 
	                                            </td>
	                                        </tr>
                                        </c:forEach>
                                        
                                        <tr >
                                        	<td class="product-price" >
                                                <span class="amount"></span> 
                                            </td>
                                          	<td class="product-price" colspan="2" >
                                                <span class="amount">优惠:</span> 
                                            </td>
                                            
                                               <td class="product-price">
                                                <span class="amount">￥<span style="color:green;">${cartDto.couponValue}</span></span> 
                                            </td>
                                     
                                            <td class="product-price"  >
                                                <span class="amount">合计:</span> 
                                            </td>
                                               <td class="product-price">
                                                <span class="amount">￥${cartDto.allValue}</span> 
                                            </td>
                                        </tr>
                                        
                                        <tr >
                                            <td class="product-price"  colspan="4" rowspan="2">
                                                <font color="red">${cartDto.tips} </font> 
                                            </td>
                                            <td class="product-price" align="left">
                                                <span class="amount">实付:</span> 
                                            </td>
                                               <td class="product-price">
                                                <span class="amount">￥ ${cartDto.actPrice}</span> 
                                            </td>
                                        </tr>
                                        <tr >
                                        <td class="product-price" align="left">
                                                <span class="amount">我的余额:</span> 
                                            </td>
                                               <td class="product-price">
                                                <span class="amount">  ${cartDto.userMoney}<span style="color:red"> -  ${cartDto.actPrice}</span>   = <span style="color:green;">${cartDto.userMoney - cartDto.actPrice}</span></span> 
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td class="actions" colspan="4">
                                                <div class="coupon">
                                                    <label for="coupon_code">Coupon:</label>
                                                    <input type="text" readonly="readonly" name="cartDto.couponCode" placeholder="键入优惠代码" value="${cartDto.couponCode}" id="coupon_code" class="input-text" >
                                                  
                                                    <input type="submit" value="更新cart" class="button">
                                                </div>
                                                  
                                            </td>
                                            
                                            <td colspan="2">
                                            	<input type="button" value="给算" class="but" onclick="window.location.href='action/cart-buy'">
                                             </td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                              
                            </form>
                        </div>                        
                    </div>                    
                </div>
            </div>
        </div>
    </div>
	

	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
