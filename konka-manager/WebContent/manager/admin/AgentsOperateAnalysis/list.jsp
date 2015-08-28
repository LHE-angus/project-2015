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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
       <c:if test="${102020 eq af.map.mod_id }"> <td>当前位置：客户管理&nbsp;&gt;&nbsp;代理商网点&nbsp;&gt;&nbsp;网点经营分析</td></c:if>
       <c:if test="${102010 eq af.map.mod_id }"> <td>当前位置：客户管理&nbsp;&gt;&nbsp;代理商网点&nbsp;&gt;&nbsp;网点列表</td></c:if>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/AgentsOperateAnalysis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <!-- <td><strong class="fb">所在区域名称：</strong>
            <html-el:text property="area_name_like" size="20" maxlength="20" styleId="area_name_like"  /></td>
          <td><strong class="fb">经办名称：</strong>
            <html-el:text property="handle_name_like" size="20" maxlength="20" styleId="handle_name_like"  /></td>-->
          
          <td><strong class="fb">R3编码　：</strong> <html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code"  /></td>
          <td><strong class="fb">网点名称：</strong>
          <html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like"  /></td>
             </tr>
        <tr>
          <td width="15"></td> 
          <td colspan="2"><jsp:include page="../_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
                <html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/AgentsOperateAnalysis" method="post">
      <html-el:hidden property="method" value="shopDispach" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap">网点名称</td>
          <td nowrap="nowrap" width="10%">分配分公司</td>
          <td nowrap="nowrap" width="15%">分配经营部或办事处</td>
          <td nowrap="nowrap" width="15%">分配业务员</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" >${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" title="区域名称:${fn:escapeXml(cur.area_name)}
　分公司:${fn:escapeXml(cur.branch_area_name)}
客户类型:${cur.customer_type}
经办名称:${fn:escapeXml(cur.handle_name)}
"> ${cur.customer_name}</td>
            <td>${cur.map.fgs_name}<br/></td>
            <td>${cur.map.jyb_name}<br/></td>
            <td>${cur.map.ywy_name}<br/></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EntpShopSellAnalysis.do', 'list','back_type=1&shop_id=${cur.map.mmt_shop_id}&page_id=EntpShopSearch&pager.requestPage=1&method=list&mod_id=${af.map.mod_id}&tree_param=&&city=&country=&town=&cls_id=0&cls_ids_select=&chain_types_str=&more=&year=&month=&pd_type=&busi_total1=&busi_proportion1=&brand_id=&brand_name=&busi_total2=&busi_proportion2=&brand_ids_select=&page_type=0&type=1');">经营情况</span>
         <!-- 
           <c:if test="${102020 eq af.map.mod_id}">
            |
            <c:if test="${not empty cur.map.mmt_shop_id }">
             <span style="cursor:pointer;" class="fblue" id="map" onclick="doNeedMethod(null, 'R3UserOperateAnalysis.do', 'edit','shop_id=${cur.map.mmt_shop_id}&mod_code=${af.map.mod_code }&'+$('#bottomPageForm').serialize())">修改网点</span>
             </c:if>
             <c:if test="${empty cur.map.mmt_shop_id }">
               <span style="color: #ccc" class="fblue" title="未成功匹配MMT网点的网点不可以修改">修改网点</span>
             </c:if>
             </c:if>
              -->
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
          </tr>
        </c:forEach>
      </table>
    </html-el:form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="AgentsOperateAnalysis.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("user_id", "${af.map.user_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("role_id", "${af.map.role_id}")
			pager.addHiddenInputs("area_code_like", "${fn:escapeXml(af.map.area_code)}");	
			pager.addHiddenInputs("area_name_like", "${fn:escapeXml(af.map.area_name)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");	
			pager.addHiddenInputs("handle_name_like", "${fn:escapeXml(af.map.handle_name)}");
			pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
	         pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
	         pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
	          pager.addHiddenInputs("ywy_user_id", "${af.map.ywy_user_id}");
	          pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
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
	

function confirmDispatchAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("请至少选择一个网点！");
	} else {
			form.submit();
	}
}




//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>