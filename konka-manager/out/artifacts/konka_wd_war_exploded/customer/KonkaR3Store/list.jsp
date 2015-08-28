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
<style>
.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div class="rtabcont1"> 
  <html-el:form action="/manager/KonkaR3Store">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">门店名称：</strong>
        </td>
        <td>
          	<html-el:text property="store_name_like" styleId="store_name_like" maxlength="40" size="18" />
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">门店编号：</strong>
        </td>
        <td>
          	<html-el:text property="store_id" styleId="store_id" maxlength="40" size="18" />
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">业务员：</strong>
        </td>
        <td>
          	<html-el:text property="ywy_name_like" maxlength="40" size="18" />
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">促销员：</strong>
        </td>
        <td>
          	<html-el:text property="zxy_name_like" maxlength="40" size="18" />
        </td>
      </tr>
      <tr>
	  	<td nowrap="nowrap" align="right">
	    	<strong class="fb">门店联系人：</strong>
	    </td>
	    <td>
	        <html-el:text property="link_man_like" maxlength="40" size="18" />
	    </td>
	  	<td nowrap="nowrap" align="right">
	    	<strong class="fb">门店电话：</strong>
	    </td>
	    <td>
	        <html-el:text property="linka_tel" maxlength="40" size="18" />
	    </td>
        <td  align="right">
            <strong class="fb">是否停用：</strong>
        </td>
        <td>
        	<html-el:select property="is_del" styleId="is_del" style="width:70px;" onchange="this.form.submit();">
            	<html-el:option value="0">未停用</html-el:option>
               	<html-el:option value="1">已停用</html-el:option>
           	</html-el:select>
        </td>
        <td  align="right">
            <strong class="fb">确认状态：</strong>
        </td>
        <td>
        	<html-el:select property="is_sure" styleId="is_sure" style="width:110px">
	         	<html-el:option value="">-请选择-</html-el:option>
	         	<html-el:option value="1">待确认</html-el:option>
	         	<html-el:option value="2">已确认</html-el:option>
	        </html-el:select>
        </td>
      </tr>
      <tr>
      	<td colspan="8" align="right" style="padding-right: 10px">
        	<input class="but1" type="submit" name="Submit" value="搜索" />
      	</td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <!-- <div class="rtabcont1">
  	<input type="button" id="export_22" value="导出" class="but_excel" />
  </div> -->
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="5%" align="center" nowrap="nowrap">分公司</td>
          <td align="center" nowrap="nowrap">门店名称</td>
          <td align="center" nowrap="nowrap">门店编号</td>
          <td width="10%" align="center" nowrap="nowrap">业务员</td>
          <td width="5%" align="center" nowrap="nowrap">促销员</td>
          <td width="5%" align="center" nowrap="nowrap">门店联系人</td>
          <td align="center" nowrap="nowrap">门店电话</td>
          <td width="5%" align="center" nowrap="nowrap">年销售额(万元)</td>
          <td width="5%" align="center" nowrap="nowrap">总营业面积(平米)</td>
          <td width="5%" align="center" nowrap="nowrap">彩电营业面积(平米)</td>
          <td width="5%" align="center" nowrap="nowrap">仓库名</td>
          <td width="5%" align="center" nowrap="nowrap">仓库送达方编码</td>
          <td width="5%" align="center" nowrap="nowrap">省</td>
          <td width="5%" align="center" nowrap="nowrap">市</td>
          <td width="5%" align="center" nowrap="nowrap">县</td>
          <td width="5%" align="center" nowrap="nowrap">镇</td>
          <td width="5%" align="center" nowrap="nowrap">地址</td>
          <td width="5%" align="center" nowrap="nowrap">状态</td>
          <td width="5%" align="center" nowrap="nowrap">是否确认</td>
          <td width="5%" align="center" nowrap="nowrap">创建人</td>
          <td width="5%" align="center" nowrap="nowrap">创建日期</td>
          <td width="5%" align="center" nowrap="nowrap">维护人</td>
          <td width="5%" align="center" nowrap="nowrap">维护日期</td>
          <td width="5%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.DEPT_NAME)}</font></td>
              <td align="left" nowrap="nowrap">
              	<span title="点击可查看该门店详情" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/KonkaR3Store.do', 'view','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.STORE_NAME}</font></span>
              </td>
              <td align="center" nowrap="nowrap">
              	<span title="点击可查看该门店详情" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/KonkaR3Store.do', 'view','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.STORE_ID}</font></span>
              </td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.YWY_NAME)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CXY_NAME)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${cur.LINK_MAN}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.LINKA_TEL)}</font></td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.YEAR_SALE_COUNT)}</font></td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.BUSINESS_AREA)}</font></td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.TV_BUSINESS_AREA)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CK_NAME)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.MF_SN)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.PROVINCE)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CITY)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.COUNTRY)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.TOWN)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.ADDR)}</font></td>
              <td align="center" nowrap="nowrap">
              	<font class="blue12px">
              		<c:if test="${cur.IS_DEL eq 0 }">启用</c:if>
              		<c:if test="${cur.IS_DEL eq 1 }">停用</c:if>
              	</font>
              </td>
              <td align="center" nowrap="nowrap">
              	<font class="blue12px">
              		<c:choose>
						<c:when test="${cur.STATUS eq '0' }">待确认</c:when>
						<c:when test="${cur.STATUS eq '2' }">已确认</c:when>
						<c:otherwise>已确认</c:otherwise>
					</c:choose>
              	</font>
              </td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CREATE_NAME)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.ADD_DATE}" pattern="yyyy-MM-dd" /></font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.MODIFY_NAME)}</font></td>
              <td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.MODIFY_DATE}" pattern="yyyy-MM-dd" /></font></td>
              <td align="center" nowrap="nowrap">
			  	<c:if test="${cur.IS_DEL eq 0 }">
              		<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/KonkaR3Store.do', 'edit','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">修改</font></span>
			  		<c:if test="${cur.STATUS eq '0' }"><span class="fblue" style="color:#ccc;">停用</span></c:if>
			  		<c:if test="${cur.STATUS ne '0' }"><span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.STORE_ID}','stop');">停用</span></c:if>
			  	</c:if>
			  	<c:if test="${cur.IS_DEL eq 1 }">
              		<span class="fblue" style="color:#ccc;">修改</span>
              		<c:if test="${cur.STATUS eq '0' }"><span class="fblue" style="color:#ccc;">启用</span></c:if>
              		<c:if test="${cur.STATUS ne '0' }"><span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.STORE_ID}','start');">启用</span></c:if>
			  		
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
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3Store.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
		            pager.addHiddenInputs("store_id", "${af.map.store_id}");
		            pager.addHiddenInputs("ywy_name_like", "${af.map.ywy_name_like}");
		            pager.addHiddenInputs("zxy_name_like", "${af.map.zxy_name_like}");
		            pager.addHiddenInputs("link_man_like", "${af.map.link_man_like}");
		            pager.addHiddenInputs("linka_tel", "${af.map.linka_tel}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            pager.addHiddenInputs("is_sure", "${af.map.is_sure}");
		            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
		            document.write(pager.toString());
		   </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">
                                          
	function stopOrstart(id,flag){
		$.dialog({
			title:  "停用/启用申请",
			width:  570,
			height: 200,
	        lock:true ,
			content:"url:../AuditDialog/audit_Form.jsp?id="+id+"&flag="+flag+"&type=store"
		});
	}
	
	$(document).ready(function() {
		//导出
		$("#export_22").click(function(){
			if(confirm("提示，您确认导出数据？")){
				$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
	    		$("#bottomPageForm").submit();
	    		$("#excel_to_all").val("");	
			}
		});
	});                                      
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
