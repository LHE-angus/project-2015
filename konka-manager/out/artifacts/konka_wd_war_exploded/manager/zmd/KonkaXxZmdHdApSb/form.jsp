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
    <html-el:form action="/zmd/KonkaXxZmdHdApSb">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="sp_hd_id" value="${af.map.sp_hd_id}" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
      <html-el:hidden property="plan_money" styleId="plan_money" value="${af.map.plan_money}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>活动名称：</td>
          <td colspan="3" align="left"><html-el:text property="hd_name" styleId="hd_name" maxlength="40" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>专卖店客户：</td>
          <td colspan="3" align="left"><html-el:select property="zmd_id" styleId="zmd_id" style="width:225px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${entityList}">
                <html-el:option value="${cur.zmd_id}">${fn:escapeXml(cur.zmd_sn)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:#F00;width: 120px;">[必填]</span>活动开展时间：</td>
          <td colspan="3" align="left"><html-el:text property="plan_start_date" styleId="plan_start_date" size="14" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.plan_start_date}'/>
            至
            <html-el:text property="plan_end_date" styleId="plan_end_date" size="14" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.plan_end_date}' /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>活动地点：</td>
          <td colspan="3" align="left"><html-el:text property="hd_addr" styleId="hd_addr" maxlength="100" style="width:365px;"/></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">活动计划投入费用：</td>
          <td colspan="3" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
              <tr>
                <td colspan="2" align="left"><span style="color: red;">注：计划投入资源的费用单位为元！</span></td>
                <td colspan="2">&nbsp;</td>
              </tr>
              <c:forEach items="${res_list}" var="cur">
                <tr>
                  <td width="15%"><c:if test="${!empty res_null}">${cur.type_name}</c:if>
                    <c:if test="${empty res_null}">${cur.tr_res_name}</c:if></td>
                  <td align="left" width="35%"><c:if test="${!empty res_null}">
                      <html-el:text property="tr_res_money_${cur.type_id}" styleId="tr_res_money_${cur.type_id}" style="width:70%;" maxlength="10" styleClass="tr_res_money" />
                    </c:if>
                    <c:if test="${empty res_null}">
                      <html-el:text property="tr_res_money_${cur.tr_res_id}" value="${cur.tr_res_money}" styleId="tr_res_money_${cur.tr_res_id}" style="width:70%;" maxlength="10" styleClass="tr_res_money" />
                    </c:if></td>
                  <td colspan="2">&nbsp;</td>
                </tr>
              </c:forEach>
              <tr>
                <td>合计（万元）</td>
                <td><span id = "hj_money"></span></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>计划产出（万元）：</td>
          <td colspan="3" align="left"><html-el:text property="plan_outputs_money" styleId="plan_outputs_money" maxlength="16" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>负责人：</td>
          <td width="35%"><html-el:text property="plan_user_name" styleId="plan_user_name" maxlength="20" /></td>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">负责人电话：</td>
          <td width="35%"><html-el:text property="plan_user_tel" styleId="plan_user_tel" maxlength="20" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
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
	$("#plan_start_date").datepicker();
	$("#plan_end_date").datepicker();
	//$("#hd_name").attr("dataType", "Require").attr("msg", "请填写活动名称！");
	$("#zmd_id").attr("dataType", "Require").attr("msg", "请选择专卖店！");
	$("#hd_addr").attr("dataType", "Require").attr("msg", "请填写活动地点！");
	$("#plan_start_date").attr("dataType", "Require").attr("msg", "请选择活动起始时间！");
	$("#plan_end_date").attr("dataType", "Require").attr("msg", "请选择活动结束时间！");
	$("#plan_outputs_money").attr("dataType", "Require").attr("msg", "请填写计划产出！");
	$("#plan_user_name").attr("dataType", "Require").attr("msg", "请填写计划负责人！");
	$("#plan_outputs_money").attr("focus",setOnlyNum);
	$(".res_pro").attr("focus",setOnlyNum);

	$(".tr_res_money").blur(function(){
		var money = 0;
		$(".tr_res_money").each(function(){
			if(this.value.length > 0){
				money += parseFloat(this.value);
			}
		});
		$("#hj_money").text(money/10000);
		$("#plan_money").val(money/10000);
	});

	if(null != '${af.map.plan_money}'){
		$("#hj_money").text('${af.map.plan_money}');
	}

	$("#hd_name").blur(function(){
		var $this = $("#hd_name");
		if(this.value.length>0){
			var param = {
					hd_name : this.value
				};
			$.post("${ctx}/manager/zmd/KonkaXxZmdHdApSb.do?method=validateHdName", param, function(isExist) {
				$("#tip_msg").remove();
				if ("0" == isExist) {
					$this.after("<span id=\'tip_msg\' style=\'color:green;margin-left:5px;\'> *恭喜您，该活动名称可以使用！</span>");
				} else {
					$this.after("<span id=\'tip_msg\' style=\'color:#F00;margin-left:5px;\'> *对不起，该活动名称已存在，请重新填写！</span>");
				}
			});
		}
	});
	
	$("#send").click(function(){
			$("#tip_msg").remove();
			if($("#hd_name").val().length == 0){
				$("#hd_name").after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\"> *请填写活动名称！</span>");
			}
					
			var isSubmit = Validator.Validate(this.form, 3);
			
			var plan_start_date = $("#plan_start_date").val().replace(/-/g, '');
			var plan_end_date = $("#plan_end_date").val().replace(/-/g, '');
			if(plan_start_date > plan_end_date){
				alert("计划结束时间必须大于计划开始时间！");
				return false;
			}
			
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });

	//选择专卖店
	$("#zmd_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();
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
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
