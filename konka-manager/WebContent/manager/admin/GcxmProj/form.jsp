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
    <html-el:form action="/admin/GcxmProj" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="info_state" styleId="info_state" value="0"/>
      <html-el:hidden property="queryString" />
      <c:set var="readyOnly" value="${empty af.map.id?false:true}"  />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%"><span style="color:red">*</span>项目类型：</td>
      		<td width="35%"><html-el:select property="proj_type" styleId="proj_type">
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="1">政府采购</html-el:option>
                <html-el:option value="2">酒店采购</html-el:option>
                <html-el:option value="3">企业采购</html-el:option>
                <html-el:option value="4">其他</html-el:option>
                </html-el:select></td>
             <td class="title_item" align="right" width="15%">项目编号：</td>
             <td width="35%"><html-el:text property="proj_code" styleId="proj_code" value="${af.map.gcxm_sn}" readonly="true" style="width:200"/>
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right"><span style="color:red">*</span>项目名称：</td>
      		<td width="35%"><html-el:text property="proj_name" styleId="proj_name" maxlength="50" style="width:350px;"  /></td>
      	    <td class="title_item" align="right" width="15%"><span style="color:red">*</span>审核流程：</td>
      		<td width="35%"><html-el:select property="process_id" styleId="process_id" disabled="${readyOnly}">
                <c:forEach items="${gpList}"  var="cur">
                	<html-el:option value="${cur.id}">${cur.process_name}</html-el:option>
                </c:forEach>
                </html-el:select></td>
      	</tr>
        <tr>
          <td class="title_item" align="right"><span style="color:red">*</span>&nbsp;分公司&nbsp;：</td>
          <td>
           <html-el:text property="dept_name" styleId="dept_name" value="${af.map.fgs_dept_name}" readonly="true" maxlength="10" style="width:350px;"  />
          </td>
           <td class="title_item" align="right">审核状态：</td>
          <td width="35%">
          <c:if test="${empty af.map.id}">未提交</c:if>
          <c:if test="${not empty af.map.id}">
              <c:if test="${af.map.info_state eq -1}">未提交</c:if>
              <c:if test="${af.map.info_state eq 0}">审核中</c:if>
              <c:if test="${af.map.info_state eq 1}">已完结</c:if>
          </c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>&nbsp;<span id="s_cg">采购人</span>&nbsp;：</td>
          <td width="50%" >
          <html-el:text property="buyer" styleId="buyer" maxlength="10" style="width:200px;"  />
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span><span id="s_dh">采购人电话：</span></td>
          <td width="50%" >
          <html-el:text property="buyer_tel" styleId="buyer_tel" maxlength="30" style="width:200px;" />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span><span id="s_rq">报价日期：</span></td>
          <td width="50%" >
          <fmt:formatDate var="offer_date" value="${af.map.offer_date}" pattern="yyyy-MM-dd" />
          <html-el:text property="offer_date" styleId="offer_date"  size="12" maxlength="10" readonly="true" value="${offer_date}" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>
          <td width="50%" >
          <fmt:formatDate var="delivery_date" value="${af.map.delivery_date}" pattern="yyyy-MM-dd" />
          <html-el:text property="delivery_date" styleId="delivery_date"  size="12" maxlength="10" readonly="true" value="${delivery_date}" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;"  />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>采购尺寸：</td>
          <td width="50%" >
          <html-el:text property="size" styleId="size" maxlength="20" style="width:200px;" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>采购数量：</td>
          <td width="50%" >
          <html-el:text property="buyer_num" styleId="buyer_num" maxlength="20" style="width:100px;" />台
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>采购预算：</td>
          <td width="50%" >
          <html-el:text property="budget" styleId="budget" maxlength="20" style="width:200px;" />元
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">参数要求：</td>
          <td width="88%" colspan="3">
          <html-el:textarea property="memo" styleId="memo" cols="5" style="width:820px;height:100px;" />
          <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;备 注：</td>
          <td width="88%" colspan="3">
          <html-el:textarea property="remark" styleId="remark" cols="5" style="width:820px;height:100px;" />
          <div id="info_tip_2" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>&nbsp;创建人：</td>
          <td width="50%" >
          <html-el:text property="create_name" styleId="create_name" maxlength="20" value="${af.map.user_name}" readonly="true" style="width:200px;" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>
          <td width="50%" >
          <fmt:formatDate var="create_date" value="${af.map.create_date}" pattern="yyyy-MM-dd" />
          <html-el:text property="create_date" styleId="create_date"  size="12" maxlength="10" readonly="true" value="${create_date}"  style="cursor:pointer;text-align:center;" />
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
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr><td colspan="4">
        <div style="display:none">
        <table width="100%">
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型1：</td>
          <td width="30%" ><input type="text" name="model_1" maxlength="125" value="${af.map.model_1}" id="model_1" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件1：</td>
          <td width="30%" >
          <input name="file_1" type="file" id="file_1" style="width: 300px;" />
          </td>
            <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_1}" target="_blank" >${af.map.fj_name_1}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型2：</td>
          <td width="30%" ><input type="text" name="model_2" maxlength="125" value="${af.map.model_2}" id="model_2" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td >
          <td class="title_item" nowrap="nowrap" align="right">参数附件2：</td>
          <td width="30%" >
          <input name="file_2" type="file" id="file_2" style="width: 300px;" />
          </td >
           <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_2}" target="_blank" >${af.map.fj_name_2}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型3：</td>
          <td width="30%" ><input type="text" name="model_3" maxlength="125" value="${af.map.model_3}" id="model_3" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件3：</td>
          <td width="30%" >
          <input name="file_3" type="file" id="file_3" style="width: 300px;" />   
          </td>
           <td width="20%"><c:if test="${not empty af.map.id }">
          <a href="${ctx}/${af.map.fj_3}" target="_blank" >${af.map.fj_name_3}</a>
           </c:if></td>
        </tr>
        </table></div>
        </td>
        </tr>
         <tr><td colspan="4" class="title_item" align="left">审核记录</td></tr>
        <tr>
        <td colspan="4">
        <table width="100%" >
        		<tr bgcolor="pink"><td>审核人</td><td>审核时间</td><td>能否满足参数</td><td>审核结果</td><td>审批意见</td></tr>
        		<c:forEach items="${auditList}" var="cur">
        		<tr>
        			<td>${cur.map.audit_user_name}</td>
        		<td><fmt:formatDate value="${cur.audit_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        		<td>
        		<c:if test="${cur.is_meet eq 0}">满足</c:if>
        		<c:if test="${cur.is_meet eq 1}">不满足</c:if>
        		</td>
        		<td>
        		<c:if test="${cur.audit_result eq 1}">审核通过</c:if>
        		<c:if test="${cur.audit_result eq -1}">驳回</c:if>
        		<c:if test="${cur.audit_result eq -2}">撤回</c:if>
        		<c:if test="${cur.audit_result eq -3}">驳回至制单人</c:if>
        		</td>
        		<td>${cur.audit_idea}</td>
        		</tr>
        		</c:forEach>
        </table>
        </td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td align="center" colspan="3" ><label>
 			<input class="but4" type="button" name="btn_submit1" id="btn_submit1" onclick="zancun(this.form);" value="暂存" /> &nbsp;&nbsp;
            <input class="but4" type="button" name="btn_submit2"  id="btn_submit2" onclick="tijiao(this.form);" value="提交" />&nbsp;&nbsp;
            <input class="but5" type="button" name="btn_back"  id="btn_back" value="返回" onclick="history.back();return false;" />
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
<script type="text/javascript">//<![CDATA[
                                        
$(document).ready(function(){ 
	 var acceptUploadFileExts = "7z,aiff, asf, avi, bmp, csv, doc, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip, docx, xlsx, pptx";
	$("#proj_type").attr("dataType", "Require").attr("msg", "请选择项目类型！");
	$("#proj_name").attr("datatype", "Require").attr("msg", "请填写项目名称");   
	$("#buyer").attr("datatype", "Require").attr("msg", "请填写采购人/投标人"); 
	$("#offer_date").attr("datatype", "Require").attr("msg", "请填写报价/开标日期"); 
	//$("#buyer_tel").attr("dataType", "Mobile").attr("msg", "手机格式不正确！").attr("Require","true");   
	$("#size").attr("dataType", "Integer").attr("msg", "请填写采购尺寸且必须为整数！").attr("Require","true");
	$("#buyer_num").attr("datatype", "Integer").attr("msg", "请填写采购数量且必须为整数！").attr("Require","true"); 

	<c:if test="${empty af.map.id}"> 
	$("#process_id").attr("datatype", "Require").attr("msg", "请选择审核流程");
	</c:if>
	
	$("#file_show" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);

	$("#file_1" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_2" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_3" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
	
	$("#memo").textbox({   
		maxLength: 300,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");  
	});

	$("#remark").textbox({   
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip_2").slideUp("normal");  
	});
	
    var id = '${af.map.id}';
	if(id!=""&&id!=null){
		var pro_ty='${af.map.proj_type}';
		if(pro_ty=="1"){
			$("#s_cg").text("投标人");
	         $("#s_dh").text("投标人电话");
	         $("#s_rq").text("开标日期"); 
		}
	} 

     $("#proj_type").change(function(){
		var py= $("#proj_type").val();
		if(py=="1"){
	         $("#s_cg").text("投标人");
	         $("#s_dh").text("投标人电话");
	         $("#s_rq").text("开标日期"); 
	    }else{
	    	$("#s_cg").text("采购人");
	         $("#s_dh").text("采购人电话");
	         $("#s_rq").text("报价日期"); 
	    }    

     });

		


	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("GcxmProj.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
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


});

function tijiao(f){ 
	var file_1 = $("#file_1").val();
	var model_1 = $("#model_1").val();
	if(file_1!=""){
		if(model_1==""){
			alert("附件已经存在，请选择推荐机型1");
			return false;
		}
	}

	var file_2 = $("#file_2").val();
	var model_2 = $("#model_2").val();
	if(file_2!=""){
		if(model_2==""){
			alert("附件已经存在，请选择推荐机型2");
			return false;
		}

	}

	var file_3 = $("#file_3").val();
	var model_3 = $("#model_3").val();
	if(file_3!=""){
		if(model_3==""){
			alert("附件已经存在，请选择推荐机型3"); 
			return false; 
		}

	} 
	
	if(Validator.Validate(f, 2)){ 
        $("#btn_submit1").attr("disabled", "true");
        $("#btn_submit2").attr("value", "正在提交...").attr("disabled", "true");
        $("#btn_back").attr("disabled", "true"); 
		$("#info_state").val(0); 
		f.submit();
	}
}

function zancun(f){ 
	var file_1 = $("#file_1").val();
	var model_1 = $("#model_1").val();
	if(file_1!=""){
		if(model_1==""){
			alert("附件已经存在，请选择推荐机型1");
			return false;
		}
	}

	var file_2 = $("#file_2").val();
	var model_2 = $("#model_2").val();
	if(file_2!=""){
		if(model_2==""){
			alert("附件已经存在，请选择推荐机型2");
			return false;
		}

	}

	var file_3 = $("#file_3").val();
	var model_3 = $("#model_3").val();
	if(file_3!=""){
		if(model_3==""){
			alert("附件已经存在，请选择推荐机型3"); 
			return false; 
		}

	} 
	
	if(Validator.Validate(f, 2)){
		$("#info_state").val(-1); 
        $("#btn_submit1").attr("value", "正在提交...").attr("disabled", "true");
        $("#btn_back").attr("disabled", "true");
		f.submit();
	}
}

function openModelChild(title){
	var url="${ctx}/manager/admin/GcxmProj.do?method=chooseModel&title="+title;
	$.dialog({
		title:  "选择推荐机型",  
		width:  625,
		height: 412, 
        lock:true ,
		content:"url:"+url
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

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
