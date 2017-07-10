<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Latest jQuery form server -->
	<script src="https://code.jquery.com/jquery.min.js"></script>
	
	<script type="text/javascript">
	  $(document).ready(function(){
		  $(".product-count").load("ajax/cartAjax-getCount");
		});
		</script>
    
    <div class="header-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="user-menu">
                        <ul>
                        <c:choose>
					    <c:when test="${!empty userInformation.id}">
					        <li><a href="action/user-info"><i class="fa fa-user"></i>欢迎您[${userInformation.userName}]</a></li>
                            <li><a href="action/cart-checkout"><i class="fa fa-user"></i> 我的购物车</a></li>
                            <li><a href="action/user-order"><i class="fa fa-user"></i> 我的订单</a></li>
                            <li><a href="action/user-logout" > <i class="fa fa-user"></i> 登出</a></li> 
					    </c:when>
					     <c:otherwise>
					 		<li><a href="action/user-login?flag=turn"><i class="fa fa-user"></i> [请登录]</a></li>
                            <li><a href="action/cart-checkout"><i class="fa fa-user"></i> 我的购物车</a></li>
                            <li><a href="action/user-order"><i class="fa fa-user"></i> 我的订单</a></li>
                            <li><a href="action/user-login?flag=turn" > <i class="fa fa-user"></i> 登陆/注册</a></li> 
					     </c:otherwise>
						</c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <!-- End header area -->
    
    <div class="site-branding-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="logo">
                        <h1><a href="./"><img src="img/logo.png"></a></h1>
                    </div>
                </div>
                
                <div class="col-sm-6">
                    <div class="shopping-item">
                        <a href="action/cart-checkout">购物车<!-- - <span class="cart-amunt">$100</span>  --> <i class="fa fa-shopping-cart"></i> <span class="product-count">0</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <!-- End site branding area -->
    
    <div class="mainmenu-area">
     <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div> 
                <div class="navbar-collapse collapse" > <!--  style="border-style:solid;border-width:1pt; border-color:#638cc7; border-radius: 5px; -->
                    <ul class="nav navbar-nav">
                        <li><a href="action/goods-turnIndex">主页</a></li>
                        <li><a href="action/goods-all?pageNow=1">全部游戏</a></li>
                        <li><a href="action/cart-checkout">购物车</a></li>
                    </ul>
                </div>  
            </div>
        </div>
    </div> 
    <!-- End mainmenu area -->
    
    
    
    
    
    