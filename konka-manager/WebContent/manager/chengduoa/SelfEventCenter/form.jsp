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
<!-- 支持流程的. -->
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：协同办公&gt;审批</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
        <html-el:form action="/chengduoa/SelfEventCenter" enctype="multipart/form-data">
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" styleId="method" value="save" />
          <html-el:hidden property="mod_id" styleId="mod_id" />
          <html-el:hidden property="id" styleId="id" />
          <html-el:hidden property="is_agent" />
          <html-el:hidden property="agent_user_id" />
          <html-el:hidden property="file_type" />
          <html-el:hidden property="is_node" style="is_node" value="1" />
          <div style="height:10px;"></div>
          <c:set var="oper_name" value="审批" />
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr>
              <td width="10%" align="center" nowrap="nowrap">负 责 人</td>
              <td ><html-el:text property="apply_user_name" styleId="apply_user_name" maxlength="50" /></td>
              
              <td width="10%" align="center" nowrap="nowrap">电    话</td>
              <td width="20%" align="left" nowrap="nowrap"><html-el:text property="apply_user_tel" styleId="apply_user_tel" maxlength="12" /></td>
              <td width="10%" align="center" nowrap="nowrap">编　　号</td>
              <td width="15%">${fn:escapeXml(af.map.file_no)}</td>
            </tr>
            <tr>
              <td align="center">审 批 人</td>
              <td><div id="audits">
                  <c:set var="audit_node_num" value="2" />
                  <c:if test="${not empty _filesAuditNodeList}">
                    <c:forEach var="cur" items="${_filesAuditNodeList}" varStatus="vs"> ${cur.audit_user}
                      <c:if test="${vs.last ne true}">，</c:if>
                    </c:forEach>
                  </c:if>
                </div></td>   
              <td align="center" nowrap="nowrap">申 请 人</td>
              <td align="left">${af.map.submit_user}</td>
              <td align="center" nowrap="nowrap">申请时间</td>
              <td nowrap="nowrap" align="left"><fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            
           <!-- 1提交文件 -->
           <c:if test="${af.map.file_type eq 0 }">
           <tr>
              <td align="center">标    题</td>
              <td colspan="3"><html-el:text property="file_title" styleId="file_title" maxlength="50" /></td> 
              <td align="center">时　　限</td>
              <td>
              <html-el:select property="time_limit" style="width:140px;">
                <html-el:option value="1">1天</html-el:option>
                <html-el:option value="2">2天</html-el:option>
                <html-el:option value="3">3天</html-el:option>
              </html-el:select>
             </td>
            </tr>
            <tr>
              <td align="center">请示内容</td>
              <td height="200" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">
	              <FCK:editor instanceName="content"  height="250" width="96%">
	                <jsp:attribute name="value" >${af.map.content }</jsp:attribute>
	              </FCK:editor>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                <span>
                ${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>
                </span>
                </c:forEach>
              </td>
            </tr>
           </c:if>
           
            <!-- 2费用申请 -->
            <c:if test="${af.map.file_type ne 0 }">
            <tr>
              <td align="center">标    题</td>
              <td>${fn:escapeXml(af.map.file_title)}</td> 
              <td align="center">申请网点</td>
              <td>${fn:escapeXml(shop_name)}</td>
              <td align="center">时　　限</td>
              <td>${fn:escapeXml(af.map.time_limit)}天</td>
            </tr>
            <tr>
               <td align="center">费用总额</td>
               <td colspan="5"><html-el:text property="column_6" styleId="column_6" style="width:120px;background:#cccccc;" maxlength="10" size="10" value="${af.map.column_6}" readonly="true" /></td>
            </tr>
            
            <c:if test="${not empty filesPropertyList}">
            <tr>
             <td width="10%" align="center" nowrap="nowrap">费用类别</td>
              <td colspan="5"><table width="96%" border="0" align="left" cellpadding="0" cellspacing="0" id="categorys_td" style="border:#ccc 1px solid;">
                <tr>
                  <td colspan="4"></td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
                  <td align="center" colspan="2">${cur.map.real_name }</td>
                  </c:forEach>
                  <td colspan="2" align="center" >审批</td>
                </tr>
                <tr>
                  <td align="left">费用类别</td>
                  <td align="center">说明</td>
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                  </c:forEach>
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                </tr>
                <c:forEach items="${filesPropertyList}" var="_cur" >
                <tr>
                  <td align="left">${fn:escapeXml(_cur.map.c_name)}<html-el:hidden property="c_index" styleId="c_index_1" value="${_cur.c_index}" /></td>
                  <td align="center">${fn:escapeXml(_cur.c_desc)}</td>
                  <td align="right">${fn:escapeXml(_cur.amount)}</td>
                  <td align="right"><fmt:formatNumber pattern="0.00" value="${_cur.cost}" /></td>
                  <c:forEach items="${_cur.map.appList}" var="ct">
                 	 <td align="right"><fmt:formatNumber pattern="0" value="${ct.amount}" /></td>
                 	 <td align="right"><fmt:formatNumber pattern="0.00" value="${ct.cost}" /></td>
                  </c:forEach>
                  <td><html-el:text property="app_amount_${_cur.c_index}" styleId="app_amount_${_cur.c_index}" maxlength="8" size="5" onkeyup="javascript:setOnlyNum(this);" value="${_cur.map.app_amount} " onblur="sum_money(this);"/></td>
                  <td><html-el:text property="app_money_${_cur.c_index}" styleId="app_money_${_cur.c_index}" maxlength="8" size="5" onkeyup="javascript:setOnlyDouble(this);" value="${_cur.map.app_money}" onblur="sum_money(this);"/></td>
                </tr>
                </c:forEach>
            </table><br /></td>
            </tr>
            </c:if>
            <tr>
              <td align="center">详细内容</td>
              <td height="250" width="100%" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">
              <FCK:editor instanceName="content"  height="250" width="96%">
	                <jsp:attribute name="value" >${af.map.content }</jsp:attribute>
	              </FCK:editor>
              <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
              	<span>${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a> <br />
              </span>
              </c:forEach>
              </td>
            </tr>
            </c:if>
            
            <c:if var="is_huiqian" test="${af.map.file_status ne -1 and af.map.file_status ne 1 and af.map.file_status ne 0}">
            <tr style="display:${display};">
              <td align="center">下发范围</td>
              <td colspan="5">${af.map.fa_names} </td>
            </tr>
            </c:if>
            <c:if test="${!is_huiqian}">
            <tr>
              <td align="center">是否下发</td>
              <td colspan="5">
                <html-el:radio property="is_xiafa" value="0" styleId="is_xiafa_0" />
                <label for="is_xiafa_0"> 否 </label>
                
                <html-el:radio property="is_xiafa" value="1" styleId="is_xiafa_1" />
                <label for="is_xiafa_1"> 是 </label>
                
                <c:set var="display" value="none" />
                <c:if test="${af.map.is_forward eq 1}">
                  <c:set var="display" value="" />
                </c:if>
                
              </td>
            </tr>
            <tbody id="is_xiafa_tr" style="display:${display};">
              <tr>
                <td align="center">下发用户</td>
                <td colspan="5"><input type="hidden" name="receive_type_1_ids" id="receive_type_1_ids" value="${af.map.fa_ids}" />
                  <input type="text" name="receive_type_1_names" id="receive_type_1_names" value="${af.map.fa_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_fa" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br />
              </tr>
              <tr>
                <td align="center">下发部门</td>
                <td colspan="5"><input type="hidden" name="receive_dept_1_ids" id="receive_dept_1_ids" value="${af.map.dept_ids}" />
                  <input type="text" name="receive_dept_1_names" id="receive_dept_1_names" value="${af.map.dept_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_dept" src="${ctx}/images/search.gif" style='vertical-align:middle; cursor: pointer;' alt='选择部门' /> <br />
              </tr>
            </tbody>
            </c:if>
            
            <tr>
              <td colspan="6" height="5"></td>
            </tr>
            <tr>
              <td colspan="3" align="center">${oper_name}意见</td>
              <td align="center">${oper_name}签名</td>
              <td colspan="2" align="center" >${oper_name}时间</td>
            </tr>
            <tr>
              <td colspan="3" align="center"><html-el:textarea property="audit_comment" styleId="audit_comment"  rows="3" cols="80" style="width:95%"></html-el:textarea></td>
              <td align="center">${userInfo.real_name}</td>
              <td colspan="2" align="center" ><fmt:formatDate value="${af.map.now}" pattern="yyyy-MM-dd HH:mm:ss" var="_now" />
                <html-el:hidden property="audit_datetime" styleId="audit_datetime" value="${_now}" />
                ${_now}</td>
            </tr>
          </table>
          
          <!-- 非会签的时候,要填写 -->
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td nowrap="nowrap" class="title_item" align="center" width="150">我的审批结果是:</td>
                <td>
                  <input type="radio" name="audit_result" value="2" id="audit_result_2"/>
                  <label for="audit_result_2" style="color:#090;font-weight:bold;">同意</label>
                  
                  <input type="radio" name="audit_result" value="1" id="audit_result_1"/>
                  <label for="audit_result_1" style="color:#f00;font-weight:bold;">驳回</label>
                  </td>
              </tr>
              <tr style="display:none" id="hidden_bohui_tr">
                <td nowrap="nowrap" class="title_item" align="center" width="150"><label style="color:#f00;font-weight:bold;">驳回至:</label></td>
                <td>
                 <select id="returnNode" name="return_node">
                  <option value =""></option>
                  <!-- 制单人有可能不在流程上面,但为了能驳回到制单人,所以要加上这个特殊的处理submit_user_id -->
                  <option value="submit_user_id${af.map.submit_user_id} ">制单人(${af.map.submit_user })</option>
				  <c:forEach var="cur" items="${returnNodeSet}">
				  <!-- 返回流程的节点级别,比如第三个节点 -->
				   <option value ="${cur.audit_level }">${cur.audit_user }</option>
				  </c:forEach>
				 </select>
                 </td>
              </tr>
            </table>
            <br/>
          
          
          <!-- 流程节点,各个人的审批意见 -->
          <c:if test="${not empty filesAuditNodeList}">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td width="70" align="center" nowrap="nowrap">审批状况</td>
                <td width="130" align="center" nowrap="nowrap" >审批开始时间</td>
                <td width="130" align="center" nowrap="nowrap">审批结束时间</td>
                <td align="center" nowrap="nowrap">审批意见</td>
                <td width="140" align="center" nowrap="nowrap" >审批人/部门</td>
              </tr>
              <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
                <c:set var="i" value="${vs.count}" />
                <c:if test="${cur.audit_result eq 2}">
                  <c:set var="audit_result" value="同意" />
                </c:if>
                <c:if test="${cur.audit_result eq 1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
                </c:if>
                <c:if test="${cur.audit_result eq -1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>转发</span>" />
                </c:if>
                <c:if var="is_vs_last" test="${vs.last}">
                	<c:set var="begin_time" value="${af.map.submit_datetime}" />
                </c:if>
                <c:if test="${!is_vs_last}">
                	<c:set var="begin_time" value="${filesAuditNodeList[i].audit_datetime}" />
                </c:if>
                <tr>
                  <td align="center" width="130" >${audit_result}</td>
                  <td align="center" width="130" ><fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <td align="center"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                  <c:set var="begin_time" value="${cur.audit_datetime}" />
                  </td>
                  <td>${fn:escapeXml(cur.audit_comment)}</td>
                  <td align="center" width="140" >${cur.audit_user}</td>
                </tr>
              </c:forEach>
            </table>
          </c:if>
          
          
          <br/>
          <div align="center" style="padding:15px; 0 0;"><input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
          <input class="but5" type="button" name="Submit5"  value="返回" onclick="history.back();" /></div>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#audit_comment" ).attr({"dataType":"LimitB","max":"2000","msg":"填写${oper_name}意见不能超过2000个字符"});
	$("input[name=audit_result]:first").attr("dataType", "Group").attr("msg", "请选择审批结果");
	$("#audit_result_2").attr("checked", true);
	$("#is_forward_0").attr("checked", true);
	
	<c:if test="${af.map.file_type ne 0 }">
	$("[id^=app_amount_]").attr("dataType", "Integer").attr("msg", "请正确填写数量");
	$("[id^=app_money_]").attr("dataType", "Double").attr("msg", "请正确填写金额");
	</c:if>
	
	$("#content").attr("dataType", "Require").attr("msg", "请示内容必须填写");
	
 	// 提交
	$("#btn_submit").click(function(){

		var audit_result_val=$('input:radio[name="audit_result"]:checked').val();
		if(audit_result_val=='1'){
			if($('#returnNode').val()==null ||$('#returnNode').val()=='' ){
				alert('请选择驳回的审核节点.');
				return false;
			}
		}
		 this.form.content.value = FCKeditorAPI.GetInstance("content").GetXHTML();
		if(Validator.Validate(this.form, 1)){
			if (confirm("是否提交审批流程? ")) {
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			}
		}
		
		
	});
	
// 	<c:if test="${!is_huiqian}">
	$("#is_xiafa_0").trigger("click");
    <c:if test="${af.map.is_forward eq 1}">
    	$("#is_xiafa_1").trigger("click");
    </c:if>
    $("#is_xiafa_0").click(function(){
    	$("#is_xiafa_tr").hide();
        
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
    $("#is_xiafa_1").click(function(){
        $("#is_xiafa_tr").show();
        resizeFrameHeight();
    });
//     </c:if>
	
	$("#is_forward_0").click(function(){
		setForwardDivHide();
		resizeFrameHeight();
	});
	
	$("#is_forward_1").click(function(){
		setForwardDivShow();
		 resizeFrameHeight();
	});
	
	$("#audit_result_1").click(function(){notForwardOtherPeople();
		$("#hidden_bohui_tr").show();
	});
	
	
	$("#audit_result_2").click(function(){
		$("#is_forward_0, #is_forward_1").removeAttr("disabled");
		$("#hidden_bohui_tr").hide();
		
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
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectDept&selectedDeptsID=" + $("#receive_dept_1_ids").val() + "&selectDepts=" + getInputContent("receive_dept_1_names"), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			$("#receive_dept_1_names").val(names);
			$("#receive_dept_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
})

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}

function selectUser(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedUsersID=" + $("#audit_user_id").val() + "&selectedUsers=" + getInputContent("audit_user_name"), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
	if (returnValue != null) {
		var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
		ids = ids.substring(0, ids.length - 1);
		names = names.substring(0, names.length - 1);
		$("#audit_user_name", $p).val(names);
		$("#audit_user_id", $p).val(ids);
	}
}

function forwardOtherPeople(){
	$("#is_forward_0").attr("disabled", "disabled");
	$("#is_forward_1").attr("checked", true);
	setForwardDivShow();
}
function notForwardOtherPeople(){
	$("#is_forward_1").attr("disabled", "disabled");
	$("#is_forward_0").attr("checked", true);
	setForwardDivHide();	
}
function setForwardDivShow(){
	$("#is_forward_div").show();
	$("#audit_user_name").attr("dataType", "Require").attr("msg", "请选择审批人");
}
function setForwardDivHide(){
	$("#is_forward_div").hide();
	$("#audit_user_name").removeAttr("dataType");
}

function getInputContent(id){
    var v = $("#" + id).val();
    if(v.length != 0) {
        return v + ",";
    }
    
    return v;
}

//计算总金额
function sum_money(){
    var total = 0; //总金额
    $("[id^=app_amount_]").each(function(){
		total+=parseFloat($(this).val()) * parseFloat($('#' + ($(this).attr("id")).replace("app_amount_","app_money_")).val());
	});
	
    $('#column_6').val(total.toFixed(2));
}

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>