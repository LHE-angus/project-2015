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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
</head>
<body>
	<div class="oarcont">

		<div class="rtabcont2">
			<form action="${ctx}/manager/admin/KonkaMobileFightReport.do" method="post" enctype="multipart/form-data">
				<html-el:hidden property="method" styleId="method" value="save" />
				<input type="hidden" id="select_id" name="select_id" value="1"/>
				<input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
<%-- 				<input type="hidden" id="report_time" name="report_time" value="${kmf.report_time}"/> --%>
				<input type="hidden" id="id" name="id" value="${kmf.id }"/>
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >门 店:</td>
						<td nowrap="nowrap" class="title_item" >${kmf.dept_name }</td>
					</tr>
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >竞品品牌:</td>
						<td nowrap="nowrap" class="title_item" >${kmf.map.brand_name}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" width="12%" class="title_item" align="right">规格段:</td>
						<td nowrap="nowrap" class="title_item" >${kmf.map.type_name} </td>
					</tr>
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >竞品型号:</td>
						<td nowrap="nowrap" class="title_item"  >${kmf.model_id}</td>
					</tr>
					
					
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >数量:</td>
						<td><html-el:text property="sales_count" styleId="sales_count" value="${kmf.num}" onblur="cal_all_price()"></html-el:text> <span id="tiShi" style="color:red;"></span>
						</td>
					</tr>
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >单价:</td>
						<td><html-el:text property="single_price" styleId="single_price" value="${kmf.price/kmf.num }" onblur="cal_all_price()"></html-el:text>
						</td>
					</tr>
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >金额</td>
						<td><html-el:text property="sales_price" styleId="sales_price" value="${kmf.price}" onblur="cal_single_price()"></html-el:text>
						</td>
					</tr>
					
					<tr>
						<td width="12%" nowrap="nowrap" class="title_item" align="right" >备注</td>
						<td><html-el:textarea property="memo" styleId="memo" styleClass="webinput" style="width:70%;height:60px;" value="${kmf.memo }"/></td>
					</tr>
					<tr>
						<td class="title_item" align="right" nowrap="nowrap">附件:</td>
						<td>
							<div id="policydivFileHidden" style="display: none;">
								<input name="activity_policy" type="file" id="activity_policy"
									style="width: 250px;" /> <img src="../../images/x.gif"
									style="vertical-align: middle; cursor: pointer;"
									id="policyDelTr" title="删除" />
							</div>
							<div id="policydivFile">
								<input name="policy_file" type="file" id="policy_file"
									style="width: 250px;" /> <img src="../../images/+.gif"
									style="vertical-align: middle; cursor: pointer;"
									id="policyAddTr" title="再添加一个" />
							</div>
							<ol>
								<c:forEach items="${kmf.map.attachlist}" var="cur">
									<li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>
									</li>
								</c:forEach>
							</ol></td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td nowrap="nowrap" class="title_item" align="left">
							<html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" /> 
							&nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
						<td>
					</tr>
					<table>
			</form>
		</div>
	</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>

<script type="text/javascript">
	
	$(document).ready(function(){
		var newTime= new Date();
		var time =new Date("${kmf.report_time}");
		var date3=newTime.getTime()-time.getTime();
		var leave1=date3 / (3600*1000*24);
		if(leave1>2)
		{
			$("#hidden").attr("hidden",true);
			$("#sales_count").attr("readonly","readonly");
			$("#single_price").attr("readonly","readonly");
			$("#sales_price").attr("readonly","readonly");
			$("#tiShi").text("  超过48小时不能修改金额，数量！");
		}
		
	//附件
	 $("#policyAddTr").click(function (){
	       $("#policydivFileHidden").clone().find("#activity_policy").attr("name", "policy_file_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
	       $("img[id='policyDelTr']").each(function(){
	           $(this).click(function (){
	               $(this).parent().remove();
	               resizeFrameHeight();
	           });
	       });
	});
	
	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("KonkaMobileFightReport.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
		  });
		    resizeFrameHeight();
	 });
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
	
	
});

	// 数量或者单价改变计算总价
    function cal_all_price(){
		var sales_count = $("#sales_count").val();
		var single_price = $("#single_price").val();
		if((sales_count * single_price).toFixed(2) > 999999999 || -999999999 > (sales_count * single_price).toFixed(2)){
			 $.dialog.alert("计算金额超出范围！",function(){});
			return;
		}
		$("#sales_price").val((sales_count * single_price).toFixed(2));
    }

	// 总价改变计算单价
    function cal_single_price(){
		var sales_count = $("#sales_count").val();
		var sales_price = $("#sales_price").val();
		$("#single_price").val((sales_price / sales_count).toFixed(2));
    }

</script>

	<jsp:include page="/__analytics.jsp" />
</body>
</html>
