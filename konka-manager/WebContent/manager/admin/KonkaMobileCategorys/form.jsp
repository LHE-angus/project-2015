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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
        <html-el:form action="/admin/KonkaMobileCategorys">
          <html-el:hidden property="mod_id" styleId="mod_id" />
          <html-el:hidden property="method" styleId="method" value="save" />
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="order_value" styleId="order_value" value="0" />
           <html-el:hidden property="c_index" styleId="c_index" />
          <table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
            <tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right"><strong>子类别名称：</strong><font color="red">*</font></td>
              <td><html-el:text property="c_name" size="80" maxlength="80" styleId="c_name" /></td>
            </tr>
            <tr>
               <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right"><strong>说明：</strong></td>
              <td><html-el:textarea property="c_comm" rows="8" cols="80" styleId="c_comm" /></td>
            </tr>
            <tr>
               <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right"><strong>排序值：</strong><font color="red">*</font></td>
              <td><html-el:text property="order_sort" size="4" maxlength="4" styleId="order_sort" /></td>
            </tr>
            <tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right"><strong>子类别状态：</strong></td>
              <td><html-el:select property="is_del">
                  <html-el:option value="0">正常(未删除)</html-el:option>
                  <html-el:option value="1">已删除</html-el:option>
                </html-el:select></td>
            </tr>
             <tr>
             <td nowrap="nowrap" height="28" class="title_item" align="right"><strong>锁定：</strong></td>
              <td><html-el:select property="is_lock">
                  <html-el:option value="0">未锁定</html-el:option>
                  <html-el:option value="1">已锁定</html-el:option>
                </html-el:select></td>
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
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#c_name"    ).attr("dataType", "Require").attr("msg", "请填写子类别名称");
	$("#c_name_1"    ).attr("dataType", "Require").attr("msg", "请选择父类别");
	$("#c_comm" ).attr({"dataType":"Limit","min":"0","max":"500","msg":"说明不能多于500个"});
	$("#solve_method" ).attr({"dataType":"Limit","min":"1","max":"500","msg":"请填写排除办法且字数少于500个"});
	$("#order_sort").attr("dataType", "Require").attr("msg", "请填写排序值").focus(setOnlyNum);
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function changePar_index(){
	if($("#c_index").val() ==$("#par_index").val()) {
		
		alert("父级目录不能选择自己，请重新选择！");
		$("#par_index").val("${af.map.par_index}");
	    return null;
	}
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	})
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = 0;
	})
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
