<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择产品</title>
<style type="text/css">
body {
	font-size:12px;
}
label {
	cursor:pointer;
}
.ti {
	background-color:#eee;
	text-align:right;
	white-space:nowrap;
}
.grp {
	font-weight:bold;
	color:#74685F;
	border-bottom:1px solid #74685F;
	padding:5px;
	margin-top:5px;
}
.td-title {
	padding-top:10px;
	font-size:12px;
	font-weight:700;
	border-bottom:1px solid #ccc;
}
</style>
</head>
<body>
<div style="height:30px;line-height:30px; background-color:#F8ECE0;padding-left:5px;">第 <span style="font-size:150%;color:#F00;font-style:italic;font-weight:800;font-family:verdana;">1</span> 步：选择产品 <span style="color:#ccc;">-&gt; 第 2 步：填写订单信息 -&gt; 第 3 步：成功下单</span></div>
<html-el:form action="/manager/JxcKonkaOrderRegister?method=listTHpdselect">
  <strong class="fb">产品型号：</strong>
  <html-el:text property="md_name_like" styleId="md_name_like" maxlength="40" />
  <html-el:hidden property="pager.requestPage" />
  <input class="but1" type="submit" name="Submit" value="搜索" />
  <input type="hidden" name="pd_ids" id="pd_ids" value="${af.map.pd_ids}" />
  
  <div class="fb"><strong class="fb">已选择（<span style="color:#F00;">${fn:length(entity2List)}</span>）</strong></div>
  <table width="100%" border="1" cellspacing="0" cellpadding="3" style="border-collapse:collapse;">
    <tr class="tabtt1">
      <td width="5%" nowrap="nowrap" align="center">序号</td>
      <td nowrap="nowrap" align="center">产品型号</td>
      <td width="12%" nowrap="nowrap" align="center">尺寸</td>
      <td width="12%" nowrap="nowrap" align="center">系列</td>
      <td width="15%" nowrap="nowrap" align="center">功能</td>
      <td width="8%" nowrap="nowrap" align="center">选择</td>
    </tr>
    <tbody>
      <c:forEach var="cur" items="${entity2List}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="left" align="left">
          ${fn:escapeXml(cur.md_name)}
            </td>
          <td align="center"><c:if test="${(cur.md_size eq 0) || (empty cur.md_size)}">无</c:if>
            <c:if test="${cur.md_size ne 0}">${cur.md_size}</c:if></td>
          <td align="center">${cur.md_serise}
            <c:if test="${empty cur.md_serise}">无</c:if></td>
          <td align="left"> ${cur.is_parts eq 1 ? '配件&nbsp;' : ''}${cur.label_3d eq 1 ? '3D&nbsp;' : ''}${cur.label_int eq 1 ? '智能&nbsp;' : ''}${cur.label_www eq 1 ? '网络' : ''} </td>
          <td align="center"><input type="button" name="sel" value=" 取消 " onclick="this.disabled = 'disabled'; this.form.pd_ids.value = '${af.map.pd_ids}'.replace(',${cur.pd_id}', '');this.form.submit();" /></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <div style="text-align:right;">
  <input class="but1" type="button" name="reset" value=" 清空所选 " onclick="location.href='JxcKonkaOrderRegister.do?method=listTHpdselect';" ${fn:length(entity2List) eq 0 ? 'disabled="disabled"' : ''} />
  <input class="but1" type="button" name="reset" value=" 确认以上产品，下一步&gt;&gt; " onclick="location.href='JxcKonkaOrderRegister.do?method=addTHInMobile&pd_ids=' + this.form.pd_ids.value;" ${fn:length(entity2List) eq 0 ? 'disabled="disabled"' : ''} /></div>
  <div class="fb"><strong class="fb">待选择</strong>
  <c:if test="${not empty af.map.md_name_like}">（<span style="color:#F00;font-weight:700;">“${af.map.md_name_like}”</span> 的搜索结果）</c:if></div>
  <table width="100%" border="1" cellspacing="0" cellpadding="3" style="border-collapse:collapse;">
    <tr class="tabtt1">
      <td width="5%" nowrap="nowrap" align="center">序号</td>
      <td nowrap="nowrap" align="center">产品型号</td>
      <td width="12%" nowrap="nowrap" align="center">尺寸</td>
      <td width="12%" nowrap="nowrap" align="center">系列</td>
      <td width="15%" nowrap="nowrap" align="center">功能</td>
      <td width="8%" nowrap="nowrap" align="center">选择</td>
    </tr>
    <tbody>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="left" align="left">
          ${fn:escapeXml(cur.md_name)}
            </td>
          <td align="center"><c:if test="${(cur.md_size eq 0) || (empty cur.md_size)}">无</c:if>
            <c:if test="${cur.md_size ne 0}">${cur.md_size}</c:if></td>
          <td align="center">${cur.md_serise}
            <c:if test="${empty cur.md_serise}">无</c:if></td>
          <td align="left"> ${cur.is_parts eq 1 ? '配件&nbsp;' : ''}${cur.label_3d eq 1 ? '3D&nbsp;' : ''}${cur.label_int eq 1 ? '智能&nbsp;' : ''}${cur.label_www eq 1 ? '网络' : ''} </td>
          <td align="center"><c:set var="curpdid" value=",${cur.pd_id}," />
            <c:if var="dis" test="${fn:contains(af.map.pd_ids, curpdid)}">
              <input type="button" name="sel" value=" 已选 " disabled="disabled" />
            </c:if>
            <c:if test="${not dis}">
              <input type="button" name="sel" value=" 选择 " onclick="this.disabled = 'disabled'; this.form.pd_ids.value += (this.form.pd_ids.value.length == 0 ? ',' : '') + '${cur.pd_id}' + ',';this.form.submit();" />
            </c:if></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</html-el:form>
<form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaOrderRegister.do">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td height="80"><div style="text-align:left; padding-right: 5px;"> 
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
          <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "listTHpdselect");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("pd_ids", "${fn:escapeXml(af.map.pd_ids)}");
				pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
				document.write(pager.toString());
			  </script> 
        </div></td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
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
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
