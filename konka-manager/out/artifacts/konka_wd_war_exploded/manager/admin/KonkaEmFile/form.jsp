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
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaEmFile" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="order_value" styleId="order_value" value="0" />
      <html-el:hidden property="is_del" styleId="is_del" value="0" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">

        <tr>
          <td class="title_item">文件标题：</td>
          <td><html-el:text property="file_title" styleId="file_title" maxlength="1000" style="width:200px;" />
          &nbsp;<span style="color:red">*</span>
          </td>
        </tr>
        <c:set var="disabled" value="false" />
        <c:if test="${not empty af.map.id}">
        	<c:set var="disabled" value="true" />
        </c:if>
        <tr>
          <td class="title_item">文件类型：</td>
          <td><html-el:select property="file_type" styleId="file_type" onchange="fileTypeChange(this.value);" disabled="${disabled}">
          		<html-el:option value="">请选择...</html-el:option>
          		<html-el:option value="10">3C证书</html-el:option>
          		<html-el:option value="20">获奖证书</html-el:option>
          		<html-el:option value="30">检测报告</html-el:option>
          		<html-el:option value="40">产品参数</html-el:option>
          		<html-el:option value="50">库存</html-el:option>
          		<html-el:option value="60">生产计划</html-el:option>
          		<html-el:option value="80">节能证书</html-el:option>
          		<html-el:option value="90">表格模版</html-el:option>
          		<html-el:option value="100">软件升级资料</html-el:option>          		
          		<html-el:option value="70">其他</html-el:option>
          	  </html-el:select>
          	  &nbsp;<span style="color:red">*</span>
          </td>
        </tr>
                <tr>
          <td class="title_item" nowrap="nowrap">文件编号：</td>
          <td width="88%" >
            <html-el:text property="file_no" styleId="file_no" maxlength="60" style="width:200px;" readonly="true" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tbody id="is_forward_tr" style="display:${display};">
          <tr>
            <td class="title_item" rowspan="2">下发对象：</td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><html-el:radio property="file_xf_type" styleId="all_user" value="0" />
                    <label for="all_user" id="u_all_user">&nbsp;全部工程机角色用户</label></td>
                </tr>
                <tr>
                  <td><html-el:radio property="file_xf_type" styleId="spe_user" value="2" />
                    <label for="spe_user" id="u_spe_user">&nbsp;选择用户</label>
                    <html-el:hidden property="link_user_ids" styleId="link_user_ids" value="${af.map.user_ids}" />
                    <input type="text" name="link_users" id="link_users" value="${af.map.user_names}" readonly="readonly"  style="width:400px;display:none;" />
                    <img id="add_link_user" src="${ctx}/images/search.gif" style='margin: 0 0 -6px 0; cursor: pointer;display:none;' alt='选择人员' /></td>
                </tr>
              </table></td>
          </tr>
        </tbody>
        <tr>
          <td class="title_item">内容：</td>
          <td><FCK:editor instanceName="content" width="80%">
              <jsp:attribute name="value">${file_content}</jsp:attribute>
            </FCK:editor>
            <div style="padding-top: 5px;">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小！</div>
            <div style="padding:2px;">2、点击最后一排倒数第三个按钮可实现全屏编辑</div></td>
        </tr>
        <tr><td class="title_item">&nbsp;</td><td>&nbsp;</td>
        </tr>
        <tr id="trFile">
          <td class="title_item">文件上传：</td>
          <td>
            <div id="divFileHidden" style="display: none;">
              <input name="file_hidden" type="file" id="file_hidden" size="120" style="width: 500px;" />
              <img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
            <div id="divFile">
              <input name="file_show" type="file" id="file_show" style="width: 500px;" />
              <img src="${ctx }/images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div></td>
        </tr>
        <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr style="display:none;">
          <td class="title_item">发件人：</td>
          <td><html-el:text property="add_user_name" styleId="add_user_name" maxlength="20" style="width:180px" value="${userInfo.user_name}" />
          &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
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
<c:if test="${!empty is_add}">
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
</c:if>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	 $("#imgAddTr").click(function (){
			$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
			resizeFrameHeight();
		    $("img[id='imgDelTr']").each(function(){
				$(this).click(function (){
					$(this).parent().remove();
					resizeFrameHeight();
				});
			});
		});

<c:if test="${af.map.file_xf_type eq 2}">
	$("#spe_user").trigger("click");
	$("#add_link_user").show();
	$("#link_users").show();
	$("#link_user_ids").attr("dataType", "Require").attr("msg", "用户必须选择！");
	// $("#link_users").val("${af.map.user_names}"+",");
    // $("#link_user_ids").val("${af.map.user_ids}"+",");
</c:if>
<c:if test="${af.map.file_xf_type ne 2}">
	$("#all_user").trigger("click");
	$("#link_user_ids").removeAttr("dataType");
</c:if>
   
    $("#add_user_name"	).attr("dataType", "Require").attr("msg", "发件人必须填写！");
	$("#file_title"	).attr("dataType", "LimitB").attr("min","1").attr("max","1000").attr("msg", "文件标题必须填写，且不能超过500个汉字！");
	$("#file_no"	).attr("dataType", "LimitB").attr("min","1").attr("max","60").attr("msg", "文件编号必须填写，且不能超过60个汉字！");
	$("#file_type").attr("dataType", "Require").attr("msg", "文件类型必须选择！");
	$("#all_user").attr("dataType", "Require").attr("msg", "下发对象必须选择！");
	
	 var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc,docx, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls,xlsx, xml, zip, exe";
	 $("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
	 $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);

	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("KonkaEmFile.do?", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("^_^ 恭喜您，删除成功!");$(a).parent().remove();} else alert("-_- 很抱歉，删除出错!"); 
		  });
		 });
	 
	$("#all_user").click(function(){
		if(this.checked){
			$("#add_link_user").hide();
			$("#link_users").hide();
			$("#tr_select_dept").hide();

			$("#link_user_ids").removeAttr("dataType");
			resizeFrameHeight();
		}
	});

	$("#spe_user").click(function(){
		if(this.checked){
			$("#add_link_user").show();
			$("#link_users").show();
			$("#tr_select_dept").show();

			$("#link_user_ids").attr("dataType", "Require").attr("msg", "用户必须选择！");
			resizeFrameHeight();
		}
	});
	
	// 提交
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	$("#add_link_user").click(function(){
		var link_users = $("#link_users").val();
		var link_user_ids = $("#link_user_ids").val();
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + link_user_ids + "&selectedUsers=" + link_users + "%20", window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
	    if(returnValue != null) {
	        var names = returnValue.user_link_names;
	        names = names.substring(0, names.length - 1);
	        $("#link_users").val(names);
	        $("#link_user_ids").val(returnValue.user_link_ids);
		};
	});

});

function showContent(module_id){
	$.ajax({
		type: "POST",
		url: "${ctx}/chengduoa/KonkaOaModuleType.do",
		data: "method=getContent&module_id=" + module_id,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(data) {
			//var oEditor = FCKeditorAPI.GetInstance('content'); 
			//oEditor.setHtml(data[0]["module_content"]);

			var oEditor = FCKeditorAPI.GetInstance('content');  
			oEditor.SetHTML(data[0]["content"]); 
		}
	});
} 

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function fileTypeChange(file_type){
	
	var file_no = "";
	if(file_type == "10"){
		//3C证书
		file_no = "3C";
	}else if(file_type == "20"){
		//获奖证书
		file_no = "HJ";
	}else if(file_type == "30"){
		//检测报告
		file_no = "JC";
	}else if(file_type == "40"){
		//产品参数
		file_no = "CP";
	}else if(file_type == "50"){
		//库存
		file_no = "KC";
	}else if(file_type == "60"){
		//生产计划
		file_no = "SC";
	}else if(file_type == "70"){
		//其他
		file_no = "QT";
	}else if(file_type == "80"){
		//其他
		file_no = "JN";
	}else if(file_type == "90"){
		//其他
		file_no = "BG";
	}else if(file_type == "100"){
		//其他
		file_no = "RJ";
	}
	if(file_type != ""){
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/KonkaEmFile.do",
			data: "method=createCode&file_type=" + file_type,
			dataType: "text",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(data) {
				file_no += data;
				$("#file_no").val(file_no);
			}
		});
	}else {
		$("#file_no").val(file_no);
	}
	
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
