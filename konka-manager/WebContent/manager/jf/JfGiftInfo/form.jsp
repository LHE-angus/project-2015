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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
    <html-el:form action="/jf/JfGiftInfo" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th colspan="2" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>礼品信息</span></th>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
          <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>礼品名称：</td>
          <td width="88%" align="left">
          	<html-el:text property="gift_name" styleId="gift_name" size="40" maxlength="15"/>
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>兑换积分：</td>
          <td width="88%" align="left">
          	<html-el:text property="scorts" styleId="scorts" size="40" maxlength="8"/>
          	<span class="note">注：每件礼品可兑换的积分</span>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>数量：</td>
          <td width="88%" align="left">
          <html-el:text property="num" styleId="num" size="40" maxlength="10"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">上传礼品图片：</td>
          <td width="88%"><c:if test="${not empty (af.map.image_src)}" var="hasImage"><a href="${ctx}/${af.map.image_src}" target="_blank"><img src="${ctx}/${fn:substringBefore(af.map.image_src, '.')}_240.jpg" style="border:0;" width="240" height="180" /></a> <br />
              <label for="chkReUploadImage">
              <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#image_src').toggle();" />
              重新上传主图</label>
              <br />
              <html-el:file property="image_src" style="display:none;width:500px;" styleId="image_src" />
            </c:if>
            <c:if test="${empty (af.map.image_src)}">
              <html-el:file property="image_src" style="width:500px;" styleId="image_src" />
            </c:if>
            <div class="note">1、支持图片格式：jpg，png，gif；</div>
            <div class="note">2、上传的图片会自动缩放成合适的尺寸，主图宽高比例最好是1:1，否则会变形；</div></td>
        </tr>
        <tr id="avl_date_score" style="display:none;">
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>添加时间：</td>
          <td width="88%" align="left">
          	<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" var="_add_date" />
				<html-el:text property="add_date" styleId="add_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_add_date}"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司");
	$("#gift_name").attr("dataType", "Require").attr("msg", "请填写礼品名称");
	$("#scorts").attr("dataType", "Require").attr("msg", "请填写可兑换的积分").focus(function(){setOnlyInt(this);});
	$("#num").attr("dataType", "Require").attr("msg", "请填写礼品数量").focus(function(){setOnlyInt(this);});
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

//正则表达式：只能输入整数
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

//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
