<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="模块授权" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.alt {
	background-color:#eee;
}
.over {
	background-color:#F4FBFF;
}
.rowlight td {padding:0px 3px;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="200" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/SetModPopedom.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="role_id" />
      <html-el:hidden property="user_id" />
      <html-el:hidden property="url" />
      <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rowlight" style="border-collapse:collapse;border:1px solid #eee;">
        <tr>
          <th width="5%">层级</th>
          <th width="20%">栏目授权</th>
          <th width="25%">权限设置</th>
          <th width="10%">选择</th>
          <th>说明</th>
        </tr>
        <c:forEach var="cur" items="${sysModuleList}" varStatus="vs">
          <c:set var="isChecked" value="${false}" />
          <c:if test="${cur.map.level eq 1}">
            <tr>
              <td colspan="5" height="25" align="right"><span style="font-weight:800;">${cur.mod_name}</span></td>
            </tr>
          </c:if>
          <c:if var="is_dir" test="${empty cur.mod_url or cur.map.level eq 1}">
            <tr>
              <td style="text-align:center;">${cur.map.level}</td>
              <td colspan="2" nowrap="nowrap"><c:forEach begin="1" end="${cur.map.level - 1}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                <strong>${cur.mod_name}</strong></td>
              <td nowrap="nowrap" style="text-align:center;"><label for="checkbox_${cur.mod_id}">
                  <input type="checkbox" id="checkbox_${cur.mod_id}" onClick="selectAllChildren('checkbox_${cur.mod_id}', this);" />
                  全选择</label></td>
              <td><span style="color:#999;">${cur.mod_desc}</span></td>
            </tr>
          </c:if>
          <c:if test="${not is_dir}">
          <tr>
            <td style="text-align:center;">${cur.map.level}</td>
            <td nowrap="nowrap">
                <c:forEach begin="1" end="${cur.map.level - 1}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                ${cur.mod_name}
            </td>
            <td nowrap="nowrap">
                <c:forEach var="basePopedom" items="${cur.basePopedomList}">
                  <logic-el:present name="mod_popedom_${cur.mod_id}">
                    <c:set var="isChecked" value="${false}" />
                    <logic-el:iterate id="selectedSxModPopedom" name="mod_popedom_${cur.mod_id}">
                      <c:if var="isChecked" test="${(selectedSxModPopedom eq basePopedom.ppdm_code) or isChecked}" />
                    </logic-el:iterate>
                  </logic-el:present>
                  <!--零售查询的添加权限，仅总部管理员可分配-->
                  <c:if test="${cur.id eq 211500 and basePopedom.ppdm_code eq 1 and role_id_eq_10 eq 1}">
                    <c:if test="${isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onClick="checkFirst('checkbox_${cur.mod_id}', this);" checked="checked"/>
                    </c:if>
                    <c:if test="${not isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onClick="checkFirst('checkbox_${cur.mod_id}', this);" />
                    </c:if>
                  </c:if>
                  <c:if test="${cur.id eq 211500 and basePopedom.ppdm_code eq 1 and role_id_eq_10 eq 0}">
                    <c:if test="${isChecked}">
                      <input type="checkbox" name="checkbox_${cur.mod_id}" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" value="${basePopedom.ppdm_code}" style="display: none" checked="checked"/>
                      <input type="checkbox" disabled="disabled"  <c:if test="${basePopedom.ppdm_code eq 128 and role_id_eq_10 eq 1 }">style="display:none"</c:if> checked="checked"/>
                    </c:if>
                    <c:if test="${not isChecked}">
                      <input type="checkbox" disabled="disabled" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" <c:if test="${basePopedom.ppdm_code eq 128 and role_id_eq_10 eq 1 }">style="display:none"</c:if> name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onClick="checkFirst('checkbox_${cur.mod_id}', this);" />
                    </c:if>
                  </c:if>
                  <c:if test="${(cur.id != 211500) or (cur.id eq 211500 and basePopedom.ppdm_code != 1)}">
                    <c:if test="${isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" <c:if test="${basePopedom.ppdm_code eq 128 and role_id_eq_10 eq 0 }">style="display:none"</c:if> name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onClick="checkFirst('checkbox_${cur.mod_id}', this);" checked="checked"/>
                    </c:if>
                    <c:if test="${not isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" <c:if test="${basePopedom.ppdm_code eq 128 and role_id_eq_10 eq 0 }">style="display:none"</c:if> name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onClick="checkFirst('checkbox_${cur.mod_id}', this);" />
                    </c:if>
                  </c:if>
                  <label for="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}"  <c:if test="${basePopedom.ppdm_code eq 128 and role_id_eq_10 eq 0 }">style="display:none"</c:if>>
                    <c:out value="${basePopedom.ppdm_desc}" />
                  </label>
                </c:forEach>
            </td>
            <td style="text-align:center;"><input type="checkbox" id="checkbox_${cur.mod_id}" onClick="checkRow('checkbox_${cur.mod_id}', this);" />
              <label for="checkbox_${cur.mod_id}"> 行选择 </label></td>
            <td><span style="color:#999;">${cur.mod_desc}</span></td>
          </tr>
            </c:if>
        </c:forEach>
        <tr>
          <td colspan="5">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="4"><input type="checkbox" id="checkbox_checkAll" onClick="checkAll(this);" />
            <label for="checkbox_checkAll"> 选择全部 </label>
            <label>
              <input class="but4" type="submit" name="Submit4" id="btn_submit" value="提交" />
              <input class="but3" type="button" name="Submit5" value="重置" onClick="this.form.reset();return false;" />
              <input class="but5" type="button" name="Submit5" value="返回" onClick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".rowlight tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".rowlight tr:even").addClass("alt");
})

function checkAll(e) {
	for (var i = 01; i < e.form.elements.length; i++) {
		if (e.form.elements[i].type == "checkbox") {
			if(e.form.elements[i].style.display==''){
				e.form.elements[i].checked = e.checked;
			}
		}
	}
}

function checkRow(name, cb) {
	var e = document.getElementsByName(name);
	for(var i = 0; i < e.length; i++) {
		if(e[i].style.display==''){ 
			e[i].checked = cb.checked;
		} 
	}
}

function selectAllChildren(prefix_id, e) {
	
	$("input[type='checkbox'][id^=" + prefix_id.replace(/(0*$)/g, '') + "]").each(function(){
		if(this.style.display==''){ 
			this.checked = e.checked;
		}
	});
}

function checkFirst(name, cb) {
	return;
//	var e = document.getElementsByName(name);
//	for(var i = 0; i < e.length; i++) {
//		if (e[i].checked == true) {
//			e[0].checked = true;
//			break;
//		}
//	}
}

$("#btn_submit").click(function(){
     $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
     this.form.submit();
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
