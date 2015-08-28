<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt; 销售登记&gt; 产品分销</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css"></style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：产品销售 &gt; 销售登记&gt; 产品分销</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcSellBill.do" >
      <html-el:hidden property="method" value="saveForFx" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="sn" value="${sn}"/>
      <html-el:hidden property="keySeq" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4" align="center">产品分销 </th>
        </tr>
        <tr>
          <td class="title_item" width="15%"><font color="red">*</font>销售日期：</td>
          <td><html-el:text property="sell_date" styleId="sell_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td class="title_item"><font color="red">*</font>销售编号：</td>
          <td><span><font color="red">NO.</font><font color="red">${sn}</font></span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>下级网点(经销商)：</td>
          <td colspan="3"><html-el:select property="jxs_shop_id" styleId="jxs_shop_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${konkaBranchCategoryList}" >
                <html-el:option value="${cur.mmt_shop_id}">${fn:escapeXml(cur.map.jxs_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <th colspan="4">产品信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品类型：</td>
          <td colspan="3"><html-el:select property="pd_type" styleId="pd_type" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${basePdTypeList}" var="cur">
                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            <span id="otherPdType" style="display:none">
            <html-el:select property="jxc_pd_type_id" styleId="jxc_pd_type_id" styleClass="bdfont" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${JxcPdTypeList}" var="cur">
                <html-el:option value="${cur.id}">${cur.name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品品牌：</td>
          <td colspan="3"><input class="webinput" type=hidden name="brand_id" id="brand_id" value="114"/>
            康佳
            <input class="webinput" type=hidden name="brand_name" id="brand_name" value="康佳"/></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品型号：</td>
          <td colspan="3"><span id="pd_id_span"></span>
            <html-el:hidden property="pd_id" styleId="pd_id"/></td>
        </tr>
        <tr>
          <th colspan="4">销售信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售数量：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="count" type="text" id="count" maxlength="8" value="1"/></td>
        </tr>
        <tr>
          <td class="title_item">产品编号：</td>
          <td colspan="3"><input class="webinput" name="pd_bh" type="text" id="pd_bh"  maxlength="100"/></td>
        </tr>
        <tr>
          <td class="title_item">产品串号：</td>
          <td colspan="3"><input class="webinput" name="pd_ch" type="text" id="pd_ch"  maxlength="100"/></td>
        </tr>
        <tr>
          <td class="title_item">经办人：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="opr_man" type="text" id="opr_man" value="${userName}" size="10" maxlength="20" /></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input class="bgButtonSave" type="button" name="btn_submit" id="btn_submit"  value="保 存" />
            &nbsp;
            <input class="bgButtonBack" type="button" name="btn_back"  id="btn_back" value="返 回" onclick="history.back();"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#jxs_shop_id").attr("dataType","Require").attr("msg","请选择下级网点!");
	$("#pd_type").attr("dataType","Require").attr("msg","请选择产品类型！");
	$("#count").attr("dataType","Number").attr("msg","请填写销售数量,且只能为正整数据！");
	var f = document.forms[0];
	$("#btn_submit").click(function(){//提交
		
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit) {
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			$("#btn_reset").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			f.submit();
		}
	});	

	$("#count").bind("keyup", function(){
		var _reg = /^\d+$/;
		var _this = $(this);
		var this_value = _this.val();//进货数量
		if("" != this_value) {
			if (!_reg.test(this_value)) {
				alert(_this.attr("msg"));
				_this.val(1);
				return false;
			}
			/*if(Number(this_value) > parseFloat($("#maxPdNum").val())){
				alert("您输入的销售产品数量超过该产品最大库存[" + parseFloat($(this).prev().val()) + "]，请重新输入！");
				_this.focus();
				_this.val(1);
			} */
		}
	});
	
});

//var nameHtml = '<input type="text" name="name" value="${af.map.name}" id="name" style="width:220px;" maxlength="50" dataType="Require" msg="请填写产品型号！"/>';
var outSysIdHtml = '<select name="out_sys_id" multiple="multiple" style="display:none;" id="out_sys_id" styleClass="bdfont"></select>';


/*产品类型 （与自定义大类控制是否下级可选）Begin*/
$("#pd_type").change(function(){
	var pd_type = $(this).val();
	//$("#brand_name").val("");
	if(0 == pd_type) {// 选择"其他"时，提供自己的产品大类列表选择
		$("#otherPdType").show();
		$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
	} else {
		$("#otherPdType").hide();
		$("#jxc_pd_type_id").removeAttr("dataType").val("");
	}
	//backSellInfo();
	//根据型号，显示产品编号，串号===
    var brand_id = $("#brand_id").val();
	if(brand_id!='' && brand_id=="${KONKA_BRAND_ID}" ){//是康佳产品显示pd-bh/pd_ch
		 $("#tbodyKjPdCh").show();
	}else{
		 $("#tbodyKjPdCh").hide();
	}
	
	getProductType(pd_type, brand_id);
	$("#pd_id").val("");//清空选择的型号
	$("#out_sys_id").val("");
	$("#count").val(1);
	$("#price").val(0);
	$("#total_price").val("0.00");
	$("#money").val("0.00");
	$("#pay_money").val("0.00");
		  
});
	//根据大类、品牌选型号
function getProductType(pd_type, brand_id, out_sys_id){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	
	//var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	var jxc_pd_type_id = $("#jxc_pd_type_id").val();
	$.ajax({
		type: "POST",
		url: 'JxcSellBill.do',
		data: "method=getPdModel&pd_type=" + pd_type + "&brand_id=" + brand_id + "&jxc_pd_type_id=" + jxc_pd_type_id + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
			$("#pd_id_span").empty();
			if (Dates.length > 1) {
				$("#pd_id_span").html(outSysIdHtml);
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name ="产品编码:["+ Dates[i].pd_id +"]  " + Dates[i].md_name;
					//$("#out_sys_id").get(0).options.add(new Option(Dates[i].md_name, Dates[i].pd_id));
					$("#out_sys_id").get(0).options.add(new Option(md_name, Dates[i].values));
					//document.forms[0].price.value=Data[i].price;//默认价格
				}
				
				var el = $("#out_sys_id").multiselect({
					selectedList: 1,
					multiple: false,
					minWidth:380,
					click:function(event,ui){
					if(ui.value != ""){
						var s = ui.value.split("#$");
						$("#pd_id").val(s[0]);
						//$("#price").val(s[1]);//取零售价
						$("#maxPdNum").val(s[2]);//最大库存
						$(".maxPdNumTip").remove();
						$("#count").after("<span style='color:red;padding-left:5px;' class='maxPdNumTip'>该产品当前库存为：【" + s[2]+ "】</span>");
					}
				}
				}).multiselectfilter({width:280}).attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
			   	$("#out_sys_id option[value='${af.map.out_sys_id}']").attr("selected", "selected");
				el.multiselect('refresh');
				
			} else {
				$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有型号,请重新选择产品类型：</span>');//.append(getPdModelListHtml).append(outSysIdHiddenHtml);
			}
		}
	});
	if(undefined != out_sys_id && "" != out_sys_id){
		$("#out_sys_id").val(out_sys_id);
	}
}

//正则表达式：只能输入数字和带小数点数字
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
		//if(this.value.length == 0) this.value = "0";
	});
}
//正则表达式：只能输入数字
function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[-\+]?\d+$/;
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>