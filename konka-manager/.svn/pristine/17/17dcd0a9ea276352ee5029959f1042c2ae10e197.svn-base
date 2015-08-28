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
				<html-el:form action="/admin/KonkaContractManager" enctype="multipart/form-data">
					<html-el:hidden property="id" styleId="id" />
					<html-el:hidden property="mod_id" styleId="mod_id" />
					<html-el:hidden property="method" styleId="method" value="save" />
					<html-el:hidden property="queryString" styleId="queryString" />
					<html-el:hidden property="file_state" styleId="file_state" />
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td nowrap="nowrap" class="title_item" align="right" width="20%"><font color="red">* </font>分公司：</td>
							<td align="left" width="30%">
							<span><font color="red"></font><font color="red">${af.map.dept_name}</font></span>
							<html-el:hidden property="dept_id" value="${af.map.dept_id}"/>
							<html-el:hidden property="dept_name" value="${af.map.dept_name}"/>
							</td>
							<td nowrap="nowrap" class="title_item" align="right" width="20%"><font color="red">* </font>单据编码：</td>
							<td align="left" width="30%"><span><font color="red"></font><font color="red">${af.map.bill_sn}</font></span>
							<html-el:hidden property="bill_sn" value="${af.map.bill_sn}"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同名称：</td>
							<td align="left" colspan="3"><html-el:text property="con_name" size="30" maxlength="30" styleId="con_name" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>合同编码：</td>
							<td align="left"><html-el:text property="con_sn" size="30" maxlength="30" styleId="con_sn" /></td>
						    <td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>授权编号：</td>
							<td align="left"><html-el:text property="sq_sn" size="30" maxlength="30" styleId="sq_sn" />
							</td>
						</tr>
						<tr>
					          <td align="right" nowrap="nowrap" class="title_item"><font color="red">* </font>客户R3编码：</td>
					          <td><c:if test="${empty af.map.r3_code}"><html-el:text readonly="true" property="r3_code" styleId="r3_code" style="width:150px;" size="30" maxlength="30" /></c:if>
					          <c:if test="${not empty af.map.r3_code}">
					          <c:if test="${is_admin ne 1}">
					          ${af.map.r3_code}<html-el:hidden property="r3_code" value="${af.map.r3_code}" /> 
					          </c:if>
					          <c:if test="${is_admin eq 1}">
					          		<html-el:text readonly="true" property="r3_code" styleId="r3_code_2" value="${af.map.r3_code}" style="width:150px;" size="30" maxlength="30" />
					          </c:if>
					          </c:if>
					          </td>
					          <td nowrap="nowrap" align="right" class="title_item"><font color="red">* </font>客户名称：</td>
					          <td><c:if test="${empty af.map.r3_code}"><html-el:text readonly="true" property="customer_name" styleId="customer_name" size="30" maxlength="30" /></c:if>
					          <c:if test="${not empty af.map.r3_code}">
					          <c:if test="${empty af.map.customer_name}"><html-el:text property="customer_name" styleId="customer_name" size="30" maxlength="30" /> </c:if>
					          <c:if test="${is_admin ne 1}">
					          <c:if test="${not empty af.map.customer_name}">${af.map.customer_name}<html-el:hidden property="customer_name" value="${af.map.customer_name}" /></c:if>
					          </c:if>
					           <c:if test="${is_admin eq 1}">
					          		<html-el:text readonly="true" property="customer_name" styleId="customer_name_2" value="${af.map.customer_name}" style="width:150px;" size="30" maxlength="30" />
					          </c:if>
					          </c:if></td>
       					 </tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>签订时间：</td>
							<td align="left"><fmt:formatDate value="${af.map.sign_date}" var="_sign_date" pattern="yyyy-MM-dd" />
								<html-el:text property="sign_date" styleId="sign_date" size="10" maxlength="10" value="${_sign_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
							<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>合同期限：</td>
							<td align="left">
								<fmt:formatDate value="${af.map.s_date}" var="_s_date" pattern="yyyy-MM-dd" />
								<html-el:text property="s_date" styleId="s_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
								至
								<fmt:formatDate value="${af.map.e_date}" var="_e_date" pattern="yyyy-MM-dd" />
								<html-el:text property="e_date" styleId="e_date" size="10" maxlength="10" value="${_e_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同基本目标（万元）：</td>
							<td align="left"><html-el:text property="con_base_money" size="20" maxlength="8" styleId="con_base_money" onfocus="javascript:setOnlyNum(this);" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">合同挑战目标（万元）：</td>
							<td align="left"><html-el:text property="con_expect_money" size="20" maxlength="8" styleId="con_expect_money" onfocus="javascript:setOnlyNum(this);" /></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">上年销售额（万元）：</td>
							<td align="left" colspan="3"><html-el:text property="last_year_sale" size="20" maxlength="8" styleId="last_year_sale" onfocus="javascript:setOnlyNum(this);" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>业务类型：</td>
							<td align="left" ><html-el:select property="c_type" styleId="c_type">
							  	    <html-el:option value="">-请选择-</html-el:option>
									<html-el:option value="1">加价</html-el:option>
									<html-el:option value="2">倒扣</html-el:option>
							</html-el:select>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">规模返利：</td>
							<td align="left"><html-el:text property="scale_sb" size="20" maxlength="8" styleId="scale_sb" onfocus="javascript:setOnlyNum(this);" />%</td>	
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">月返（百分比）：</td>
							<td align="left"><html-el:text property="month_sb" size="20" maxlength="8" styleId="month_sb" onfocus="javascript:setOnlyNum(this);" />%
							</td>
							<td nowrap="nowrap" class="title_item" align="right">特价机点位（百分比）：</td>
							<td align="left"><html-el:text property="tjj_money" size="20" maxlength="8" styleId="tjj_money" onfocus="javascript:setOnlyNum(this);" />%</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">其他返利（百分比）：</td>
							<td align="left"><html-el:text property="other_sb" size="20" maxlength="8" styleId="other_sb" onfocus="javascript:setOnlyNum(this);"/>%
							</td>
							<td nowrap="nowrap" class="title_item" align="right">工程机点位（百分比）：</td>
							<td align="left"><html-el:text property="gcj_money" size="20" maxlength="8" styleId="gcj_money" onfocus="javascript:setOnlyNum(this);"/>%</td>
						</tr>
						<tr>
				          <td width="15%" nowrap="nowrap" class="title_item" align="right">其他返利备注：</td>
				          <td width="88%" align="left" colspan="3">
				          	<html-el:textarea property="o_remark" styleId="o_remark" cols="5" style="width:450px;height:70px;" />
				          	<div id="info_tip_2" style="color:#0066FF;font-size:15px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
				          </td>
				        </tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同条款：</td>
							<td align="left" colspan="3"><html-el:text property="con_content" size="70" maxlength="100" styleId="con_content" /></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">洽谈甲方：</td>
							<td align="left"><html-el:text property="talks_man_a" size="30" maxlength="30" styleId="talks_man_a" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">洽谈乙方：</td>
							<td align="left"><html-el:text property="talks_man_b" size="30" maxlength="30" styleId="talks_man_b" /></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">经营部：</td>
							<td align="left">
							<html-el:text property="jyb_name_1" size="30" maxlength="30" styleId="jyb_name_1"  value="${af.map.jyb_name}" disabled="true" />
							<html-el:hidden property="jyb_name"  styleId="jyb_name" />
							<html-el:hidden property="jyb_id"  styleId="jyb_id" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">业务员：</td>
							<td align="left">
							<html-el:text property="ywy_name_1" size="30" maxlength="30" styleId="ywy_name_1"  value="${af.map.ywy_name}" disabled="true" />
							<html-el:hidden property="ywy_name"  styleId="ywy_name" />
							<html-el:hidden property="ywy_job_id"  styleId="ywy_job_id" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">制单人：</td>
							<td align="left"><html-el:text property="user_name" size="30" maxlength="30" styleId="user_name" value="${user_name}" disabled="true" />
											<html-el:hidden property="add_user_name" value="${user_name}" />
											<html-el:hidden property="add_user_id" value="${user_id}" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">创建日期：</td>
							<td align="left"><fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
							<html-el:text property="add_date" size="30" maxlength="30" styleId="add_date" value="${add_date}" disabled="true" style="width:80px;background-color: #D8D8D8;" onclick="new Calendar(2005, 2030, 0).show(this);"/></td>
						</tr>
						<tr>
				          <td width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
				          <td width="88%" align="left" colspan="3">
				          	<html-el:textarea property="demo" styleId="demo" cols="5" style="width:450px;height:70px;" />
				          	<div id="info_tip" style="color:#0066FF;font-size:15px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
				          </td>
				        </tr>
						 <tr>
				            <td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
				            <td colspan="3"><div id="divFileHidden" style="display: none;">
				                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
				                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
				              <div id="divFile">
				                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
				                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
				            </td>
			          	</tr>
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
			           <tr>
							<td nowrap="nowrap" class="title_item" align="right">合同期结算额（万元）：</td>
							<td align="left"><html-el:text property="con_date_money" size="20" maxlength="8" styleId="con_date_money" onfocus="javascript:setOnlyNum(this);" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">基本目标完成率（百分比）：</td>
							<td align="left"><html-el:text property="base_target_wcl" size="20" maxlength="8" styleId="base_target_wcl" onfocus="javascript:setOnlyNum(this);" />%</td>
						</tr>
						 <tr>
							<td nowrap="nowrap" class="title_item" align="right">合同期回款额（万元）：</td>
							<td align="left" colspan="3"><html-el:text property="con_date_backmoney" size="20" maxlength="8" styleId="con_date_backmoney" onfocus="javascript:setOnlyNum(this);" />
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="3" align="center">
						    	<input type="button" name="save" class="bgButtonSave" value="提交" id="btn_submit"/>
					            <input type="button" name="temp_save" class="bgButtonSave" value="保存" id="btn_temp_save"/>
					            <input class="bgButtonBack" type="button" name="reset" value="返回" id="btn_back" onclick="history.back();return false;"/>
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	//$("#con_name").attr("dataType", "Require").attr("msg", "请填写合同名称！");
	$("#con_sn").attr("dataType", "Require").attr("msg", "请填写合同编码！");
	$("#r3_code").attr("dataType", "Require").attr("msg", "请选择R3客户！");
	$("#customer_name").attr("dataType", "Require").attr("msg", "客户名称不能为空！");
	$("#sign_date").attr("dataType", "Require").attr("msg", "签订时间不能为空！");
	$("#s_date").attr("dataType", "Require").attr("msg", "合同起始时间不能为空！");
	$("#e_date").attr("dataType", "Require").attr("msg", "合同结束时间不能为空！");
	$("#c_type").attr("dataType", "Require").attr("msg","业务类型不能为空 ！");
	//$("#talks_man_a").attr("dataType", "Require").attr("msg", "洽谈甲方不能为空！");
	//$("#talks_man_b").attr("dataType", "Require").attr("msg", "洽谈乙方不能为空！");
	$("#sq_sn").attr("dataType", "Require").attr("msg", "授权编码不能为空！");
	//$("#last_year_sale").attr("dataType", "Require").attr("msg", "上年销售额不能为空！");
	
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


	 $("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除此附件？')) return;
	  	    $.post("KonkaContractManager.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  	  });
	   }); 


	 // $("#con_base_money,#con_expect_money,#scale_sb,#month_sb,#tjj_money,#other_sb,#gcj_money,#con_date_money,#base_target_wcl,#con_date_backmoney").blur(function(){
		 // var con_base_money = $(this).val();
		 // if($.trim(con_base_money).length > 8){
			//alert("长度不能超过8位！");
			//return;
		//  }

	//  }); 


	 $("#demo").textbox({
			maxLength: 150,
			onInput: function(event, status) {
				var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
				$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			}
		}).blur(function() {
			$("#info_tip").slideUp("normal");
		});	

	 $("#o_remark").textbox({
			maxLength: 150,
			onInput: function(event, status) {
				var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
				$("#info_tip_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			}
		}).blur(function() {
			$("#info_tip_2").slideUp("normal");
		});		


	// 选择客户
	var dept_id = "${af.map.dept_id}";
	$("#r3_code").click(function(){
		var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaContractManager.do?method=listCustomer&fgs_id=" + dept_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) returnValue = window.returnValue;
		$("#r3_code").val(returnValue.cust_r3_code);
		$("#customer_name").val(returnValue.cust_name);
		$("#ywy_job_id").val(returnValue.ywy_id);
		$("#ywy_name").val(returnValue.ywy_user_name);
		$("#ywy_name_1").val(returnValue.ywy_user_name);
		$("#jyb_id").val(returnValue.ywy_dept_id);
		$("#jyb_name").val(returnValue.ywy_dept_name);
		$("#jyb_name_1").val(returnValue.ywy_dept_name);
	});


	
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			var d1=$("#s_date").val();
			var d2=$("#e_date").val();
			if(CompareDate(d1,d2)){
				alert("起始时间不能大于结束时间！");
				return;
			}

			var c_b_money=$("#con_base_money").val();
			var c_e_money=$("#con_expect_money").val();
			if(""!=c_b_money){
				if(""==c_e_money){
					alert("挑战目标不能小于基本目标");
					return;
				}else if(parseFloat(c_e_money)<parseFloat(c_b_money)){
					alert("挑战目标不能小于基本目标");
					return;
				}
				
			}

			$("#file_state").val(1);
			
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_temp_save").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

	$("#btn_temp_save").click(function(){
		if(Validator.Validate(this.form, 3)){
			var d1=$("#s_date").val();
			var d2=$("#e_date").val();
			if(CompareDate(d1,d2)){
				alert("起始时间不能大于结束时间！");
				return;
			}

			var c_b_money=$("#con_base_money").val();
			var c_e_money=$("#con_expect_money").val();
			if(""!=c_b_money){
				if(""==c_e_money){
					alert("挑战目标不能小于基本目标");
					return;
				}else if(parseFloat(c_e_money)<parseFloat(c_b_money)){
					alert("挑战目标不能小于基本目标");
					return;
				}
				
			}

			$("#file_state").val(2);
            $("#btn_temp_save").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_submit").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

	
	
});

function CompareDate(d1,d2)
{
  return ((new Date(d1.replace(/-/g,"//"))) > (new Date(d2.replace(/-/g,"//"))));
}

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
		if(obj.value.length == 0) obj.value = "0";
	});
}


function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
