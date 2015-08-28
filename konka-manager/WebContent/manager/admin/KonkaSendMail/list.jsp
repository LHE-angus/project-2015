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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  </div>
<div class="rtabcont1" style="font-weight:700;color:#F00;">
 说明：可以添加多个邮件接收收件人、抄送人，但至少添加一个主送，否则邮件无法发送。
 </div>
<div class="rtabcont1">
 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
   <tr class="tabtt1">
      <td width="5%" align="center" nowrap="nowrap">序号</td>
      <td nowrap="nowrap"  align="center">报表名称</td>
      <td width="10%" align="center">操作</td>
    </tr>
    <tbody>
      <tr> 
        <td nowrap="nowrap" align="center">1</td>
        <td align="left">A类经办彩电销售业绩排名(总经理、副总、经营部、财务经理、经办经理、多媒体营销部) </td>
         <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=11&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
       <tr> 
        <td nowrap="nowrap" align="center">2</td>
        <td align="left">B类经办彩电销售业绩排名(总经理、副总、经营部、财务经理、经办经理、多媒体营销部) </td>
         <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=12&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
       <tr> 
        <td nowrap="nowrap" align="center">3</td>
        <td align="left">C类经办彩电销售业绩排名(总经理、副总、经营部、财务经理、经办经理、多媒体营销部) </td>
        <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=13&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
	  
	        <tr> 
        <td nowrap="nowrap" align="center">4</td>
        <td align="left">A类经办彩电销售业绩排名（市场部、销售部、培训部、财务部） </td>
         <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=110&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
       <tr> 
        <td nowrap="nowrap" align="center">5</td>
        <td align="left">B类经办彩电销售业绩排名（市场部、销售部、培训部、财务部 </td>
         <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=120&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
       <tr> 
        <td nowrap="nowrap" align="center">6</td>
        <td align="left">C类经办彩电销售业绩排名（市场部、销售部、培训部、财务部 </td>
        <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=130&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
      <tr> 
        <td nowrap="nowrap" align="center">7</td>
        <td align="left">彩电综合业绩排名（分公司结算） </td>
        <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=21&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
      <tr> 
        <td nowrap="nowrap" align="center">8</td>
        <td align="left">彩电综合业绩排名（总部结算） </td>
        <td align="center" nowrap="nowrap">
        <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'view','id=1&'+$('#bottomPageForm').serialize())">查看</span>
        | -->
     	<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMail.do', 'add','info_type=22&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >编辑</span></logic-el:notMatch>
     	 </td>
      </tr>
    </tbody>
 </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>                                  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
