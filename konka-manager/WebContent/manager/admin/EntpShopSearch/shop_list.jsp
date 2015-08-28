<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:if test="${have_data eq '1'}">
    <html-el:form action="/admin/EntpShopSearch">
                <html-el:hidden property="method" value="add" />
                <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
                <html-el:hidden property="tree_param" value="${tree_param}" />
	            <html-el:hidden property="province" value="${af.map.province}" />
	            <html-el:hidden property="city" value="${af.map.city}" />
	            <html-el:hidden property="country" value="${af.map.country}" />
	            <html-el:hidden property="town" value="${af.map.town}" />
	            <html-el:hidden property="shop_name_like" value="${af.map.shop_name_like}" />
	            <html-el:hidden property="cls_id" value="${af.map.cls_id}" />
	            <html-el:hidden property="cls_ids_select" value="${af.map.cls_ids_select}" />
	            <html-el:hidden property="shop_types_str" value="${shop_types_str}" />
	            <html-el:hidden property="more" value="${af.map.more}" />
	            <html-el:hidden property="year" value="${af.map.year}" />
	            <html-el:hidden property="month" value="${af.map.month}" />
	            <html-el:hidden property="pd_type" value="${af.map.pd_type}" />
	            <html-el:hidden property="busi_total1" value="${af.map.busi_total1}" />
	            <html-el:hidden property="busi_proportion1" value="${af.map.busi_proportion1}" />
	            <html-el:hidden property="brand_id" value="${af.map.brand_id}" />
	            <html-el:hidden property="brand_name" value="${af.map.brand_name}" />
	            <html-el:hidden property="busi_total2" value="${af.map.busi_total2}" />
	            <html-el:hidden property="busi_proportion2" value="${af.map.busi_proportion2}" />
	            <html-el:hidden property="brand_ids_select" value="${brand_ids_select}" />
	            <html-el:hidden property="page_type" value="${af.map.page_type}" />
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="10" style="padding-left:10px;">
                  <input name="button" type="button" value="批量开拓" onclick="ajaxShopDevelop('',1);" />
                  <!--<input name="button" type="button" value="全部开拓" onclick="allSubmit(this.form);" /> -->
                 </td>
                </tr>
	            </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
                  <tr class="tabtt1">
                    <td align="center" width="4%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
                    <td nowrap="nowrap"><font class="blue">网点名称</font></td>
                    <td align="center" width="100" nowrap="nowrap" ><font class="blue">联系人</font></td>
                    <td align="center" width="100" nowrap="nowrap" ><font class="blue">联系电话</font></td>
                    <td align="center" width="160" nowrap="nowrap" ><font class="blue">操作</font></td>
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
                      <td align="left"><a href="<c:url value='/manager/admin/EntpShopSearch.do?method=view&shop_id=${cur.shop_id}&mod_id=${af.map.mod_id}'/>" style="text-decoration:none;"><font class="blue12px">${cur.shop_name}</font></a></td>
                      <td align="center">${cur.link_user}</td>
                      <td align="center">${cur.link_phone}</td>
                       <td align="center">
                         <span style="cursor:pointer;"  class="fblue" onclick="sellAnalysisSubmit('bottomPageForm','page_id=EntpShopSearch&shop_id=${cur.shop_id}');">经营情况</span>
                        |
                      <c:if test="${empty cur.map.shop_develop_status}">
                         <span id="span_${cur.shop_id}" style="cursor:pointer;" class="fblue" onclick="ajaxShopDevelop('${cur.shop_id}',0)">开拓</span>                      
                      </c:if>
                      <c:if test="${cur.map.shop_develop_status eq 0}">
                         <span style="cursor:pointer;color:#CCCCCC;" class="fblue">待开拓</span>
                      </c:if>
                      <c:if test="${cur.map.shop_develop_status eq 1}">
                         <span style="cursor:pointer;color:#CCCCCC;" class="fblue">开拓中</span>
                      </c:if>
                      <c:if test="${cur.map.shop_develop_status eq 2}">
                         <span style="cursor:pointer;color:#CCCCCC;" class="fblue">已开拓</span>
                      </c:if>
                       <!-- 
                       <span style="cursor:pointer;" onclick="doNeedMethod(null, 'EntpShopSearch.do', 'add','shop_id=${cur.shop_id}&'+$('#bottomPageForm').serialize())">开拓</span> |
                       <span><a href="<c:url value='/EntpShopMaps.do?shop_id=${cur.shop_id}'/>" style="text-decoration:none;color:#000;" target="_blank">周边</a></span>
                       --> 
                       </td>
                    </tr>
                  </c:forEach>
                </table></html-el:form>
                    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EntpShopSearch.do">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
                        <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("province", "${af.map.province}");
			            pager.addHiddenInputs("city", "${af.map.city}");
			            pager.addHiddenInputs("country", "${af.map.country}");
			            pager.addHiddenInputs("town", "${af.map.town}");
			            pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
			            pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
			            pager.addHiddenInputs("cls_ids_select", "${af.map.cls_ids_select}");
			            pager.addHiddenInputs("shop_types_str", "${shop_types_str}");
			            pager.addHiddenInputs("more", "${af.map.more}");
			            pager.addHiddenInputs("year", "${af.map.year}");
			            pager.addHiddenInputs("month", "${af.map.month}");
			            pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
			            pager.addHiddenInputs("busi_total1", "${af.map.busi_total1}");
			            pager.addHiddenInputs("busi_proportion1", "${af.map.busi_proportion1}");
			            pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
			            pager.addHiddenInputs("brand_name", "${af.map.brand_name}");
			            pager.addHiddenInputs("busi_total2", "${af.map.busi_total2}");
			            pager.addHiddenInputs("busi_proportion2", "${af.map.busi_proportion2}");
			            pager.addHiddenInputs("brand_ids_select", "${brand_ids_select}");
			            pager.addHiddenInputs("page_type", "${af.map.page_type}");
			            document.write(pager.toString());
			   		</script></td>
                    </tr>
              </table>
           </form>
         </c:if>
        <c:if test="${have_data eq '0'}">
            <div align="center" style="padding-top:40px;color:#f00;">对不起，没有查询出符合条件的记录集！</div>
        </c:if>