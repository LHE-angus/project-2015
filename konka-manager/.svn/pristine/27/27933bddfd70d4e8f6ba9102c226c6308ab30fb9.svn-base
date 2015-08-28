<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
  <div class="rtabcont2">
  <html-el:form action="/zmd/KonkaXxZmdDemomac">
    <html-el:hidden property="id" value="${af.map.id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="queryString" />
    <c:set var="readonly" value="false" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:#F00;">[必填]</span>专卖店：</td>
          <td><select name="zmdList" id="zmdList" multiple="multiple" >
				<c:forEach items="${zmdList}" var="cur" varStatus="vs">
					<option value="${cur.zmd_id }"<c:if test="${cur.zmd_id eq zmd_id }">selected="selected"</c:if>>${cur.zmd_sn}</option>
				</c:forEach>
			</select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:#F00;">[必填]</span>型号：</td>
          <td><select name="konkaXxPdList" id="konkaXxPdList" multiple="multiple">
				<c:forEach items="${konkaXxPdList}" var="cur" varStatus="vs">
					<option value="${cur.md_name }" <c:if test="${cur.md_name eq md_name}">selected="selected"</c:if>>${cur.md_name}</option>
				</c:forEach>
			</select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:#F00;">[必填]</span>数量：</td>
          <td><html-el:text property="counts" styleId="counts" style="width:120px;" size="26" maxlength="20" onkeyup="javascript:setOnlyNum(this);" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">状态：</td>
          <td><html-el:select property="state" value="60200">
	          <html-el:option value="60100">未上样</html-el:option>
	          <html-el:option value="60200">上样</html-el:option>
	          <html-el:option value="60300">下样</html-el:option></html-el:select><span style="color:#F00;">[默认上样]</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">上样时间：</td>
          <td><html-el:text property="up_date" styleId="up_date"  size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.up_date}'/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">下样时间：</td>
          <td><html-el:text property="down_date" styleId="down_date" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.down_date}' /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">备注：</td>
          <td><html-el:textarea property="memo" styleId="memo" cols="60" rows="3"></html-el:textarea></td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but4" type="button" name="Submit4" id="send" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>

<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#up_date").datepicker();
	$("#down_date").datepicker();
	$("#zmdList").attr("dataType" , "Require").attr("msg" , "请选择专卖店！");
	$("#konkaXxPdList").attr("dataType" , "Require").attr("msg" , "请选择样机型号！");
	$("#counts").attr("dataType" , "Require").attr("msg" , "请输入样机数量！");
	$("#konkaJxcStoreInfoList").attr("dataType" , "Require").attr("msg" , "请选择仓位！");
	$("#memo").attr("dataType", "Limit").attr("max", "200").attr("msg", "备注不能超过200个文字");
	//$("#memo").focus(setLength).attr("len", 200);
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if(isSubmit){
			this.form.submit();
		}
	});
	//选择专卖店
	$("#zmdList").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();
	//选择型号
	$("#konkaXxPdList").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();
	//选择仓库
	$("#konkaJxcStoreInfoList").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();//.attr("datatype", "Ms").attr("msg", "请选择商品！");
	
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
		if(this.value.length == 0) this.value = "0";
	});
}

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}

function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	});
}
function openChild(num){
	if(num == 0) {
	    var returnValue = window.showModalDialog("KonkaXxZmdDemomac.do?method=forwardToKonkaZmdList&t=" + new Date().getTime(), window, "dialogWidth:550px;status:no;dialogHeight:340px");
	    if(returnValue != null) {
			var value = new Array();
			value = returnValue.split(",");
	        document.forms[0].zmd_sn.value = value[0];
	        document.forms[0].zmd_id.value = value[1];
	    } 
	}else if(num == 1) {
	    var returnValue = window.showModalDialog("KonkaXxZmdDemomac.do?method=forwardToKonkapdlist&t=" + new Date().getTime(), window, "dialogWidth:550px;status:no;dialogHeight:340px");
	    if(returnValue != null) {
			var value = new Array();
			value = returnValue.split(",");
	        document.forms[0].md_name.value = value[0];
	        document.forms[0].dept_pd_id.value = value[1];
	    } 
	}else if(num == 2) {
	    var returnValue = window.showModalDialog("KonkaXxZmdDemomac.do?method=forwardToKonkaStorelist&t=" + new Date().getTime(), window, "dialogWidth:550px;status:no;dialogHeight:340px");
	    if(returnValue != null) {
			var value = new Array();
			value = returnValue.split(",");
	        document.forms[0].from_store_name.value = value[0];
	        document.forms[0].from_store.value = value[1];
	    } 
	}
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
