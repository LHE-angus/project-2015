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
    <html-el:form action="/zmd/KonkaXxZmdQuotaSet" styleClass="form_search">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
            <td><strong class="fb">专卖店编号：</strong><html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
              	<html-el:option value="">==请选择==</html-el:option>
           		<c:forEach var="cur" items="${zmdList}">
           			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
           		</c:forEach>
              </html-el:select></td>
            <td><strong class="fb">指标日期：</strong>
            	<html-el:select property="quota_date_year" styleId="quota_date_year">
            		<html-el:option value="">请选择...</html-el:option>
            		<html-el:option value="2012">2012年</html-el:option>
            		<html-el:option value="2013">2013年</html-el:option>
            		<html-el:option value="2014">2014年</html-el:option>
            		<html-el:option value="2015">2015年</html-el:option>
            		<html-el:option value="2016">2016年</html-el:option>
            		<html-el:option value="2017">2017年</html-el:option>
            		<html-el:option value="2018">2018年</html-el:option>
            		<html-el:option value="2019">2019年</html-el:option>
            		<html-el:option value="2020">2020年</html-el:option>
            	</html-el:select>
            </td>
			<td><input class="but1" type="button" value="查看" id="btn_search" /></td>
		</tr>
	  </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
	<c:if test="${empty af.map.zmd_id}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr>
				<td align="left" height="28"><strong class="fb" style="color:green;">请选择查询条件点击查询！</strong></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${not empty af.map.zmd_id}">
	    <html-el:form action="/zmd/KonkaXxZmdQuotaSet" styleClass="form_quota">
	    <html-el:hidden property="method" value="save" />
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="zmd_id" value="${af.map.zmd_id}" />
	    <html-el:hidden property="quota_date_year" value="${af.map.quota_date_year}" />
	    <html-el:hidden property="queryString" />
	      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	        <c:if test="${empty entityList}">
	          <tr>
	            <td colspan="2" align="center"><strong class="fb" style="color:green;">暂无数据，请填写相应指标值点击保存！</strong></td>
	          </tr>
	          <c:forEach begin="1" end="12" varStatus="vs">
	             <tr>
	                <td width="17%" nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>${af.map.quota_date_year}年${vs.count}月：</td>
	                <td><input type="text" name="quota_date_${vs.count}" size="10" maxlength="10" id="quota_date_${vs.count}" value="" /><span style="margin-left:5px;">万元</span></td>
	             </tr>
	          </c:forEach>
	        </c:if>
	        <c:if test="${not empty entityList}">
			  <c:forEach items="${entityList}" varStatus="vs" var="cur">
	             <tr>
	                <td width="17%" nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span><fmt:formatDate value="${cur.quota_date}" pattern="yyyy年MM月" />：</td>
	                <td><input type="text" name="quota_date_${vs.count}" size="10" maxlength="6" id="quota_date_${vs.count}" value="${cur.quota_value}" /><span style="margin-left:5px;">万元</span></td>
	             </tr>
	          </c:forEach>	        
	        </c:if>
	        <tr>
	          <td>&nbsp;&nbsp;</td>
	          <td><input class="but4" type="button" name="Submit4" value="保存" id="btn_sub" />
	            <input class="but3" type="reset"  value="重填 " />
	            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
	        </tr>
	      </table>
		 </html-el:form>
	</c:if>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#zmd_id"         ).attr("dataType" , "Require").attr("msg" , "选择专卖店！");
	$("#quota_date_year").attr("dataType" , "Require").attr("msg" , "请选择日期！");
	
	$("#btn_search").click(function (){
		if(Validator.Validate(this.form, 1)){
			$(".form_search").submit();
		}
	});
	
	for(var i =1 ; i<=12 ; i++){
		$("#quota_date_" + i).attr("dataType" , "Require").attr("msg" , "请填写数值！");
		$("#quota_date_" + i).focus(setOnlyNum);
	}
	
	$("#btn_sub").click(function (){
		if(Validator.Validate(this.form, 3)){
			$(".form_quota").submit();
		}
	});

});

function setOnlyNum() {
	$(this).keypress(function (){
		if(!this.value.match(/^[\+]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "";
	});
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>