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
    <html-el:form action="/zmd/KonkaXxZmdAuditCreditLine">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table id="tab_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;"><table width="80%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><!--<html-el:text property="zmd_sn_like" styleId="zmd_sn_like" size="20" maxlength="20" ></html-el:text>-->
                  <html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
                    <html-el:option value="">=请选择=</html-el:option>
                    <c:forEach items="${zmdList}" var="cur">
                      <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                    </c:forEach>
                  </html-el:select></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;"></td>
                <td style="padding:2px;"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaXxZmdAuditCreditLine.do">
      <div style="text-align:left;margin-left:5px;margin-top:-5px;margin-bottom:5px;">
        <input type="button" class="but8" value="批量审核" id="batchAudit" />
      </div>
      <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap" align="center">专卖店编号</td>
          <td width="10%" nowrap="nowrap" align="center">信用等级</td>
          <td width="10%" nowrap="nowrap" align="center">申请额度</td>
          <td width="8%" nowrap="nowrap" align="center">申请时间</td>
          <td width="8%" nowrap="nowrap" align="center">申请人</td>
          <td width="6%" nowrap="nowrap" align="center">状态</td>
          <td width="8%" nowrap="nowrap" align="center">审核时间</td>
          <td width="5%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center"><c:if test="${cur.audit_state lt 1}">
                <input name="pks" type="checkbox" class="pks" id="pks" value="${cur.acc_id}" />
              </c:if>
              <c:if test="${cur.audit_state eq 1}">
                <input name="pks" type="checkbox" class="pks" id="pks" disabled="disabled" />
              </c:if></td>
            <td align="left" nowrap="nowrap"><font class="blue12px">${cur.map.zmd_sn}</font></td>
            <td align="left" nowrap="nowrap"><font class="blue12px">${cur.map.credit_type}</font></td>
            <td align="right" nowrap="nowrap"><font class="kz-price-12">
              <fmt:formatNumber value="${cur.money}" type="currency" />
              </font></td>
            <td align="center" nowrap="nowrap"><font class="blue12px">
              <fmt:formatDate value="${cur.apply_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </font></td>
            <td align="center" nowrap="nowrap"><font class="blue12px">${cur.apply_username}</font></td>
            <td align="center" nowrap="nowrap"><font class="blue12px">
              <c:choose>
                <c:when test="${cur.audit_state eq 0}">未审核</c:when>
                <c:when test="${cur.audit_state eq 1}"><span style="color:green;">审核通过</span></c:when>
                <c:when test="${cur.audit_state eq -1}"><span style="color:#f00">审核不通过</span></c:when>
              </c:choose>
              </font></td>
            <td align="center" nowrap="nowrap"><font class="blue12px">
              <fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </font>
              <c:if test="${empty cur.audit_date}"><span>-</span></c:if></td>
            <td align="center" nowrap="nowrap"><c:if test="${cur.audit_state lt 1}"> <span style="cursor:pointer;" class="fblue" onclick="openAuditDiv(${af.map.mod_id}, '${cur.map.zmd_sn}','${cur.map.credit_type}', ${cur.money}, $(this).parent().prev().prev().prev().prev().text(), ${cur.acc_id})">快速审核</span> <span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaXxZmdAuditCreditLine.do', 'acc_id=${cur.acc_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">审核</span>
                <!--	              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'mod_id=802001');$('#message_tip').show();">查看</span>-->
              </c:if>
              <c:if test="${cur.audit_state eq 1}"> <span style="color:#ccc">快速审核</span> <span style="color:#ccc">审核</span> </c:if></td>
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
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAuditCreditLine.do">
      <table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			            pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
						pager.addHiddenInputs("zmd_sn_like", "${af.map.zmd_sn_like}");	
	            		document.write(pager.toString());
	            	</script></td>
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
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
  </div>
</div>
<div id="plAuditDiv" style="display:none;">
  <html-el:form action="/zmd/KonkaXxZmdAuditCreditLine">
    <html-el:hidden property="method" value="plAudit" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="is_audit" value="0" />
    <html-el:hidden property="queryString" />
    <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="" align="center" nowrap="nowrap">专卖店编号</td>
        <td width="" align="center" nowrap="nowrap">申请信用等级</td>
        <td width="" align="center" nowrap="nowrap">申请额度</td>
        <td width="" align="center" nowrap="nowrap">申请时间</td>
      </tr>
      <tbody id="creditLineTbody">
      </tbody>
    </table>
    <div class="rtabcont1" align="left">
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td><table width="95%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
              <tr>
                <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">审核状态：</strong></td>
                <td align="left"><label for="audit_state1" style="cursor:pointer">
                    <input type="radio" name="audit_state" value="1" id="audit_state1" />
                    审核通过</label>
                  <label for="audit_state2" style="cursor:pointer">
                    <input type="radio" name="audit_state" value="-1" id="audit_state2" />
                    审核不通过</label></td>
              </tr>
              <tr>
                <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">审核备注：</strong></td>
                <td align="left"><textarea name="audit_memo" cols="60" rows="5" id="audit_memo"></textarea>
                  <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
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
			alert("请选择要批量审核的信用额度申请！");
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
										var f = document.forms[3];
										if(Validator.Validate(f, 1)){
											if(confirm("确定要批量审核？")){
												f.submit();
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
							var acc_id = "${cur.acc_id}";
							var zmd_sn = "${cur.map.zmd_sn}";
							var money = "${cur.money}";
							var credit_type = "${cur.map.credit_type}";
							var apply_date = '<fmt:formatDate value="${cur.apply_date}" pattern="yyyy-MM-dd HH:mm:ss" />';
							if (form.pks[i].value == parseInt(acc_id, 10)) {
								$("#creditLineTbody").append(
									'<tr>' +
										'<td>' + zmd_sn + '</td>' + 
										'<td align="left">' + credit_type + '</td>' + 
										'<td align="right">' + money + '</td>' + 
										'<td align="center">' + apply_date + '</td>' +
										'<input type="hidden" name="pkss" value="' + parseInt(acc_id, 10) + '" />' +
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

function openAuditDiv(mod_id, zmd_sn,credit_type, money, apply_date, acc_id){
	$("#plAuditDiv").dialog({
	      title: '审核', 
	      width: 600,
	      height: 350,
	      draggable: true,
	      position:'center',
	      resizable: false,
	      modal : true,
	      buttons: {"确认": function() {
				var f = document.forms[3];
				if(Validator.Validate(f, 1)){
					if(confirm("确定要审核？")){
						f.submit();
					} else {
						return false;
					}
				}
			},
			"关闭": function() {$(this).dialog("close");}
 		  }
	}).dialog("open");

	$("#creditLineTbody").empty();
	$("#creditLineTbody").append(
		'<tr>' +
			'<td>' + zmd_sn + '</td>' + 
			'<td align="left">' + credit_type + '</td>' + 
			'<td align="right">' + money + '</td>' + 
			'<td align="center">' + apply_date + '</td>' +
			'<input type="hidden" name="acc_id_hidden" value="' + parseInt(acc_id, 10) + '" />' +
		'</tr>'	
	);  
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>