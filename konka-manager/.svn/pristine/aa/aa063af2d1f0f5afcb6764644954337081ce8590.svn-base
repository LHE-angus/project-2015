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
    <html-el:form action="/admin/MqtKonkaStocksPreview">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td>
	        <c:if test="${!empty kdList}">
	          <strong class="fb">分公司：</strong>
	          	<html-el:select property="dept_sn" styleId="dept_sn">
		          	<html-el:option value="">-请选择-</html-el:option>
		          	<c:forEach items="${kdList}" var="cur">
		          		<html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
		          	</c:forEach>
	          	</html-el:select>
	          	 &nbsp;&nbsp;
	         </c:if>
          <strong class="fb">R3编码：</strong>
            <html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like" />
           &nbsp;&nbsp;<strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like" />
           &nbsp;&nbsp;<strong class="fb">产品型号：</strong>
            <html-el:text property="pd_name_like" size="15" maxlength="20" styleId="pd_name_like" />
          &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div>
  <input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" />
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="center" width="8%">分公司</td>
          <td nowrap="nowrap" align="center" width="8%">R3编码</td>
          <td nowrap="nowrap" align="center">客户名称</td>
          <td nowrap="nowrap" align="center" width="8%">产品型号</td>
          <td nowrap="nowrap" align="center" width="6%">初始库存</td>
          <td nowrap="nowrap" align="center" width="6%">进货数量</td>
          <td nowrap="nowrap" align="center" width="8%">进货金额</td>
          <td nowrap="nowrap" align="center" width="6%">销售数量</td>
          <td nowrap="nowrap" align="center" width="8%">销售金额</td>
          <td nowrap="nowrap" align="center" width="6%">剩余库存</td>
          <td nowrap="nowrap" align="center" width="12%">盘存时间</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.r3_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.customer_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.pd_name)}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.initcount}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.incount}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.inmoney}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.outcount}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.outmoney}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.stocks}" pattern="0" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.ts}" pattern="yyyy-MM-dd HH:mm" /></td>
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
        </tr>
      </c:forEach>
      </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="MqtKonkaStocksPreview.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");	
			pager.addHiddenInputs("dept_sn", "${fn:escapeXml(af.map.dept_sn)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");	
			pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
//导出
$("#export_22").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
		$("#bottomPageForm").submit();	
	}
});                                     
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>