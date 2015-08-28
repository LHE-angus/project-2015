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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/manager/admin/KonkaCompetProdManage/tips/style.css" rel="stylesheet" type="text/css" />
</head>
<body style="font-family:Microsoft Yahei,'宋体';">
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
	<html-el:form action="/admin/KonkaCompetProdManage" enctype="multipart/form-data">
		<html-el:hidden property="method" value="importSave" />
		<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      	<html-el:hidden property="queryString" />
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	  <tr>
	  	  	<td align="center" colspan="4" style="font-weight:900;">竞品导入</td>
	  	  </tr>
	  	  <tr>
		  	<td width="12%" class="title_item" nowrap="nowrap">模板：</td>
		  	<td width="88%" style="text-align:left;padding-left:5px;">
		  		<a href="${ctx}/manager/admin/KonkaCompetProdManage/template/temp.xls">模板下载</a>
		  	</td>
		  </tr>
	  	  <tr>
		  	<td nowrap="nowrap" class="title_item">文件上传：</td>
		  	<td style="text-align:left;padding-left:5px;">
		  		<input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
		  		<c:if test="${not empty csCompetProdList}">
		  			&nbsp;&nbsp;<a href="javascript:viewErrorInfoDiv();">查看错误明细</a>
		  		</c:if>
		  	</td>
		  </tr>
	  	  <tr>
		  	<td>&nbsp;</td>
		  	<td style="text-align:left;padding-left:5px;">
		  		<html-el:button property="btn_submit" styleId="btn_submit" styleClass="but_excel" value="导 入  " />
		  	</td>
		  </tr>
		 
		</table>
	</html-el:form>
	<span style="color:red;">图片上传：图片名称与型号对应批量上传,相同信息导入时，按机型覆盖前期数据</span>
	<span style="color:red;">增加竞品信息EXCEL导出功能</span>
  </div>
</div>

<!-- 导出错误明细 -->
<c:if test="${not empty csCompetProdList}">
	<div id="errorInfo">
	  <table>
  	  	<tr>
  	  		<td width="15%" height="70"><img src="${ctx}/images/manager/cry1.gif" alt="" style="vertical-align:middle;" /></td>
  	  		<td nowrap="nowrap">
  	  			<span style="font-family:'Microsoft yahei','黑体','宋体';font-size:18px;vertical-align:middle;color:#FF0000;line-height:26px;">抱歉，导入数据失败！</span><br />
	  	  		<span style="font-family:'Microsoft yahei','黑体','宋体';font-size:14px;vertical-align:middle;color:#666;line-height:26px;">请将鼠标移至下表中的标记处查看详细错误信息</span>
  	  		</td>
  	  	</tr>
  	  </table>
  	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="font-family:Microsoft Yahei,'宋体';">
  	  	<tr class="tabtt1">
  	  	  <td width="3%" nowrap="nowrap" align="center">序号</td>
  	  	  <td width="10%" nowrap="nowrap" align="center">品牌</td>
          <td width="6" nowrap="nowrap" align="center">尺寸</td>
          <td width="10%" nowrap="nowrap" align="center">智能电视</td>
          <td width="10%" nowrap="nowrap" align="center">三维电视</td>
          <td width="10%" nowrap="nowrap" align="center">背光源</td>
          <td width="" nowrap="nowrap" align="center">机型</td>
          <td width="10%" nowrap="nowrap" align="center">参考价格（元）</td>
          <td width="8%" nowrap="nowrap" align="center">排序值</td>
          <td width="8%" nowrap="nowrap" align="center">状态</td>
  	  	</tr>
  	  	<tbody>
          <c:forEach var="cur" items="${csCompetProdList}" varStatus="vs">
          	<tr>
          		<td align="center">${vs.count}</td>
          		<!-- 品牌 -->
       			<c:choose>
          			<c:when test="${empty cur.map.msg0}">
		  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.brand_name, " ", "&nbsp;")}</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:left;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg0}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con0, " ", "&nbsp;")}</td>
		  			</c:otherwise>
       			</c:choose>
       			<!-- 尺寸 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg1}">
		  				<td nowrap="nowrap" align="center">${cur.screen_size}</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg1}</strong>');" onmouseout="tooltip.hide();">${cur.map.con1}</td>
		  			</c:otherwise>
		  		</c:choose>
		  		<!-- 智能电视 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg2}">
		  				<td nowrap="nowrap" align="center">
		  					<c:if test="${cur.is_smart_tv eq 0}">否</c:if>
          	  				<c:if test="${cur.is_smart_tv eq 1}">是</c:if>
		  				</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg2}</strong>');" onmouseout="tooltip.hide();">${cur.map.con2}</td>
		  			</c:otherwise>
		  		</c:choose>
		  		<!-- 三维电视 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg3}">
		  				<td nowrap="nowrap" align="center">
		  					<c:if test="${cur.d_tv eq 0}">2D</c:if>
          	  				<c:if test="${cur.d_tv eq 1}">3D</c:if>
		  				</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg3}</strong>');" onmouseout="tooltip.hide();">${cur.map.con3}</td>
		  			</c:otherwise>
		  		</c:choose>
		  		<!-- 背光源 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg4}">
		  				<td nowrap="nowrap" align="center">
		  					<c:if test="${cur.back_light eq 0}">LED</c:if>
			          	  	<c:if test="${cur.back_light eq 1}">CCFL</c:if>
			          	  	<c:if test="${cur.back_light eq 9}">其他</c:if>
						</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg4}</strong>');" onmouseout="tooltip.hide();">${cur.map.con4}</td>
		  			</c:otherwise>
		  		</c:choose>
          		<!-- 机型 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg5}">
		  				<td nowrap="nowrap" align="left">${cur.md_name}</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg5}</strong>');" onmouseout="tooltip.hide();">${cur.map.con5}</td>
		  			</c:otherwise>
		  		</c:choose>
          		<!-- 参考价格（元） -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg6}">
		  				<td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.ref_price}" pattern="#,###.00" /></td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:right;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg6}</strong>');" onmouseout="tooltip.hide();">${cur.map.con6}</td>
		  			</c:otherwise>
		  		</c:choose>
          		<!-- 排序值 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg7}">
		  				<td nowrap="nowrap" align="center">${cur.order_value}</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg7}</strong>');" onmouseout="tooltip.hide();">${cur.map.con7}</td>
		  			</c:otherwise>
		  		</c:choose>
		  		<!-- 状态 -->
          		<c:choose>
		  			<c:when test="${empty cur.map.msg8}">
		  				<td nowrap="nowrap" align="center">${cur.is_del}</td>
		  			</c:when>
		  			<c:otherwise>
		  				<td nowrap="nowrap" style="text-align:center;color:#FF0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg8}</strong>');" onmouseout="tooltip.hide();">${cur.map.con8}</td>
		  			</c:otherwise>
		  		</c:choose>
          	</tr>
          </c:forEach>
  	  	</tbody>
  	  </table>
	</div>
</c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/manager/admin/KonkaCompetProdManage/tips/script.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请上传数据Excel文件");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if (confirm("确定导入数据？")) {
				this.form.submit();
			}
		}
	});

	//显示上传文件错误详细信息
	viewErrorInfoDiv();
});

function viewErrorInfoDiv(){
	if ("" != "${csCompetProdList}") {
		$("#errorInfo").dialog({
//			bgiframe: true,
			closeOnEscape:false,
			width: 650,
			height: 400,
			resizable: false,
			draggable: true,
			position:'center',
			modal : true,
			title : '<span style="font-family:Microsoft Yahei, \'宋体\';">导入错误详细信息<span>'
		}).dialog("open");
	}
}
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>