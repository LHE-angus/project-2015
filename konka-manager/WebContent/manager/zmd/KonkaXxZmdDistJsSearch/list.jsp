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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdDistJsSearch">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;">
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td width="10%">&nbsp;<strong class="fb">分公司名称：</strong></td>
            <td width="40%"><c:if test="${empty dept_name}">
                <html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${konkaDepts}">
                    <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty dept_name}">
                <label style="color:blue;">${dept_name}</label>
              </c:if></td>
            <td width="10%"><strong class="fb">专卖店编号：</strong></td>
            <td width="40%"><c:if test="${empty zmd_sn}">
                <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${zmdList}">
                    <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty zmd_sn}">
                <label style="color:blue;">${zmd_sn}</label>
              </c:if></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">结算人姓名：</strong></td>
            <td><html-el:text property="js_dist_user_realname_like" styleId="js_dist_user_realname_like" size="15" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">结算日期：</strong></td>
            <td><html-el:text property="js_dist_date_ge" styleId="js_dist_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="js_dist_date_le" styleId="js_dist_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">结算状态：</strong></td>
            <td><html-el:select property="js_dist_state" styleId="js_dist_state" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="0">未结算</html-el:option>
                <html-el:option value="1">已结算</html-el:option>
              </html-el:select></td>
            <td><strong class="fb">订单流水号：</strong></td>
            <td><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="15" maxlength="20"></html-el:text>
              &nbsp;&nbsp;
              <input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center">订单流水号</td>
        <td width="10%" align="center">分公司名称</td>
        <td align="center">专卖店编号</td>
        <td width="12%" align="center">结算时间</td>
        <td width="12%" align="center">结算人姓名</td>
        <td width="10%" align="center">结算状态</td>
        <td width="10%" align="center">物流费</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td nowrap="nowrap" align="center" class="fblue"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font style="text-decoration:underline;">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></a></td>
          <td nowrap="nowrap" align="left" class="dept_name"><font class="blue12px">${cur.map.dept_name}</font></td>
          <td nowrap="nowrap" align="left"><font class="blue12px">${cur.zmd_sn}</font></td>
          <td nowrap="nowrap" align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.js_dist_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td nowrap="nowrap" align="left"><font class="blue12px">${cur.js_dist_user_realname}</font></td>
          <td nowrap="nowrap" align="center"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.js_dist_state eq 0}"><font color="red">未结算</font></c:when>
              <c:when test="${cur.js_dist_state eq 1}"><font color="green">已结算</font></c:when>
            </c:choose>
            </font></td>
          <td nowrap="nowrap" align="right"><font class="kz-price-12">
            <fmt:formatNumber value="${cur.fee_of_post}" type="currency" /><c:if test="${empty cur.fee_of_post}">0.00</c:if>
            </font></td>
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
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdDistJsSearch.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left"><input class="but_excel" type="button"  value="导出" id="export_all" /></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
			pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");	
			pager.addHiddenInputs("js_dist_date_ge", "${af.map.js_dist_date_ge}");
            pager.addHiddenInputs("js_dist_date_le", "${af.map.js_dist_date_le}");
			pager.addHiddenInputs("js_dist_user_realname_like", "${fn:escapeXml(af.map.js_dist_user_realname_like)}");
			pager.addHiddenInputs("js_dist_state", "${af.map.js_dist_state}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<c:if test="${not empty entityList_all}">
  <div style="100%;overflow-x:auto;padding-bottom:5px;display:none;" id="divExcel_all" title="康佳物流结算表${af.map.now_date}">
    <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" colspan="7"><font style="font-weight:bold;font-size:20px;">康佳物流结算表</font></td>
      </tr>
    </table>
    <table id="table2" width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center">分公司名称</td>
        <td width="15%" align="center">专卖店编号</td>
        <td width="15%" align="center">订单流水号</td>
        <td width="20%" align="center">物流结算时间</td>
        <td width="10%" align="center">物流结算人姓名</td>
        <td width="10%" align="center">物流结算状态</td>
        <td width="10%" align="center">物流费（元）</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="left" class="dept_name"><font class="blue12px">${cur.map.dept_name}</font></td>
          <td align="left"><font class="blue12px">${cur.zmd_sn}</font></td>
          <td align="left" style="mso-number-format:'\@';"><font class="blue12px">${cur.sell_bill_id}</font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.js_dist_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="left"><font class="blue12px">${cur.js_dist_user_realname}</font></td>
          <td align="center"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.js_dist_state eq 0}"><font color="red">未结算</font></c:when>
              <c:when test="${cur.js_dist_state eq 1}"><font color="green">已结算</font></c:when>
            </c:choose>
            </font></td>
          <td align="right"><font class="blue12px">
            <fmt:formatNumber value="${cur.fee_of_post}" pattern="#,#00.00#" />
            </font></td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${total_js_all}" pattern="#,#00.00#" /></td>
      </tr>
    </table>
  </div>
</c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#js_dist_date_ge").datepicker();
	$("#js_dist_date_le").datepicker();

	trMerge("dept_name",$("#table1"));
	trMerge("dept_name",$("#table2"));

	$("#sell_bill_id").focus(setOnlyNum);

	// 返回页面根据需求导出全部数据
	var allToExcel = "${af.map.allToExcel}";
	if("ebiz" == allToExcel){
		toExcel('divExcel_all', '${ctx}/manager/zmd/KonkaXxZmdDztzSearch.do?method=toExcel');
	}
	$("#export_all").click(function(){
		$("#bottomPageForm").append("<input type='hidden' name='allToExcel' value='ebiz' />");
		$("#bottomPageForm").submit();
	});

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
		this.t_value = '';
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
