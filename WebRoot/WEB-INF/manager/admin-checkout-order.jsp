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
		min-width: 770px;
		text-align:center
		
	} 
	#rowDiv table th{
		text-align:center
	} 
	</style>
	
  </head>
  
  <body>
  	<jsp:include page="partpage/admin-head.jsp" />
  	
	<script type="text/javascript">
  	$(document).ready(function(){

	  	  ;
	}); 

	</script>
	
	
	
	<div style="width: 80%;  margin:100px auto; ">  

	
	<div style="margin: 80px 35px 1px;" >
		<p>当前显示: 第 <span style="color:red">${pageInf.page}</span>页 - 共${pageInf.pageCount}页</p>
	</div>
	
		<div id="rowDiv">
			<table id="or" width="100%" border="1" rules="all" align="center" >
			  <tr>
			    <th width="16%" align="center" scope="col">订单号</th>
			    <th align="center" scope="col">下单用户ID</th>
			    <th align="center" scope="col">下单日期</th>
			    <th align="center" scope="col">总计金额</th>
			    <th align="center" valign="middle" scope="col">实付金额</th>
			    <th scope="col">游戏</th>
			    <th scope="col">账号</th>
			  </tr>
			  <c:forEach items="${orderList}" var="order">
			  <tr>
			    <td align="center">${order.id }</td>
			    <td align="center">${order.user.id}</td>
			    <td align="center">${order.orderDate}</td>
			    <td align="center">${order.allValue}</td>
			    <td align="center" >${order.actPrice}</td>
			    <td align="center"><a href="action/manager-orderDetail?orderId=${order.id}" >共${order.goodsNum}个 </a> </td>
			    <td align="center"><a href="action/manager-orderDetail?orderId=${order.id}">共${order.accountNum}个 </a> </td>
			  </tr>
			 </c:forEach>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td align="center">&nbsp;</td>
			    <td align="center">&nbsp;</td>
			    <td align="center" >&nbsp;</td>
			     <td align="center" >&nbsp;</td>
			    <td align="center"><a href="#" >&nbsp;</a> </td>
			    <td align="center"><a href="#" >&nbsp;</a> </td>
			  </tr>
			    <tr>
			     <td align="center" >&nbsp;</td>
			    <td align="center">&nbsp;</td>
			    <td align="center">&nbsp;</td>
			    <td align="center">&nbsp;</td>
			    <td align="center" >&nbsp;</td>
			    <td align="center"><a href="#" >&nbsp;</a> </td>
			    <td align="center"><a href="#" >&nbsp;</a> </td>
			  </tr>
			
			</table>
		</div>  
	</div>  
	
	<div class="row">
              <div class="col-md-12">
                  <div class="product-pagination text-center">
                      <nav>
                        <ul class="pagination">
                         <c:if test="${pageInf.page-1 > 0 }" >
                          <li>
		                       <a href="action/manager-checkoutOrder?page=${pageInf.page-1}" aria-label="Previous">
		                        <span aria-hidden="true">&laquo;</span>
		                       </a>
                        	</li>
                         </c:if>
                           <c:if test="${pageInf.page-1 > 0 }" >
                          	<li><a href="action/manager-checkoutOrder?page=${pageInf.page-1}">${pageInf.page-1}</a></li>
                          </c:if>
                          	<li><a href="#" style="color: black;" onclick="return false">  ${pageInf.page} </a> </li>
                          <c:if test="${pageInf.page+1 <= pageInf.pageCount }" >
                          	<li><a href="action/manager-checkoutOrder?page=${pageInf.page+1}">${pageInf.page+1}</a></li>
                          </c:if>
                           <c:if test="${pageInf.page+2 < pageInf.pageCount }" >
                          	<li><a href="action/manager-checkoutOrder?page=${pageInf.page+2}">${pageInf.page+2}</a></li>
                          </c:if>
                            <c:if test="${pageInf.page+3 < pageInf.pageCount }" >
                          	<li><a href="action/manager-checkoutOrder?page=${pageInf.page+3}">${pageInf.page+3}</a></li>
                          </c:if>
                          
                          <c:if test="${pageInf.page+1 <= pageInf.pageCount }" >
						<li><a href="action/manager-checkoutOrder?page=${pageInf.page+1}" aria-label="Next">
                         <span aria-hidden="true">&raquo;</span>
                    	</a></li>
					</c:if>
                        </ul>
                      </nav>                        
                  </div>
              </div>
          </div>
	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
