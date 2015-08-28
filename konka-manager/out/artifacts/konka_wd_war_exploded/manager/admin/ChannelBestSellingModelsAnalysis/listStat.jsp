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
<div class="oarcont" >
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/ChannelBestSellingModelsAnalysis">
      <html-el:hidden property="method" value="listStat" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="model_list" value="${af.map.model_list}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">类别：</strong>
            <c:forEach var="cur" items="${idtList}" varStatus="vs">
              <html-el:radio property="type" styleId="type_${vs.count}" value="${cur.data_type}" onclick="this.form.submit();" />
              <label for="type_${vs.count}">&nbsp;${cur.type_name }</label>
              <c:if test="${af.map.type eq  cur.data_type}">
                <c:set var="tb_title" value="${cur.type_name }"></c:set>
              </c:if>
            </c:forEach>
            &nbsp; <span id="type_search"><strong class="fb">名称</strong></span>：
            <html-el:text property="keyWord" size="30" maxlength="255" styleId="keyWord" />
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  &nbsp;
  <input class="but1" type="submit"  value="导出" name="toExcelButton1" onclick="toExcel('divExcel', '?method=toExcel');" />
  <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
  <div  id="divExcel" title="畅销机型统计.xls" class="rtabcont1" style="overflow-x:auto;">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="left">名称</td>
        <c:forEach var="cur" items="${arr}" varStatus="vs1">
          <td align="center" nowrap="nowrap" width="5%">${cur}</td>
        </c:forEach>
        <td nowrap="nowrap" align="center"  width="100">所选机型累计</td>
        <td nowrap="nowrap" align="center" width="180">所选机型在该${tb_title}的占比</td>
        <td nowrap="nowrap" align="center" width="190">所选机型在所有${tb_title}的占比</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <c:forEach var="_cur" items="${cur.taskParaList}" varStatus="_vs">
          <tr>
            <c:if test="${_vs.first eq true}">
              <td  align="center">${vs.count}</td>
              <td  align="left" nowrap="nowrap">${_cur.task_name}</td>
            </c:if>
            <c:forEach var="e_sum" items="${cur.map.sumList}" varStatus="vs2">
              <td align="right" nowrap="nowrap"><c:if test="${e_sum ==0 || e_sum eq null}">0</c:if>
                <c:if test="${e_sum !=0 || e_sum ne null}">${e_sum}</c:if></td>
            </c:forEach>
            <td align="right">${cur.map.t_sum} </td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.t_per0}" pattern="0.00" />
              %</td>
            <td align="right"><fmt:formatNumber value="${cur.map.t_per1}" pattern="0.00000" />
              % </td>
          </tr>
        </c:forEach>
      </c:forEach>
    </table>
  </div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelBestSellingModelsAnalysis.do">
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listStat");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}")
			pager.addHiddenInputs("type", "${af.map.type}");	
			pager.addHiddenInputs("keyWord", "${fn:escapeXml(af.map.keyWord)}");
			pager.addHiddenInputs("model_list", "${af.map.model_list}");
			
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});


// var arr_types=["单客户","客户群","分公司","分大区"];
// function changeType(value){
// 		var type_search = document.getElementById("type_search");
// 		type_search.innerHTML = arr_types[parseInt(value) -1];
// }
// var type_search = '${af.map.type}';
// if(type_search ==''){
// 	changeType(1);
// }else{
// 	changeType(type_search);
// }

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>