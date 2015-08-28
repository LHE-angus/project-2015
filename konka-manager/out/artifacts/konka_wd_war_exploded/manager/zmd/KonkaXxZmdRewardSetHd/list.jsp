<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdRewardSetHd">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">专卖店编号：</strong>
            <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${zmdList}">
                <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp; <strong class="fb">活动开始时间：</strong>
            <html-el:text property="hd_start_date" styleId="hd_start_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp; <strong class="fb">活动结束时间：</strong>
            <html-el:text property="hd_end_date" styleId="hd_end_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp; <strong class="fb">活动标题：</strong>
            <html-el:text property="hd_title_like" styleId="hd_title_like" size="16" maxlength="20"></html-el:text>
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxZmdRewardSetHd.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="30%">专卖店返佣</td>
        <td nowrap="nowrap" align="center">活动</td>
        <td nowrap="nowrap" align="center" width="8%">活动反佣</td>
        <td nowrap="nowrap" align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left"><span style="color:#F00; font-weight:700;">${fn:escapeXml(cur.map.zmd_sn)}</span>专卖店 “
              <c:forEach var="cur_1" items="${baseTypesList70000}">
                <c:if test="${cur.reward_type eq cur_1.type_id}">${cur_1.type_name}</c:if>
              </c:forEach>
              ”返佣设置</td>
            <td align="left">${cur.map.hd_title} （${cur.map.start_date} ~ ${cur.map.end_date}）</td>
            <td align="right" style="font-weight:700;color:#F00;"><fmt:formatNumber value="${cur.reward_ratio}" pattern=".00"/>
              %</td>
            <td align="center" nowrap="nowrap"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdRewardSetHd.do', 'view','hd_set_id=${cur.hd_set_id}&'+$('#bottomPageForm').serialize())">查看</span>|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdRewardSetHd.do', 'edit','hd_set_id=${cur.hd_set_id}&'+$('#bottomPageForm').serialize())">修改</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdRewardSetHd.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
				pager.addHiddenInputs("hd_title_like", "${af.map.hd_title_like}");
				pager.addHiddenInputs("hd_start_date", "${af.map.hd_start_date}");
				pager.addHiddenInputs("hd_end_date", "${af.map.hd_end_date}");
				pager.addHiddenInputs("reward_type", "${af.map.reward_type}");
				document.write(pager.toString());
			  </script>
            </div></td>
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

	$("#hd_start_date").datepicker();
	$("#hd_end_date").datepicker();

	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
