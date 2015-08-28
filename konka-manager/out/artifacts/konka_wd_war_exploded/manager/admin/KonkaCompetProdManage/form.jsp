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
<body style="font-family:Microsoft Yahei,'宋体';">
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
	<html-el:form action="/admin/KonkaCompetProdManage" enctype="multipart/form-data">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
	  	  <td align="center" colspan="4" style="font-weight:900;">竞品添加</td>
	  	</tr>
	  	<tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>机型名称：</td>
          <td width="88%">
          	<c:if test="${empty af.map.id}"><html-el:text property="md_name" styleId="md_name" size="20" maxlength="60" /></c:if>
          	<c:if test="${not empty af.map.id}">${af.map.md_name}<html-el:hidden property="md_name" styleId="md_name" /></c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>品牌：</td>
          <td>
          	<select name="brandList" id="brandList" multiple="multiple">
				<c:forEach items="${brandList}" var="cur" varStatus="vs">
					<option value="${cur.brand_id}[#####]${cur.brand_name}">${cur.brand_name}</option>
				</c:forEach>
			</select>
			<html-el:hidden property="brand_id" styleId="brand_id" />
      		<html-el:hidden property="brand_name" styleId="brand_name" />
          </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>尺寸：</td>
          <td><html-el:text property="screen_size" styleId="screen_size" size="4" maxlength="8" /></td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]<strong class="fb" style="align:center">规格段：</strong></td>
          <td><html-el:select property="Par_type_id" styleId="Par_type_id" value="${af.map.par_type_id}">
            		<html-el:option value="">=请选择=</html-el:option>
            		<c:forEach items="${eList}" var="cur">
            			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
            		</c:forEach>
            	</html-el:select>
           </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>智能电视：</td>
          <td>
          	<html-el:select property="is_smart_tv" styleId="is_smart_tv">
          		<html-el:option value="">=请选择=</html-el:option>
          		<html-el:option value="0">否</html-el:option>
          		<html-el:option value="1">是</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>三维电视：</td>
          <td>
          	<html-el:select property="d_tv" styleId="d_tv">
          		<html-el:option value="">=请选择=</html-el:option>
          		<html-el:option value="0">2D</html-el:option>
          		<html-el:option value="1">3D</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>背光源：</td>
          <td>
          	<html-el:select property="back_light" styleId="back_light">
          		<html-el:option value="">=请选择=</html-el:option>
          		<html-el:option value="0">LED</html-el:option>
          		<html-el:option value="1">CCFL</html-el:option>
          		<html-el:option value="9">其他</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item"><span style="color:red">[必填]</span>参考价格：</td>
          <td><html-el:text property="ref_price" styleId="ref_price" size="8" maxlength="6" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">描述：</td>
          <td>
           	<html-el:textarea property="type_desc" styleId="type_desc" cols="60" rows="4"/>
           	<div id="info_chat_content" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/manager/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" size="4" maxlength="4"/></td>
        </tr>
        <tr>
            <td colspan="4" height="40"  align="center">
            	<input class="but4" type="button" name="Submit4" value="提交 " id="btn_submit" />
            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){

	$("#md_name").attr("dataType","Require").attr("msg","机型名称不能为空！");
	$("#brand_id").attr("dataType","Require").attr("msg","请选择品牌！");
	$("#screen_size").attr("dataType","Require").attr("msg","尺寸不能为空！");
	$("#is_smart_tv").attr("dataType","Require").attr("msg","请选择是否为智能电视！");
	$("#d_tv").attr("dataType","Require").attr("msg","请选择是否为三维电视！");
	$("#back_light").attr("dataType","Require").attr("msg","请选择背光源！");
	$("#ref_price").attr("dataType","Require").attr("msg","参考价格不能为空！");
	
	$("#order_value").attr("dataType","Integer").attr("msg","排序值为整数！").attr("require", "false");
	$("#type_desc").textbox({
		maxLength: 80,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/manager/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
	});
	
	$("#brandList").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:160,
		click: function(event, ui){
			if(ui.value != ""){
				var brand_id = ui.value.split("[#####]")[0];
				var brand_name = ui.value.split("[#####]")[1];
				$("#brand_id").val(brand_id);
				$("#brand_name").val(brand_name);
				//alert($("#brand_id").val() + "  " + $("#brand_name").val());
			}
		}
	}).multiselectfilter();

	if ("" != "${af.map.id}") {
		$("#brandList").val("${af.map.brand_id}[#####]${af.map.brand_name}").multiselect("refresh");
	}

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定要提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>