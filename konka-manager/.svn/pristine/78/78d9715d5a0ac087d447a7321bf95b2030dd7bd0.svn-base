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
    <html-el:form action="/admin/KonkaActInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;
          <strong class="fb">分公司：</strong>
        		<html-el:select property="dept_sn" styleId="dept_sn" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select> &nbsp;&nbsp;<strong class="fb">客户名称：</strong>
            <html-el:text property="c_name_like" styleId="c_name_like" size="20" maxlength="20" />
            &nbsp;&nbsp; <strong class="fb">R3编码：</strong>
            <html-el:text property="r3_code_like" styleId="r3_code_like" size="20" maxlength="20" />
            &nbsp;&nbsp;
            <strong class="fb">上报人：</strong>
            <html-el:text property="add_user_name_like" styleId="add_user_name_like" size="20" maxlength="20" />
            &nbsp;&nbsp;</td></tr>
            <tr><td nowrap="nowrap">&nbsp;&nbsp;
            <strong class="fb">活动类型：</strong>
            <html-el:select property="type_id" styleId="type_id">
            	<html-el:option value="">--请选择--</html-el:option>
            	<html-el:option value="30010">县级</html-el:option>
            	<html-el:option value="30020">乡镇联合</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;<strong class="fb">活动时间：</strong>
        		<html-el:text property="start_time_start" styleId="start_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="start_time_end" styleId="start_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<strong class="fb">活动状态：</strong>
            <html-el:select property="status" styleId="status">
            	<html-el:option value="">--请选择--</html-el:option>
            	<html-el:option value="0">有效</html-el:option>
            	<html-el:option value="1">无效</html-el:option>
            </html-el:select>&nbsp;&nbsp;
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
          <c:if test="${fn:contains(popedom, '+1+')}">
          <input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaActInfo.do?method=add&mod_id=${af.map.mod_id}';" />
          </c:if>
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x:scroll;">
    <form id="listForm" name="listForm" method="post" action="KonkaActInfo.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="8%" nowrap="nowrap" align="center">活动开始时间</td>
          <td width="8%" nowrap="nowrap" align="center">活动结束时间</td>
           <td width="5%" nowrap="nowrap" align="center">活动编码</td>
          <td width="5%" nowrap="nowrap" align="center">分公司</td>
          <td width="8%" nowrap="nowrap" align="center">客户名称</td>
          <td width="5%" nowrap="nowrap" align="center">R3编码</td>
          <td width="5%" nowrap="nowrap" align="center">活动类型</td>
          <td width="5%" nowrap="nowrap" align="center">活动目标（万）</td>
          <td width="8%" nowrap="nowrap" align="center">销量达成（万）</td>
          <td width="8%" nowrap="nowrap" align="center">活动完成率</td>
          <td width="5%" nowrap="nowrap" align="center">上报人</td>
          <td width="8%" nowrap="nowrap" align="center">活动照片</td>
          <td width="8%" nowrap="nowrap" align="center">活动状态</td>
          <td width="8%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" >
                <input name="pks" type="checkbox" id="pks_${cur.act_id}" value="${cur.act_id}" />
              </td>
              <td align="left" nowrap="nowrap" ><fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd" /></td>
              <td align="left" nowrap="nowrap" ><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd" /></td>
              <td align="left" nowrap="nowrap"><span style="cursor:pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaActInfo.do', 'view', 'act_id=${cur.act_id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.act_sn}</span></td>
              <td align="left" nowrap="nowrap">${cur.dept_name}</td>
              <td align="left" nowrap="nowrap">${cur.c_name}</td>
              <td align="left" nowrap="nowrap">${cur.r3_code}</td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.type_id eq 30010}">县级</c:if>
              <c:if test="${cur.type_id eq 30020}">乡镇联合</c:if>
              </td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber value="${cur.target_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber value="${cur.done_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap">
              <c:if test="${not empty cur.target_money }">
              <fmt:formatNumber value="${cur.target_money ne 0 ? (cur.done_money / cur.target_money)*100 : 0 }" />
              </c:if>
              <c:if test="${empty cur.target_money }">
              0
              </c:if>
              %</td>
              <td align="center" nowrap="nowrap">${cur.add_user_name}</td>
              <td align="center" nowrap="nowrap">
              <c:forEach var="cur1" items="${cur.map.attachmentList}" varStatus="vs">
                  <li style="list-style: none;"><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur1.save_name}" target="_blank">${cur1.file_name}</a>&nbsp;&nbsp;</li>
                </c:forEach></td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.status eq 0}">有效</c:if>
              <c:if test="${cur.status eq 1}">无效</c:if>
              </td>
              <td align="center" nowrap="nowrap">
                <c:choose>
		              <c:when test="${fn:contains(popedom, '+8+')}">
		             <span style="cursor: pointer;color: blue;" onclick="doNeedMethod(null, 'KonkaActInfo.do', 'valid','act_id=${cur.act_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">有效</span>|
		              </c:when>
		              <c:otherwise>
		              <span style="color:#ccc;">有效</span>|
		              </c:otherwise>
			    </c:choose>
			    <c:choose>
		              <c:when test="${fn:contains(popedom, '+16+')}">
		             <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('是否设置为无效！', 'KonkaActInfo.do', 'invalid','act_id=${cur.act_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">无效</span>|
		              </c:when>
		              <c:otherwise>
		              <span style="color:#ccc;">无效</span>|
		              </c:otherwise>
			    </c:choose>
                <span style="cursor: pointer;color: blue;" onclick="confirmUpdate(null, 'KonkaActInfo.do', 'act_id=${cur.act_id}&' + $('#bottomPageForm').serialize())">修改</span>
                |
                <span style="cursor: pointer;color: blue;" onclick="confirmDelete(null, 'KonkaActInfo.do', 'act_id=${cur.act_id}&' + $('#bottomPageForm').serialize())">删除</span>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaActInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("c_name_like", "${fn:escapeXml(af.map.c_name_like)}");
			pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
			pager.addHiddenInputs("status", "${af.map.status}");
			pager.addHiddenInputs("dept_sn", "${af.map.dept_sn}");
			pager.addHiddenInputs("add_user_name_like", "${af.map.add_user_name_like}");
			pager.addHiddenInputs("start_time_end", "${af.map.start_time_end}");
			pager.addHiddenInputs("start_time_start", "${af.map.start_time_start}");
			pager.addHiddenInputs("type_id", "${af.map.type_id}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="促销上报明细">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="8%" nowrap="nowrap" align="center">客户姓名</td>
          <td width="8%" nowrap="nowrap" align="center">客户电话</td>
          <td width="5%" nowrap="nowrap" align="center">活动目标（万）</td>
          <td width="8%" nowrap="nowrap" align="center">销量达成（万）</td>
          <td width="8%" nowrap="nowrap" align="center">活动完成率</td>
           <td width="5%" nowrap="nowrap" align="center">零售机型</td>
          <td width="5%" nowrap="nowrap" align="center">数量</td>
          <td width="8%" nowrap="nowrap" align="center">单价</td>
          <td width="5%" nowrap="nowrap" align="center">总金额</td>
          <td width="5%" nowrap="nowrap" align="center">上报人</td>
          <td width="5%" nowrap="nowrap" align="center">提交时间</td>
          <td width="8%" nowrap="nowrap" align="center">活动状态</td>
        </tr>    
        <c:forEach var="cur1" items="${allList}" varStatus="vs">
            <tr>
              <td align="left" nowrap="nowrap">${cur1.c_name}</td>
              <td align="left" nowrap="nowrap">${cur1.c_link_name}</td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber value="${cur1.target_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber value="${cur1.done_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur1.target_money ne 0 ? (cur1.done_money / cur1.target_money)*100 : 0 }" />%</td>
              <td align="left" nowrap="nowrap" >${cur1.map.model_name}</td>
              <td align="left" nowrap="nowrap">${cur1.map.num}</td>
              <td align="left" nowrap="nowrap">${cur1.map.single_price}</td>
              <td align="left" nowrap="nowrap">${cur1.map.all_price}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.add_date}" pattern="yyyy-MM-dd"/></td>
              <td align="center" nowrap="nowrap">${cur1.add_user_name}</td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur1.status eq 0}">有效</c:if>
              <c:if test="${cur1.status eq 1}">无效</c:if>
              </td>
            </tr>
          </c:forEach>
        </tbody>
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
			toExcel('divExcel_all', '${ctx}/manager/admin/KonkaActInfo.do?method=toExcel');
		}

		
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
