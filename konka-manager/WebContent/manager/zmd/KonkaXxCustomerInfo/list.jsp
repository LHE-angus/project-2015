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
    <html-el:form action="/zmd/KonkaXxCustomerInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">消费者姓名：</strong>
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" size="16" maxlength="20"></html-el:text>
            &nbsp; <strong class="fb">消费者性别：</strong>
            <html-el:select property="buyer_sex" styleId="buyer_sex" >
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="1">男</html-el:option>
              <html-el:option value="2">女</html-el:option>
              <html-el:option value="3">保密</html-el:option>
              <html-el:option value="0">不详</html-el:option>
            </html-el:select>
            <br/>
            <strong class="fb">消费者所在地：</strong>
            <html-el:select property="province" styleId="province" style="width:150px;" >
              <option value="">-请选择省/直辖市/自治区-</option>
            </html-el:select>
            &nbsp;
            <html-el:select property="city" styleId="city" style="width:100px;" >
              <option value="">-请选择市-</option>
            </html-el:select>
            &nbsp;
            <html-el:select property="country" styleId="country" style="width:100px;" >
              <option value="">-请选择县-</option>
            </html-el:select>
            &nbsp;
            <html-el:select property="town" styleId="town" style="width:100px;" >
              <option value="">-请选择乡/镇-</option>
            </html-el:select>
            &nbsp;
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">消费者姓名</td>
        <td nowrap="nowrap" align="center" width="12%">身份证</td>
        <td nowrap="nowrap" align="center" width="5%">性别</td>
        <td nowrap="nowrap"  align="center" width="8%">生日</td>
        <td nowrap="nowrap"  align="center" width="8%">电话</td>
        <td nowrap="nowrap"  align="center" width="8%">手机</td>
        <td align="center" width="14%">所在地</td>
        <td align="center">联系地址</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${cur.buyer_name}</td>
            <td align="center" nowrap="nowrap">${cur.buyer_id}
              <c:if test="${empty cur.buyer_id}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.buyer_sex eq 0}">不详</c:when>
                <c:when test="${cur.buyer_sex eq 1}">男</c:when>
                <c:when test="${cur.buyer_sex eq 2}">女</c:when>
                <c:otherwise>保密</c:otherwise>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${empty cur.buyer_birthday}"><span>-</span></c:when>
                <c:when test="${!empty cur.buyer_birthday}">
                  <fmt:formatDate value="${cur.buyer_birthday}" pattern="yyyy-MM-dd" />
                </c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap">${cur.buyer_tel}
              <c:if test="${empty cur.buyer_tel}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="center" nowrap="nowrap">${cur.buyer_phone}
              <c:if test="${empty cur.buyer_phone}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="left" title="${cur.map.p_index_name}">${fnx:abbreviate(cur.map.p_index_name, 10 * 2, '...')}
              <c:if test="${empty cur.map.p_index_name}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="left" title="${cur.buyer_link_addr}">${fnx:abbreviate(cur.buyer_link_addr, 25 * 2, '...')}
              <c:if test="${empty cur.buyer_link_addr}"><span style="color:gray;">未填写</span></c:if></td>
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
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxCustomerInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("buyer_name_like", "${af.map.buyer_name_like}");
				pager.addHiddenInputs("buyer_sex", "${af.map.buyer_sex}");
	            pager.addHiddenInputs("province", "${af.map.province}");
	            pager.addHiddenInputs("city", "${af.map.city}");
	            pager.addHiddenInputs("country", "${af.map.country}");
	            pager.addHiddenInputs("town", "${af.map.town}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	  // 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
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
