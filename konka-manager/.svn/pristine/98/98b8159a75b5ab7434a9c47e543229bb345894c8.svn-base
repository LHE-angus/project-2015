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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
.rtable1 td {
	padding:0px 2px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/JcfxKczzKh">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="jcfxQglsqdzzqkfgspmList" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	<tr>
       	<td align="right"><strong class="fb">客户分组：</strong></td>
       		<td>
       			 <html-el:select property="fz_id" styleId="fz_id">
					 <html-el:option value="">请选择</html-el:option>
	              <c:forEach items="${fzlist}" var="cur">
	                <html-el:option value="${cur.id}">${cur.title}</html-el:option>
	              </c:forEach>
	            </html-el:select>
	             <a href="${ctx}/manager/admin/JcfxKczzKh.do?method=list"  style="font-family:Microsoft YAHEI;color: red;" >分组客户维护</a>
	        </td>
       		<td align="right"><strong class="fb">年月：</strong></td>
       		<td>
       			 <html-el:select property="year" styleId="year">
	              <c:forEach items="${yearList}" var="cur">
	                <html-el:option value="${cur}">${cur}年</html-el:option>
	              </c:forEach>
	            </html-el:select>
	            <html-el:select property="month" styleId="month">
	              <html-el:option value="01">1月</html-el:option>
	              <html-el:option value="02">2月</html-el:option>
	              <html-el:option value="03">3月</html-el:option>
	              <html-el:option value="04">4月</html-el:option>
	              <html-el:option value="05">5月</html-el:option>
	              <html-el:option value="06">6月</html-el:option>
	              <html-el:option value="07">7月</html-el:option>
	              <html-el:option value="08">8月</html-el:option>
	              <html-el:option value="09">9月</html-el:option>
	              <html-el:option value="10">10月</html-el:option>
	              <html-el:option value="11">11月</html-el:option>
	              <html-el:option value="12">12月</html-el:option>
	            </html-el:select>
       		</td>
       		<td>
       		<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
	        <input class="but_excel" type="button" name="export_excel" disabled="true" id="export_excel" value="导出" />
       		</td>
       	</tr>
      </table>
  </div><!--
  <div style="text-align: left;padding-left: 10px">
  	<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
	<input class="but_excel" type="button" name="export_excel" disabled="disabled" id="export_excel" value="导出" />
  </div>
  --></html-el:form>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
     <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="left" colspan="2">统计时间：   ${firestday}~${curday}</td>
        <td nowrap="nowrap" width="150px;" align="right" colspan="7" >单位：万元、天</td>
      </tr>
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >区域</td>
         <td nowrap="nowrap" align="left" >分公司</td>
        <td  nowrap="nowrap" align="right">期初库存</td> 
        <td nowrap="nowrap" align="right" >本期进货</td>
        <td nowrap="nowrap" width="150px;" align="right">本期零售</td>
        <td nowrap="nowrap" width="150px;" align="right">期末库存</td>
         <td nowrap="nowrap" width="150px;" align="center">周转天数</td>
         <td nowrap="nowrap" width="150px;" align="center"></td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
      	<td align="center" nowrap="nowrap">${vs.count}</td>
      	   <td align="center" nowrap="nowrap">${cur.AREA_NAME}</td>
      	<td align="left" nowrap="nowrap">${cur.BRANCH_AREA_NAME}</td>
      	<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.INIT_MONEY}" pattern="0.00" /></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.COMM_MONEY}" pattern="0.00" /></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_MONEY}" pattern="0.00" /></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.END_MONEY}" pattern="0.00" /></td> 
        <td align="right" nowrap="nowrap"> <fmt:formatNumber value="${cur.CUR_DAY}" pattern="0" /></td>
         <td align="right" nowrap="nowrap"></td>
      </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#button").click(function(){
		if (Validator.Validate(this.form, 1)){
			this.form.submit();
			}
	});
});
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
