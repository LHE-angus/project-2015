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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  <html-el:form action="/zmd/KonkaXxGoods">
    <html-el:hidden property="goods_id" value="${af.map.goods_id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="t" styleId="t" value="new Date().getTime();"/>
    <html-el:hidden property="queryString" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">物料名称：</td>
          <td><html-el:text property="goods_name" styleId="goods_name" style="width:120px;" size="40" maxlength="20" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">仓位：</td>
          <td>
          	<html-el:select property="store" styleId="store">
          		<html-el:option value="">请选择</html-el:option>
          		<c:forEach items="${storeList}" var="cur">
          			<c:if test="${cur.id eq af.map.store}">
          				<option value="${cur.id}" selected="selected">${cur.store_name}</option>
          			</c:if>
          			<c:if test="${cur.id ne af.map.store}">
          				<option value="${cur.id}">${cur.store_name}</option>
          			</c:if>
          		</c:forEach>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">数量：</td>
          <td><html-el:text property="counts" styleId="counts" style="width:120px;" size="26" maxlength="8" onkeyup="javascript:setOnlyNum(this);"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">备注：</td>
          <td><html-el:textarea property="memo" styleId="memo" cols="60" rows="4"></html-el:textarea></td>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#goods_name").attr("dataType" , "Require").attr("msg" , "请输入物料名称！");
	$("#store").attr("dataType" , "Require").attr("msg" , "请选择仓位！");
	$("#counts").attr("dataType" , "Require").attr("msg" , "请输入数量！");
	
	//$("#goods_name").focus(setLength).attr("len", 40);
	$("#memo").attr("dataType", "Limit").attr("max", "200").attr("msg", "备注不能超过200个文字");
	//$("#memo").focus(setLength).attr("len", 200);
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if(isSubmit){
			$("#t").val(new Date().getTime());
			this.form.submit();
		}
	});
});
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
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
