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
    <html-el:form action="/zmd/KonkaXxZmdAuditUserInfo.do">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><c:if test="${empty dept_id}"> <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id" >
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach items="${dept_name}" var="cur">
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
          <td><strong class="fb">提交时间：</strong>
            <html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;<strong class="fb">资质审核状态：</strong>
            <html-el:select property="audit_status" styleId="audit_status" >
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList130000}">
                <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp; <strong class="fb">备案审核状态：</strong>
            <html-el:select property="arc_state" styleId="arc_state" >
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList20000}">
                <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select>
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
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="cursor: pointer;" type="submit" name="Submit2" value="资质申请" onclick="location.href='KonkaXxZmdAuditUserInfo.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td width="10%" align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">客户姓名</td>
        <td width="10%" align="center" nowrap="nowrap">专卖店编码</td>
        <td width="10%" align="center" nowrap="nowrap">资质申请人</td>
        <td width="10%" align="center" nowrap="nowrap">提交时间</td>
        <td width="8%" align="center" nowrap="nowrap">资质审核状态</td>
        <td width="10%" align="center" nowrap="nowrap">备案状态</td>
        <td width="18%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap"><c:if test="${!empty cur.user_name}"><a style="color: blue;cursor: pointer;" href="${ctx}/manager/zmd/KonkaXxZmdAuditUserInfoAudit.do?method=view&zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}">${cur.user_name}</a></c:if></td>
          <td align="left" nowrap="nowrap"><c:if test="${!empty cur.zmd_sn}"><a style="color: blue;cursor: pointer;" href="${ctx}/manager/zmd/KonkaXxZmd.do?method=view&zmd_id=${cur.map.zmd_id}&mod_id=${af.map.mod_id}">${cur.zmd_sn}</a></c:if></td>
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
          <td align="center" nowrap="nowrap"><c:if test="${cur.map.is_edit eq -1}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAuditUserInfo.do', 'edit','zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">资质修改</span></c:if>
            <c:if test="${cur.map.is_edit eq 1}"><span style="color: gray;">资质修改</span></c:if>
            <c:choose>
              <c:when test="${cur.map.arc_state eq 20100}">|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmd.do', 'edit','zmd_id=${cur.map.zmd_id}&dept_id=${cur.dept_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">备案修改</span></c:when>
              <c:when test="${cur.map.arc_state eq 20200}">|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmd.do', 'edit','zmd_id=${cur.map.zmd_id}&dept_id=${cur.dept_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">备案修改</span></c:when>
              <c:otherwise><span style="color: gray;">|备案修改</span></c:otherwise>
            </c:choose>
            <c:choose>
              <c:when test="${(cur.audit_status eq 130300) && (empty cur.map.arc_state)}">|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdApply.do', 'add','zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">备案申请</span></c:when>
              <c:otherwise><span style="color: gray;">|备案申请</span></c:otherwise>
            </c:choose>
            <c:if test="${cur.map.is_del eq 1}">|<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否删除！', 'KonkaXxZmdAuditUserInfo.do', 'delete','zmd_user_id=${cur.zmd_user_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span></c:if>
            <c:if test="${cur.map.is_del ne 1}">|<span style="color: gray;">删除</span></c:if>
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
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAuditUserInfo.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("dept_id","${af.map.dept_id}");
            pager.addHiddenInputs("add_date_ge","${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le","${af.map.add_date_le}");
            pager.addHiddenInputs("audit_status","${af.map.audit_status}");
            pager.addHiddenInputs("arc_state","${af.map.arc_state}");
            pager.addHiddenInputs("user_name_like","${af.map.user_name_like}");
            pager.addHiddenInputs("zmd_sn_like","${af.map.zmd_sn_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	$("#arc_state option[value = '20300']").remove();
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
