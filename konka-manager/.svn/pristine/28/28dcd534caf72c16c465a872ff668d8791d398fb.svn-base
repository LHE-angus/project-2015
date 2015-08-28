<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileCustVisit"  method="post">
      <html-el:hidden property="visit_id" styleId="visit_id" value="${af.map.visit_id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
       <html-el:hidden property="report_type" styleId="report_type" value="${report_type}" />
       <html-el:hidden property="is_del" styleId="is_del" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.visit_type_id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr><td colspan="2">
         <div align="right"><span style="cursor:pointer;text-decoration: none;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','list' ,'report_type=${af.map.report_type}&is_del=0&mod_id=${af.map.mod_id}')">查看历史记录</span></div>
         </td></tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">创建日期：</td>
          <td width="88%" align="left">
		      <c:if test="${not empty today}">
		      ${today}
		       </c:if>
		        <c:if test="${empty today}">
		        <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" var="add_date" />
		       ${add_date}
		        </c:if>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}">开始日期：</c:if>
          <c:if test="${report_type eq 4}">开始日期：</c:if>
          </td>
          <td width="88%" align="left">
             <fmt:formatDate value="${af.map.begin_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="begin_date" styleId="begin_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr><!--
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}">结束日期：</c:if>
          <c:if test="${report_type eq 4}">结束日期：</c:if>
          </td>
          <td width="88%" align="left">
             <fmt:formatDate value="${af.map.end_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="end_date" styleId="end_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr>
        --><c:if test="${report_type eq 3}">
         <tr>
    		 <td width="12%" nowrap="nowrap" class="title_item" align="right">客户名称：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="cust_id" styleId="cust_id" value="${r3_code}">
    		<html-el:option value="">-请选择客户-</html-el:option>
	    		<c:forEach items="${konkaR3ShopDevList}" var="cur">
	    		<c:if test="${not empty cur.cust_id}">
	    		<html-el:option value="${cur.cust_id}">${cur.cust_name}</html-el:option>
	    		</c:if>
	    		</c:forEach>
	    	</html-el:select>
	    	<html-el:hidden property="cust_name" styleId="cust_name"/>
	    	</td>
  	   </tr>
  	    <tr>
	    <td align="right" nowrap="nowrap" class="title_item">被访人：</td>
	    <td align="left" nowrap="nowrap">
	        <html-el:text property="consumer_name" styleId="consumer_name"></html-el:text>
	    </td>
		  </tr>
		    <tr>
		    <td align="right" nowrap="nowrap" class="title_item">被访人电话：</td>
		    <td align="left" nowrap="nowrap">
		        <html-el:text property="consumer_phone" styleId="consumer_phone"></html-el:text>
		    </td>
		  </tr>
	  </c:if>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}">开拓说明：</c:if>
          <c:if test="${report_type eq 4}">事物说明：</c:if></td>
          <td width="88%" align="left">
             <html-el:textarea property="visit_desc" styleId="visit_desc" styleClass="webinput" style="width:40%;height:60px;" />
             <div id="visit_desc_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
         <c:if test="${report_type eq 3}">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注说明：</td>
          <td width="88%" align="left">
             <html-el:textarea property="remark" styleId="remark" styleClass="webinput" style="width:60%;height:60px;" />
             <div id="visit_desc_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        </c:if>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><c:if test="${report_type eq 3}">开拓状态：</c:if> <c:if test="${report_type eq 4}">拜访状态：</c:if></td>
          <td width="88%" align="left">
            <html-el:select property="state" styleId="state" style="width:120px;">
              <html-el:option value="">--请选择--</html-el:option>
	          <c:if test="${report_type eq 3}">
	      		   <html-el:option value="1">开拓中</html-el:option>
	      		   <html-el:option value="2">已关闭</html-el:option>
	      	   <!--<html-el:option value="3">已开拓成功</html-el:option>-->
	      	  </c:if>
	          <c:if test="${report_type eq 4}">
	      		   <html-el:option value="0">需跟踪</html-el:option>
	      		   <html-el:option value="1">已关闭</html-el:option>
      	      </c:if>
        	 </html-el:select>
          </td>
        </tr>
        <tr>	 
          <td colspan="2" align="center">
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
        <tr style="height: 100px;"></tr>
      </table>
    </html-el:form>
  </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>

<script type="text/javascript"><!--//<![CDATA[

//]]>-->--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
