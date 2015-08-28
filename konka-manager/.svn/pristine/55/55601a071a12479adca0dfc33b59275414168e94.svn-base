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
      <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaProSqAudit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
            <html-el:text property="file_no_like" styleId="file_no_like" size="20" maxlength="20" />
            &nbsp;&nbsp; <strong class="fb">项目名称：</strong>
            <html-el:text property="pro_name_like" styleId="pro_name_like" size="20" maxlength="20" />
            &nbsp;&nbsp;<strong class="fb">项目状态：</strong>
            <html-el:select property="pro_state" styleId="pro_state">
            	<html-el:option value="">--请选择--</html-el:option>
            	<html-el:option value="0">报备中</html-el:option>
            	<html-el:option value="1">跟踪中</html-el:option>
            	<html-el:option value="2">已完结</html-el:option>
            	<html-el:option value="3">已取消</html-el:option>
            </html-el:select>
            </td></tr>
            <tr><td>
            &nbsp;&nbsp;&nbsp;<strong class="fb">报备时间：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<strong class="fb">分公司：</strong>
        		<html-el:select property="dept_id" styleId="dept_id" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;
        		<input class="but1" type="submit" name="Submit" value="搜索" />
        	</td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    
    <form id="listForm" name="listForm" method="post" action="KonkaProSqAudit.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="15%" nowrap="nowrap">文件编号</td>
          <td nowrap="nowrap">项目名称</td>
          <td width="10%" nowrap="nowrap" align="center">投标人名称</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">报备人</td>
           <td width="10%" nowrap="nowrap" align="center">报备时间</td>
           <td width="10%" nowrap="nowrap" align="center">项目状态</td>
           <td width="10%" nowrap="nowrap" align="center">审核状态</td>
          <td width="15%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" valign="top">
                <c:if test="${cur.pro_state eq 0}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" /></c:if>
                <c:if test="${cur.pro_state ne 0}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" disabled="disabled" /></c:if>
              </td>
              <td align="left" nowrap="nowrap" ><span style="cursor:pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaProSqAudit.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.file_no}</span></td>
              <td align="left" nowrap="nowrap" ><span style="cursor:pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaProSqAudit.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${fn:escapeXml(cur.pro_name)}</span></td>
              <td align="left" nowrap="nowrap">${cur.tbr_name}</td>
              <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
              <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
              <td align="center" nowrap="nowrap"  ><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.pro_state eq 0}">报备中</c:if>
              <c:if test="${cur.pro_state eq 1}">跟踪中</c:if>
              <c:if test="${cur.pro_state eq 2}">已完结</c:if>
              <c:if test="${cur.pro_state eq 3}">已取消</c:if>
             </td>
             <td align="center" nowrap="nowrap">
              <c:if test="${cur.file_state eq 0}">保存</c:if>
              <c:if test="${cur.file_state eq 1}">提交</c:if>
              <c:if test="${cur.file_state eq 2}">审核中</c:if>
              <c:if test="${cur.file_state eq 3}">审核通过</c:if>
              <c:if test="${cur.file_state eq 4}">驳回</c:if>
              <c:if test="${cur.file_state eq 5}">驳回到申请人</c:if>
             </td>
              <td align="center" valign="top">
              
              <c:if test="${(cur.file_state eq 1 || cur.file_state eq 2|| cur.file_state eq 4) && cur.pro_state eq 0}">
              <span style="cursor: pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaProSqAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">审核</span>
              </c:if>
              <c:if test="${cur.file_state eq 3 || cur.file_state eq 5 || cur.pro_state ne 0}">
              <span style="color:#ccc;">审核</span>
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
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaProSqAudit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("file_no_like", "${fn:escapeXml(af.map.file_no_like)}");
			pager.addHiddenInputs("pro_name_like", "${fn:escapeXml(af.map.pro_name_like)}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
			pager.addHiddenInputs("pro_state", "${af.map.pro_state}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="工程机项目申报明细">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td width="15%" nowrap="nowrap">文件编号</td>
        <td nowrap="nowrap">项目名称</td>
        <td width="10%" nowrap="nowrap" align="center">投标人名称</td>
        <td width="10%" nowrap="nowrap" align="center">分公司</td>
        <td width="10%" nowrap="nowrap" align="center">报备人</td>
        <td width="10%" nowrap="nowrap" align="center">报备时间</td>
        <td width="10%" nowrap="nowrap" align="center">项目状态</td>
        <td width="10%" nowrap="nowrap" align="center">审核状态</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap" >${cur.file_no}</td>
          <td align="left" nowrap="nowrap" >${fn:escapeXml(cur.pro_name)}</td>
          <td align="left" nowrap="nowrap">${cur.tbr_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.fzr_name}</td>
          <td align="center" nowrap="nowrap" valign="top" ><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
          <td align="center" nowrap="nowrap">
          <c:if test="${cur.pro_state eq 0}">报备中</c:if>
          <c:if test="${cur.pro_state eq 1}">跟踪中</c:if>
          <c:if test="${cur.pro_state eq 2}">已完结</c:if>
          <c:if test="${cur.pro_state eq 3}">已取消</c:if>
         </td>
         <td align="center" nowrap="nowrap">
              <c:if test="${cur.file_state eq 0}">保存</c:if>
              <c:if test="${cur.file_state eq 1}">提交</c:if>
              <c:if test="${cur.file_state eq 2}">审核中</c:if>
              <c:if test="${cur.file_state eq 3}">审核通过</c:if>
              <c:if test="${cur.file_state eq 4}">驳回</c:if>
              <c:if test="${cur.file_state eq 5}">驳回到申请人</c:if>
             </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
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


		// 导出excel
	    $("#export_excel").click(function(){
	    	if(confirm("提示，您确认导出数据？")){
	    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
	    		$("#bottomPageForm").submit();
	    	}
	    });

	    var excel_all = "${af.map.excel_all}";
		if("1" == excel_all){
			toExcel('divExcel_all', '${ctx}/manager/admin/KonkaProSq.do?method=toExcel');
		}
		
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
