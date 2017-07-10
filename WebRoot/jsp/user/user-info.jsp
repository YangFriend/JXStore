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
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="本站售商品 均为虚无缥缈 谨防受骗">
	
    <title>我的信息</title>
    
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
	#infoDiv{
		/*width:1024px;*/
		margin: 15px 0 0 0;
	    padding: 20px;
	    border: 0;
	    border: 1px dotted #5a88ca;
	    background: #f5f5f5;
	    line-height: 1.4;
	    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		margin-left:100px;
		font-size:150%;
	}
	#infoDiv button{
	    background-color: #638cc7;
	    border: none;
	    color: white;
	    padding: 12px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	
	}
	#infoDiv div{
		 margin :20px 1px;
		 
	} 
	
	#infoDiv p{
		display: inline;
	} 
	#infoDiv span{
		display:inline-block;
		margin-bottom: 5px;
		font-weight: 700;
		width:150px;
		display:-moz-inline-box;
	  
	}


	</style>
  </head>
  
  <body>
	<jsp:include page="/jsp/partpage/head.jsp" />

	<script type="text/javascript">
  	$(document).ready(function(){
  		
  		$("#ButtModifyPas").click(function(){
  			$("#DivModifyPas").slideToggle("slow");
  		});
  		
  	  	$("#upPassword").submit( function (e){
	  		return 	validatUpPassword();
	  	});
  	  	
  		//rmCookie = ok 移除记住密码的cookie
  		var rmCookie = $("#rmCookie").val();
  		if ( rmCookie == 'ok'){
  			delCookie("name_c");
  			delCookie("paw_c");
  		}
  	
	}); 
  	
  	function validatUpPassword(){
  		var password1 = $("#password1").val();
  		var password2 = $("#password2").val();
  		if ( ( /^\w{6,16}$/.test(password1) ) ){
 			if(password1 === password2){
 				
 				return true;
	  	  	}else{
	  			$("#tips").text("两次输入密码不一致.");
	  			return false;
	  		}
  		}else{
  			$("#tips").text("六位以上的字母,数字(已经很简单了-.-)");
  			return false;
  		}
  	}

	function delCookie(name){  
	    var exp = new Date();  
	    exp.setTime(exp.getTime() - 1);  
	    var cval=getCookie(name);
	    if(cval!=null)  
	        document.cookie= name + "="+cval+";expires="+exp.toGMTString();  
	}  
	function getCookie(name)  
	{  
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");  
	    if(arr=document.cookie.match(reg))  
	        return (arr[2]);  
	    else  
	        return null;  
	} 
	</script>
	<%

		String agent = request.getHeader("User-Agent");
		String browInfo[] = agent.split(";");
		String osInfo = browInfo[1].trim();//浏览器信息
		String ip = request.getRemoteAddr();//客户端ip地址
		pageContext.setAttribute("ip", ip);
		pageContext.setAttribute("osInfo", osInfo);
		
	%>
	<div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>我的信息</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
	
	<div style=" margin:100px auto; "> 
	 
		<div id="infoDiv" >  
			<input type="hidden" value="<%=request.getAttribute("rmCookie")%>" id="rmCookie"/>
			 <div> <span> 用户名：</span> <p>${userInformation.userName} </p> </div>
			 <div> <span> 电子邮件：</span> <p>${userInformation.email}  </p> </div>
			 <div> <span> 账户余额：</span> <p>${userInformation.money} </p> </div>
			 <div><span> 密码：</span> <p> ${fn:substring(userInformation.userPassword, 0, 8)}*********[已加密]*******</p>
			 	<button id="ButtModifyPas" type="button" >修改密码</button> 
			 	 </div>
			 <div id="DivModifyPas" style="display: none; margin-left:80px;  border: 1px solid #d1dee2;">
			 	<form id="upPassword" action="action/user-upPassword" method="post">
				  <div> 
				  	<label>验证原密码:</label> <input type="password" name="userDto.oldPassword" maxlength="16" />
				  	 
				  </div>
				  <div> 
				  	<label>输入新密码:</label> <input id="password1" type="password"  name="userDto.newPassword" maxlength="16" /> 
				  	<label>重复:</label> <input id="password2" type="password" name="userDto.newPassword2" maxlength="16" /> 
				  	<button type="submit"> 提交 </button>
				  </div>
			 	</form>
			  </div>
			<div> <span> 注册日期：</span> <p>${userInformation.regDate}</p></div>
		 	<div> <span> 登陆状态</span> <p >${ip} / ${osInfo} </p> </div>
		
			<div> <p id="tips" style="color:red;" > ${errorMsg} &nbsp;</p></div>
		</div>
		
	</div>  
		  
	<jsp:include page="/jsp/partpage/tail.jsp" />
  </body>
</html>
