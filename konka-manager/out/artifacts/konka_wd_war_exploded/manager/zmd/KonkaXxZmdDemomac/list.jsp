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
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdDemomac">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">产品类别：</strong>
            <html-el:select property="cls_id" onchange="this.form.submit();">
              <c:forEach var="cur" items="${bpcList}">
                <html-el:option value="${cur.cls_id}" styleId="${cur.cls_id}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;<strong class="fb">型号：</strong>
            <html-el:text property="md_name" />&nbsp;<strong class="fb">专卖店：</strong>
            <html-el:select property="zmd_sn" style="width:150px;" onchange="this.form.submit();">
            <html-el:option value="">==请选择==</html-el:option>
            <c:forEach var="cur" items="${konkaXxZmdList}">
            <html-el:option value="${cur.zmd_sn}">${cur.zmd_sn}</html-el:option></c:forEach>
            </html-el:select></td>
        </tr>
        <tr><td width="15"></td>
         <td><strong class="fb">状态：</strong>
            <html-el:select property="state" onchange="this.form.submit();">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="60100">未上样</html-el:option>
              <html-el:option value="60200">上样</html-el:option>
              <html-el:option value="60300">下样</html-el:option>
            </html-el:select>&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='KonkaXxZmdDemomac.do?method=add&mod_id=${af.map.mod_id}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td align="center" width="40" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap" align="center">
        产品类别
        </td>
        <td align="center" nowrap="nowrap" align="center">
        型号
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        参考价格
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        安装费用
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        专卖店
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        数量
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        状态
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        分配人
        </td>
        <td  align="center" nowrap="nowrap" align="center">
        分配时间
        </td>
        <td align="center" width="50" nowrap="nowrap">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
            <td align="center" nowrap="nowrap">${cur.md_name}</td>
            <td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><fmt:formatNumber value="${cur.price_ref}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><fmt:formatNumber value="${cur.fix_fee}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><a href="KonkaXxZmdDemomac.do?method=add&mod_id=${af.map.mod_id}&zmd_id=${cur.map.zmd_id}"><span style="color: #f00">${cur.map.zmd_sn }</span></a></td>
            <td align="right" nowrap="nowrap">${cur.map.counts }</td>
            <td align="center" nowrap="nowrap"><c:if test="${cur.map.state eq '上样'}"><span style="color: green;">${cur.map.state}</span></c:if>
              <c:if test="${cur.map.state eq '未上样'}"><a href="javascript:confirmUpdate(null, 'KonkaXxZmdDemomac.do', 'id=${cur.map.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"><span style="color: #f00">${cur.map.state}</span></a></c:if>
              <c:if test="${cur.map.state eq '下样'}"><span style="color: gray;">${cur.map.state}</span></c:if></td>
            <td align="center" nowrap="nowrap">${cur.map.user_name }</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.dist_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center"><span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaXxZmdDemomac.do', 'id=${cur.map.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
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
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdDemomac.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
			pager.addHiddenInputs("md_name", "${af.map.md_name}");
			pager.addHiddenInputs("zmd_name", "${af.map.zmd_name}");
			pager.addHiddenInputs("state", "${af.map.state}");
			pager.addHiddenInputs("zmd_sn", "${af.map.zmd_sn}");
			document.write(pager.toString());
		</script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
