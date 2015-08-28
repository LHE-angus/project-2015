<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html lang="en">
<head>
</head>

<body>

<div class="conjtainer"><!-- Menu button for smallar screens -->
<div class="navbar-header">
<button class="navbar-toggle btn-navbar" type="button"
	data-toggle="collapse" data-target=".bs-navbar-collapse"><span></span>
</button>
<!-- Site name for smallar screens --> <a href="index.html"
	class="navbar-brand hidden-lg"></a></div>



<!-- Navigation starts --> <nav
	class="collapse navbar-collapse bs-navbar-collapse" role="navigation">

<ul class="nav navbar-nav">

	<!-- Upload to server link. Class "dropdown-big" creates big dropdown -->
	<li class="dropdown dropdown-big"><a href="#"
		class="dropdown-toggle" data-toggle="dropdown"><span
		class="label label-success"><i class="icon-cloud-upload"></i> </span><strong>BI决策分析系统</strong></a>
	</li>
</ul>
 <ul class="nav navbar-nav pull-right">
          <li class="dropdown pull-right">            
            <a   href="${ctx}/manager/admin/Frames3.do?method=index">
              <i class="icon-user"></i>返回 <b class="caret"></b>              
            </a>
          </li>
          
        </ul>
</nav></div>



</body>
</html>