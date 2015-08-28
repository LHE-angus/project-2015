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
<script type="text/javascript" src="${ctx}/commons/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <c:if test="${empty p_audit_node_id}">
	          <td>当前位置：行政办公系统&nbsp;&gt;&nbsp;请假申请</td>
        </c:if>
         <c:if test="${not empty p_audit_node_id}">
         	<td>当前位置：${naviString}</td>
         </c:if>
      </tr>
    </table>
  </div>
  <div class="tabsPageHeaderContent">
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/FilesSubmit" enctype="multipart/form-data">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="is_node" style="is_node" value="1" />
      <html-el:hidden property="submit_dept" value="${userInfo.map.org_name}" />
      <html-el:hidden property="p_audit_node_id" value="${p_audit_node_id}" />
       <!-- 区分是不是直接提交到流程-->
      <html-el:hidden property="send_to_process" value="" styleId="send_to_process"/>
      <html-el:hidden property="id" styleId="id" />
      <div style="height:10px;"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <c:if test="${af.map.file_status ne -1}">
          <tr>
            <td nowrap="nowrap" class="title_item">负 责 人：</td>
            <td nowrap="nowrap"><html-el:text property="apply_user_name" styleId="apply_user_name" maxlength="50" style="width:80%" />
            </td>
            <td nowrap="nowrap" class="title_item">电　　话：</td>
            <td nowrap="nowrap"><html-el:text property="apply_user_tel" styleId="apply_user_tel" maxlength="20" style="width:140px" /></td> 
          </tr>
          <tr>
            <td class="title_item">标　　题：</td>
            <td nowrap="nowrap"><html-el:text property="file_title" styleId="file_title" maxlength="100" style="width:80%;" /></td>
            <td nowrap="nowrap" class="title_item">文件编号：</td>
            <td nowrap="nowrap">
            <html-el:text property="file_no_left" styleId="file_no_left" maxlength="4" style="width:20px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_middle" styleId="file_no_middle" maxlength="8" style="width:32px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_right" styleId="file_no_right" value="自动生成" maxlength="16" style="width:80px;backgound-color:#E0E0E0;" readonly="true"/></td>
          </tr>
          <tr>
            <td class="title_item">申请人：</td>
            <td nowrap="nowrap"><html-el:text property="submit_user" styleId="submit_user" maxlength="100" style="width:80%;vertical-align:middle;" readonly="true" value="${userInfo.real_name}" /></td>
            <td class="title_item">申请时间：</td>
            <td nowrap="nowrap"><fmt:formatDate value="${submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" var="_submit_datetime" />
              <html-el:text property="submit_datetime" styleId="submit_datetime" size="20" maxlength="20" readonly="true" value="${_submit_datetime}" style="width:140px;" /></td>
          </tr>
          <tr>
            <td class="title_item">审批流程：</td>
            <c:if test="${empty p_audit_node_id}">
            <td nowrap="nowrap">
           <html-el:select property="audit_node_id" styleId="audit_node_id" style="width:385px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:optionsCollection name="KonkaoaFilesAuditNodeList" label="audit_node_name" value="link_id" />
            </html-el:select>
            &nbsp;<span style="color:red">*</span>
            </td>
            </c:if>
            <c:if test="${not empty p_audit_node_id}">
            <td nowrap="nowrap">
	           ${filesAuditNodeName }
	           <input type="hidden" name="audit_node_id" value="${filesAuditNodeId }"/>
            </td>
            </c:if>
            <td class="title_item">时　　限：</td>
            <td>
            <html-el:text property="time_limit" styleId="time_limit" style="width:140px;" ></html-el:text>
           </td>
          </tr>
          <c:if test="${!empty is_add}">
	          <tr>
		        <td class="title_item">模板名称</td>
		        <td><html-el:select property="module_name" styleId="module_name" style="width:225px;"> 
		        <html-el:option value="">==请选择==</html-el:option>
		              <c:forEach var="cur" items="${konkaOaModuleTypeList}">
		                <html-el:option value="${cur.module_id}">${fn:escapeXml(cur.module_name)}</html-el:option>
		              </c:forEach>
		            </html-el:select>
		             &nbsp;<span style="color:red">*</span>
		            </td>
	          </tr>
          </c:if>
          <tr>
            <td class="title_item">请示内容：</td>
            <td><span style="color: gray">复制Word文档的内容,会引入大量的文档样式。会影响在浏览器的显示效果，请尽量避免类似操作,并且注意预览模板的效果</span></td>
          </tr>
          <tr>
           <td class="title_item"></td>
            <td colspan="4">
            	<textarea rows="30" cols="50" name="content" id="content">${af.map.content}</textarea>
			  <script type="text/javascript">CKEDITOR.replace('content');</script>
            </td>
          </tr>
          
          <tr>
            <td class="title_item">&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap" class="title_item">上传附件：</td>
            <td><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
               <c:if test="${not empty attachmentList}">
          
          <tr style="display: none;">
            <td height="28" class="title_item">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
            </tr>
           </c:if>
            </td>
          </tr>
          
               <!-- 不做功能会签 -->
<!--           <tr style="display:none;"> -->
<!--             <td class="title_item">是否会签：</td> -->
<%--             <td colspan="5"><html-el:radio property="is_countersign" value="0" styleId="is_countersign_0" /> --%>
<!--               <label for="is_countersign_0"> 否 </label> -->
<%--               <html-el:radio property="is_countersign" value="1" styleId="is_countersign_1" /> --%>
<!--               <label for="is_countersign_1"> 是 </label> -->
<%--               <c:set var="display" value="none" /> --%>
<%--               <c:if test="${af.map.is_countersign eq 1}"> --%>
<%--                 <c:set var="display" value="" /> --%>
<%--               </c:if></td> --%>
<!--           </tr> -->
<!--           <tr id="is_countersign_tr" style="display: none;"> -->
<!--             <td class="title_item">会 签 人：</td> -->
<%--             <td colspan="5"><input type="hidden" name="countersign_ids" id="countersign_ids" value="${af.map.countersign_ids}" /> --%>
<%--               <input type="text" name="countersign_names" id="countersign_names" value="${af.map.countersign_names}" readonly="readonly"  style="width:80%;vertical-align:middle;" /> --%>
<%--               <img id="add_coun" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br /></td> --%>
<!--           </tr> -->
           <!-- 不做功能会签 -->
          
          <tr style="display: none;">
            <td class="title_item">是否下发：</td>
            <td colspan="5"><html-el:radio property="is_forward" value="0" styleId="is_forward_0" />
              <label for="is_forward_0"> 否 </label>
              <html-el:radio property="is_forward" value="1" styleId="is_forward_1" />
              <label for="is_forward_1"> 是 </label>
              <c:set var="display" value="none" />
              <c:if test="${af.map.is_forward eq 1}">
                <c:set var="display" value="" />
              </c:if></td>
          </tr>
          <tbody id="is_forward_tr" style="display: none;">
            <tr>
              <td class="title_item">下发用户：</td>
              <td colspan="5"><input type="hidden" name="receive_type_1_ids" id="receive_type_1_ids" value="${af.map.fa_ids}" />
                <input type="text" name="receive_type_1_names" id="receive_type_1_names" value="${af.map.fa_names}" readonly="readonly"  style="width:80%;vertical-align:middle;" />
                <img id="add_fa" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br />
                
                <!-- 不再实现送和呈方式 20150402发现java代码早已不实现了但页面有保留,严重恶心了代码的结构-->
<%--                 <input type="hidden" name="receive_type_2_ids" id="receive_type_2_ids" value="${af.map.song_ids}" /> --%>
<%--                 <input type="hidden" name="receive_type_3_ids" id="receive_type_3_ids" value="${af.map.cheng_ids}" /></td> --%>
            </tr>
            <tr>
              <td class="title_item">下发部门：</td>
              <td colspan="5"><input type="hidden" name="receive_dept_1_ids" id="receive_dept_1_ids" value="${af.map.dept_ids}" />
                <input type="text" name="receive_dept_1_names" id="receive_dept_1_names" value="${af.map.dept_names}" readonly="readonly"  style="width:80%;vertical-align:middle;" />
                <img id="add_dept" src="${ctx}/images/search.gif" style='vertical-align:middle; cursor: pointer;' alt='选择部门' /> <br />
            </tr>
          </tbody>
          
        </c:if>
   </table>
      <c:if test="${af.map.file_status eq 2}">
        <c:if test="${not empty filesAuditNodeList}">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
            <tr>
              <td width="90" align="center" nowrap="nowrap" class="td_bord">审批状况</td>
              <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批开始时间</td>
              <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批结束时间</td>
              <td align="center" nowrap="nowrap" class="td_bord">审批意见</td>
              <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批人/部门</td>
            </tr>
            <c:set var="begin_time" value="${af.map.submit_datetime}" />
            <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
              <c:set var="audit_result" value="未审批" />
              <c:if test="${cur.audit_result eq 2}">
                <c:set var="audit_result" value="<span style='color:#090;'>同意</span>" />
              </c:if>
              <c:if test="${cur.audit_result eq 1}">
                <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
              </c:if>
              <tr>
                <td align="center">${audit_result}</td>
                <td align="center"><c:if test="${not empty cur.audit_datetime}">
                    <fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                  </c:if></td>
                <td align="center"><c:if test="${not empty cur.audit_datetime}">
                    <fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                  </c:if></td>
                <td><c:if test="${not empty cur.audit_comment}"> ${fn:escapeXml(cur.audit_comment)} </c:if></td>
                <td>${cur.audit_user}</td>
              </tr>
            </c:forEach>
          </table>
        </c:if>
      </c:if>
      <div align="center" style="padding:15px; 0 0;">
        <input class="but4" type="button" name="Submit4" value="暂存" id="btn_submit_save" style="align:center;"/>
        <input class="but4" type="button" name="Submit4" value="提交" id="btn_submit_process" style="align:center;"/>
        <input class="but5" type="button" name="Submit5" value="返回" style="align:center;" onclick="history.back();" />
      </div>
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
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
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

    <c:if test="${af.map.is_forward ne 1}">
    $("#is_forward_0").trigger("click");
    </c:if>


    <c:if test="${af.map.is_stamp ne 1}">
    $("#is_stamp_0").trigger("click");
    </c:if>
    
    var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc,docx, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pptx , pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls,xlsx, xml, zip, exe";
    <c:if test="${af.map.file_status ne -1}">

    $("#submit_dept" ).attr("dataType", "Require").attr("msg", "提交部门必须填写");
    $("#file_title"  ).attr("dataType", "Require").attr("msg", "标题必须填写");
    $("#apply_people").attr("dataType", "Require").attr("msg", "申请人必须填写");       
    $("#audit_node_id").attr("dataType", "Require").attr("msg", "审批流程必须选择");
    $("#module_name").attr("dataType", "Require").attr("msg", "请假模板必须选择"); 
    $("#apply_user_tel").attr("require", "false").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("input[name=category11]:first").attr("dataType", "Group").attr("min", "1").attr("msg", "文件类型必须选择");
    $("input[name=is_forward]:first").attr("dataType", "Group").attr("min", "1").attr("msg", "是否下发必须选择");
    
    $("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
    $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
    $("#content"     ).attr("dataType", "Require").attr("msg", "请示内容必须填写");
    
    $("a[id^='att_del_']").click(function() {
  	  var a = this;
  	   if(!confirm('确实要删除此附件？')) return;
  	    $.post("ExpenseClaims.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
  	  });
   });
    
    var $is_stamp_1 = $("#is_stamp_1"), $is_stamp_first = $("input[name=category14]:first");
    
    $is_stamp_first.attr("min", "1").attr("msg", "需要的盖章类别必须选择");
    </c:if>
        
    // 提交
    var uri = document.getElementById("is_use_direct_uri_1");
    
    $("#btn_submit").click(function(){
        <c:if test="${af.map.file_status ne -1}">
        this.form.content.value = CKEDITOR.instances.content;
        if($is_stamp_1.is(":checked")){$is_stamp_first.attr("dataType", "Group");}else{$is_stamp_first.removeAttr("dataType");}
        </c:if>
        if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "提交中...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });
    
    
    // 提交
    $("#btn_submit_process").click(function(){
    	<c:if test="${af.map.file_status ne -1}">
	        this.form.content.value = CKEDITOR.instances.content;
	        if($is_stamp_1.is(":checked")){$is_stamp_first.attr("dataType", "Group");}else{$is_stamp_first.removeAttr("dataType");}
        </c:if>
        if(Validator.Validate(this.form, 1)){
            $("#btn_submit_save").attr("disabled", "true");
            $("#btn_submit_process").attr("value", "提交中...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });
    
    //保存 
    $("#btn_submit_save").click(function(){
    	<c:if test="${af.map.file_status ne -1}">
	        this.form.content.value = CKEDITOR.instances.content;
	        if($is_stamp_1.is(":checked")){$is_stamp_first.attr("dataType", "Group");}else{$is_stamp_first.removeAttr("dataType");}
        </c:if>
        if(Validator.Validate(this.form, 1)){
            $("#btn_submit_process").attr("disabled", "true");
            $("#btn_submit_save").attr("value", "保存 ...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });
    

   $("#add_fa").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + $("#receive_type_1_ids").val() + "&selectedUsers=" + getInputContent("receive_type_1_names") , window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			$("#receive_type_1_names").val(names);
			$("#receive_type_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
	
	$("#add_dept").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectDept&selectedDeptsID=" + $("#receive_dept_1_ids").val() + "&selectDepts=" + getInputContent("receive_dept_1_names") , window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			// alert(names);
			$("#receive_dept_1_names").val(names);
			$("#receive_dept_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
	
// 	$("#add_coun").click(function(){
// 		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + $("#countersign_ids").val() + "&selectedUsers=" + getInputContent("countersign_names"), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
// 		if (returnValue != null) {
// 			var names = returnValue.user_link_names;
// 			names = names.substring(0, names.length - 1);
// 			$("#countersign_names").val(names);
// 			$("#countersign_ids").val(returnValue.user_link_ids);
// 		}
// 	});	
	
	$("#is_stamp_0").click(function(){$("#is_stamp_div").slideUp();});
	$("#is_stamp_1").click(function(){$("#is_stamp_div").slideDown();});

    $("#is_forward_0").click(function(){
        $("#is_forward_tr").hide();

		//人员
        $("#receive_type_1_ids").val("");
    	$("#receive_type_1_names").val("");
    	$("#receive_type_2_ids").val("");
    	$("#receive_type_3_ids").val(""); 
    	//部门
    	$("#receive_dept_1_ids").val("");
    	$("#receive_dept_1_names").val("");
    	$("#receive_dept_2_ids").val("");
    	$("#receive_dept_3_ids").val(""); 
        resizeFrameHeight();
	});
    $("#is_forward_1").click(function(){
        $("#is_forward_tr").show();
        resizeFrameHeight();
    });
    

    var is_add = '${is_add}';
	//模板
	//if(is_add == 2){
		$("#module_name").multiselect({
		   noneSelectedText: '==请选择==',
		   selectedList: 1,
		   multiple: false,
		   minWidth:220,
		      click: function(event, ui){
		         if(ui.value != ""){
			       // alert(ui.value); 
		            showContent(ui.value);
		         } else {
		        	CKEDITOR.instances.content.setData("");
		         }
		      }
		    }).multiselectfilter();
	//} 
});

function showContent(module_id){
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/oa/KonkaOaModuleType.do",
		data: "method=getContent&module_id=" + module_id,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(data) {
			CKEDITOR.instances.content.setData(data[0]["content"]);
		}
	});
} 



function removeAuditUser(obj){
    $(obj).parent().remove();
    a_u_n--;
    var n = 1;
    $("#num", $audits).each(function(){$(this).text(n++);});
}

function selectUser(obj) {
    var $p = $(obj).parent();
    var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedUsersID=" +  $("#audit_user_id", $p).val(), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
    if (returnValue != null) { 
        var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
        ids = ids.substring(0, ids.length - 1);
        names = names.substring(0, names.length - 1);
        $("#audit_user_name", $p).val(names);
        $("#audit_user_id", $p).val(ids);
    }
}

function getAuditUsers(obj) {
    var users = "${userInfo.id},";
    $("input[name=audit_user_id]").each(function(){
        if($(this).val().length != 0 && $(this).val() != obj.value) {
            users += $(this).val() + ",";
        }
    });
    return users.substring(0, users.length - 1);
}

function getInputContent(id){
    var v = $("#" + id).val();
    if(v.length != 0) {
        return v + ",";
    }
    
    return v;
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
