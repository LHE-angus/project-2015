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
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="overflow: auto;">
    <html-el:form action="/admin/GcxmProjOffer" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="info_state" styleId="info_state" value="0"/>
      <html-el:hidden property="queryString" />
      <c:set var="readyOnly" value="${empty af.map.offer_id?false:true}"  />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%">项目编号：</td>
      		<td width="35%"><html-el:text property="proj_code" styleId="proj_code" value="${af.map.gcxm_sn}" readonly="true" style="width:200"/></td>
             <td class="title_item" align="right" width="15%">项目类型：</td>
             <td width="35%" style="color:red">
            <c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td width="35%"><html-el:text property="proj_name" styleId="proj_name" value="${af.map.proj_name}" readonly="true" maxlength="10" style="width:350px;"  /></td>
      		<td class="title_item" align="right" width="15%"><span style="color:red">*</span>审核流程：</td>
      		<td width="35%"><html-el:select property="process_id" styleId="process_id" disabled="${readyOnly}">
                <c:forEach items="${gpList}"  var="cur">
                	<html-el:option value="${cur.id}">${cur.process_name}</html-el:option>
                </c:forEach>
                </html-el:select></td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
           <html-el:text property="dept_name" styleId="dept_name" value="${af.map.fgs_dept_name}" readonly="true" maxlength="10" style="width:350px;"  />
          </td>
           <td class="title_item" align="right">审核状态：</td>
          <td width="35%">
              <c:if test="${af.map.gcxmProjOffer.info_state eq -1 or empty af.map.gcxmProjOffer.info_state}">未提交</c:if>
              <c:if test="${af.map.gcxmProjOffer.info_state eq 0}">审核中</c:if>
              <c:if test="${af.map.gcxmProjOffer.info_state eq 1}">已完结</c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;<span id="s_cg">推荐机型1</span>&nbsp;：</td>
          <td width="50%" >
          <html-el:text property="model_1" styleId="model_1" value="${af.map.model_1}" readonly="true" maxlength="10" style="width:200px;"  />
          </td>
          </tr>
          <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;<span id="s_cg">推荐机型2</span>&nbsp;：</td>
          <td width="50%" >
          <html-el:text property="model_2" styleId="model_2" value="${af.map.model_2}" readonly="true" maxlength="10" style="width:200px;"  />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;<span id="s_cg">推荐机型3</span>&nbsp;：</td>
          <td width="50%" >
          <html-el:text property="model_3" styleId="model_3" value="${af.map.model_3}" readonly="true" maxlength="10" style="width:200px;"  />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>报价型号：</td>
          <td width="50%" >
          <html-el:text property="offer_model" styleId="offer_model" value="${af.map.gcxmProjOffer.offer_model}" maxlength="20" style="width:200px;" onkeyup="upperCase(this.id);"/></td>
          <td class="title_item" nowrap="nowrap" align="right">分公司报价：</td> 
          <td width="50%" >
          <html-el:text property="offer_price" styleId="offer_price" value="${af.map.gcxmProjOffer.offer_price}" maxlength="10" style="width:100px;" />元/台
          </td>
        </tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>交货日期：</td>    
          <td width="50%" >
          <fmt:formatDate var="delivery_date" value="${af.map.delivery_date}" pattern="yyyy-MM-dd" /> 
          <html-el:text property="delivery_date" styleId="delivery_date"  size="12" maxlength="10" value="${delivery_date}" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;"  />
          </td>     
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">参数要求：</td>
          <td width="88%" colspan="3">
          <html-el:textarea property="offer_memo" styleId="offer_memo" value="${af.map.gcxmProjOffer.offer_memo}" cols="5" style="width:820px;height:100px;" />
          <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>&nbsp;创建人：</td>
          <td width="50%" >
          <html-el:text property="create_name" styleId="create_name" maxlength="20" value="${af.map.user_name}" readonly="true" style="width:200px;" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>      
          <td width="50%" >
          <fmt:formatDate var="create_date" value="${af.map.create_date}" pattern="yyyy-MM-dd" />  
          <html-el:text property="create_date" styleId="create_date"  size="12" maxlength="10" readonly="true" value="${create_date}"  style="cursor:pointer;text-align:center;"  />
          </td>
        </tr>
        <tr id="trFile">
          <td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
          <td><div id="divFileHidden" style="display: none;">
              <input name="file_hidden" type="file" id="file_hidden" style="width: 280px;" />
              <img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
            <div id="divFile">
              <input name="file_show" type="file" id="file_show" style="width: 280px;" />   
              <img src="${ctx }/images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" />
        </div></td></tr>
         <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item" align="right">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>  
         <tr>
          <td>&nbsp;</td>
          <td align="center" colspan="3" ><label>
 			<input class="but4" type="button"  id="btn_submit1" value="暂存" /> 
            <input class="but4" type="button"  id="btn_submit" value="提交" />
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
        
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">
                                        
$(document).ready(function(){ 
	 var acceptUploadFileExts = "7z,aiff, asf, avi, bmp, csv, doc, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip, docx, xlsx, pptx";
	$("#offer_model").attr("dataType", "Require").attr("msg", "请填写报价型号");
	$("#delivery_date").attr("dataType", "Require").attr("msg", "请填写交货日期");
	$("#file_show" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);

	<c:if test="${empty af.map.offer_id}"> 
	$("#process_id").attr("datatype", "Require").attr("msg", "请选择审核流程");
	</c:if>

	
	$("#offer_memo").textbox({   
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");  
	});
	


	 $("a[id^='att_del_']").click(function() {  
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("GcxmProjOffer.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
		  });
		    window.parent.resizeFrameHeight('mainFrame', 3);
		 });
	

	$("#imgAddTr").click(function (){
		$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
	    $("img[id='imgDelTr']").each(function(){
			$(this).click(function (){
				$(this).parent().remove();
			});
		});

	    window.parent.resizeFrameHeight('mainFrame', 3);
	});

	
	
	// 提交
	$("#btn_submit").click(function(){

		var offer_model = $("#offer_model").val();
		if(offer_model.indexOf(' ')>-1){
			alert("报价型号不能含有空格！");
			return ;
		}

		var is_model=false;
		var models= '${af.map.models}';
		var modelss=models.split(",");
		for(i=0;i<modelss.length;i++){
			if(modelss[i]==offer_model){
				is_model=true;
			}	
		}

		if(!is_model){
			$("#offer_price").attr("datatype", "Require").attr("msg", "报价型号与推荐型号不一致时，分公司报价必填"); 
		}else{
			$("#offer_price").removeAttr("dataType");
		}	
		
		
		if(Validator.Validate(this.form, 2)){
			 $("#btn_submit1").attr("disabled", "true");
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true"); 
    		$("#info_state").val(0);
			this.form.submit();
		}
	});


	// 暂存 
	$("#btn_submit1").click(function(){

		var offer_model = $("#offer_model").val();
		if(offer_model.indexOf(' ')>-1){
			alert("报价型号不能含有空格！");
			return ;
		}

		var is_model=false;
		var models= '${af.map.models}';
		var modelss=models.split(",");
		for(i=0;i<modelss.length;i++){
			if(modelss[i]==offer_model){
				is_model=true;
			}	
		}

		if(!is_model){
			$("#offer_price").attr("datatype", "Require").attr("msg", "报价型号与推荐型号不一致时，分公司报价必填"); 
		}else{
			$("#offer_price").removeAttr("dataType");
		}	 	

		$("#info_state").val(-1);
		
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "";
	});
}


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
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}
function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
}

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
