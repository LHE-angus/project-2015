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
    <html-el:form action="/admin/KonkaShopDevelop">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       <tr>
          <td width="15"></td>
          <td><strong class="fb">网点名：</strong>
                <html-el:text property="shop_name_like" styleId="shop_name_like" size="20" maxlength="20"/></td>
            
          <td><strong class="fb">是否分配业务员：</strong>
               <html-el:select property="is_assigned_ywy" styleId="is_assigned_ywy">
                 <html-el:option value="">-请选择-</html-el:option>
                 <html-el:option value="0">未分配</html-el:option>
                 <html-el:option value="1">已分配</html-el:option>
               </html-el:select></td>
          <td><strong class="fb">开拓状态：</strong>
                <html-el:select property="shop_develop_status" styleId="shop_develop_status">
                  <html-el:option value="">-请选择-</html-el:option>
                  <html-el:option value="0">待开拓</html-el:option>
                  <html-el:option value="1">正在开拓</html-el:option>
                  <html-el:option value="2">已开拓</html-el:option>
                </html-el:select></td>
         <td><strong class="fb">拜访状态：</strong>
                <html-el:select property="shop_visit_status" styleId="shop_visit_status">
                  <html-el:option value="">-请选择-</html-el:option>
                  <!--<html-el:option value="0">未拜访</html-el:option>-->
                  <html-el:option value="1">拜访中</html-el:option>
                  <html-el:option value="2">拜访完成</html-el:option>
                </html-el:select></td>
         <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaShopDevelop.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">网点名</font></td>
            <td width="60" align="center" nowrap="nowrap" ><font class="blue">开拓状态</font></td>
            <td width="60" align="center" nowrap="nowrap" ><font class="blue">拜访状态</font></td>
            <td width="60" nowrap="nowrap" align="center" ><font class="blue">拜访次数</font></td>
            <td width="80" align="center" nowrap="nowrap" ><font class="blue">最后拜访日期</font></td>
            <td width="150" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${shop_List}" varStatus="vs">
            <tr>
              <td height="28"  align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left">${cur.shop_name}</td>
              <td align="center">
                <c:choose>
                 <c:when test="${cur.develop_status eq 0}"> <span style="color: green;">待开拓</span> </c:when>
                 <c:when test="${cur.develop_status eq 1}"> <span style="color: green;">正在开拓</span> </c:when>
                 <c:when test="${cur.develop_status eq 2}"> <span style="color: #F00;">已开拓</span> </c:when>
                </c:choose></td>
                <td align="center">
                  <c:if test="${cur.develop_status eq 0}">--</c:if>
                  <c:if test="${cur.develop_status ne 0}">
                    <c:choose>
                     <c:when test="${cur.map.visit_status eq 0}"> <span style="color: green;">未拜访</span> </c:when>
                     <c:when test="${cur.map.visit_status eq 1}"> <span style="color: green;">拜访中</span> </c:when>
                     <c:when test="${cur.map.visit_status eq 2}"> <span style="color: #F00;">拜访完成</span> </c:when>
                    </c:choose>
                  </c:if>
               </td>
              <td align="center">
               <c:if test="${cur.develop_status eq 0}">--</c:if>
               <c:if test="${cur.develop_status ne 0}">${cur.map.visit_count}</c:if>
              </td>
              <td align="center">
               <c:if test="${cur.develop_status eq 0}">--</c:if>
               <c:if test="${cur.develop_status ne 0}"><fmt:formatDate value="${cur.map.visit_date }" pattern="yyyy-MM-dd"></fmt:formatDate></c:if>             
              </td>
              <td align="center" nowrap="nowrap">
                 <%--经营情况 --%>
                 <!-- <span style="cursor:pointer;"  class="fblue" onclick="sellAnalysisSubmit('bottomPageForm','page_id=EntpShopSearch5W&shop_id=${cur.shop_id}&type=1');">经营情况</span> -->
                  <%-- 待开拓的网点，分配业务员开拓 --%>
                  <c:if test="${cur.develop_status eq 0}">
                    <logic-el:match name="popedom" value="+2+">
                    <c:if test="${af.map.dev_able eq 1}">
                      <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaShopDevelop.do','shopIndirectDevelop','develop_id=${cur.id}&pks=${cur.shop_id }&' + $('#bottomPageForm').serialize())">业务员开拓</span> 
                    </c:if>
                    <c:if test="${af.map.dev_able ne 1}"> 
                      <span style="cursor:pointer;color:#CCCCCC;" class="fblue">业务员开拓</span>
                    </c:if>
                    </logic-el:match>
                    <logic-el:notMatch name="popedom" value="+2+">
                      <span style="cursor:pointer;color:#CCCCCC;" class="fblue">业务员开拓</span>
                    </logic-el:notMatch>
                  </c:if>
                  <%-- 开拓中、已开拓网点，查看业务员拜访记录--%>
                  <c:if test="${cur.develop_status ne 0}">
                    | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaShopVisitRecord.do','visit','shop_id=${cur.shop_id}&' + $('#bottomPageForm').serialize())">业务员拜访记录</span> 
                  </c:if>   
                  <logic-el:match name="popedom" value="+2+">          
                  <c:if test="${af.map.dev_able eq 1}">
                      | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaShopDevelop.do', 'toShopDirectDevelop','develop_id=${cur.id}&shop_id=${cur.shop_id}&'+$('#bottomPageForm').serialize())">直接开拓</span>  
                  </c:if>
                  <c:if test="${af.map.dev_able ne 1}"> 
                      | <span style="cursor:pointer;color:#CCCCCC;" class="fblue">直接开拓</span>
                 </c:if>  
                 </logic-el:match>
                 <logic-el:notMatch name="popedom" value="+2+">
                      | <span style="cursor:pointer;color:#CCCCCC;" class="fblue">直接开拓</span>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaShopDevelop.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
	            pager.addHiddenInputs("is_assigned_ywy", "${af.map.is_assigned_ywy}");
	            pager.addHiddenInputs("shop_develop_status", "${af.map.shop_develop_status}");
	            pager.addHiddenInputs("shop_visit_status", "${af.map.shop_visit_status}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">说明：管理员可以维护全部用户，事业部、分公司、办事处、经营部都只可以维护本部门下属的用户</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 440,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});

function sellAnalysisSubmit(form,key_values){
	var action = 'EntpShopSellAnalysis.do';
	var method = 'list';
	/*
	var brand_name_zh = document.getElementById("brand_name").value;
	if(brand_name_zh != ""){
		 key_values = key_values +'&brand_name_zh='+ encodeURI(brand_name_zh);
	}
	*/
	key_values = key_values +'&'+ $('#'+form).serialize();
	doNeedMethod(null, action, method, key_values);	
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>