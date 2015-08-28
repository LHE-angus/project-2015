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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
         <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/chengduoa/AuditNodeManager" enctype="multipart/form-data">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="link_id" styleId="link_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td width="15%" class="title_item">流程名称：</td>
          <td width="85%"><html-el:text property="audit_node_name" styleId="audit_node_name" style="width:300px" /></td>
        </tr>
        <tr>
          <td width="15%" class="title_item">流程类别：</td>
          <td width="85%"><html-el:select property="node_type" styleId="node_type">
          	  <html-el:option value="0">文件审批</html-el:option>
          	  <html-el:option value="1">费用审批</html-el:option>
          	  <html-el:option value="2">请假审批</html-el:option>
          </html-el:select>
          </td>
        </tr>
        <tr>
          <td>审批流程：</td>
          <td id="nodes">
          	<c:if test="${not empty konkaoaFilesAuditNodeList}">
              <c:forEach var="cur" items="${konkaoaFilesAuditNodeList}" varStatus="vs">
                <div>审批节点：
                  <input type="hidden" name="node_user_id" id="node_user_id" value="${cur.audit_user_id}" />
                  <input type="text" name="node_user_name" id="node_user_name" readonly="readonly" value="${cur.audit_user}" style="width:200px;vertical-align:middle;" />
                  <img onclick="selectUser(this);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" />
                  <c:if test="${1 eq vs.count }"><img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></c:if>
                  <c:if test="${1 ne vs.count and (af.map.id == ''||af.map.id == null)}"> <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" onclick="$(this).parent().remove();" title="删除" /></c:if>
                </div>
              </c:forEach>
            </c:if>
            <c:if test="${empty konkaoaFilesAuditNodeList}">
              <div>审批节点：
                <input type="text" style="width:200px;" readonly="readonly" name="node_user_name" id="node_user_name"/>
                <input type="hidden" name="node_user_id" id="node_user_id"/>
                <img onclick="selectUser(this);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
            </c:if>
          </td>
        </tr>
      </table>
      <div align="center" style="padding:15px; 0 0;">
        <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
        <input class="but5" type="button" name="Submit5"  value="返回" onclick="history.back();return false;" />
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<div style="display:none;" id="n_m">审批节点：
  <input type="text" style="width:200px;" readonly="readonly" name="node_user_name" id="node_user_name"/>
  <input type="hidden" name="node_user_id" id="node_user_id" />
  <img onclick="selectUser(this);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" />
  <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" onclick="$(this).parent().remove();" title="删除" /></div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#audit_node_name").attr("dataType", "Require").attr("msg", "流程名称必须填写！");
	
	 var div = $('#n_m');
     div.find("#node_user_name").attr("dataType", "Require").attr("msg", "请选择审批节点");
	
	$("input[name=node_user_name]:visible").each(function(){ 
			$(this).attr("dataType", "Require").attr("msg", "请选择审批节点！");
	 }); 

	 //	
	 var id = '${af.map.id}';
	 if(id == ''||id == null){
	$("#imgAddTr").click(function(){
		$("#nodes").append($("#n_m").clone().removeAttr("id").show());
		resizeFrameHeight();
	});
	 }
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "提交中...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });	

	$("#audit_node_name").change(function(){
		if(this.value.length > 0 && this.value != '${af.map.audit_node_name}'){
			$.ajax({
				type: "POST",
				url: "AuditNodeManager.do",
				data: "method=validateName&audit_node_name=" + this.value,
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(isExist) {
					if(isExist == 11) {
						alert("该流程名称已存在，请重新填写!");
						$("#audit_node_name").val("");
						return ;
					}  
				}
			});
			} 
		});
});

//选择审核人
function selectUser(obj) {
	var id = '${af.map.id}';
	 if(id == ''||id == null){
    	var $p = $(obj).parent();
		var ids = document.getElementsByName("node_user_name");
		var select_ids = ""; 
		$("input[name='node_user_id']").each(function(){  
			if("" != $(this).val())
			select_ids += $(this).val()+","; 
		 }); 
    	var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedIds="+ select_ids +"&selectedUsersID=" +  $("#node_user_id", $p).val(), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
    	if (returnValue != null) { 
        	var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
        	ids = ids.substring(0, ids.length - 1);
        	names = names.substring(0, names.length - 1);
        	$("#node_user_name", $p).val(names);
        	$("#node_user_id", $p).val(ids);
    	}
	 }
}

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
