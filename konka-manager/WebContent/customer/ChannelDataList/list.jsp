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
<body >
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/ChannelDataList">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="is_first" value="1"/>
      <table width="100%" border="0" cellpadding="0" class="rtable1">
       <tr>
          <td >
           &nbsp;&nbsp;<strong class="fb">订单号:</strong>
            <html-el:text property="column_8_like" styleId="column_8_like" maxlength="20" size="9"  />
          &nbsp;&nbsp;<strong class="fb">创建日期:</strong>
            <html-el:text property="s_date" styleId="s_date" size="10" maxlength="10" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
            至<html-el:text property="e_date" styleId="e_date" size="10"  maxlength="10" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
             &nbsp;&nbsp;<strong class="fb">凭证日期:</strong>
            <html-el:text property="startDate1" styleId="startDate1" size="10" maxlength="10" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
            至
            <html-el:text property="endDate1" styleId="endDate1" size="10"  maxlength="10" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
            &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <div class="rtabcont2" id="tb_div">
    <input type="button" id="export_excel" class="but_excel" value="导出" style="cursor: pointer;"></input>
	       	
  <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    当前页合计：数量：<c:out value="${pagerCount}"/>&nbsp;&nbsp;金额： <c:out value="${pagerMoney}"/>&nbsp;&nbsp;&nbsp;&nbsp;查询结果合计：数量： <c:out value="${allCount}"/>&nbsp;&nbsp;金额：<c:out value="${allMoney}"/>
    <div style="overflow-x:auto;"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">销售组织</td>
        <td align="center" nowrap="nowrap">售达方</td>
        <td align="center" nowrap="nowrap">名称(售)</td>
        <td align="center" nowrap="nowrap">送达方</td>
        <td align="center" nowrap="nowrap">名称（送）</td>
        <td align="center" nowrap="nowrap">创建日期</td>
        <td align="center" nowrap="nowrap">凭证日期</td>
        <td align="center" nowrap="nowrap">订单号</td>
        <td align="center" nowrap="nowrap">订单类型</td>
        <td align="center" nowrap="nowrap">项目</td>
        <td align="center" nowrap="nowrap">机型</td>
        <td align="center" nowrap="nowrap">订单数量</td>
        <td align="center" nowrap="nowrap">单价（含税）</td>
        <td align="center" nowrap="nowrap">总金额（含税）</td>
        <td align="center" nowrap="nowrap">交货单数量</td>
        <td align="center" nowrap="nowrap">已发货数量</td>
        <td align="center" nowrap="nowrap">开发票数量</td>
        <td align="center" nowrap="nowrap">K007（含税）</td>
        <td align="center" nowrap="nowrap">总净值金额(含税)</td>
        <td align="center" nowrap="nowrap">客户物料号</td>
        <td align="center" nowrap="nowrap">办事处</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr> 
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.column_25}</td>
          <td align="left" nowrap="nowrap">${cur.column_1}</td>
       	  <td align="left" nowrap="nowrap">${cur.map.column_04}</td>
          <td align="left" nowrap="nowrap">${cur.column_5}</td>
          <td align="left" nowrap="nowrap">${cur.map.column_06}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.column_7}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.column_26}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap">${cur.column_8}</td>
          <td align="center" nowrap="nowrap">${cur.column_9}</td>
          <td align="center" nowrap="nowrap">${cur.column_10}</td>
          <td align="center" nowrap="nowrap">${cur.column_11}</td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_13}" pattern="#,##0.00"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_30}" pattern="#,###"/></td>
		  <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_28}" pattern="#,###"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_29}" pattern="#,###"/></td>
          <td align="center" nowrap="nowrap">${cur.column_15}</td>
          <td align="center" nowrap="nowrap">${cur.column_17}</td>
          <td align="center" nowrap="nowrap">${cur.column_23}</td>
          <td align="center" nowrap="nowrap">${cur.column_24}</td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
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
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelDataList.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");	
            pager.addHiddenInputs("column_8_like", "${af.map.column_8_like}");
            pager.addHiddenInputs("s_date", "${af.map.s_date}");
            pager.addHiddenInputs("e_date", "${af.map.e_date}");
            pager.addHiddenInputs("startDate1", "${af.map.startDate1}");
            pager.addHiddenInputs("endDate1", "${af.map.endDate1}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	 // 导出excel
    $("#export_excel").click(function(){
    	$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' id='excel_all' />");
		$("#bottomPageForm").submit();
		$("#excel_all").val("");
    });
});

</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
