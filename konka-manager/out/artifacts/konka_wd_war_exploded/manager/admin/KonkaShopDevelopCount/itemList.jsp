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
    <html-el:form action="/admin/KonkaShopDevelopCount">
     <html-el:hidden property="method" value="itemList" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="develop_status" value="${af.map.develop_status}" />
      <html-el:hidden property="status" value="${af.map.status}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15" ></td>
          <td width="8%" nowrap="nowrap"><strong class="fb">网点名称：</strong>
            <html-el:text property="shop_name_like" styleId="shop_name_like" size="40" maxlength="80" /></td>
            <td ><strong class="fb">&nbsp;&nbsp;开拓起止日期：</strong>
           <html-el:text property="s_date" styleId="s_date" size="9" maxlength="9" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
            至
           <html-el:text property="e_date" styleId="e_date" size="9"  maxlength="9" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
       &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" />
       </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
   <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaShopDevelopCount.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
           <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
           <td align="left" nowrap="nowrap" align="center">网点名称</td>
           <td width="100" nowrap="nowrap" align="center">网点转化类型</td>
           <td width="60" nowrap="nowrap" align="center">开拓状态</td>
           <td width="100" nowrap="nowrap" align="center">开拓开始时间</td>
           <td width="100" nowrap="nowrap" align="center">开拓完成时间</td>
           <td width="50" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
             <td height="28" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left">${cur.shop_name}</td>         
              <td align="center">
              <c:if test="${cur.jxs_id eq null && cur.r3_id eq null}">--</c:if>
               <c:if test="${cur.jxs_id ne null}"> <span style="color: #F00;">经销商</span> </c:if>
               <c:if test="${cur.r3_id   ne null}"> <span style="color: #F00;">R3用户</span> </c:if>
                </td>
               <td align="center"><c:choose>
               <c:when test="${cur.develop_status eq 0}"> <span style="color: green;">待开拓</span> </c:when>
                  <c:when test="${cur.develop_status eq 1}"> <span style="color: green;">正在开拓</span> </c:when>
                  <c:when test="${cur.develop_status eq 2}"> <span style="color: #F00;">已开拓</span> </c:when>
                </c:choose></td>
              <td align="center">
                <fmt:formatDate value="${cur.start_date }" pattern="yyyy-MM-dd"></fmt:formatDate>           
              </td>             
              <td align="center">
               <c:if test="${cur.develop_status ne 2}">--</c:if>
               <c:if test="${cur.develop_status eq 2}"><fmt:formatDate value="${cur.end_date }" pattern="yyyy-MM-dd"></fmt:formatDate></c:if>             
              </td>
              <td align="center" nowrap="nowrap">
                 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaShopDevelopCount.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
     
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaShopDevelopCount.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "itemList");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
				pager.addHiddenInputs("s_date", "${af.map.s_date}");
				pager.addHiddenInputs("e_date", "${af.map.e_date}");
				pager.addHiddenInputs("develop_status", "${af.map.develop_status}");
				pager.addHiddenInputs("status", "${af.map.status}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
