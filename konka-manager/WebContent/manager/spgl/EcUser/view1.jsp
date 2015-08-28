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
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
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

  <div class="rtabcont2">
    <html-el:form action="/spgl/EcUser.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}"><c:set value="true" var="is_admin" /></c:if>
     <div align="left">
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table2">
        <tr class="tr1">
         <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="15%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">单价数量</td>          
          <td width="10%" nowrap="nowrap" align="left">获得佣金</td>   
          <td width="10%" nowrap="nowrap" align="center">时间</td>
          <td width="10%" nowrap="nowrap" align="center">提现兑换状态</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.map.pd_name }"/></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${fn:escapeXml(cur.map.price)}" pattern="￥0.00" /> * ${cur.map.num }</span></td>
              <td align="left" nowrap="nowrap"><c:if test="${cur.map.rebates_status eq 1 }"><font color="green">
              <fmt:formatNumber value="${fn:escapeXml(cur.map.rebates)}" pattern="￥0.00" /></font></c:if><c:if test="${cur.map.rebates_status ne 1 }"><font color="red">
              <fmt:formatNumber value="${fn:escapeXml(cur.map.rebates)}" pattern="￥0.00" /></font></c:if></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.rebates_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap">
              	<c:if test="${cur.map.rebates_status eq 1 }">提现 已发放</c:if>
              	<c:if test="${cur.map.rebates_status eq 2 }">提现 等待发放</c:if>
              	<c:if test="${cur.map.rebates_status eq 3 }">已兑换积分</c:if>
              	<c:if test="${cur.map.rebates_status eq 4 }">抵扣货款</c:if>
              </td>  
             </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="6"> 暂无记录 </td>
            </tr>
          </c:if>
        </tbody>
         <tr>
          <td colspan="6" height="40"  align="center">
                <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		
		
	});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>