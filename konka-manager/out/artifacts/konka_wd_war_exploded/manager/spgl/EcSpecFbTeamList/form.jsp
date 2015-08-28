<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcSpecFbTeamList" enctype="multipart/form-data" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>球队名称：</td>
          <td width="88%" align="left"><html-el:text property="team_name" styleId="team_name" size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>球队组名：</td>
          <td width="88%" align="left">
            <html-el:select property="group_name" styleId="group_name" style="width:120px;">
       		   <html-el:option value="A组">A组</html-el:option>
                <html-el:option value="B组">B组</html-el:option>
				<html-el:option value="C组">C组</html-el:option>
				<html-el:option value="D组">D组</html-el:option>
				<html-el:option value="E组">E组</html-el:option>
				<html-el:option value="F组">F组</html-el:option>
				<html-el:option value="G组">G组</html-el:option>
				<html-el:option value="H组">H组</html-el:option>
				<html-el:option value="81A组">81A组</html-el:option>
				<html-el:option value="81B组">81B组</html-el:option>
				<html-el:option value="81C组">81C组</html-el:option>
				<html-el:option value="81D组">81D组</html-el:option>
				<html-el:option value="81E组">81E组</html-el:option>
				<html-el:option value="81F组">81F组</html-el:option>
				<html-el:option value="81G组">81G组</html-el:option>
				<html-el:option value="81H组">81H组</html-el:option>
				<html-el:option value="81I组">81I组</html-el:option>
            </html-el:select>&nbsp;
          </td>
        </tr>
        <tr>
       		<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>球队国旗：</td>
       		<td>
       			<c:if test="${empty af.map.id}"><html-el:file property="team_flag_pic_url" styleId="team_flag_pic_url" styleClass="webinput" style="width:" /></c:if>
          			<c:if test="${not empty af.map.id}">
          				<div id="main_pic_div" class="main_box">
	          				<div class="pic_box">
	          					<a id="main_pic_a" href="${ctx}/${af.map.team_flag_pic_url}" title="用创新赢得尊严！">
		        					<img src="${ctx}/${fn:substringBefore(af.map.team_flag_pic_url, '.')}_191.jpg" />
		        				</a>
		        				<html-el:hidden property="team_flag_pic_url_hidden" styleId="team_flag_pic_url_hidden" value="${af.map.team_flag_pic_url}" />
		        				<a class="xubox_close xubox_close1_0" href="javascript:delPic('main_pic_div');"></a>
		        			</div>
          				</div>
          				<div style="margin-top:5px;">
		        			<input type="checkbox" name="chkReUploadMainPicFileImage" id="chkReUploadMainPicFileImage" value="1" onclick="$('#team_flag_pic_url').toggle();" style="vertical-align:middle;cursor:pointer;" />
			            	<label for="chkReUploadMainPicFileImage" style="vertical-align:middle;cursor:pointer;">重新上传</label>
          				</div>
		            	<html-el:file property="team_flag_pic_url" styleId="team_flag_pic_url" styleClass="webinput" style="display:none;width:" />
          			</c:if>
       			<div>注：此处上传图片尺寸为1:1时，效果最佳。</div>
       		</td>
       	</tr>
        <tr>
       		<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>球队图片：</td>
       		<td>
       			<c:if test="${empty af.map.id}"><html-el:file property="team_main_pic_url" styleId="team_main_pic_url" styleClass="webinput" style="width:" /></c:if>
          			<c:if test="${not empty af.map.id}">
          				<div id="main_pic_div" class="main_box">
	          				<div class="pic_box">
	          					<a id="main_pic_a" href="${ctx}/${af.map.team_main_pic_url}" title="用创新赢得尊严！">
		        					<img src="${ctx}/${fn:substringBefore(af.map.team_main_pic_url, '.')}_191.jpg" />
		        				</a>
		        				<html-el:hidden property="team_main_pic_url_hidden" styleId="team_main_pic_url_hidden" value="${af.map.team_main_pic_url}" />
		        				<a class="xubox_close xubox_close1_0" href="javascript:delPic('main_pic_div');"></a>
		        			</div>
          				</div>
          				<div style="margin-top:5px;">
		        			<input type="checkbox" name="chkReUploadMainPicFileImage" id="chkReUploadMainPicFileImage" value="1" onclick="$('#team_main_pic_url').toggle();" style="vertical-align:middle;cursor:pointer;" />
			            	<label for="chkReUploadMainPicFileImage" style="vertical-align:middle;cursor:pointer;">重新上传</label>
          				</div>
		            	<html-el:file property="team_main_pic_url" styleId="team_main_pic_url" styleClass="webinput" style="display:none;width:" />
          			</c:if>
       			<div>注：此处上传图片尺寸为1:1时，效果最佳。</div>
       		</td>
        </tr>
        <tr>
       		<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>球队介绍:</td>
       		<td><FCK:editor instanceName="team_intro">
              <jsp:attribute name="value">${af.map.team_intro}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div>
            </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>排序号：</td>
          <td width="88%" align="left"><html-el:text property="order_sort_num" styleId="order_sort_num" size="25" maxlength="15"/>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>状态：</td>
          <td width="88%" align="left">
          <html-el:select property="is_del" styleId="is_del" style="width:120px;">
      		   <html-el:option value="0">启用</html-el:option>
      		   <html-el:option value="1">停用</html-el:option>
        	 </html-el:select>
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
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#team_name").attr("datatype", "Require").attr("msg", "请填写球队名称");
	$("#order_sort_num").attr("datatype", "Require").attr("msg", "请填写排序号");
	$("#is_del").attr("datatype", "Require").attr("msg", "请填写状态");
	$("#group_name").attr("datatype", "Require").attr("msg", "请填写球队组名");
	if ("" == "${af.map.id}") {
		$("#team_flag_pic_url").attr("datatype", "Require").attr("msg", "请选择球队国旗");
	}
	if ("" == "${af.map.id}") {
		$("#team_main_pic_url").attr("datatype", "Require").attr("msg", "请选择球队图片");
	}
	
	$("#btn_submit").click(function(){
		if ("" != "${af.map.id}") {
			if (0 == $("input[type='hidden'][name='team_flag_pic_url_hidden']").length) {
				$("#team_flag_pic_url_hidden").attr("dataType", "Require").attr("msg", "请上传球队国旗！");
			}
			if (0 == $("input[type='hidden'][name='team_main_pic_url_hidden']").length) {
				$("#team_main_pic_url_hidden").attr("dataType", "Require").attr("msg", "请上传球队图片！");
			}
		}
		/* var oEditor = FCKeditorAPI.GetInstance("team_intro"); 
		alert(oEditor.EditorDocument.body.innerText.length); */
		
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...");
	            //$("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});


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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
