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
    <html-el:form action="/admin/KonkaR3ShopNew.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">分公司：</strong>
          	<html-el:select property="dept_id" styleId="dept_id" style="width:110px">
              <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
          </td>
          <td>
          	<strong class="fb">客户名称：</strong>
          	<html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" />
          </td>
          <td>
          	<strong class="fb">R3编码：</strong>
          	<html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" />
          </td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" width="10%">分公司</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" >${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
            <td align="center" nowrap="nowrap"><c:if test="${!empty cur.map.c_name}">${cur.map.c_name}[${cur.map.c_index}]</c:if><c:if test="${empty cur.map.c_name}">无</c:if></td>
            <td align="left">${fn:escapeXml(cur.map.r3_code)}</td>
            <td align="left" >${fn:escapeXml(cur.map.merged_r3_code)}</td>
            <td align="center" nowrap="nowrap">
            <a href="${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=add&mod_id=${af.map.mod_id}&CUSTID=${cur.map.konka_r3_id}&FROMSALESMAN=1&from_manager_user_id=${from_manager_user_id}">
            <span style="cursor:pointer;" class="fblue">进货</span></a>&nbsp;
            <a href="${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=addTH&mod_id=${af.map.mod_id}&CUSTID=${cur.map.konka_r3_id}&FROMSALESMAN=1&from_manager_user_id=${from_manager_user_id}">
            <span style="cursor:pointer;" class="fblue">退货</span></a>&nbsp;
            <a href="${ctx}/customer/manager/JxcKonkaOrderRegister.do?mod_id=${af.map.mod_id}&CUSTID=${cur.map.konka_r3_id}&FROMSALESMAN=1&is_all=1">
            <span style="cursor:pointer;" class="fblue">历史记录</span></a></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopNew.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//分公司列表初始化
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();

	//默认当前分公司
	var fgs = '${current_fgs_code}';
	if(fgs!=""){
		$("#dept_id").val(fgs);
		$("#dept_id").change();
	}
}) 
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>