<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxPdAudit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">产品型号：</strong>
          	<html-el:text property="md_name_like" styleId="md_name_like" maxlength="10" />
          	&nbsp;<strong class="fb">审核状态：</strong>
            <html-el:select property="audit_state" styleId="audit_state" onchange="this.form.submit();" style="width:160px;">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="0">==未审核==</html-el:option>
              <html-el:option value="1">==审核通过==</html-el:option>
              <html-el:option value="2">==审核不通过==</html-el:option>
            </html-el:select>
            &nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="t_1"> 
     <tr style="display:none;" id="t_2">
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表</td>
     </tr>
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">分公司</td>
        <td nowrap="nowrap"  align="center" width="8%">产品型号</td>
        <td nowrap="nowrap" align="center">工厂仓位</td>
        <td nowrap="nowrap"  align="center" width="8%">参考价格</td>
        <td nowrap="nowrap"  align="center" width="8%">价格下限</td>
        <td nowrap="nowrap"  align="center" width="8%">上架时间</td>
        <td nowrap="nowrap"  align="center" width="8%">下架时间</td>
        <td nowrap="nowrap"  align="center" width="6%">状态</td>
        <td nowrap="nowrap" align="center" width="6%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.md_name)}</td>
            <td align="left">${fn:escapeXml(cur.map.fac_store_name)}</td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_ref}" /></span></td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_min}" /></span></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.up_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.audit_state == 0}">未审核</c:if>
            <c:if test="${cur.audit_state == 1}">审核通过</c:if>
            <c:if test="${cur.audit_state == 2}">审核不通过</c:if></td>
            <td align="center" nowrap="nowrap">
            <c:if test="${empty is_cw}"><span style="color: gray;">审核</span></c:if>
            <c:if test="${not empty is_cw}">
            	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPdAudit.do', 'edit','dept_pd_id=${cur.dept_pd_id}&dept_id=${cur.dept_id}&'+$('#bottomPageForm').serialize())">审核</span>
            </c:if>	
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
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
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPdAudit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
				pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#price_ref_ge").attr("focus",setOnlyNum);
	$("#price_ref_le").attr("focus",setOnlyNum);
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
