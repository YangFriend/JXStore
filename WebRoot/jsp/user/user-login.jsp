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
    <title>用户登陆/注册</title>
    
    <link href="img/logo.png" type=image/x-icon rel="shortcut icon" />
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    
	<link rel="stylesheet" href="css/login.css">
	
  </head>
  
  <body>
  <%
  
  	//读取cookie 到pageContext
  	Cookie[] cookies =request.getCookies();
  	if(cookies != null ){
		for(Cookie c : cookies){
			if (c.getName().equals("name_c")){
				pageContext.setAttribute("name_c", c.getValue());
			}
			if (c.getName().equals("paw_c")){
				pageContext.setAttribute("paw_c", c.getValue());
			}
		}
	}
  	
  	
  	
  %>
	<jsp:include page="/jsp/partpage/head.jsp" />
	
	<script type="text/javascript">
	var g_checkPostContent = "";
	var g_isCommit = false;
  	$(document).ready(function(){
  
  		$(".tab").click(function(){
  			var X=$(this).attr('id');
  			if(X=='signup'){
  				$("#login").removeClass('select');  
  				$("#signup").addClass('select');  
  				$("#loginbox").slideUp();  
  				$("#signupbox").slideDown();
  			}else{  
  				$("#signup").removeClass('select');  
  				$("#login").addClass('select');  
  				$("#signupbox").slideUp();  
  				$("#loginbox").slideDown();  
  			}
  			return false
  		}); 
	  	
  		$("#signupForm").submit( function (e){
	  		return checkSignupForm();
	  	});
	  	
	  
	  	
 	  	$("#signupForm input").blur( function (){ 
 	  		checkSignupForm();
 	 	});
	  	  
	}); 
  	
  	
  	function checkSignupForm(){
  		var signupForm_username = $("#SignupForm_username").val();
  		var signupForm_password = $("#SignupForm_password").val();
  		var signupForm_password2 = $("#SignupForm_password2").val();
  		var signupForm_email = $("#SignupForm_email").val();
  		
  		if ( ( /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{3,16}$/.test(signupForm_username) ) ){
  			if (g_checkPostContent  != signupForm_username){
  				
	  			$.post("ajax/userAjax-checkName",{name:signupForm_username},
	  					function(data){
	  						if(data == 'false'){
	  							$("#nTips").css("color","ForestGreen");
	  							$("#nTips").text("√恭喜该用户名还未被注册");
	  							g_checkPostContent = signupForm_username;
	  							g_isCommit = true;
	  						}else{
	  							$("#nTips").css("color","red");
	  				  			$("#nTips").text("×抱歉该用户名已被注册");
	  				  			g_isCommit = false;
	  						}
	  			});
  			}
  		}else{
  			$("#nTips").css("color","red");
  			$("#nTips").text("请输入 三个以上的中文,字母,数字...");
  			g_isCommit = false;
  		}
  		
		if ( ( /^\w{6,16}$/.test(signupForm_password) ) ){
			$("#pTips").text("");
 			if(signupForm_password === signupForm_password2){
 				$("#p2Tips").text("");
 				g_isCommit = true;
	  	  	}else{
	  			$("#p2Tips").text("两次输入密码不一致.");
	  			g_isCommit = false;
	  		}
  		}else{
  			$("#pTips").text("六位以上的字母,数字(已经很简单了-.-)");
  			g_isCommit = false;
  		}
		
  		if(signupForm_email != ""){
  			if ( ( /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(signupForm_email) ) ){
  	  			$("#eTips").text("");
  	  			g_isCommit = true;
  	  		}else{
  	  			$("#eTips").text("请输入正确的电子邮件地址.");
  	  			g_isCommit = false;
  	  		}
  		}
  		return g_isCommit;
  		
  	};
	</script>



	<div style="width: 80%; margin:100px auto; ">  
		<div id="container">  
			<div id="tabbox"> 
				<input id="login" type="button" class="tab select"  value= 登陆 />
				<input id="signup" type="button" class="tab " value=注册 />
			</div>  
			
			<div id="panel">  
				<div id="loginbox">
					<div class="theCenterBox" style="">
					  <div class="theLoginBox">
					    <div class="loginTxt">登录</div>
					    <div class="theLoginArea" id="loginBox">
					      <form id="loginForm" action="action/user-login"" method="post">
					        <p style="position: relative;">
					          <label for="LoginForm_name">用户名：</label> 
					          <input placeholder="请输入您的账号" name="userName" value="${name_c}" id="LoginForm_name" type="text" maxlength="30" />
<%--					          <span>请输入您的账号</span>--%> </p>
					        <p style="position: relative;">
					          <label for="LoginForm_password">密码：</label>
					          <input placeholder="请输入您的密码" name="password" value="${paw_c}" id="LoginForm_password" type="password" maxlength="30" />
<%--					          <span>请输入您的密码</span> --%></p>
	    					<span style="color:red"> ${errorMsg}</span> 
					        <div class="loginSubmitBnt fixPadding">
					          <div>
					            <input id="autoLogin" class="theRememberMe" name="rememberMe" value="1"  type="checkbox" />
					            <label class="theRememberMeLabel" for="autoLogin">记住登陆状态(勿在不受信任的电脑勾选)</label>
					            <div class="login_submit">
					              <input name="userSubmit" class="theSubmitButton" value="" type="submit" />
					          
					            </div>
					          </div>
					        </div>
					      </form>
					    </div>
					  </div>
					</div>
				</div>  
				
				<div id="signupbox">
					<div id="tabbox"> 
					
						<div class="theCenterBox" style="">
						  <div class="theLoginBox" >
						    <div class="loginTxt">注册账号</div>
						    <form id="signupForm" action="action/user-signup" method="post"> 
						      <div class="theLoginArea" id="loginBox">
						    	<p style="position: relative;">
						          <label for="LoginForm_username"> <font> *</font>用户名： </label> <font id="nTips"> </font>
						          <input placeholder="请输入您的用户名[必填]" name="userDto.userName" id="SignupForm_username" type="text" maxlength="16" />
						          <span>请输入您的用户名</span> </p>
						        <p style="position: relative;">
						          <label for="LoginForm_password"><font> *</font>密码：</label> <font id="pTips"></font>
						          <input placeholder="请输入您的密码[必填]" name="userDto.password" id="SignupForm_password" type="password" maxlength="16" />
						          <span>请输入您的密码</span> </p>
						        <p style="position: relative;">
						          <label for="LoginForm_checkpaw"><font> *</font>重复：</label> <font id="p2Tips"></font>
						          <input placeholder="重复您的密码[必填]" name="userDto.password2" id="SignupForm_password2" type="password" maxlength="16" />
						          <span>重复输入您的密码</span> </p>
						        <p style="position: relative;">
						          <label for="LoginForm_email">邮箱：</label> <font id="eTips"></font> 
						          <input placeholder="因大多数的邮件服务商的反垃圾机制,也就不验证了,你可以不输入." name="userDto.email" id="SignupForm_email" type="text" maxlength="50" />
						          <span>请输入您的邮箱</span> </p> 
						        <div class="loginSubmitBnt">
						          <div class="reg_submit">
						         	<input name="userSubmit" class="theSubmitButton" value="" type="submit" />
						         	 <span style="position: relative; right: -130px; top: -30px; color:red" id="subTips"> </span>
						          </div>
						       
						        </div>
						      </div>
						      
						    </form>
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
