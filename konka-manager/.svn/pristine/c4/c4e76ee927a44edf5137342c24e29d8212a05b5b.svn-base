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
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcShopStatisticsByTime">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <input type="hidden" name="search_flag" value="search_flag" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td nowrap="nowrap"><strong class="fb">管辖区域：</strong>
            <html-el:select property="area_code_search" styleId="area_code_search" style="width:80px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${area_list}" var="cur" varStatus="vs">
                <html-el:option value="${cur[0]}">${fn:escapeXml(cur[1])}</html-el:option>
              </c:forEach>
            </html-el:select>
           &nbsp;<strong class="fb">是否代理商：</strong>
            <html-el:select property="is_agent" styleId="is_agent" style="width:40px;">
              <html-el:option value="1">是</html-el:option>
              <html-el:option value="0">否</html-el:option>
            </html-el:select>
           &nbsp;<strong class="fb">网点：</strong>
            <html-el:select property="id" styleId="id" style="width:140px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${konkaR3ShopForSearchList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.customer_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
           &nbsp;<strong class="fb">R3编码：</strong>
            <html-el:text property="r3_code_like" styleClass="webinput" styleId="r3_code_like" style="width:90px;" /></td>
        </tr>
        <tr>
          <td align="left" nowrap="nowrap"><strong style="color:red">*</strong><strong class="fb">起止日期：</strong>
            <html-el:text property="start_date" styleId="start_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:70px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
           &nbsp;<span>至</span>
            <html-el:text property="end_date" styleId="end_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:70px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
            <span class="jxcTip">特别提醒：默认查询当月数据，选择时间段不宜超过一个季度，否则将会影响查询速度。</span>
            <input class="bgSearch" type="button" name="buttonSearch" id="buttonSearch" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="">
      <input type="hidden" name="start_date" value="${af.map.start_date}" />
      <input type="hidden" name="end_date" value="${af.map.end_date}" />
      <input type="hidden" name="area_code_search" value="${af.map.area_code_search}" />
      <input type="hidden" name="is_agent" value="${af.map.is_agent}" />
      <input type="hidden" name="agent_id" value="${af.map.agent_id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="5%">行号</th>
          <th width="12%">网点名称</th>
          <th width="12%">R3编码</th>
          <th width="10%">所在区域名称</th>
          <th width="10%">经办名称</th>
          <th width="10%">客户群类型</th>
          <th width="8%">操作</th>
        </tr>
        <c:forEach items="${konkaR3ShopList}" var="cur" varStatus="status">
          <tr>
            <td align="center">${status.count}</td>
            <td align="center">${cur.customer_name}</td>
            <td align="center">${cur.customer_code}</td>
            <td align="center">${cur.area_name}</td>
            <td align="center">${cur.handle_name}</td>
            <td align="center">${cur.customer_type}</td>
            <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopStatisticsByTime.do', 'view','id=${cur.id}&shop_name=${cur.customer_name}&'+$('#bottomPageForm').serialize())">报表</span>
              <c:if test="${af.map.is_agent eq 1}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopStatisticsByTime.do', 'list','agent_id=${cur.id}&'+$('#listForm').serialize())">代理商下网点</span> </c:if></td>
          </tr>
        </c:forEach>
      </table>
      <c:if test="${empty search_flag}">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
          <tr>
            <td><span class="jxcTip">默认不显示数据，选择条件点搜索显示数据</span></td>
          </tr>
        </table>
      </c:if>
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
		       pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
	           pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
		       pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
		       pager.addHiddenInputs("area_code_search", "${fn:escapeXml(af.map.area_code_search)}");
		       pager.addHiddenInputs("search_flag", "${fn:escapeXml(af.map.search_flag)}");
		       pager.addHiddenInputs("is_agent", "${fn:escapeXml(af.map.is_agent)}");
		       document.write(pager.toString());
		       </script></td>
          </tr>
        </table>
      </form>
    </div>
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
	$("#area_code_search").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:140
	}).multiselectfilter({width:70});       
	
	$("#id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:250
			
		}).multiselectfilter({width:120});                                         
		
	});                              
	var f=document.forms[0];

	$(".bgSearch").click(function(){
		$("#start_date").attr("dataType", "Require").attr("msg", "请选择起始日期");
		$("#end_date").attr("dataType", "Require").attr("msg", "请选择结束日期");
	   	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("起始日期不能大于结束日期！");
				return false;
			}
		
			if(!Validator.Validate(f, 1)){
				return false;
			}else{
				f.submit();
			}
   });  
            
 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>