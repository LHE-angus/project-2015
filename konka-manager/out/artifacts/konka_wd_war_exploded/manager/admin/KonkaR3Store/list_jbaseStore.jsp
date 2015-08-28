<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>仓库信息</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<!--<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JBaseStore">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">仓库名称：</strong>
	          <html-el:text property="store_name_like" styleClass="webinput" styleId="store_name_like" maxlength="40"/>
	          &nbsp;<strong class="fb">仓库编码：</strong>
	          <html-el:text property="store_sn_like" styleClass="webinput" styleId="store_sn_like" maxlength="40"/>
	          &nbsp;
	          <strong class="fb">是否停用：</strong>
              <html-el:select property="is_del" styleId="is_del" style="width:70px;" onchange="this.form.submit();">
                <html-el:option value="">请选择</html-el:option>
                <html-el:option value="0">未停用</html-el:option>
                <html-el:option value="1">已停用</html-el:option>
              </html-el:select>&nbsp;&nbsp;
	          &nbsp;<strong class="fb">送达方编码：</strong>
	          <html-el:text property="sale_r3_code_like" styleClass="webinput" styleId="sale_r3_code_like" maxlength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>  
	-->
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%" nowrap="nowrap">序号</th>
	      <th width="8%" nowrap="nowrap">仓库ID</th>
	      <th width="15%" nowrap="nowrap">仓库名称</th>
	      <th width="10%" nowrap="nowrap">送达方编码</th>
	      <th width="8%" nowrap="nowrap">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="center" nowrap="nowrap">${cur.store_id}</td>
	        <td align="center" nowrap="nowrap">${cur.store_name}</td>
	        <td align="center" nowrap="nowrap">${cur.map.sale_r3_code}</td>
	        <td align="center" nowrap="nowrap">
	            <a href="#" id="sel" onclick="selectCust('${cur.store_id}','${cur.store_name}','${cur.map.sale_r3_code}')">选择</a>
	        </td>
	      </tr>
	    </c:forEach>
	   
	  </table>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[

function selectCust(ck_id, ck_name, sdf_r3_code){
	var api = frameElement.api;
	var W = api.opener;
	
	W.document.getElementById("ck_id").value = ck_id;
	W.document.getElementById("ck_name").value = ck_name;
	W.document.getElementById("mf_sn").value = sdf_r3_code;
	api.close();
	/*window.returnValue = { 'ck_id' : ck_id, 'ck_name' : ck_name, 'sdf_r3_code' : sdf_r3_code};
	if (window.opener && window.opener != null) window.opener.returnValue = { 'ck_id' : ck_id, 'ck_name' : ck_name, 'sdf_r3_code' : sdf_r3_code};
	window.close();*/
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>