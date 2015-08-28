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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
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
    <html-el:form action="/oa/ExpenseQuery">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="50%" nowrap="nowrap">
             &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
             <html-el:text property="file_no" styleId="file_no" size="20" maxlength="20" />
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name" styleId="customer_name" size="20"  maxlength="40"/>
              &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">R3编码：</strong>
            <html-el:text property="r3_code" styleId="r3_code" size="20"  maxlength="16"/>
   			<br />
   		   &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件标题：</strong>
            <html-el:text property="file_title_like" styleId="file_title_like" size="20" maxlength="20" />
          
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">申请时间：</strong>
          <html-el:text property="st_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          至
          <html-el:text property="en_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
  			&nbsp;&nbsp;&nbsp;&nbsp;
            <strong class="fb">文件状态：</strong>
          <html-el:select property="map_file_status">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">未提交</html-el:option>
            <html-el:option value="1">审批中</html-el:option>
            <html-el:option value="2">已审批</html-el:option>
          </html-el:select>
          <br/>
           &nbsp;&nbsp;&nbsp;&nbsp;<jsp:include page="../_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
           &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<input type="submit"  id="btn_submit"  value="搜 索"  class="but1">
          </td>
        </tr>
      </table>
      <div><input type="button" onclick="confirmDispatchAll(this.form);" value="导出" class="but_excel"></input>
      	<input type="hidden" name="outexcl" id="outexcl" value="0"/>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
  <div style="width:100%;overflow-x:scroll;">
    <form id="listForm" name="listForm" method="post" action="ExpenseQuery.do?">
      <input type="hidden" name="method" id="method" value="download" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="10%"nowrap="nowrap" align="center">申请部门</td>
          <td width="8%" nowrap="nowrap">文件编号</td>
          <td nowrap="nowrap">文件标题</td>
          <td width="12%" nowrap="nowrap" align="center">客户名称</td>
          <td width="8%" nowrap="nowrap" align="center" title="R3编码">R3编码</td>
          <td width="8%" nowrap="nowrap" align="center">费用总额</td>
          
          <td width="8%"nowrap="nowrap" align="center">申请人</td>
          <td width="10%" nowrap="nowrap" align="center">申请时间</td>
          <td width="8%" nowrap="nowrap" align="center">审批时间</td>
          <td width="8%" nowrap="nowrap" align="center">当前审批人</td>
          <td width="6%" nowrap="nowrap" align="center">文件状态</td>
	 	  <td width="150px" nowrap="nowrap" align="center">最近一次审批时间</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${cur.submit_dept}</td>
              <td align="left"  nowrap="nowrap">${fn:escapeXml(cur.file_no)}</td>
              <td align="left"  nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">${fn:escapeXml(cur.file_title)}</span></td>
              <td nowrap="nowrap" align="left" title="${cur.map.r3_shop_name}">${fn:substring(cur.map.r3_shop_name,0,12)}<c:if test="${empty cur.map.r3_shop_name}">无</c:if></td>
              <td nowrap="nowrap" align="left">${cur.map.r3_code}<c:if test="${empty cur.map.r3_code}">无</c:if></td>
              <td nowrap="nowrap" align="right"><fmt:formatNumber pattern="0.00" value="${cur.map.column_6}" /></td>
              <td nowrap="nowrap" align="center">${cur.submit_user}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td nowrap="nowrap" align="center">${cur.map.archive_datetime}</td>
              <td align="center">${fn:escapeXml(cur.map.cur_audit_user_name)}</td>
              <td align="center" nowrap="nowrap">
              <c:choose>
                  <c:when test="${cur.file_status eq 0}"><span style="color:#000;font-weight:bold;">未提交</span></c:when>
                  <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                  <c:when test="${cur.file_status eq 2}"><span style="color:#f00;font-weight:bold;">已审批</span></c:when>
                </c:choose>
              </td>
              
              <c:if test="${cur.map.last_audit_date_time ne null}">
              	<td><fmt:formatDate value="${cur.map.last_audit_date_time}" pattern="yyyy-MM-dd HH:mm" /></td>
              </c:if>
              <c:if test="${cur.map.last_audit_date_time eq null or cur.map.last_audit_date_time eq ''}">
              	<td></td>
              </c:if>
              
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
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ExpenseQuery.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("file_title_like", "${fn:escapeXml(af.map.file_title_like)}");
					pager.addHiddenInputs("submit_user_id", "${fn:escapeXml(af.map.submit_user_id)}");					
					pager.addHiddenInputs("st_submit_datetime", "${af.map.st_submit_datetime}");
					pager.addHiddenInputs("en_submit_datetime", "${af.map.en_submit_datetime}");
					pager.addHiddenInputs("submit_dept_id", "${af.map.submit_dept_id}");	
					pager.addHiddenInputs("r3_shop_id", "${af.map.r3_shop_id}");	
					pager.addHiddenInputs("map_file_status", "${af.map.map_file_status}");
					pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
			        pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
			        pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
			        pager.addHiddenInputs("ywy_user_id", "${af.map.ywy_user_id}");
			        pager.addHiddenInputs("customer_name", "${af.map.customer_name}");
			        pager.addHiddenInputs("file_no", "${af.map.file_no}");
			        pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#r3_shop_id option[value='${af.map.r3_shop_id}']").attr("selected",true);
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
				alert("提交时间结束日期不得大于开始日期,请重新选择!");
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
    window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=openSms&id=" +id, window, "dialogWidth:450px;status:no;dialogHeight:140px");
}

function view_and_print(id) {
	 window.open("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id);

}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
