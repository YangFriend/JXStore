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
  	<jsp:include page="partpage/admin-head.jsp" />
  	
	<script type="text/javascript">
  	$(document).ready(function(){

	  	  ;
	}); 

	</script>
	
	
	
	<div style="width: 80%;  margin:100px auto; ">  
	
	<div 
		<span> 分类索引:</span>  <a href="action/manager-goods?display=sell&page=1">仅在售 </a>&nbsp; / &nbsp;<a href="action/manager-goods?display=drop&page=1">已下架</a> &nbsp; / &nbsp;<a href="action/manager-goods?display=all&page=1">全部</a> 
	</div>
	
	<div style="margin: 80px 35px 1px;" >
		<p>当前显示: <span style="color:red">${pageInf.hint} </span> - 第 ${pageInf.page} 页   -  共 ${pageInf.pageCount} 页 </p>
	
	</div>

		<div id="rowDiv">
			<table width="80%" border="1">
			  <tr>
			    <th>ID</th>
			    <th>游戏名</th>
			    <th>类型</th>
			    <th>价格</th>
			    <th>库存</th>
			    <th>上架日期</th>
			    <th>介绍</th>
			    <th>状态</th>
			    <th>管理</th>
			  </tr>
			  <c:forEach items="${goodsList}" var="goods" varStatus="step">
				  <tr>
				    <td>${goods.id}</td>
				    <td>${goods.name}</td>
				    <td>${goods.gameType}</td>
				    <td>${goods.price}</td>
				     <td>${goods.surplus}</td>
				    <td>${goods.addedDate}</td>
				    <td width="200" >${fn:substring(goods.info, 0, 24)}...</td>
				    <td>${goods.status == 1?'<span style="color:red">下架</span>': '在售' }</td>
				 	<c:choose>
					   <c:when test="${(step.index%2)==0}">
					   		<td align="left"> <a href="action/manager-modGoods?goodsId=${goods.id}">修改</a></td>
					   </c:when>
					   <c:otherwise>
					   		<td align="right"> <a href="action/manager-modGoods?goodsId=${goods.id}"">修改</a></td>
					   </c:otherwise>
					</c:choose>
				  </tr>
			  </c:forEach>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			
			<div class="row">
                <div class="col-md-12">
                    <div class="product-pagination text-center">
                        <nav>
                          <ul class="pagination">
                           <c:if test="${pageInf.page-1 > 0 }" >
                            <li>
		                       <a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page-1}" aria-label="Previous">
		                        <span aria-hidden="true">&laquo;</span>
		                       </a>
                          	</li>
                           </c:if>
                             <c:if test="${pageInf.page-1 > 0 }" >
                            	<li><a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page-1}">${pageInf.page-1}</a></li>
                            </c:if>
                            	<li><a href="#" style="color: black;" onclick="return false">  ${pageInf.page} </a> </li>
                            <c:if test="${pageInf.page+1 <= pageInf.pageCount }" >
                            	<li><a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page+1}">${pageInf.page+1}</a></li>
                            </c:if>
                             <c:if test="${pageInf.page+2 < pageInf.pageCount }" >
                            	<li><a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page+2}">${pageInf.page+2}</a></li>
                            </c:if>
                              <c:if test="${pageInf.page+3 < pageInf.pageCount }" >
                            	<li><a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page+3}">${pageInf.page+3}</a></li>
                            </c:if>
                            
                            <c:if test="${pageInf.page+1 <= pageInf.pageCount }" >
								<li><a href="action/manager-goods?display=${pageInf.display}&page=${pageInf.page+1}" aria-label="Next">
		                         <span aria-hidden="true">&raquo;</span>
		                    	</a></li>
							</c:if>
                          </ul>
                        </nav>                        
                    </div>
                </div>
            </div>
			
		</div>  
	</div>  
	<div style="margin:0px 0px 20px 100px;">
		<a href="action/manager-upGoods" class="but">上架游戏? </a> 
	</div>


	<jsp:include page="partpage/admin-tail.jsp" />
  </body>
</html>
