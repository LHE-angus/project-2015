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
  	<div class="rtabcont1">
  		<c:if test="${not empty no_user_tip}">
  		<div>
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
	  	<html-el:form action="/admin/KonkaSpActivityManager" enctype="multipart/form-data">
			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.tradeIndex}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="id" styleId="id" value="${af.map.id}"/>
	      	<html-el:hidden property="queryString" />
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		        <tr>
		          <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>计划上报</span></th>
					<th width="35%" >&nbsp;</th>
					<th width="10%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>单据编号：</span></th>
					<th colspan="3" align="left"><span><font color="red"></font><font color="red">${af.map.sp_sn}</font></span>
					<html-el:hidden property="sp_sn" value="${af.map.sp_sn}"/>
					</th>
		        </tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>主题：</td>
					<td align="left" colspan="4" width="90%">
					<html-el:text property="sp_name" styleId="sp_name" readonly="true" maxlength="50" style="width:300px;"/>
					</td>
				</tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>活动类型：</td>
					<td align="left" width="40%">
					<html-el:select property="hd_id" disabled="true" styleId="hd_id"  style="background-color:#fff;">
					<html-el:option value="">请选择…</html-el:option>
					<html-el:optionsCollection name="spActivityList" label="hd_type" value="id"/>
					</html-el:select>
					</td>
					<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>活动状态：</td>
					<td width="50%">
					<html-el:select  property="hd_status" styleId="hd_status">
						<html-el:option value="30">进行中</html-el:option>
					</html-el:select>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>上报人：</td>
					<td align="left">
					<html-el:hidden property="report_user_job_id" />
					<html-el:text property="report_user_name" value="${af.map.report_user_name}" styleId="report_user_name" readonly="true"  maxlength="20" style="width:200px;"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">创建日期：</td>
					<td align="left">
					<fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_date" size="40" maxlength="30" styleId="add_date" value="${add_date}" readonly="true" style="width:80px;"/>
					</td>
				</tr>
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>主要客户：</td>
					<td align="left">
					<html-el:text property="main_customer" styleId="main_customer" style="width:200px;" maxlength="30" readonly="true"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>主要R3编码：</td>
					<td align="left" width="40%">
					<html-el:text property="main_r3_code" styleId="main_r3_code" style="width:200px;" maxlength="30" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap">其他客户：</td>
					<td align="left">
					<html-el:text property="other_customer" styleId="other_customer" style="width:200px;" maxlength="60" readonly="true"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">其他R3编码：</td>
					<td align="left" width="40%">
					<html-el:text property="other_r3_code" styleId="other_r3_code" style="width:200px;" maxlength="60" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>活动目标：</td>
					<td align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
							<tr>
								<td class="title_item" align="right" nowrap="nowrap" width="20%"><font color="red">* </font>任务金额(万元):</td>
								<td align="left" width="80%"><html-el:text property="task_money" styleId="task_money" style="width:100px;" maxlength="10" readonly="true"/></td>
							</tr>
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>任务销量(台):</td>
								<td align="left"><html-el:text property="task_sail_num" styleId="task_sail_num" style="width:100px;" maxlength="10" readonly="true"/></td>
							</tr>
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>活动渠道排名:</td>
								<td align="left"><html-el:text property="activity_ranking" styleId="activity_ranking" style="width:100px;" maxlength="10" readonly="true"/></td>
							</tr>
						</table>
					</td>
					<td nowrap="nowrap" class="title_item" align="left" colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>准备期:</td>
								<td align="center"><fmt:formatDate value="${af.map.zb_s_date}" pattern="yyyy-MM-dd" var="_zb_s_date" />
		          <input name="zb_s_date" id="zb_s_date" size="12" value="${_zb_s_date}" readonly="readonly" class="Wdate" type="text" style="width:80px;"/></td>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>至：</td>
								<td align="center"><fmt:formatDate value="${af.map.zb_e_date}" pattern="yyyy-MM-dd" var="_zb_e_date" />
		          <input name="zb_e_date" id="zb_e_date" size="12" value="${_zb_e_date}" readonly="readonly" class="Wdate" type="text" style="width:80px;"/></td>
							</tr>
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>爆发期:</td>
								<td align="center"><fmt:formatDate value="${af.map.bf_s_date}" pattern="yyyy-MM-dd" var="_bf_s_date" />
		          <input name="bf_s_date" id="bf_s_date" size="12" value="${_bf_s_date}" readonly="readonly" class="Wdate" type="text"  style="width:80px;"/></td>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>至：</td>
								<td align="center"><fmt:formatDate value="${af.map.bf_e_date}" pattern="yyyy-MM-dd" var="_bf_e_date" />
		          <input name="bf_e_date" id="bf_e_date" size="12" value="${_bf_e_date}" readonly="readonly" class="Wdate" type="text" style="width:80px;"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>负责人：</td>
					<td align="left">
					<html-el:text property="charge_person" styleId="charge_person" style="width:40%;" readonly="true"/>
					<html-el:hidden property="charge_person_id" />
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>联系电话：</td>
					<td align="left">
					<html-el:text property="charge_person_tel" maxlength="30" readonly="true" styleId="charge_person_tel" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>分公司：</td>
					<td align="left" width="40%">
					<html-el:hidden property="dept_id" value="${dept_fgs.dept_id}"  styleId="dept_id" />
					<html-el:text property="dept_name" value="${dept_fgs.dept_name}"  styleId="dept_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td> 
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>经营部：</td>
					<td align="left">
					<html-el:hidden property="jyb_id"  styleId="jyb_id" />
					<html-el:text property="jyb_name"  styleId="jyb_name" style="width:200px;" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>客户类型：</td>
					<td align="left" width="40%">
					<html-el:hidden property="par_customer_type"  styleId="par_customer_type" />
					<html-el:text property="par_customer_type_name"  styleId="par_customer_type_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td> 
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>业务员：</td>
					<td align="left">
					<html-el:hidden property="ywy_id"  styleId="ywy_id" />
					<html-el:text property="ywy_name" maxlength="30" styleId="ywy_name" readonly="true"  style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>细分类型：</td>
					<td align="left" width="40%" colspan="4">
					<html-el:hidden property="customer_type"  styleId="customer_type" />
					<html-el:text property="customer_type_name"  styleId="customer_type_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td> 
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">地址：</td>
					<td align="left" colspan="4" style="width:90%">
					<html-el:text property="hd_addr" styleId="hd_addr" readonly="true" maxlength="60" style="width:400px;"/>
					</td>
				</tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap">活动备注：</td>
					<td align="left" colspan="4">
					<html-el:textarea property="hd_remark" styleId="hd_remark" readonly="true" style="resize:none;" cols="50" rows="5"></html-el:textarea>
					</td>
				</tr>
				
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">活动方案：</td>
					<td colspan="3" width="95%;">
					<div id="policydivFileHidden" style="display: none;" >
	                <input name="activity_policy" type="file" id="activity_policy" disabled="disabled" style="width: 250px;" />
	                </div>
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'policy_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;</li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
		        <tr>
					<th width="10%" align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					过程管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">预约数：</td>
					<td align="left" colspan=""><html-el:text property="yy_num" maxlength="16" styleId="yy_num" style="width:100px;"/></td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>维护日期：</td>
					<td align="left">
					<fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_date" maxlength="30" styleId="add_date" value="${add_date}" readonly="true" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>活动准备情况说明 ：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="file_remark" styleId="file_remark" style="resize:none;"  cols="50" rows="5"></html-el:textarea>
					<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">活动照片：</td>
					<td colspan="3" width="95%">
					<div id="photodivFileHidden" style="display: none;" >
	                <input name="hd_photo" type="file" id="hd_photo" style="width: 250px;" />
	                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="photoDelTr" title="删除"/>
	                </div>
	              	<div id="photodivFile">
	                <input name="hd_photo_file_show" type="file" id="hd_photo_file_show" style="width: 250px;" />
	                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="photoAddTr" title="再添加一个" />
	                </div>
	            	<ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'hd_photo_file_show')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
		        <tr>
		          <td colspan="4" align="center">
		         
		            <input type="button" name="temp_save" class="bgButtonSave" value="保存" id="btn_temp_save"/>
		            <input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();"/>
		            <br/>
		            <div style="height: 50px">&nbsp;</div>
		          </td>
		        </tr>
		    </table>
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

	$("#sp_name").attr("dataType", "Require").attr("msg", "请填写主题");
	$("#hd_id").attr("dataType", "Require").attr("msg", "请选择活动类型");
	$("#main_customer").attr("dataType", "Require").attr("msg", "请选择主要客户");
	//$("#other_customer").attr("dataType", "Require").attr("msg", "请选择其他客户");
	$("#zb_s_date").attr("dataType", "Require").attr("msg", "请选择准备期起");
	$("#zb_e_date").attr("dataType", "Require").attr("msg", "请选择准备期止");
	$("#bf_s_date").attr("dataType", "Require").attr("msg", "请选择爆发期起");
	$("#bf_e_date").attr("dataType", "Require").attr("msg", "请选择爆发期止");
	$("#charge_person").attr("dataType", "Require").attr("msg", "请选择负责人");
	$("#charge_person_tel").attr("dataType", "Require").attr("msg", "请填写联系电话");
	$("#file_remark").attr("dataType", "Require").attr("msg", "请填写活动准备情况说明 ");
	$("#file_remark").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	$("#total_sail_money").attr("Require",true).attr("dataType", "Double").attr("msg", "请填写销售总金额,且必须为数字");
	$("#total_sail_num").attr("dataType", "Require").attr("msg", "请填写销售总数量");
	//$("#yy_num").attr("dataType" , "Number").attr("msg", "预约数必须为数字");
	$("#yy_num").focus(setOnlyNum2);
	$("#hd_photo").attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png")
	$("#hd_photo_file_show").attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png")
	//$("#charge_person_tel").attr("datatype", "Phone"  ).attr("require", "true").attr("msg", "请正确填写联系电话");
	
	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("KonkaSpActivityManager.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
		  });

		    resizeFrameHeight();
		 });
		
 	$("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        resizeFrameHeight();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
	 });
 $("#policyAddTr").click(function (){
        $("#policydivFileHidden").clone().find("#activity_policy").attr("name", "policyfile_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
        resizeFrameHeight();
        $("img[id='policyDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
 });
 $("#photoAddTr").click(function (){
        $("#photodivFileHidden").clone().find("#hd_photo").attr("name", "hd_photo_file_show_" + new Date().getTime()).end().appendTo($("#photodivFile")).show();
        resizeFrameHeight();
        $("img[id='photoDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
 }); 
	
 $("#hd_id").change(function(){
	  var hd_id = $(this).val();
	  $.ajax({
		  type:"POST",
		  url:"KonkaSpActivityManager.do",
		  data:"method=addSdSail&hd_id=" + hd_id,
		  dataType:"json",
		  error:function(request,settings){
			  alert("检查失败，请稍后再尝试");
		  },
		  success: function(oper) {
				for(var x in oper){
					if(oper[x].md_type) {		
						$("#orderHidden").clone().appendTo($("#tbodyOrder")).show();
						resizeFrameHeight();
				    	var lastTR = $("tr:last", "#tbodyOrder");
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
				    	
				    	bindJhCountAndJhPrice(md_num, md_money, lastTR);
				    	
				    	$("td:last", lastTR).click(function (){
					    	$(this).parent().remove();
					    }).css("cursor", "pointer");
					}
				}
			}
	  });
	});
 
 $("#btn_submit").click(function() {
	 if(Validator.Validate(this.form, 3)){
		 if(confirm("点击提交后无法修改,您确定要提交吗？")) {
				if(Validator.Validate(this.form, 3)){
				 $("#file_status").val("2");
				this.form.submit();
				}
			 }
	 }
 });
 
 $("#btn_temp_save").click(function() {
	 if(Validator.Validate(this.form, 3)){
		$("#file_status").val("1");
		this.form.submit();
	 }
 });
 
});//ready end
	/*处理返回值数据 ===start===*/

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function getKonkaR3List() { 
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaSpActivityManager.do?method=spActivityManagerForR3List&azaz=" + Math.random(),window,"dialogWidth:650px;status:no;dialogHeight:390px");
    
	if (returnValue != null && returnValue.ids != "") {
	    var r3_code_array = new Array();
	    var r3_code_name_array = new Array();
	
	    r3_code_array = returnValue.ids.split("_");
	    r3_code_name_array = returnValue.names.split("_");

	    /*  var r3_code_va = $("#r3_code", lastTR);//R3客户管理
    	var l4_dept_id = $("#jyb_id", lastTR);//隐藏域经营部ID
    	var l4_dept_name = $("#jyb_name", lastTR);//经营部
		var ywy_user_name = $("#ywy_name", lastTR);//业务员
		var customer_name = $("#customer", lastTR);//客户名称
		var customer_type = $("#c_type", lastTR);//隐藏域 客户类型ID
		var customer_type_name = $("#c_name", lastTR);//客户类型
		var job_id = $("#ywy_job_id", lastTR);//隐藏域 业务员岗位ID */
	
		for(var i = 0; i < r3_code_array.length; i++){
			var customer = new Array();
			customer = r3_code_name_array[i].split(",");
			$("#main_customer").attr("value",customer[customer.length-1]);
			$("#main_r3_code").attr("value",r3_code_array[3]);
			
			$("#jyb_id").attr("value",r3_code_array[0]);
			$("#jyb_name").attr("value",r3_code_array[1]);
			
			$("#customer_type").attr("value",r3_code_array[2]);
			
			$("#ywy_id").attr("value",r3_code_array[4]);
			//$("#ywy_id_name").attr("value",r3_code_array[i]);
		}
	}
}
	
function getKonkaOhterR3List() { 
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaSpActivityManager.do?method=spActivityManagerForR3List&azaz=" + Math.random(),window,"dialogWidth:650px;status:no;dialogHeight:390px");
    
	if (returnValue != null && returnValue.ids != "") {
	    var r3_code_array = new Array();
	    var r3_code_name_array = new Array();
	
	    r3_code_array = returnValue.ids.split(",");
	    r3_code_name_array = returnValue.names.split(",");
	
	   var  r3_codes = '';
	   for(var i = 0; i<r3_code_array.length; i++){
		   var code = new Array();
		   code = r3_code_array[i].split("_");
		   r3_codes += code[3] + ",";
	   }
	    
		$("#other_customer").attr("value",r3_code_name_array);
	    $("#other_r3_code").attr("value",r3_codes);
	}
}	

function getKonkaChargePerson(){
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaSpActivityManager.do?method=chargePerson&azaz=" + Math.random(),window,"dialogWidth:650px;status:no;dialogHeight:390px");
	
	if(returnValue != null && returnValue.ids != ""){
		var charge_person_id_array = new Array();
		var charge_person_name_array = new Array();
		
		charge_person_id_array = returnValue.ids.split(",");
		charge_person_name_array =  returnValue.names.split(",");
		
		$("#charge_person").attr("value",charge_person_name_array);
	}
}
	
function getKonkaR3Order() { 
		$("#orderHidden").clone().appendTo($("#tbodyOrder")).show();
    	var lastTR = $("tr:last", "#tbodyOrder");
    	
    	/* var r3_code_va = $("#r3_code", lastTR);//R3客户管理
    	var l4_dept_id = $("#jyb_id", lastTR);//隐藏域经营部ID
    	var l4_dept_name = $("#jyb_name", lastTR);//经营部
    	
		var ywy_user_name = $("#ywy_name", lastTR);//业务员
		var customer_name = $("#customer", lastTR);//客户名称
		var customer_type = $("#c_type", lastTR);//隐藏域 客户类型ID
		
		var customer_type_name = $("#c_name", lastTR);//客户类型
		var job_id = $("#ywy_job_id", lastTR);//隐藏域 业务员岗位ID
		
		var value_array = r3_value_array[i].split("_");
		
		l4_dept_id.val(value_array[0]);
		l4_dept_name.val(value_array[1]);
		ywy_user_name.val(value_array[2]);
		customer_name.val(value_array[3]);
		customer_type.val(value_array[4]);
		customer_type_name.val(value_array[5]);
		job_id.val(value_array[6]);
		
		r3_code_va.val(r3_code[i]);
		
      	r3_code_va.attr("id",r3_code[i]);//改变id */

		//删除操作，影响弹出页面的返回值
		$("td:last", lastTR).click(function (){
	    	$(this).parent().remove();
	    	 calcPdNumAndPrice("tbodyOrder");
	    }).css("cursor", "pointer");
	resizeFrameHeight(2);
}

/*处理返回值数据 ===end===*/

//判断产品型号是否选择重复
function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();  
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }  
	return isRepeat;
}

//输入数量和台数的绑定计算函数
function bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
	good_count.keyup(function(){//数量
		var _reg = /^\d+$/;
		var count =(this.value);
   		if (!_reg.test(count)) {
   			good_count.val(1);
   			count = 1;
   		}
   		var price = parseFloat(good_price.val());//单价
   		if(isNaN(price)) price = 0;
   		
   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		
   		count = parseFloat(count);//数量
   		if(isNaN(count)) count = 1;
   	   	
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_price.keyup(function(){//单价
		var price = (this.value);
		var _reg = /^[\+\-]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			good_price.val(0);
			price = 0;
		}
		price = parseFloat(this.value);
		if(isNaN(price)) price = 0;
		if(price > parseFloat(99999.99)){
			alert("价格太大了，上限为10万！");
			good_price.val(99999.99);
			price = 99999.99;
		}
   		var count = parseFloat(good_count.val());//数量
   		if(isNaN(count)) count = 1;

   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_discount.keyup(function(){//折扣
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount_price.val((discount * sum_price/100).toFixed(2));//折扣金额
   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
		calcPdNumAndPrice("tbodyContent");
	});
	
	// 下面的代码是xxd新增，为放开可修改折扣金额输入框 add by xxd @20130702
	good_discount_price.keyup(function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount_price.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount.val(sum_price == 0 ? '-' : (discount * 100 / sum_price).toFixed(2));//折扣率
		calcPdNumAndPrice("tbodyContent");
	});
	// end. add by xxd @20130702
	
	calcPdNumAndPrice("tbodyContent");
}

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.text((good_count * good_price).toFixed(2));//金额
   	sum_price.val((good_count * good_price).toFixed(2));//隐藏域金额
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	var dd_discount_sum = 0;
	$("#good_count", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("#good_sum_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	$("#good_discount_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_discount_sum += this_value;
	});
	
	$("#dd_count_sum").text(dd_count_sum);
	$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
	$("#dd_discount_sum").text("￥" + (dd_discount_sum.toFixed(2)));
	$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
	//$("#money").val(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2));
	$("#order_num").val(dd_count_sum);//隐藏域订单总数
	$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
	$("#discount_price_sum").val(dd_discount_sum.toFixed(2));//隐藏域订单折扣金额
}

function setOnlyNum2() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>