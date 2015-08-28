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
    <html-el:form action="/spgl/EcUser.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">姓名：</strong>
            <html-el:text property="name_like" styleId="name_like" style="width:160px;" />&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">工卡号：</strong>
            <html-el:text property="card_no_like" styleId="card_no_like" style="width:160px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">部门名称：</strong>
            <html-el:text property="department_like" styleId="department_like" style="width:160px;" />
         </td>
         </tr>
         <tr style="height:25px;">
         <td style="height:25px;" nowrap="nowrap">
         &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">会员类型：</strong><html-el:select property="user_type" styleId="user_type" >
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="1">工卡会员</html-el:option>
                <html-el:option value="2">触网会员</html-el:option>
              </html-el:select>&nbsp;&nbsp;
               <strong class="fb">会员等级：</strong><html-el:select property="card_level_name" styleId="card_level_name" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach items="${levelList}" var="cur">
                	 <html-el:option value="${cur.card_level}">${cur.card_level_name}</html-el:option>
                </c:forEach>
              </html-el:select>&nbsp;&nbsp;  
           <strong class="fb">审核状态：</strong><html-el:select property="is_act" styleId="is_act" >
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="0">审核通过</html-el:option>
                <html-el:option value="1">待完善资料</html-el:option>
                <html-el:option value="2">待审核</html-el:option>
                <html-el:option value="3">审核不通过</html-el:option>
              </html-el:select>&nbsp;&nbsp;
              <strong class="fb">是否停用：</strong><html-el:select property="is_del" styleId="is_del" >
                <html-el:option value="0">未停用</html-el:option>
                <html-el:option value="1">已停用</html-el:option>
              </html-el:select>
              </td>
        </tr>
        <tr style="height:25px;">
         <td style="height:25px;" nowrap="nowrap">
          <strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;部门：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;&nbsp;
               &nbsp;<strong class="fb">审核时间：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              &nbsp; <input class="but1" type="submit" name="Submit" value="搜索" />
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
        <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='EcUser.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';return false;" />
        </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow-x:auto; ">
    <form id="listForm" name="listForm" method="post" action="EcUser.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">工卡号</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">登录名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">姓名</font></td>            
            <td width="10%" nowrap="nowrap" ><font class="blue">注册时间</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">R3编码</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">所在分公司</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">类型</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">部门名称</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">会员等级</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">付款积分</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">奖励积分</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">已使用积分</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">未使用积分</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">已发放佣金</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">未发放佣金</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">已使用购物券</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">未使用购物券</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">审核状态</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">审核时间</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">审核人</font></td>
            <td width="20%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td  nowrap="nowrap"  align="center">${af.map.pager.pageSize*(af.map.pager.currentPage-1)+vs.count}</td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.card_no}" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.user_name}" /></td>
              <td  nowrap="nowrap" align="left" valign="middle"><a style="text-decoration:underline;" href="EcUser.do?method=view&user_id=${cur.id}&mod_id=${af.map.mod_id}">${cur.real_name}</a></td>
              <td  nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.map.r3_code}" /></td>
               <td  nowrap="nowrap" align="left"><c:out value="${cur.map.dept_name}" /></td>
              <td  nowrap="nowrap" align="left">
              	<c:if test="${cur.user_type eq 1}">工卡会员</c:if>
              	<c:if test="${cur.user_type eq 2}">触网会员</c:if>
              	<c:if test="${cur.user_type eq 3}">其他</c:if>
              </td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.department}" /></td>
              <td  nowrap="nowrap" align="center"><c:out value="${cur.map.card_level_name}" /></td>
              <td  nowrap="nowrap" align="center"><a style="text-decoration:underline;" href="EcUser.do?method=view3&user_id=${cur.id}&mod_id=${af.map.mod_id}"><c:out value="${not empty cur.map.pay_integral ? cur.map.pay_integral:0.00 }" /></a></td>
              <td  nowrap="nowrap" align="center"><c:out value="${cur.map.jlScore}" /></td>
              <td  nowrap="nowrap" align="center"><a style="text-decoration:underline;" href="EcUser.do?method=view3&user_id=${cur.id}&mod_id=${af.map.mod_id}"><c:out value="${not empty cur.map.da_score ? cur.map.da_score:0.00 }" /></a></td>
              <td  nowrap="nowrap" align="center"><a style="text-decoration:underline;" href="EcUser.do?method=view3&user_id=${cur.id}&mod_id=${af.map.mod_id}">
              <c:if test="${cur.dept_id eq 2110}">
              	<c:out value="${not empty cur.map.pay_integral ? cur.map.pay_integral:0.00 }" />
              </c:if>
              <c:if test="${cur.dept_id ne 2110}">
              <c:out value="${not empty cur.map.wa_score ? cur.map.wa_score:0.00 }" />
              </c:if>
              </a></td>
              <td  nowrap="nowrap" align="center"><a style="text-decoration:underline;" href="EcUser.do?method=view1&user_id=${cur.id}&mod_id=${af.map.mod_id}"><c:out value="${not empty cur.map.da_rebates ? cur.map.da_rebates:0.00 }" /></a></td>
              <td  nowrap="nowrap" align="center"><a style="text-decoration:underline;" href="EcUser.do?method=view2&user_id=${cur.id}&mod_id=${af.map.mod_id}"><c:out value="${not empty cur.map.wa_rebates ? cur.map.w_rebates:0.00 }" /></a></td>
              <td  nowrap="nowrap" align="center"><c:out value="${not empty cur.map.da_price ? cur.map.da_price:0.00 }" /></td>
              <td  nowrap="nowrap" align="center"><c:out value="${not empty cur.map.wa_price ? cur.map.wa_price:0.00 }" /></td>
              <td  nowrap="nowrap" align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
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
              
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUserScoreRec.do', 'list','user_id=${cur.id}&mod_id=${af.map.mod_id }&'+$('#bottomPageForm').serialize())">会员积分</span>
                  </logic-el:match>
                    |
                   <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'EcUser.do', 'initPassword','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">登录密码重置</span></logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc" class="fblue" title="重置密码">登录密码重置</span></logic-el:notMatch>
                 |
                 <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'EcUser.do', 'initPassword2','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">支付密码重置</span></logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc" class="fblue" title="重置密码">支付密码重置</span></logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUser.do', 'edit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
                    <!--  <span style="cursor:pointer;" class="fblue" onclick="test('${cur.user_name}')">测试</span>    -->
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">  
                  <span style="color:#CCC;" class="fblue">修改</span>
                  </logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUser.do', 'audit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">审核</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">审核</span>
                  </logic-el:notMatch>
                  |
                  <logic-el:match name="popedom" value="+2+">
                  <span class="fblue" style="cursor:pointer;" onclick="confirmDelete('是否停用该用户？', 'EcUser.do', 'user_id=${cur.id}&'+$('#bottomPageForm').serialize())">停用</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">停用</span>
                  </logic-el:notMatch>
                  
              </c:if>
                <c:if test="${cur.is_del eq 1}">
                <span style="color:#ccc" class="fblue" title="重置密码">登录密码重置</span>|
                <span style="color:#CCC;" class="fblue">修改</span>|
                 <span class="fblue" style="cursor:pointer;" onclick="doNeedMethod('是否启用该用户？', 'EcUser.do', 'reStart','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">启用</span>  
                </c:if></td>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcUser.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("name_like", "${fn:escapeXml(af.map.name_like)}");
	            pager.addHiddenInputs("is_act", "${af.map.is_act}");
	            pager.addHiddenInputs("is_del", "${af.map.is_del}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("user_type", "${af.map.user_type}");
	            pager.addHiddenInputs("card_level_name", "${af.map.card_level_name}");
	            pager.addHiddenInputs("card_no_like", "${fn:escapeXml(af.map.card_no_like)}");
	            pager.addHiddenInputs("department_like", "${fn:escapeXml(af.map.department_like)}");
	            pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
				pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  <div class="clear"></div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="岗位列表">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">登录名</font></td>
            <td nowrap="nowrap" ><font class="blue">在岗人员</font></td>
            <td nowrap="nowrap" ><font class="blue">类型</font></td>
            <td nowrap="nowrap"><font class="blue">部门</font></td>
            <td nowrap="nowrap"><font class="blue">会员等级</font></td>
            <td nowrap="nowrap"><font class="blue">付款积分</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
          </tr>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <c:if test="${cur.user_type eq 99}">
              <c:set var="is_admin" value="true" />
            </c:if>
            <tr>
              <td height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left" valign="middle">${cur.real_name}</td>
              <td align="left">
              	<c:if test="${cur.user_type eq 1}">工卡会员</c:if>
              	<c:if test="${cur.user_type eq 2}">触网会员</c:if>
              	<c:if test="${cur.user_type eq 3}">其他</c:if>
              </td>
              <td align="left"><c:out value="${cur.department}" /></td>
              <td align="center"><c:out value="${cur.map.card_level_name}" /></td>
              <td align="center"><c:out value="${not empty cur.map.pay_integral ? cur.map.pay_integral:0.00 }" /></td>
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
            </tr>
          </c:forEach>
      </table>
    </div>
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
    	 this.form.action="${ctx}/manager/spgl/EcUser.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcUser.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });
    
});

function test(user_name){
	var url = "${ctx}/manager/spgl/EcUser.do?method=test&user_name="+user_name;
	url = encodeURI(encodeURI(url)); 
	alert(url); 
	location.href = url;  
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
