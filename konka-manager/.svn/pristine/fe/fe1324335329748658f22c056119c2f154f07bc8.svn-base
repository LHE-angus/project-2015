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
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
				<html-el:form action="/admin/KonkaR3Shop" enctype="multipart/form-data">
					<html-el:hidden property="id" styleId="id" />
					<html-el:hidden property="mod_id" styleId="mod_id" />
					<html-el:hidden property="method" styleId="method" value="r3Add" />
					<html-el:hidden property="queryString" styleId="queryString" />
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<th height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>常规信息</span></th>
							<th>&nbsp;</th>
							<th height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>上次维护时间</span></th>
							<th>&nbsp;</th>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户名称：</td>
							<td width="40%" align="left"><html-el:text property="customer_name" size="40" maxlength="30" styleId="customer_name" /></td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>R3编码：</td>
							<td width="40%" align="left"><html-el:text property="r3_code" size="40" maxlength="30" styleId="r3_code" />
							 &nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="r3_code_exist_error">该R3编码已存在，请重新输入！</span></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所在区域名称：</td>
							<td width="40%" align="left">
								<html-el:select property="area_name" styleId="area_name">
									<html-el:option value="">-请选择-</html-el:option>
									<html-el:option value="华东">华东</html-el:option>
					                <html-el:option value="山东">山东</html-el:option>
									<html-el:option value="东北">东北</html-el:option>
									<html-el:option value="华北">华北</html-el:option>
									<html-el:option value="华南">华南</html-el:option>
									<html-el:option value="西南">西南</html-el:option>
									<html-el:option value="华中">华中</html-el:option>
									<html-el:option value="西北">西北</html-el:option>
                            	</html-el:select>
							</td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
							<td width="40%" align="left"><html-el:text property="customer_code" size="40" maxlength="30" styleId="customer_code" /></td>
							
						</tr>
						<!-- 
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>经办名称：</td>
							<td width="88%" align="left"><html-el:select property="handle_name" styleId="handle_name">
					         	<html-el:option value="">请选择</html-el:option>
					         	<c:forEach items="${handleList}" var="cur">
					         		<html-el:option value="${cur.handle_name}">${cur.handle_name}</html-el:option>
					         	</c:forEach>
					         </html-el:select></td>
						</tr>
						 -->
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
							<td width="40%" align="left">
								 <html-el:hidden property="branch_area_name" styleId="branch_area_name" />
						         <html-el:select property="branch_area_name_2" styleId="branch_area_name_2">
						         	<html-el:option value="">请选择</html-el:option>
						         	<c:forEach items="${BranchList}" var="cur">
						         		<html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
						         	</c:forEach>
						         </html-el:select></td>
						    <td width="10%" nowrap="nowrap" class="title_item" align="right">2010合并编码：</td>
							<td width="40%" align="left"><html-el:text property="merge_code_2010" size="40" maxlength="30" styleId="merge_code_2010" /></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户类别：</td>
							<td width="40%" align="left">
							<html-el:select property="customer_type" styleId="customer_type">
								<html-el:option value="">请选择...</html-el:option>
								<c:forEach var="cur" items="${konkaCategoryList}">
									<html-el:option value="${cur.c_index}">[${cur.c_comm}] ${cur.c_name}</html-el:option>
								</c:forEach>
							</html-el:select> 
							</td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">交易状态 ：</td>
							<td width="40%" align="left"><html-el:select property="status" styleId="status">
															<html-el:option value="1">有交易</html-el:option>
															<html-el:option value="2">无交易</html-el:option>
														 </html-el:select></td>
						</tr>
						
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">上年结算额：</td>
							<td width="40%" align="left"><html-el:text property="last_year_money" size="20" maxlength="10" styleId="last_year_money" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">上年回款额：</td>
							<td width="40%" align="left"><html-el:text property="last_year_back_money" size="20" maxlength="10" styleId="last_year_back_money" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">备注：</td>
							<td width="40%" align="left" colspan="3"><html-el:text property="r3_desc" size="40" maxlength="30" styleId="r3_desc" /></td>
						</tr>
						<!--
						<tr>
						 <td width="12%" nowrap="nowrap" class="title_item" align="right">是否继续创建买卖提网点：</td>
						 <td width="88%" align="left">
						 <html-el:select property="createMmt" styleId="createMmt">
						                              <html-el:option value="1">否</html-el:option>
						                              <html-el:option value="0">是</html-el:option>
						 							  </html-el:select>
						 							  
						 </td>
						</tr>
						-->
						<tr>
							<th colspan="4" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>客户资料</span></th>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">企业类型：</td>
							<td width="40%" align="left">
								<html-el:select property="entp_type" styleId="entp_type">
									<html-el:option value="">请选择...</html-el:option>
									<c:forEach items="${entpTypeList}" var="cur">
										<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">注册资金：</td>
							<td width="40%" align="left"><html-el:text property="entp_reg_money" styleId="entp_reg_money" size="20" maxlength="10" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">客户规模（年销售额）：</td>
							<td width="40%" align="left">
								<html-el:select property="entp_scale" styleId="entp_scale">
									<html-el:option value="">请选择...</html-el:option>
									<c:forEach items="${entpScaleList}" var="cur">
										<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">员工总数：</td>
							<td width="40%" align="left">
								<html-el:select property="entp_man_count" styleId="entp_man_count">
									<html-el:option value="">请选择...</html-el:option>
									<html-el:option value="1">1000以上</html-el:option>
									<html-el:option value="2">500~1000以下</html-el:option>
									<html-el:option value="3">100~500以下</html-el:option>
									<html-el:option value="4">100以下</html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">公司电话：</td>
							<td width="40%" align="left"><html-el:text property="entp_tel" styleId="entp_tel" size="40" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">公司传真：</td>
							<td width="40%" align="left"><html-el:text property="entp_fax" styleId="entp_fax" size="40" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">成立时间：</td>
							<td width="40%" align="left"><html-el:text property="entp_birthday" styleId="entp_birthday" size="10" maxlength="10" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">邮编：</td>
							<td width="40%" align="left"><html-el:text property="entp_post" styleId="entp_post" size="40" maxlength="20" /></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">所在城市：</td>
							<td width="40%" align="left" colspan="3">
								<select name="province" id="province" style="width:180px;">
				                  <option value="">-请选择省/直辖市/自治区-</option>
				                </select>
				                &nbsp;
				                <select name="city" id="city" style="width:100px;">
				                  <option value="">-请选择市-</option>
				                </select>
				                &nbsp;
				                <select name="country" id="country" style="width:100px;">
				                  <option value="">-请选择县-</option>
				                </select>
				                &nbsp;
				                <select name="town" id="town" style="width:100px;">
				                  <option value="">-请选择乡镇-</option>
				                </select>
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">城市级别：</td>
							<td width="40%" align="left" colspan="3">
								<html-el:select property="entp_p_level" styleId="entp_p_level">
									<html-el:option value="">请选择...</html-el:option>
									<html-el:option value="1">一线城市</html-el:option>
									<html-el:option value="2">二线城市</html-el:option>
									<html-el:option value="3">三线城市</html-el:option>
									<html-el:option value="4">四线城市</html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">公司地址：</td>
							<td width="40%" align="left" colspan="3"><html-el:text property="entp_addr" styleId="entp_addr" size="60" maxlength="40" /></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">公司网址：</td>
							<td width="40%" align="left" colspan="3"><html-el:text property="entp_www" styleId="entp_www" size="60" maxlength="40" /></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">主营产品：</td>
							<td width="40%" align="left" colspan="3"><html-el:text property="entp_main_pds" styleId="entp_main_pds" size="60" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">销售区域：</td>
							<td width="40%" align="left" colspan="3"><html-el:text property="entp_sale_area" styleId="entp_sale_area" size="60" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap" class="title_item" align="right">客户简介：</td>
							<td width="40%" align="left" colspan="3"><html-el:textarea property="entp_inro" styleId="entp_inro" rows="5" style="width:400px;"></html-el:textarea></td>
						</tr>
						<tr>
							<th colspan="4" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>联系信息</span></th>
						</tr>
						<tr>
							<td colspan="4" align="left">
								<table>
									<tr>
										<td width="15%">职务</td>
										<td width="10%">姓名</td>
										<td width="5%">性别</td>
										<td width="10%">出生日期</td>
										<td width="20%">联系电话</td>
										<td width="20%">邮箱地址</td>
										<td width="15%">个人偏好</td>
										<td width="5%" align="center" nowrap="nowrap" id="addLinkTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" name="imgAddTr" style="vertical-align:middle;" /></td>
									</tr>
									<tr id="divTr">
										<td width="15%"><input type="text" name="r3_link_position" size="20" maxlength="20" /></td>
										<td width="10%"><input type="text" name="r3_link_real_name" size="10" maxlength="20" /></td>
										<td width="5%">
											<select name="r3_link_sex">
												<option value="">请选择...</option>
												<option value="0">男</option>
												<option value="1">女</option>
												<option value="2">未知</option>
											</select>
										</td>
										<td width="10%"><input type="text" name="r3_link_birthday" size="6" maxlength="10" readonly="readonly" onclick="new Calendar(1900, 2021, 0).show(this);" /></td>
										<td width="20%"><input type="text" name="r3_link_tel" size="20" maxlength="40" /></td>
										<td width="20%"><input type="text" name="r3_link_email" size="20" maxlength="40" /></td>
										<td width="15%"><input type="text" name="r3_link_favor" size="20" maxlength="100" /></td>
										<td width="5%" align="center"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
									</tr>
									
									<tbody id="showAddTrsTbody"></tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;<input type="hidden" name="createMmt" value="1" /></td>
							<td colspan="3">
								<html-el:button property="" value=" 保 存 " styleClass="websub" styleId="btn_submit" />
								<html-el:button property="" value=" 重 置 " styleClass="websub" styleId="btn_reset" onclick="this.form.reset();" />
								<html-el:button property="" value=" 返 回 " styleClass="websub" styleId="btn_back" onclick="history.back();" />
							</td>
						</tr>
						<tr>
							<td colspan="4" style="height: 150px;">&nbsp;</td>
						</tr>
					</table>
				</html-el:form>
			</div>
		</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	//$("#branch_area_name").change( function() {
	//	var branch_area_name = $("#branch_area_name").val();
	//	$("#handle_name").empty();

	//	if(""==branch_area_name){
	   	//	var opt = new Option( "请选择...",""); 
		//	$("#handle_name").get(0).options.add(opt);
		 //  	}
	   //	if(branch_area_name!=""){
		//   	$.ajax({
		//		type: "POST",
		//		cache: false,
		//		url: "${ctx}/manager/admin/KonkaR3Shop.do",
		//		data: "method=getHandleList&branch_area_name=" + $("#branch_area_name").val(),
		//		dataType: "json", 
		//		error: function(request, settings){
		//			alert("系统出现错误，请重新操作或联系管理员");
		//			var opt = new Option( "请选择...",""); 
		//		    $("#handle_name").get(0).options.add(opt);},
		//		success: function(data) {
		//			if (data.length >= 1) {
		//				var opt = new Option( "请选择...",""); 
		//				$("#handle_name").get(0).options.add(opt);
		//				for(var i = 0; i < data.length - 1; i++) {
		//					var opt = new Option( data[i].name,data[i].id); 
		//					$("#handle_name").get(0).options.add(opt);
		//					$("#handle_name").val("${af.map.handle_name}");
		//				}
		//			}
		//		}
		//	});
	 //  	}
//	});

	//<c:if test="${not empty af.map.branch_area_name}">
	//	$("#branch_area_name").val("${af.map.branch_area_name}");
	//	$("#branch_area_name").change();
	//</c:if>
	
	$("#area_name").attr("dataType", "Require").attr("msg", "请选择");
	$("#branch_area_name_2").attr("dataType", "Require").attr("msg", "请选择");
	$("#customer_type").attr("dataType", "Require").attr("msg", "请填写");
	$("#r3_code").attr("dataType", "Require").attr("msg", "请填写");
	//$("#handle_name").attr("dataType", "Require").attr("msg", "请选择");
	$("#customer_name").attr("dataType", "Require").attr("msg", "请填写");

	$("#entp_inro").attr("dataType", "LimitB").attr("min","0").attr("max","80").attr("msg", "最多只能填写40个汉字！");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

	$("#branch_area_name_2").change(function(){
			var dept_name = $(this).find("option:selected").text();
			$("#branch_area_name").val(dept_name);
	});
	
	// 验证R3编码是否存在
	$("#r3_code").blur(function(){
		$("#btn_submit").attr("disabled", "disabled");
		var r3_code = $("#r3_code").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#r3_code_exist_error").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#r3_code_exist_error").hide();
			return;
		}

		$("#r3_code_exist_error").hide();
		$(this).css("background-color", "#fff");
		
		$.ajax({
			type: "POST",
			url: "KonkaR3Shop.do",
			data: "method=validateR3Code&r3_code=" + $(this).val(),
			dataType: "json",
			error: function(request, settings) {
				alert("检查用户名重复失败，请稍候再次尝试。");
				$("#r3_code_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if (oper.result) {
					$("#r3_code_exist_error").show();
					$("#btn_submit").attr("disabled", "disabled");
					$("#r3_code").css("background-color", "#ddcc00");
					$("#r3_code").focus();
				} else {
					$("#r3_code_exist_error").hide();
					$("#r3_code").css("background-color", "#fff");
					$("#btn_submit").removeAttr("disabled");
				}
			}
		});
	});	

	$("#addLinkTD").click(function(){
		var tr_pd = $("#divTr").clone(true).attr("class","tr_pd");
		tr_pd.children().eq(7).attr("class","td_del");
		tr_pd.children().eq(7).children().eq(0).attr("src","${ctx}/images/x.gif");
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		
		var lastTR = $("tr:last", "#showAddTrsTbody");

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
	    }).css("cursor", "pointer");
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": ""});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": ""});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": ""});
	$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": ""});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
});

//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
