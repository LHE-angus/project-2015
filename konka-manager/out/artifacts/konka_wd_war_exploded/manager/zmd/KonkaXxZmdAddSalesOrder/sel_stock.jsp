<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!-- <link href="${ctx}/scripts/jquery-ui/themes/redmond/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" /> -->
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
<!--<style type="text/css">-->
<!--   html { overflow:hidden;} -->
<!--</style>-->
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  请选择库存来源：<br />
  <ul style="margin:10px auto; width: 40em;line-height:50px;font-size:1.5em;">
  	<li><a href="javascript:void(0);" onclick="to(0);">专卖店自有库存</a></li>
  	<li><a href="javascript:void(0);" onclick="to(1);">分公司统一库存</a><span style="font-size:12px;color:#888;">（零库存销售模式入口，分公司负责发货）</span></li>
  </ul>
  <form id="form" action="KonkaXxZmdAddSalesOrder.do" method="post">
  	<input type="hidden" id="method" name="method" value="add" />
  	<input type="hidden" id="stock_from" name="stock_from" />
  	<input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}" />
  </form>
  </div>
</div>
<script type="text/javascript"><!--//<![CDATA[
function to(stock_from) {
	var m = document.getElementById("method");
	var sf = document.getElementById("stock_from");
	sf.value = stock_from;
	if (stock_from == 0) m.value = "add2";
	sf.form.submit();
}
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>