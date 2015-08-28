<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
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
    <html-el:form action="/admin/KonkaMobileVisitPlan"  method="post">
      <html-el:hidden property="plan_id" styleId="plan_id" value="${af.map.plan_id}" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="R3customerStr" styleId="R3customerStr" value="${af.map.R3customerStr}" />
      <html-el:hidden property="shopStr" styleId="shopStr" value="${af.map.shopStr}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>创建日期：</td>
          <td width="88%" align="left">
		      ${today}
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划月份：</td>
          <td width="88%" align="left">
                                
		      <html-el:select property="year" styleId="year" >
	      		   <html-el:option value="2014">2014</html-el:option>
	      		   <html-el:option value="2015">2015</html-el:option>
	      		   <html-el:option value="2016">2016</html-el:option>
	      		   <html-el:option value="2017">2017</html-el:option>
	      		   <html-el:option value="2018">2018</html-el:option>
	      		   <html-el:option value="2019">2019</html-el:option>
	      		   <html-el:option value="2020">2020</html-el:option>
	    	</html-el:select>年
	    	<html-el:select property="month" styleId="month" >
	    	   <html-el:option value="01">1</html-el:option>
      		   <html-el:option value="02">2</html-el:option>
      		   <html-el:option value="03">3</html-el:option>
      		   <html-el:option value="04">4</html-el:option>
      		   <html-el:option value="05">5</html-el:option>
      		   <html-el:option value="06">6</html-el:option>
      		   <html-el:option value="07">7</html-el:option>
      		   <html-el:option value="08">8</html-el:option>
      		   <html-el:option value="09">9</html-el:option>
      		   <html-el:option value="10">10</html-el:option>
      		   <html-el:option value="11">11</html-el:option>
      		   <html-el:option value="12">12</html-el:option>
	    	</html-el:select>月
          </td>
        </tr>
         <tr>
    		<td align="right" nowrap="nowrap" class="title_item">拜访客户：</td>
    		<td align="left" nowrap="nowrap">
    		<select id="r3_code_name" multiple="multiple" name="example-basic" size="5">
				<c:forEach items="${custList}" var="cur">
					<option value="${cur.map.customer_code}#${cur.map.customer_name}" >${cur.map.customer_name}</option>
				</c:forEach>
			</select>
	    	</td>
  	   </tr>
  	   <tr>
    		<td align="right" nowrap="nowrap" class="title_item">拜访终端：</td>
    		<td align="left" nowrap="nowrap">
    		<select id="shop_id_name" multiple="multiple" name="example-basic" size="5">
				<c:forEach items="${storeList}" var="cur">
					<option value="${cur.store_id}#${cur.store_name}" >${cur.store_name}</option>
				</c:forEach>
			</select>
	    	</td>
  	   </tr>
  	    <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访客户数：</td>
          <td width="88%" align="left">
             <html-el:hidden property="plan_of_access_cust" styleId="plan_of_access_cust" />
		     <label id="plan_of_access_cust_count" style="color: red;">${af.map.plan_of_access_cust}</label>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访终端数：</td>
          <td width="88%" align="left">
              <html-el:hidden property="plan_of_access_shop" styleId="plan_of_access_shop" />
		      <label id="plan_of_access_shop_count" style="color: red">${af.map.plan_of_access_cust}</label>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访次数：</td>
          <td width="88%" align="left">
		     <html-el:text property="plan_of_access" styleId="plan_of_access" size="12" maxlength="8"/>
          </td>
        </tr>
           <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">计划开拓数：</td>
          <td width="88%" align="left">
		     <html-el:text property="plan_of_dev_cust" styleId="plan_of_dev_cust" size="12" maxlength="8"/>
          </td>
        </tr>
            <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">计划说明：</td>
          <td width="88%" align="left">
             <html-el:textarea property="plan_desc" styleId="plan_desc" styleClass="webinput" style="width:70%;height:60px;" />
             <div id="plan_desc_note"  style="color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
          <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 

<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	$("#plan_of_access").attr("dataType", "Require").attr("msg", " 计划拜访次数不能为空");
	
		$("#plan_desc").textbox({
			maxLength: 500,
			onInput: function(event, status) {
				var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
				$("#plan_desc_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			}
		}).blur(function() {
			$("#plan_desc_note").slideUp("normal");
		});
	     //回显客户和终端信息
		var r3 = $("#R3customerStr").val();
		var shop = $("#shopStr").val();
		var r3arr = r3.split(",");
		var shoparr = shop.split(",");
		for ( var i = 0; i < r3arr.length; i++) {
			$("#r3_code_name option[value='" + r3arr[i] + "']").attr(
					"selected", "selected");
		}
		for ( var j = 0; j < shoparr.length; j++) {
			$("#shop_id_name option[value='" + shoparr[j] + "']").attr(
					"selected", "selected");
		}

         function multiCustOrShop(id){
        	 $("#"+id).multiselect( {
 				noneSelectedText : "==请选择==",
 				checkAllText : "全选",
 				uncheckAllText : '全不选',
 				selectedList : 3
 			}).multiselectfilter();
         }
         multiCustOrShop("r3_code_name");
         multiCustOrShop("shop_id_name");
         
		 function multi(id){
		    	$("#"+id).multiselect({
		    		noneSelectedText: '==请选择==',
		    		selectedList: 1,
		    		multiple: false,
		    		minWidth:150,
		    		click: function(event, ui){
		    	       if(ui.value != ""){
		    	    	   $("#"+id).val(ui.value);
		    	       }
		    		}
		    	}).multiselectfilter();
		    }

		    multi("year");
		    multi("month");
			
            //计算选择客户数量
			window.setInterval(function(){  
				var R3count = $("#r3_code_name").multiselect("getChecked").map(
						function() {
							return this.value;
						}).get();
					$("#plan_of_access_cust").val(R3count.length);
					$("#plan_of_access_cust_count").text(R3count.length);
			}, 1000);
			//计算选择终端数量
			window.setInterval(function() {
					var shopcount = $("#shop_id_name").multiselect("getChecked").map(
							function() {
								return this.value;
							}).get();
					$("#plan_of_access_shop").val(shopcount.length);
					$("#plan_of_access_shop_count").text(shopcount.length);
			}, 1000);

				$("#btn_submit").click(function() {
						//判断是否选择客户
						var valuestrR3 = $("#r3_code_name").multiselect("getChecked").map(
								function() {
									return this.value;
								}).get();
						//判断是否选择终端
						var valuestrshopid = $("#shop_id_name").multiselect("getChecked")
								.map(function() {
									return this.value;
								}).get();
						if ((valuestrR3==''||valuestrR3.length<0)&&(valuestrshopid == '' || valuestrshopid.length<0)) {
							  alert("客户终端至少选择一项");
							return false;
						}
						var bfcs=$("#plan_of_access").val();
						var bf_cust_totle=$("#plan_of_access_cust").val();
						var bf_shop_totle=$("#plan_of_access_shop").val();
					    if(parseInt(bfcs)<(parseInt(bf_cust_totle)+parseInt(bf_shop_totle))){
                                alert("拜访次数必须大于计划客户和终端的总数！");
                                return false;
						    }
						if (Validator.Validate(this.form, 3)) {
							if (confirm("确定提交表单？")) {
								$("#btn_submit").attr("value", "正在提交...");
								this.form.submit();
							} else {
								return false;
							}
						}
			}
		);
	});
//]]> 
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
