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
    
    <title>管理员 - 登陆页 </title>
    
    
    <link href="img/logo.png" type=image/x-icon rel="shortcut icon" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    
	<link rel="stylesheet" href="css/login.css">
	
	<style type="text/css">
		#pe{
			width: 400px;
			height: 200px;
			margin-left: auto;
  			margin-right:auto;
  			margin-top:80px ;
  			margin-bottom:150px;
  			
		}

	</style>
	
  </head>
  
  <body>
  	
	<script type="text/javascript">
  	$(document).ready(function(){

	  	  ;
	}); 

	</script>
	
	
	<div class="product-big-title-area">
     <div class="container">
         <div class="row">
             <div class="col-md-12">
                 <div class="product-bit-title text-center">
                     <h2> 管理员-登陆 </h2>
                 </div>
             </div>
         </div>
     </div>
 </div>
	
	<div id="pe" >  
		  <div class="theLoginBox">
		   
		    <div class="theLoginArea" id="loginBox">
		    
		      <form id="loginForm" action="action/manager-login"" method="post">
		      	<input type="hidden" name="flag" value="${flag}">
		        <p style="position: relative;">
		          <label for="LoginForm_name">用户名：</label> 
		          <input placeholder="请输入您的账号" name="name" id="LoginForm_name" type="text" maxlength="30" />
		        <p style="position: relative;">
		          <label for="LoginForm_password">密码：</label>
		          <input placeholder="请输入您的密码" name="password" id="LoginForm_password" type="password" maxlength="30" />
  					<div>
  						<span style="color:red"> ${errorMsg} </span> 
  						<br /><br />
  					</div>
  					
		        <div class="loginSubmitBnt fixPadding">
		          <div>
		            <div class="login_submit">
		              <input name="userSubmit" class="theSubmitButton" value="" type="submit" />
		          
		            </div>
		          </div>
		        </div>
		      </form>
		      
		    </div>
		  </div>
	</div>
	
	

	 <div class="footer-top-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row" >
            	<div id="tailNav"> 
	            	<table border="0">
					  <tr>
					    <td>&nbsp;</td>
					  
					    <td>&nbsp;&nbsp;-&nbsp;&nbsp;</td>
					    <td><a href="action/goods-turnIndex">返回主页</a></td>
					  </tr>
					</table>
				</div>
            </div>
            
        </div>
    </div> 
    
  </body>
</html>