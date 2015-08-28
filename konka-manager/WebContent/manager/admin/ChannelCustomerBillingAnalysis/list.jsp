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
  <div class="rtabcont2" >
  <html-el:form action="/admin/ChannelCustomerBillingAnalysis.do" enctype="multipart/form-data">
        <html-el:hidden property="method" value="list"/>
         <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
         <table>
          <tr>
          <td width="15"></td>
          <td >
          <strong class="fb">类别：</strong>
          <c:forEach var="cur" items="${idtList}" varStatus="vs">
            <html-el:radio property="type" styleId="type_${vs.count}" value="${cur.data_type}" onclick="this.form.submit();" /><label for="type_${vs.count}">&nbsp;${cur.type_name }</label>
            </c:forEach>&nbsp;&nbsp;
          <strong class="fb">售达方:</strong>
            <html-el:text property="column_1" styleId="column_1" maxlength="40" />
            &nbsp;&nbsp;<strong class="fb">机型:</strong>
            <html-el:text property="column_11" styleId="column_11" maxlength="10" size="9"  />
           </td>
        </tr>
          <tr>
             <td width="15"></td>
             <td><strong class="fb">时间：</strong>
              <html-el:radio property="date" styleId="date1" value="0"  />每天
               <html-el:radio property="date" styleId="date2" value="1"  />每月
              &nbsp;<strong class="fb">范围：</strong>
              <html-el:text property="date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
              &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索"  />
               
              </td>
             </tr> 
         </table>
  
  </html-el:form>
  </div>
  
  <logic-el:match name="popedom" value="+1+">
    </logic-el:match>
    &nbsp;<input class="but_excel" type="submit"  value="导出" name="toExcelButton1" onclick="toExcel('divExcel', '?method=toExcel');" />
    <div id="divExcel" title="客户结算统计.xls" class="rtabcont2" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
            <td width="30" align="center" nowrap="nowrap">序号</td>
            <td align="center" nowrap="nowrap">售达方</td>
            <td align="center" nowrap="nowrap">机型</td>
            <td align="center" nowrap="nowrap">数量</td>
            <td align="center" nowrap="nowrap">平均单价（含税）</td>
            <td align="center" nowrap="nowrap">总金额（含税）</td>
           
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		        <tr>
		            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
		            <td align="center" nowrap="nowrap">${cur.column_1}</td> 
		            <td align="center" nowrap="nowrap">${cur.column_11}</td>
		            <td align="center" nowrap="nowrap">${cur.map.num}</td>
		            <td align="center" nowrap="nowrap">${cur.map.price}</td> 
		            <td align="center" nowrap="nowrap">${cur.map.total}</td> 
		       
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
				</tr>
				</c:forEach>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelCustomerBillingAnalysis.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("type", "${af.map.type}");	
            pager.addHiddenInputs("column_1", "${af.map.column_1}");
            pager.addHiddenInputs("column_11", "${af.map.column_11}");
            pager.addHiddenInputs("date", "${af.map.date}");
            pager.addHiddenInputs("date_start", "${af.map.date_start}");
            pager.addHiddenInputs("date_end", "${af.map.date_end}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	if ("true" == "${empty entityList}") {
		$("#toExcelButton1").attr("disabled","true");
	}
});





//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
