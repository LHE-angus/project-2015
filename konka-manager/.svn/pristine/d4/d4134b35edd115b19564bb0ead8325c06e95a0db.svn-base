<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<div class="rtabcont2">
		<html-el:form action="/admin/KonkaMobileThingsBase" method="post">
			<html-el:hidden property="id" styleId="id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<html-el:hidden property="method" styleId="method" value="save" />
			<html-el:hidden property="queryString" styleId="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
				<tr>
					<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>物料或样机名称：</strong></td>
					<td width="88%" align="left"><html-el:text property="thing_name" size="40" maxlength="30" styleId="thing_name" /></td>
				</tr>
				<tr>
					<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>物料或样机类别：</strong></td>
					<td>
					 <html-el:select property="wl_index" styleId="wl_index" style="width:130px;">
					<html-el:option value="">请选择父类别</html-el:option>
	                  <c:forEach var="cur" items="${categoryList}">
	                    <html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
	                  </c:forEach>
	                 </html-el:select> 
	                  <html-el:select property="wl_type" styleId="wl_type" style="width:130px;">
		                  <html-el:option value="">请选择子类别</html-el:option>
		                </html-el:select>
		                <font style="font-size: 12px;color: gray" id="wl_msg">*请选择物料或样机父类别</font>
		            </td> 
				</tr>
				<tr>
					<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>启用时间：</strong></td>
					<td width="88%" align="left">
					<fmt:formatDate value="${af.map.done_start}" pattern="yyyy-MM-dd" var="_done_start" />
						<html-el:text property="done_start" styleId="done_start" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_done_start}"/>
					</td>
				</tr>
				<tr>
					<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>报废时间：</strong></td>
					<td width="88%" align="left">
					<fmt:formatDate value="${af.map.done_end}" pattern="yyyy-MM-dd" var="_done_end" />
						<html-el:text property="done_end" styleId="done_end" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" value="${_done_end}"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label>
			            <input class="but4" type="button" name="Submit4" id="send" value="提交" />
			            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
         		 	</label></td>
				</tr>
			</table>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
		$("#wl_index").change( function() {
			$("#wl_type").empty();
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getWlTypeListByWlIndex&wl_index=" + $("#wl_index").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					if (data.length >= 1) {
						var opt = new Option( "请选择...",""); 
						$("#wl_type").get(0).options.add(opt);
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#wl_type").get(0).options.add(opt);
						}
						<c:if test="${not empty af.map.wl_type }">$("#wl_type").val("${af.map.wl_type}");</c:if>
					}
				}
			});
		   	
		});
		
		<c:if test="${not empty af.map.wl_index}">
		$("#wl_index").val("${af.map.wl_index}");
		$("#wl_index").change();
		</c:if>
		
	$("#thing_name").attr("dataType", "Require").attr("msg", "请填写物料或样机名称");
	$("#wl_index").attr("dataType", "Require").attr("msg", "请选择物料或样机父类别");
	$("#wl_type").attr("dataType", "Require").attr("msg", "请选择物料或样机子类别");
	$("#done_start").attr("dataType", "Require").attr("msg", "请填写启用时间");
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
