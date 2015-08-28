<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>网点经营情况</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <c:if test="${empty af.map.is_entp_shop}">
    <html-el:form action="/KonkaJxcShopSellStatistics">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="4%" nowrap="nowrap"><strong class="fb">是否代理商：</strong></td>
          <td width="5%"><html-el:select property="is_agent" styleId="is_agent" style="width:80px;">
              <html-el:option value="1">是</html-el:option>
              <html-el:option value="0">否</html-el:option>
            </html-el:select></td>
          <td width="6%" nowrap="nowrap"><strong class="fb">网点名称：</strong></td>
          <td width="20%"><html-el:hidden property="r3_shop_id_temp" styleId="r3_shop_id_temp"/>
            <html-el:select property="r3_shop_id" styleId="r3_shop_id" style="width:240px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${konkaR3ShopForSearchList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.customer_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td width="6%" nowrap="nowrap"><strong class="fb">R3编码：</strong></td>
          <td width="8%"><html-el:text property="r3_code_like" styleClass="webinput" styleId="r3_code_like" /></td>
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </c:if>
  <c:if test="${not empty af.map.is_entp_shop}">
    <html-el:form action="/KonkaJxcShopSellStatistics">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="is_entp_shop" value="1"/>
      <html-el:hidden property="agent_id" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="6%" nowrap="nowrap"><strong class="fb">网点名称:</strong></td>
          <td width="20%"><html-el:hidden property="mmt_shop_id_temp" styleId="mmt_shop_id_temp"/>
            <html-el:select property="mmt_shop_id" styleId="mmt_shop_id" style="width:240px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${konkaBranchCategoryList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.mmt_shop_id}">${fn:escapeXml(cur.map.jxs_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;
            <html-el:submit value="搜 索" styleClass="bgSearch"/>
          </td>
        </tr>
      </table>
    </html-el:form>
    <div class="rtabcont1">
      <html-el:button value="返 回" styleClass="bgButtonBack" property="fh" onclick="history.back();"/>
    </div>
  </c:if>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
  <c:if test="${not empty konkaR3ShopList}">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%">行号</th>
        <th width="12%">网点名称</th>
        <c:if test="${af.map.is_agent eq 0}">
          <th width="12%">R3编码</th>
        </c:if>
        <th width="10%">所在区域名称</th>
        <th width="10%">经办名称</th>
        <th width="10%">客户群类型</th>
        <th width="8%">统计报表</th>
      </tr>
      <c:forEach items="${konkaR3ShopList}" var="cur" varStatus="status">
        <tr>
          <td align="center">${status.count}</td>
          <td align="center">${cur.customer_name}</td>
          <c:if test="${af.map.is_agent eq 0}">
            <td align="center">${cur.customer_code}</td>
          </c:if>
          <td align="center">${cur.area_name}</td>
          <td align="center">${cur.handle_name}</td>
          <td align="center"><c:if test="${empty cur.customer_type}">无</c:if>
            <c:if test="${not empty cur.customer_type}">${cur.customer_type}</c:if></td>
          <td align="center"><c:if test="${af.map.is_agent eq 0}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopSellStatistics.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())"> 报表 </span> </c:if>
            <c:if test="${af.map.is_agent eq 1}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopSellStatistics.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())"> 报表 </span> | <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopSellStatistics.do', 'list','agent_id=${cur.id}&is_entp_shop=1&'+$('#bottomPageForm').serialize())"> 代理商下网点 </span> </c:if></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <c:if test="${not empty konkaEntpShopList}">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%">行号</th>
        <th width="12%">网点名称</th>
        <th width="10%">联系人</th>
        <th width="10%">联系电话</th>
        <th width="8%">统计报表</th>
      </tr>
      <c:forEach items="${konkaEntpShopList}" var="cur" varStatus="status">
        <tr>
          <td align="center">${status.count}</td>
          <td align="center">${cur.shop_name}</td>
          <td align="center">${cur.link_user}</td>
          <td align="center">${cur.link_phone}</td>
          <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcShopSellStatistics.do', 'view','id=${cur.shop_id}&is_not_direct=1&'+$('#bottomPageForm').serialize())">报表 </span></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcShopSellStatistics.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("agent_id", "${af.map.agent_id}");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("is_entp_shop", "${af.map.is_entp_shop}");
		       pager.addHiddenInputs("is_agent", "${af.map.is_agent}");
		       pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	<c:if test="${empty af.map.is_entp_shop}">
	$("#r3_shop_id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:240
			
		}).multiselectfilter({width:150});                                         
		
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
			var id=$("#r3_shop_id").val();
			if(id != ''){
				$("#r3_shop_id_temp").val(id);
			}
			$("#r3_shop_id_temp").val("");//清空隐藏域临时变量
				f.submit();
	    });  
	</c:if>
	<c:if test="${not empty af.map.is_entp_shop}">
	$("#mmt_shop_id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:240
			
		}).multiselectfilter({width:150});                                         
		
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
			var id=$("#mmt_shop_id").val();
			if(id != ''){
				$("#mmt_shop_id_temp").val(id);
			}
			$("#mmt_shop_id_temp").val("");//清空隐藏域临时变量
				f.submit();
	    });  
	</c:if>
});                       
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>