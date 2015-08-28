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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/BaseProvinceList.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;">
            <ul>
             <li>
                <strong class="fb">区域名称：</strong>
                <html-el:text property="name" style="width:175px;" />
                <strong class="fb">&nbsp;&nbsp;省简称：</strong>
                <html-el:text property="p_code" maxlength="10" style="width:150px;" />
              </li>
              <li style="padding-top:3px;">
                <strong class="fb">所属区域：</strong>
                <select name="province" id="province" style="width:180px;">
                     <option value="">-请选择省/直辖市/自治区-</option>
                </select>&nbsp;
                <select name="city" id="city" style="width:100px;">
                     <option value="">-请选择市-</option>
                </select>&nbsp;
                <select name="country" id="country" style="width:100px;">
                     <option value="">-请选择县-</option>
                </select>&nbsp;&nbsp;
                <input class="but1" type="submit" name="Submit" value="搜索" />     
              </li>
           </ul>
         </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="BaseProvinceList.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tbody>
          <tr class="tabtt1">
         	<td width="30" nowrap="nowrap" align="center">序号</td> 
            <td width="6%" nowrap="nowrap">地区编码</td>
            <td nowrap="nowrap">区域名称</td>
            <td width="17%" nowrap="nowrap">区域简称</td>
            <td width="17%" nowrap="nowrap">父区域名称</td>
            <td width="17%" nowrap="nowrap">根区域名称</td>
            <td width="4%" nowrap="nowrap">省简称</td>
            <td width="10%" align="center" nowrap="nowrap">电话</td>
            <td width="10%" align="center" nowrap="nowrap">邮编</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
              <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="center"><a href="BaseProvinceList.do?method=view&p_index=${cur.p_index}&mod_id=${af.map.mod_id}" title="${cur.p_index}" style="text-decoration:underline">${fn:escapeXml(cur.p_index)}</a></td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.p_name)}</td>
            <td align="left" nowrap="nowrap">${cur.s_name}</td>
            <td align="left" nowrap="nowrap"><c:if test="${cur.par_index ne 0}"><a href="BaseProvinceList.do?method=view&p_index=${cur.par_index}&mod_id=${af.map.mod_id}" title="${cur.map.par_name}" style="text-decoration:underline">${fn:escapeXml(cur.map.par_name)}</a></c:if><c:if test="${cur.par_index eq 0}">--</c:if> </td>
            <td align="left" nowrap="nowrap"><a href="BaseProvinceList.do?method=view&p_index=${cur.root_code}&mod_id=${af.map.mod_id}" title="${cur.map.root_name}" style="text-decoration:underline">${fn:escapeXml(cur.map.root_name)}</a></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.p_code)}</td>
            <td align="center" nowrap="nowrap">${cur.tel_code}</td>
            <td align="center" nowrap="nowrap">${cur.post_code}</td>
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
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="BaseProvinceList.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("name", "${af.map.name}");
	            pager.addHiddenInputs("p_index", "${af.map.p_index}");
	            pager.addHiddenInputs("p_code", "${af.map.p_code}");
	            pager.addHiddenInputs("province", "${af.map.province}");
	            pager.addHiddenInputs("city", "${af.map.city}");
	            pager.addHiddenInputs("country", "${af.map.country}");
	            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){                                          
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
});

function noDelete(){
	//confirmDeleteAll(this.form);
	alert("暂不支持删除！");
	return null;
};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>