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
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
     <!-- <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>  --> 
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <html-el:form action="/oa/FilesSubmit">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="100%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件标题：</strong>
          <html-el:text property="file_title_like" size="20" maxlength="20" styleId="file_title_like" />
           &nbsp;&nbsp;<strong class="fb">申请时间：</strong>
          <html-el:text property="st_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          至
          <html-el:text property="en_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
       <br /> <br />
       
          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
          <html-el:text property="file_no" size="20" maxlength="20" styleId="file_no" />
         &nbsp;&nbsp;<strong class="fb">文件状态：</strong>
          <html-el:select property="map_file_status">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">未审批</html-el:option>
            <html-el:option value="1">审批中</html-el:option>
            <html-el:option value="2">已审批</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" id="btn_submit" value="搜索" />
        </td>
      </tr>
    </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="align:left" name="button" type="button"  value="新增" class="but2"  onclick="location.href='FilesSubmit.do?method=add&mod_id=${af.map.mod_id}';" />
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="FilesSubmit.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="30" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="80">文件编号</td>
          <td nowrap="nowrap">文件标题</td>
          <td width="80" nowrap="nowrap" align="center">申请人</td>
          <td width="120" nowrap="nowrap" align="center">申请时间</td>
          <td width="80" nowrap="nowrap" align="center">文件状态</td>
          <td width="100" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="center" nowrap="nowrap" valign="top" >
                <c:if test="${cur.file_status eq 0}">
                  <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
                </c:if>
                <c:if test="${cur.file_status eq 1 or cur.file_status eq 2}">
                  <input name="xxxxxx" type="checkbox" disabled="disabled" />
                </c:if></td>
              <td>${cur.file_no}</td>
              <td align="left" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">
              	<span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">${fn:escapeXml(cur.file_title)}</span>
              	<!-- 业务通,请假申请? -->
              	<c:if test="${cur.map.link_id_count lt 1 && cur.file_status eq 2 }">
              		<c:if test="${cur.is_node eq 1 && not empty p_audit_node_id}">
	                	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'FilesSubmit.do','resubmit' ,'mod_id=${af.map.mod_id}&id=${cur.id}&p_audit_node_id=${p_audit_node_id }&' + $('#bottomPageForm').serialize())" title="复制文件内容到新文件中，重新提交被驳回的申请">【重新提交】</span>
	                </c:if>
              	</c:if>
              	<c:if test="${cur.map.link_id_count gt 0 && cur.file_status eq 2 }">
              		<c:if test="${cur.is_node eq 1 && not empty p_audit_node_id}">
              			<font style="color: grey;">【已重新提交】</font>
	                </c:if>
              	</c:if>
              </td>
              <td nowrap="nowrap" align="center">${cur.submit_user}</td>
              <td align="center" nowrap="nowrap" valign="top" ><fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              
              <td align="center" nowrap="nowrap" valign="top" ><c:choose>
                  <c:when test="${cur.file_status eq 0}"><span style="color:#000;font-weight:bold;">未提交</span></c:when>
                  <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                  <c:when test="${cur.file_status eq 2}"><span style="color:#00CC66;font-weight:bold;">已审批</span></c:when>
                </c:choose>
              </td>

				<td align="center" nowrap="nowrap" valign="top">
					<!-- 没有流程的复制 --> 
					<c:if test="${cur.is_node eq 1}">
						<span style="cursor: pointer;" class="fblue"
							onclick="doNeedMethod(null, 'FilesSubmit.do','copy' ,'id=${cur.id}&p_audit_node_id=${p_audit_node_id }&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"
							title="复制文件内容到新文件中">复制</span>
					</c:if> 
					
					<!-- 没有流程的复制 --> 
					<c:if test="${cur.is_node eq 0}">
						<span style="cursor: pointer;" class="fblue" onclick="location.href='${ctx}/manager/oa/FilesSubmit.do?method=copy&id=${cur.id}&mod_id=${af.map.mod_id}';"
							title="复制文件内容到新文件中">复制</span>
					</c:if> 
					
					<!-- edit--> 
					<c:if test="${cur.file_status eq 0}">
						<span style="cursor: pointer;"
							onclick="confirmUpdate(null, 'FilesSubmit.do', 'id=${cur.id}&p_audit_node_id=${p_audit_node_id }&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">|修改</span>
					</c:if>
					<c:if test="${cur.file_status ne 0}">
						<span style="color:gray">|修改</span>
					</c:if>
					 
					<!-- delete -->
					<c:if test="${cur.file_status eq 0}">
						<span style="cursor: pointer;"
							onclick="confirmDelete(null, 'FilesSubmit.do', 'id=${cur.id}&p_audit_node_id=${p_audit_node_id }&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">|删除</span>
					</c:if> 
					<c:if test="${cur.file_status ne 0}">
						<span style="color:gray">|删除</span>
					</c:if>
					
					<!-- 撤回 --> 
					<c:if test="${cur.is_node eq 1 && not empty p_audit_node_id}">
						<c:if test="${cur.file_status le 1}">
							<span style="cursor: pointer;" class="fblue"
								onclick="doNeedMethod('确认撤回吗？', 'FilesSubmit.do','cancel' ,'id=${cur.id}&p_audit_node_id=${p_audit_node_id }&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"
								title="撤回">撤回</span>
						</c:if>
						<c:if test="${cur.file_status gt 1}">
							<span style="color: grey;">撤回</span>
						</c:if>
					</c:if>
				</td>
			</tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="FilesSubmit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("file_title_like", "${fn:escapeXml(af.map.file_title_like)}");
					pager.addHiddenInputs("map_file_status", "${af.map.map_file_status}");
					pager.addHiddenInputs("st_submit_datetime", "${af.map.st_submit_datetime}");
					pager.addHiddenInputs("en_submit_datetime", "${af.map.en_submit_datetime}");		
					pager.addHiddenInputs("c_index_11", "${af.map.c_index_11}");	
					pager.addHiddenInputs("c_index_12", "${af.map.c_index_12}");								
					pager.addHiddenInputs("file_no", "${af.map.file_no}");								
		            document.write(pager.toString());
		            </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
	 $(document).ready(function() {
		$("#span_help").click(function(){
		$("#cvtooltipClose").cvtooltip({
			panel: "#body_oarcont",
			  direction: 1,                
			    width: 420,
			     left: 320,
			     top: 5,
			     speed: 500,
			    delay: 10000
			    });
			});	   

	var f = document.getElementById('af');
	$("#btn_submit").click(function(){
		if (f.st_submit_datetime.value != "" && f.en_submit_datetime.value != "") {
			if (f.en_submit_datetime.value < f.st_submit_datetime.value) {
				alert("申请时间结束日期不得大于开始日期,请重新选择!");
				return false;
			}
		}
		f.submit();
	});
});

function view_and_print(id) {
   // window.showModalDialog("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
	 window.open("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id);

}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
