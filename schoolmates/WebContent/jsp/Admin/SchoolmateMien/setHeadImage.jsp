<%@ page language="java" import="java.util.*,com.schoolmates.model.*,java.lang.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
	<head>
	     <%
	    String path = request.getContextPath();
	
	    String basePath = request.getScheme() + "://"
	
	            + request.getServerName() + ":" + request.getServerPort()
	
	            + path + "/";
	     %>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>image upload</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/bootstrap.min.css">
		<script type="text/javascript" src="<%=basePath %>js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/bootstrap.min.js"></script>

	</head>
	<body>
	   <%
         ArrayList newslist=(ArrayList)request.getAttribute("newslist");
		 Integer currentPage = (Integer)request.getAttribute("page");
		 int previousPage = (currentPage-1);
		 int nextPage = (currentPage+1);
        %>
      
              
		<div style="margin-top:3em">
			<form class="form-horizontal" action="/schoolmates/SchoolmateMien/setHeadImage" enctype="multipart/form-data" method="post" >
					     	  
		         <% for (int i = 0; i < newslist.size(); i++) {
                    SchoolmateMien news = (SchoolmateMien)newslist.get(i);
                  %>
		        
				<input type="text" name="id" value="<%=news.getId() %>" style="visibility: hidden"  hidden/>
                  <%} %>
				<input type="file" name="file"/>
				<input style="margin-top: 1em" type="submit" class="btn btn-primary" value="提交" >
			</form>
		</div>
   
	</body>
</html>