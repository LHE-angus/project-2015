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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}

span.sel-tab {
	height: 30px;
	display: block;
	float : right;
	padding: 0px 10px;
	margin-top: 2px;
	margin-right: 10px;
	border-radius:3px 3px 0px 0px;
}
span.sel-tab-active {
	border-bottom : 0px;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
	background-color: #FFF;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}<span class="sel-tab"><a href="${ctx}/manager/admin/KonkaCustomerSailData.do?method=showcust">专卖店销售历史</a></span><span class="sel-tab sel-tab-active">客户端销售查询</span></td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
			<html-el:form action="/admin/KonkaCustomerSailData">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" styleId="mod_id" />
				<table width="100%" border="0" cellspacing="1" cellpadding="5"
					class="rtable1">
					<tr>
						<td width="10%" nowrap="nowrap" title="客户业务员所在部门"><strong
							class="fb">部门：</strong></td>
						<td width="30%"><c:if test="${empty current_dept}">
								<html-el:select property="l3_dept_id" styleId="l3_dept_id"
									disabled="${disabled}">
									<html-el:option value="">-分公司/经营部-</html-el:option>
								</html-el:select>
          	&nbsp;
          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
									<html-el:option value="">-请选择-</html-el:option>
								</html-el:select>
          	&nbsp;
          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
									<html-el:option value="">-请选择-</html-el:option>
								</html-el:select>
							</c:if>
							<c:if test="${not empty current_dept}">${fn:replace(current_dept.full_name, ',', ' &gt; ')}</c:if>
						</td>
						<td width="10%"><strong class="fb">客户名称：</strong></td>
						<td width="30%"><html-el:text property="customer_name_like"
								size="30" style="width:170px;" maxlength="40"
								styleId="customer_name_like" styleClass="webinput" /></td>
						<td width="20%" rowspan="4" align="center" nowrap="nowrap"><html-el:submit
								styleId="btn_submit" styleClass="but1">搜索</html-el:submit> <input
							type="button" value="Excel" id="export_excel" class="but_excel"
							style="margin-left: 10px;" /></td>
					</tr>
					<tr>
						<td><strong class="fb">产品型号：</strong></td>
						<td><html-el:text property="md_name_like"
								size="30" style="width:170px;" maxlength="40"
								styleId="md_name_like" styleClass="webinput" /> </td>
						<td><strong class="fb">时间范围：</strong></td>
						<td><html-el:text property="date_begin" size="9"
								maxlength="10" readonly="true" styleClass="webinput"
								style="cursor:pointer;"
								onclick="new Calendar(2005, 2030, 0).show(this);" /> - <html-el:text
								property="date_end" size="9" maxlength="10" readonly="true"
								styleClass="webinput" style="cursor:pointer;"
								onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
					</tr>
					<tr>
			          <td><strong class="fb">客户分类：</strong></td>
			          <td><html-el:select property="c_comm" styleId="c_comm" onchange="customer_cate_id_chg();">
			              <html-el:option value="">-客户类型-</html-el:option>
			              <c:forEach var="cur" items="${konkaCategoryList}">
			              	 <c:if test="${not empty cur.map.c_comm}"><html-el:option value="${cur.map.par_index}">${cur.map.c_comm}</html-el:option></c:if>
			              </c:forEach>
			            </html-el:select>&nbsp;
			            <select name="customer_cate_id" id="customer_cate_id">
			              <option value="">-细分类型-</option>
			            </select></td>
			            <td></td>
			            <td></td>
			        </tr>
				</table>
			</html-el:form>
		</div>
  <div class="rtabcont1">
    <%@ include
	file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;"></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司R3编码</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">参考金额</td>
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.ADD_DATE}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.DEPT_SN}</td>
          <td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.JB_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.R3_CODE}</td>
           <td align="left" nowrap="nowrap">${cur.C_COMM}</td>
          <td align="left" nowrap="nowrap">${cur.C_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.MD_SIZE }</td>
          <td align="left" nowrap="nowrap">${cur.GOODS_NAME}</td>
          <td align="right" nowrap="nowrap">${cur.NUM }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.PRICE }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.MONEY}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.PRICE_REF }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.PRICE_REF eq null? null:cur.PRICE_REF*cur.NUM}" type="currency" />
            </span></td>
          <td align="left" nowrap="nowrap">${cur.LINK_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_TEL}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_ID}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_ADDR}</td>
          <td align="left" nowrap="nowrap">${cur.NOTES}</td>
          <td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,客户端', ',')[cur.DATA_SOURCE-1]}</td>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaCustomerSailData.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");							
			pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");							
			pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");							
			pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");							
			pager.addHiddenInputs("office_id", "${af.map.office_id}");							
			pager.addHiddenInputs("category_id", "${af.map.category_id}");							
			pager.addHiddenInputs("model_id", "${af.map.model_id}");							
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("yw_name", "${af.map.yw_name}");
			pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
			pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");									
			pager.addHiddenInputs("c_index", "${af.map.c_index}");									
			pager.addHiddenInputs("customer_cate_id", "${af.map.customer_cate_id}");
			pager.addHiddenInputs("c_comm", "${af.map.c_comm}");										
			pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");										
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="销售明细">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司R3编码</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">参考金额</td>
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.ADD_DATE}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.DEPT_SN}</td>
          <td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.JB_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.R3_CODE}</td>
          <td align="left" nowrap="nowrap">${cur.C_COMM}</td>
          <td align="left" nowrap="nowrap">${cur.C_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.MD_SIZE }</td>
          <td align="left" nowrap="nowrap">${cur.GOODS_NAME}</td>
          <td align="right" nowrap="nowrap">${cur.NUM }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${PRICE }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.MONEY}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.PRICE_REF }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.PRICE_REF*cur.NUM}" type="currency" />
            </span></td>
          <td align="left" nowrap="nowrap">${cur.LINK_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_TEL}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_ID}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_ADDR}</td>
          <td align="left" nowrap="nowrap">${cur.NOTES}</td>
          <td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,客户端', ',')[cur.DATA_SOURCE-1]}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var current_dept = '${empty current_dept}';
	if(current_dept != 'false'){
		$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
		$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
		$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
		$("#l3_dept_id").change();
	}
	//subcomp_id_chg();
	//category_id_chg();
	customer_cate_id_chg();

	
	$(".list-tr td").each(function(){
		var text = $(this).html();
		if($.trim(text).length == 0) {
			$(this).html("<span style='color:#CCC;'>未填写</span>");
		}
	});

	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });

    // 导出excel
    $("#export_excel").click(function(){
    	if(confirm("提示，您确认导出数据？")){
    		loading();
    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
    		$("#bottomPageForm").submit();
    	}
    });

    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '${ctx}/manager/admin/KonkaCustomerSailData.do?method=toExcel');
	}
	
});

//分公司- 连动-办事处
function subcomp_id_chg(){
	$("#office_id").empty();
	$("<option value=''>-所属办事处-</option>").appendTo($("#office_id"));
	var url = "${ctx}/manager/admin/KonkaCustomerSailData.do?method=getDept&subcomp_id="+$('#subcomp_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				$("<option></option>").val(item[1]).text(item[0]).appendTo($("#office_id"));
			});
		}
		if('${af.map.office_id }' != null || '${af.map.office_id }' != '' ){
			$("#office_id").val('${af.map.office_id }');
		}
	});
}

//类别-连动-型号
function category_id_chg(){
	$("#model_id").empty();
	$("<option value=''>-产品型号-</option>").appendTo($("#model_id"));
	var url = "${ctx}/manager/admin/KonkaCustomerSailData.do?method=getModel&size_id="+$('#measure_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#model_id"));
			});
			if('${af.map.model_id }' != null || '${af.map.model_id }' != '' ){
				$("#model_id").val('${af.map.model_id }');
			}
		}
	});
}

//类别-连动-客户类别
function customer_cate_id_chg(){
	$("#customer_cate_id").empty();
	$("<option value=''>-细分类型-</option>").appendTo($("#customer_cate_id"));
	var url = "${ctx}/manager/admin/KonkaCustomerSailData.do?method=getCType&c_comm="+$('#c_comm').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#customer_cate_id"));
			});
			if('${af.map.customer_cate_id }' != null || '${af.map.customer_cate_id}' != '' ){
				$("#customer_cate_id").val('${af.map.customer_cate_id }');
			}
		}
	});
}

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
