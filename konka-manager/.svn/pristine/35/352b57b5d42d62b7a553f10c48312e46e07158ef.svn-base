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
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table id="tab_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td id="ceshi">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/zmd/KonkaXxZmdInquiryCreditLine">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id"  value="${userInfo.dept_id}"/>
      <table id="tab_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;">
          	<table width="80%" border="0" cellpadding="0" cellspacing="0">
          		 <tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
		            		<html-el:option value="">=请选择=</html-el:option>
		            		<c:forEach items="${zmdList}" var="cur">
		            			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
		            		</c:forEach>
		            	</html-el:select>
          			</td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">审核状态：</strong></td>
          			<td style="padding:2px;">
          				<html-el:select property="audit_state" styleId="audit_state">
          					<html-el:option value="">=请选择=</html-el:option>
          					<html-el:option value="0">未审核</html-el:option>
          					<html-el:option value="1">审核通过</html-el:option>
          					<html-el:option value="-1">审核不通过</html-el:option>
          				</html-el:select>
          				&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
          		</tr>
          		<!--<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单日期：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
          				至
          				<html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
          			</td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">销售单状态：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:select property="sell_state" styleId="sell_state">
			           		<html-el:option value="">==请选择==</html-el:option>
			           		<html-el:option value="0">未付款</html-el:option>
			           		<html-el:option value="10">已付款未审核</html-el:option>
			           		<html-el:option value="20">已审核通过</html-el:option>
			           		<html-el:option value="21">已审核不通过</html-el:option>
			          	</html-el:select>
          			</td>
          		</tr>
          		<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单人姓名：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="20" maxlength="20" ></html-el:text></td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">消费者姓名：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
          		</tr> -->
          	</table>
          </td>
        </tr>
      </table>
    </html-el:form>
   </div>
   <div class="rtabcont1">
     <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td nowrap="nowrap" align="center">专卖店编号</td>
          <td width="10%" nowrap="nowrap" align="center">申请前额度</td>
          <td width="10%" nowrap="nowrap" align="center">申请额度</td>
          <td width="8%" nowrap="nowrap" align="center">申请时间</td>
          <td width="8%" nowrap="nowrap" align="center">申请人姓名</td>
          <td width="8%" nowrap="nowrap" align="center">状态</td>
          <td width="8%" nowrap="nowrap" align="center">审核时间</td>
        </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.map.zmd_sn}</font></td>
              <td align="right" nowrap="nowrap"><font class="kz-price-12"><fmt:formatNumber value="${cur.credit_line}" type="currency" /></font></td>
              <td align="right" nowrap="nowrap"><font class="kz-price-12"><fmt:formatNumber value="${cur.money}" type="currency" /></font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.apply_date}" pattern="yyyy-MM-dd HH:mm:ss" /></font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.apply_username}</font></td>
              <td align="center" nowrap="nowrap">
              	<font class="blue12px">
              		<c:if test="${cur.audit_state eq 0}">未审核</c:if>
              		<c:if test="${cur.audit_state eq 1}"><span style="color:green;">审核通过</span></c:if>
              		<c:if test="${cur.audit_state eq -1}"><span style="color:#f00">审核不通过</span></c:if>
              	</font>
              </td>
              <td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd HH:mm:ss" /></font>
              <c:if test="${empty cur.audit_date}">-</c:if></td>
            </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
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
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdInquiryCreditLine.do">
    	<table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
      		<tr>
        		<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
	          		<script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			            pager.addHiddenInputs("dept_id", "${userInfo.dept_id}");
			            pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
	            		document.write(pager.toString());
	            	</script>
            	</td>
      		</tr>
    	</table>
  	</form>
  </div>
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span>
    </div>
  </div>
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
$(document).ready(function(){
//	if ("${sessionScope.roleInfo.role_id}" == "350") {
//		$('body').everyTime('2das','C',function(){
//		    //执行一个会超过20秒以上的程式
//			$("#ceshi").append("测试@");
//			$.ajax({
//		    	type: "POST" , 
//		        url: 'KonkaXxZmdAuditSalesOrder.do' , 
//		        data: "method=getSellBillInfo&dept_id=${sessionScope.userInfo.dept_id}&n=" + Math.random(), //+ "&md_name=" + escape(encodeURIComponent(md_name)) + "&n=" + Math.random(), 
//		        dataType: "json" , 
//		        async: false, 
//		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
//		        success: function (result) {
//
//		        }
//			});
//		},0,true);
//	}
	
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();


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
	
	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}

	$("#zmd_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200
	}).multiselectfilter();

});

//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>