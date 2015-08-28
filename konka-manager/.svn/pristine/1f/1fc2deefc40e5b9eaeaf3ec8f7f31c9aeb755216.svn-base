<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品信息列表</title>
<script type="text/javascript" src="../../commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 产品管理</div>

<html-el:form action="/JxcPd.do?method=delete">
<div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
    <tr>
    <td>
     &nbsp;<strong class="fb">产品大类</strong>：
    <html-el:select property="pd_type" styleId="pd_type" styleClass="bdfont" style="width:100px">
      <html-el:option value="">请选择...</html-el:option>
      <c:forEach items="${basePdTypeList}" var="cur">
      	<html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
      </c:forEach>
      <html-el:option value="0">其他</html-el:option>
    </html-el:select>&nbsp;
	<strong class="fb">所属系统</strong>：
	<html-el:select property="own_sys" styleId="own_sys" styleClass="bdfont"  style="width:100px">
	  <html-el:option value="">请选择...</html-el:option>
	  <html-el:option value="0">非家电下乡</html-el:option>
	  <html-el:option value="1">家电下乡</html-el:option>
	</html-el:select>
    
    &nbsp;<input name="button" type="button" class="bgSearch" id="s_button" value="搜 索" />
    </td>
    </tr>
	</table> 
</div>
<div class="rtabcont1">
 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
  <tr>
    <th width="5%" nowrap="nowrap">序号</th>
    <th width="8%" nowrap="nowrap">产品编号</th>
    <th width="8%" nowrap="nowrap">产品类型</th>
    <th width="8%" nowrap="nowrap">品牌</th>
    <th nowrap="nowrap">产品型号</th>
    <th width="8%" nowrap="nowrap">库存</th>
    <th width="8%" nowrap="nowrap">参考进货价</th>
    <th width="8%" nowrap="nowrap">零售价</th>
    <th width="8%" nowrap="nowrap">所属系统</th>
    <th width="8%" nowrap="nowrap">操作</th>
  </tr>
  <c:forEach var="cur" items="${entityList}" varStatus="vs">
    <tr>
      <td align="center" nowrap="nowrap" height="30">${vs.count}</td>
      <td align="center" nowrap="nowrap" title="${cur.out_sys_id}">${cur.out_sys_id le 0 ? '--' : cur.out_sys_id}</td>
      <td align="left" nowrap="nowrap">${cur.pd_type_name}</td>
      <td align="left" nowrap="nowrap">${cur.brand_name}</td>
      <td align="left" nowrap="nowrap">${cur.name}</td>
      <td align="right" nowrap="nowrap">${cur.count}&nbsp;${cur.unit}</td>
      <td align="right" nowrap="nowrap">
      	<c:if test="${empty cur.ref_price}">0.00</c:if>
      	<c:if test="${not empty cur.ref_price}"><fmt:formatNumber value="${cur.ref_price}" type="currency" /></c:if>
      </td>
      <td align="right" nowrap="nowrap">
      	<c:if test="${empty cur.price}">0</c:if>
      	<c:if test="${not empty cur.price}"><fmt:formatNumber value="${cur.price}" type="currency" /></c:if>
     </td>
      <td align="center" nowrap="nowrap">
      	<c:if test="${cur.own_sys eq 0}">非家电下乡</c:if>
      	<c:if test="${cur.own_sys eq 1}"><span style="color:#060;">家电下乡</span></c:if>
	  </td>
      <td align="center" nowrap="nowrap">
      	<c:if test="${cur.is_del eq 0}">
      		<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null,'JxcPd.do','view', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
      		|
      		<c:if test="${cur.count gt 0}">
      			<span style="color:#999"  >停用</span>
      		</c:if>
      		<c:if test="${cur.count eq 0}">
	      		<span style="cursor:pointer;" class="fblue"  onclick="confirmDelete('确定停用该产品？', 'JxcPd.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">停用</span>
      		</c:if>
      	</c:if>
      	<c:if test="${cur.is_del eq 1}">
      		<span style="color:#999">查看</span>
      		|
      		<span style="color:#999">停用</span>
      	</c:if>
	  </td>
    </tr>
  </c:forEach>
</table>
</div>
</html-el:form>
<form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcPd.do">
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
        <script type="text/javascript"><!--
          var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
          pager.addHiddenInputs("method", "list");
          pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
          pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
          document.write(pager.toString());
          </script></td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#s_button").click(function(){
			window.location.href = "${ctx}/jxcnokey/JxcPd.do?method=list&keySeq=${af.map.keySeq}&pd_type=" + $("#pd_type").val() + "&own_sys=" + $("#own_sys").val();
		})
	});
	
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>