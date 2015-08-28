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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <html-el:form action="/ywygps/KonkaYwyMobile">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">手机号&nbsp;&nbsp;：</strong>
            <html-el:text property="ywy_mobile_no" size="15" maxlength="20" styleId="ywy_mobile_no"  maxlength="11"/>
            &nbsp;&nbsp;&nbsp;<strong class="fb">公司确认码：</strong>
            <html-el:text property="entp_crc_like" size="20" maxlength="20" styleId="entp_crc_like"  maxlength="30"/>
            <br/>
            <br/>
            <jsp:include page="../../admin/_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
            &nbsp;&nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /></td>
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
         <input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaYwyMobile.do?method=add&mod_id=${af.map.mod_id}&fs_id=${af.map.fs_id}';" />
         </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaYwyMobile.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
       <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" style="height:18px;">序号</td>
          <td align="center" nowrap="nowrap">真实姓名</td>
          <td align="center" nowrap="nowrap" width="10%">手机号</td>
          <td align="center" nowrap="nowrap" width="10%">业务员确认码</td>
          <td align="center" nowrap="nowrap" width="10%">所属公司</td>
          <td align="center" nowrap="nowrap" width="10%">公司确认码</td>
          <td align="center" nowrap="nowrap" width="5%">是否确认</td>
          <td align="center" nowrap="nowrap" width="10%">确认时间</td>
          <td align="center" nowrap="nowrap" width="5%">使用状态</td>
          <td align="center" nowrap="nowrap" width="5%">本月设置方案</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
            <td align="center" ><a style="text-decoration:underline;" href="${ctx}/manager/admin/PeProdUser.do?method=view&user_id=${cur.user_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${cur.map.real_name}</a></td>

            <td align="center" >${cur.mobile_no}</td>
            <td align="center" >${cur.ywy_crc}</td>
            <td align="center" >${cur.map.entp_name}</td>
            <td align="center" >${cur.entp_crc}</td>
            <td align="center" >
            <c:if test="${cur.is_activate eq 1}">已确认</c:if>
            <c:if test="${cur.is_activate eq 0}">未确认</c:if>
            </td> 
            <td align="center" >
            <c:if test="${cur.is_activate eq 1}"><fmt:formatDate value="${cur.confirm_date }" pattern="yyyy-MM-dd"></fmt:formatDate></c:if>
            <c:if test="${cur.is_activate eq 0}">--</c:if>
            </td>
            <td align="center" >
            <c:if test="${cur.use_status eq 0}">未使用</c:if>
            <c:if test="${cur.use_status eq 1}">使用中</c:if>
            <c:if test="${cur.use_status eq 2}">已停用</c:if>
            </td>
            <td align="center" >${cur.map.setplan_name}</td>
            <td align="center" nowrap="nowrap">
            <logic-el:match name="popedom" value="+2+">
             <c:if test="${cur.is_activate eq 0}">
               <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobile.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
             </c:if>   
             <c:if test="${cur.is_activate eq 1}">
               <span style="cursor:pointer;color:#CCCCCC;" class="fblue">修改</span>
             </c:if>
             </logic-el:match> 
             <logic-el:notMatch name="popedom" value="+2+">
               <span style="cursor:pointer;color:#CCCCCC;" class="fblue">修改</span>
             </logic-el:notMatch>
             |
             <logic-el:match name="popedom" value="+2+">
             <c:if test="${cur.use_status eq 1}">
               <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobile.do','useStatusProcess' ,'id=${cur.id}&mobile_no=${cur.mobile_no}&use_status=2&' + $('#bottomPageForm').serialize())">停用</span>
            </c:if>
            <c:if test="${cur.use_status eq 0 || cur.use_status eq 2}">
               <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobile.do','useStatusProcess' ,'id=${cur.id}&mobile_no=${cur.mobile_no}&use_status=1&' + $('#bottomPageForm').serialize())">启用</span>
            </c:if>
            </logic-el:match>
            <logic-el:notMatch name="popedom" value="+2+">
               <span style="cursor:pointer;color:#CCCCCC;" class="fblue">停用</span>
             </logic-el:notMatch>
             |
             <logic-el:match name="popedom" value="+4+">
              <span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaYwyMobile.do','id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
             </logic-el:match>
              <logic-el:notMatch name="popedom" value="+4+">
               <span style="cursor:pointer;color:#CCCCCC;" class="fblue">删除</span>
             </logic-el:notMatch>
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYwyMobile.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("ywy_mobile_no", "${af.map.ywy_mobile_no}");
            pager.addHiddenInputs("entp_crc_like", "${af.map.entp_crc_like}");
            pager.addHiddenInputs("fs_id", "${af.map.fs_id}");         
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
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
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>