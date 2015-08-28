<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<c:if test="${report_type eq 1}"><c:set var="naviString" value="正常客户拜访" /></c:if>
<c:if test="${report_type eq 2}"><c:set var="naviString" value="老客户重拾拜访" /></c:if>
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
    <html-el:form  enctype="multipart/form-data"  action="/admin/KonkaMobileCustVisit.do" method="post" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="visit_id" value="${af.map.visit_id}" />
      <html-el:hidden property="mod_id"  value="${af.map.mod_id}" />
       <html-el:hidden property="is_del" styleId="is_del" />
      <html-el:hidden property="report_type_tj"  value="${report_type_tj}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
    <td colspan="2" align="right"><span id="sj_his" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','list' ,'report_type_tj=${report_type_tj}&is_del=0&mod_id=${af.map.mod_id}')" >历史记录</span></td>
  </tr>
       <tr>
       <td align="center" nowrap="nowrap" class="title_item">创建日期：</td>
       <td>
      <c:if test="${not empty today}">
      ${today}
       </c:if>
        <c:if test="${empty today}">
        <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" var="add_date" />
       ${add_date}
        </c:if>
       </td>
       </tr>
       <tr>
       <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>开始日期：</td>
		<td><fmt:formatDate value="${af.map.begin_date}" pattern="yyyy-MM-dd" var="begin_date" />
         <html-el:text property="begin_date" styleClass="webinput" styleId="begin_date"  onclick="new Calendar(2005, 2030, 0).show(this);"  style="cursor:pointer;text-align:center;width:100px;" value="${begin_date}" readonly="readonly" />
		</td>
       </tr>
        <tr>
        <td align="center" nowrap="nowrap" class="title_item">结束日期：</td>
		<td><fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" var="end_date" />
         <html-el:text property="end_date" styleClass="webinput" styleId="end_date"  onclick="new Calendar(2005, 2030, 0).show(this);"  style="cursor:pointer;text-align:center;width:100px;" value="${end_date}" readonly="readonly" />
		</td>
       </tr>  
       <tr>
           <td align="center" nowrap="nowrap" class="title_item" >拜访类别：</td>
       		<td>
       			<html-el:select property="report_type" styleId="report_type" style="width:140px;">
		           <html-el:option value="">－请选择－</html-el:option>
	      		   <html-el:option value="1">正常客户</html-el:option>
	      		   <html-el:option value="2">重拾客户</html-el:option>
        	 	</html-el:select> 
       		</td>
       </tr>
        <tr>
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>客户名称：</td>
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
   <td align="center" nowrap="nowrap" class="title_item">客户编码：</td>
   <td align="left" nowrap="nowrap" id="r3_code_show">
   <c:if test="${not empty af.map.visit_id}">
   ${r3_code}
   </c:if>
   </td>
   </tr>
   
   <tr>
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>终端名称：</td>
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
   <td align="center" nowrap="nowrap" class="title_item">拜访类型：</td>
   <td align="left" nowrap="nowrap">
   <c:forEach items="${visitTypeList}" var ="visttype"  varStatus="status" >
   <label for="pay_type_${status.count}">
   <input type="checkbox" name="visit_type_id" 
   <c:if  test="${not empty visttype.map.checked}">${visttype.map.checked}</c:if>
   id="visit_type_${visttype.visit_type_id}" value="${visttype.visit_type_id}" />
   &nbsp;&nbsp;${visttype.visit_type_name}</label>&nbsp;&nbsp;
   <c:if test="${status.count % 3 eq 0}">
   <br/>
   </c:if>
   </c:forEach>
   </td>
   </tr>
	 <tr>
    <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>反馈问题：</td>
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="feed_list" styleId="feed_list" style="width:300px;height:50px;resize:none" />
          	<div id="feed_list_note" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
  </tr>   	
  <tr>
    <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>拜访说明：</td>
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="visit_desc" styleId="visit_desc" style="width:300px;height:50px;resize:none" />
          	<div id="visit_desc_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
          	
  </tr>
  <!--<tr>
          <td align="center" nowrap="nowrap" class="title_item">备注说明</td>
         <td align="left" nowrap="nowrap"> 
             <html-el:textarea property="remark" styleId="remark" styleClass="webinput" style="width:70%;height:60px;" />
             <div id="remark_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
   </tr> -->
  <c:if test="${report_type eq 1}">
   <tr>
    <td align="center" nowrap="nowrap" class="title_item">国产排名：</td>
    <td align="left" nowrap="nowrap">
        <html-el:select property="domestic_ranking" styleId="domestic_ranking">
	    	 <html-el:option value="0">-请选择-</html-el:option>
	    		<html-el:option value="1">1</html-el:option>
	    		<html-el:option value="2">2</html-el:option>
	    		<html-el:option value="3">3</html-el:option>
	    		<html-el:option value="4">4</html-el:option>
	    		<html-el:option value="5">5</html-el:option>
	    		<html-el:option value="6">6</html-el:option>
	    	</html-el:select>	
    </td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">零售量（台）：</td>
    <td align="left" nowrap="nowrap">
        <html-el:text property="consumer_sales" styleId="consumer_sales"  maxlength="10"></html-el:text>
    </td>
  </tr>
   <tr>
    <td align="center" nowrap="nowrap" class="title_item">零售额（万元）：</td>
    <td align="left" nowrap="nowrap">
        <html-el:text property="retail_sales" styleId="retail_sales" maxlength="10"></html-el:text>
    </td>
  </tr>
  </c:if>
   <tr>
    <td align="center" nowrap="nowrap" class="title_item">被访人：</td>
    <td align="left" nowrap="nowrap">
        <html-el:text property="consumer_name" styleId="consumer_name" maxlength="10"></html-el:text>
    </td>
  </tr>
    <tr>
    <td align="center" nowrap="nowrap" class="title_item">被访人电话：</td>
    <td align="left" nowrap="nowrap">
        <html-el:text property="consumer_phone" styleId="consumer_phone" maxlength="13"></html-el:text>
    </td>
  </tr>
      <tr>
          <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>拜访状态：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="state"  styleId="state" value="${af.map.state}">
	    		<html-el:option value="0">--需跟踪--</html-el:option>
	    		<html-el:option value="1">--已关闭--</html-el:option>
	    	</html-el:select>
	    	</td> </tr>  
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
           <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />&nbsp;
              <input class="btn_reset" type="button" name="Submit5" value="重置" onclick="this.form.reset();return false;" />&nbsp;
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
     
	$("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("KonkaSpActivityManager.do", { method : "deleteFile1", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
		  });

		    resizeFrameHeight();
		 });
	
	$("#policyAddTr").click(function (){
	      $("#policydivFileHidden").clone().find("#activity_policy").attr("name", "policy_file_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
	      resizeFrameHeight();
	      $("img[id='policyDelTr']").each(function(){
	          $(this).click(function (){
	              $(this).parent().remove();
	              resizeFrameHeight();
	          });
	      });
	});

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
	
	
	
	$("#domestic_ranking").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#domestic_ranking").val(ui.value);
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
	    	   $("#r3_code_show").html(ui.value);
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

	$("#btn_submit").click(function(){

		var cust=$("#r3_code_name").val(),shop=$("#shop_id_name").val();
		if(cust==""&&shop==""){
            alert("终端客户必选一个");
            return false;
		}
		var visit_types=$("input:checkbox[name='visit_type_id']:checked");
		if(visit_types.length==0){
			alert("必须选择一个拜访类型");
            return false;
			}
		if(Validator.Validate(this.form, 3)){
		 $("#btn_submit").attr("value", "正在提交...");//.attr("disabled", "true");
		 this.form.submit();
		}
	});
	<c:forEach items="${konkaMobileCustVisitTypeList}" var="cur">
		$("#visit_type_${cur.visit_type_id}").attr("checked",true);
	</c:forEach>
	

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
