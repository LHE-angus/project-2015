<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
<title>仓库信息</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<html-el:form action="/manager/JBasePartner">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="right" style="padding-left:5px;">
        	<strong class="fb">供应商名称：</strong>
        </td>
        <td>
          	<html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="40"/>
        </td>
        <td align="right">
        	<strong class="fb">是否停用：</strong>
        </td>
        <td>
          	<html-el:select property="is_del" styleId="is_del" style="width:85px;" onchange="this.form.submit();">
            	<html-el:option value="0">未停用</html-el:option>
            	<html-el:option value="1">已停用</html-el:option>
          	</html-el:select>
        </td>
        <td align="right">
        	<strong class="fb">联系人姓名：</strong>
        </td>
        <td>
        	<html-el:text property="link_name_like" styleClass="webinput" styleId="link_name_like" maxlength="40"/>
        </td>
      </tr>
      <tr>
        <td align="right">
        	<strong class="fb">联系人电话：</strong>
        </td>
        <td>
        	<html-el:text property="link_mobile" styleClass="webinput" styleId="link_mobile" maxlength="40"/>
        </td>
        <td align="right">
        	<strong class="fb">性质：</strong>
        </td>
        <td>
          	<html-el:select property="partner_obj" styleId="partner_obj" style="width:85px;">
            	<html-el:option value="">—请选择—</html-el:option>
            	<html-el:option value="0">个人</html-el:option>
            	<html-el:option value="1">组织/单位</html-el:option>
          	</html-el:select>
        </td>
      	<td align="right">
          	<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
      	</td>
      	<td></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBasePartner.do?method=add&mod_id=${af.map.mod_id}'" />
</div>
<div class="rtabcont1">
<div style="overflow-x: auto">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="3%" nowrap="nowrap">序号</th>
      <th width="10%" nowrap="nowrap">名称</th>
      <th width="8%" nowrap="nowrap">编码</th>
      <th width="4%" nowrap="nowrap">性质</th>
      <th width="8%" nowrap="nowrap">联系人姓名</th>
      <th width="8%" nowrap="nowrap">电话</th>
      <th width="3%" nowrap="nowrap">停用</th>
      <th width="8%" nowrap="nowrap">账户</th>
      <th width="8%" nowrap="nowrap">添加时间</th>
      <th width="6%" nowrap="nowrap">省</th>
      <th width="6%" nowrap="nowrap">市</th>
      <th width="6%" nowrap="nowrap">县</th>
      <th width="6%" nowrap="nowrap">镇</th>
      <th width="8%" nowrap="nowrap">公司地址</th>
      <th width="8%" nowrap="nowrap">操作</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td nowrap="nowrap" align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td nowrap="nowrap" align="left">
        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JBasePartner.do', 'view','&mod_id=${af.map.mod_id}&partner_id=${cur.partner_id}&'+$('#bottomPageForm').serialize())">${cur.partner_name}</span> 
        </td>
        <td nowrap="nowrap" align="left">${cur.partner_sn}</td>
        <td nowrap="nowrap" align="left">
        	<c:if test="${cur.partner_obj eq 1}"><span>组织/单位</span></c:if>
          	<c:if test="${cur.partner_obj eq 0}"><span>个人</span></c:if>
        </td>
        <td nowrap="nowrap" align="center">${cur.link_name}</td>
        <td nowrap="nowrap" align="left">${cur.link_mobile}</td>
        <td nowrap="nowrap" align="center"><c:if test="${cur.is_del eq 0}"><span style="color:green;">否</span></c:if>
          <c:if test="${cur.is_del eq 1}"><span style="color:#f00;">是</span></c:if></td>
        <td nowrap="nowrap" align="center">${empty cur.partner_c_id ? '无' : '有'}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
        <td nowrap="nowrap">${cur.map.PROVINCE }</td>
        <td nowrap="nowrap">${cur.map.CITY }</td>
        <td nowrap="nowrap">${cur.map.COUNTRY }</td>
        <td nowrap="nowrap">${cur.map.TOWN }</td>
        <td nowrap="nowrap">${cur.partner_addr }</td>
        <td align="center" nowrap="nowrap">
        	<c:if test="${cur.is_del eq 0}"> 
        		<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JBasePartner.do', 'edit','&mod_id=${af.map.mod_id}&partner_id=${cur.partner_id}&'+$('#bottomPageForm').serialize())">修改</span> 
        		| <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定停用吗？', 'JBasePartner.do', 'delete','&partner_id=${cur.partner_id}&'+$('#bottomPageForm').serialize())">停用</span> 
        	</c:if>
          	<c:if test="${cur.is_del eq 1}"> 
          		<span class="fblue" style="color:#ccc;">修改</span> 
          		| <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定启用吗？', 'JBasePartner.do', 'reStart','&mod_id=${af.map.mod_id}&partner_id=${cur.partner_id}&'+$('#bottomPageForm').serialize())">启用</span> 
          	</c:if>
      	</td>
      </tr>
      <c:if test="${vs.last}">
        <c:forEach begin="1" end="${9 - vs.index}">
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </c:if>
    </c:forEach>
    <c:if test="${empty entityList}">
      <c:forEach begin="0" end="9">
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  </div>
  <c:if test="${not empty entityList}">
    <div class="rtabcont3">
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBasePartner.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("partner_type", "${af.map.partner_type}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            pager.addHiddenInputs("link_name_like", "${af.map.link_name_like}");
		            pager.addHiddenInputs("link_mobile", "${af.map.link_mobile}");
		            pager.addHiddenInputs("partner_obj", "${af.map.partner_obj}");
		            document.write(pager.toString());
		            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>