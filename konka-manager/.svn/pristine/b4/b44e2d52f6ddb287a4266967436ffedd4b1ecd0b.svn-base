<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盘存管理&gt;盘存管理</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：盘存管理 &gt; 盘存管理</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcPcInfo">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="id" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="keySeq" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">添加盘存记录</th>
        </tr>
        <tr>
          <td width="15%" align="right" nowrap="nowrap" class="title_item"><font color="red">*</font>所属系统：</td>
          <td align="left">
            <label for="own_sys_0">
              <html-el:radio property="own_sys" styleId="own_sys_0" value="0">非家电下乡</html-el:radio>
            </label>
            &nbsp;
            <label for="own_sys_1">
              <html-el:radio property="own_sys" styleId="own_sys_1" value="1" >家电下乡</html-el:radio>
            </label>
          </td>
        </tr>
        <tr>
          <td align="right"  nowrap="nowrap"  class="title_item"><font color="red">*</font>产品类型：</td>
          <td align="left">
            <html-el:select property="pd_type" styleId="pd_type" styleClass="bdfont" style="width:170px">
              <c:forEach items="${basePdTypeList}" var="cur">
                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            <span id="otherPdType" style="display:none">
            <html-el:select property="jxc_pd_type_id" styleId="jxc_pd_type_id" styleClass="bdfont" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${jxcPdTypeList}" var="cur">
                <html-el:option value="${cur.id}">${cur.name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </span></td>
        </tr>
        <tr>
          <td align="right"  nowrap="nowrap" class="title_item"><font color="red">*</font>产品品牌：</td>
          <td align="left">
            <html-el:text styleClass="webinput" title="点击选择产品品牌" property="brand_name" styleId="brand_name" style="cursor:pointer" readonly="true" maxlength="10" onclick="openChild()" />
            <html-el:hidden property="brand_id" styleId="brand_id" />
          </td>
        </tr>
        <tr id="jdxx">
          <td align="right" nowrap="nowrap" class="title_item"><font color="red" >*</font>产品型号：</td>
          <td align="left"><span id="pd_id_span"></span>
            <html-el:hidden property="pd_id" styleId="pd_id"/>
          </td>
        </tr>
        <tr id="divTr" style="display: none;">
          <td nowrap="nowrap" class="title_item">当前产品系统库存：
            <html-el:hidden property="cur_cost_price"  /></td>
          <td></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><font color="red">*</font>盘存数量：</td>
          <td>
            <html-el:text property="pc_num" styleId="pc_num" styleClass="webinput" maxlength="10"/>
          </td>
        </tr>
        <tr id="pc_result_tr">
          <td nowrap="nowrap" class="title_item">盘存结果：
            <html-el:hidden property="pc_result"  />
            <html-el:hidden property="py_pk_num"  />
          </td>
          <td></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">详细信息：</td>
          <td>
            <html-el:textarea property="pc_desc" styleClass="webtextarea" />
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">盘存日期：</td>
          <td>
            <html-el:text property="pc_date" styleClass="webinput" size="8" maxlength="10" readonly="true" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);"  />
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">备注：</td>
          <td>
            <html-el:text property="remark" styleClass="webinput" maxlength="100" style="width:60%" />
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <html-el:button property="save" styleClass="bgButtonSave" styleId="pc_submit" value="保 存"/>
            <input class="bgButtonBack" type="submit" name="pc_back" value="返 回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
var outSysIdHtml = '<select name="out_sys_id" id="out_sys_id" styleClass="bdfont"></select>'
var f = document.forms[0];                                          
var sv_count = -1;
var is_no_store_info = false;
var is_no_store_info_alram = '<font color="red">该型号产品没有库存信息，不能盘存！</font>';
var own_sys_name = "";
var type_name_ = $('#pd_type option:selected').text();

$(document).ready(function() {
	$("#pc_num").focus(setOnlyNum).attr("dataType", "Require").attr("msg", "请填写盘存数量,且必须是正整数！");
	$("#brand_name").attr("dataType","Require").attr("msg","请选择产品品牌！");
	
	$("#pc_num").blur(function(){
		setPcResult();
	});

	/*所属系统 Begin*/
	$("input[type='radio'][name='own_sys']").click(function(){
		$("#divTr").attr("style","display:none");//隐藏库存状态
		sv_count = -1;
		setPcResult();
		
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		var own_sys = $(this).val();
		if(0 == own_sys) { //非家电下乡
			own_sys_name = "非家电下乡";
			$("#pd_type option[value='0']").remove();
			$("#pd_type").get(0).options.add(new Option("其他", "0"));
			//删除产品大类select中重复选项 BEGIN
			var obj = $("#pd_type");

			//删除产品大类select中重复选项END
		} else { //家电下乡
			own_sys_name = "家电下乡";
			$("#pd_id_span").empty();
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
			$("#pd_type option[value='0']").remove();  
		}
	});
	/*所属系统 End*/
	
	/*产品类型 Begin*/
	$("#pd_type").change(function(){
		$("#divTr").attr("style","display:none");//隐藏库存状态
		sv_count = -1;
		setPcResult();
		
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		var pd_type = $(this).val();
		if(0 == pd_type) {
			$("#otherPdType").show();
			$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
		} else {
			type_name_ = $('#pd_type option:selected').text();
			
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
		}
	});
	/*产品类型 End*/
	
	/*其他产品类型 Begin*/
	$("#jxc_pd_type_id").change(function(){
		$("#divTr").attr("style","display:none");//隐藏库存状态
		sv_count = -1;
		setPcResult();
		type_name_ = $('#jxc_pd_type_id option:selected').text();
		
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
	});
	/*其他产品类型 End*/
	
	$("#pc_submit").click(function(){
		if("" == f.pd_id.value){
			alert("没有型号信息，不能提交！！");
			return false;
		}
		if(is_no_store_info){
			alert("该型号没有库存信息，不能提交！！");
			return false;
		}
		if (Validator.Validate(f, 3)){
			if(confirm("提交后,所属系统：【" + own_sys_name + "】、 类型：【" 
					+ type_name_ + "】、 品牌：【" 
					+ $('#brand_name').val() + "】、 型号：【" + $('#out_sys_id option:selected').text()
					 + "】 的产品库存数将更新为【" + $('#pc_num').val() + "】，是否提交？")){
				f.submit();
			}
		}
	});	
	
	$("input[type='radio'][name='own_sys']:checked").click();
	
});

/*取品牌Begin*/
function openChild(){
  $("#divTr").attr("style","display:none");//隐藏库存状态
  sv_count = -1;
  setPcResult();
  
  var pd_type = $("#pd_type").val();
  var returnValue = window.showModalDialog("JxcPcInfo.do?method=chooseBrand&pd_type="+pd_type+"&time="+new Date(), window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
  	if(returnValue != null) {
	var value = new Array();
	value = returnValue.split(",");
   	document.forms[0].brand_name.value = value[0];
    document.forms[0].brand_id.value = value[1];
  }
  var brand_id = $("#brand_id").val();
  getProductType(pd_type, brand_id);
}
/*取品牌End*/

function getProductType(pd_type, brand_id, out_sys_id){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (undefined != out_sys_id && 0 == own_sys && "" != "${af.map.name}" && out_sys_id < 0) {
		$("#pd_id_span").empty().append(nameHtml)
		return false;
	}
	var jxc_pd_type_id = $("#jxc_pd_type_id").val();

	$.ajax({
		type: "POST",
		url: 'JxcPcInfo.do',
		data: "method=getJxcPdList&pd_type=" + pd_type +"&brand_id=" + brand_id  + "&jxc_pd_type_id=" + jxc_pd_type_id + "&own_sys=" + own_sys + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
			$("#pd_id_span").empty();
			if (Dates.length > 1) {
				$("#pd_id_span").html(outSysIdHtml);
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name = Dates[i].name;
					$("#out_sys_id").get(0).options.add(new Option(md_name, Dates[i].values));
				}
				var el = $("#out_sys_id").multiselect({
					selectedList: 1,
					multiple: false,
					minWidth:320,
					click: function(event, ui){
				       if(ui.value != ""){
				    	   var arrays_v = ui.value.split("@#");
				    	   $("#pd_id").val(arrays_v[0]);
				    	   findStoreState(arrays_v[1]);
				    	   f.cur_cost_price.value = arrays_v[2];
				       }
				   	}
				}).multiselectfilter({width:220}).attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
				$("#out_sys_id option[value='${af.map.out_sys_id}']").attr("selected", "selected");
				var array = $("#out_sys_id").val().split("@#");
				findStoreState(array[1]);
				$("#pd_id").val(array[0]);
				f.cur_cost_price.value = array[2];
			} else {
				$("#pd_id").val("");
				$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有数据</span>');
			}
			$("#out_sys_id").get(0).val("");
		}
	});
}

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

function findStoreState(count){//查找当前型号产品实时库存数
	var divTr = $("#divTr");
	if("" == count){
		divTr.children().eq(1).html(is_no_store_info_alram);
		is_no_store_info = true;
	}else{
		is_no_store_info = false;
		divTr.children().eq(1).text(count);
	}
	divTr.attr("style","display:");
	sv_count = count;
	setPcResult();
}
function setPcResult(){
	$("#pc_result_tr").children().eq(1).text("");
	f.pc_result.value = "";

	var JQ_pd_num = $("#pc_num");
	var pc_num_ = JQ_pd_num.val();
	if(sv_count == -1 || "" == pc_num_){
		return null;
	}
	var reg = /^\d+$/;
	if (!reg.test(pc_num_)) {
		alert(JQ_pd_num.attr("msg"));
		JQ_pd_num.val("");
		return false;
	}
	var str = "";
	f.py_pk_num.value = pc_num_ - sv_count ;
	if(f.py_pk_num.value > 0){
		str = "盘前库存 " + sv_count + " 个，盘盈 " + (f.py_pk_num.value) + " 个。";
	}else{
		str = "盘前库存 " + sv_count + " 个，盘亏 " + (0 - f.py_pk_num.value) + " 个。";
	}
	$("#pc_result_tr").children().eq(1).text(str);
	f.pc_result.value = str;
};

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
