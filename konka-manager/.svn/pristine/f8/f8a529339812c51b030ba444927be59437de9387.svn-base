<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}

.filed_border {
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom: 1px solid #ccc;;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
		<td width="60"><img src="${ctx}/images/manager/help.gif"
			style="vertical-align: middle;" /> <span id="span_help"
			style="cursor: pointer;">帮助</span></td>
	</tr>
</table>
</div>
<html-el:form	action="/admin/KonkaMobileSailDataBill">
<div class="rtabcont1">
	<html-el:hidden property="method" styleId="method" value="listSwitch" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<html-el:hidden property="is_del" styleId="is_del" />
	<table width="100%" border="0" cellspacing="1" cellpadding="5"
		class="rtable1">
		<tr>
			<td width="10%" nowrap="nowrap" title="客户业务员所在部门" align="right">
			<strong class="fb">部门：</strong></td>
			<td width="28%"><c:if test="${empty current_dept}">
				<html-el:select property="l3_dept_id" styleId="l3_dept_id"
					disabled="${disabled}">
					<html-el:option value="">-分公司/经营部-</html-el:option>
				</html-el:select>
				<html-el:select property="l4_dept_id" styleId="l4_dept_id">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
				<html-el:select property="l5_dept_id" styleId="l5_dept_id">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
			</c:if> <c:if test="${not empty current_dept}">
	          		${fn:replace(current_dept.full_name, ',', ' &gt; ')}
	          	</c:if></td>

			<td align="right" width="10%"><strong class="fb">上报门店：</strong>
			</td>
			<td><html-el:text property="dept_name_like" size="30"
				maxlength="40" styleId="dept_name_like" /></td>

		</tr>
		<tr>
			<td align="right"><strong class="fb">业务员 ：</strong></td>
			<td><html-el:text property="report_name_like" size="30"
				style="width:170px;" maxlength="40" styleId="report_name_like"
				styleClass="webinput" /></td>
			<td align="right"><strong class="fb">时间范围：</strong></td>
			<td>
			<html-el:select property="year" styleId="year">
			        <html-el:option value="">-请选择-</html-el:option>
					<html-el:option value="2012">-2012-</html-el:option>
					<html-el:option value="2013">-2013-</html-el:option>
					<html-el:option value="2014">-2014-</html-el:option>
					<html-el:option value="2015">-2015-</html-el:option>
					<html-el:option value="2016">-2016-</html-el:option>
					<html-el:option value="2017">-2017-</html-el:option>
					<html-el:option value="2018">-2018-</html-el:option>
					<html-el:option value="2019">-2019-</html-el:option>
					<html-el:option value="2020">-2020-</html-el:option>
				</html-el:select>
			 - <html-el:select property="month" styleId="month">
			        <html-el:option value="">-请选择-</html-el:option>
					<html-el:option value="01">-01-</html-el:option>
					<html-el:option value="02">-02-</html-el:option>
					<html-el:option value="03">-03-</html-el:option>
					<html-el:option value="04">-04-</html-el:option>
					<html-el:option value="05">-05-</html-el:option>
					<html-el:option value="06">-06-</html-el:option>
					<html-el:option value="07">-07-</html-el:option>
					<html-el:option value="08">-08-</html-el:option>
					<html-el:option value="09">-09-</html-el:option>
					<html-el:option value="10">-10-</html-el:option>
					<html-el:option value="11">-11-</html-el:option>
					<html-el:option value="12">-12-</html-el:option>
				</html-el:select>
				</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td colspan="2" align="right" nowrap="nowrap"><html-el:submit
				styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
		</tr>
	</table>
</div>
<div class="rtabcont1"><%@ include
	file="/commons/pages/messages.jsp"%></div>
<div class="rtabcont1" style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
	    <td width="1%" align="center" nowrap="nowrap">
	    <input name="chkAll" type="checkbox" id="chkAll" value="-1"  />
	    &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;
	    </td>
		<td width="2%" align="center" nowrap="nowrap">序号</td>
		<td width="4%" align="center" nowrap="nowrap">分公司</td>
		<td width="4%" align="center" nowrap="nowrap">门店名称</td>
		<td width="4%" align="center" nowrap="nowrap">促销员JOB_ID</td>
		<td width="4%" align="center" nowrap="nowrap">促销员姓名</td>
		<td width="4%" align="center" nowrap="nowrap">销售总金额</td>
		<td width="4%" align="center" nowrap="nowrap">销售总数量</td>
		<td width="4%" align="center" nowrap="nowrap">促销员上报金额</td>
		<td width="4%" align="center" nowrap="nowrap">审核后金额</td>
	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr class="list-tr">
			<!--		    <td style="display:none;"><html-el:hidden property="user_id" value="${cur.report_id}" /></td>-->
			<td >
			
			<html-el:checkbox property="bill_ids" styleClass="bill_ids" styleId="bill_ids_${vs.count}"  value="${cur.map.bill_ids}" />
			<input type="checkbox" name="sail_ids" class="sail_ids" id="sail_ids_${vs.count}" style="display:none;"  value="${cur.map.sail_ids}" />
			</td>
			<td align="center" nowrap="nowrap">${vs.count}</td>
			<td align="center" nowrap="nowrap">${cur.subcomp_name}</td>
			<td align="center" nowrap="nowrap">${cur.dept_name}</td>
			<td align="center" nowrap="nowrap">${cur.map.ywy_job_id}</td>
			<td align="center" nowrap="nowrap">${cur.report_name}</td>
			<td align="center" nowrap="nowrap"><a href="#"
				onclick="listsailorbill('${cur.report_id}','${cur.dept_id}','sail')">
			<span class="kz-price-12"> <fmt:formatNumber
				value="${cur.all_price}" type="currency" /></span> </a></td>
			<td align="center" nowrap="nowrap">${cur.num}</td>
			<td align="right" nowrap="nowrap"><a href="#"
				onclick="listsailorbill('${cur.report_id}','${cur.dept_id}','bill')">
			<span class="kz-price-12"> <fmt:formatNumber
				value="${cur.map.dec_money}" type="currency" /> </span> </a></td>
			<td align="right" nowrap="nowrap"><a href="#"
				onclick="listsailorbill('${cur.report_id}','${cur.dept_id}','bill')">
			<span class="kz-price-12"> <fmt:formatNumber
				value="${cur.map.audit_money}" type="currency" /> </span> </a></td>
		</tr>
		<c:if test="${vs.last eq true}">
			<c:set var="i" value="${vs.count}" />
		</c:if>
	</c:forEach>

</table>

</div>
<div  align="center" id="show_title" ><strong><font color="red">请填写本次转单相关信息</font></strong>&nbsp;&nbsp;&nbsp;<strong>标题<font color="red">*</font></strong>
<html-el:text property="switch_title" styleId="title" style="widht:150px;" maxlength="25" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>备注:</strong>
<html-el:text property="switch_remark" style="widht:200px;"  maxlength="100"/>
</div>
<div align="center">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="转单" class="but4" id="_submit" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input value="返 回"
			class="but5" id="btn_back" type="button"
			onclick="history.back();return false;" /></div>
</html-el:form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function() {
		$("#title").attr("datatype", "Require").attr("msg", "请填写本次转单的标题");
		  $("#_submit").click(function(){
			  var isSubmit = Validator.Validate(this.form, 3);
				if(isSubmit){
					 $("#method").val("saveSwitch");
		              this.form.submit();
		              $("#method").val("listSwitch");
					}
                });
		  
		  
			 $("#chkAll").click(function(){
				
				  var checkallFlag= $(this).attr("checked");
				  if(checkallFlag=="checked"){
					  $(".bill_ids").attr("checked","checked");
					  $(".sail_ids").attr("checked","checked");
					  }
				  else{
					  $(".bill_ids").removeAttr("checked");
					  $(".sail_ids").removeAttr("checked");
					  }
	                });
		  
		  
		  $(".bill_ids").click(function(){
			  var checkFlag= $(this).attr("checked");
			  if(checkFlag=="checked"){
				  $(this).parent().children(".sail_ids").attr("checked","checked");
				  }
			  else{
				  $(this).parent().children(".sail_ids").removeAttr("checked");
				  }
                });
		  

						var current_dept = '${empty current_dept}';
						if (current_dept != 'false') {
							$("#l3_dept_id").attr( {
								"subElement" : "l4_dept_id",
								"defaultText" : "-请选择-",
								"defaultValue" : "",
								"selectedValue" : "${af.map.l3_dept_id}"
							});
							$("#l4_dept_id").attr( {
								"subElement" : "l5_dept_id",
								"defaultText" : "-请选择-",
								"defaultValue" : "",
								"selectedValue" : "${af.map.l4_dept_id}"
							});
							$("#l5_dept_id").attr( {
								"defaultText" : "-请选择-",
								"defaultValue" : "",
								"selectedValue" : "${af.map.l5_dept_id}"
							});

							$("#l3_dept_id")
									.cs(
											"${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId",
											"par_id",
											"${empty cs_par_id ? 0 : cs_par_id}");
							$("#l3_dept_id").change();
						}
						//subcomp_id_chg();
						category_id_chg();
						customer_cate_id_chg();

						

						var queryForm = document.forms[0];
						$("#span_help").click(function() {
							$("#cvtooltipClose").cvtooltip( {
								panel : "#body_oarcont",
								direction : 1,
								width : 420,
								left : 320,
								top : 5,
								speed : 500,
								delay : 10000
							});
						});

						
                 
					});



	function listsailorbill(user_id,store_id,type){
		
		var year=$("#year").val();
		var month=$("#month").val();
        window.showModalDialog("KonkaMobileSailDataBill.do?method=listSailOrBill&" + encodeURI("user_id=" + user_id +"&store_id=" + store_id +"&year=" + year +"&month=" + month +"&type=" + type +"&audit_state=2&random=" + Math.random()), window, "dialogWidth:900px;status:no;dialogHeight:580px");
		}

	//分公司- 连动-办事处
	function subcomp_id_chg() {
		$("#office_id").empty();
		$("__tag_210$4_-所属办事处-__tag_210$28_").appendTo($("#office_id"));
		var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getDept&subcomp_id="
				+ $('#subcomp_id').val();
		$.getJSON(url,
				function(data) {
					if (data != null) {
						$.each(data, function(i, item) {
							$("__tag_215$7___tag_236$22_").val(item[1]).text(
									item[0]).appendTo($("#office_id"));
						});
					}
					if ('${af.map.office_id }' != null
							|| '${af.map.office_id }' != '') {
						$("#office_id").val('${af.map.office_id }');
					}
				});
	}

	//类别-连动-型号
	function category_id_chg() {
		$("#model_id").empty();
		$("__tag_227$4_-产品型号-__tag_227$27_").appendTo($("#model_id"));
		var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getModel&size_id="
				+ $('#measure_id').val();
		$.getJSON(url, function(data) {
			if (data != null) {
				$.each(data, function(i, item) {
					var option = $("__tag_232$20___tag_256$34_").val(item[1])
							.text(item[0]);
					option.appendTo($("#model_id"));
				});
				if ('${af.map.model_id }' != null
						|| '${af.map.model_id }' != '') {
					$("#model_id").val('${af.map.model_id }');
				}
			}
		});
	}

	//类别-连动-客户类别
	function customer_cate_id_chg() {
		$("#customer_cate_id").empty();
		$("__tag_245$4_-细分类型-__tag_245$27_").appendTo($("#customer_cate_id"));
		var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getCType&c_comm="
				+ $('#c_comm').val();
		$.getJSON(url, function(data) {
			if (data != null) {
				$.each(data, function(i, item) {
					var option = $("__tag_250$20___tag_277$34_").val(item[1])
							.text(item[0]);
					option.appendTo($("#customer_cate_id"));
				});
				if ('${af.map.customer_cate_id }' != null
						|| '${af.map.customer_cate_id}' != '') {
					$("#customer_cate_id").val('${af.map.customer_cate_id }');
				}
			}
		});
	}

	function loading() {
		jLoading("&nbsp;&nbsp;正在加载数据...", {
			autoHide : false,
			HorizontalPosition : "center",
			VerticalPosition : "center",
			MinWidth : 150
		});
		}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
