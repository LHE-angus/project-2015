<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div>
  <h3 align="center" ><strong class="fb">门店查看</strong></h3>
  </div>
    <div class="rtabcont2">
	<html-el:form action="/paragon/KonkaParagonShowinfo" enctype="multipart/form-data">
		<html-el:hidden property="show_shop_id" styleId="show_shop_id" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
			<tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">门店代码：</td>
		       <td><c:out value="${af.map.show_shop_code}" /></td>
		    </tr>
		    <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">门店名称：</td>
		       <td><c:out value="${af.map.show_shop_name}" /></td>
		    </tr>
		     <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">所属区域：</td>
		       <td>
	      		   <c:choose>
						<c:when test="${af.map.area_id eq 10}">华东</c:when>
						<c:when test="${af.map.area_id eq 20}">山东</c:when>
						<c:when test="${af.map.area_id eq 30}">东北</c:when>
						<c:when test="${af.map.area_id eq 40}">华北</c:when>
						<c:when test="${af.map.area_id eq 50}">华南</c:when>
						<c:when test="${af.map.area_id eq 60}">西南</c:when>
						<c:when test="${af.map.area_id eq 70}">华中</c:when>
						<c:when test="${af.map.area_id eq 80}">西北</c:when>
					</c:choose>
			  </td>
		    </tr>
		    <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">所属分公司：</td>
		       <td><c:out value="${af.map.map.part_name}" /></td>
		    </tr>
		     <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">所属经办：</td>
		       <td><c:out value="${af.map.channel_name}" /></td>
		    </tr>
		    <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">客户名称：</td>
		       <td><c:out value="${af.map.custom_name}" /></td>
		    </tr>
		    <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">客户类别：</td>
		       <td>
		           <c:choose>
						<c:when test="${af.map.custom_type eq 1}">连锁</c:when>
						<c:when test="${af.map.custom_type eq 2}">超市</c:when>
						<c:when test="${af.map.custom_type eq 3}">县乡客户群</c:when>
						<c:when test="${af.map.custom_type eq 4}">城市客户群</c:when>
						<c:otherwise>城市专卖店</c:otherwise>
					</c:choose>
			  </td>
		    </tr>
		    <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">有无直销员：</td>
		       <td>
			       	<c:if test="${af.map.if_seller eq 0}">无直销员</c:if>
			        <c:if test="${af.map.if_seller eq 01}">有直销员</c:if>
		       </td>
		    </tr>
		     <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">数据上报员：</td>
		       <td>
			       	<c:if test="${empty entity1.map.sales_name}">未指定</c:if>
			        <c:if test="${not empty entity1.map.sales_name}">${entity1.map.sales_name}</c:if>
		       </td>
		    </tr>
		     <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">添加人：</td>
		       <td><c:out value="${af.map.map.user_name}" /></td>
		    </tr>
		     <tr >
		       <td height="28" width="15%" nowrap="nowrap" class="title_item">添加时间：</td>
		       <td><fmt:formatDate value="${af.map.addtime}" pattern="yyyy-MM-dd"/> </td>
		    </tr>
			</table>
			 <div>
			     <br />
			        <label >
			            <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
			          </label>
			  </div>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
