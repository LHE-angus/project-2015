<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

input {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

label {
	cursor: pointer;
}
</style>
</head>
<body style="font-family: Microsoft Yahei;">
<div style="width: 100%">
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
	</tr>
</table>
</div>
<div class="rtabcont2"><html-el:form
	action="/admin/KonkaOrderPlan" enctype="multipart/form-data">
	<html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		class="rtable3">
		<tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">* </font>客户：</td>
			<td align="left" width="80%" colspan="3"><html-el:select
				property="customer_code_select" styleId="customer_code_select"
				onchange="customer_code_chg();" value="${af.map.r3_code}">
				<html-el:option value="">--请选择--</html-el:option>
				<c:forEach items="${customerList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.map.customer_code}">${cur.map.customer_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
		</tr>

		<tr>
			<td class="title_item" align="right" nowrap="nowrap">客户编码：</td>
			<td><html-el:text property="r3_code" styleId="r3_code"></html-el:text></td>
			<td class="title_item" align="right" nowrap="nowrap">客户名称：</td>
			<td><html-el:text property="customer_name"
				styleId="customer_name"></html-el:text></td>
		</tr>

		<tr>
			<td class="title_item" align="right" nowrap="nowrap">进货年月：</td>

			<td colspan="3"><html-el:select property="plan_year"
				styleId="plan_year">
				<html-el:option value="">-请选择-</html-el:option>
				<c:forEach var="year" begin="2014" end="2024" step="1">
					<html-el:option value="${year}">${year}</html-el:option>
				</c:forEach>
			</html-el:select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html-el:select property="plan_month"
				styleId="plan_month">
				<html-el:option value="">-请选择-</html-el:option>
				<c:forEach var="month" begin="01" end="12" step="1">
					<html-el:option value="${month}">${month}</html-el:option>
				</c:forEach>
			</html-el:select></td>
		</tr>
		<tr>
			<td class="title_item" align="right" nowrap="nowrap">计划机型：</td>
			<td colspan="3"><html-el:hidden property="select-choice-2"
				styleId="select-choice-2"></html-el:hidden> <html-el:text
				property="pd_name" styleId="pd_name"></html-el:text> <!--
            <html-el:select property="pd_id" styleId="pd_id"  onchange="pd_id_chg();">
              <c:forEach var="cur" items="${modelList}"> 
				<html-el:option value="${cur.pd_id}">${cur.md_name}</html-el:option>
				</c:forEach>
            </html-el:select>
              --> <html-el:hidden property="pd_id" styleId="pd_id" /></td>
		</tr>

		<tr>
			<td class="title_item" align="right" nowrap="nowrap">上月末库存：</td>
			<td><html-el:text property="last_stock_num"
				styleId="last_stock_num" onfocus="javascript:setOnlyInt(this)"></html-el:text>
			&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="getlaststocknum" value="上月末库存" /></td>
			<td class="title_item" align="right" nowrap="nowrap">计划进货数量：</td>
			<td><html-el:text property="plan_stock_num"
				styleId="plan_stock_num" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
		</tr>
		<tr>
			<td class="title_item" align="right" nowrap="nowrap">计划销售数量：</td>
			<td colspan="3"><html-el:text property="plan_sale_num"
				styleId="plan_sale_num" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
		</tr>

		<c:if test="${not empty af.map.id}">

			<tr>
				<td class="title_item" align="right" nowrap="nowrap">添加人：</td>
				<td>${af.map.add_user_name}</td>


				<td class="title_item" align="right" nowrap="nowrap">添加时间：</td>
				<td><fmt:formatDate value="${af.map.add_date}" var="add_date"
					pattern="yyyy-MM-dd" /> ${add_date}</td>
			</tr>

		</c:if>




		<tr>
			<td class="title_item" align="right" nowrap="nowrap">备注：</td>
			<td align="left" colspan="3"><html-el:textarea property="memo"
				styleId="memo" cols="5" style="width:600px;height:100px;"></html-el:textarea>
			</td>
		</tr>



		<tr>
			<td colspan="4" align="center"><input type="button"
				name="temp_submit" class="bgButtonSave" value="保存" id="btn_submit" />
			<input class="bgButtonBack" type="button" name="return" value="返回"
				id="btn_reset" onclick=
	history.back();;;
/> <br />
			<div style="height: 50px">&nbsp;</div>
			</td>
		</tr>
	</table>
</html-el:form></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">
	//<![CDATA[

	var f = document.forms[0];
	$(document).ready(
			function() {
				var pd_module_data;

				$("#r3_code").attr("readOnly", "readOnly").attr("dataType",
						"Require").attr("msg", "请选择客户");
				$("#customer_name").attr("readOnly", "readOnly").attr(
						"dataType", "Require").attr("msg", "请选择客户");
				$("#pd_id").attr("dataType", "Require").attr("msg", "请选择机型");
				$("#pd_name").attr("dataType", "Require").attr("msg", "请选择机型");
				$("#plan_year").attr("dataType", "Require").attr("msg",
						"请选择计划年份");
				$("#plan_month").attr("dataType", "Require").attr("msg",
						"请选择计划月份");
				$("#memo").attr("datatype", "Limit").attr("max", "200").attr(
						"min", "0").attr("msg", "备注必须在200个字之内");

				//初始化待选机型
				pd_module_init();

				/**$("#order_target").attr("Require",true).attr("dataType", "Double").attr("msg", "请正确填写，只能为数字");//shu
				$("#open_date").attr("dataType", "Require").attr("msg", "请填写");
				$("#hd_addr").attr("dataType", "Require").attr("msg", "请填写");
				$("#charge_person_job_id").attr("dataType", "Require").attr("msg", "请填写");
				$("#charge_person_tel").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
				$("#meeting_photo" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
				$("#photo_file" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
				$("#meeting_memo").attr("datatype", "Limit"  ).attr("max", "200").attr("min","0").attr("msg", "会议备注必须在200个字之内");*/

				/**$("#pd_name").click(function(){
					var returnValue = window.showModalDialog("KonkaOrderPlan.do?method=selectModel",window,"dialogWidth:900px;status:no;dialogHeight:500px");
					$("#pd_id").val(returnValue.model_id);
					$("#pd_name").val(returnValue.model_name);
				});**/

				$("#pd_name").bind("input propertychange", function() {
					initAutoDiv(0);
				});

				$("#btn_submit").click(function() {

					if (Validator.Validate(this.form, 3)) {
						this.form.submit();
					}
					resizeFrameHeight();
				});
				$("#getlaststocknum").click(function() {
					var r3_code = $("#r3_code").val();
					var pd_name = $("#pd_name").val();
					var year=$("#plan_year").val();
					var month=$("#plan_month").val();
					alert(r3_code+"+"+pd_name+"+"+year+"+"+month+"+");
					if(null!=r3_code && null!=pd_name && null!=year && null!=month){
						$.ajax( {
							type : "POST",
							url : "${ctx}/manager/admin/KonkaOrderPlan.do?method=getLastStock",
							data:{"year":year,"month":month,"r3_code":r3_code,"pd_name":pd_name},
							dataType : "jsonp",
							jsonp : "jsonpcallback",
							error : function(xhr, ajaxOptions, thrownError) {},
							success : function(result) {
								$("#last_stock_num").val(result);
								}
							});
						}else{
							alert("客户,年度,月度,型号填写完成后才能计算库存！");
							return ;
							}
				});

				function multi(id) {
					$("#" + id).multiselect(
							{
								noneSelectedText : '==请选择==',
								selectedList : 1,
								multiple : false,
								minWidth : 150,
								click : function(event, ui) {
									if (ui.value != "") {
										$("#" + id).val(ui.value);
										$("#r3_code").val($("#" + id).val());
										$("#customer_name").val(
												$("#" + id).find(
														"option:selected")
														.text());

									}
								}
							}).multiselectfilter();
				}
				multi("customer_code_select");
				$("#customer_code_select").val('${af.map.r3_code}');
				//calcPdNumAndPrice("tbodyOrder");
				resizeFrameHeight();
			});//ready end
	/*处理返回值数据 ===start===*/

	function resizeFrameHeight(offset, min_height) {
		$("#mainFrame", window.parent.document).height(
				Math.max((min_height || 0), $(document).find("body").height(),
						$(document).children().height())
						+ (offset || 0));
	}

	//正则表达式：只能输入数字
	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter", function() {
			return false;
		});
		$(obj).keypress(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).keyup(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).blur(function() {
			if (!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))
				obj.value = obj.o_value;
			else {
				if (obj.value.match(/^\d+$/))
					obj.value = obj.value;
				if (obj.value.match(/^\.$/))
					obj.value = 0;
				obj.o_value = obj.value;
			}
			if (obj.value.length == 0 || isNaN(obj.value) || obj.value == 0)
				obj.value = "0";
		});
	}

	//根据用户名和密码初始化型号信息
	function pd_module_init() {
		// 正在加载数据层
		var load_data = $.dialog( {
			content : '数据加载中...',
			max : false,
			min : false,
			icon : 'loading.gif',
			title : '提示！'
		});

		$.ajax( {
			type : "POST",
			url : "${ctx}/manager/admin/KonkaOrderPlan.do?method=selectModel",
			dataType : "jsonp",
			jsonp : "jsonpcallback",
			error : function(xhr, ajaxOptions, thrownError) {
				alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText
						+ "," + xhr.status + "," + thrownError + "】！");
			},
			success : function(result) {
				// 关闭-正在加载数据层
			load_data.close();

			if (result.status == "-1") {
				$.dialog.alert(result.msg, function() {
				});
			} else {
				pd_module_data = result;
			}
		}
		});
	}
	//输入关键字搜索
	function initAutoDiv() {
		// 隐藏下拉框
		if ($("#auto_prompt_div"))
			$("#auto_prompt_div").remove();
		// 输入框有改变将型号置为空
		$("#select-choice-2").val("");

		if ($.trim($("#pd_name").val()).length >= 2) {
			var val = $.trim($("#pd_name").val());
			var count = 0;
			for ( var i = 0; i < pd_module_data.length; i++) {
				var id = pd_module_data[i].id;
				var name = pd_module_data[i].name;
				if (name.toLowerCase().indexOf(val.toLowerCase()) != -1)
					count++;
			}

			var top = $("#pd_name").offset().top;
			var left = $("#pd_name").offset().left;
			var width = $("#pd_name").width();
			var auto_prompt_div = $("<div />").width(width).css("position",
					"absolute").css("backgroundColor", "white").css("left",
					left).css("top", top + $("#pd_name").height() + 6).css(
					"border", "1px solid #1C86EE")
					.attr("id", "auto_prompt_div");
			var table = $("<table width='100%' id=\"div_table\" />").attr(
					"cellpadding", "0").attr("cellspacing", "0");

			// 满足条件控制在30个内，如果超过30个则不显示
			if (count != 0) {
				for ( var i = 0; i < pd_module_data.length; i++) {
					var id = pd_module_data[i].id;
					var name = pd_module_data[i].name;
					if (name.toLowerCase().indexOf(val.toLowerCase()) != -1) {
						var tr = $("<tr />").css("cursor", "pointer").attr(
								"class", "tr_value").mouseout(
								function() {
									$(this).css("backgroundColor", "white")
											.css("color", "black");
								}).mouseover(
								function() {
									$(this).css("backgroundColor", "#1C86EE")
											.css("color", "white");
								}).click(
								function() {
									$("#pd_name").val(
											$(this).find("td").eq(0).html());
									$("#pd_id")
											.val(
													$(this).find("td").eq(0)
															.attr("id"));
									$("#select-choice-2")
											.val(
													$(this).find("td").eq(0)
															.attr("id"));
									$("#auto_prompt_div").remove();
								});
						var td = $("<td class=\"td_class\" />").html(name).css(
								"fontSize", "12px").css("margin",
								"5px 5px 5px 5px").css("padding-left", "6px")
								.attr("align", "left").attr("id", id);
						tr.append(td);
						table.append(tr);
					}
				}
			} else {
				var tr = $("<tr />").css("cursor", "pointer").attr("class",
						"tr_value").mouseout(
						function() {
							$(this).css("backgroundColor", "white").css(
									"color", "black");
						}).mouseover(
						function() {
							$(this).css("backgroundColor", "#1C86EE").css(
									"color", "white");
						});
				var td = $("<td />").html(
						"提示，您搜索的型号“" + val + "”，共检索到 " + count
								+ " 条数据，请精确搜索条件！").css("fontSize", "12px").css(
						"margin", "5px 5px 5px 5px").css("padding-left", "6px")
						.attr("align", "left");
				tr.append(td);
				table.append(tr);
			}

			auto_prompt_div.append(table);
			$(document.body).append(auto_prompt_div);
		}
	}
	function customer_code_chg() {
		$("#r3_code").val($("#customer_code_select").val());
		$("#customer_name").val(
				$("#customer_code_select").find("option:selected").text());
	}
	function pd_id_chg() {
		$("#pd_name").val($("#pd_id").find("option:selected").text());
	}

	function DBC2SBC(str) {
		var result = '';
		for (i = 0; i < str.length; i++) {
			code = str.charCodeAt(i);//获取当前字符的unicode编码
			if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已及各种字符
			{
				result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
			} else if (code == 12288)//空格
			{
				result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
	//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>