<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<c:set var="type_id" value="" />
<c:set var="type_name" value="资讯" />
<c:forEach var="cur" items="${peArticleTypeList}">
	<c:if test="${cur.type_name eq fnx:substringAfterLast_ss(naviString, ' ')}">
		<c:set var="type_id" value="${cur.id}" />
		<c:set var="type_name" value="${cur.type_name}" />
	</c:if>
</c:forEach>
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
    <html-el:form action="/admin/KonkaPeArticleInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="receive_user_type" />
      <html-el:hidden property="public_target" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="20%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">${type_name}标题：</strong>
            <html-el:text property="title" size="20" style="width:90px;" maxlength="10" styleId="title" styleClass="webinput" />
            &nbsp;<strong class="fb">发布时间：</strong>
            <html-el:text property="st_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="en_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            &nbsp;<strong class="fb">信息状态：</strong>
            <html-el:select property="states" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="1">已发布</html-el:option>
              <html-el:option value="0">未发布</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;
            <input type="button" name="" value="搜索" id="btn_submit" class="but1" />
          </td>
        </tr>
      </table>
    </html-el:form>
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td>
      <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaPeArticleInfo.do?method=add&mod_id=${af.map.mod_id}&receive_user_type=${af.map.receive_user_type}&public_target=${af.map.public_target}';" />
      <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.forms[1]);" />
      </td>
     </tr>
     </table>
    </div>
    <div class="rtabcont1">
    <html-el:form action="/admin/KonkaPeArticleInfo">
        <div style="text-align: left">
        <html-el:hidden property="method" value="delete" />
        <html-el:hidden property="receive_user_type" />
        <html-el:hidden property="public_target" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        </div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="5%" align="center" ><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
            <td nowrap="nowrap">${type_name}标题</td>
            <td width="15%">添加人</td>
            <td width="8%" nowrap="nowrap" align="center" >状态</td>
            <td width="5%" nowrap="nowrap" align="center" >排序值</td>
            <td width="10%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">
              <c:if test="${userInfo.id eq cur.add_user_id}">
              	<input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
              <c:if test="${userInfo.id ne cur.add_user_id}">
              	<input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" readonly="readonly" />
              </c:if></td>
              <td align="left">${fn:escapeXml(cur.title)}</td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.states eq 0 }"><span style="color:red;">未发布</span></c:when>
                  <c:when test="${cur.states eq 1 }"><span style="color:green;">已发布</span></c:when>
                </c:choose></td>
              <td align="center" nowrap="nowrap">${cur.order_value}</td>
              <td align="center" nowrap="nowrap">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPeArticleInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
              <c:if test="${userInfo.id eq cur.add_user_id}">
              |
			  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPeArticleInfo.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
			  |
			  <span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaPeArticleInfo.do', 'id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span>
			  </c:if>
			  <c:if test="${userInfo.id ne cur.add_user_id}">
              |
			  <span style="color:#ccc;">修改</span>
			  |
			  <span style="color:#ccc;">删除</span>
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
            </tr>
          </c:forEach>
        </table>
      </html-el:form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPeArticleInfo.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
			            pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
			            pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
			            pager.addHiddenInputs("states", "${af.map.states}");
			            pager.addHiddenInputs("receive_user_type", "${af.map.receive_user_type}");
			            pager.addHiddenInputs("public_target", "${af.map.public_target}");
			            document.write(pager.toString());
			   </script></td>
          </tr>
        </table>
      </form>
    </div>
    <div class="clear"></div>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("发布时间结束日期不得小于开始日期,请重新选择!");
				return false;
			}
		}
		this.form.submit();
	});
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
