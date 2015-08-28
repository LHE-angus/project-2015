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
    <html-el:form action="/jf/JfRule">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readonly" value="${empty af.map.id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th colspan="2" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>积分规则信息</span></th>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
          <td width="88%" align="left">
          <c:if test="${readonly}">
              <html-el:hidden property="dept_id" styleId="${dept_id}" />
            <html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
           </c:if>
           <c:if test="${ not readonly}">
            <html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
           </c:if>
            </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>产品型号：</td>
          <td width="88%" align="left">
          	<c:if test="${readonly}">
          	<html-el:hidden property="pd_id" styleId="${pd_id}" />
          	<html-el:select property="pd_id" styleId="pd_id" disabled="${readonly}">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach items="${pdList}" var="cur">
                <html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </c:if>
            <c:if test="${ not readonly}">
          	<html-el:select property="pd_id" styleId="pd_id" disabled="${readonly}">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach items="${pdList}" var="cur">
                <html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </c:if>
            </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>积分类型：</td>
          <td width="88%" align="left"><html-el:select property="jf_type" styleId="jf_type">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="1">按数量固定返积分</html-el:option>
              <html-el:option value="2">按金额比例返积分</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font><span id="jf_value_title">积分值</span>：</td>
          <td width="88%" align="left"><html-el:text property="jf_value" size="8" maxlength="8" styleId="jf_value" />
          &nbsp;<span id="jf_value_desc" class="note"></span>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">有效期 ：</td>
          <td width="88%" align="left">
          	<c:if test="${readonly}">
          	<html-el:hidden property="jf_avl_type" styleId="${jf_avl_type}" />
          	<html-el:select property="jf_avl_type" styleId="jf_avl_type" disabled="${readonly}">
              <html-el:option value="1">长期有效</html-el:option>
              <html-el:option value="2">时间段有效</html-el:option>
            </html-el:select>
           	</c:if>
           	<c:if test="${ not readonly}">
          	<html-el:select property="jf_avl_type" styleId="jf_avl_type" disabled="${readonly}">
              <html-el:option value="1">长期有效</html-el:option>
              <html-el:option value="2">时间段有效</html-el:option>
            </html-el:select>
           	</c:if>
           </td>
        </tr>
        <tr id="avl_date_score" style="display:none;">
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>有效期范围：</td>
          <td width="88%" align="left">
          	<fmt:formatDate  var="_jf_avl_start" value="${af.map.jf_avl_start}" pattern="yyyy-MM-dd"/>
          	<html-el:text property="jf_avl_start" styleId="jf_avl_start" size="9" maxlength="10" value="${_jf_avl_start}" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" disabled="${readonly}"/>
            -
            <fmt:formatDate var="_jf_avl_end" value="${af.map.jf_avl_end}" pattern="yyyy-MM-dd"/>
            <html-el:text property="jf_avl_end" styleId="jf_avl_end" size="9" maxlength="10" value="${_jf_avl_end}" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);"  disabled="${readonly}"/></td>
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
	$("#pd_id").attr("dataType", "Require").attr("msg", "请选择产品型号");
	$("#jf_type").attr("dataType", "Require").attr("msg", "请选择积分类型");
	$("#jf_value").attr("dataType", "Require").attr("msg", "请填写积分值").focus(function(){setOnlyNum(this);});


	$("#jf_type").change(function(){
		var jf_avl_type = $(this).val();
		if(jf_avl_type == 1){
			$("#jf_value_title").html("积分值");
			$("#jf_value_desc").html("注：按照每销售一台固定返还积分数量");
		}else{
			$("#jf_value_title").html("积分比例（%）");
			$("#jf_value_desc").html("注：按照销售金额返还的比例值");
		}
	}).change();

	$("#jf_avl_type").change(function(){
		var jf_avl_type = $(this).val();
		if(jf_avl_type == 2){
			$("#jf_avl_start").attr("dataType", "Require").attr("msg", "请选择有效期开始时间");
			$("#jf_avl_end").attr("dataType", "Require").attr("msg", "请选择有效期结束时间");
			$("#avl_date_score").show();
		}else{
			$("#jf_avl_start").val("");
			$("#jf_avl_end").val("");
			$("#jf_avl_start").removeAttr("dataType");
			$("#jf_avl_end").removeAttr("dataType");
			$("#avl_date_score").hide();
		}
	}).change();


	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

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
