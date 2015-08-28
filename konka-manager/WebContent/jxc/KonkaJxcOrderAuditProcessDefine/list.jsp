<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderAuditProcessDefine">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
         <c:if test="${not empty is_show_dept}">
          <td width="6%" nowrap="nowrap"><strong class="fb">分公司:</strong></td>
          <td width="5%"><html-el:select property="add_dept_id" styleId="add_dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${konkaDeptList}" var="cur" varStatus="vs">
              <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</html-el:option>
           	 </c:forEach>
            </html-el:select></td>
         </c:if>
         
          <td width="6%" nowrap="nowrap"><strong class="fb">客户类型:</strong></td>
          <td width="5%"><html-el:select property="customer_type" styleId="customer_type" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${konkaCategoryList}" var="cur" varStatus="vs">
              <html-el:option value="${cur.c_index}">${fn:escapeXml(cur.c_name)}</html-el:option>
           	 </c:forEach>
            </html-el:select></td>
   
          <td width="6%" nowrap="nowrap"><strong class="fb">是否删除:</strong></td>
          <td width="8%"><html-el:select property="is_del" styleId="is_del" style="width:80px;">
            <html-el:option value="">全部</html-el:option>
            <html-el:option value="0">未删除</html-el:option>
            <html-el:option value="1">已删除</html-el:option>
          </html-el:select></td>
           <td width="6%" nowrap="nowrap"><strong class="fb">是否停用:</strong></td>
          <td width="8%"><html-el:select property="is_stop" styleId="is_stop" style="width:80px;">
            <html-el:option value="">全部</html-el:option>
            <html-el:option value="0">未停用</html-el:option>
            <html-el:option value="1">已停用</html-el:option>
          </html-el:select></td>
          
          
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcOrderAuditProcessDefine.do?method=delete">
  <input type="hidden" name="mod_id" id="mod_id" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <!--     <input type="button" name="delete" id="delete" class="bgButton" value="删除全部" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />&nbsp; -->
     	<input type="button" name="add" id="add" class="bgButtonAdd"  value="新 增 " onclick="location.href='KonkaJxcOrderAuditProcessDefine.do?method=add&mod_id=${af.map.mod_id}';" />
    </div>
    <div class="rtabcont1" style="overflow-x: auto;">
      <div style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <!--        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>-->
          <th width="5%" nowrap="nowrap">序号</th>
          <th width="10%" nowrap="nowrap">流程编号</th>
          <th nowrap="nowrap">流程描述</th>
          <th width="8%" nowrap="nowrap">客户类型</th>
          <th width="5%" nowrap="nowrap">添加人</th>
          <th width="5%" nowrap="nowrap">添加人部门</th>
          <th width="10%" nowrap="nowrap">添加日期</th>
          <th width="10%" nowrap="nowrap">适用范围</th>
          <th width="5%" nowrap="nowrap">是否删除</th>
          <th width="5%" nowrap="nowrap">是否停用</th>
          <th width="5%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr> 
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="center" nowrap="nowrap">${cur.id} </td>
            <td align="left" nowrap="nowrap">${cur.process_desc}</td>
            <td align="center">${cur.map.c_name}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.add_dept_name)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center" nowrap="nowrap">
            <c:choose>
                <c:when test="${cur.used_field eq 0}"><span style="color:#060;">正常订单流程</span></c:when>
                <c:when test="${cur.used_field eq 1}"><span style="color:#F00;">变更订单流程</span></c:when>
                 <c:when test="${cur.used_field eq 2}"><span style="color:blue;">退货订单流程</span></c:when>
              </c:choose>
            </td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.is_del eq 0}"> <span style="color:#060;">未删除</span></c:when>
                <c:when test="${cur.is_del eq 1}"> <span style="color:#F00;">已删除</span></c:when>
              </c:choose>
              </td>
              <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.is_stop eq 0}"> <span style="color:#060;">未停用</span></c:when>
                <c:when test="${cur.is_stop eq 1}"> <span style="color:#F00;">已停用</span></c:when>
              </c:choose>
              </td>
            <td align="center" nowrap="nowrap">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAuditProcessDefine.do', 'edit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">修改</span>
           	| <c:choose>
               <c:when test="${cur.is_del eq 0}"> 
               <span style="cursor:pointer;"  class="fblue"  onclick="if(confirm('确定删除？')){doNeedMethod(null, 'KonkaJxcOrderAuditProcessDefine.do', 'delete','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())}else{return false}">删除</span>
               <c:if test="${cur.is_stop eq 0}">
                | <span title="已启用" style="color: gray">启用</span>
            |  <span style="cursor:pointer;"  class="fblue"  onclick="if(confirm('确定停用？')){doNeedMethod(null, 'KonkaJxcOrderAuditProcessDefine.do', 'stop','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())}else{return false}">停用</span>
              </c:if>
                <c:if test="${cur.is_stop eq 1}">
               | <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('确定启用？', 'KonkaJxcOrderAuditProcessDefine.do', 'start','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用</span>
               | <span title="已停用" style="color: gray">停用</span></c:if>
               </c:when>
               <c:when test="${cur.is_del eq 1}"> <span title="已删除" style="color: gray">删除</span>
                | <span title="已删除,不能启用" style="color: gray">启用</span>
               | <span title="已删除,不能停用" style="color: gray">停用</span>
              
               </c:when>
            </c:choose> 
           	| <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderAuditProcessDefine.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span>
            </td>
          </tr>
        </c:forEach>
      </table>
      </div>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcOrderAuditProcessDefine.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("process_type", "${af.map.process_type}");
		       pager.addHiddenInputs("is_del", "${af.map.is_del}");
		       pager.addHiddenInputs("is_stop", "${af.map.is_stop}");
		       pager.addHiddenInputs("customer_type", "${af.map.customer_type}");
		       <c:if test="${not empty is_show_dept}">
		       pager.addHiddenInputs("add_dept_id", "${af.map.add_dept_id}");
		       </c:if>
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
 
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#add_dept_id").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:180
		
	}).multiselectfilter({width:90});  
	

	$("#customer_type").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:180
		
	}).multiselectfilter({width:90});                                         
	
          
	
	
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>