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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
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

td {
	align: center;
	nowrap: nowrap;
}

.title_item_no_bc {
	align: right;
	text-align: right;
	width: 100px;
	font-weight: bold;
	color: #74685F;
}
</style>
</head>
<body style="font-family: Microsoft Yahei;">
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
		<div class="rtabcont2">
			<!--手动添加模块 -->
			<c:if test="${empty af.map.trans_id}">
				<div class="rtabcont2">
	               <div align="left" style="font-weight: bold; color: #74685F;">手动添加订单</div>
	               <div style="margin-top:10px;">
	               <strong>订单流水号：</strong>&nbsp;&nbsp;&nbsp;
					<input name="add_by_hand" id="add_by_hand" style="width: 200px;"
						maxlength="60" /> <input type="button" value="添加" id="add" class="but2"></input>
						</div>
				</div>
			</c:if>
			<div class="tab1" style="overflow-x: auto;">
				<html-el:form  action="/admin/KonkaOrderInfoTrans.do" method="post">
					<html-el:hidden property="method" value="save" />
					<html-el:hidden property="trans_id" value="${af.map.trans_id}" />
					<html-el:hidden property="mod_id" value="205750" />
					<!--上面的详情list---begin -->

					<div align="left" style="font-weight: bold; color: #74685F;margin-bottom:10px;">已选择订购单详细</div>
					<div width="100%" style="overflow-x:scoll;">
					<table width="100%" id="fhdetails" border="0" cellspacing="0" cellpadding="0"
						class="rtable2">
						<tbody id="fhtbodydetails">
						<tr class="tabtt1">
							<td align="center" nowrap="nowrap">物流交货单</td>
							<td align="center" nowrap="nowrap">流水号</td>
							<td align="center" nowrap="nowrap">提交日期</td>
							<td align="center" nowrap="nowrap">客户名称</td>
							<td align="center" nowrap="nowrap">售达方</td>
							<td align="center" nowrap="nowrap">送达方</td>
							<td align="center" nowrap="nowrap">型号</td>
							<td align="center" nowrap="nowrap">数量</td>
							<td align="center" nowrap="nowrap">已发货数量</td>
							<td align="center" nowrap="nowrap">本次发货数量</td>
							<td align="center" nowrap="nowrap">是否发货</td>
						</tr>
						<c:if test="${not empty entityList}">
							<c:forEach var="cur" items="${entityList}" varStatus="vs">
								<tr>
									<html-el:hidden property="order_id" styleId="order_id" value="${cur.order_id}"/>
									<html-el:hidden property="ensu_id" styleId="ensu_id" value="${cur.ensu_id}"/>
									<html-el:hidden property="model_money" styleId="model_money" value="${cur.model_money}"/>
									<html-el:hidden property="model_name" styleId="model_name" value="${cur.model_name}"/>
									<html-el:hidden property="model_num" styleId="model_num" value="${cur.model_num}"/>
									<html-el:hidden property="r3_code" styleId="r3_code" value="${cur.r3_code}"/>
									<html-el:hidden property="r3_code_sdf" styleId="r3_code_sdf" value="${cur.r3_code_sdf}"/>
									<html-el:hidden property="r3_id" styleId="r3_id" value="${cur.r3_id}"/>
									<html-el:hidden property="r3_vbedl" styleId="r3_vbedl" value="${cur.r3_vbedl}"/>
									<html-el:hidden property="trade_index" styleId="trade_index" value="${cur.trade_index}" styleClass="trade_index"/>
									<html-el:hidden property="trans_recl_user" styleId="trans_recl_user" value="${cur.trans_recl_user}"/>
									<html-el:hidden property="trans_recl_user_phone" styleId="trans_recl_user_phone" value="${cur.trans_recl_user_phone}"/>
									<html-el:hidden property="trans_recl_addr" styleId="trans_recl_addr" value="${cur.trans_recl_addr}"/>
									<html-el:hidden property="add_date" styleId="add_date" value="${cur.map.save_date}"/>
									<html-el:hidden property="cust_name" styleId="cust_name" value="${cur.map.name1}"/>
									<html-el:hidden property="have_trans_num" styleId="have_trans_num" value="${cur.map.have_trans_num}"/>
									<html-el:hidden property="ag" styleId="ag" value="${cur.map.ag}"/>
									<html-el:hidden property="have_ensued_num" styleId="have_ensued_num" value="${cur.map.have_ensued_num}"/>
									
									<td align="center">${cur.r3_vbedl }</td>
									<td align="center">${cur.trade_index }</td>
									<td align="center"><fmt:formatDate value="${cur.map.save_date}" pattern="yyyy-MM-dd"/></td>
									<td align="left">${cur.map.name1}</td>
									<td align="center">${cur.map.ag}</td>
									<td align="center">${cur.r3_code_sdf }</td>
									<td align="center">${cur.model_name }</td>
									<td align="center">${cur.model_num }</td>
									<td align="center">${cur.map.have_trans_num }</td>
									<c:if test="${empty cur.trans_num}">
									<td align="center">
									<html-el:text property="trans_num" styleId="trans_num" value="0" styleClass="trans_num" style="width:60px;" maxlength="6" onfocus="setOnlyInt(this)"/></td>
									<td align="center"><input name="pks" class="pks" id="pks" type="checkbox" value="${vs.count}"/></td>
									</c:if>
									<c:if test="${not empty cur.trans_num}">
									<td align="center">
									<html-el:text property="trans_num" styleId="trans_num" value="${cur.trans_num}" styleClass="trans_num" style="width:60px;" maxlength="6" onfocus="setOnlyInt(this)"/></td>
									<td align="center"><input name="pks" class="pks" id="pks" type="checkbox" checked="checked" value="${vs.count}" /></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
						<tbody class="add_tbody"></tbody>
					</table>
					</div>
					<!--上面的详情list---end -->

					<!-- 下面填写发货司机等部分--begin -->
					<div align="left"
						style="font-weight: bold; margin-top:10px; color: #74685F; border-bottom: 1px solid #74685F;">发货信息</div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tableClass" id="fhtable2">
						<!-- 第一行 -->
						<tr>
							<td class="title_item_no_bc">发货地点：</td>
							<td colspan="3"><html-el:text property="address"
									value="${af.map.address}" styleId="address" style="width:400px;"
									styleClass="webinput"  maxlength="100"/></td>
							<td></td>
							<td></td>
						</tr>
						<!-- 第二行 -->
						<tr>
							<td class="title_item_no_bc">承运单位：</td>
							<td><html-el:text property="trans_unit"
									value="${af.map.trans_unit}" style="width:200px;"
									styleClass="webinput"  maxlength="50"/></td>
							<td class="title_item_no_bc">身份证号码：</td>
							<td><html-el:text property="link_id"
									value="${af.map.link_id}" style="width:150px;"
									styleClass="webinput" styleId="link_id"  maxlength="18"/></td>
							<td class="title_item_no_bc">起运开始时间：</td>
							<td>
							<fmt:formatDate var="trans_plan_date" value="${af.map.trans_plan_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="trans_plan_date"
									value="${trans_plan_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
						</tr>

						<!-- 第三行 -->
						<tr>
							<td class="title_item_no_bc">司机姓名：</td>
							<td><html-el:text property="link_name"
									value="${af.map.link_name}" style="width:100px;"
									styleClass="webinput" styleId="link_name"  maxlength="20"/></td>
							<td class="title_item_no_bc">承运车辆车况：</td>
							<td><html-el:text property="car_desc"
									value="${af.map.car_desc}" style="width:150px;"
									styleClass="webinput"  maxlength="50"/></td>
							<td class="title_item_no_bc">预定送达时间：</td>
							<td>
							<fmt:formatDate var="trans_arri_plan_date" value="${af.map.trans_arri_plan_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="trans_arri_plan_date"
									value="${trans_arri_plan_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
						</tr>

						<!-- 第四行 -->
						<tr>
							<td class="title_item_no_bc">联系电话(手机)：</td>
							<td><html-el:text property="link_phone"
									value="${af.map.link_phone}" style="width:100px;"
									styleClass="webinput" styleId="link_phone" maxlength="20" />
									</td>
							<td class="title_item_no_bc">约定到车时间：</td>
							<td>
							<fmt:formatDate var="prom_plan_date" value="${af.map.prom_plan_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="prom_plan_date"
									value="${prom_plan_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
							<td class="title_item_no_bc">实际送达时间：</td>
							<td>
							<fmt:formatDate var="trans_real_arri_date" value="${af.map.trans_real_arri_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="trans_real_arri_date"
									value="${trans_real_arri_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
						</tr>

						<!-- 第四行 -->
						<tr>
							<td class="title_item_no_bc">车牌号：</td>
							<td><html-el:text property="link_car_no"
									value="${af.map.link_car_no}" style="width:100px;"
									styleClass="webinput" styleId="link_car_no"  maxlength="10"  /></td>
							<td class="title_item_no_bc">实际到车时间：</td>
							<td>
							<fmt:formatDate var="prom_real_date" value="${af.map.prom_real_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="prom_real_date"
									value="${prom_real_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
							<td class="title_item_no_bc">卸车完成时间：</td>
							<td>
							<fmt:formatDate var="trans_fini_date" value="${af.map.trans_fini_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="trans_fini_date"
									value="${trans_fini_date}" readonly="true"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" /></td>
						</tr>

						<!--第五行 -->
						<tr>
							<td class="title_item_no_bc">发货时间：</td>
							<td align="left">
								<fmt:formatDate var="trans_date" value="${af.map.trans_date}" pattern="yyyy-MM-dd" />
								<html-el:text property="trans_date" readonly="true" value="${trans_date }"
									onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"
									title="点击选择日期" />
							</td>
<%-- 							<c:if test="${not empty af.map.trans_index_detail}"> --%>
<!-- 								<td class="title_item_no_bc">发货单编号：</td> -->
<%-- 								<td><font color="red">${af.map.trans_index_detail}</font></td> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${empty af.map.trans_index_detail}"> --%>
								<td></td>
								<td></td>
<%-- 							</c:if> --%>
							<td></td>
							<td></td>
						</tr>
						<!--第六行 -->
						<tr>
							<td class="title_item_no_bc">配送方式：</td>
							<td align="left"><html-el:select
 									property="trans_mode" styleId="trans_mode"
									value="${af.map.trans_mode}">
									<html-el:option value="0">自有物流</html-el:option>
									<html-el:option value="1">第三方物流</html-el:option>
									<html-el:option value="2">其他</html-el:option>
								</html-el:select></td>
							<td class="title_item_no_bc">发货快递公司：</td>
							<td align="left"><html-el:text
									property="express_name" value="${af.map.express_name}"
									style="width:100px;" styleClass="webinput"  maxlength="50"/></td>
							<td></td>
							<td></td>
						</tr>
						<!--第七行 -->
						<tr>
							<td class="title_item_no_bc">备注：</td>
							<td colspan="5"><html-el:textarea property="trans_memo"
									styleId="trans_memo" style="width:80%;" rows="2"></html-el:textarea>
								<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
							</td>
						</tr>



						<!-- 保存按钮 -->


						<tr>
							<td colspan="6" align="center"><label> 
<!-- 									<input type="button" id="btn_submit" class="but4" value="提交" />  -->
<!-- 									<input type="button" class="but5" value="返回" onclick="javascript:history.back(-2);" /> -->
									<html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 "  styleClass="but4"/>
									<html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();return false;"  styleClass="but5"/>

							</label></td>
						</tr>
					</table>
					<!-- 下面填写发货司机等部分--end -->
					<p>&nbsp;</p>	
					<p>&nbsp;</p>	
					<p>&nbsp;</p>	
					<p>&nbsp;</p>	
					<p>&nbsp;</p>	
					<p>&nbsp;</p>	
				</html-el:form>
			</div>



</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[                                       
var f = document.forms[0];
$(document).ready(function(){
	$("#link_car_no").attr("dataType" , "Require").attr("msg" , "车牌号必须填写！");
	$("#link_name").attr("dataType" , "Require").attr("msg" , "司机姓名必须填写！");
	$("#link_phone").attr("require", "true").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("#address").attr("dataType" , "Require").attr("msg" , "发货地点必须填写！");
	$("#link_id").attr("dataType" , "IdCard").attr("require", "true").attr("msg" , "请填写正确的身份证号码！");

	$("#trans_memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		 var isSubmit = Validator.Validate(this.form, 3);
		var count=0;//用来记录发货的行数
		$(".pks").each(function(){
			var trans_num = $(this).parent().parent().children('td').eq(9).children().val();
			if($(this).attr("checked")){
				count++;//选择了行就+1
				if(trans_num==0){
					alert("选择发货的必须填写发货数量");
					isSubmit=false;
					return false;
					}
				}
			});
		
		
		if(count<1){
			alert("必须选择至少一个机型进行发货");
			isSubmit=false;//一行都没选择那就不给提交
		}
        if(isSubmit){
        	$("#btn_submit").attr("disabled","true");
        	f.submit();
        }
	});
	
	//判断本次发货数量是否小于数量-已发货数量
	$(".trans_num").blur(function(){
		var $trans_num = $(this);
		var trans_num = $(this).val();
		var model_num = $(this).parent().parent().children('td').eq(7).text();
		var have_trans_num = $(this).parent().parent().children('td').eq(8).text();
		if(trans_num > (parseFloat(model_num) - parseFloat(have_trans_num))){
			alert("本次发货数量超出最大可发货数量！");
			$trans_num.val(0);
		}
	});


	
	


	
	
	$("#add").click(function(){
		var flag = true;
		var count =1;
		var trade_index = $("#add_by_hand").val();
		if(trade_index.length<1){
			alert("流水号不能为空!");
			flag = false;
			return false;
		} 
		$(".trade_index").each(function(){
			count++;
			if($(this).val() == trade_index){
				alert("流水号已存在，不能重复添加!");
				flag = false;
				return false;
			}	
		});
		if(flag){
			$.ajax({
				type: "POST",
				url: "KonkaOrderInfoTrans.do",
				data: {method : "ajaxGetAdd", "trade_index": trade_index},
				dataType: "json",
				cache:false,
				error: function(){alert("数据加载请求失败！");},
				success: function(ret){
					if(ret){
						if(null != ret.list && ret.list.length > 0){
							var html = "";
							for(var i = 0; i < ret.list.length; i++){
								var cur = ret.list[i];
								html += "<tr>";
								html += "<input type='hidden' name='order_id' value='" + cur.order_id + "'/>";
								html += "<input type='hidden' name='ensu_id' value='" + cur.ensu_id + "'/>";
								html += "<input type='hidden' name='model_money' value='" + cur.model_money + "'/>";
								html += "<input type='hidden' name='model_name' value='" + cur.model_name + "'/>";
								html += "<input type='hidden' name='model_num' value='" + cur.model_num + "'/>";
								html += "<input type='hidden' name='r3_code' value='" + cur.r3_code + "'/>";
								html += "<input type='hidden' name='r3_code_sdf' value='" + cur.r3_code_sdf + "'/>";
								html += "<input type='hidden' name='r3_id' value='" + cur.r3_id + "'/>";
								html += "<input type='hidden' name='r3_vbedl' value='" + cur.r3_vbedl + "'/>";
								html += "<input type='hidden' name='trade_index' value='" + cur.trade_index + "' class='trade_index'/>";
								html += "<input type='hidden' name='trans_recl_user' value='" + cur.trans_recl_user + "'/>";
								html += "<input type='hidden' name='trans_recl_user_phone' value='" + cur.trans_recl_user_phone + "'/>";
								html += "<input type='hidden' name='trans_recl_addr' value='" + cur.trans_recl_addr + "'/>";
								html += "<input type='hidden' name='add_date' value='" + cur.save_date + "'/>";
								html += "<input type='hidden' name='cust_name' value='" + cur.name1 + "'/>";
								html += "<input type='hidden' name='have_trans_num' value='" + cur.have_trans_num + "'/>";
								html += "<input type='hidden' name='ag' value='" + cur.ag + "'/>";
								html += "<input type='hidden' name='have_ensued_num' value='" + cur.have_ensued_num + "'/>";
								
								html += "<td align='center'>" + cur.r3_vbedl + "</td>";
								html += "<td align='center'>" + cur.trade_index + "</td>";
								html += "<td align='center'>" + cur.save_date + "</td>";
								html += "<td align='left'>" + cur.name1 + "</td>";
								html += "<td align='center'>" + cur.ag + "</td>";
								html += "<td align='center'>" + cur.r3_code_sdf + "</td>";
								html += "<td align='center'>" + cur.model_name + "</td>";
								html += "<td align='center'>" + cur.model_num + "</td>";
								html += "<td align='center'>" + cur.have_trans_num + "</td>";
								html += "<td align='center'><input type='text' name='trans_num' id='trans_num' value='0' class='trans_num' style='width:60px;'  maxlength='6' onfocus='setOnlyInt(this)'/></td>";
								html += "<td align='center'><input name='pks' id='pks' type='checkbox'  value='"+(count+i)+"' /></td>";
								html += "</tr>";
							}
							$(".add_tbody:first").clone(true).appendTo("#fhtbodydetails");
							$(".add_tbody:last").html(html);
							$("#add_by_hand").val("");
							//iframe高度自适应
							window.parent.resizeFrameHeight('mainFrame', 3);
						} else {
							alert("对不起，没有查询到你要找的订单流水号或者您要添加的订单流水号不满足发货条件！");
							$("#add_by_hand").val("");
						}
					} else {
							alert("对不起，没有查询到你要找的订单流水号或者您要添加的订单流水号不满足发货条件！");
							$("#add_by_hand").val("");
					}
				}
		   }); 
		}
	});
	
});

//正则表达式：只能输入正整数
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>