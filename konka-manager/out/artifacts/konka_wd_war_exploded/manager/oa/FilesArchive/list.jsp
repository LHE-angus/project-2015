<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/oa/FilesArchive.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        	<td style="padding-left:20px" colspan="6"><jsp:include page="../_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" /></td>
        </tr>
        <tr>
          <td style="padding-left:20px" width="80px"><strong class="fb">文件编号：</strong></td>
          <td>
            <html-el:text property="file_code" size="20" maxlength="20" styleId="file_code" />
          </td>
          <td width="80px"><strong class="fb">文件主题：</strong></td>
          <td>
            <html-el:text property="title" styleId="title" size="20" maxlength="50" />
          </td>
          <td width="80px"><strong class="fb">申请人：</strong></td>
          <td><html-el:text property="apply_man" styleId="apply_man" size="20" maxlength="50" /></td>
        </tr>
        <tr>
          <td style="padding-left:20px"><strong class="fb">事务类型：</strong></td>
          <td>
            <html-el:select property="mod_type">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="notice">公告通知</html-el:option>
              <html-el:option value="file">下发文件</html-el:option>
              <html-el:option value="expense">费用申请</html-el:option>
            </html-el:select>
          </td>
          <td><strong class="fb">发文时间：</strong></td>
          <td>
            <html-el:text property="add_starttime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="add_endtime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          </td>
          <td colspan="2"><input class="but1" type="submit" id="btn_submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <c:if test="${not empty canDelete}">
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
        </td>
      </tr>
    </table>
  </div>
  </c:if>
  <div class="rtabcont1">
  <form id="listForm" name="listForm" method="post" action="FilesArchive.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="30" nowrap="nowrap" align="center"><c:if test="${not empty canDelete}"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></c:if>
        <c:if test="${empty canDelete}">序号</c:if>
        </td>
        <td width="60" nowrap="nowrap" align="center">事务类型</td>
        <td width="70" nowrap="nowrap">文件编号</td>
        <td nowrap="nowrap">文件主题</td>
        <td nowrap="nowrap">申请人/部门</td>
        <td width="100" nowrap="nowrap" align="center">发文时间</td>
      </tr>
      <tbody>
        <c:forEach var='cur' items='${entityList}' varStatus="vs">
          <c:set var="ids" value="${ids},${cur.id }" />
            <tr valign="top" id="tr_${cur.id}">
            <td valign="top" align="center" nowrap="nowrap"><c:if test="${not empty canDelete}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}_${cur.mod_type}" /></c:if>
            <c:if test="${empty canDelete}">${vs.count}</c:if>
            </td>
            <td valign="top" align="center" >${cur.mod_name}</td>
            <td valign="top" align="left" >${cur.file_code}</td>   
            <td valign="top" align="left" title="${cur.title}">
            <c:choose>
                <c:when test="${cur.mod_type eq 'notice' }"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'DocIssue.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${fn:substring(cur.title,0,40)}...</span></c:when>
                <c:when test="${cur.mod_type eq 'file' or  cur.mod_type eq 'expense'}"><span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">${fn:substring(cur.title,0,40)}...</span></c:when>
                <c:otherwise></c:otherwise>
              </c:choose></td>
            <td valign="top" align="left">${cur.pass_man_name}/${cur.part_name}</td>
            <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
          <c:set var="ids" value="${ids},${cur.id }" />
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="FilesArchive.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", '${af.map.mod_id}');
				pager.addHiddenInputs("file_code", '${af.map.file_code}');
				pager.addHiddenInputs("title", '${af.map.title}');
				pager.addHiddenInputs("apply_man", '${af.map.apply_man}');
				pager.addHiddenInputs("mod_type", '${af.map.mod_type}');
				pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
		        pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
		        pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
		        pager.addHiddenInputs("ywy_user_id", "${af.map.ywy_user_id}");
		        pager.addHiddenInputs("add_starttime", "${af.map.add_starttime}");
				pager.addHiddenInputs("add_endtime", "${af.map.add_endtime}");
				document.write(pager.toString());
            	 </script>
            </td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
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
		var date_begin = $("#add_starttime").val();
		var date_end = $("#add_endtime").val();
		if (date_begin != "" && date_end != "") {
			if (date_begin < date_end) {
				alert("发文时间结束日期不得大于开始日期,请重新选择!");
				return false;
			}
		}
		f.submit();
	});	   
});  
  
function view_and_print(id) {
	window.open("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id,'_blank','top=0,toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no');
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
