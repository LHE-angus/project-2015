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
    <html-el:form action="/oa/KonkaOaNotice" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="order_value" styleId="order_value" value="0" />
      <html-el:hidden property="is_del" styleId="is_del" value="0" />
      <html-el:hidden property="doc_type" styleId="doc_type" value="${af.map.doc_type}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" nowrap="nowrap">文件编号：</td>
          <td width="88%" >
            <html-el:text property="file_no_left" styleId="file_no_left" maxlength="4" style="width:20px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_middle" styleId="file_no_middle" maxlength="8" style="width:32px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_right" styleId="file_no_right" value="自动生成" maxlength="16" style="width:80px;backgound-color:#E0E0E0;" readonly="true"/></td>
        </tr>
        <tr>
          <td class="title_item">文件标题：</td>
          <td><html-el:text property="title" styleId="title" maxlength="100" style="width:74%;" />
          &nbsp;<span style="color:red">*</span>
          </td>
        </tr>
        <tbody id="is_forward_tr" style="display:${display};">
          <tr>
            <td class="title_item" rowspan="2">下发范围：</td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><html-el:radio property="receive_type" styleId="all_user" value="0" />
                    <label for="all_user" id="u_all_user">&nbsp;全部用户</label></td>
                </tr>
                <tr>
                  <td><html-el:radio property="receive_type" styleId="spe_user" value="1" />
                    <label for="spe_user" id="u_spe_user">&nbsp;选择用户</label>
                    <html-el:hidden property="link_user_ids" styleId="link_user_ids" value="${af.map.user_ids}" />
                    <input type="text" name="link_users" id="link_users" value="${af.map.user_names}" readonly="readonly"  style="width:400px;display:none;" />
                    <img id="add_link_user" src="${ctx}/images/search.gif" style='margin: 0 0 -6px 0; cursor: pointer;display:none;' alt='选择人员' /></td>
                </tr>
                <tr id="tr_select_dept" style="display:none;">
                  <td style="padding-left:31px;">选择部门
                    <html-el:hidden property="link_dept_ids" styleId="link_dept_ids" value="${af.map.dept_ids}" />
                    <input type="text" name="link_depts" id="link_depts" value="${af.map.dept_names}" readonly="readonly"  style="width:400px;" />
                    <img id="add_link_dept" src="${ctx}/images/search.gif" style='margin: 0 0 -6px 0; cursor: pointer;' alt='选择部门' /> <br /></td>
                </tr>
              </table></td>
          </tr>
        </tbody>
        <tr>
          <td class="title_item">内容：</td>
          <td><FCK:editor instanceName="content" width="80%"  height="280px">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
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
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <!-- 
        <tr>
        	<td class="title_item">是否发送短信：</td>
        	<td><html-el:select property="domsg" styleId="domsg">
        	        <html-el:option styleId="domsg_2" value="2">否</html-el:option>
        	        <html-el:option styleId="domsg_1" value="1">是</html-el:option>
        	</html-el:select>
        	</td>
        </tr>
         -->
        <tr style="display:none;">
          <td class="title_item">发件人：</td>
          <td><html-el:text property="draft_man" styleId="draft_man" maxlength="20" style="width:180px" value="${userInfo.user_name}" />
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
	 
<c:if test="${af.map.receive_type eq 1}">
	$("#spe_user").trigger("click");
	$("#add_link_user").show();
	$("#link_users").show();
	// $("#link_users").val("${af.map.user_names}"+",");
    // $("#link_user_ids").val("${af.map.user_ids}"+",");
    $("#tr_select_dept").show();
</c:if>
<c:if test="${af.map.receive_type ne 1}">
	$("#all_user").trigger("click");
</c:if>
   
    $("#draft_man"	).attr("dataType", "Require").attr("msg", "拟稿人必须填写！");
	$("#title"	).attr("dataType", "Require").attr("msg", "文件标题必须填写！");
	
	 var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc,docx, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls,xlsx, xml, zip, exe";
	 $("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
	 $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);

	 $("a[id^='att_del_']").click(function() {
		  var a = this;
		   if(!confirm('确实要删除此附件？')) return;
		    $.post("FilesSubmit.do?", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		   if (success){alert("^_^ 恭喜您，删除成功!");$(a).parent().remove();} else alert("-_- 很抱歉，删除出错!"); 
		  });
		    resizeFrameHeight();
		 });
	 
	$("#all_user").click(function(){
		if(this.checked){
			$("#add_link_user").hide();
			$("#link_users").hide();
			$("#tr_select_dept").hide();
			resizeFrameHeight();
		}
	});

	$("#spe_user").click(function(){
		if(this.checked){
			$("#add_link_user").show();
			$("#link_users").show();
			$("#tr_select_dept").show();
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
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + link_user_ids + "&selectedUsers=" + link_users + "%20", window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
	    if(returnValue != null) {
	        var names = returnValue.user_link_names;
	        names = names.substring(0, names.length - 1);
	        $("#link_users").val(names);
	        $("#link_user_ids").val(returnValue.user_link_ids);
		};
	});

	$("#add_link_dept").click(function(){
		var link_depts = $("#link_depts").val();
		var link_dept_ids = $("#link_dept_ids").val();
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectDept&selectedDeptsID=" + link_dept_ids + "&selectedDepts=" + link_depts + "%20", window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
	    if(returnValue != null) {
	        var names = returnValue.user_link_names;
	        $("#link_depts").val(names);
	        $("#link_dept_ids").val(returnValue.user_link_ids);
		};
	});
});

function showContent(module_id){
	$.ajax({
		type: "POST",
		url: "KonkaOaModuleType.do",
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

//文件编号生成---新增
<c:if test="${empty af.map.file_no}">
   var fgs_dept_name = '${af.map.fgs_dept_name}';
   var yymm = '${af.map.yymm}';
   var file_first = '';
   if(fgs_dept_name != ''){
	  for(var i=0; i < fgs_dept_name.length; i++){
		  var s = fgs_dept_name.charAt(i);
		  file_first = file_first + ucfirstLetter(CC2PY(s));
	  }	
   }else{
	    file_first = 'KK';
   }
   $("#file_no_left").val(file_first);
   $("#file_no_middle").val(yymm);
</c:if>
//文件编号初始化---编辑
<c:if test="${not empty af.map.file_no}">
   var file_no_str = '${af.map.file_no}';
   $("#file_no_left").val(file_no_str.substr(0,2));
   $("#file_no_middle").val(file_no_str.substr(2,4));
   $("#file_no_right").val(file_no_str.substr(6));
</c:if>

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
