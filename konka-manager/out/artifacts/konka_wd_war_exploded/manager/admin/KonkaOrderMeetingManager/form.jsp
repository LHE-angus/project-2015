<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
  		<c:if test="${not empty no_user_tip}">
  		<div>
  		<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
	  	<html-el:form action="/admin/KonkaOrderMeetingManager" enctype="multipart/form-data">
			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="id" value="${af.map.id}" />
	      	<html-el:hidden property="queryString" styleId="queryString"/>
	      	<html-el:hidden property="file_status" styleId="file_status"/>
	      	<html-el:hidden property="meeting_sn" styleId="meeting_sn" value="${af.map.meeting_sn}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		        <tr>
		          <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>计划上报</span></th>
					<th width="35%" >&nbsp;</th>
					<th width="10%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>单据编号：</span></th>
					<th colspan="3" align="left"><span><font color="red">${af.map.meeting_sn}</font></span>
					<html-el:hidden property="meeting_sn" value="${af.map.meeting_sn}"/>
					</th>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>主题：</td>
					<td align="left" colspan="3" width="90%">
					<html-el:text property="meeting_name" styleId="meeting_name" style="width:300px;" maxlength="80"/>
					</td>
				</tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>会议类型：</td>
					<td align="left" width="50%">
					<html-el:select property="meeting_id" styleId="meeting_id">
					<html-el:option value="">请选择…</html-el:option>
					<html-el:optionsCollection name="spActivityList" label="hd_type" value="id"/>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>分公司：</td>
					<td align="left" width="40%">
					<html-el:hidden property="dept_id" value="${af.map.dept_id}"  styleId="dept_id" />
					<html-el:text property="dept_name" value="${af.map.dept_name}"  styleId="dept_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right"><font color="red">* </font>订货目标（万元）：</td>
					<td align="left" colspan="3">
					<html-el:text property="order_target" styleId="order_target" value="${af.map.order_target}" maxlength="10" style="width:100px;" onfocus="javascript:setOnlyPositiveNum(this)" />
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>召开时间：</td>
					<td align="left">
					<fmt:formatDate var="open_date" value="${af.map.open_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="open_date" styleId="open_date" value="${open_date}" maxlength="10" readonly="true"  style="width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
					</td>
					<td class="title_item" align="right" nowrap="nowrap">会议状态：</td>
					<td align="left">
					<html-el:select property="meeting_status" styleId="meeting_status">
					<html-el:option value="0">未上报</html-el:option>
					<html-el:option value="30">进行中</html-el:option>
					</html-el:select>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>负责人：</td>
					<td align="left">
					<html-el:hidden property="charge_person" />
					<html-el:select property="charge_person_job_id" styleId="charge_person_job_id">
						<c:forEach items="${user_list}" var="cur">
						<html-el:option value="${cur.job_id}">${cur.department}->${cur.user_name}</html-el:option>
						</c:forEach>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>联系电话：</td>
					<td align="left">
					<html-el:text property="charge_person_tel" maxlength="30" styleId="charge_person_tel" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">上报人：</td>
					<td align="left">
					<html-el:hidden property="report_user_job_id" />
					<html-el:text property="report_user_name" value="${af.map.report_user_name }" styleId="report_user_name" readonly="true" maxlength="30" style="width:200px;"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">创建日期：</td>
					<td align="left">
					<fmt:formatDate var="meeting_date" value="${af.map.add_meeting_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_meeting_date" styleId="add_meeting_date" value="${meeting_date}" readonly="true" maxlength="30" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>地址：</td>
					<td align="left" colspan="3" style="width:90%">
					<html-el:text property="hd_addr" styleId="hd_addr" maxlength="50" style="width:400px;"/>
					</td>
				</tr>
		        <tr>
		          <td class="title_item" align="right" nowrap="nowrap">参与客户：</td>
		          <td colspan="3">
		            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		              <tr class="title_top">
		                <td width="10%" nowrap="nowrap">R3编码</td>
		                <td width="30%">客户名称</td>
		                <td width="10%" nowrap="nowrap">客户类型</td>
		                <td width="10%" nowrap="nowrap">业务员</td>
		                <td width="10%" nowrap="nowrap">经营部</td>
		                <td width="5%" align="center" style="cursor:pointer;" title="添加" onclick="getKonkaR3MmtMatch();">
		                <img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/>
		                </td>
		              </tr>
		              
		              
		              <tr id="trHidden" style="display:none;">
		                <td align="center">
		                  <html-el:text property="r3_code" styleId="r3_code" readonly="" styleClass="webinput_r3code" />
		                </td>
		                <td align="center">
		                <html-el:text property="customer" styleId="customer" readonly="true" styleClass="webinput good_count" />
		                </td>
		                <td align="center">
		                <html-el:text property="c_name" styleId="c_name" readonly="true" styleClass="webinput" />
		                <html-el:hidden property="c_type" styleId="c_type" />
		                </td>
		                <td align="center">
		                <html-el:text property="ywy_name" styleId="ywy_name" readonly="true" styleClass="webinput" />
		                <html-el:hidden property="ywy_job_id" styleId="ywy_job_id" />
		                </td>
		                <td align="center">
		                  <html-el:text property="jyb_name" styleId="jyb_name" readonly="true" styleClass="webinput" />
		                  <html-el:hidden property="jyb_id" styleId="jyb_id" />
		                </td>
		                <td align="center" title="删除" ><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
		              </tr>
		             
		              <tbody id="tbodyContent">
			               <c:forEach items="${af.map.orderMeetingCustomerList}" var="cur">
				              <tr id="trHidden" style="">
				              	<td align="center">
				                  <html-el:text property="r3_code" styleId="${cur.r3_code}" value="${cur.r3_code}" readonly="true" styleClass="webinput_r3code" />
				                </td>
				                <td align="center">
				                <html-el:text property="customer_${cur.r3_code}" styleId="customer_${cur.r3_code}" value="${cur.customer}" readonly="true" style="width:99%;" styleClass="webinput good_count" />
				                </td>
				                <td align="center">
				                <html-el:text property="c_name_${cur.r3_code}" styleId="c_name_${cur.r3_code}" value="${cur.c_name}" readonly="true" styleClass="webinput" />
				                <html-el:hidden property="c_type_${cur.r3_code}" styleId="c_type_${cur.r3_code}" value="${cur.c_type}" />
				                </td>
				                <td align="center">
				                <html-el:text property="ywy_name_${cur.r3_code}" styleId="ywy_name_${cur.r3_code}" value="${cur.ywy_nmae}" readonly="true" styleClass="webinput" />
				                <html-el:hidden property="ywy_job_id_${cur.r3_code}" styleId="ywy_job_id_${cur.r3_code}" value="${cur.ywy_job_id}" />
				                </td>
				                <td align="center">
				                  <html-el:text property="jyb_name_${cur.r3_code}" styleId="jyb_name_${cur.r3_code}" value="${cur.jyb_name}" readonly="true" styleClass="webinput" />
				                  <html-el:hidden property="jyb_id_${cur.r3_code}" styleId="jyb_id_${cur.r3_code}" value="${cur.jyb_id}" />
				                </td>
				                <td align="center" title="删除" style="cursor: pointer;"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
				              </tr>
			              
			              </c:forEach>
		              </tbody>
		            </table>
		          </td>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap">会议备注：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="meeting_memo" styleId="meeting_memo" cols="5" style="width:600px;height:100px;"></html-el:textarea>
					</td>
				</tr>
				</table>
		    
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		    <tr>
	          <td colspan="4" align="center">
	            <input type="button" name="temp_save" class="bgButtonSave" value="保存" id="btn_save"/>
	            <input class="bgButtonBack" type="button" name="return" value="返回" id="btn_reset" onclick="history.back();"/>
	            <br/>
		          <div style="height: 50px" id="process_tips">&nbsp;</div>
	          </td>
	        </tr>
	        </table>
		</html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>

<script type="text/javascript">//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
	$("#meeting_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#meeting_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#order_target").attr("Require",true).attr("dataType", "Double").attr("msg", "请正确填写，只能为数字");//shu
  	$("#open_date").attr("dataType", "Require").attr("msg", "请填写");
	$("#hd_addr").attr("dataType", "Require").attr("msg", "请填写");
	$("#charge_person_job_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#charge_person_tel").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("#meeting_photo" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
	$("#photo_file" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
  	$("#meeting_memo").attr("datatype", "Limit"  ).attr("max", "200").attr("min","0").attr("msg", "会议备注必须在200个字之内");
  	$("#meeting_memo").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	  	//begin 转化全角为半角
         $("#meeting_memo").val($.trim(DBC2SBC(this.value)));
		//end 
	});

  	$("#charge_person_tel").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	  	//begin 转化全角为半角
         $("#charge_person_tel").val($.trim(DBC2SBC(this.value)));
		//end 
	});
  	
  	
  	
	 var lastTR = $("tr", "#tbodyContent");
	$("td:last", lastTR).click(function (){
	 	$(this).parent().remove();
	 }).css("cursor", "pointer");
 

 
 $("#btn_submit").click(function() {
	 if(confirm("点击提交后无法修改，如果您不想提交，可以点击保存。您确定要提交吗？")) {
		if(Validator.Validate(this.form, 3)){
		 $("#file_status").val("0");
		 this.form.submit();
		 
		$('#process_tips').html('保存正在执行，请耐心多等它一会！').css('color','red');
		
		$(this).attr('disabled','disabled');
		$('#btn_save').attr('disabled','disabled');
		$('#btn_reset').attr('disabled','disabled');
		
		resizeFrameHeight(2);
			
		}
	 }
 });
 
 $("#btn_save").click(function() {
	 if(Validator.Validate(this.form, 3)){
		$("#file_status").val("1");
		 $("#btn_save").attr("disabled", "true");
		this.form.submit();
		
		$('#process_tips').html('保存正在执行，请耐心多等它一会！').css('color','red');
		
		$(this).attr('disabled','disabled');
		$('#btn_submit').attr('disabled','disabled');
		$('#btn_reset').attr('disabled','disabled');
		
		resizeFrameHeight(2);
		
	 }
 });
 
	//可查询选择框
	$('#charge_person_job_id').select2({
	    allowClear: true
	});
		
 
 $("#meeting_id").change(function() {	
 	var meeting_id = $("#meeting_id").val();
	$.ajax({
		type: "POST",
		url: "KonkaOrderMeetingManager.do",
		data: "method=addSdSail&meeting_id=" + meeting_id,
		dataType: "json",
		error: function(request, settings) {
			alert("检查失败，请稍候再次尝试。");
		},
		success: function(oper) {
			$("tr", "#tbodyOrder").each(function (){
		    	$(this).remove();
		    });
			for(var x in oper){
				if(oper[x].type) {
					$("#orderHidden").clone().appendTo($("#tbodyOrder")).show();
			    	var lastTR = $("tr:last", "#tbodyOrder");
					resizeFrameHeight();
			    	var md_type = $("#md_type", lastTR);//R3客户管理
			    	var md_name = $("#md_name", lastTR);//隐藏域经营部ID
			    	var md_money = $("#md_money", lastTR);
			    	var md_num = $("#md_num", lastTR);
			    	var md_memo = $("#md_memo", lastTR); 
			    	
			    	md_name.attr("id",oper[x].md_name);
			    	md_type.attr("id","md_type" + oper[x].md_name);
			    	md_money.attr("id","md_money" + oper[x].md_name);
			    	md_num.attr("id","md_num" + oper[x].md_name);
			    	md_memo.attr("id","md_memo" + oper[x].md_name);
			    	
			    	md_type.attr("name","md_type" + oper[x].md_name);
			    	md_money.attr("name","md_money" + oper[x].md_name);
			    	md_num.attr("name","md_num" + oper[x].md_name);
			    	md_memo.attr("name","md_memo" + oper[x].md_name);
			    	
			    	md_type.attr("value",oper[x].type);
			    	md_name.attr("value",oper[x].md_name);
			    	md_money.attr("value",0);
			    	md_num.attr("value",0);
			    	
			    	$("td:last", lastTR).click(function (){
				    	$(this).parent().remove();
				    }).css("cursor", "pointer");
				}
			}
		}
	});
 });
 
 resizeFrameHeight();
});//ready end
	
function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function getKonkaR3MmtMatch() {
	var webinput_r3codes = "";
	$(".webinput_r3code").each(function(){
		webinput_r3codes = webinput_r3codes + this.value + ",";
	});
	
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaOrderMeetingManager.do?method=orderMeetingList&azaz=" + Math.random(),window,"dialogWidth:630px;status:no;dialogHeight:400px");
	if (returnValue != null && returnValue.ids != "") {
		//前一次操作返回的值  + 当前操作返回的值 = 当前的值
	    
	    var r3_value_array = new Array();
	    var r3_code = new Array();
	
	    r3_value_array = returnValue.ids.split(",");
	
		for(var i = 0; i < r3_value_array.length; i++){
			var value_array = r3_value_array[i].split("_");
			if(webinput_r3codes.indexOf(value_array[7])==-1){

			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	resizeFrameHeight();
	    	var r3_code_va = $("#r3_code", lastTR);//R3客户管理
	    	var l4_dept_id = $("#jyb_id", lastTR);//隐藏域经营部ID
	    	var l4_dept_name = $("#jyb_name", lastTR);//经营部
			var ywy_user_name = $("#ywy_name", lastTR);//业务员
			var customer_name = $("#customer", lastTR);//客户名称
			var customer_type = $("#c_type", lastTR);//隐藏域 客户类型ID
			var customer_type_name = $("#c_name", lastTR);//客户类型
			var job_id = $("#ywy_job_id", lastTR);//隐藏域 业务员岗位ID
			
			
			l4_dept_id.attr("value", value_array[0]);
			l4_dept_name.attr("value", value_array[1]);
			ywy_user_name.attr("value", value_array[2]);
			customer_name.attr("value", value_array[3]);
			customer_type.attr("value", value_array[4]);
			customer_type_name.attr("value", value_array[5]);
			job_id.attr("value", value_array[6]);
			r3_code_va.attr("value", value_array[7]);
	      	
	      	r3_code_va.attr("id",value_array[7]);//改变id
	    	l4_dept_id.attr("id","jyb_id_" + value_array[7]);//隐藏域经营部ID
	    	l4_dept_name.attr("id","jyb_name_" + value_array[7]);//经营部
			ywy_user_name.attr("id","ywy_name_" + value_array[7]);//业务员
			customer_name.attr("id","customer_" + value_array[7]);//客户名称
			customer_type.attr("id","c_type_" + value_array[7]);//隐藏域 客户类型ID
			customer_type_name.attr("id","c_name_" + value_array[7]);//客户类型
			job_id.attr("id","ywy_job_id_" + value_array[7]);//隐藏域 业务员岗位ID
			
	    	l4_dept_id.attr("name","jyb_id_" + value_array[7]);//隐藏域经营部ID
	    	l4_dept_name.attr("name","jyb_name_" + value_array[7]);//经营部
			ywy_user_name.attr("name","ywy_name_" + value_array[7]);//业务员
			customer_name.attr("name","customer_" + value_array[7]);//客户名称
			customer_type.attr("name","c_type_" + value_array[7]);//隐藏域 客户类型ID
			customer_type_name.attr("name","c_name_" + value_array[7]);//客户类型
			job_id.attr("name","ywy_job_id_" + value_array[7]);//隐藏域 业务员岗位ID

			//删除操作，影响弹出页面的返回值
			$("td:last", lastTR).click(function (){
		    	$(this).parent().remove();
		    }).css("cursor", "pointer");
			}
		}
		resizeFrameHeight(0);
	}
}

//正则表达式：只能输入数字
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
//正则表达式：只能输入数字（不包括负数）
function setOnlyPositiveNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}

function DBC2SBC(str)
{
 var result = '';
 for (i=0 ; i<str.length; i++)
 {
  code = str.charCodeAt(i);//获取当前字符的unicode编码
  if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已及各种字符
  {
   result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  }else if (code == 12288)//空格
  {
   result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  }else
  {
   result += str.charAt(i);
  }
 }
 return result;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>