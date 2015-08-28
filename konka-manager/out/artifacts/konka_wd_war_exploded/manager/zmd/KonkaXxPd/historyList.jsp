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
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;&nbsp;<span style="color:blue">${md_name}</span>&nbsp;型号的历史版本&nbsp;&nbsp;<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
      </tr>
    </table>
 </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">产品型号</td>
        <td nowrap="nowrap" align="center">工厂仓位</td>
        <td nowrap="nowrap" align="center" width="9%">参考价格</td>
        <td nowrap="nowrap" align="center" width="9%">价格下限</td>
        <td nowrap="nowrap" align="center" width="8%">上架时间</td>
        <td nowrap="nowrap" align="center" width="8%">下架时间</td>
        <td nowrap="nowrap" align="center" width="8%">添加时间</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td  align="left">${cur.md_name}</td>
            <td align="left">${fn:escapeXml(cur.map.fac_store_name)}</td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_ref}" /></span></td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_min}" /></span></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.up_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
           <!--  <td align="center" nowrap="nowrap"><span style="cursor: pointer;" id="his_${cur.history_id}" class="fblue">版本还原</span></td> -->
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPd.do?method=historyList">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("dept_pd_id", "${af.map.dept_pd_id}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	$("span[id^=his_]").click(function(){
	 	var	but= confirm("确定要还原该版本吗？");
	 	if(but){
		var $this = $(this);
		var history_id = this.id.replace("his_", "");
		var mod_id = ${af.map.mod_id};
		location.href="${ctx}/manager/zmd/KonkaXxPd.do?method=returnHistoryPd&history_id="+history_id+"&mod_id="+mod_id;
	 	} 
	 	else{
	 		return false;
		}
	});
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
