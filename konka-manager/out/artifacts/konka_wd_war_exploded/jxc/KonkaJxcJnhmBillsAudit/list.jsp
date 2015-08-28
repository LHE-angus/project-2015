<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
<title>节能惠民订单审核</title>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcJnhmBillsAudit">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="10%" nowrap="nowrap"><span style="color:#f00;">*</span>网点名称：
          <html-el:select property="r3_shop_id" styleId="r3_shop_id" style="width:240px;">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach items="${konkaR3ShopForSearchList}" var="cur" varStatus="vs">
              <html-el:option value="${cur.id}">${fn:escapeXml(cur.customer_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          &nbsp;审核状态
          <html-el:select property="states" styleId="states" style="width:120px;">
            <html-el:option value="0">待审核</html-el:option>
            <html-el:option value="1">审核通过</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;
          <html-el:button value="搜 索" styleClass="bgSearch" property=""/></td>
      </tr>
      <tr>
        <td><span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
  <c:if test="${not empty customer_name}">
    <div align="center" style="height:30px;font-size:20px;font-weight:bolder;"> ${customer_name}节能惠民订单</div>
  </c:if>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcJnhmBillsAudit.do">
    <input type="hidden" name="method" value="list"/>
    <input type="hidden" name="r3_shop_id" value="${r3_shop_id}"/>
    <input type="hidden" name="mod_id" value="${af.map.mod_id}"/>
    <c:if test="${not empty entityList}">
      <div>
        <input style="width:90px;height:20px;background:url(${ctx}/images/manager/but_u.gif) repeat-x;font:normal 12px/20px '宋体';text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;" name="Submit2" value="一键审核" onclick="doNeedMethod('是否审核？', 'KonkaJxcJnhmBillsAudit.do','audit_all', 'shop_id=${shop_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}')" />
        <input style="width:135px;height:22px;background:url(${ctx}/images/manager/but_u.gif) repeat-x;font:normal 12px/20px '宋体';text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;" type="button"  value="批量审核" id="batchAudit" />
      </div>
    </c:if>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tbody>
        <tr>
          <th width="5%" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="10%" align="center" nowrap="nowrap">销售单号</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th width="6%" nowrap="nowrap" align="center">单价（元）</th>
          <th width="6%" nowrap="nowrap" align="center">数量（台）</th>
          <th width="6%" nowrap="nowrap" align="center">补贴金额（元）</th>
          <th width="8%" nowrap="nowrap" align="center">购买人</th>
          <th width="8%" nowrap="nowrap" align="center">联系电话</th>
          <th width="8%" nowrap="nowrap" align="center">销售时间</th>
          <th width="6%" nowrap="nowrap" align="center">审核状态</th>
          <th width="6%" nowrap="nowrap" align="center">审核</th>
        </tr>
        <c:if test="${not empty entityList}">
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><c:if test="${cur.map.states eq 1}">
                  <input name="pks" type="checkbox" class="pks" id="pks" value="${cur.id}" disabled="disabled" />
                </c:if>
                <c:if test="${cur.map.states ne 1}">
                  <input name="pks" type="checkbox" class="pks" id="pks" value="${cur.id}" />
                </c:if></td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.sn)}</td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.pd_names)}</td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber value="${cur.map.sell_price}" type="currency" />
                </span></td>
              <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.sell_count}" pattern="#0" /></td>
              <c:if test="${not empty cur.map.sell_allowance_money}">
                <td align="right" nowrap="nowrap"><span class="kz-price-12">
                  <fmt:formatNumber value="${cur.map.sell_allowance_money}" type="currency" />
                  </span></td>
              </c:if>
              <c:if test="${empty cur.map.sell_allowance_money}">
                <td align="center" nowrap="nowrap"><span style="color:gray;">-</span></td>
              </c:if>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.customer_name)}</td>
              <td align="center" nowrap="nowrap"><c:if test="${not empty cur.map.customer_tel}">${cur.map.customer_tel}</c:if>
                <c:if test="${empty cur.map.customer_tel}"><span style="color:gray;">未填写</span></c:if></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" /></td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.map.states eq 1}">审核通过</c:when>
                  <c:otherwise>待审核</c:otherwise>
                </c:choose></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.map.states ne 1}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcJnhmBillsAudit.do', 'audit','id=${cur.id}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                <c:if test="${cur.map.states eq 1}"><span style="color:gray">审核</span> </c:if></td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty entityList}">
          <tr>
            <td height="30" align="center" nowrap="nowrap" colspan="11"><font color="red">无数据</font></td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcJnhmBillsAudit.do">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40"><div style="text-align: right; padding-right: 5px;">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("r3_shop_id", "${af.map.r3_shop_id}");
				document.write(pager.toString());
			  </script>
          </div></td>
      </tr>
    </table>
  </form>
</div>
<div id="plAuditDiv" style="display:none;">
  <html-el:form action="/KonkaJxcJnhmBillsAudit">
    <html-el:hidden property="method" value="plAudit" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="shop_id" value="${shop_id}" />
    <html-el:hidden property="is_audit" value="0" />
    <html-el:hidden property="queryString" />
    <table id="tab_3" width="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="30%" align="center" nowrap="nowrap">销售单号</td>
        <td width="25%" align="center" nowrap="nowrap">产品型号</td>
        <td width="20%" align="center" nowrap="nowrap">补贴金额（元）</td>
        <td width="25%" align="center" nowrap="nowrap">销售时间</td>
      </tr>
      <tbody id="creditLineTbody">
      </tbody>
    </table>
    <div class="rtabcont1" align="left">
      <table width="95%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
              <tr>
                <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">审核状态：</strong></td>
                <td align="left"><label for="audit_state1" style="cursor:pointer">
                    <input type="radio" name="audit_state" value="1" id="audit_state1" />
                    审核通过</label>
                  <label for="audit_state2" style="cursor:pointer">
                    <input type="radio" name="audit_state" value="0" id="audit_state2" />
                    待审核</label></td>
              </tr>
              <tr>
                <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">审核备注：</strong></td>
                <td align="left"><textarea name="audit_memo" cols="70" rows="5" id="audit_memo"></textarea>
                  <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
              </tr>
              <tr>
              <td height="10px">
              </td>
              </tr>
              <tr >
                <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">审核人：</strong></td>
                <td><input type="text" name="audit_user_name" id="audit_user_name" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </div>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script>
<script type="text/javascript">//<![CDATA[
var f=document.forms[0];
$(document).ready(function(){
	$("#r3_shop_id").attr("dataType", "Require").attr("msg", "请选择网点");
	$(".bgSearch").click(function(){
		if(Validator.Validate(f, 1)){
			f.submit();
		}
	});  

	//DIV自适应宽度Begin
	var ww = $(".oarcont").width();
	//alert("ww = " + ww);
	var w3 = $("#tab_3").width();
	var w4 = $("#tab_4").width();
	var w = 0;
	for ( var i = 1; i <= 3; i++) {
		var w_t = $("#tab_" + i).width();
		var className = $("#tab_" + i).parent().attr("class");
		if (className == "" || className == undefined) {
			className = $("#tab_" + i).parent().parent().attr("class");
		}
		//alert(className);
		if ("oartop" == className) {
			w_t = w_t + 17;
		} else if ("rtabcont1" == className) {

			w_t = w_t + 7 + 7;
		}
		
		//alert("tab_" + i +" = " + w_t);
		if (w_t > w) {
			w = w_t;
		}
		//alert("w = " + w);
	}
	$("#tab_3").css("width", w3);
	$("#tab_4").css("width", w4);
	$(".oarcont").css("width", w);
	if (ww < w) {
		$("body").css("overflow-x", "scroll");
	}
	//DIV自适应宽度End
	
	$("#batchAudit").click(function(){
		form = this.form;
		var checkedCount = 0;
		if (!form.pks) {
			return;
		}
		if(!form.pks.length) {
			if (form.pks.checked == true) {
				checkedCount = 1;
			}
		}
		for (var i = 0; i < form.pks.length; i++) {
			if (form.pks[i].checked == true) {
				//alert(form.pks[i].value);
				checkedCount++;
			}
		}
		if (checkedCount == 0) {
			alert("请选择要批量审核的节能惠民订单！");
			return false;
		} else {
			$("#plAuditDiv").dialog({
			      title: '批量审核', 
			      width: 600,
			      height: 350,
			      draggable: true,
			      position:'center',
			      resizable: false,
			      modal : true,
			      buttons: {"确认": function() {
										var ff = document.forms[3];
										if(Validator.Validate(ff, 1)){
											if($('input:radio[name="audit_state"]:checked').val() == null){
												alert("请选择审核状态！");
												return false;
											}
											if($('input:radio[name="audit_state"]:checked').val() == 0 && $('#audit_memo').val().length == 0){
												alert("请填写审核结果批注！");
												return false;
												}
											if(confirm("确定要批量审核？")){
												ff.submit();
											} else {
												return false;
											}
										}
									},
							"关闭": function() {$(this).dialog("close");}
						   }
			}).dialog("open");

			$("#creditLineTbody").empty();
			var num = $(".pks").length;
			if (num == 1 || num == "1") {
				$("#salesOrderTbody").append(
					'<tr>' +
						'<td>' + $("#pks").parent().next().text() + '</td>' + 
						'<td>' + $("#pks").parent().next().next().text() + '</td>' + 
						'<td>' + $("#pks").parent().next().next().next().text() + '</td>' +
						'<td>' + $("#pks").parent().next().next().next().next().text() + '</td>' +
						'<input type="hidden" name="pkss" value="' + form.pks.value + '" />' +
					'</tr>'
				);
			} else {
				for ( var i = 0; i < num; i++) {
					if (form.pks[i].checked == true) {
						<c:forEach items="${entityList}" var="cur" varStatus="vs">
							var id = "${cur.id}";
							var sn = "${cur.sn}";
							var pd_name = "${cur.map.pd_names}";
							var sell_allowance_money = "${cur.map.sell_allowance_money}";
							var sell_date = '<fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" />';
							if (form.pks[i].value == parseInt(id, 10)) {
								$("#creditLineTbody").append(
									'<tr>' +
										'<td>' + sn + '</td>' + 
										'<td align="left">' + pd_name + '</td>' + 
										'<td align="right">' + sell_allowance_money + '</td>' + 
										'<td>' + sell_date + '</td>' +
										'<input type="hidden" name="pkss" value="' + parseInt(id, 10) + '" />' +
									'</tr>'
								);
							}
						</c:forEach>
					}
				}
			}
		}
	});
});  
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>