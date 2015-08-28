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
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="overflow: auto;">
    <html-el:form action="/admin/GcxmProjSupply" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="info_state" styleId="info_state" value="0"/>
      <html-el:hidden property="queryString" />
      <c:set var="readyOnly" value="${empty af.map.offer_id?false:true}"  />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%">项目编号：</td>
      		<td width="35%"><html-el:text property="proj_code" styleId="proj_code" value="${af.map.gcxm_sn}" readonly="true" style="width:200"/></td>
            <td class="title_item" align="right" width="15%">项目类型：</td>
           <td width="35%" style="color:red">
            <c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td colspan="3"><html-el:text property="proj_name" styleId="proj_name" value="${af.map.proj_name}" readonly="true" maxlength="10" style="width:350px;"  /></td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
           <html-el:text property="dept_name" styleId="dept_name" value="${af.map.fgs_dept_name}" readonly="true" maxlength="10" style="width:350px;"  />
          </td>
           <td class="title_item" align="right">项目状态：</td>
          <td width="35%">
              <c:if test="${empty af.map.gcxmProjSupply.info_state}">未提交</c:if>
              <c:if test="${af.map.gcxmProjSupply.info_state eq -1}">进行中</c:if>
              <c:if test="${af.map.gcxmProjSupply.info_state eq 1}">已结束</c:if> 
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">报价型号：</td>
          <td width="50%" >
          <html-el:text property="offer_model" styleId="offer_model" value="${af.map.gcxmProjOffer.offer_model}" maxlength="20" style="width:200px;" readonly="true" /></td>
          <td class="title_item" nowrap="nowrap" align="right">分公司报价：</td>
          <td width="50%" >
          <html-el:text property="offer_price" styleId="offer_price" value="${af.map.gcxmProjOffer.offer_price}" maxlength="20" style="width:100px;" readonly="true" />元/台
          </td>
        </tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>    
          <td width="50%" >
          <fmt:formatDate value="${af.map.delivery_date}" pattern="yyyy-MM-dd" />  
          </td>     
        </tr>
        <tr>
        <td colspan="4" align="left" class="title_item">竞品价格</td>
        </tr>
         <tr>
         <td colspan="4"><table width="90%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="5%" align="center" nowrap="nowrap">序号</td>
          			<td align="center" width="10%" nowrap="nowrap"><span style="color:red;">* </span>品牌</td>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>型号</td>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>报价</td>
          			<td width="15%" align="center" nowrap="nowrap">说明</td>
          			<td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
          		</tr>
          		<tr id="tr_model" style="display:none;">
          		    <td align="center" nowrap="nowrap"><span id="audit_level_show" class="jxcTip"></span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="brand_name" size="20" maxlength="20" styleClass="brand_name" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="compet_model" styleClass="compet_model" size="20" maxlength="20" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="compet_price" size="8" maxlength="8" styleClass="compet_price" onfocus="javascript:setOnlyInt(this)" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="compet_memo" size="20" maxlength="20" styleClass="compet_memo" /></td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<c:forEach var="cur1" items="${gcList}" varStatus="vs">
          			<tr class="tr_pd_1">
		          	    <td align="center" nowrap="nowrap"><span id="audit_level_show_2" class="jxcTip2">${vs.count}</span><input type="hidden" name="c_ids" id="c_ids" value="${cur1.id}" /></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="brand_name" size="40" maxlength="20" styleClass="brand_name" value="${cur1.brand_name}" /></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_model" styleClass="compet_model" size="40" maxlength="20" value="${cur1.compet_model}" /></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_price" size="8" maxlength="8" styleClass="compet_price" value="${cur1.compet_price}" onfocus="javascript:setOnlyInt(this)" /></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_memo" size="40" maxlength="20" styleClass="compet_memo" value="${cur1.compet_memo}"/></td>
	          			<td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
	          		</tr>
	          		</c:forEach>
          		<tbody id="showAddTrsTbody"></tbody>
          </table>
          </td>
         </tr> 
         <tr>
         <td colspan="4" class="title_item" align="left"><span style="color:red">*</span>是否中标：<html-el:radio property="is_win" value="0" styleClass="is_win">已中标</html-el:radio>
         <html-el:radio property="is_win" value="1" styleClass="is_win">未中标</html-el:radio></td>
         </tr>
          <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>供货机型：</td>
          <td width="50%" >
          <html-el:text property="supply_model" styleId="supply_model"  maxlength="20" style="width:200px;" value="${af.map.supply_model}" onkeyup="upperCase(this.id)"/></td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>供货数量：</td>
          <td width="50%" >
          <html-el:text property="supply_num" styleId="supply_num"  value="${af.map.supply_num}" maxlength="8" style="width:100px;" />台
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">描述：</td>
          <td width="88%" colspan="3">
          <html-el:textarea property="supply_memo" styleId="supply_memo" value="${af.map.supply_memo}" cols="5" style="width:820px;height:100px;" />
          <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;创建人：</td> 
          <td width="50%" >
          <html-el:text property="create_name" styleId="create_name" maxlength="20" value="${af.map.user_name}" readonly="true" style="width:200px;" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>      
          <td width="50%" >
          <fmt:formatDate var="create_date" value="${af.map.create_date}" pattern="yyyy-MM-dd" />  
          <html-el:text property="create_date" styleId="create_date"  size="12" maxlength="10" readonly="true" value="${create_date}"  style="cursor:pointer;text-align:center;"  />
          </td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td align="center" colspan="3" ><label>
 			<input class="but4" type="button"  id="btn_submit1" value="暂存" /> 
            <input class="but4" type="button"  id="btn_submit" value="提交" />
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
       
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">
                                        
$(document).ready(function(){ 
	
	$("#supply_memo").textbox({   
		maxLength: 300,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");  
	});


	$(".td_del").click(function(){  
		$(this).parent().remove();

		$(".tr_pd_1 .jxcTip2").each(function(i){
			$(this).text(i+1);  
		}); 
		window.parent.resizeFrameHeight('mainFrame', 3);
	}); 

	// 计数，处理点击添加以后输入行的ID问题
	var num_count = 1;
	$("#addPdTD").click(function(){
		var tr_pd = $("#tr_model").clone(true).attr("class","tr_pd");
		num_count++;
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");

		$("#showAddTrsTbody .jxcTip").each(function(i){
			$(this).text(i+1);
		}); 

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			$("#showAddTrsTbody .jxcTip").each(function(i){
				$(this).text(i+1);
			});
			if (window.parent.resizeFrameHeight) window.parent.resizeFrameHeight('mainFrame', 3);
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	// 提交
	$("#btn_submit").click(function(){ 

		var is_win = $("input[name='is_win']:checked").val();
		if(undefined == is_win){
			alert("提示，请选择是否中标！");
			return;
		}

		if(is_win=="0"){
			$("#supply_model").attr("dataType", "Require").attr("msg", "请填写供货机型！");
			$("#supply_num").attr("datatype", "Require").attr("msg", "请填写供货数量！").focus(function(){setOnlyInt(this);});
			$("#supply_memo").removeAttr("dataType").removeAttr("msg");
		}else if(is_win=="1"){
			$("#supply_model").removeAttr("dataType").removeAttr("msg");
			$("#supply_num").removeAttr("dataType").removeAttr("msg");
			$("#supply_memo").attr("dataType", "Require").attr("msg", "请填写描述！");
		}

		$(".tr_pd_1 .brand_name").attr("dataType", "Require").attr("msg", "品牌必填！");
		$(".tr_pd_1 .compet_model").attr("dataType", "Require").attr("msg", "型号必填！");
		$(".tr_pd_1 .compet_price").attr("dataType", "Require").attr("msg", "价格必填！");
		

		$(".tr_pd .brand_name").attr("dataType", "Require").attr("msg", "品牌必填！");
		$(".tr_pd .compet_model").attr("dataType", "Require").attr("msg", "型号必填！");
		$(".tr_pd .compet_price").attr("dataType", "Require").attr("msg", "价格必填！");
		 

		$("#info_state").val(1);
		
		if(Validator.Validate(this.form, 2)){

			if(confirm("请您确认该项目已结束，不再对结果进行修改。")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();

			}
			
		}
	});


	// 暂存 
	$("#btn_submit1").click(function(){
		var is_win = $("input[name='is_win']:checked").val();
		if(undefined == is_win){
			alert("提示，请选择是否中标！");
			return;
		}

		if(is_win=="0"){
			$("#supply_model").attr("dataType", "Require").attr("msg", "请填写供货机型！");
			$("#supply_num").attr("datatype", "Require").attr("msg", "请填写供货数量！").focus(function(){setOnlyInt(this);});
			$("#supply_memo").removeAttr("dataType").removeAttr("msg");
		}else if(is_win=="1"){
			$("#supply_model").removeAttr("dataType").removeAttr("msg");
			$("#supply_num").removeAttr("dataType").removeAttr("msg");
			$("#supply_memo").attr("dataType", "Require").attr("msg", "请填写描述！");
		}

		$(".tr_pd_1 .brand_name").attr("dataType", "Require").attr("msg", "品牌必填！");
		$(".tr_pd_1 .compet_model").attr("dataType", "Require").attr("msg", "型号必填！");
		$(".tr_pd_1 .compet_price").attr("dataType", "Require").attr("msg", "价格必填！");
		
		

		$(".tr_pd .brand_name").attr("dataType", "Require").attr("msg", "品牌必填！");
		$(".tr_pd .compet_model").attr("dataType", "Require").attr("msg", "型号必填！");
		$(".tr_pd .compet_price").attr("dataType", "Require").attr("msg", "价格必填！");
		

		$("#info_state").val(-1);
		
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});


});


function setOnlyNum() {
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
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
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

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}
function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
} 

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
