<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcShopStatisticsByArea">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <input type="hidden" name="is_agent" value="${af.map.is_agent}" />
    <input type="hidden" name="agent_id" value="${af.map.agent_id}" />
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td nowrap="nowrap">
       &nbsp;&nbsp;网点:
			<html-el:select property="id" styleId="id" style="width:260px;">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach items="${konkaEntpShopList_no_page}" var="cur" varStatus="vs">
              <html-el:option value="${cur.shop_id}">${fn:escapeXml(cur.shop_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
		<input class="bgSearch" type="button" name="buttonSearch" id="buttonSearch" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
	<div><html-el:button value="返 回" styleClass="bgButtonBack" property="fh" onclick="history.back();"/></div>

 <form id="listForm" name="listForm" method="post" action="">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%" nowrap="nowrap">行号</th>
      <th nowrap="nowrap">网点名称</th>
      <th width="12%" nowrap="nowrap">联系人</th>
      <th width="12%" nowrap="nowrap">联系电话</th>
      <th width="10%" nowrap="nowrap">操作</th>
    </tr>
    <c:forEach items="${konkaEntpShopList}" var="cur" varStatus="status">
      <tr>
        <td align="center">${status.count}</td>
        <td align="center">${cur.shop_name}</td>
        <td align="center">${cur.link_user}</td>
        <td align="center">${cur.link_phone}</td>
        <td align="center">
        <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopStatisticsByTime.do', 'view','id=${cur.shop_id}&shop_name=${cur.shop_name}&area_code_search=${af.map.area_code_search}&'+$('#bottomPageForm').serialize())">报表</span>
        </td>
      </tr>
    </c:forEach>
   
  </table>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcShopStatisticsByArea.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("id", "${af.map.id}");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("is_agent", "${fn:escapeXml(af.map.is_agent)}");
		       pager.addHiddenInputs("agent_id", "${fn:escapeXml(af.map.agent_id)}");
		       pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
		       pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
  
   
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:260
			
		}).multiselectfilter({width:150});                                         
		
	});                              
	var f=document.forms[0];
	$(".bgSearch").click(function(){
	   	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}else{
				f.submit();
			}
   });  
            
 
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>