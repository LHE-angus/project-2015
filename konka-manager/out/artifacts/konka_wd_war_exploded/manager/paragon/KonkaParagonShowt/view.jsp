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
  <h3 align="center" ><strong class="fb">展台展柜查看</strong></h3>
  </div>
    <div class="rtabcont2">
		<html-el:form action="/paragon/KonkaParagonShowt" enctype="multipart/form-data">
			<html-el:hidden property="showt_id" styleId="showt_id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				<tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">门店代码：</td>
			       <td><c:out value="${af.map.show_shop_code}" /></td>
			    </tr>
			    <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">门店名称：</td>
			       <td><c:out value="${af.map.map.show_shop_name}" /></td>
			    </tr>
			     <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">形象版本代码：</td>
			       <td><c:out value="${af.map.map.version_code}" /></td>
			    </tr>
			     <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">形象版本名称：</td>
			       <td><c:out value="${af.map.map.version_name}" /></td>
			    </tr>
			    <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">展位面积：</td>
			       <td><c:out value="${af.map.showt_area}" />㎡</td>
			    </tr>
			    <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">展位类型：</td>
			       <td>${af.map.showt_type}</td>
			    </tr>
			     <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">展位延米：</td>
			       <td><c:out value="${af.map.showt_mile}" />m</td>
			    </tr>
			    <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">制作时间：</td>
			       <td>
			       	<fmt:formatDate value="${af.map.showt_time}" pattern="yyyy-MM-dd"/>
			       </td>
			    </tr>
			    <tr>
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">制作费用：</td>
			       <td>
			       	￥<fmt:formatNumber value="${af.map.showt_cash}" pattern="#,###.00" type="currency"/>元</td>
			    </tr>
			     <tr>
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">数据期号：</td>
			       <td>
			       	<c:out value="${af.map.fixdate}" /></td>
			    </tr>
			       <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">添加人：</td>
			       <td><c:out value="${af.map.map.user_name}" /></td>
			    </tr>
			       <tr >
			       <td height="28" width="15%" nowrap="nowrap" class="title_item">添加时间：</td>
			       <td><fmt:formatDate value="${af.map.addtime}" pattern="yyyy-MM-dd"/>
			       </td>
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
