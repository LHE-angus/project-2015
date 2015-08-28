<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv = "X-UA-Compatible" cotent = "IE=edge,chrome=1"/>
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
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
    <html-el:form action="/oa/AuditIngFiles">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="50%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件标题：</strong>
            <html-el:text property="file_title_like" size="20" maxlength="20" styleId="file_title_like" />
             &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
            <html-el:text property="file_no" size="20" maxlength="20" styleId="file_no" />
            &nbsp;&nbsp;<strong class="fb">申请时间：</strong>
            <html-el:text property="st_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
           	 至
            <html-el:text property="en_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            <br/>
            &nbsp;&nbsp;&nbsp;&nbsp;<jsp:include page="../_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
            &nbsp;&nbsp;<strong class="fb">文件状态：</strong>
            <html-el:select property="map_file_status" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">未提交</html-el:option>
              <html-el:option value="1">审批中</html-el:option>
              <html-el:option value="2">已审批</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="btn_submit"  id="btn_submit" value="搜索" />
          </td>
        </tr>
      </table>
      <div><input type="button" onclick="confirmDispatchAll(this.form);" value="导出" class="but_excel"></input>
      	<input type="hidden" name="outexcl" id="outexcl" value="0"/>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="AuditIngFiles.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="120px" nowrap="nowrap">部门</td>
          <td width="80px" nowrap="nowrap">文件编号</td>
          <td with="200px">文件标题</td>
          <td width="60px"nowrap="nowrap" align="center">申请人</td>
          <td width="120px" nowrap="nowrap" align="center">申请时间</td>
          <td width="80px" nowrap="nowrap" align="center">当前审批人</td>
          <td width="8%" nowrap="nowrap" align="center">审批时间</td>
          <td width="80px" nowrap="nowrap" align="center">文件状态</td>
          <td width="150px" nowrap="nowrap" align="center">最近一次审批时间</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="left">${cur.submit_dept}</td>
              <td align="left">${fn:escapeXml(cur.file_no)}</td>
              <td align="left" title="${cur.file_title}"><span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">
              <c:choose>  
              <c:when test="${fn:length(cur.file_title) > 30}">  
              <c:out value="${fn:substring(cur.file_title, 0, 30)}...." />  
              </c:when>  
              <c:otherwise>  
              <c:out value=" ${fn:escapeXml(cur.file_title)}" />  
              </c:otherwise>  
              </c:choose>  
              </span></td>
              <td nowrap="nowrap" align="center">${cur.submit_user}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center">${fn:escapeXml(cur.map.cur_audit_user_name)}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.archive_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" nowrap="nowrap">
              <c:choose>
                  <c:when test="${cur.file_status eq 0}"><span style="color:#000;font-weight:bold;">未提交</span></c:when>
                  <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                  <c:when test="${cur.file_status eq 2}"><span style="color:#00CC66;font-weight:bold;">已审批</span></c:when>
                </c:choose>
              </td>
              <c:if test="${cur.map.last_audit_date_time ne null}">
              	<td><fmt:formatDate value="${cur.map.last_audit_date_time}" pattern="yyyy-MM-dd HH:mm" /></td>
              </c:if>
              <c:if test="${cur.map.last_audit_date_time eq null}">
              	<td></td>
              </c:if>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="AuditIngFiles.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("file_title_like", "${fn:escapeXml(af.map.file_title_like)}");
					pager.addHiddenInputs("submit_user_like", "${fn:escapeXml(af.map.submit_user_like)}");					
					pager.addHiddenInputs("st_submit_datetime", "${af.map.st_submit_datetime}");
					pager.addHiddenInputs("en_submit_datetime", "${af.map.en_submit_datetime}");
					pager.addHiddenInputs("map_file_status", "${af.map.map_file_status}");
					pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
			        pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
			        pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
			        pager.addHiddenInputs("ywy_user_id", "${af.map.ywy_user_id}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
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

function confirmDispatchAll(form) {
	$("#outexcl").val("1");
	form.submit();
	$("#outexcl").val("0");
}
function openSms(id) {
    window.open("DiaLog.do?azaz=" + Math.random() + "&method=openSms&id=" +id);
}
function view_and_print(id) {
    window.open("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id);
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
