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
	#rowDiv{
		margin:10px 1px 30px 100px;
	}
	#rowDiv table{
		min-width: 900px;
		text-align:center
		
	} 
	#rowDiv table th{
		text-align:center
	}
	
	.but{
		margin:0px 0px 20px 0px;
		background-color: #638cc7;
	    border: none;
	    color: white;
	    padding: 12px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	}
	#formDiv{
	 	display: none; 
	 	margin:30px 0px 0px 0px;
	 	border: 1px solid #d1dee2;
	}
	#formDiv p{
		display:inline;
		color: red;
	    font-size: 16px;
	}
	#formDiv input{
		display:inline;
	}
	
	#formDiv h3{
		display:inline;
	}
	

	
	</style>
	
  </head>
  
  <body>
  	<jsp:include page="partpage/admin-head.jsp" />
  	
	<script type="text/javascript">
	var g_OptUserId;
	var g_Tips1;
	var g_Tips2;
	
  	$(document).ready(function(){
  		
		$("#list button").click(function(){
			var userid = $(this).attr("name");
			$("#formDiv").slideDown("slow",function(){
				//用回调改 更安全
				$("#formDiv h3:first").text("<!> 对ID=["+userid+"]的用户进行操作");
				$("#bolckbut").attr("name",userid);
				$("#acceptbut").attr("name",userid);
				$("#restbut").attr("name",userid);
			});
			
		});
		
		$("#bolckbut").click(function(){
			var userid = $(this).attr("name");
			if(confirm("确定禁止用户 ["+userid+"] 登陆?")){
				g_OptUserId = userid;
				g_Tips1 = "<!>已屏蔽用户 ["+userid+"] 登陆";
				
				$.get("ajax/managerAjax-blockUser",{userId: userid},callBack);
			}
	
		});
		
		$("#acceptbut").click(function(){
			var userid = $(this).attr("name");
			if(confirm("<!>允许用户 ["+userid+"] 登陆?")){
				g_OptUserId = userid;
				g_Tips1 = "<!>已允许用户 ["+userid+"] 登陆";
				
				$.get("ajax/managerAjax-acceptUser",{userId: userid},callBack);
			}
		});

		
		$("#restbut").click(function(){
			
			if( validatUpPassword() ){
				var userid = $(this).attr("name");
				if(confirm("确定为用户 ["+userid+"] 重置密码?")){
					var newPass = $("#password1").val();
					$.get("ajax/managerAjax-restUserPwd",{userId: userid,userNewPwd: newPass},
							function(data){
						if(data == "ok"){
							$("#tips2").text("<!>已为用户 ["+userid+"] 重置密码");
							$("#"+userid+"-userPassword").text("---------<已被重置>---------");
							
							$("#password1").val("");
							$("#password2").val("");
						}else{
							$("#tips2").text("<!>更改失败!");
						}
						
					});
				}
			}
		});
		
  	 
  	  	
	}); 
  	
  	function callBack(data){
  		console.log(data);
  		if(data == "ok"){
  			$("#"+g_OptUserId+"-status").text("mod");
  			$("#tips1").text(g_Tips1);
  		}else{
  			$("#tips1").text("<!>更改失败!");
  		}
  		
  	}
  	
  	
  	function validatUpPassword(){
  		var password1 = $("#password1").val();
  		var password2 = $("#password2").val();
  		if ( ( /^\w{6,16}$/.test(password1) ) ){
 			if(password1 === password2){
 				
 				return true;
	  	  	}else{
	  			$("#tips2").text("<!>两次输入密码不一致.");
	  			
	  			return false;
	  		}
  		}else{
  			$("#tips2").text("<!>请输入六位以上的字母,数字-.-");
  			
  			return false;
  		}
  	}
	</script>
	
	
	
	<div style="width: 80%;  margin:100px auto; ">  

		<div style="margin: 80px 35px 1px;" >
			<p>当前: <span style="color:red"> ${pageInf.hint} </span></p>
		</div>
		
		<div id="rowDiv">
			<table id= "list" width="80%" border="1">
			  <tr>
			    <th>ID</th>
			    <th>用户名</th>
			    <th>密码</th>
			    <th>电子邮件</th>
			    <th>余额</th>
			    <th>注册日期</th>
			    <th>状态</th>
			    <th>管理</th>
			  </tr>
			  <c:forEach items="${userList}" var="user" varStatus="step">
			  <tr>
			    <td> ${user.id} </td>
			    <td> ${user.userName} </td>
			    <td id="${user.id}-userPassword"> ${user.userPassword} </td>
			    <td> ${user.email} </td>
			    <td> ${user.money} </td>
			    <td> ${user.regDate} </td>
			    <td id="${user.id}-status"> ${user.status == 1?'禁止登陆': '正常' } </td>
				<c:choose>
				   <c:when test="${(step.index%2)==0}">
				   		<td align="left"><button type="button" name="${user.id}" >修改</button></td>
				   </c:when>
				   <c:otherwise>
				   		<td align="right"><button type="button" name="${user.id}" >修改</button></td>
				   </c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
				 <tr>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
				 </tr>
				  <tr>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
				 </tr>
			</table>
			
			<div id="formDiv" >
				<br />
				<h3 style="color: red"></h3><br /> <br />
				
				<input id="bolckbut" class="but" type="button" name="" value="禁止登陆"> </input>
				<input id="acceptbut" class="but" type="button" name=""  value="允许登陆"> </input>
				<p id="tips1" ></p><br />
				
				<h3>重置其密码</h3> <p id="tips2" > </p><br />
				
				<label>新密码:</label> <input id="password1" type="password"  name="userDto.newPassword" maxlength="16" /> 
			  	<label>重复:</label> <input id="password2" type="password" name="userDto.newPassword2" maxlength="16" /> 
				<input id="restbut" class="but" type="button" name="" value="提交 "> </input>
			</div>
			
		</div>  
		
	
	</div>  

	
	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
