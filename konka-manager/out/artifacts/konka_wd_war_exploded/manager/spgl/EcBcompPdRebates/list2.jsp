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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
	<div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="button" class="but2" value="新 增" onclick="location.href='EcBcompPdRebates.do?method=add2&mod_id=${af.map.mod_id}&goods_id=${af.map.goods_id}';" />
				</td>
			</tr>
		</table>		
	</div>
	<div class="rtabcont1">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
			<tr class="tabtt1">
				<td width="5%" nowrap="nowrap" align="center">序号</td>
				<td nowrap="nowrap" align="center">商品编码</td>
				<td nowrap="nowrap" align="center">商品名称</td>
				<td nowrap="nowrap" align="center">所属系统</td>
				<td nowrap="nowrap" align="center">分公司</td>
				<td nowrap="nowrap" width="10%"  align="center">R3客户</td>
				<td width="10%" nowrap="nowrap" align="center">返利类型</td>
				<td nowrap="nowrap" align="center">数值</td>
				<td width="10%" nowrap="nowrap" align="center">添加时间</td>
				<td width="10%" nowrap="nowrap" align="center">操作</td>
			</tr>
			<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap">${vs.count}</td>
					<td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.pd_sn)}</td>
					<td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.pd_name)}</td>
					<td align="center" nowrap="nowrap">
						<c:if test="${cur.own_sys eq 1}">工卡</c:if>
						<c:if test="${cur.own_sys eq 2}">触网</c:if>
					</td>
					<td align="center" nowrap="nowrap">
						<c:if test="${cur.dept_id eq 0}">总部</c:if>
						${cur.map.dept_name}
					</td> 
					<td align="center" nowrap="nowrap">${cur.map.customer_name}</td>
					<td align="center" nowrap="nowrap">
						<c:if test="${cur.b_type eq '0'}">按比例（%）</c:if>
						<c:if test="${cur.b_type eq 1}">固定金额（元）</c:if>
					</td>
					<td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.b_value}" pattern="#,#00.00" /><c:if test="${cur.b_type eq '0'}">&nbsp;%</c:if></td>
					<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
					<td align="center" nowrap="nowrap">
						<a style="color: blue" href="javascript:doNeedMethod(null, 'EcBcompPdRebates.do', 'view2', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">查看</a> | 
						<a style="color: blue" href="javascript:doNeedMethod(null, 'EcBcompPdRebates.do', 'edit2', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">修改</a> | 
						<a style="color: blue" href="javascript:doNeedMethod('确定删除此记录？', 'EcBcompPdRebates.do', 'delete2', 'id=${cur.id}&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not vs.last}"><div style="height:40px;"></div></c:if>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="EcBcompPdRebates.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40">
						<div style="text-align: right; padding-right: 5px;"> 
							<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
							<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list2");
								pager.addHiddenInputs("mod_id", "${fn:escapeXml(af.map.mod_id)}");
								document.write(pager.toString());
							</script> 
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>