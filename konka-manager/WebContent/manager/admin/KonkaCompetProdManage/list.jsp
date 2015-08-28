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
<body style="font-family:Microsoft Yahei,'宋体';">
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
    <html-el:form action="/admin/KonkaCompetProdManage">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      	<tr>
      		<td width="15"></td>
          	<td>
          		<strong class="fb">品牌名称：</strong>
            	<html-el:select property="brand_id" styleId="brand_id">
            		<html-el:option value="">=请选择=</html-el:option>
            		<c:forEach items="${pdBrandList}" var="cur">
            			<html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
            		</c:forEach>
            	</html-el:select>
            </td>
            <td>
            	<strong class="fb">尺寸：</strong>
            	<html-el:text property="screen_size_like" styleId="screen_size_like" />
            </td>
            <td>
            	<strong class="fb">智能电视：</strong>
            	<html-el:select property="is_smart_tv" styleId="is_smart_tv">
            		<html-el:option value="">=请选择=</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            	</html-el:select>
            </td>
            <td>
            	<strong class="fb">三维电视：</strong>
            	<html-el:select property="d_tv" styleId="d_tv">
            		<html-el:option value="">=请选择=</html-el:option>
            		<html-el:option value="0">2D</html-el:option>
            		<html-el:option value="1">3D</html-el:option>
            	</html-el:select>
            </td>
            <td>
            	<strong class="fb">是否删除：</strong>
            	<html-el:select property="is_del" styleId="is_del">
            		<html-el:option value="">=请选择=</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            	</html-el:select>
            </td>
            <td><input type="hidden" id='excel_all' name="excel_all" value="0"/></td>
		</tr>
		<tr>
			<td width="15"></td>
            <td>
            	<strong class="fb">背光源：</strong>
            	<html-el:select property="back_light" styleId="back_light">
            		<html-el:option value="">=请选择=</html-el:option>
            		<html-el:option value="0">LED</html-el:option>
            		<html-el:option value="1">CCFL</html-el:option>
            		<html-el:option value="9">其他</html-el:option>
            	</html-el:select>
            </td>
            <td>
            	<strong class="fb">机型：</strong>
            	<html-el:text property="md_name_like" styleId="md_name_like" />
            </td>
           	<td colspan="2">
           		<strong class="fb">参考价格：</strong>
            	<html-el:text property="price_ref_ge" styleId="price_ref_ge" maxlength="12" size="12" />
            	-
            	<html-el:text property="price_ref_le" styleId="price_ref_le" maxlength="12" size="12" />
            </td>
            <td colspan="2">
           		<strong class="fb" style="align:center">规格段：</strong>
           		<html-el:select property="Par_type_id" styleId="Par_type_id">
            		<html-el:option value="">=请选择=</html-el:option>
            		<c:forEach items="${eList}" var="cur">
            			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
            		</c:forEach>
            	</html-el:select>
            </td>
          	<td><input class="but1" type="submit" name="Submit" value="搜索"/></td>
      	</tr>
      </table>
  	</html-el:form>
  </div>
    <form id="listForm" name="listForm" method="post" action="KonkaCompetProdManage.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <div class="rtabcont1">
	    <%@ include file="/commons/pages/messages.jsp"%>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>
			    <logic-el:match name="popedom" value="+1+">
			      <input class="but3" type="button" name="delete" id="delete" value="删除" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
			      <input class="but10" type="button" name="brand" value="品牌" onclick="location.href='KonkaMobilePdBrand.do?mod_id=903240&tree_param=${tree_param}'" />
			      <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaCompetProdManage.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
			      <input class="but_excel" type="button" value="导入" onclick="location.href='KonkaCompetProdManage.do?method=importMd&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
			      <input class="but_excel" type="button" id="output" name="output" value="导出"/>
			    </logic-el:match>
			</td>
		  </tr>
   		</table>		
  	  </div>
  	  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      	<tr class="tabtt1">
          <td width="3%" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td> 
          <td width="10%" nowrap="nowrap" align="left">品牌</td>
          <td width="6%" nowrap="nowrap" align="right">尺寸</td>
          <td width="6%" nowrap="nowrap" align="center">规格段</td>
          <td width="10%" nowrap="nowrap" align="left">智能电视</td>
          <td width="10%" nowrap="nowrap" align="left">三维电视</td>
          <td width="10%" nowrap="nowrap" align="left">背光源</td>
          <td width="21%" nowrap="nowrap" align="left">机型</td>
          <td width="10%" nowrap="nowrap" align="right">参考价格（元）</td>
          <td width="8%" nowrap="nowrap" align="right">排序值</td>
          <td width="12%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
          	<tr>
          	  <td align="center" nowrap="nowrap"><!--${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}-->
          	  	<c:if test="${cur.is_del eq 0}"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></c:if>
              	<c:if test="${cur.is_del eq 1}"><input name="pks" type="checkbox" disabled="disabled" /></c:if>
          	  </td>
          	  <td align="left">${cur.brand_name}</td>
          	  <td align="right">${cur.screen_size}</td>
          	  <td align="center">${cur.map.par_type_name}</td>
          	  <td align="left">
          	  	<c:if test="${cur.is_smart_tv eq 0}">否</c:if>
          	  	<c:if test="${cur.is_smart_tv eq 1}">是</c:if>
          	  </td>
          	  <td align="left">
          	  	<c:if test="${cur.d_tv eq 0}">2D</c:if>
          	  	<c:if test="${cur.d_tv eq 1}">3D</c:if>
          	  </td>
          	  <td align="left">
          	  	<c:if test="${cur.back_light eq 0}">LED</c:if>
          	  	<c:if test="${cur.back_light eq 1}">CCFL</c:if>
          	  	<c:if test="${cur.back_light eq 9}">其他</c:if>
          	  </td>
          	  <td align="left">${cur.md_name}</td>
          	  <td align="right"><fmt:formatNumber value="${cur.ref_price}" pattern="#,###.00" /></td>
          	  <td align="right">${cur.order_value}</td>
          	  <td align="center" nowrap="nowrap">
          	  	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaCompetProdManage.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span>
          	  	<c:if test="${cur.is_del eq 0}">
	              	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaCompetProdManage.do', 'edit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改</span>
	              	<span style="cursor: pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaCompetProdManage.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">无效</span>
          	  	</c:if>
          	  	<c:if test="${cur.is_del eq 1}">
          	  		<span style="cursor: pointer;color:#333333;">修改</span>
          	  		<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaCompetProdManage.do', 'updateDel','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">有效</span>
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
              </tr>
        	</c:forEach>
          </c:if>
      	</tbody>
      </table>
      </div>
    </form>
  	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaCompetProdManage.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("screen_size_like", "${af.map.screen_size_like}");
				pager.addHiddenInputs("price_ref_ge", "${af.map.price_ref_ge}");
				pager.addHiddenInputs("price_ref_le", "${af.map.price_ref_le}");
				pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
				pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
				pager.addHiddenInputs("is_smart_tv", "${af.map.is_smart_tv}");
				pager.addHiddenInputs("d_tv", "${af.map.d_tv}");
				pager.addHiddenInputs("back_light", "${af.map.back_light}");
				pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");
				document.write(pager.toString());
			  </script> 
            </div></td>
        </tr>
      </table>
    </form>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#output").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
			$("#bottomPageForm").submit();	
		}
	});
});



//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>