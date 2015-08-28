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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdVerification">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;"> 专卖店编号：
            <html-el:text property="zmd_sn_like" maxlength="30" />
            &nbsp;经营性质：
            <html-el:select property="busi_type" styleId="busi_type" style="width:100px;" onchange="this.form.submit();">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList10000}">
                <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;审核状态：
            <html-el:select property="arc_state" styleId="arc_state" style="width:100px;" onchange="this.form.submit();">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="0">未备案</html-el:option>
              <html-el:option value="1">备案通过</html-el:option>
              <html-el:option value="-1">备案未通过</html-el:option>
            </html-el:select>
            &nbsp;
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center"><font class="blue">序号</font></td>
        <td width="10%" nowrap="nowrap"  align="center"><font class="blue">专卖店编号</font></td>
        <td width="10%" nowrap="nowrap"  align="center"><font class="blue">资质客户</font></td>
        <td align="center" nowrap="nowrap"><font class="blue">专卖店地址</font></td>
        <td width="6%" align="center" nowrap="nowrap"><font class="blue">营业面积(㎡)</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">租赁期</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">经营性质</font></td>
        <td width="6%" align="center" nowrap="nowrap"><font class="blue">审核状态</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">备案申请时间</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td nowrap="nowrap" title="${cur.zmd_sn}"><font class="blue12px">
            <c:out value="${fnx:abbreviate(cur.zmd_sn, 2 * 5, '...')}" />
            </font></td>
          <td align="center" nowrap="nowrap">${cur.map.zmd_user_name}</td>
          <td align="left" title="${cur.addr}"><font class="blue12px">
            <c:out value="${fnx:abbreviate(cur.addr, 2 * 10, '...')}" />
            </font></td>
          <td nowrap="nowrap" align="right"><font class="blue12px">${cur.busi_area}</font>
            <c:if test="${empty cur.busi_area}"><span style="color:#999;">未填写</span></c:if></td>
          <td align="center"><c:if test="${!empty cur.rent_end && !empty cur.rent_start}"> <font class="blue12px">
              <fmt:formatDate value="${cur.rent_start}" pattern="yyyy-MM-dd " />
              至
              <fmt:formatDate value="${cur.rent_end}" pattern="yyyy-MM-dd " />
              </font> </c:if>
            <c:if test="${empty cur.rent_end ||empty cur.rent_start }"><span style="color:#999;">未填写</span></c:if></td>
          <td nowrap="nowrap" align="center"><c:choose>
              <c:when test="${!empty cur.busi_type}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList10000}">
                  <c:if test="${cur_2.type_id eq cur.busi_type}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise><span style="color:#999;">未填写</span></c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap" title="${empty cur.audit_comment ?'无' : cur.audit_comment}"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.map.audit_status eq 1}">备案通过</c:when>
              <c:when test="${cur.map.audit_status eq 0}">未备案</c:when>
              <c:when test="${cur.map.audit_status eq -1}">备案未通过</c:when>
            </c:choose>
            </font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.apply_date}" pattern="yyyy-MM-dd" />
            </font></td>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdVerification.do', 'edit','zmd_id=${cur.zmd_id}&'+$('#bottomPageForm').serialize())">备案审核</span></td>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdVerification.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("zmd_sn_like", "${af.map.zmd_sn_like}");
	            pager.addHiddenInputs("busi_type", "${af.map.busi_type}");
	            pager.addHiddenInputs("arc_state", "${af.map.arc_state}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#arc_state option[value='20300']").remove(); 
		
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}	
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
