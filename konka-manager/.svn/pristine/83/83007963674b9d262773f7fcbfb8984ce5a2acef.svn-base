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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdAuditUserInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><c:if test="${empty dept_id}"> <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id" >
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach items="${konkaDeptList}" var="cur">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp; </c:if>
            <strong class="fb">专卖店编号：</strong>
            <html-el:text property="zmd_sn_like" styleId="zmd_sn_like" />
            &nbsp;<strong class="fb">客户姓名：</strong>
            <html-el:text property="user_name_like" styleId="user_name_like" /></td>
        </tr>
        <tr>
          <td width="15"></td>
          <td><strong class="fb">申请时间：</strong>
            <html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
   <div style="100%;overflow-x:scroll;height:340px;" >
    <table id="table1" width="2650"  border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="50" nowrap="nowrap" align="center">序号</td>
        <td width="120" nowrap="nowrap" align="center">分公司</td>
        <td width="120" nowrap="nowrap" align="center">客户姓名</td>
        <td width="120" nowrap="nowrap" align="center">专卖店编码</td>
        <td width="120" nowrap="nowrap" align="center">R3专卖店名称</td>
        <td width="120" nowrap="nowrap" align="center">R3编码</td>
        <td width="120" nowrap="nowrap" align="center">R3送达方编码</td>
        <td width="120" nowrap="nowrap" align="center">经营模式</td>
        <td width="250" nowrap="nowrap" align="center">专卖店地址</td>
        <td width="120" nowrap="nowrap" align="center">专卖店负责人</td>
        <td width="120" nowrap="nowrap" align="center">联系方式</td>
        <td width="200" nowrap="nowrap" align="center">申请建店费用（万元）</td>
        <td width="200" nowrap="nowrap" align="center">申请样机额度（万元）</td>
        <td width="120" nowrap="nowrap" align="center">资质申请人</td>
        <td width="120" nowrap="nowrap" align="center">申请时间</td>
        <td width="120" nowrap="nowrap" align="center">资质审核状态</td>
        <td width="120" nowrap="nowrap" align="center">备案状态</td>
        <td width="120" nowrap="nowrap" align="center">R3客户信息</td>
        <td width="120" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap"><a style="color: blue;cursor: pointer;" href="${ctx}/manager/zmd/KonkaXxZmdAuditUserInfoAudit.do?method=view&zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}">${cur.user_name}</a></td>
          <td align="left" nowrap="nowrap"><a style="color: blue;cursor: pointer;" href="${ctx}/manager/zmd/KonkaXxZmd.do?method=view&zmd_id=${cur.map.zmd_id}&mod_id=${af.map.mod_id}">${cur.zmd_sn}</a></td>
          <td align="left" nowrap="nowrap">${cur.map.r3_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_id}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_send_num}</td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.map.busi_mod}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList100000}">
                  <c:if test="${cur_2.type_id eq cur.map.busi_mod}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
          <td align="left" nowrap="nowrap">${cur.map.full_name}${cur.map.addr}</td>
          <td align="left" nowrap="nowrap">${cur.map.host_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.host_phone}</td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.money_of_dcrt_apply}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.money_of_dm_apply}" pattern="#0.00" /></td>
          <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.audit_status}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList130000}">
                  <c:if test="${cur_2.type_id eq cur.audit_status}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.map.arc_state}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList20000}">
                  <c:if test="${cur_2.type_id eq cur.map.arc_state}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><c:if test="${empty cur.map.konkaR3Shop}"><span style="color:#F00;">无客户资料<a href="javascript:void(0);" style="color:#F00;" title="客户资料来自R3系统，每日将自动同步至康佳渠道管理信息系统，该R3客户${cur.map.r3_id}在系统中暂时没有客户资料可能是由于系统未做同步功能，您可以联系系统管理员询问更多的信息">？</a></span></c:if>
          <c:if test="${not empty cur.map.konkaR3Shop}"><span style="color:green;">有客户资料</span></c:if></td>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAuditUserInfo.do', 'edit','zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}&edit_value=1&'+$('#bottomPageForm').serialize())">资质修改</span>|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmd.do', 'edit','zmd_id=${cur.map.zmd_id}&dept_id=${cur.dept_id}&mod_id=${af.map.mod_id}&edit_value=1&'+$('#bottomPageForm').serialize())">备案修改</span><c:if test="${(cur.is_del eq 0) && (!empty is_admin)}">|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否删除！', 'KonkaXxZmdAuditUserInfo.do', 'delete','zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}&is_f=1&&'+$('#bottomPageForm').serialize())">删除</span></c:if>|<c:if test="${empty cur.map.konkaR3Shop}"><span style="color:#ccc;text-decoration:underline;cursor:pointer;" title="店长帐号是专卖店店长用来登录客户端的用户资料，由于系统中暂不存在该专卖店的R3客户（${cur.map.r3_id}）资料，您还不能为该专卖店添加店长帐号。">创建帐号</span></c:if><c:if test="${not empty cur.map.konkaR3Shop and empty cur.map.peProdUser}"><span style="text-decoration:underline;cursor:pointer;" class="fblue" onclick="location.href='${ctx}/manager/admin/CustomerUsers.do?method=listSelectZmd&cust_id=${cur.map.konkaR3Shop.id}&mod_id=${af.map.mod_id}&zmd_id=${cur.map.zmd_id}';" title="添加店长帐号">创建帐号</span></c:if><c:if test="${not empty cur.map.konkaR3Shop and not empty cur.map.peProdUser}"><span style="text-decoration:underline;cursor:pointer;" class="fblue" onclick="location.href='${ctx}/manager/admin/CustomerUsers.do?method=edit&cust_id=${cur.map.konkaR3Shop.id}&mod_id=${af.map.mod_id}&zmd_id=${cur.map.zmd_id}&user_id=${cur.map.peProdUser.id}&request_from=zmd';" title="修改店长帐号">修改帐号</span></c:if>
          </td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
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
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAuditUserInfo.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><input class="but_excel" type="button" name="excel_btn" value="导出" id="excel_btn" onclick="toExcel('divExcel', '${ctx}/manager/zmd/KonkaXxZmdAuditUserInfo.do?method=toExcel');"/></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("dept_id","${af.map.dept_id}");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("add_date_ge","${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le","${af.map.add_date_le}");
            pager.addHiddenInputs("user_name_like","${af.map.user_name_like}");
            pager.addHiddenInputs("zmd_sn_like","${af.map.zmd_sn_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    <div style="100%;overflow-x:scroll;height:340px;display: none;" id="divExcel" title="专卖店客户信息" >
    <table id="table1" width="2100"  border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="50" align="center">序号</td>
        <td width="120" align="center">分公司</td>
        <td width="120" align="center">客户姓名</td>
        <td width="120" align="center">专卖店编码</td>
        <td width="120" align="center">R3专卖店名称</td>
        <td width="120" align="center">R3编码</td>
        <td width="120" align="center">R3送达方编码</td>
        <td width="120" align="center">经营模式</td>
        <td width="250" align="center">专卖店地址</td>
        <td width="120" align="center">专卖店负责人</td>
        <td width="120" align="center">联系方式</td>
        <td width="120" align="center">申请建店费用</td>
        <td width="120" align="center">申请样机额度</td>
        <td width="120" align="center">资质申请人</td>
        <td width="120" align="center">申请时间</td>
        <td width="120" align="center">资质审核状态</td>
        <td width="120" align="center">备案状态</td>
        <td width="120" align="center">备案通过时间</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.user_name}</td>
          <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_id}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_send_num}</td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.map.busi_mod}">
                <c:forEach var="cur_1" items="${baseTypesList100000}">
                  <c:if test="${cur_1.type_id eq cur.map.busi_mod}">${cur_1.type_name} </c:if>
                </c:forEach>
                </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
          <td align="left" >${cur.map.full_name}${cur.map.addr}</td>
          <td align="left" nowrap="nowrap">${cur.map.host_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.host_phone}</td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.money_of_dcrt_apply}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.money_of_dm_apply}" pattern="#0.00" /></td>
          <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.audit_status}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList130000}">
                  <c:if test="${cur_2.type_id eq cur.audit_status}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${!empty cur.map.arc_state}"> <font class="blue12px">
                <c:forEach var="cur_2" items="${baseTypesList20000}">
                  <c:if test="${cur_2.type_id eq cur.map.arc_state}">${cur_2.type_name} </c:if>
                </c:forEach>
                </font> </c:when>
              <c:otherwise>--</c:otherwise>
            </c:choose></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.zmd_audit_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </c:forEach>
    </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
