<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库存管理 &gt; 盘存管理</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcPcInfo">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="id" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">添加盘存记录</th>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><font color="red">*</font>所属仓库：</td>
          <td>
            <html-el:select property="store_id" styleId="store_id" onchange="findStoreState();">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
        </tr>
        <tr>
          <td width="18%" nowrap="nowrap" class="title_item"><font color="red">*</font>大类：</td>
          <td>
            <html-el:select property="cls_id" styleId="cls_id" onchange="addPePdModle(this);">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${pdClazzList}">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><font color="red">*</font>型号：</td>
          <td>
            <html-el:select property="pd_id" styleId="pd_id" onchange="findStoreState();">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select>
            <div style="display: none;" id="divOption">
              <html-el:select property="#">
                <html-el:option value="">请选择...</html-el:option>
              </html-el:select>
            </div>
          </td>
        </tr>
        <tr id="divTr" style="display: none;">
          <td nowrap="nowrap" class="title_item">当前产品系统库存:</td>
          <td></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><font color="red">*</font>盘存数量：</td>
          <td>
            <html-el:text property="pc_num" styleId="pc_num" styleClass="webinput" maxlength="10" />
          </td>
        </tr>
        <tr id="pc_result_tr">
          <td nowrap="nowrap" class="title_item">盘存结果：
            <html-el:hidden property="pc_result" />
            <html-el:hidden property="py_pk_num" />
          </td>
          <td>${af.map.pd_result}</td>
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
            <html-el:text property="pc_date" styleClass="webinput" size="12" maxlength="10" readonly="readonly" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);"  />
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
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];                                          
var sv_count = -1;
var is_no_store_info = false;
var is_no_store_info_alram = '<font color="red">该型号产品没有库存信息，不能盘存！</font>';

$(document).ready(function() {
	findStoreState();
	//addPePdModle($("#cls_id"));

	$("#cls_id").attr("dataType", "Require").attr("msg", "请填写大类！");
	$("#store_id").attr("dataType", "Require").attr("msg", "请填写型号！");
	$("#pd_id").attr("dataType", "Require").attr("msg", "请填写型号！");
	$("#pc_num").focus(setOnlyNum).attr("dataType", "Number").attr("msg", "请填写盘存数量,且必须是正整数！");
	
	$("#pc_num").bind("blur", function(){
		setPcResult();
	});
});         
function findStoreState(){//查找当前型号产品实时库存数
	var pd_id = $("#pd_id").val();
	var store_id = $("#store_id").val();
	if(0 < pd_id && 0 < store_id){
		$.ajax({
			type:"POST",
			url:"KonkaJxcPcInfo.do",
			data:"method=getStoreState&pd_id=" + pd_id + "&store_id=" + store_id,
			dataType:"json",
			error:function(request,settings){alert("数据加载失败！");},
			success:function(count){
				var divTr = $("#divTr");
				if("-9" == count[0].pd_num){
					divTr.children().eq(1).html(is_no_store_info_alram);
					is_no_store_info = true;
				}else{
					is_no_store_info = false;
					divTr.children().eq(1).text(count[0].pd_num);
				}
				divTr.attr("style","display:");
				sv_count = count[0].pd_num;
				setPcResult();
				//divTr.style.display = "";
			}
		});	
	}else {
		sv_count = -1;
		setPcResult();
		$("#divTr").attr("style","display: none;");
	}
}               

function addPePdModle(obj){//根据品牌查找型号列表，并添加至型号select
	var pd_big_type = $(obj).val();
	//if("" != pd_big_type){
		$.ajax({
			type:"POST",
			url:"KonkaJxcPcInfo.do",
			data:"method=getPePdModle&pd_big_type=" + pd_big_type,
			dataType:"json",
			error:function(request,settings){alert("数据加载失败！");},
			success:function(pdModles){
				$baseOption = $("#divOption").children().children();
				$pdSelect = $("#pd_id");
				$pdSelect.empty();
				$baseOption.eq(0).clone().appendTo($pdSelect);
				for (var i=0;i<pdModles.length - 1;i++){
					var cur = pdModles[i];
					$newOption = $baseOption.eq(0).clone().appendTo($pdSelect);
					$newOption.val(cur.pd_id);
					$newOption.text(cur.md_name);
				}
				$pdSelect.val("");
			}
		});	
	//}
	$("#pd_id").val("");
	findStoreState();
}       

$("#pc_submit").click(function(){
	if(is_no_store_info){
		alert("该型号没有库存信息，不能提交！！");
		return false;
	}
	
	if(Validator.Validate(this.form, 3)){
		if(confirm("提交后,仓库：【" + $('#store_id option:selected').text() + "】、 大类：【" 
				+ $('#cls_id option:selected').text() + "】、 型号：【" + $('#pd_id option:selected').text()
				 + "】 的产品库存数将更新为【" + $('#pc_num').val() + "】，是否提交？")){
		
			this.form.submit();
		}
	}
});

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
	f.py_pk_num.value = pc_num_ - sv_count;
	if(f.py_pk_num.value > 0){
		str = "实时库存 " + sv_count + " 个，盘盈 " + (f.py_pk_num.value) + " 个。";
	}else{
		str = "实时库存 " + sv_count + " 个，盘亏 " + (0 - f.py_pk_num.value) + " 个。";
	}
	$("#pc_result_tr").children().eq(1).text(str);
	f.pc_result.value = str;
};

function setOnlyNum() {
	$(this).css("ime-mode","disabled");
	$(this).attr("t_value","");
	$(this).attr("o_value","");
	$(this).bind("dragenter",function () {
		return false;
	});
	$(this).keypress(function () {
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;
		else this.t_value=this.value;
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function () {
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;
		else this.t_value=this.value;
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function () {
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;
		else {
			if(this.value.match(/^\.\d+$/))this.value=0+this.value;
			if(this.value.match(/^\.$/))this.value=0;
			this.o_value=this.value;
		}
		if(this.value.length==0)this.value="";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
