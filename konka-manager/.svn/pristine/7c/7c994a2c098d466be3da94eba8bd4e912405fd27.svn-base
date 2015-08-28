<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>


</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form  enctype="multipart/form-data"  action="/admin/KonkaPhotoUpload.do" method="post" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id"  value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id"  value="${af.map.dept_id}" />
      <html-el:hidden property="dept_name"  value="${af.map.dept_name}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
  </tr>
  	<tr>
       <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>上报日期：</td>
		<td><fmt:formatDate value="${af.map.report_date}" pattern="yyyy-MM-dd" var="report_date" />
         <html-el:text property="report_date" styleClass="webinput" styleId="report_date"  onclick="new Calendar(2005, 2030, 0).show(this);"  style="cursor:pointer;text-align:center;width:100px;" value="${report_date}" readonly="readonly" />
		</td>
       </tr> 	
  <tr>
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>活动类型：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="type_id" styleId="type_id" value="${type_id}">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${photoTypes}" var="cur">
	    		<html-el:option value="${cur.ID}">${cur.TYPE_TILTE}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   </tr>
       <tr>
    		<td align="center" nowrap="nowrap" class="title_item">客户：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="r3_code_name" styleId="r3_code_name" value="${r3_code_name}">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${custList}" var="cur">
	    		<html-el:option value="${cur.map.customer_code}#${cur.map.customer_name}">${cur.map.customer_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   </tr>
   <tr>
    		<td align="center" nowrap="nowrap" class="title_item">终端：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="shop_id_name" styleId="shop_id_name" value="${shop_id_name}" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${storeList}" var="cur">
	    		  <html-el:option value="${cur.store_id}#${cur.store_name}">${cur.store_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   </tr>
  	      <tr>
    <td align="center" nowrap="nowrap" class="title_item">上报说明：</td>
    <td align="left" nowrap="nowrap">
    <html-el:textarea property="report_memo" styleId="report_memo" style="width:500px;height:80px;resize:none" />
    </td>
  </tr>

  <tr>
    <td align="center" nowrap="nowrap" class="title_item">备注：</td>
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="remark" styleId="remark" style="width:500px;height:80px;resize:none" />
          	<div id="remark_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
          	
  </tr>

	    	<tr>
<td class="title_item" align="center" nowrap="nowrap">附件：</td>
			<td>
        			<div id="policydivFileHidden" style="display: none;" >
               <input name="activity_policy" type="file" id="activity_policy" style="width: 250px;" />
               <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="policyDelTr" title="删除"/>
               </div>
             	<div id="policydivFile">
               <input name="policy_file" type="file" id="policy_file" style="width: 250px;" />
               <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="policyAddTr" title="再添加一个" /></div>
               <ol>
           	<c:forEach items="${af.map.attachmentsList}" var="cur">
			 <li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>
			 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
			</c:forEach>
			</ol>
			</td>
</tr>
        <tr>
        <td width="20%"></td>
          <td><label>
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" /></label></td>
        </tr>
      </table>
    </html-el:form>
    <div style="height:50px;"></div>
  </div>
  
          <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>




<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("input[type!='button']").each(function(){ this.disabled=true; }); 
	
	function resizeFrameHeight(offset, min_height) {
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
	}
	
	$("#begin_date").attr("dataType", "Require").attr("msg", "请选择拜访时间");
	$("#visit_desc").attr("dataType", "Require").attr("msg", "请填写开拓说明");
	$("#consumer_sales,#retail_sales,#consumer_phone").focus(setOnlyNum);
	$("#feed_list").attr("dataType", "Require").attr("msg", "请填写反馈说明");
	
	//$("#state").attr("dataType", "Require").attr("msg", "请选择拜访结果");
	//$("#consumer_name").attr("dataType", "Require").attr("msg", "请填写联系人");
	//$("#consumer_phone").attr("dataType", "Require").attr("msg", "请填写联系电话");
	
	
	$("#visit_desc").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#visit_desc_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#visit_desc_note").slideUp("normal");
	});
	$("#feed_list").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#feed_list_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#feed_list_note").slideUp("normal");
	});
	
	$("#remark").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#remark_note").slideUp("normal");
	});
	
	
	$("#type_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#type_id").val(ui.value);
	       }
		}
	}).multiselectfilter();
	$("#r3_code_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#r3_code_name").val(ui.value);
	       }
		}
	}).multiselectfilter();
	$("#shop_id_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#shop_id_name").val(ui.value);
	       }
		}
	}).multiselectfilter();
	$("#state").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#state").val(ui.value);
	       }
		}
	});


})
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
