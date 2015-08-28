<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>${naviString}</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body style="font-family: Microsoft Yahei;">
		<div class="rtabcont2">
			
				<html-el:form  action="KonkaOrderInfoTrans.do" method="post">
					<html-el:hidden property="method" value="confirmSave" />
					<html-el:hidden property="mod_id" styleId="mod_id" value="${mod_id }"/>
					<html-el:hidden property="trans_id" value="${af.map.trans_id}" />
					<!--上面的详情list---begin -->

					
					
					  <c:if test="${not empty entityList}">
							<c:forEach var="cur" items="${entityList}" varStatus="vs">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
								
								 <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">已选择订购单详细</th>
        </tr>
									<tr >
									<html-el:hidden property="ensu_id" styleId="ensu_id" value="${cur.ensu_id}"/>
									<td class="title_item" align="right" nowrap="nowrap">物流交货单号</td><td nowrap="nowrap" align="left">${cur.r3_vbedl }</td>		
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">订单流水号</td><td nowrap="nowrap" align="left">${cur.trade_index }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">发货单号</td><td nowrap="nowrap" align="left"><div class="div_trans_index_detail">${cur.trans_index_detail }</div></td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">提交日期</td><td nowrap="nowrap" align="left"><fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd"/></td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">客户名称</td><td nowrap="nowrap" align="left">${cur.map.customer_name }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">型号</td><td nowrap="nowrap" align="left">${cur.model_name }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">数量</td><td nowrap="nowrap" align="left">${cur.model_num }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">已发货数量</td><td nowrap="nowrap" align="left">${cur.trans_num }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">已签收数量</td><td nowrap="nowrap" align="left">${empty cur.trans_ensured_num?0:cur.trans_ensured_num }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">未签收数量 <input type="hidden" name="trans_unensu_num" value="${cur.trans_num-cur.trans_ensured_num }"/></td><td nowrap="nowrap" align="left">${cur.trans_num-cur.trans_ensured_num }</td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">本次签收数量<font color="red">*</font></td><td nowrap="nowrap" align="left"><html-el:text property="trans_ensu_num" styleId="trans_ensu_num" styleClass="trans_ensu_num" onfocus="setOnlyInt(this)"/></td>
									</tr>
									<tr >
									<td class="title_item" align="right" nowrap="nowrap">签收说明</td><td nowrap="nowrap" align="left"><html-el:text property="trans_ensu_desc" styleId="trans_ensu_desc" styleClass="webinput" maxlength="200"/></td>
									</tr>
								</table>
							</c:forEach>
						</c:if>
					
					<!--上面的详情list---end -->
					<table id="fhdetails" border="0" cellspacing="0" cellpadding="0" class="rtab2">
					<tr >
						<td class="title_item" align="right" nowrap="nowrap">签收人姓名</td>
						<td width="92%"><html-el:text property="trans_ensu_user" styleId="trans_ensu_user" styleClass="webinput" maxlength="60"/></td>
					</tr>
					<tr >
						<td class="title_item" align="right" nowrap="nowrap">联系电话</td>
						<td><html-el:text property="trans_ensu_user_phone" styleId="trans_ensu_user_phone" styleClass="webinput" maxlength="30"/></td>
					</tr>
					<tr >
						<td class="title_item" align="right" nowrap="nowrap">收货时间</td>
						<td><html-el:text property="trans_ensu_date" styleId="trans_ensu_date" value="${trans_ensu_date}" readonly="true" 
									style="cursor:pointer;text-align:center;width:80px;"/></td>
					</tr>
					<tr >
						<td>&nbsp;</td>
						<td align="center">
							<input type="button" id="btn_submit" class="but4" value="提交" /> 
							<input type="button" id="btn_back" class="but5" value="返回" onclick="javascript:history.back()"/> 
						</td>
					</tr>
					</table>
				</html-el:form>
			
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
<script type="text/javascript">//<![CDATA[                                       
var f = document.forms[0];
$(document).ready(function(){
	$("input[name='trans_ensu_num']").attr("dataType", "Require").attr("msg", "请填写！");
	$("#trans_ensu_user_phone").attr("require", "true").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("#trans_ensu_user").attr("dataType", "Require").attr("msg", "请填写签收人姓名！");
	$("#trans_ensu_date").attr("dataType", "Require").attr("msg", "请填写收货时间！");

	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		if (Validator.Validate(this.form, 3)){
			$("#btn_submit").attr("disabled","true");
			f.submit();
		}
	});
	
	//判断本次签收数量是否小于未签收数量
	$(".trans_ensu_num").blur(function(){
		var $trans_ensu_num = $(this);
		var cur_input_ensu_num = $(this).val();
		var ensured_num = $(this).parent().parent().parent().children('tr').eq(9).children('td').eq(1).text();
		if(parseInt(cur_input_ensu_num) > parseInt(ensured_num)){
			alert("本次签收数量大于未签收数量！");
			$trans_ensu_num.val(0);
		}
	});
	
});

function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>