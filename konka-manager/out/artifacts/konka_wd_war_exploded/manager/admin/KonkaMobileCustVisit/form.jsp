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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/KonkaMobileCustVisit"  method="post" enctype="multipart/form-data">
      <html-el:hidden property="visit_id" styleId="visit_id" value="${af.map.visit_id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
       <html-el:hidden property="report_type" styleId="report_type" value="${report_type}" />
       <html-el:hidden property="report_type_tj" styleId="report_type_tj" value="${report_type_tj}" />
       <html-el:hidden property="is_del" styleId="is_del" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.visit_type_id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr><td colspan="2">
         <div align="right"><span style="cursor:pointer;text-decoration: none;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','list' ,'report_type=${af.map.report_type}&is_del=0&mod_id=${af.map.mod_id}')">查看历史记录</span></div>
         </td></tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">创建日期：</td>
          <td width="88%" align="left">
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
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}"><font color="red">*</font>开始日期：</c:if>
          <c:if test="${report_type eq 4}"><font color="red">*</font>开始日期：</c:if>
          </td>
          <td width="88%" align="left">
             <fmt:formatDate value="${af.map.begin_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="begin_date" styleId="begin_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr>
        <!--
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}">结束日期：</c:if>
          <c:if test="${report_type eq 4}">结束日期：</c:if>
          </td>
          <td width="88%" align="left">
             <fmt:formatDate value="${af.map.end_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="end_date" styleId="end_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr>
        -->
        <c:if test="${report_type eq 3}">
         <tr>
    		 <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">*</font>客户名称：</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="cust_id_name" styleId="cust_id_name" value="${cust_id_name}">
    		<html-el:option value="">-请选择客户-</html-el:option>
	    		<c:forEach items="${konkaR3ShopDevList}" var="cur">
	    		<c:if test="${not empty cur.cust_id}">
	    		<html-el:option value="${cur.cust_id}#${cur.cust_name}">${cur.cust_name}</html-el:option>
	    		</c:if>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   </tr>
  	    <tr>
	    <td align="right" nowrap="nowrap" class="title_item">被访人：</td>
	    <td align="left" nowrap="nowrap">
	        <html-el:text property="consumer_name" styleId="consumer_name"></html-el:text>
	    </td>
		  </tr>
		    <tr>
		    <td align="right" nowrap="nowrap" class="title_item">被访人电话：</td>
		    <td align="left" nowrap="nowrap">
		        <html-el:text property="consumer_phone" styleId="consumer_phone"></html-el:text>
		    </td>
		  </tr>
	  </c:if>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
          <c:if test="${report_type eq 3}">开拓说明：</c:if>
          <c:if test="${report_type eq 4}"><font color="red">*</font>事物说明：</c:if></td>
          <td width="88%" align="left">
             <html-el:textarea property="visit_desc" styleId="visit_desc" styleClass="webinput" style="width:70%;height:60px;resize:none;" />
             <div id="visit_desc_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
       <c:if test="${report_type eq 3}">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注说明</td>
          <td width="88%" align="left">
             <html-el:textarea property="remark" styleId="remark" styleClass="webinput" style="width:70%;height:60px;" />
             <div id="remark_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        </c:if>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><c:if test="${report_type eq 3}"><font color="red">*</font>开拓状态：</c:if> <c:if test="${report_type eq 4}"><font color="red">*</font>拜访状态：</c:if></td>
          <td width="88%" align="left">
            <html-el:select property="state" styleId="state" style="width:120px;">
              <html-el:option value="">--请选择--</html-el:option>
	          <c:if test="${report_type eq 3}">
	      		   <html-el:option value="1">开拓中</html-el:option>
	      		   <html-el:option value="2">已关闭</html-el:option>
	      	   <!--<html-el:option value="3">已开拓成功</html-el:option>-->
	      	  </c:if>
	          <c:if test="${report_type eq 4}">
	      		   <html-el:option value="0">需跟踪</html-el:option>
	      		   <html-el:option value="1">已关闭</html-el:option>
      	      </c:if>
        	 </html-el:select>
          </td>
        </tr>
        <tr>
            <td class="title_item" align="center" nowrap="nowrap">附件:</td>
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
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
        <tr style="height: 100px;"></tr>
      </table>
    </html-el:form>
  </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>




<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>

<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	
	$("#begin_date").attr("dataType", "Require").attr("msg", "开拓时间不能为空！");
	$("#state").attr("dataType", "Require").attr("msg", "开拓结果不能为空！");
	if(${af.map.report_type}==3){
		$("#cust_id_name").attr("dataType", "Require").attr("msg", "拜访客户不能为空！");
		//$("#consumer_name").attr("dataType", "Require").attr("msg", "被拜访人不能为空！");
		//$("#consumer_phone").attr("dataType", "Require").attr("msg", "被拜访人电话不能为空！");
	}
	if(${af.map.report_type}==4){
		$("#visit_desc").attr("dataType", "Require").attr("msg", "事物说明！");
	}
	$("#visit_desc").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#visit_desc_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#visit_desc_note").slideUp("normal");
	});

	$("#remark").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#visit_desc_note").slideUp("normal");
	});

	function multi(id){
    	$("#"+id).multiselect({
    		noneSelectedText: '==请选择==',
    		selectedList: 1,
    		multiple: false,
    		minWidth:150,
    		click: function(event, ui){
    	       if(ui.value != ""){
    	    	   $("#"+id).val(ui.value);
    	       }
    		}
    	}).multiselectfilter();
    }

	 $("#policyAddTr").click(function (){
	       $("#policydivFileHidden").clone().find("#activity_policy").attr("name", "policy_file_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
	       $("img[id='policyDelTr']").each(function(){
	           $(this).click(function (){
	               $(this).parent().remove();
	               resizeFrameHeight();
	           });
	       });
	});
	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("KonkaSpActivityManager.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
		  });
		    resizeFrameHeight();
	 });
	
    //multi("state");
    multi("cust_id_name");
    
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
	function checkMaxLen(obj,maxlength){
		   if(obj.value.length>maxlength){
		     obj.value = obj.value.substr(0,maxlength);
		   }
	 }
	//$("#cust_name").val($("#cust_id").find("option:selected").text());
	/**$("#cust_id").change(function(){
		$("#cust_name").val($(this).find("option:selected").text());
		});**/
	//$("#begin_date").attr("datatype", "Require").attr("msg", "请选择拜访时间");
	//$("#end_date").attr("datatype", "Require").attr("msg", "请选择结束时间");
	//$("#report_type").attr("datatype", "Require").attr("msg", "请选择上报类型");
	//$("#visit_type_name").attr("datatype", "Require").attr("msg", "请选择拜访类型");
	//$("#state").attr("datatype", "Require").attr("msg", "请选择状态");
	var visit_desc=$("#visit_desc").val();
	if(visit_desc.length<1){
	  if($("#report_type").val()==3){
		  //$("#visit_desc").attr("datatype", "Require").attr("msg", "请填写开拓说明");
	   }  
	  if($("#report_type").val()==4){
		//$("#visit_desc").attr("datatype", "Require").attr("msg", "请填写事物说明");
	   }
	 }
	$("#btn_submit").click(function(){
		var visitdesclen=$("#visit_desc").val();
	    if(visitdesclen.length>500){
	            alert("说明内容不能大于500个字符");
	            return false;
	     }
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
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

//]]>-->--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
