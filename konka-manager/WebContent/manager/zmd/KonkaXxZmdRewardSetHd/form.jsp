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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdRewardSetHd">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="hd_set_id" value="${af.map.hd_set_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司名称：</td>
          <td><strong class="fb">${fn:escapeXml(dept_name)}</strong></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>专卖店编号：</td>
          <td width="85%"><html-el:select property="zmd_id" styleId="zmd_id" style="width:154px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${zmdList}">
                <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>活动标题：</td>
          <td width="85%"><html-el:select property="hd_id" styleId="hd_id" style="width:325px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach items="${konkaXxZmdHdSetList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.hd_id}">（
                  <fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd HH:mm"/>
                  -
                  <fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd HH:mm"/>
                  ）${cur.hd_title}</html-el:option>
              </c:forEach>
            </html-el:select>
            <span style="color:blue"> 如果没有您要选的活动，请点击 </span>
            <input class="but2" type="button" name="add_hd" value="添加" onclick="location.href='${ctx}/manager/zmd/KonkaXxZmdHdSet.do?method=add&mod_id=860103';" /></td>
        </tr>
        <tr>
          <td align="right" width="15%" nowrap="nowrap" class="title_item" ><span style="color:#F00;">[必填]</span>反佣类型：</td>
          <td width="85%"><html-el:select property="reward_type" style="width:225px;" styleId="reward_type">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList70000}">
                <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td align="right" width="15%" nowrap="nowrap" class="title_item" ><span style="color:#F00;">[必填]</span>反佣比例(%)：</td>
          <td width="85%"><html-el:text property="reward_ratio" styleId="reward_ratio" maxlength="10" size="12" />
            &nbsp;(注：百分比值的范围：1-100)</td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#zmd_id").attr("dataType", "Require").attr("msg", "请选择专卖店！");
	$("#hd_id").attr("dataType", "Require").attr("msg", "请输入活动标题！");
	$("#reward_type").attr("dataType", "Require").attr("msg", "请选择反佣类型！");
	$("#reward_ratio").attr("dataType", "Require").attr("msg", "请输入反佣比例！").attr("focus",setOnlyNum);
	
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			
			if (isSubmit) {
				var reward_ratio = Number($.trim($("#reward_ratio").val()));
				if(reward_ratio < 0 || reward_ratio > 100){
					alert("反佣比例在1-100之间！");
					return false;
				} else {
					$(":button").attr("disabled", "true");
					$(":reset").attr("disabled", "true");
					this.form.submit();
				}
			}
	 });
	
});
function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		this.t_value = '';
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
