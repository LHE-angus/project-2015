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
    <html-el:form action="/zmd/KonkaXxZmdHdSet">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td><strong class="fb">${dept_name}</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>活动标题：</td>
          <td><html-el:text property="hd_title" styleId="hd_title" size="50" maxlength="30"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>活动开始时间：</td>
          <td><html-el:text property="start_date" styleId="start_date"  size="16" maxlength="16" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.start_date}'/>
            &nbsp;
            <html-el:select property="start_date_hh" styleId="start_date_hh">
              <html-el:option value="00">00</html-el:option>
              <html-el:option value="01">01</html-el:option>
              <html-el:option value="02">02</html-el:option>
              <html-el:option value="03">03</html-el:option>
              <html-el:option value="04">04</html-el:option>
              <html-el:option value="05">05</html-el:option>
              <html-el:option value="06">06</html-el:option>
              <html-el:option value="07">07</html-el:option>
              <html-el:option value="08">08</html-el:option>
              <html-el:option value="09">09</html-el:option>
              <html-el:option value="10">10</html-el:option>
              <html-el:option value="11">11</html-el:option>
              <html-el:option value="12">12</html-el:option>
              <html-el:option value="13">13</html-el:option>
              <html-el:option value="14">14</html-el:option>
              <html-el:option value="15">15</html-el:option>
              <html-el:option value="16">16</html-el:option>
              <html-el:option value="17">17</html-el:option>
              <html-el:option value="18">18</html-el:option>
              <html-el:option value="19">19</html-el:option>
              <html-el:option value="20">20</html-el:option>
              <html-el:option value="21">21</html-el:option>
              <html-el:option value="22">22</html-el:option>
              <html-el:option value="23">23</html-el:option>
            </html-el:select>
            时
            <html-el:select property="start_date_mm" styleId="start_date_mm">
              <html-el:option value="00">00</html-el:option>
              <html-el:option value="05">05</html-el:option>
              <html-el:option value="10">10</html-el:option>
              <html-el:option value="15">15</html-el:option>
              <html-el:option value="20">20</html-el:option>
              <html-el:option value="25">25</html-el:option>
              <html-el:option value="30">30</html-el:option>
              <html-el:option value="35">35</html-el:option>
              <html-el:option value="40">40</html-el:option>
              <html-el:option value="45">45</html-el:option>
              <html-el:option value="50">50</html-el:option>
              <html-el:option value="55">55</html-el:option>
            </html-el:select>
            分 </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>活动结束时间：</td>
          <td><html-el:text property="end_date" styleId="end_date" size="16" maxlength="16" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.end_date}' />
            &nbsp;
            <html-el:select property="end_date_hh" styleId="end_date_hh">
              <html-el:option value="00">00</html-el:option>
              <html-el:option value="01">01</html-el:option>
              <html-el:option value="02">02</html-el:option>
              <html-el:option value="03">03</html-el:option>
              <html-el:option value="04">04</html-el:option>
              <html-el:option value="05">05</html-el:option>
              <html-el:option value="06">06</html-el:option>
              <html-el:option value="07">07</html-el:option>
              <html-el:option value="08">08</html-el:option>
              <html-el:option value="09">09</html-el:option>
              <html-el:option value="10">10</html-el:option>
              <html-el:option value="11">11</html-el:option>
              <html-el:option value="12">12</html-el:option>
              <html-el:option value="13">13</html-el:option>
              <html-el:option value="14">14</html-el:option>
              <html-el:option value="15">15</html-el:option>
              <html-el:option value="16">16</html-el:option>
              <html-el:option value="17">17</html-el:option>
              <html-el:option value="18">18</html-el:option>
              <html-el:option value="19">19</html-el:option>
              <html-el:option value="20">20</html-el:option>
              <html-el:option value="21">21</html-el:option>
              <html-el:option value="22">22</html-el:option>
              <html-el:option value="23">23</html-el:option>
            </html-el:select>
            时
            <html-el:select property="end_date_mm" styleId="end_date_mm">
              <html-el:option value="00">00</html-el:option>
              <html-el:option value="05">05</html-el:option>
              <html-el:option value="10">10</html-el:option>
              <html-el:option value="15">15</html-el:option>
              <html-el:option value="20">20</html-el:option>
              <html-el:option value="25">25</html-el:option>
              <html-el:option value="30">30</html-el:option>
              <html-el:option value="35">35</html-el:option>
              <html-el:option value="40">40</html-el:option>
              <html-el:option value="45">45</html-el:option>
              <html-el:option value="50">50</html-el:option>
              <html-el:option value="55">55</html-el:option>
            </html-el:select>
            分</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">活动内容：</td>
          <td><html-el:textarea property="hd_contend" styleId="hd_contend"  style="width:380px;"/>
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#start_date").datepicker();
	$("#start_date").datepicker('option','yearRange','2012:2032'); //设置日期控件的年份范围
	
	$("#end_date").datepicker();
	$("#end_date").datepicker('option','yearRange','2012:2032');
	
	$("#start_date").attr("dataType", "Require").attr("msg", "请填写活动开始时间！");
	$("#end_date").attr("dataType", "Require").attr("msg", "请填写活动结束时间！");
	$("#hd_title").attr("dataType", "Require").attr("msg", "请填写活动标题！");
	
	$("#hd_contend").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
	});
	
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			var start_date_val = $("#start_date").val()+" "+$("#start_date_hh").val()+":"+$("#start_date_mm").val()+":00";
			var end_date_val = $("#end_date").val()+" "+$("#end_date_hh").val()+":"+$("#end_date_mm").val()+":00";
			  if (isSubmit) {
					if(confirm("您确认要添加标题为 "+'"'+$("#hd_title").val()+'"'+" 时间为 "+start_date_val+" 至 "+end_date_val+" 的活动吗？"))
					   {
						$(":button").attr("disabled", "true");
						$(":reset").attr("disabled", "true");
						this.form.submit();
					    }else{
							return false;
						}
					}
	 });
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
