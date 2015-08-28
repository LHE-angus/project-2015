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
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
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
    <html-el:form action="/admin/R3UserOperateAnalysis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="downloadAll" styleId="downloadAll"/>
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="900"><strong class="fb">网点名称：</strong>
                <html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like"  /></td>
          <td width="900"><strong class="fb">所在区域：</strong>
            <html-el:text property="area_name_like" size="20" maxlength="20" styleId="area_name_like"  /></td>
       </tr>
       <tr>
          <td width="15"></td>
          <td width="900"><strong class="fb"> R3编码　：</strong>
                <html-el:text property="code_like" size="20" maxlength="20" styleId="code_like"  /></td>
          <td width="900"><strong class="fb">经办名称：</strong>
            <html-el:text property="handle_name_like" size="20" maxlength="20" styleId="handle_name_like"  /></td>
      </tr>
      <tr> 
      	<td width="15"></td>    
        <td colspan="2"><jsp:include page="../_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
         &nbsp;&nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <c:if test="${not empty entityList}">
   <br/>
   <!--div style="text-align: left">
     <input type="button" name="download_page" value=" 下载本页面数据 " onclick="toExcel('divExcel', '${ctx}/manager/admin/R3UserOperateAnalysis.do?method=toExcel')" />
          <input type="button" name="download_all" value=" 下载查询结果所有数据 " onclick="downloadAll();" />
   </div>
   <br/-->
   </c:if>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="R3UserOperateAnalysis.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" width="60" align="center">序号</td>
          <td nowrap="nowrap" width="60">R3编码</td>
          <td nowrap="nowrap">网点名称</td>
          <td nowrap="nowrap" width="10%">客户类型</td>
          <td nowrap="nowrap" width="10%">区域信息</td>
          <td nowrap="nowrap" width="10%">经办名称</td>
          <td nowrap="nowrap" width="25%">网点分配</td>
          <td nowrap="nowrap" align="center" width="70">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
            <c:if test="${cur.is_match eq 0 or empty cur.is_match}">
            <td align="left">${fn:escapeXml(cur.customer_name)}
            </td>    
            </c:if>
            <c:if test="${cur.is_match eq 1}">
            <td align="left" ><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.id}&mod_id=${af.map.mod_id}">${cur.customer_name}</a> </td>
            </c:if>   
            <td>${cur.customer_type}</td>
            <td>${fn:escapeXml(cur.area_name)}</td>
            <td>${fn:escapeXml(cur.handle_name)}</td>
            <td><%@ include file="../_inc/view_fgs_jyb_bsc_ywy_name.jsp" %></td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.is_match eq 1}">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EntpShopSellAnalysis.do', 'list','back_type=1&shop_id=${cur.map.mmt_shop_id}&page_id=EntpShopSearch&pager.requestPage=1&method=list&mod_id=101030&tree_param=&&city=&country=&town=&cls_id=0&cls_ids_select=&chain_types_str=&more=&year=&month=&pd_type=&busi_total1=&busi_proportion1=&brand_id=&brand_name=&busi_total2=&busi_proportion2=&brand_ids_select=&page_type=0&type=1');">经营情况</span>
            </c:if>
            <c:if test="${cur.is_match ne 1}">
            	<span style="color:#ccc;" title="未匹配买卖提网点">经营情况</span>
            </c:if> 
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="${ctx}/manager/admin/R3UserOperateAnalysis.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("area_code_like", "${fn:escapeXml(af.map.area_code_like)}");	
			pager.addHiddenInputs("area_name_like", "${af.map.area_name_like}");	
			pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");	
			pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
	        pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
	        pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
	        pager.addHiddenInputs("ywy_user_id", "${af.map.ywy_user_id}");
	        pager.addHiddenInputs("code_like", "${fn:escapeXml(af.map.code_like)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="divExcel" style="display:none;" title="奥马网点梳理">
 <table width="600" border="1" cellpadding="0" cellspacing="0" class="rtable2">
  <tbody style="overflow:auto;">
       <tr>
         <th rowspan="2">序号</th>
         <th colspan="5"  nowrap="nowrap">奥马公司归属部门</th>
         <th  nowrap="nowrap"></th>
         <th colspan="10" nowrap="nowrap">奥马网点基本资料</th>
       </tr>
       <tr>
		<td  nowrap="nowrap">客户R3编码</td>
		<td  nowrap="nowrap">合并客户编码</td>
        <td  nowrap="nowrap">区域</td>
        <td  nowrap="nowrap">分公司</td>
        <td  nowrap="nowrap">经办名称</td>
        <td  nowrap="nowrap">客户名称</td>
        <td nowrap="nowrap">网点ID</td>
        <td width="20%" nowrap="nowrap">网点名称</td>
        <td  nowrap="nowrap">省</td>
        <td  nowrap="nowrap">市</td>
        <td  nowrap="nowrap">县/区</td>
        <td  nowrap="nowrap">中标类型</td>
        <td  nowrap="nowrap">网点联系人</td>
        <td  nowrap="nowrap">联系人职务</td>
        <td  nowrap="nowrap">手机</td>
        <td  nowrap="nowrap">电话</td>
        <td  nowrap="nowrap">网点地址</td>
        <td  nowrap="nowrap">主营产品</td>
        <td  nowrap="nowrap"></td>
   	  </tr>
    <c:forEach items="${downloadList}" var="cur" varStatus="vs">
	  <tr>
	     <td align="center" nowrap="nowrap">${fn:escapeXml(vs.count)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_code)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.area_name)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.branch_area_name)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.handle_name)}</td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_name)} </td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.shop_id)} </td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.shop_name)} </td>
         <c:forEach items="${cur.map.list}" var="list" varStatus="vu">
        	 <td align="left" nowrap="nowrap">${fn:escapeXml(list.p_name)} </td>
         </c:forEach>
         <td align="left" nowrap="nowrap">
	         <c:if test="${fn:escapeXml(cur.map.is_rural eq 1)}"> 下乡&nbsp;&nbsp;</c:if>
	         <c:if test="${fn:escapeXml(cur.map.is_auth eq 1)}">换新</c:if>
         </td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.link_user)} </td>
         <td align="left" nowrap="nowrap"> </td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.link_phone)} </td>
         <td align="left" nowrap="nowrap"></td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.street_addr)} </td>
         <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.main_pd)} </td>
         
      </tr>
 	</c:forEach>
    </tbody>
 </table>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var downloadAll = "${af.map.downloadAll}";
	if("all" == downloadAll){
		toExcel('divExcel', '${ctx}/manager/admin/R3UserOperateAnalysis.do?method=toExcel');
	}
	
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
    })
});

function downloadAll(){
	$("#downloadAll").val("all");
	document.forms[0].submit();
};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>