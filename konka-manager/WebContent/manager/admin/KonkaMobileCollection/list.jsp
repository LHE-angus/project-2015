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
    <html-el:form action="/admin/KonkaMobileCollection">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table>
        <tr>
          <td width="80%" nowrap="nowrap">
            &nbsp;<strong class="fb">存品型号：</strong>
	            <html-el:select property="coll_pd_id" styleId="coll_pd_id">
	          		<html-el:option value="">请选择...</html-el:option>
	          		<html-el:optionsCollection name="pePdModelList" label="md_name" value="pd_id"/>
	          	</html-el:select>
            
            &nbsp;<strong class="fb">选择仓库：</strong>
            	<html-el:hidden property="storage_id" styleId="storage_id" />
				<html-el:text property="storage_name" styleId="storage_name" size="30" maxlength="20"/>
				<img id="select_storage" src="${ctx}/images/search.gif" style='margin: 0 0 -3px 0; cursor: pointer;' alt='选择仓库'/>		
			
			&nbsp;<strong class="fb">是否锁定：</strong>
				<html-el:select property="status" styleId="status">
	          		<html-el:option value="">请选择...</html-el:option>
	          		<html-el:option value="0">正常</html-el:option>
	          		<html-el:option value="1">已锁定</html-el:option>
	          	</html-el:select>
            &nbsp;&nbsp;
            <input type="button" name="" value="搜索" id="btn_submit" class="but1" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
	    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaMobileCollection.do?method=add&mod_id=${af.map.mod_id}&';" />
	     &nbsp;
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center" >序号</td>
          <td width="140" nowrap="nowrap" align="center" >存品型号</td>
          <td align="center" >所在仓库</td>
          <td align="center" >所在仓位</td>
          <td width="70" nowrap="nowrap" align="center" >初始库存</td>
          <td width="70" nowrap="nowrap" align="center" >业务数</td>
          <td width="70" nowrap="nowrap" align="center" >是否锁定</td>
          <td width="70" nowrap="nowrap" align="center" >出/入库</td>
          <td width="120" nowrap="nowrap" align="center" >操作</td>
          <td width="70" nowrap="nowrap" align="center" >操作历史</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.coll_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.storage_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.storage_area_name)}</td>
            <td align="center" nowrap="nowrap">${cur.base_num}</td>
            <td align="center" nowrap="nowrap">${cur.busi_num}</td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.status eq 0}">正常</c:if>
            	<c:if test="${cur.status eq 1}">锁定</c:if>
            </td>
            <td align="center" nowrap="nowrap">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCollection.do','sendRec', 'coll_id=${cur.coll_id}&opty=0&'+$('#bottomPageForm').serialize())">出库</span> 
            |
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCollection.do','sendRec', 'coll_id=${cur.coll_id}&opty=1&'+$('#bottomPageForm').serialize())">入库</span> 
            </td>
            <td align="center" nowrap="nowrap">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCollection.do', 'view','coll_id=${cur.coll_id}&'+$('#bottomPageForm').serialize())">查看</span>
            |
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCollection.do', 'edit','coll_id=${cur.coll_id}&'+$('#bottomPageForm').serialize())">修改</span>
            |
            <c:if test="${cur.status eq 0}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('将存品锁定.','KonkaMobileCollection.do','doLock','coll_id=${cur.coll_id}&opty=2&newStatus=1&'+$('#bottomPageForm').serialize())">锁定</span> </c:if>
            <c:if test="${cur.status eq 1}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('将存品解锁.','KonkaMobileCollection.do','doLock','coll_id=${cur.coll_id}&opty=3&newStatus=0&'+$('#bottomPageForm').serialize())">解锁</span> </c:if>
            </td>
             <td align="center" nowrap="nowrap">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileStocksHis.do','list', 'coll_id=${cur.coll_id}&storage_id=${cur.storage_id}&'+$('#bottomPageForm').serialize())">操作历史</span> 
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
          </tr>
        </c:forEach>
      </table>
      
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCollection.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("coll_pd_id", "${fn:escapeXml(af.map.coll_pd_id)}");							
			pager.addHiddenInputs("storage_id", "${fn:escapeXml(af.map.storage_id)}");	
			pager.addHiddenInputs("storage_name", "${fn:escapeXml(af.map.storage_name)}");	
			pager.addHiddenInputs("status", "${fn:escapeXml(af.map.status)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#storage_id").attr("dataType", "Require").attr("msg", "请先选择仓库.");
	
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
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

	$("#select_storage").click(function() {
				var returnValue = window.showModalDialog(
								"selectStorages.do?storage_type=2&azaz=" + Math.random(),
								window,
								"dialogWidth:550px;status:no;dialogHeight:530px");
				if (returnValue != null) {
					$("#storage_name").val(returnValue.storagename);
					$("#storage_id").val(returnValue.storageid);
				};
			});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
