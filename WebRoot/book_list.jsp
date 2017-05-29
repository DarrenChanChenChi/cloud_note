<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <% --%>
<%-- String path = request.getContextPath(); --%>
<%-- String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; --%>
<%-- %> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta charset="utf-8"/>
    <title>My JSP 'book_list.jsp' starting page</title>

  </head>
  
  <body>
  	<table>
  		<tr>
  			<td>笔记本名</td>
  			<td>创建时间</td>
  			<td>所属用户</td>
  		</tr>
  		<c:forEach items="${result.data}" var="book">
  		<tr>
  			<td>${book.cn_notebook_name }</td>
  			<td>${book.cn_notebook_createtime }</td>
  			<td>${book.user.cn_user_name }</td>
  		</tr>
  		</c:forEach>
  	</table>
  </body>
</html>
