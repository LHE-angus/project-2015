<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!-- <link href="${ctx}/scripts/jquery-ui/themes/redmond/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" /> -->
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
<!--<style type="text/css">-->
<!--   html { overflow:hidden;} -->
<!--</style>-->
</head>
<body>
<div class="oarcont" style="position:relative;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
	  <html-el:form action="/zmd/KonkaXxZmdPosAllocation">
		  <html-el:hidden property="method" value="save" />
		  <html-el:hidden property="mod_id" />
		  <html-el:hidden property="dept_id" value="${konkaDept.dept_id}" />
		  <html-el:hidden property="acc_id" value="${af.map.acc_id}" />
		  <html-el:hidden property="queryString" />
		  <table id="main_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
		  	<tr>
		  		<td colspan="2" style="font-weight:bold;font-size:18px;height:40px;text-align:center;">专卖店POS机分配</td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right">分公司：</td>
		  		<td align="left" style="padding-bottom:5px;">${konkaDept.dept_desc}</td>
		  	</tr>
		  	<tr>
		  		<td class="title_item" nowrap="nowrap" align="right" ><strong>专卖店：</strong></td>
	            <td align="left" style="padding-bottom:5px;">
	            	<html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
	            		<html-el:option value="">=请选择=</html-el:option>
	            		<c:forEach items="${zmdList}" var="cur">
	            			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
	            		</c:forEach>
	            	</html-el:select>
	            </td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>POS编号：</strong></td>
	            <td width="85%" align="left" style="padding-bottom:5px;">
	            	<html-el:text property="pos_sn" styleId="pos_sn" maxlength="20" size="12"></html-el:text>
	            	<font color="red">*</font> <span style="" id="s_after"></span> &nbsp;<span id="loading" style="display:none;"><img src="images/loading.gif" style="vertical-align:middle; margin: 2px;" />正在处理...</span>
	            </td>
		  	</tr>
<!--		  	<tr>-->
<!--		  		<td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>申请原因：</strong></td>-->
<!--	            <td width="85%" align="left" style="padding-bottom:5px;">-->
<!--	            	<html-el:textarea property="apply_reason" styleId="apply_reason" cols="70" rows="3"></html-el:textarea>-->
<!--	            	<div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>-->
<!--	            </td>-->
<!--		  	</tr>-->
		  	<tr>
		  		<td align="center" colspan="2">
		  			<input class="but4" type="button" name="Submit4" value="保存 " id="btn_submit" />
	            	<input class="but3" type="reset" value="重填" id="reset" />
	            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
		  		</td>
		  	</tr>
		  </table>
	  </html-el:form>
  </div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:50%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:50%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在提交申请，请稍等...</span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#zmd_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200
		
	}).multiselectfilter();

	$("#money").focus(setOnlyNum1);

	$("#zmd_id").attr("dataType", "Require").attr("msg", "请选择专卖店！");
	$("#pos_sn").attr("dataType", "Require").attr("msg", "请填写POSb编号！");
	$("#apply_reason").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content_1").slideUp("normal");
	});

	$("#pos_sn").blur(function(){
		if ("" != $(this).val()) {
			$("#loading").ajaxStart(function(){$("#btn_submit").attr("disabled", "true");$(this).show();});	
			$("#loading").ajaxStop(function(){$(this).hide();});
			$("#tip").remove();	
			$.ajax({
				type: "POST" , 
		        url: 'KonkaXxZmdPosAllocation.do' , 
		        data: "method=validatePos&zmd_id=${af.map.zmd_id}&pos_sn=" + $(this).val() + "&n=" + Math.random(), 
		        dataType: "json" , 
		        async: false, 
		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
		        success: function (result) {
			        if (result == 1) {//可以使用
			        	$("#s_after").after('<span id="tip" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该POS编号可用。<\/span>'); 
						$("#btn_submit").attr("disabled", "");
					} else if (result == 2) {//已存在
						$("#s_after").after('<span id="tip" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该POS编号已存在！<\/span>'); 
						$("#btn_submit").attr("disabled", "true");
					}
		        }
			});
		}
	});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
				$("#message_tip").show();
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});

function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]>--></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>