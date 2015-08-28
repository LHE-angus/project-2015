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
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/paragon/KonkaParagonEquipmentJ" styleClass="form1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="etype"  />
      <html-el:hidden property="mod_id"  />
     <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr> 
          <td width="15"></td>
	      <td nowrap="nowrap">
            <strong class="fb">门店名称：</strong>
            	<html-el:text property="shop_name_like" size="20" style="width:90px;" maxlength="10" styleId="shop_name_like" styleClass="webinput" />
            </td>
            <td>
            <strong class="fb">门店代码：</strong>
            	<html-el:text property="shop_code_like" size="20" style="width:90px;" maxlength="10" styleId="shop_code_like" styleClass="webinput" />
            </td>
           <td><strong class="fb">数据期号：</strong>
           <html-el:text property="fixdate" styleId="fixdate" size="8" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            </td>
        </tr>
         <tr>
	        <td width="15"></td>
	        <td nowrap="nowrap">
	        	<strong class="fb">区域　：</strong>
        		<html-el:select property="area_id" styleId="area_id">
	                <html-el:option value="">请选择...</html-el:option>
	                <html-el:option value="10">华东</html-el:option>
	                <html-el:option value="20">山东</html-el:option>
	                <html-el:option value="30">东北</html-el:option>
	                <html-el:option value="40">华北</html-el:option>
	                <html-el:option value="50">华南</html-el:option>
	                <html-el:option value="60">西南</html-el:option>
	                <html-el:option value="70">华中</html-el:option>
	                <html-el:option value="80">西北</html-el:option>
              </html-el:select>
			</td>
	        <td><strong class="fb">分公司　：</strong>
	        	<html-el:select property="part_company_id" styleId="part_company_id">
		        	<html-el:option value="">请选择...</html-el:option>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select></td>
	        <td>
	        	<strong class="fb">经办　　：</strong>
	        	<html-el:text property="channel_name_like" size="15" maxlength="20" styleId="channel_name_like"  />
          	</td>
	        <td><html-el:submit styleClass="but1" value="搜索" styleId="btn_submit" /></td>
           </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaParagonEquipmentJ.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <input type="hidden" name="etype" id="etype" value="${af.map.etype}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="60" align="center" >门店代码</td>
          <td align="center" >门店名称</td>
          <td nowrap="nowrap" align="center">
          <c:choose>
	          <c:when test="${af.map.etype eq 1}">设备名称</c:when>
	          <c:when test="${af.map.etype eq 2}">样机型号</c:when>
          </c:choose>
          </td>
          <td nowrap="nowrap" align="center" >
          <c:choose>
	          <c:when test="${af.map.etype eq 1}">设备</c:when>
	          <c:when test="${af.map.etype eq 2}">样机</c:when>
          </c:choose>数量</td>
          <td width="80" align="center" >数据期号</td>
          <td width="80" align="center" >添加时间</td>
          <td width="80" align="center" >启用时间</td>
          <td width="80" align="center" >废弃时间</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${cur.SHOW_SHOP_CODE}</td>
            <td align="left" nowrap="nowrap">
			<a onclick="selectType('${cur.CUSTOM_NAME}');" title="点击查询" style="cursor:pointer;">
			${cur.CUSTOM_NAME}</a></td>
            <td align=left nowrap="nowrap">${fn:escapeXml(cur.EQUIPMENT_NAME)}</td>
            <td align="center" nowrap="nowrap"><c:if test="${cur.EQUIPMENT_NUM eq 0}">*</c:if><c:if test="${cur.EQUIPMENT_NUM ne 0}">${cur.EQUIPMENT_NUM}</c:if></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.FIXDATE)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.ADDTIME}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.STARTIME}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.ENDTIME}" pattern="yyyy-MM-dd" /></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
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
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonEquipmentJ.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("etype", "${af.map.etype}");
			pager.addHiddenInputs("shop_name_like", "${fn:escapeXml(af.map.shop_name_like)}");	
			pager.addHiddenInputs("shop_code_like", "${fn:escapeXml(af.map.shop_code_like)}");	
			pager.addHiddenInputs("fixdate", "${af.map.fixdate}");
			pager.addHiddenInputs("channel_name_like", "${af.map.channel_name_like}");
			pager.addHiddenInputs("part_company_id", "${af.map.part_company_id}");
			pager.addHiddenInputs("area_id", "${af.map.area_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("结束日期不得大于开始日期,请重新选择!")
				return false;
			}
		}
		this.form.submit();
	});
	
});
function selectType(name){
	$("#shop_name_like").val(name);
	$(".form1").submit();
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
