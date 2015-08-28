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
					<th width="15%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>单据编号：</span></th>
					<th colspan="3" align="left"><span><font color="red"></font><font color="red">${af.map.meeting_sn}</font></span>
					<html-el:hidden property="meeting_sn" value="${af.map.meeting_sn}"/>
					</th>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>主题：</td>
					<td align="left" colspan="3" width="90%">
					<html-el:text property="meeting_name" styleId="meeting_name" readonly="true" style="width:300px;" maxlength="80" />
					</td>
				</tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>会议类型：</td>
					<td align="left" width="40%">
					<html-el:select property="meeting_id" styleId="meeting_id"  disabled="true" style="background-color:#fff;">
					<html-el:option value="">请选择…</html-el:option>
					<html-el:optionsCollection name="spActivityList" label="hd_type" value="id"/>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">分公司：</td>
					<td align="left" width="40%">
					<html-el:hidden property="dept_id" value="${af.map.dept_id}"  styleId="dept_id" />
					<html-el:text property="dept_name" value="${af.map.dept_name}" styleId="dept_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>订货目标（万元）：</td>
					<td align="left" colspan="3" style="width:90%">
					<html-el:text property="order_target" styleId="order_target" readonly="true"  maxlength="10" style="width:100px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>召开时间：</td>
					<td align="left">
					<fmt:formatDate var="open_date" value="${af.map.open_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="open_date" styleId="open_date" value="${open_date}" size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" />
					</td>
					<td class="title_item" align="right" nowrap="nowrap">会议状态：</td>
					<td align="left">
					<html-el:select property="meeting_status" styleId="meeting_status">
					<html-el:option value="30">进行中</html-el:option>
					</html-el:select>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>负责人：</td>
					<td align="left">
					<html-el:hidden property="charge_person" />
				    <html-el:select property="charge_person_job_id" styleId="charge_person_job_id" disabled="true" style="background-color:#fff;">
					<c:forEach items="${user_list}" var="cur">
					<html-el:option value="${cur.job_id}">${cur.department}->${cur.user_name}</html-el:option>
					</c:forEach>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>联系电话：</td>
					<td align="left">
					<html-el:text property="charge_person_tel" readonly="true" styleId="charge_person_tel"  maxlength="20" style="width:100px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">上报人：</td>
					<td align="left">
					<html-el:hidden property="report_user_job_id" />
					<html-el:text property="report_user_name" value="${af.map.report_user_name }" styleId="report_user_name" maxlength="20" style="width:200px;"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">创建日期：</td>
					<td align="left">
					<fmt:formatDate var="meeting_date" value="${af.map.add_meeting_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_meeting_date" maxlength="30" styleId="add_meeting_date" value="${meeting_date}" readonly="true" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">地址：</td>
					<td align="left" colspan="3" style="width:90%">
					<html-el:text property="hd_addr" styleId="hd_addr" readonly="true" maxlength="40" style="width:200px;"/>
					</td>
				</tr>
		        <tr>
		          <td class="title_item" align="right" nowrap="nowrap">参与客户：</td>
		          <td colspan="3">
		            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		              <tr class="title_top">
		                <td nowrap="nowrap">客户名称</td>
		                <td width="5%" nowrap="nowrap">客户类型</td>
		                <td width="8%" nowrap="nowrap">业务员</td>
		                <td width="10%" nowrap="nowrap">经营部</td>
		              </tr>
		              <tr id="trHidden" style="display:none;">
		                <td align="center">
		                  <html-el:hidden property="r3_code" styleId="r3_code" />
		                <html-el:text property="customer" styleId="customer" readonly="true" style="width:99%;" styleClass="webinput good_count" />
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
		              </tr>
		              <tbody id="tbodyContent">
		               <c:forEach items="${af.map.orderMeetingCustomerList}" var="cur">
		              <tr id="trHidden" style="">
		                <td align="center">
		                  <html-el:hidden property="r3_code" styleId="${cur.r3_code}" value="${cur.r3_code}"/>
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
		              </tr>
		              </c:forEach>
		              </tbody>
		            </table>
		          </td>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap">会议备注：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="meeting_memo" styleId="meeting_memo" readonly="true" style="resize:none;" cols="50" rows="5"></html-el:textarea>
					</td>
				</tr>
		        <tr>
					<th align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					过程管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">维护日期：</td>
					<td align="left" colspan="3">
					<fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_date" size="40" maxlength="30" value="${add_date}" readonly="true" styleId="add_date" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>会议流程：</td>
					<td align="left" colspan="3"><html-el:text property="meeting_process" styleId="meeting_process"  maxlength="100" style="width:300px;"/></td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>过程描述：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="meeting_process_des" styleId="meeting_process_des" style="resize:none;" cols="50" rows="5"></html-el:textarea>
					<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">会议政策：</td>
					<td colspan="3" width="95%">
					<div id="policydivFileHidden" style="display: none;" >
	                <input name="meeting_policy" type="file" id="meeting_policy" style="width: 250px;" />
	                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="policyDelTr" title="删除"/>
	                </div>
	              	<div id="policydivFile">
	                <input name="policy_file" type="file" id="policy_file" style="width: 250px;" />
	                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="policyAddTr" title="再添加一个" /></div>
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'policy_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">会议照片：</td>
					<td colspan="3" width="95%">
					<div id="photodivFileHidden" style="display: none;" >
	                <input name="meeting_photo" type="file" id="meeting_photo" style="width: 250px;" />
	                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="photoDelTr" title="删除"/>
	                </div>
	              	<div id="photodivFile">
	                <input name="photo_file" type="file" id="photo_file" style="width: 250px;" />
	                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="photoAddTr" title="再添加一个" /></div>
	            	<ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'photo_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
					</c:if>
					</c:forEach>
					</ol>只支持 bmp, gif, jpeg, jpg, png的格式的图片。
	            	</td>
				</tr>
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
	var acceptUploadImageFileExts = "bmp, gif, jpeg, jpg, png";
	$("#meeting_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#meeting_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#order_target").attr("Require",true).attr("dataType", "Double").attr("msg", "请正确填写，只能为数字");//shu
  	$("#open_date").attr("dataType", "Require").attr("msg", "请填写");
	
	$("#charge_person_tel").attr("require", "true").attr("msg", "请正确填写联系电话");
  	$("#meeting_process").attr("Require",true).attr("dataType", "Limit").attr("max", "50").attr("min","1").attr("msg", "会议流程必须在50个字之内");
  	$("#meeting_process_des").attr("Require",true).attr("dataType", "Limit").attr("max", "200").attr("min","1").attr("msg", "过程描述必须在200个字之内");
  	$("#meeting_photo" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png");
	$("#photo_file" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png");
	$("#meeting_process_des").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	$("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "attachment_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        resizeFrameHeight();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
	 });
 $("#policyAddTr").click(function (){
        $("#policydivFileHidden").clone().find("#meeting_policy").attr("name", "policy_file_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
        resizeFrameHeight();
        $("img[id='policyDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
 });
 $("#photoAddTr").click(function (){
        $("#photodivFileHidden").clone().find("#meeting_photo").attr("name", "photo_file_" + new Date().getTime()).end().appendTo($("#photodivFile")).show();
        resizeFrameHeight();
        $("img[id='photoDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight();
            });
        });
 });
	

 
$("a[id^='att_del_']").click(function() {
	  var a = this;
	   if(!confirm('确实要删除此附件？')) return;
	    $.post("${ctx}/manager/chengduoa/ExpenseClaims.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  });
 });
 

 $("#btn_save").click(function() {
	 if(Validator.Validate(this.form, 3)){
		$("#file_status").val("1");
		this.form.submit();
		$('#process_tips').html('保存正在执行，请耐心多等它一会！').css('color','red');
		
		$(this).attr('disabled','disabled');
		$('#btn_reset').attr('disabled','disabled');
		
	 }
	 resizeFrameHeight();
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
			    	
			    	bindJhCountAndJhPrice(md_num, md_money, lastTR);
			    	
			    	$("td:last", lastTR).click(function (){
				    	$(this).parent().remove();
				    }).css("cursor", "pointer");
				}
			}
		}
	});
 });
 
 calcPdNumAndPrice("tbodyOrder");
 resizeFrameHeight();
});//ready end
	/*处理返回值数据 ===start===*/
	
function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

//输入数量和台数的绑定计算函数
 function bindJhCountAndJhPrice(good_count, good_price, lastTR) {
	good_count.keyup(function(){//数量
		var _reg = /^\d+$/;
		var count =(this.value);
   		if (!_reg.test(count)) {
   			good_count.val(1);
   			count = 1;
   		}
   		var price = parseFloat(good_price.val());//单价
   		if(isNaN(price)) price = 0;
   		
   		count = parseFloat(count);//数量
   		if(isNaN(count)) count = 1;
   	   	
		calcPdNumAndPrice("tbodyOrder");
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

		calcPdNumAndPrice("tbodyOrder");
	});

	calcPdNumAndPrice("tbodyOrder");
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	$("[id^=md_num]", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).attr("value"));
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("[id^=md_money]", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	
	$("#dd_count_sum").text(dd_count_sum);
	$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
	//$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)));
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>