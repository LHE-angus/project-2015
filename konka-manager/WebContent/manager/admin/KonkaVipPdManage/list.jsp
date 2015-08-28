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
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form  action="/admin/KonkaVipPdManage.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">分公司：</strong>
          	 <html-el:select property="dept_id" styleId="dept_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select> </td>
          <td>
          	<strong class="fb">商品型号：</strong>
          	<html-el:text property="pd_code" size="20" maxlength="20" styleId="pd_code" />
          </td>
          <td>
          <strong class="fb">商品类型：</strong>
          <html-el:select property="pd_type"  styleId="pd_type">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="1">新品首发</html-el:option>
	    		<html-el:option value="2">精品推荐</html-el:option>
	    		<html-el:option value="3">热销商品</html-el:option>
	    		<html-el:option value="4">特惠商品</html-el:option>
          </html-el:select>
          </td>
          </tr>
          <tr>
           <td width="15"></td>
           <td >
            <strong class="fb">商品类型：</strong>
    		
    		<html-el:select property="is_locked"  styleId="is_locked">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="0">锁定</html-el:option>
	    		<html-el:option value="1">未锁定</html-el:option>
	    	</html-el:select>
	    	</td>
	    	<td > 
           <strong class="fb">是否展示：</strong>
    		
    		<html-el:select property="is_hidden"  styleId="is_hidden">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="0">展示</html-el:option>
	    		<html-el:option value="1">未展示</html-el:option>
	    		
	    		
	    	</html-el:select>
	    	</td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td></tr>
          <tr>
          <td width="15"></td>
          <td colspan="4">
         <input class="but2" type="button" name="Submit4" id="btn_submit" value="添加"
           onclick="location.href='${ctx}/manager/admin/KonkaVipPdManage.do?method=add&mod_id=${af.map.mod_id}'"/>
            <input class="but3" type="button" name=button3 id="button3" style="width:80px;" value="批量删" onClick="confirmOperateAll('确认删除吗?', 'deleteAll',this.form);" />
           </td>
        </tr>
      </table>
   
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
           <td width="250px" rowspan="5" >
           <image style="width:250px;height:140px; no_repeat;" src="${ctx}/${cur.map.save_path}"></image>
          </td>
           </tr>
           <tr><td nowrap="nowrap" width="10%" align="right" >商品型号:</td><td align="left" nowrap="nowrap">${cur.pd_code}</td>
            <td nowrap="nowrap" width="10%" align="right" >上架分公司:</td><td align="left" nowrap="nowrap">${cur.fgs_name}</td></tr>
           <tr>  <td  nowrap="nowrap" width="10%" align="right" >添加人:</td> <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
             <td  nowrap="nowrap" width="10%" align="right" >添加时间:</td> <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td></tr>
           <tr> 
           <td nowrap="nowrap" width="10%" align="right" >商品类型:</td>
			<td>
			<c:choose>
        	<c:when test="${cur.pd_type eq 1}">新品首发</c:when>
        	<c:when test="${cur.pd_type eq 2}">精品推荐</c:when>
        	<c:when test="${cur.pd_type eq 3}">热销商品</c:when>
        	<c:when test="${cur.pd_type eq 4}">特惠商品</c:when>
       	    </c:choose>
			</td>           
            <td  nowrap="nowrap" width="10%" align="right" >商品说明:</td> <td  align="left" nowrap="nowrap">${cur.pd_desc}</td></tr>
           <tr><td colspan="4" align="right">
           <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
           <label>
        <c:if test="${!_flag}">
        <c:if test="${cur.is_hidden eq 1}">
          <input class="but2" type="button" id="btn_submit1" value="展示"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_hidden_flag=${cur.is_hidden}&'+$('#bottomPageForm').serialize());"/>&nbsp;
         </c:if>
           <c:if test="${cur.is_hidden eq 0}">
           <input class="but3" type="button" id="btn_submit2" value="未展示"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_hidden_flag=${cur.is_hidden}&'+$('#bottomPageForm').serialize());"/>&nbsp;
           </c:if>
            <c:if test="${cur.is_locked eq 0}">
           <input class="but2" type="button"id="btn_submit3" value="锁定"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_locked_flag=${cur.is_locked}&'+$('#bottomPageForm').serialize());"/>&nbsp;
          </c:if>
          <c:if test="${cur.is_locked eq 1}">
           <input class="but3" type="button" id="btn_submit4" value="未锁定"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_locked_flag=${cur.is_locked}&'+$('#bottomPageForm').serialize());"/>&nbsp;
           </c:if>
           <input class="but4" type="button" id="btn_submit5" value="修改"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','edit','id=${cur.id}&mod_id=${af.map.mod_id}&pic_id=${cur.map.pic_id}&'+$('#bottomPageForm').serialize());"/>&nbsp;
          <input class="but3" type="button"  id="btn_submit6" value="删除"
          onclick="doNeedMethod('确定删除该条记录？','../admin/KonkaVipPdManage.do','delete','id=${cur.id}&mod_id=${af.map.mod_id}&pic_id=${cur.map.pic_id}&'+$('#bottomPageForm').serialize());" />
        </c:if>
         <c:if test="${_flag}">
         <c:if test="${cur.is_locked eq 0}">
         <c:if test="${cur.is_hidden eq 1}">
          <input class="but4" type="button" name="Submit4" id="btn_submit7" value="展示"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_hidden_flag=${cur.is_hidden}&'+$('#bottomPageForm').serialize());"/>&nbsp;
          </c:if>
           <c:if test="${cur.is_hidden eq 0}">
           <input class="but3" type="button" name="Submit4" id="btn_submit8" value="未展示"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','show','id=${cur.id}&mod_id=${af.map.mod_id}&is_hidden_flag=${cur.is_hidden}&'+$('#bottomPageForm').serialize());"/>&nbsp;
          </c:if>
           <input class="but4" type="button" name="Submit4" id="btn_submit9" value="修改"
           onclick="doNeedMethod(null,'../admin/KonkaVipPdManage.do','edit','id=${cur.id}&mod_id=${af.map.mod_id}&pic_id=${cur.map.pic_id}&'+$('#bottomPageForm').serialize());"/>&nbsp;
          <input class="but3" type="button" name="Submit4" id="btn_submit10" value="删除"
          onclick="doNeedMethod('确定删除该条记录？','../admin/KonkaVipPdManage.do','delete','id=${cur.id}&mod_id=${af.map.mod_id}&pic_id=${cur.map.pic_id}&'+$('#bottomPageForm').serialize());" />
        </c:if>
        <c:if test="${cur.is_locked eq 1}">
          <font style="color:red">该商品的信息已被总部锁定，分公司无权限对其进行任何操作！</font>
         </c:if>
          </c:if>
          
          </label>
          </td></tr></div>
        </c:forEach>
      </table>
      </div>
       </html-el:form>
       </div>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaVipPdManage.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("pd_code", "${af.map.pd_code}");
			pager.addHiddenInputs("pd_tpe", "${af.map.pd_tpe}");
			pager.addHiddenInputs("is_locked", "${af.map.is_locked}");
			pager.addHiddenInputs("is_hidden", "${af.map.is_hidden}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    <br />
    
 
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>