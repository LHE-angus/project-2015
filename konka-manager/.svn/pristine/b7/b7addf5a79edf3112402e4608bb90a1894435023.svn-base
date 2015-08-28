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
        <td>当前位置：选择门店</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
        <html-el:form action="/paragon/SelectShowInfoForManinfo">
         <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" styleId="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <html-el:hidden property="user_id" value="${af.map.user_id}" />
            <div style="height:5px;"></div>
           <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
	         <tr>
	          <td width="15"></td>
	          <td><strong class="fb">门店代码：</strong>
	             <html-el:text property="shop_order_like" styleId="shop_order_like" size="24" maxlength="100" style="width:100px;" /></td>
	          <td><strong class="fb">门店名称：</strong><html-el:text property="shop_name_like" size="15" maxlength="20" styleId="shop_name_like"  /></td>
	          <td nowrap="nowrap">
	        	<strong class="fb">区域　：</strong>
        		<html-el:select property="area_id" styleId="area_id">
	                <html-el:option value="">请选择...</html-el:option>
	                <html-el:option value="10">华东</html-el:option>
	                <html-el:option value="20">山东</html-el:option>
	                <html-el:option value="30">东北</html-el:option>
	                <html-el:option value="40">华北</html-el:option>
	                <html-el:option value="50">华南</html-el:option>
	                <html-el:option value="60">西南</html-el:option>
	                <html-el:option value="70">华中</html-el:option>
	                <html-el:option value="80">西北</html-el:option>
              </html-el:select>
			</td>
			</tr>
			<tr>
			   <td width="15"></td>
	           <td><strong class="fb">分公司　：</strong>
	        	<html-el:select property="part_company_id" styleId="part_company_id">
		        	<html-el:option value="">请选择...</html-el:option>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select></td>
	         <td></td>
	          <td><html-el:submit styleClass="but1" value="搜索" /></td>
	         </tr>
	   </table>
	</html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="rtabcont1">
    <html-el:form action="/paragon/SelectShowInfoForManinfo" enctype="multipart/form-data" >
     <html-el:hidden property="method" styleId="method" value="save" />
     <html-el:hidden property="queryString" styleId="queryString" value=""/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="selectype" styleId="selectype"  />
         <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	    <tr class="tabtt1">
     	    	<td width="25" nowrap="nowrap" align="center">序号</td>
				<td nowrap="nowrap" align="center">门店代码</td>
				<td nowrap="nowrap" align="center">门店名称</td>
				<td nowrap="nowrap" align="center">区域</td>
				<td nowrap="nowrap" align="center">分公司</td>
			</tr>
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr  style="cursor: pointer;" onclick="setReturnValue('${cur.show_shop_id}','${cur.show_shop_code}','${cur.show_shop_name}');">
					<td align="center" nowrap="nowrap">${vs.count}
						<html-el:hidden property="show_shop_codes_${cur.show_shop_id}" styleId="show_shop_codes_${cur.show_shop_id}" value="${cur.show_shop_code}"/>
						<html-el:hidden property="show_shop_names_${cur.show_shop_id}" styleId="show_shop_names_${cur.show_shop_id}" value="${cur.show_shop_name}"/>
					</td>
					<td align="center" nowrap="nowrap">${cur.show_shop_code}</td>
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
				</tr>
				</c:forEach>
        </table>
     </html-el:form>
     
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="SelectShowInfoForManinfo.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40" align="center">
						<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
						<script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
							pager.addHiddenInputs("shop_order_like", "${af.map.shop_order_like}");
							pager.addHiddenInputs("channel_name_like", "${af.map.channel_name_like}");
							pager.addHiddenInputs("part_company_id", "${af.map.part_company_id}");
							pager.addHiddenInputs("area_id", "${af.map.area_id}");
							pager.addHiddenInputs("user_id", "${af.map.user_id}");
							document.write(pager.toString());
						</script>
					</td>
				</tr>
			</table>
		</form>
    </div>
    <div class="clear"></div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
function setReturnValue(id,code,name) {
		window.returnValue = {
			shop_id : id,
			shop_code : code,
			shop_name : name
		};
		window.close(); 
};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
