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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
		<html-el:form action="/paragon/KonkaParagonSub">
		<html-el:hidden property="method" styleId="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
       	    <td width="15"></td>
 			<td><strong class="fb">门店代码：</strong><html-el:text property="shop_order_like" size="15" maxlength="20" styleId="shop_order_like"  /></td>
 			<td><strong class="fb">门店名称：</strong><html-el:text property="shop_name_like" size="15" maxlength="20" styleId="shop_name_like"  /></td>
	        <td><strong class="fb">经办名称：</strong><html-el:text property="channel_name_like" size="15" maxlength="20" styleId="channel_name_like"  /></td>
	        <td><html-el:submit styleClass="but1" value="搜索" /></td>
	        <td width="15"></td>
          </tr>
       </table>
		</html-el:form>
	</div>
	 <div class="rtabcont1">
	<%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
		<form id="listForm" name="listForm" method="post" action="">
		<input type="hidden" name="method" id="method" value="delete" />
		<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	       <tr class="tabtt1">
					<td nowrap="nowrap" align="center">门店代码</td>
					<td nowrap="nowrap" align="center">门店名称</td>
					<td nowrap="nowrap" align="center">区域</td>
					<td nowrap="nowrap" align="center">分公司</td>
					<td nowrap="nowrap" align="center">经办</td>
					<td nowrap="nowrap" align="center">客户名</td>
					<td nowrap="nowrap" align="center">客户类别</td>
					<td nowrap="nowrap" align="center">有无直销员</td>
					<td width="120" nowrap="nowrap" align="center">操作</td>
				</tr>
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap"><a href="KonkaParagonSub.do?method=view&mod_id=${af.map.mod_id}&show_shop_id=${cur.show_shop_id}" title="点击查看门店详细信息">${cur.show_shop_code}</a></td>
					<td align="left">${cur.show_shop_name}</td>
					<td align="center" nowrap="nowrap">
						<c:choose>
							<c:when test="${cur.area_id eq 10}">华东</c:when>
							<c:when test="${cur.area_id eq 20}">山东</c:when>
							<c:when test="${cur.area_id eq 30}">东北</c:when>
							<c:when test="${cur.area_id eq 40}">华北</c:when>
							<c:when test="${cur.area_id eq 50}">华南</c:when>
							<c:when test="${cur.area_id eq 60}">西南</c:when>
							<c:when test="${cur.area_id eq 70}">华中</c:when>
							<c:when test="${cur.area_id eq 80}">西北</c:when>
						</c:choose>
					</td>
					<td align="center" nowrap="nowrap">${cur.map.part_name}</td>
					<td align="center" nowrap="nowrap">${cur.channel_name}</td>
					<td align="center">${cur.custom_name}</td>
					<td align="center" nowrap="nowrap">
					<c:choose>
						<c:when test="${cur.custom_type eq 1}">连锁</c:when>
						<c:when test="${cur.custom_type eq 2}">超市</c:when>
						<c:when test="${cur.custom_type eq 3}">县乡客户群</c:when>
						<c:when test="${cur.custom_type eq 4}">城市客户群</c:when>
						<c:otherwise>城市专卖店</c:otherwise>
					</c:choose>
					</td>
					<td align="center" nowrap="nowrap">
					<c:if test="${cur.if_seller eq 0 }">无</c:if>
					<c:if test="${cur.if_seller eq 1 }">有</c:if>
					</td>
					<td align="center">
					<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonSub.do', 'view','mod_id=${af.map.mod_id}&scode=${cur.show_shop_code}&'+$('#bottomPageForm').serialize())">查看</span>
					<logic-el:match name="popedom" value="+2+">
						<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonSub.do','add', 'mod_id=${af.map.mod_id}&scode=${cur.show_shop_code}&' + $('#bottomPageForm').serialize())">上报</span>
					</logic-el:match>
					</td>
				</tr>
				<c:if test="${vs.last eq true}">
					<c:set var="i" value="${vs.count}" />
				</c:if>
				</c:forEach>
				<c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
				<tr align="center">
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
				</c:forEach>
			</table>
		</form>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonSub.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40" align="center">
						<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
						<script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
							pager.addHiddenInputs("shop_code_like", "${af.map.shop_code_like}");
							pager.addHiddenInputs("channel_name_like", "${af.map.channel_name_like}");
							document.write(pager.toString());
						</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	 $("#part_company_id").change( function() {
			var dept_id = $("#part_company_id").val();
			$("#channel_id").empty();

			if(""==dept_id){
		   		var opt1 = new Option( "请选择...",""); 
				$("#channel_id").get(0).options.add(opt1);
			   	}
		   	if(dept_id!=""){
			   	$.ajax({
					type: "POST",
					cache: false,
					url: "${ctx}/manager/admin/CsAjax.do",
					data: "method=getChannelId&part_company_id=" + $("#part_company_id").val(),
					dataType: "json",
					error: function(request, settings){},
					success: function(data) {
						if (data.length >= 1) {
							var opt1 = new Option( "请选择...",""); 
							$("#channel_id").get(0).options.add(opt1);
							
							for(var i = 0; i < data.length - 1; i++) {
								var opt = new Option( data[i].name,data[i].id); 
								$("#channel_id").get(0).options.add(opt);
							}
							
							<c:if test="${not empty af.map.channel_id }">$("#channel_id").val("${af.map.channel_id}");$("#channel_id").change();</c:if>
							<c:if test="${empty af.map.channel_id}"></c:if>
						}
					}
				});
		   	}
		});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>