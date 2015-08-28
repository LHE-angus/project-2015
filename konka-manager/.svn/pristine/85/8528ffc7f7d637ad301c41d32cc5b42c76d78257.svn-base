<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
    <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="button" class="but2" value="确认" id="btn_sub"  />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center"></td>
        <td nowrap="nowrap" width="10%" align="center">类型</td>
        <td nowrap="nowrap" align="center" >套餐名称</td>
        <td nowrap="nowrap" align="center" >套餐价格</td>
      </tr>
      <c:forEach items="${hadEntityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><input type="checkbox" class="tt" id="${cur.id}" checked="checked" /> </td>
          <td align="left" nowrap="nowrap" width="10%" id="tj_${cur.id}">
            <c:if test="${cur.binding_type eq 0}">服务套餐</c:if><c:if test="${cur.binding_type eq 1}">商品套餐</c:if>
          </td>
          <td align="left" width="10%" nowrap="nowrap" id="tg_${cur.id}">${cur.goods_name} </td>
          <td align="left" width="10%" nowrap="nowrap" id="tp_${cur.id}">${cur.price} </td>
        </tr>
      </c:forEach>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><input class="tt" type="checkbox" id="${cur.id}" /> </td>
          <td align="left" nowrap="nowrap" width="10%" id="tj_${cur.id}">
            <c:if test="${cur.binding_type eq 0}">服务套餐</c:if>
            <c:if test="${cur.binding_type eq 1}">商品套餐</c:if>
          </td>
          <td align="left" width="10%" nowrap="nowrap" id="tg_${cur.id}">${cur.goods_name} </td>
          <td align="left" width="10%" nowrap="nowrap" id="tp_${cur.id}">${cur.price} </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#btn_sub").click(function(){
		var return_value = "";
		$(".tt").each(function(){
			if($(this).attr("checked")){
				var str = "";
				var id = this.id;
				str += $("#tj_"+id).text()+"@"+$("#tg_"+id).text()+"@"+$("#tp_"+id).text()+"@"+id;
				return_value = return_value + str + "##";
			}
		});
		window.returnValue = return_value;
		window.close();
		
	});

});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
