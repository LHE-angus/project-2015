<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<!--<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />-->
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
button{height:25px;font-family:Microsoft YAHEI;}
textarea {font-family:Microsoft YAHEI;font-size:12px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1">
  		<html-el:form action="/manager/KonkaStockInventory" enctype="multipart/form-data">
  			<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  			<html-el:hidden property="id" value="${af.map.id}" />
  			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		    	<tr>
		          <th colspan="4" style="font-size:15px;">盘点信息</th>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">盘点日期：</td>
		          <td><fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" /></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">仓库：</td>
		          <td id="storeTd">${af.map.store_name}</td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">商品名称：</td>
		          <td id="goodsTd">${af.map.goods_name}</td>
		        </tr>
		        <!--<tr>
		          <td class="title_item" width="15%">条码：</td>
		          <td></td>
		        </tr>-->
		        <tr>
		          <td class="title_item" width="15%">盘点前：</td>
		          <td>${af.map.stocks}</td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">盘点后：</td>
		          <td>${af.map.ver_stocks}</td>
		        </tr>
		        <tr>
		          <td class="title_item">结果</td>
		          <td id="vsResultTd">
		          	<c:if test="${af.map.trade_type eq 30}">盘存数量小于当前系统库存数量，当前库存状况为：<span style="color:#CD0000;">盘亏</span></c:if>
    				<c:if test="${af.map.trade_type eq 31}">盘存数量大于当前系统库存数量，当前库存状况为：<span style="color:#009900;">盘盈</span></c:if>
    				<c:if test="${af.map.trade_type eq 0}">盘存数量等于当前系统库存数量，当前库存状况为：库实相符</span></c:if>
		          </td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">备注(REMARK)：</td>
		          <td><html-el:textarea property="memo" styleId="memo" styleClass="webinput" cols="60" style="height:60px;" /></td>
		        </tr>
		        <tr>
		          <td colspan="2" align="center">
		            <input type="button" id="btn_back" name="back" class="bgButtonBack" value="返回" onclick="history.back();return false;" />
		          </td>
		        </tr>
		    </table>
  		</html-el:form>
  	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>