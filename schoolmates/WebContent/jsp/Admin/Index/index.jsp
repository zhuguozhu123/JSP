<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<title>Title Page</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
		<link rel="Shortcut Icon" href="<%=basePath%>res/favicon.icon">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/backgroundAdmin/router.js" ></script>
		<style>
			#main-nav {
	            margin-left: 1px;
	        }
 
            #main-nav.nav-tabs.nav-stacked > li > a {
                padding: 10px 8px;
                font-size: 12px;
                font-weight: 600;
                color: #4A515B;
                background: #E9E9E9;
                background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FAFAFA), color-stop(100%,#E9E9E9));
                background: -webkit-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
                background: -o-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
                background: -ms-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
                background: linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
                -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
                border: 1px solid #D5D5D5;
                border-radius: 4px;
            }
 
            #main-nav.nav-tabs.nav-stacked > li > a > span {
                color: #4A515B;
            }

            #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
                color: #FFF;
                background: #3C4049;
                background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#4A515B), color-stop(100%,#3C4049));
                background: -webkit-linear-gradient(top, #4A515B 0%,#3C4049 100%);
                background: -o-linear-gradient(top, #4A515B 0%,#3C4049 100%);
                background: -ms-linear-gradient(top, #4A515B 0%,#3C4049 100%);
                background: linear-gradient(top, #4A515B 0%,#3C4049 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
                -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
                border-color: #2B2E33;
            }

            #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
                color: #FFF;
            }
 
            #main-nav.nav-tabs.nav-stacked > li {
                margin-bottom: 4px;
            }
 
	        /*定义二级菜单样式*/
	        .secondmenu a {
	            font-size: 10px;
	            color: #4A515B;
	            text-align: center;
	        }
	 
	        .navbar-static-top {
	            background-color: #212121;
	            margin-bottom: 5px;
	        }
	 
	        .navbar-brand {
	            background: url('') no-repeat 10px 8px;
	            display: inline-block;
	            vertical-align: middle;
	            padding-left: 50px;
	            color: #fff;
	        }


	        @font-face {
			  font-family: 'Glyphicons Halflings';
			  src: url('../fonts/glyphicons-halflings-regular.eot');

			.glyphicon {
			  position: relative;
			  top: 1px;
			  display: inline-block;
			  font-family: 'Glyphicons Halflings';
			  -webkit-font-smoothing: antialiased;
			  font-style: normal;
			  font-weight: normal;
			  line-height: 1;
			  -moz-osx-font-smoothing: grayscale;
			}
		</style>
	</head>
	<body>
		<!--
			左边菜单栏
		-->
		<div class="navbar navbar-duomi navbar-static-top" role="navigation">
	        <div class="container-fluid">
	            <div class="navbar-header">
	                <a class="navbar-brand" href="#" id="logo">中大南方校友网管理后台
	                </a>
	            </div>
	        </div>
	    </div>
	    <div class="container-fluid">
	        <div class="row">
	            <div class="col-md-2">
	                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
	                    <li class="nav-list" name="index">
	                        <a href="#">
	                            <i class="glyphicon glyphicon-th-large"></i>
	                            首页         
	                        </a>
	                    </li>
	                    <li class="nav-list" name="system">
	                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-cog"></i>
	                            系统管理
	                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
	                        </a>
	                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                            <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-list active" name="../NewsCenter">
	                        <a href="javascript:void(0)" onclick="router.action('/schoolmates/NewsCenter/index')">
	                            <i class="glyphicon glyphicon-credit-card"></i>
	                            新闻模块       
	                        </a>
	                    </li>
	 
	                    <li class="nav-list" name="../SchoolmateOrganize">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmateOrganize/index')">
	                            <i class="glyphicon glyphicon-globe"></i>
	                            校友组织模块
	                            <!-- <span class="label label-warning pull-right">5</span> -->
	                        </a>
	                    </li>
	 
	                    <li class="nav-list" name="../SchoolmateActivity">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmateActivity/index')">
	                            <i class="glyphicon glyphicon-calendar"></i>
	                            校友活动模块
	                        </a>
	                    </li>

	                    <li class="nav-list" name="../SchoolmateRes">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmateRes/index')">
	                            <i class="glyphicon glyphicon-calendar"></i>
	                            校友资源共享模块
	                        </a>
	                    </li>
	                    <li class="nav-list" name="../SchoolmateMien">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmateMien/index')">
	                            <i class="glyphicon glyphicon-calendar"></i>
	                            校友风采模块
	                        </a>
	                    </li>

	                    <li class="nav-list" name="../SchoolmateServer">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmateServer/index')">
	                            <i class="glyphicon glyphicon-fire"></i>
	                             校友服务模块
	                        </a>
	                    </li>

	                    <li class="nav-list" name="../SchoolmatePublish">
	                        <a href="javascript:void(0);" onclick="router.action('/schoolmates/SchoolmatePublish/index')">
	                            <i class="glyphicon glyphicon-fire"></i>
	                             校友期刊模块
	                        </a>
	                    </li>

	                    <li class="nav-list" name="SchoolmateServer">
	                        <a href="/schoolmates/jsp/Admin/Login/index.jsp" >
	                            <i class="glyphicon glyphicon-fire"></i>
	                             退出
	                        </a>
	                    </li>

	                </ul>
	            </div>
	            <div class="col-md-10" >
	                <iframe id="mainPage" src="/schoolmates/NewsCenter/index" frameBorder="0" width="100%" scrolling="yes" height="900"></iframe>
	            </div>
	        </div>
	    </div>		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<!-- <script src="Hello World"></script> -->
	</body>
</html>