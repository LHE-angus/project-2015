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
    <html-el:form action="/manager/VADefailsOfConsumer">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="md_name" styleId="md_name" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td width="15"></td>
         <td width="70"><strong class="fb">销售日期：</strong></td>
          <td><html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
           <td width="100"><strong class="fb">客户名称：</strong></td>
          <td><html-el:text property="r3_kh_name" size="30" style="width:170px;" maxlength="40" styleId="r3_kh_name" styleClass="webinput" /></td>
        </tr>
          <tr>
          <td width="15"></td>
          <td width="100"><strong class="fb">门店：</strong></td>
          <td><html-el:text property="store_name" size="30" style="width:170px;" maxlength="40" styleId="store_name" styleClass="webinput" /></td>
         
          <td width="150"><strong class="fb">产品型号：</strong></td>
          <td><html-el:text property="model_id" size="30" style="width:170px;" maxlength="40" styleId="model_id" onkeyup="upperCase(this.id)" styleClass="webinput" /></td>
          </tr>
          <tr>
          <td width="15"></td>
          <td width="100"><strong class="fb">消费者姓名：</strong></td>
          <td><html-el:text property="buyer_name" size="30" style="width:170px;" maxlength="40" styleId="buyer_name" styleClass="webinput" /></td>
          <td width="70"><strong class="fb">电话 ：</strong></td>
          <td colspan="2"><html-el:text property="buyer_tel" size="30" style="width:170px;" maxlength="40" styleId="buyer_tel" styleClass="webinput" /></td>
          <td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
           <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" /></td>     
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
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">门店</td>
         <td align="center" nowrap="nowrap">日期</td>
         <!-- 
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
         -->
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
       
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          
          <td align="left" nowrap="nowrap">${cur.buyer_name}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_tel}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_id}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_link_addr}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.l4_dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.store_name}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy/MM/dd" /></td>
           <!--
          <td align="center" nowrap="nowrap">${cur.md_size}</td>
          -->
          <td align="left" nowrap="nowrap">${cur.md_name }</td>
          <td align="right" nowrap="nowrap">${cur.counts }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.total_money}" type="currency" /> </span></td>
          
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="VADefailsOfConsumer.do">
    <input type="hidden" name="excel_all" id="excel_all" />
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript"> var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize},${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");							
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");							
			pager.addHiddenInputs("store_name", "${af.map.store_name}");							
			pager.addHiddenInputs("buyer_name", "${af.map.buyer_name}");		
			pager.addHiddenInputs("buyer_tel", "${af.map.buyer_tel}");
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("r3_kh_name", "${af.map.r3_kh_name}");
			pager.addHiddenInputs("model_id", "${af.map.model_id}");
			pager.addHiddenInputs("size_id", "${af.map.size_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="消费者信息">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>

        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">门店</td>
        <td align="center" nowrap="nowrap">日期</td>
        <!-- 
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
         -->
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
         
          <td align="left" nowrap="nowrap">${cur.buyer_name}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_tel}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_id}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_link_addr}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.l4_dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.store_name}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy/MM/dd" /></td>
          <!-- 
          <td align="center" nowrap="nowrap">${cur.md_size}</td>
           -->
          <td align="left" nowrap="nowrap">${cur.md_name }</td>
          <td align="right" nowrap="nowrap">${cur.counts }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.total_money}" type="currency" /> </span></td>
         
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
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
