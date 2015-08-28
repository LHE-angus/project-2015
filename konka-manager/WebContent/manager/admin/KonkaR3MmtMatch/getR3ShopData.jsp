<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>R3客户同步</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3MmtMatch.do">
      <html-el:hidden property="method" styleId="methodID" value="getR3ShopData" />
      <html-el:hidden property="kunnrs" styleId="kunnr_value"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" class="rtable1">
        <tr>
          <td>
          	<strong class="fb">部门：</strong>
          	<html-el:select property="sales_dept" styleId="sales_dept">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${kdList}">
                <html-el:option value="${cur.dept_sn}">${cur.dept_name} [${cur.dept_sn}]</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
          
            
          <td>
          	<strong class="fb">R3客户创建时间：</strong>
          	<html-el:text property="in_date" size="10" maxlength="20" styleId="in_date" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2000, 2021, 0).show(this);" />
          </td>
          <td>
          	<strong class="fb" >R3客户编码：</strong>
          	<html-el:text property="r3_code" size="10" maxlength="20" styleId="r3_code" />
          </td>
         <td><html-el:submit styleClass="but1" styleId="searchBtn" value="搜索" alt="搜索一下,是否在R/3系统存在数据" title="搜索一下,是否在R/3系统存在数据"/></td>
        </tr>
    
        <tr>
       		<td colspan="2">
       		<html-el:submit styleClass="btn_sync" styleId="syncBtn" value="同步" title="请选择需要同步的客户"></html-el:submit>
       		&nbsp;
         	<html-el:button property="goback_btn" onclick="javascript:history.back();" styleClass="but5" value="返回" title="返回上一页面"></html-el:button>
         	</td>
         	<td colspan="2"></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  
   <div class="rtabcont1">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="2%" align="center"><input name="chkAll" type="checkbox" id="chkAll" checked="checked" /></td>
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="5%" align="center" nowrap="nowrap">销售组织</td>
          <td width="12%" align="center" nowrap="nowrap">客户分类ID</td>
          <td width="12%" align="center" nowrap="nowrap">R3客户编码</td>
          <td width="12%" align="center" nowrap="nowrap">R3客户名称</td>
          <td width="8%" nowrap="nowrap" align="center">R3客户创建时间</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><input name="checkbox" checked="checked" type="checkbox" value="${cur.KUNNR}"/></td>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.knb1.BUKRS}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.KUKLA)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.KUNNR)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.NAME1)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.ERDAT)}</td>
          </tr>
        </c:forEach>
        
      </table>

    <br />
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	//全选||非全选
	$("#chkAll").click(function() {
		if ($("#chkAll").attr("checked") == "checked") {
			$("[name='checkbox']").attr("checked", 'checked');//全选
		} else {
			$("[name='checkbox']").removeAttr("checked");//取消全选
		}
		
	});
	
	$("#searchBtn").click(function(){
		var sales_dept = $("#sales_dept").val();
		var in_date = $("#in_date").val();
		if(sales_dept==""){
			alert("请指定部门编码!");
			return false ;
		}
		if(in_date==""){
			alert("请指定客户创建起始日期!");
			return false ;
		}
		
		$("#methodID").attr("value","getR3ShopData");
		return true;
	});
	
	$("#syncBtn").click(function(){
		 //执行同步前,一次性统计需要同步的数据
		 var kunnrs = "";
		 $("[name='checkbox']:checked").each(function(){
		    	kunnrs += $(this).val()+"-";
		    });
		 if(kunnrs==""){
			alert("请选择需要同步的客户");
			return false;
		}
		$("#kunnr_value").attr("value",kunnrs);
		$("#methodID").attr("value","syncFromR3");
	});

});

	//
</script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>