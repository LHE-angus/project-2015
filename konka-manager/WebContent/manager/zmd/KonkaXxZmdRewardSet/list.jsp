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
    <html-el:form action="/zmd/KonkaXxZmdRewardSet">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="75%"><strong class="fb">专卖店编号：</strong>
            <html-el:text property="zmd_sn" styleId="zmd_sn"  maxlength="20"></html-el:text>
<!--             &nbsp;<strong class="fb">营业状态：</strong> -->
<%--             <html-el:select property="is_open" styleId="is_open" onchange="this.form.submit();"> --%>
<%--               <html-el:option value="">==请选择==</html-el:option> --%>
<%--               <c:forEach var="cur_03" items="${baseTypesList30000}"> --%>
<%--                 <html-el:option value="${cur_03.type_id}">${cur_03.type_name}</html-el:option> --%>
<%--               </c:forEach> --%>
<%--             </html-el:select> --%>
            </td>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" >专卖店编号及地址</td>
        <td nowrap="nowrap" align="center" width="10%">专卖店负责人</td>
        <td nowrap="nowrap" align="center" >返佣比例</td>
<!--         <td nowrap="nowrap"  align="center" width="8%">营业状态</td> -->
        <td nowrap="nowrap" align="center" width="10%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.zmd_sn)}，${cur.map.p_index_name}${cur.addr}</td>
            <td align="left">${empty cur.host_name ? '未填写' : cur.host_name}</td>
            <td><c:forEach var="cur1" items="${cur.map.konkaXxZmdRewardSetList}" varStatus="vs"> ${cur1.map.type_name}:${cur1.reward_ratio}%
                <c:if test="${not vs.last}">，</c:if>
              </c:forEach></td>
<%--             <td align="center"><c:forEach var="cur_03" items="${baseTypesList30000}"> --%>
<%--                 <c:if test="${cur.is_open eq cur_03.type_id}">${cur_03.type_name}</c:if> --%>
<%--               </c:forEach> --%>
<%--               <c:if test="${empty cur.is_open}">-</c:if></td> --%>
            <td align="center" nowrap="nowrap"><img src="${ctx}/images/set.png" /> <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdRewardSet.do', 'edit','zmd_sn=${cur.zmd_sn}&zmd_id=${cur.zmd_id}&'+$('#bottomPageForm').serialize())">设置返佣</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
<!--             <td>&nbsp;</td> -->
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdRewardSet.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("zmd_sn", "${af.map.zmd_sn}");
				pager.addHiddenInputs("is_open", "${af.map.is_open}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
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
