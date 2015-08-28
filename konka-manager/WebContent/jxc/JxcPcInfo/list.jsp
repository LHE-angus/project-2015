<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width: 100%;">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：盘存管理 &gt; 盘存管理</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcPcInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableTop">
        <tr>
          <td width="80%" nowrap="nowrap"> <strong class="fb">产品大类：</strong>
            <html-el:select property="pd_type" styleId="pd_type" style="width:80px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${basePdTypeList}">
                <html-el:option value="${cur.pd_type}">${fn:escapeXml(cur.pd_name)}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            <span id="otherPdType" style="display:none">
            <html-el:select property="jxc_pd_type_id" styleId="jxc_pd_type_id" styleClass="bdfont" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${jxcPdTypeList}" var="cur">
                <html-el:option value="${cur.id}">${cur.name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </span> &nbsp;
            <input class="bgSearch" type="button" name="Submit" id="selectButton" value="搜 索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <input class="bgButtonAdd" type="button" name="Submit2" value="新 增" onclick="location.href='JxcPcInfo.do?method=add&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize();" />
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="JxcPcInfo.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tableClass">
        <tr>
          <th width="4%" align="center" nowrap="nowrap">序号</th>
          <th width="12%"nowrap="nowrap" align="center">产品大类</th>
          <th width="12%" nowrap="nowrap" align="center">产品品牌</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th width="12%" nowrap="nowrap" align="center">所属系统</th>
          <th width="6%" nowrap="nowrap" align="center">盘存数量</th>
          <th width="12%" nowrap="nowrap" align="center">盘存时间</th>
          <th width="12%" nowrap="nowrap" align="center">添加时间</th>
          <th width="6%" nowrap="nowrap" align="center">操作</th>
        </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="left">${fn:escapeXml(cur.pd_type_name)}</td>
              <td align="left">${fn:escapeXml(cur.brand_name)}</td>
              <td align="left">${fn:escapeXml(cur.pd_name)}</td>
              <td align="left">
                <c:if test="${cur.own_sys eq 1}">家电下乡</c:if>
                <c:if test="${cur.own_sys ne 1}">非家电下乡</c:if>
              </td>
              <td align="left">${cur.pc_num}</td>
              <td align="center">
                <fmt:formatDate value="${cur.pc_date}" pattern="yyyy-MM-dd" />
              </td>
              <td align="center">
                <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
              </td>
              <td align="center" nowrap="nowrap"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcPcInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看 </span> </td>
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
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcPcInfo.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tableClass">
        <tr>
          <td height="40" align="center">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("jxc_pd_type_id", "${af.map.jxc_pd_type_id}");
			            pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
			            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
			            document.write(pager.toString());
			   </script>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">
var f = document.forms[0];

$(document).ready(function(){
	$("#pd_type").change();
});

$("#selectButton").click(function(){
	if (Validator.Validate(f, 3)){
		f.submit();
	}
});	

/*产品类型 Begin*/
$("#pd_type").change(function(){
	var pd_type = $(this).val();
	if("" != pd_type && 0 == pd_type) {
		$("#otherPdType").show();
		$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
	} else {
		$("#otherPdType").hide();
		$("#jxc_pd_type_id").removeAttr("dataType").val("");
	}
});
/*产品类型 End*/
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
