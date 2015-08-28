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
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <!-- - -->
      <tr>
        <td nowrap="nowrap" class="title_item" align="right" colspan="2"><fieldset  style="border:1px solid blue;height:300px;">
            <legend>统计的机型</legend>
            <div id="ccccc"> </div>
            <div id="modelList">
              <html-el:form action="/admin/ChannelBestSellingModelsAnalysis">
                <html-el:hidden property="method" value="modelList" />
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
                  <tr class="tabtt1">
                    <td  >&nbsp;</td>
                    <td  ><strong class="fb">型号：</strong>
                      <html-el:text property="column_11" style="width:100px;" /></td>
                    <td ><input class="but1" type="submit" name="Submit" value="搜索" /></td>
                  </tr>
                </table>
              </html-el:form>
              <table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="rtable2">
                <c:forEach var="cur" items="${modelList}" varStatus="vs">
                  <tr>
                    <td align="center"><input name="pks" type="checkbox"
													id="pks" value="${cur.map.model}" /></td>
                    <td align="center" nowrap="nowrap">${cur.map.model}</td>
                    <td align="center"><html-el:button property=""
														value="选 择" styleClass="but4" styleId="btn_submit"
														onclick="choose('${cur.map.model}');" /></td>
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
                  </tr>
                </c:forEach>
              </table>
              <form id="bottomPageForm" name="bottomPageForm" method="post"
										action="ChannelBestSellingModelsAnalysis.do">
                <table width="750" border="0" align="center" cellpadding="0"
											cellspacing="0">
                  <tr>
                    <td height="40" align="center"><script
														type="text/javascript"
														src="${ctx}/commons/scripts/pager.js">;</script>
                      <script
														type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "modelList");
            pager.addHiddenInputs("column_11", "${af.map.column_11}");
            document.write(pager.toString());
            </script></td>
                  </tr>
                </table>
              </form>
            </div>
          </fieldset></td>
      </tr>
      <!-- - -->
      <tr>
        <td>&nbsp;</td>
        <td><input id="gsBTN" type='button' value='统计' onclick="getStat();"/></td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
function getModel() { 
	  var returnValue = window.showModalDialog("ChannelBestSellingModelsAnalysis.do?method=modelList", window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
	   
		if (returnValue != null) {
			$("#column_11").val(returnValue.column_11);
		};
	};
	function getStat() { 
			var returnValue = window.showModalDialog("ChannelBestSellingModelsAnalysis.do?method=list", window, "dialogWidth:800px;status:no;dialogHeight:680px");
			if (returnValue != null) {
	          if(returnValue){
	        	  history.back();
	              }
			}
			};
	
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
