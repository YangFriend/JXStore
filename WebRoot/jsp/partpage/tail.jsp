<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   <!-- 尾部 start -->
       <div class="footer-top-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div id="bull" class="footer-menu">
                        <h2  style="color:Chartreuse;" ><span >公告</span></h2> 
                    </div>
                </div>
                
                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">用户导航</h2>
                        <ul>
                            <li><a href="action/user-info">我的账户</a></li>
                            <li><a href="action/user-order">我的订单</a></li>
                            <li><a href="action/user-info">修改信息</a></li>
                        </ul>                        
                    </div>
                </div>
                
                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">Manager Nav</h2>
                        <ul>
                            <li><a target="_blank"  href="action/manager-users?flag=u">管理用户</a></li>
                            <li><a target="_blank" href="action/manager-goods?flag=g">管理商品</a></li>
                            <li><a target="_blank" href="action/manager-checkoutOrder?flag=o">所有订单</a></li>
                        </ul>                        
                    </div>
                </div>
                
                   <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 style="font-family: raleway,sans-serif;font-size: 25px; font-weight: 100;color: #fff;">一厢情愿的友情链接</h2>
                        <ul>
                         	<li><a target="_blank" href="https://www.jd.com/">京东商城</a></li>
                            <li><a target="_blank" href="https://www.taobao.com/">淘宝商城</a></li>
                            <li><a target="_blank" href="http://www.w3school.com.cn">感谢W3c提供免费 高质量的技术教程</a></li>
                            <li><a target="_blank" href="http://www.w3school.com.cn/">
								<img src="http://www.w3school.com.cn/i/w3school_logo_black.gif" 
								alt="W3School 在线教程" />
								</a>
							</li>
                        </ul>                        
                    </div>
                </div>
        
            </div>
        </div>
    </div> 
    <!-- End footer top area -->
  <script type="text/javascript">
  	$(document).ready(function(){
  	    $.get("ajax/bulletinAjax-latestBulletin",function(data,status){
  	    	if(data == "error"){
  	    		$("#bull").append("<p>加载失败</p>");
  	    		return ;
  	    	}
	    	var latestBulletin = data.latestBulletin;
	    	var ul = document.createElement("ul");
	    	$("#bull").append(ul);
	    	for(inex in latestBulletin){
	    		$("#bull ul").prepend("<li> <a href='action/bulletin-detail?id="+latestBulletin[inex].id+"' > <" + latestBulletin[inex].title+ "> </a> </li>")
	    	}
	    	$("#bull ul").append("<li> <a href='action/action/bulletin-all' style='color:Aqua; text-align:right;'> 查看更多>> </a> </li>")
	    	
	    });
  	});
  
	</script>
	
    <!-- Bootstrap JS form CDN -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    
    <!-- jQuery sticky menu -->
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.sticky.js"></script>
    
    <!-- jQuery easing -->
    <script src="js/jquery.easing.1.3.min.js"></script>
    
    <!-- Main Script -->
    <script src="js/main.js"></script>
    
    <!-- Slider -->
    <script type="text/javascript" src="js/bxslider.min.js"></script>
	<script type="text/javascript" src="js/script.slider.js"></script>
     <!-- 尾部end -->

   