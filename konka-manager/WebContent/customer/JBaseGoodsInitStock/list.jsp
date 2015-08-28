<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>初始化库存</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JBaseGoodsInitStock">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">仓库：</strong>
	          <html-el:select property="store_id" styleClass="store_id" styleId="store_id" multiple="multiple">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	          &nbsp;<strong class="fb">商品名称：</strong>
	          <html-el:text property="goods_name_like" styleClass="webinput" styleId="goods_name_like" maxlength="30"/>
	          &nbsp;<strong class="fb">初始化日期：</strong>
	          <input name="init_date_s" id="init_date_s" size="12" value="${af.map.init_date_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'init_date_e\')}'})" />
	            	    至
	          <input name="init_date_e" id="init_date_e" size="12"  value="${af.map.init_date_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'init_date_s\')||\'2013-11-01\'}'})" />
	          &nbsp;<strong class="fb">初始化状态：</strong>
	          <html-el:select property="init_state" styleId="init_state">
	          	<html-el:option value="">请选择</html-el:option>
	          	<html-el:option value="0">正常</html-el:option>
	          	<html-el:option value="1">作废</html-el:option>
	          </html-el:select>
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBaseGoodsInitStock.do?method=add&mod_id=${af.map.mod_id}'" />
	  <input name="input" type="button" id="but_excel" class="but_excel" value="导 入" onclick="location.href='${ctx}/customer/manager/JBaseGoodsInitStock.do?method=excel&mod_id=${af.map.mod_id}'" />
	  <input name="input" type="button" id="butinit" class="but3" value="一键初始化" style="width:100px;"/>
	</div>
	<div class="rtabcont1">
	<div style="overflow-x: auto">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%">序号</th>
	      <th width="10%">仓库</th>
	      <th width="10%">型号</th>
	      <th width="8%">初始库存</th>
	      <th width="8%">进货单价（元）</th>
	      <th width="8%">初始化金额（元）</th>
	      <th width="8%">初始化时间</th>
<!-- 	      <th width="8%">初始化人</th> -->
	      <th width="8%">说明</th>
	      <th width="8%">状态</th>
	      <th width="10%">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="left">&nbsp;${cur.map.store_name }</td>
	        <td align="left">&nbsp;${cur.map.goods_name }</td>
	        <td align="right">&nbsp;${cur.init_count }&nbsp;</td>
	        <td align="right">&nbsp;<fmt:formatNumber value="${cur.buy_price}" pattern="0.00"/>&nbsp;</td>
	        <td align="right">&nbsp;<fmt:formatNumber value="${cur.init_money}" pattern="0.00"/> &nbsp;</td>
	        <td align="center"><fmt:formatDate value="${cur.init_date }" pattern="yyyy-MM-dd"/></td>
<%-- 	        <td align="left">&nbsp;${cur.init_user }&nbsp;</td> --%>
	        <td align="left">&nbsp;${cur.init_desc }&nbsp;</td>
	        <td align="center">
	        	<c:if test="${cur.init_state eq 0 }">正常</c:if>
	        	<c:if test="${cur.init_state eq 1 }">作废</c:if>
	        </td>
	        <td align="center" nowrap="nowrap">
	        	<c:if test="${cur.init_state eq 0}">
	        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JBaseGoodsInitStock.do', 'view','&mod_id=${af.map.mod_id}&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> 
<%-- 	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JBaseGoodsInitStock.do', 'edit','&mod_id=${af.map.mod_id}&id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span> |  --%>
<%-- 	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定作废吗？', 'JBaseGoodsInitStock.do', 'delete','&id=${cur.id}&'+$('#bottomPageForm').serialize())">作废</span> --%>
	        	</c:if>
	        	<c:if test="${cur.init_state eq 1}">
	        	<span class="fblue" style="color:#ccc;">查看</span>  
<!-- 	        	<span class="fblue" style="color:#ccc;">修改</span> |  -->
<%-- 	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定启用吗？', 'JBaseGoodsInitStock.do', 'reStart','&mod_id=${af.map.mod_id}&id=${cur.id}&'+$('#bottomPageForm').serialize())">启用</span> --%>
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
              </tr>
        	</c:forEach>
        </c:if>
	  </table>
	  </div>
	  <c:if test="${not empty entityList}">
	    <div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBaseGoodsInitStock.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("store_id", "${af.map.store_id}");
		            pager.addHiddenInputs("init_state", "${af.map.init_state}");
		            pager.addHiddenInputs("init_date_s", "${af.map.init_date_s}");
		            pager.addHiddenInputs("init_date_e", "${af.map.init_date_e}");
		            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");
		            document.write(pager.toString());
		            </script></td>
	          </tr>
	        </table>
	      </form>
	    </div>
	  </c:if>
	</div>
	<div style="display:none;background-color:white;height: 200px" id="add_body">
	  <table border="0" cellpadding="0"  cellspacing="0" width="400px">
	  	  <tr style="height: 30px;"></tr>
		  <tr>
		    <td class="title_item" style="height:40px;padding-left: 20px;" width="40%">选择一键初始化仓库：</td>
		    <td align="left">
		    	<select name="init_store_id" id="init_store_id">
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<option value="${cur.store_id}">${cur.store_name}</option>
	          		</c:forEach>
	            </select>
		    </td>
		  </tr>
		  <tr>
		    <td class="title_item" style="height:40px;padding-left: 20px;">选择初始化时间：</td>
		    <td align="left">
		    	<input name="one_key_init_date" id="one_key_init_date" size="12" value="${nowdate}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'${beforedate}',maxDate:'${nowdate }'})" />
		    	<select name="HH">
	          		<c:forEach begin="0" end="23" varStatus="vs">
						<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}时</option>		          	
	          		</c:forEach>
	            </select>
	            <select name="mm">
	          		<c:forEach begin="0" end="59" varStatus="vs">
						<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}分</option>		          	
	          		</c:forEach>
	            </select>
		    </td>
		  </tr>
		  <tr style="height:40px;">
		  	 <td colspan="1" align="center"><input class="but5" type="button" name="Submit5" value="返回" id="btn_c"/></td>
		  	 <td colspan="1" align="left"><input class="but4" type="button" name="Submit4" value="提交" id="btn_add" style="align:center;"/></td>
		  </tr>
	 </table>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#init_store_id").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	$("#store_id").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	$("#init_state").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	
	//覆盖层
	var add_body = new LightBox("add_body");
	add_body.Over = true;  //是否启用覆盖层  :true 、 false;
	add_body.OverLay.Color = "#000"; //覆盖层颜色 ，必须启用覆盖层才有作用
	add_body.OverLay.Opacity = 10; //覆盖层透明度 
	add_body.Fixed = true; // 是否定位
	add_body.Center = true; //是否居中

	$("#butinit").click(function (){
		$("#init_store_id").attr("dataType",'Require').attr("msg","请选择一键初始化仓库！");
		add_body.Show();
	});

	$("#btn_c").click(function (){
		add_body.Close();
	});
	
	$("#btn_add").click(function (){
		if(confirm("请您慎重:\n    一键初始化是将未初始化的商品型号的库存全部初始化为0。\n    对已初始化的型号将不能再进行初始化操作，只能通过盘点来调整库存。")){
			var one_key_init_date = $("#one_key_init_date").val();
			var init_store_id= $("#init_store_id").val();
			if(init_store_id.length<1){
				alert("仓库不能为空！");
				return false;
			}
			if(one_key_init_date.length<1){
				alert("初始化时间不能为空！");
				return false;
			}
			add_body.Close();
			location.href = "${ctx}/customer/manager/JBaseGoodsInitStock.do?method=oneKeyInitStock&mod_id=${af.map.mod_id}&init_store_id="+init_store_id + "&one_key_init_date="+one_key_init_date;
		}
	});
});	
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>