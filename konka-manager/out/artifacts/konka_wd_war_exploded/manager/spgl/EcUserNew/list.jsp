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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcUserNew.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">登录名：</strong>
            <html-el:text property="user_name_like" styleId="user_name_like" maxlength="30" style="width:120px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">客户编码：</strong>
            <html-el:text property="cust_code_like" styleId="cust_code_like" maxlength="30" style="width:120px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">会员编号：</strong>
            <html-el:text property="user_no_like" styleId="user_no_like" maxlength="30" style="width:120px;" />
             &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">关联码：</strong>
            <html-el:text property="link_code" styleId="link_code" maxlength="30" style="width:120px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">审核人：</strong>
            <html-el:text property="chargeman_like" styleId="chargeman_like" maxlength="30" style="width:120px;" />
         </td>
         </tr>
         <tr style="height:25px;">
         <td style="height:25px;" nowrap="nowrap">
              <strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;总部/分公司：</strong>
            <html-el:select property="plat_sys" styleId="plat_sys" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">总部</html-el:option>
              <html-el:option value="1">分公司</html-el:option>
            </html-el:select> &nbsp;&nbsp; 
              <strong class="fb"> 所属组织：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> &nbsp;&nbsp;&nbsp;  
              <strong class="fb">是否停用：</strong><html-el:select property="is_del" styleId="is_del" onchange="this.form.submit();">
                <html-el:option value="0">未停用</html-el:option>
                <html-el:option value="1">已停用</html-el:option>
              </html-el:select>
              </td>
        </tr>
        <tr style="height:25px;">
         <td style="height:25px;" nowrap="nowrap">  
          <strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;审核状态：</strong><html-el:select property="is_act" styleId="is_act" onchange="this.form.submit();">
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="0">审核通过</html-el:option>
                <html-el:option value="1">待完善资料</html-el:option>
                <html-el:option value="2">待审核</html-el:option>
                <html-el:option value="3">审核不通过</html-el:option>
              </html-el:select>&nbsp;&nbsp;
            &nbsp;&nbsp;
              <strong class="fb">会员级别：</strong>
              <html-el:select property="level1" styleId="level1" >
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="1">1</html-el:option>
                <html-el:option value="2">2</html-el:option>
                <html-el:option value="3">3</html-el:option>
                <html-el:option value="4">4</html-el:option>
                <html-el:option value="5">5</html-el:option>
                <html-el:option value="6">6</html-el:option>
              </html-el:select>&nbsp;&nbsp;&nbsp;&nbsp; 
               &nbsp;<strong class="fb">审核时间：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
              <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
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
         <logic-el:match name="popedom" value="+1+">
        <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='EcUserNew.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';return false;" />
        </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow-x:auto; ">
    <form id="listForm" name="listForm" method="post" action="EcUserNew.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" align="center"><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap"  align="center"><font class="blue">会员编号</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">登录名</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">会员级别</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">上级会员</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">手机</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">客户名称</font></td> 
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">关联码</font></td> 
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">客户编码</font></td>            
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">所属组织</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">总部/分公司</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">审核状态</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">审核时间</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">审核人</font></td>
            <td width="20%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td  nowrap="nowrap"  align="center">${af.map.pager.pageSize*(af.map.pager.currentPage-1)+vs.count}</td>
                <td  nowrap="nowrap" align="left">
              	${cur.user_no}
              </td>
               <td  nowrap="nowrap" align="left" ><c:out value="${cur.user_name}"/></td> 
               <td  nowrap="nowrap" align="center">
              	${cur.map.user_level}
              </td>
               <td  nowrap="nowrap" align="left">
              	${cur.link_user_name}
              </td>
              <td  nowrap="nowrap" align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td  nowrap="nowrap" align="left">
              	${cur.map.cust_name}
              </td>
              <td  nowrap="nowrap" align="left">
              	${cur.link_code}
              </td>
              <td  nowrap="nowrap" align="left">
              	${cur.map.cust_code}
              </td>
               <td  nowrap="nowrap" align="left"> 
              	${cur.map.group_name}
              </td>
              <td  nowrap="nowrap" align="left"> 
               <c:if test="${cur.plat_sys eq 0}">总部</c:if>
               <c:if test="${cur.plat_sys eq 1}">分公司</c:if>
              </td>
              <td  nowrap="nowrap" align="center">
                <c:if test="${cur.is_act eq 0}">审核通过</c:if>
              	<c:if test="${cur.is_act eq 1}">待完善资料</c:if>
              	<c:if test="${cur.is_act eq 2}">待审核</c:if>
              	<c:if test="${cur.is_act eq 3}">审核不通过</c:if>
               </td> 
               <td  nowrap="nowrap" align="center"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
               <td  nowrap="nowrap" align="center">${cur.chargeman}</td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.is_del eq 0}">
                   <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'EcUserNew.do', 'initPassword','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">登录密码重置</span></logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc" class="fblue" title="重置密码">登录密码重置</span></logic-el:notMatch>
                 |
                 <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'EcUserNew.do', 'initPassword2','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">支付密码重置</span></logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc" class="fblue" title="重置密码">支付密码重置</span></logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUserNew.do', 'edit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">修改</span>
                  </logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+8+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUserNew.do', 'audit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">审核</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+8+">
                  <span style="color:#CCC;" class="fblue">审核</span>
                  </logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+4+">
                  <span class="fblue" style="cursor:pointer;" onclick="confirmDelete('是否停用该用户？', 'EcUserNew.do', 'user_id=${cur.id}&'+$('#bottomPageForm').serialize())">停用</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+4+">
                  <span style="color:#CCC;" class="fblue">停用</span>
                  </logic-el:notMatch>
                </c:if>
                <c:if test="${cur.is_del eq 1}">
                <span style="color:#ccc" class="fblue" title="重置密码">登录密码重置</span>|
                <span style="color:#CCC;" class="fblue">修改</span>|
                 <logic-el:match name="popedom" value="+4+">
                 <span class="fblue" style="cursor:pointer;" onclick="doNeedMethod('是否启用该用户？', 'EcUserNew.do', 'reStart','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">启用</span>  
                 </logic-el:match>
                 <logic-el:notMatch name="popedom" value="+4+">
                 <span style="color:#CCC;" class="fblue">启用</span>
                 </logic-el:notMatch>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcUserNew.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("cust_code_like", "${fn:escapeXml(af.map.cust_code_like)}");
	            pager.addHiddenInputs("is_act", "${af.map.is_act}");
	            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
	            pager.addHiddenInputs("user_no_like", "${af.map.user_no_like}");
	            pager.addHiddenInputs("is_del", "${af.map.is_del}");
	            pager.addHiddenInputs("plat_sys", "${af.map.plat_sys}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("level1", "${af.map.level1}"); 
	            pager.addHiddenInputs("chargeman_like", "${af.map.chargeman_like}");  
	            pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
				pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
				pager.addHiddenInputs("link_code", "${af.map.link_code}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
   
	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/EcUserNew.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcUserNew.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });
    
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
