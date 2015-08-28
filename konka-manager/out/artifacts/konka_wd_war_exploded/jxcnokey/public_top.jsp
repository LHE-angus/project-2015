<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<div class="topcont">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="377" class="topleft"><img alt="买卖提进销存康佳" src="${ctx}/styles/jxc/images/logo.gif" /></td>
      <td align="right" class="rtopcont"><table width="375" border="0" cellspacing="0" cellpadding="0" style="float:right;" id="topFramesTable">
          <tr id="trTop">
            <td width="75" align="center">
            <div id="leftJxcMyProfile" class="rtoptab">
            <a href="Frames.do?goToLeftMethod=leftJxcMyProfile" name="leftJxcMyProfile" target="indexMainFrame">
                <h3><img src="${ctx}/styles/jxc/images/icon7.gif" /></h3>
                我的信息</a></div></td>
            <td width="75" align="center">
            <div id="leftKonkaOrderInfo" class="rtoptab">
            <a href="Frames.do?goToLeftMethod=leftKonkaOrderInfo" name="leftKonkaOrderInfo" target="indexMainFrame">
                <h3><img src="${ctx}/styles/jxc/images/icon8.gif" /></h3>
                订单管理</a></div></td>
            <td width="75" align="center">
            <div id="leftJxcStockBill" class="rtoptab">
            <a href="Frames.do?goToLeftMethod=leftJxcStockBill" name="leftJxcStockBill" target="indexMainFrame">
                <h3><img src="${ctx}/styles/jxc/images/icon4.gif" /></h3>
                进货管理</a></div></td>
            <td width="75" align="center">
            <div id="leftJxcSellBill" class="rtoptab">
            <a href="Frames.do?goToLeftMethod=leftJxcSellBill" name="leftJxcSellBill" target="indexMainFrame">
                <h3><img src="${ctx}/styles/jxc/images/icon5.gif" /></h3>
                产品销售</a></div></td>
            <td width="75" align="center">
            <div id="leftJxcStock" class="rtoptab">
            <a href="Frames.do?goToLeftMethod=leftJxcStock" name="leftJxcStock" target="indexMainFrame">
                <h3><img src="${ctx}/styles/jxc/images/icon6.gif" /></h3>
                库存管理</a></div></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <div class="clear"></div>
  <script type="text/javascript">//<![CDATA[
  $(document).ready(function(){

	var divJdId = "";
	$("div", "#topFramesTable").mouseover(function(){
		$(this).addClass("rtoptab_cur");
	}).mouseout(function(){
		$(this).removeClass("rtoptab_cur");
		if ("" != divJdId) {
			$("#" + divJdId).addClass("rtoptab_cur");
		}
	});
	
	var keySeq = "";
	<c:if test="${isLocal}">
	keySeq = "${JXC_TEST_KEY_VALUE}";
	</c:if>
	<c:if test="${not isLocal}">
	keySeq = external.GetPrecis('UsbKey');
	</c:if>
	$("a").click(function(){ 
		var _this = $(this);
		var url = _this.attr("href");

		divJdId = _this.attr("name");
		
		if(url.indexOf('keySeq') == -1){
			if (url.indexOf('?') > -1) {
				url = url + "&keySeq=" + keySeq;
			} else {
				url = url + "?keySeq=" + keySeq;
			}
		}
		
		_this.attr("href", url);

		$("div", "#topFramesTable").removeClass("rtoptab_cur");
		_this.parent().addClass("rtoptab_cur");
	});

	<c:if test="${not empty af.map.clickHrefName}">
	$("a[name='${af.map.clickHrefName}']").click();
	</c:if>
	
  });
//]]></script> 
</div>
