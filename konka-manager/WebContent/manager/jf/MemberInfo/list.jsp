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
    <html-el:form action="/jf/MemberInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap"> &nbsp;&nbsp;<strong class="fb">会员姓名：</strong>
            <html-el:text property="user_name_like" styleId="user_name_like" style="width:100px;" />
            &nbsp;<strong class="fb">电话：</strong>
            <html-el:text property="mp_like" styleId="mp_like" style="width:100px;" />
            &nbsp;
            <strong class="fb">卡号：</strong>
            <html-el:text property="user_sn_like" styleId="user_sn_like" style="width:100px;" />&nbsp;
            </td></tr>
            <tr><td>
             &nbsp; &nbsp;<strong class="fb">地区：</strong>
            <select name="province" id="province" class="bd">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            <select name="city" id="city" class="bd">
              <option value="" >-请选择市-</option>
            </select>
            <select name="country" id="country" class="bd">
              <option value="">-请选择县-</option>
            </select>
            <select name="town" id="town" class="bd">
              <option value="">-请选择乡/镇-</option>
            </select>
              &nbsp;
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
              <br/>
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
        <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='MemberInfo.do?method=add&mod_id=${af.map.mod_id}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">会员卡号</font></td>
            <td nowrap="nowrap" ><font class="blue">会员姓名</font></td>
            <td nowrap="nowrap" width="10%"><font class="blue">会员等级</font></td>
            <td nowrap="nowrap"><font class="blue">会员积分</font></td>
            <td nowrap="nowrap" width="100" ><font class="blue">会员手机</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">注册时间</font></td>
            <td nowrap="nowrap" align="center" ><font class="blue">状态</font></td>
            <td width="150" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.user_sn}" /></td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left" valign="middle">
              	<c:if test="${cur.user_level eq 0}">个人会员</c:if>
              	<c:if test="${cur.user_level eq 1}">白银会员</c:if>
              	<c:if test="${cur.user_level eq 2}">黄金会员</c:if>
              	<c:if test="${cur.user_level eq 3}">钻石会员</c:if>
              </td>
              <td align="left"><c:out value="${cur.map.total_scorts}" /></td>
              <td align="left"><c:out value="${cur.mp}" /></td>
              <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.user_state eq 0}">正常</c:if>
              <c:if test="${cur.user_state eq 1}">停用</c:if>
              </td>
              <td align="center" nowrap="nowrap">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'MemberInfo.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">编辑</span>
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'MemberInfo.do','look' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
              <c:if test="${cur.user_state eq 0}">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定停用吗？', 'MemberInfo.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">停用</span>
              </c:if>
              <c:if test="${cur.user_state eq 1}">
              <span style="color:#CCC;" class="fblue">停用</span>
              </c:if>
              <a><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'MemberInfo.do','memberCardChange' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">会员卡更换</span></a></td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="MemberInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
	            pager.addHiddenInputs("mp_like", "${af.map.mp_like}");
	            pager.addHiddenInputs("user_sn_like", "${af.map.user_sn_like}");
	            pager.addHiddenInputs("province", "${af.map.province}");
	            pager.addHiddenInputs("city", "${af.map.city}");
	            pager.addHiddenInputs("country", "${af.map.country}");
	            pager.addHiddenInputs("town", "${af.map.town}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//区域回显
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
