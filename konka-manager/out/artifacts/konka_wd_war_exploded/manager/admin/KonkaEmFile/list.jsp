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
    <html-el:form action="/admin/KonkaEmFile">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
            <html-el:text property="file_no_like" styleId="file_no_like" size="20" maxlength="20" />
            &nbsp;&nbsp; <strong class="fb">文件标题：</strong>
            <html-el:text property="file_title_like" styleId="file_title_like" size="20" maxlength="20" />
          </td>
        </tr>
        <tr>
        	<td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件类型：</strong>
        		<html-el:select property="file_type" styleId="file_type">
        			<html-el:option value="">全部</html-el:option>
        			<html-el:option value="10">3C证书</html-el:option>
          			<html-el:option value="20">获奖证书</html-el:option>
	          		<html-el:option value="30">检测报告</html-el:option>
	          		<html-el:option value="40">产品参数</html-el:option>
	          		<html-el:option value="50">库存</html-el:option>
	          		<html-el:option value="60">生产计划</html-el:option>
	          		<html-el:option value="80">节能证书</html-el:option>
	          		<html-el:option value="90">表格模版</html-el:option>
	          		<html-el:option value="100">软件升级资料</html-el:option>
	          		<html-el:option value="70">其他</html-el:option>
        		</html-el:select>
        		&nbsp;&nbsp; <strong class="fb">下发时间：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
        	</td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaEmFile.do?method=add&mod_id=${af.map.mod_id}';" />
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaEmFile.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="100" nowrap="nowrap">文件编号</td>
          <td nowrap="nowrap">文件标题</td>
          <td width="80" nowrap="nowrap" align="center">文件类型</td>
          <td width="120" nowrap="nowrap" align="center">下发时间</td>
          <td width="80" nowrap="nowrap" align="center">发件人</td>
          <td width="100" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" valign="top">
                <c:if test="${cur.is_del le 0}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" /></c:if>
                <c:if test="${cur.is_del gt 0}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" disabled="disabled" /></c:if>
              </td>
              <td align="left" valign="top" ><span style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaEmFile.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.file_no}</span></td>
              <td align="left" valign="top" ><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaEmFile.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${fn:escapeXml(cur.file_title)}</span></td>
              <td align="center" valign="top"><c:choose>
                  <c:when test="${cur.file_type eq 10}">3C证书</c:when>
                  <c:when test="${cur.file_type eq 20}">获奖证书</c:when>
                  <c:when test="${cur.file_type eq 30}">检测报告</c:when>
                  <c:when test="${cur.file_type eq 40}">产品参数</c:when>
                  <c:when test="${cur.file_type eq 50}">库存</c:when>
                  <c:when test="${cur.file_type eq 60}">生产计划</c:when>
                  <c:when test="${cur.file_type eq 80}">节能证书</c:when>
                  <c:when test="${cur.file_type eq 90}">表格模版</c:when>
                  <c:when test="${cur.file_type eq 100}">软件升级资料</c:when>
                  <c:when test="${cur.file_type eq 70}">其他</c:when>
                </c:choose>
             </td>
              <td align="center" nowrap="nowrap" valign="top" ><fmt:formatDate value="${cur.add_time}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" valign="top">${fn:escapeXml(cur.add_user_name)}</td>
              <td align="center" valign="top">
                <span style="cursor: pointer;" onclick="confirmUpdate(null, 'KonkaEmFile.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
                |
                <span style="cursor: pointer;" onclick="confirmDelete(null, 'KonkaEmFile.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaEmFile.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("file_no_like", "${fn:escapeXml(af.map.file_no_like)}");
			pager.addHiddenInputs("file_title_like", "${fn:escapeXml(af.map.file_title_like)}");
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("file_type", "${af.map.file_type}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
    <div id="divExcel" title="文件列表" style="display:none;"></div>
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
		    var div_list = "";
		    div_list += "<table width='100%' border='0' cellspacing='0' cellpadding='0' class='rtable2'>";
		    div_list += "<tr class='tabtt1'>";
		    div_list += "<td width='5%' align='center'>序号</td>";
		    div_list += "<td width='20%' nowrap='nowrap'>文件编号</td>";
		    div_list += "<td nowrap='nowrap'>文件标题</td>";
		    div_list += "<td width='10%' nowrap='nowrap' align='center'>文件类型</td>";
		    div_list += "<td width='20%' nowrap='nowrap' align='center'>下发时间</td>";
		    div_list += "<td width='10%' nowrap='nowrap' align='center'>发件人</td>";
		    div_list += "</tr>";
		    <c:forEach var="cur" items="${excelList}" varStatus="vs">
		    	div_list += "<tr>";
		    	div_list += "<td align='center' valign='top'>${vs.count}</td>";
		    	div_list += "<td align='left' valign='top' >${cur.file_no}</td>";
		    	div_list += "<td align='left' valign='top' >${fn:escapeXml(cur.file_title)}</td>";
		    	var file_type_name = "";
		    	<c:if test="${cur.file_type eq 10}">
		    		file_type_name = "3C证书";
		    	</c:if>
		    	<c:if test="${cur.file_type eq 20}">
	    			file_type_name = "获奖证书";
	    		</c:if>
	    		<c:if test="${cur.file_type eq 30}">
    				file_type_name = "检测报告";
    			</c:if>
    			<c:if test="${cur.file_type eq 40}">
					file_type_name = "产品参数";
				</c:if>
				<c:if test="${cur.file_type eq 50}">
					file_type_name = "库存";
				</c:if>
				<c:if test="${cur.file_type eq 60}">
					file_type_name = "生产计划";
				</c:if>
				<c:if test="${cur.file_type eq 80}">
					file_type_name = "节能证书";
			    </c:if>
			    <c:if test="${cur.file_type eq 90}">
					file_type_name = "表格模版";
			    </c:if>
			    <c:if test="${cur.file_type eq 100}">
					file_type_name = "软件升级资料";
			    </c:if>
				<c:if test="${cur.file_type eq 70}">
					file_type_name = "其他";
				</c:if>
				div_list += "<td align='center' valign='top'>" + file_type_name + "</td>";
		    	div_list += "<td align='center' nowrap='nowrap' valign='top' ><fmt:formatDate value='${cur.add_time}' pattern='yyyy-MM-dd HH:mm' /></td>";
		    	div_list += "<td align='center' valign='top'>${fn:escapeXml(cur.add_user_name)}</td>";
		    	div_list += "</tr>";
		    </c:forEach>
		    div_list += "</table>";
		    $("#divExcel").empty();
		    $("#divExcel").append(div_list);
	    	toExcel('divExcel', '${ctx}/manager/admin/KonkaEmFile.do?method=toExcel');
	    }); 
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
