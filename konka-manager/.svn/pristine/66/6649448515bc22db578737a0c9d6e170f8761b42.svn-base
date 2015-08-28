<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:if test="${have_data eq '1'}">
    <html-el:form action="/admin/AgentsList">
                <html-el:hidden property="method" value="save" />
                <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
                <html-el:hidden property="tree_param" value="${tree_param}" />
	            <html-el:hidden property="province" value="${af.map.province}" />
	            <html-el:hidden property="city" value="${af.map.city}" />
	            <html-el:hidden property="country" value="${af.map.country}" />
	            <html-el:hidden property="town" value="${af.map.town}" />	            
                <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
                <html-el:hidden property="r3_id" value="${af.map.r3_id}" />
	            
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="10" style="padding-left:10px;">
                  <input name="button" type="button" value="批量开拓" onclick="ajaxShopDevelop('',1);" />
                 </td>
                </tr>
	            </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
                  <tr class="tabtt1">
                    <td align="center" width="25" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
                    <td nowrap="nowrap"><font class="blue">网点名称</font></td>
                    <td align="center" width="100" nowrap="nowrap" ><font class="blue">联系人</font></td>
                    <td align="center" width="80" nowrap="nowrap" ><font class="blue">联系电话</font></td>
                    <td align="center" width="120" nowrap="nowrap" ><font class="blue">操作</font></td>
                  </tr>
                  <c:forEach var="cur" items="${entityList}" varStatus="vs">
                    <tr id="tr_${cur.shop_id}">
                      <td align="center">     
                      <c:if test="${empty cur.map.shop_develop_status}">
                       <input name="pks" type="checkbox" id="pks_${cur.shop_id}" value="${cur.shop_id}" />
                      </c:if>
                      <c:if test="${not empty cur.map.shop_develop_status}">
                       <input name="pks" type="checkbox" id="pks_${cur.shop_id}" value="${cur.shop_id}" disabled="disabled"/>
                      </c:if>
                      </td>
                      <td nowrap="nowrap" align="left">
                      <a href="<c:url value='/manager/admin/EntpShopSearch5W.do?method=view&shop_id=${cur.shop_id}&mod_id=${af.map.mod_id}'/>" style="text-decoration:none;">
                      <font class="blue12px">${cur.shop_name}</font></a></td>
                      <td align="center">${cur.link_user}</td>
                      <td nowrap="nowrap" align="center">${cur.link_phone}</td>
                      <td align="center">
                      <c:if test="${empty cur.map.shop_develop_status}">
                         <span id="span_${cur.shop_id}" style="cursor:pointer;" class="fblue" onclick="ajaxShopDevelop('${cur.shop_id}',0)">开拓</span>                      
                      </c:if>
                      <c:if test="${cur.map.shop_develop_status eq 0}">
                         <span style="cursor:pointer;color:#CCCCCC;" class="fblue">待开拓</span>
                      </c:if>
                      <c:if test="${cur.map.shop_develop_status eq 1}">
                         <span style="cursor:pointer;color:#CCCCCC;" class="fblue">开拓中</span>
                      </c:if>
                       </td>
                    </tr>
                  </c:forEach>
            </table>
        </html-el:form>
        <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="AgentsList.do">
                  <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="40" align="center">
                       <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "searchKonkaShop");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("province", "${af.map.province}");
			            pager.addHiddenInputs("city", "${af.map.city}");
			            pager.addHiddenInputs("country", "${af.map.country}");
			            pager.addHiddenInputs("town", "${af.map.town}");
			            pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
			            pager.addHiddenInputs("shop_id", "${af.map.shop_id}");
			            pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
			            document.write(pager.toString());
	   		           </script></td>
                    </tr>
              </table>
           </form>
         </c:if>
        <c:if test="${have_data eq '0'}">
            <div align="center" style="padding-top:40px;color:#f00;">对不起，没有查询出符合条件的记录集！</div>
        </c:if>