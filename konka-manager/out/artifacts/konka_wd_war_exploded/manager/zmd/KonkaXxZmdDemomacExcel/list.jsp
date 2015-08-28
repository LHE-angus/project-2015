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
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdDemomacExcel">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">产品类别：</strong>
            <html-el:select property="cls_id" onchange="this.form.submit();">
              <c:forEach var="cur" items="${bpcList}">
                <html-el:option value="${cur.cls_id}" styleId="${cur.cls_id}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;<strong class="fb">型号：</strong>
            <html-el:text property="md_name" />&nbsp;<strong class="fb">专卖店：</strong>
            <html-el:select property="zmd_sn" style="width:150px;" onchange="this.form.submit();">
            <html-el:option value="">==请选择==</html-el:option>
            <c:forEach var="cur" items="${konkaXxZmdList}">
            <html-el:option value="${cur.zmd_sn}">${cur.zmd_sn}</html-el:option></c:forEach>
            </html-el:select></td>
        </tr>
        <tr><td width="15"></td><td><strong class="fb">状态：</strong>
            <html-el:select property="state" onchange="this.form.submit();">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="60100">未上样</html-el:option>
              <html-el:option value="60200">上样</html-el:option>
              <html-el:option value="60300">下样</html-el:option>
            </html-el:select>&nbsp;<strong class="fb">导出条数：</strong>
            <html-el:text property="counts" styleId="counts" style="width:60px;" />&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td></tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input name="button" type="button" class="but_excel" id="toExcel" value="导出" onclick="toExcel('divExcel', 'KonkaXxZmdDemomacExcel.do?method=toExcel');" />
          <span style="color: #ff0000">&nbsp;&nbsp;&nbsp;&nbsp;（注：默认导出全部数据,请设置导出数据条数！）</span> </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td colspan="10" align="center" style="font-weight:bolder">康佳专卖店样机动态管理表（系统自动生成）</td>
      </tr>
      <tr class="tabtt1">
        <td align="center" width="40" nowrap="nowrap">序号</td>
        <td  align="center" nowrap="nowrap" align="center">
        分公司
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        专卖店
        </td>
        <td align="center" nowrap="nowrap" align="center">
        样机品类
        </td>
        <td align="center" nowrap="nowrap" align="center">
        型号
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        数量
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        价格
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        上样时间
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        状态
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        下样时间
        </td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.zmd_sn }</td>
            <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
            <td align="center" nowrap="nowrap">${cur.md_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.counts }</td>
            <td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><fmt:formatNumber value="${cur.price_ref}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.up_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><c:if test="${cur.map.state eq '上样'}"><span style="color: green;">${cur.map.state}</span></c:if>
              <c:if test="${cur.map.state eq '未上样'}"><span style="color: #f00">${cur.map.state}</span></c:if>
              <c:if test="${cur.map.state eq '下样'}"><span style="color: gray;">${cur.map.state}</span></c:if></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.down_date}" pattern="yyyy-MM-dd" /></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:if test="${not empty entityList}">
          <tr>
            <td align="center" colspan="5" style="color: #ff0000">合计：</td>
            <td align="right">${sumCounts}</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </div>
  <div class="pageContent" id="divExcel" title="专卖店样机动态管理表" style="display: none;">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td colspan="11" align="center" style="font-weight:bolder">康佳专卖店样机动态管理表（系统自动生成）</td>
      </tr>
      <tr class="tabtt1">
        <td align="center" width="40" nowrap="nowrap">序号</td>
        <td  align="center" nowrap="nowrap" align="center">
        分公司
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        专卖店
        </td>
        <td align="center" nowrap="nowrap" align="center">
        样机品类
        </td>
        <td align="center" nowrap="nowrap" align="center">
        型号
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        数量
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        价格（元）
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        上样时间
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        状态
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        下样时间
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        备注
        </td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.zmd_sn }</td>
            <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
            <td align="center" nowrap="nowrap">${cur.md_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.counts }</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.price_ref}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.up_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">${cur.map.state }</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.down_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center">${cur.map.memo}</td>
          </tr>
        </c:forEach>
        <c:if test="${not empty entityList}">
          <tr>
            <td align="center" colspan="5" style="color: #ff0000">合计：</td>
            <td align="right">${sumCounts}</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#counts").attr("focus",setOnlyNum);
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
