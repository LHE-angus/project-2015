<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货管理 &gt; 进货登记</title>
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
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStockBill">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="sn" styleId="sn"/>
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="7">进货登记</th>
        </tr>
        <c:if test="${roleId eq 30}">
          <tr>
            <td class="title_item">上级部门：</td>
            <td colspan="6"><c:if test="${add}">${fn:escapeXml(peDept.dept_name)}
                <html-el:hidden property="part_dept_id" styleId="part_dept_id" value="${peDept.dept_id}"/>
              </c:if>
              <c:if test="${update}"> ${fn:escapeXml(af.map.map.dept_name)} </c:if></td>
          </tr>
        </c:if>
        <tr>
          <td class="title_item"><font color="red">*</font>进货日期：</td>
          <td colspan="2"><c:if test="${add}">
              <html-el:text property="add_date" styleId="add_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" />
            </c:if>
            <c:if test="${update}">
              <fmt:formatDate value="${af.map.add_date }" pattern="yyyy-MM-dd"></fmt:formatDate>
            </c:if></td>
          <td class="title_item"><font color="red">*</font>进货编号：</td>
          <td colspan="3"><font color="red">NO:${af.map.sn}</font></td>
        </tr>
        <tr class="title_top">
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">进货仓库</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">产品类型</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">产品型号</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">数量</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">单价</font></td>
          <td align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">备注</font></td>
          <td width="5%" align="center" bgcolor="#fff2dc"><html-el:button styleClass="bgButton" property="addPdInput" styleId="addPdInput" value="添加"  style="cursor: pointer;"></html-el:button></td>
        </tr>
        <tr id="pdmodel" class="hasPdmodel" style="display: none;">
          <td align="center"><html-el:select property="store_id" styleId="store_id" style="width:130px;">
              <option value="">请选择...</option>
              <c:forEach items="${storeList}" var="cur">
                <option value="${cur.id}">${cur.store_name}</option>
              </c:forEach>
            </html-el:select></td>
          <td align="center"><html-el:select property="cls_id" styleId="cls_id" style="width:130px;">
              <option value="">请选择...</option>
              <c:forEach items="${basePdClassList}" var="cur">
                <option value="${cur.cls_id}">${cur.tree_name}</option>
              </c:forEach>
            </html-el:select></td>
          <td align="center"><html-el:hidden property="pd_id" styleId="pd_id" style="width:160px;"/>
            <html-el:select property="pd_id_values" styleId="pd_id_values">
              <option value="">请选择...</option>
            </html-el:select></td>
          <td align="center"><html-el:hidden property="maxPdCount" styleId="maxPdCount"/>
            <html-el:text property="count" value="" styleClass="webinput" styleId="jh_count" maxlength="8" size="6"></html-el:text></td>
          <td align="center"><html-el:text property="price" value="" styleClass="webinput" styleId="jh_price" maxlength="10" size="6"></html-el:text></td>
          <td align="center" height="30"><html-el:text property="remark" styleId="remark" styleClass="webinput" maxlength="130"></html-el:text></td>
          <td align="center"  style="cursor: pointer;"><html-el:button styleClass="bgButton" property="del" value="删除"  style="cursor: pointer;" /></td>
        </tr>
        <tbody id="tbody_jhdj">
        </tbody>
        <tr><td colspan="7">&nbsp;<span id="store_tip"></span></td></tr>
        <tr>
          <td colspan="7" align="center"><input type="button" name="save" class="bgButtonSave" value=" 保 存 " id="btn_submit"/>
            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/>
            <html-el:button property="back" styleId="back" value=" 返回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
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
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
var f = document.forms[0];                                          
                                        
$(document).ready(function(){
	$("#add_date").attr("datatype", "Require").attr("msg", "请选择进货日期！");
	
	// 提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		$("#tip").remove();
		var repeat = isRepeat();
		if (repeat) {
			alert("添加的型号有重复，请重新选择！");
			return false;
		}
		if ($("#pd_id", "#tbody_jhdj").length == 0) {
			alert("请至少添加一个产品进货信息！");
	        return false;
		}
		
		if (Validator.Validate(this.form, 1)){
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
	        $("#back").attr("disabled", "true");
			f.submit();
		}
	});

	$("#addPdInput").click(function(){
		$("#pdmodel").clone().appendTo($("#tbody_jhdj")).show();
		
		var lastTR = $("tr:last", "#tbody_jhdj");
		var JQ_store_id= $("#store_id", lastTR);//仓库
		var JQ_pd_count = $("#jh_count", lastTR);//进货数量
		var JQ_price = $("#jh_price", lastTR);//进货单价
		var JQ_cls_id = $("#cls_id", lastTR);//类型
		var JQ_pd_id = $("#pd_id_values", lastTR);//型号
		var JQ_hidden_pd_id = $("#pd_id", lastTR);//隐藏型号
		var JQ_maxPdCount = $("#maxPdCount", lastTR);//隐藏最大库存
		
		
		JQ_store_id.attr("dataType", "Require").attr("msg", "请选择仓库");
		JQ_cls_id.attr("dataType", "Require").attr("msg", "请选择类型");
		JQ_pd_id.attr("dataType", "Require").attr("msg", "请选择型号");
		JQ_pd_count.attr("dataType", "Integer").attr("msg", "请填写数量,且必须是整数");
		JQ_price.attr("dataType","Currency").attr("msg","请填写进货单价，且必须为正数！");

		//取型号
		doSelectAjaxForTwoPars("CsAjax.do", JQ_store_id, JQ_cls_id, "", JQ_pd_id, "", "getPePdModelListBySidAndDidAndCid");

		//动态清空类型、型号下拉列表
		JQ_store_id.change(function(){
			$("#store_tip").empty();
			JQ_cls_id.val("");
			JQ_pd_id.val("");
		});
		JQ_cls_id.change(function(){
			$("#store_tip").empty();
			JQ_pd_id.val("");
		});
		
		JQ_pd_count.focus(function(){
			if (JQ_store_id.val() == "") {return false;}
			if (JQ_cls_id.val() == "") {return false;}
			if (JQ_pd_id.val() == "") {return false;}
			var pdIdValues = JQ_pd_id.val().split("@#");
			JQ_hidden_pd_id.val(pdIdValues[0]);
			JQ_maxPdCount.val(pdIdValues[1]);
			var selectPdName = $("option:selected", JQ_pd_id).text();// JQ_pd_id(':selected').text();
			$("#store_tip").empty().append('<span id="tip" style="color:#f00;">产品：[' + selectPdName + ']当前库存数量为:' + JQ_maxPdCount.val() + '</span>');
		}).keyup(function(){
			$("#thInfo", lastTR).remove();
			if (JQ_store_id.val() == "") {return false;}
			if (JQ_cls_id.val() == "") {return false;}
			if (JQ_pd_id.val() == "") {return false;}
			//填写的发货数量
			var thisCount = parseFloat($(this).val());
			if(isNaN(thisCount)) thisCount = 0;
			//取库存数量
			if(Number(thisCount) + Number(JQ_maxPdCount.val()) < 0){
				alert("您输入的退货产品数量超出了当前库存数量，导致该产品库存不足，请重新输入！");
				thisCount = 1;
			}
			if(thisCount < 0){
				$(this).after("<span id='thInfo' title='退货：当输入的数量为负数时。'><img src='${ctx}/styles/jxc/images/th.gif' style='vertical-align: text-bottom;padding-left:5px;'/></span>");
			}
			this.value = thisCount;
		});
		
		//删除按钮
	   	$("td:last", lastTR).click(function (){
	    	$(this).parent().remove();
	    	}).css("cursor", "pointer");
	});

	 
});

function isRepeat(){
	var flag = false;
	var arrays_v = [];
	$("tr", "#tbody_jhdj").each(function(){
		var _thisTr = $(this);
		var store_id = $("#store_id", _thisTr).val();
		var cls_id = $("#cls_id", _thisTr).val();
		var pd_id = $("#pd_id", _thisTr).val();
		if ("" == store_id || "" == cls_id || "" == pd_id) {
			return false;
		}
		var a_v = [];
		a_v.push(store_id);
		a_v.push(cls_id);
		a_v.push(pd_id);
		arrays_v.push(a_v.join(","));
	});
	
	var sort_arrays_v = arrays_v.sort();
	for(var i = 0; i < sort_arrays_v.length; i++) {
		if (sort_arrays_v[i] == sort_arrays_v[i + 1]) {
			flag = true;
			break;
		}  
    }
	return flag;
}
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
