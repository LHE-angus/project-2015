<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/ConsumerInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="md_name" styleId="md_name" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
         	<td align="right" width="70"><strong class="fb">销售日期：</strong></td>
         	<td>
          		<html-el:text property="date_begin" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            	-
            	<html-el:text property="date_end" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            </td>
         	<td align="right" width="100"><strong class="fb">上报人：</strong></td>
          	<td><html-el:text property="report_name_like" size="30" style="width:170px;" maxlength="40" styleId="report_name_like" styleClass="webinput" /></td>
         	<td align='right' width="80">状态：</td>
         	<td>
         		<html-el:select property="is_del" styleId="is_del">
         			<html-el:option value="">-请选择-</html-el:option>
         			<html-el:option value="0">有效</html-el:option>
         			<html-el:option value="1">无效</html-el:option>
         		</html-el:select>
         	</td>
        </tr>
        <tr>
          	<td width="100" align="right"><strong class="fb">消费者姓名：</strong></td>
          	<td><html-el:text property="consumer_name_like" size="30" style="width:170px;" maxlength="40" styleId="consumer_name_like" styleClass="webinput" /></td>
          	<td width="70" align="right"><strong class="fb">电话 ：</strong></td>
          	<td><html-el:text property="consumer_phone_like" size="30" style="width:170px;" maxlength="40" styleId="consumer_phone_like" styleClass="webinput" /></td>
           	<td></td>
          	<td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
           		<input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
           	</td>     
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include
	file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>       
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td width="10%" nowrap="nowrap" align="center">身份证</td>
        <td width="15%" nowrap="nowrap" align="center">地址</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="3%" nowrap="nowrap" align="center">状态</td>
        <td width="3%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          
          <td align="center" nowrap="nowrap">${cur.consumer_name}</td>
          <td align="left" nowrap="nowrap">${cur.consumer_phone}</td>
          <td align="left" nowrap="nowrap">${cur.master_code}</td>
          <td align="left" nowrap="nowrap">${cur.consumer_addr}</td>
          <td align="right" nowrap="nowrap"><a title="点击可查详情" style="cursor:pointer;" class="fblue" onclick="showDetails('${cur.consumer_name}','${cur.consumer_phone}');">${cur.num}</a></td>
          <td align="right" nowrap="nowrap">
          	<fmt:formatNumber value="${cur.price}" pattern="#,##0.00"/>
          </td>
          <td align="center">
          	<c:if test="${cur.is_del eq 0 }">
            	有效
            </c:if>
            <c:if test="${cur.is_del eq 1 }">
            	无效
            </c:if>
          </td>
          <td align="center">
          	<c:if test="${cur.is_del eq 0 }">
            	<span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.consumer_phone}','1');">无效</span>
            </c:if>
            <c:if test="${cur.is_del eq 1 }">
            	<span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.consumer_phone}','0');">有效</span>
            </c:if>
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
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ConsumerInfo.do">
    <input type="hidden" name="excel_all" id="excel_all" />
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript"> var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize},${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");							
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("consumer_name_like", "${af.map.consumer_name_like}");
			pager.addHiddenInputs("consumer_phone", "${af.map.consumer_phone}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">//<![CDATA[
function stopOrstart(consumer_phone,flag){
	var str = '';
	if(flag==1){
		str='是否将该该消费者设置为无效？';
	}
	if(flag==0){
		str='是否将该消费者设置为有效？';
	}
	if(confirm(str)){
		$.post('${ctx}/customer/manager/ConsumerInfo.do?method=stopOrStart&consumer_phone='+consumer_phone+'&is_del='+flag,function(result){
			$("#bottomPageForm").submit();
		},'json');
	}
}

//详情列表
function showDetails(consumer_name,consumer_phone){
	consumer_name = encodeURI(encodeURI(consumer_name));
	$.dialog({
		title:  "购机列表",
		width:  1000,
		height: 500,
        lock:true ,
        zIndex:1979,
		content:"url:ConsumerInfo.do?method=listForDetails&consumer_phone="+consumer_phone+"&consumer_name="+consumer_name
	});
}

$(document).ready(function(){
	$(".list-tr td").each(function(){
		var text = $(this).html();
		if($.trim(text).length == 0) {
			$(this).html("<span style='color:#CCC;'>未填写</span>");
		}
	});
	
    // 导出excel
    $("#export_excel").click(function(){
        $("#excel_all").val("1");
		$("#bottomPageForm").submit();
    });

    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '?method=toExcel');
	}
	
});
function upperCase(x)
{
var y=document.getElementById(x).value
document.getElementById(x).value=y.toUpperCase()
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
