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
    <html-el:form  action="/admin/KonkaMobileRetailRest">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="query_or_count" styleId="query_or_count" value="query" />
     <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
		  <td><strong class="fb">促销员：</strong>
            <html-el:text property="retail_name_like" size="30" style="width:90px;" maxlength="40" styleId="retail_name_like" styleClass="webinput" /></td>
          <td><strong class="fb">是否审批：</strong>
          	<html-el:select property="status">
          		<html-el:option value="">请选择...</html-el:option>
          		<html-el:option value="1">审批已通过</html-el:option>
          		<html-el:option value="0">未审批</html-el:option>
          		<html-el:option value="-1">审批未通过</html-el:option>
          	</html-el:select>
          </td>
          <td><strong class="fb">时间范围：</strong>
			<html-el:text property="retail_date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
           -
            <html-el:text property="retail_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
         </td>
         <td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
         &nbsp;&nbsp;&nbsp;<html-el:button property="btn_count" styleId="btn_count" styleClass="but1" >统计</html-el:button>
          </td>
        </tr>
      </table>
    </html-el:form>
    </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
         	<logic-el:match name="popedom" value="+1+">
        		<input class="but8" type="button" name="Submit2" value="请假审批" id="btn_sub"  />
        	</logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	  <html-el:form  action="/admin/KonkaMobileRetailRest" styleClass="form_class">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="status" styleId="status" value="0" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	        <c:if test="${empty af.map.query_or_count || af.map.query_or_count eq 'query'}">
	          <td width="30" align="center"><input type="checkbox" id="all" /></td>
	          <td width="110" nowrap="nowrap" align="center">分公司</td>
	          <td width="110" nowrap="nowrap" align="center">促销员</td>
	          <td width="80" nowrap="nowrap" align="center">审批状态</td>
	          <td width="80" nowrap="nowrap" align="center">休息日期</td>
	          <td nowrap="nowrap" align="center" >事由</td>
	        </c:if>
	        <c:if test="${af.map.query_or_count eq 'count'}">
	          <td width="30" align="center">序号</td>
	          <td nowrap="nowrap" align="center">分公司</td>
	          <td nowrap="nowrap" align="center">办事处</td>
	          <td width="110" nowrap="nowrap" align="center">促销员</td>
	          <td width="90" nowrap="nowrap" align="center">休息总天数</td>
	        </c:if>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
          	<c:if test="${empty af.map.query_or_count || af.map.query_or_count eq 'query'}">
	            <td align="center" nowrap="nowrap">
	            	<c:if test="${cur.status eq '0'}"><input type="checkbox" class="checkbox" name="id_s" value="${cur.id}" /></c:if>
	            	<c:if test="${cur.status ne '0'}"><input type="checkbox" disabled="disabled" /></c:if>
	            </td>
	            <td align="center">${cur.office_name } </td>
	            <td align="center">${cur.retail_name } </td>
	            <td align="center"><c:if test="${cur.status eq 0}">未审批</c:if><c:if test="${cur.status eq 1}"><span style="color:green;">审批已通过</span></c:if><c:if test="${cur.status eq -1}"><span style="color:red;">审批未通过</span></c:if></td>
	            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.retail_date}" pattern="yyyy-MM-dd"/> </td>
	            <td align="left" >${cur.retail_desc}</td>
	        </c:if>
          	 <c:if test="${af.map.query_or_count eq 'count'}">
	            <td align="center" nowrap="nowrap">${vs.count} </td>
	            <td align="left" >${cur.subcomp_name } </td>
	            <td align="left" >${cur.office_name } </td>
	            <td align="center" >${cur.retail_name } </td>
	            <td align="right" >${cur.map.count}&nbsp;天</td>
	        </c:if>
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
            <c:if test="${empty af.map.query_or_count || af.map.query_or_count eq 'query'}">
            	<td>&nbsp;</td>
            </c:if>
          </tr>
        </c:forEach>
      </table>
    </html-el:form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileRetailRest.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("retail_name_like", "${fn:escapeXml(af.map.retail_name_like)}");							
			pager.addHiddenInputs("retail_date_begin", "${fn:escapeXml(af.map.retail_date_begin)}");							
			pager.addHiddenInputs("retail_date_end", "${fn:escapeXml(af.map.retail_date_end)}");							
			pager.addHiddenInputs("query_or_count", "${af.map.query_or_count}");							
			pager.addHiddenInputs("status", "${af.map.status}");							
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<div style="display:none;top:20%;left:20%;background:#fff;font-size:12px;z-index:999999;" id="light_box">
		<table width="300">
			<tr>
				<td align="right" colspan="2" height="22"><span style="cursor:pointer;margin-right:3px;" id="light_box_close">关闭</span></td>
			</tr>
			<tr>
				<td align="right" height="30" valign="top"><input class="but8" type="button" value="审批通过" id="ststus_1" style="margin-right:8px;" /> </td>
				<td align="left" valign="top"><input class="but8" type="button" value="审批不通过" id="ststus_-1" style="margin-left:8px;" /></td>
			</tr>
		</table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 全选按钮操作
	$("#all").click(function(){
		if($("#all").attr("checked")== "checked"){
			$(".checkbox").attr("checked", "checked");//全选
		} else {
			$(".checkbox").removeAttr("checked");//取消全选
		}
	});

	var light_box = new LightBox("light_box");
	light_box.Over = true;  //是否启用覆盖层  :true 、 false;
	light_box.OverLay.Color = "#b9b9b9"; //覆盖层颜色 ，必须启用覆盖层才有作用
	light_box.OverLay.Opacity = 93; //覆盖层透明度 
	light_box.Fixed = true; // 是否定位
	light_box.Center = true; //是否居中
	
	// 审批按钮
	$("#btn_sub").click(function(){
		var flag = false;
		$(".checkbox").each(function(){
			if($(this).attr("checked")== "checked") flag = true;
		});
		if (!flag){
			alert("请至少选择一个信息！");
			return;
		}
		light_box.Show();
	});
	// 关闭弹窗
	$("#light_box_close").click(function(){
		light_box.Close();
	});
	// 审批通过
	$("#ststus_1").click(function(){
		$("#status").val("1");
		$(".form_class").submit();
	});
	// 审批不通过
	$("#ststus_-1").click(function(){
		$("#status").val("-1");
		$(".form_class").submit();
	});
	
	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	$("#btn_count").click(function(){
		$('#query_or_count',queryForm).val('count');
		queryForm.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
