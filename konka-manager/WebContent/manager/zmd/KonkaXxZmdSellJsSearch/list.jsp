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
    <html-el:form action="/zmd/KonkaXxZmdSellJsSearch">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;" >
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="rtable5">
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
            <td>&nbsp;<strong class="fb">开单人姓名：</strong></td>
            <td><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="15" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">开单日期：</strong></td>
            <td><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">结算人姓名：</strong></td>
            <td><html-el:text property="js_bill_user_realname_like" styleId="js_bill_user_realname_like" size="15" maxlength="20"></html-el:text></td>
            <td><strong class="fb">结算日期：</strong></td>
            <td><html-el:text property="js_bill_date_ge" styleId="js_bill_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="js_bill_date_le" styleId="js_bill_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">结算状态：</strong></td>
            <td><html-el:select property="js_bill_state" styleId="js_bill_state" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="0">未结算</html-el:option>
                <html-el:option value="2">已结算</html-el:option>
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
        <td width="9%" align="center">订单流水号</td>
        <td width="10%" align="center">分公司名称</td>
        <td align="center">专卖店编号</td>
        <td width="14%" align="center">开单时间</td>
        <td width="8%" align="center">开单人姓名</td>
        <td width="14%" align="center">结算时间</td>
        <td width="8%" align="center">结算人姓名</td>
        <td width="6%" align="center">结算状态</td>
        <td width="10%" align="center">结算金额</td>
        <td width="8%" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" class="fblue" style="cursor:pointer;"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font style="text-decoration:underline;">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></a></td>
          <td align="left" class="dept_name"><font class="blue12px">${cur.map.dept_name}</font></td>
          <td align="left" title="${cur.zmd_sn}"><font class="blue12px">${fnx:abbreviate(cur.zmd_sn, 2 * 8, '')}</font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="left" title="${cur.add_user_realname}"><font class="blue12px">${fnx:abbreviate(cur.add_user_realname, 2 * 6, '')}</font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.js_bill_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            <c:if test="${empty cur.js_bill_date}"><span style="color:gray;">未填写</span></c:if>
            </font></td>
          <td align="left" title="${cur.js_bill_user_realname}"><font class="blue12px">${fnx:abbreviate(cur.js_bill_user_realname, 2 * 6, '')}</font>
            <c:if test="${empty cur.js_bill_user_realname}"><span style="color:gray;">未填写</span></c:if></td>
          <td align="center" nowrap="nowrap"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.js_bill_state ne 2}"><font color="red">未结算</font></c:when>
              <c:when test="${cur.js_bill_state eq 2}"><font color="green">已结算</font></c:when>
            </c:choose>
            </font></td>
          <td align="right"><c:forEach items="${konkaXxRewardModifyList}" var="cur1">
              <c:if test="${cur.sell_bill_id eq cur1.map.sell_bill_id and cur1.map.modify_count ne 0}"> <span title="佣金已被分公司调整" style="font-size:6px;color:#f00;">调</span> </c:if>
            </c:forEach>
            <span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.js_bill_money}" /></span></td>
          <td align="center"><img src="${ctx}/images/more.png" alt="" />
            <c:if test="${cur.js_bill_state eq 2}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdSellJsSearch.do', 'showDetail','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">明细</span></c:if>
            <c:if test="${cur.js_bill_state ne 2}"><span style="color:#ccc;">明细</span></c:if></td>
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
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdSellJsSearch.do">
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
            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
			pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
			pager.addHiddenInputs("js_bill_date_ge", "${af.map.js_bill_date_ge}");
            pager.addHiddenInputs("js_bill_date_le", "${af.map.js_bill_date_le}");
			pager.addHiddenInputs("js_bill_user_realname_like", "${fn:escapeXml(af.map.js_bill_user_realname_like)}");
			pager.addHiddenInputs("js_bill_state", "${af.map.js_bill_state}");
			pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<c:if test="${not empty entityList_all}">
  <div style="100%;overflow-x:auto;padding-bottom:5px;display:none;" id="divExcel_all" title="康佳专卖店销售单结算表${af.map.now_date}">
    <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" colspan="9"><font style="font-weight:bold;font-size:20px;">康佳专卖店销售单结算表</font></td>
      </tr>
    </table>
    <table id="table2" width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center">订单流水号</td>
        <td width="10%" align="center">分公司名称</td>
        <td width="10%" align="center">专卖店编号</td>
        <td width="12%" align="center">开单时间</td>
        <td width="8%" align="center">开单人姓名</td>
        <td width="12%" align="center">结算时间</td>
        <td width="12%" align="center">结算人姓名</td>
        <td width="10%" align="center">结算状态</td>
        <td width="13%" align="center">结算金额（元）</td>
      </tr>
      <c:forEach var="cur" items="${entityList_all}" varStatus="vs">
        <tr>
          <td align="left" nowrap="nowrap" style="mso-number-format:'\@';"><font class="blue12px">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></td>
          <td align="left" class="dept_name"><font class="blue12px">
            <c:out value="${cur.map.dept_name}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.zmd_sn}" />
            </font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.add_user_realname}" />
            </font></td>
          <td align="center"><font class="blue12px">
            <fmt:formatDate value="${cur.js_bill_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.js_bill_user_realname}" />
            </font></td>
          <td align="center"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.js_bill_state eq 0}"><font color="red">未结算</font></c:when>
              <c:when test="${cur.js_bill_state eq 1}"><font color="green">已结算</font></c:when>
            </c:choose>
            </font></td>
          <td align="right"><font class="blue12px">
            <fmt:formatNumber value="${cur.js_bill_money}" pattern="#,#00.00#" />
            </font></td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap" class="dept_name_all"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
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

	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	$("#js_bill_date_ge").datepicker();
	$("#js_bill_date_le").datepicker();

	$("#sell_bill_id").focus(setOnlyNum);
	
	trMerge("dept_name",$("#table1"));
	trMerge("dept_name",$("#table2"));
	
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
