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
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/PePdModel" enctype="multipart/form-data">
      <html-el:hidden property="pd_id" styleId="pd_id" value="${af.map.pd_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <c:if test="${not empty af.map.pd_id}">
          <c:set var="readonly" value="true" />
        </c:if>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">企业名称:</td>
          <td><strong class="fb">康佳集团股份有限公司</strong></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>产品类别：</td>
          <td width="88%"><html-el:select property="cls_id" styleId="cls_id" style="width:200px;">
              <c:forEach var="cur" items="${basePdClazzList}">
                <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            <span id="cls_full_name" class="note" style="padding-left:4px;"></span></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>型号名称：</td>
          <td width="88%"><html-el:text property="md_name" styleId="md_name" size="40" maxlength="30"/>
            <div class="note">注：型号名称中不能有空白字符；</div></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>尺寸：</td>
          <td width="88%"><html-el:text property="md_size" styleId="md_size" size="40" maxlength="15"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>系列：</td>
          <td width="88%"><html-el:text property="md_serise" styleId="md_serise" size="40" maxlength="15"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">物料编号：</td>
          <td width="88%"><html-el:text property="mat_num" styleId="mat_num" size="20" maxlength="16"/>
            &nbsp;<span id="mat_msg" style="color:red;"></span></td>
        </tr>
        <tr>
           <td nowrap="nowrap" class="title_item">产品说明：</td>
          <td>
           	<html-el:text property="pd_desc" styleId="pd_desc" maxlength="100" size="40"/>
           	<div id="info_chat_content" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/manager/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">上传主图：</td>
          <td width="88%"><c:if test="${not empty (af.map.main_pic)}" var="hasImage"><a href="${ctx}/${af.map.main_pic}" target="_blank"><img src="${ctx}/${fn:substringBefore(af.map.main_pic, '.')}_240.jpg" style="border:0;" width="240" height="180" /></a> <br />
              <label for="chkReUploadImage">
                <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#main_pic').toggle();" />
                重新上传主图</label>
              <br />
              <html-el:file property="main_pic" style="display:none;width:500px;" styleId="main_pic" />
            </c:if>
            <c:if test="${empty (af.map.main_pic)}">
              <html-el:file property="main_pic" style="width:500px;" styleId="main_pic" />
            </c:if>
            <div class="note">1、支持图片格式：jpg，png，gif；</div>
            <div class="note">2、上传的主图会自动缩放成合适的尺寸，主图宽高比例最好是1:1，否则会变形；</div></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">参考价格：</td>
          <td width="88%">
          <!--<html-el:text property="price_ref" styleId="price_ref" maxlength="8" size="12"/>-->
          <input type="text" name="price_ref" value="${af.map.price_ref}" maxlength="8" size="12"/>	
            元</td>
        </tr>
        <tr style="display:none;">
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>审核状态：</td>
          <td width="88%"><label for="audit_state1" style="width:80px;">
              <html-el:radio property="audit_state" styleClass="audit_state" styleId="audit_state1" value="0" style="width:50px;">正在审核</html-el:radio>
            </label>
            <label for="audit_state2" style="width:80px;">
              <html-el:radio property="audit_state" styleClass="audit_state" styleId="audit_state2" value="1" style="width:50px;">审核通过</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">排序值：</td>
          <td width="88%"><html-el:text property="order_value" styleId="order_value" maxlength="8" size="8"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">停产时间：</td>
          <td width="88%"><fmt:formatDate value="${af.map.out_date}" pattern="yyyy-MM-dd" var="_out_date"/>
            <html-el:text property="out_date" value="${_out_date}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">上市时间：</td>
          <td width="88%"><fmt:formatDate value="${af.map.in_date}" pattern="yyyy-MM-dd" var="_in_date"/>
            <html-el:text property="in_date" value="${_in_date}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
         <tr>
        <td nowrap="nowrap" class="title_item">型号分类:</td>
        <td>
			<html-el:select property="goods_name_type" styleId="goods_name_type">
	          <html-el:option value="-1">--请选择--</html-el:option>
              <html-el:option value="1">新品</html-el:option>
              <html-el:option value="2">主销</html-el:option>
              <html-el:option value="3">退市</html-el:option>
              <html-el:option value="4">停产</html-el:option>
		    </html-el:select>
        </td>
        </tr>
        <tr style="display:none;">
          <td nowrap="nowrap" class="title_item">是否上架：</td>
          <td width="88%">
          <label for="is_sell1" style="width:80px;">
              <html-el:radio property="is_sell" styleClass="is_sell" styleId="is_sell1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="is_sell2" style="width:80px;">
              <html-el:radio property="is_sell" styleClass="is_sell" styleId="is_sell2"  value="1" style="width:50px;">是</html-el:radio>
            </label>
            </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否锁定：</td>
          <td width="88%"><label for="is_locked1" style="width:80px;">
              <html-el:radio property="is_locked" styleClass="is_locked" styleId="is_locked1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="is_locked2" style="width:80px;">
              <html-el:radio property="is_locked" styleClass="is_locked" styleId="is_locked2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">3D电视: ${af.map.label_3d}</td>
          <td width="88%"><label for="label_3d1" style="width:80px;">
              <html-el:radio property="label_3d" styleClass="label_3d" styleId="label_3d1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="label_3d2" style="width:80px;">
              <html-el:radio property="label_3d" styleClass="label_3d" styleId="label_3d2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">智能电视：${af.map.label_int}</td>
          <td width="88%"><label for="label_int1" style="width:80px;">
              <html-el:radio property="label_int" styleClass="label_int" styleId="label_int1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="label_int2" style="width:80px;">
              <html-el:radio property="label_int" styleClass="label_int" styleId="label_int2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">网络电视：${af.map.label_www}</td>
          <td width="88%"><label for="label_www1" style="width:80px;">
              <html-el:radio property="label_www" styleClass="label_www" styleId="label_www1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="label_www2" style="width:80px;">
              <html-el:radio property="label_www" styleClass="label_www" styleId="label_www2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item">是否为配件：</td>
          <td width="88%"><label for="is_parts1" style="width:80px;">
              <html-el:radio property="is_parts" styleClass="is_parts" styleId="is_parts1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="is_parts2" style="width:80px;">
              <html-el:radio property="is_parts" styleClass="is_parts" styleId="is_parts2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否为4K：</td>
          <td width="88%"><label for="is_4k1" style="width:80px;">
              <html-el:radio property="is_4k" styleClass="is_4k" styleId="is_4k1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="is_4k2" style="width:80px;">
              <html-el:radio property="is_4k" styleClass="is_4k" styleId="is_4k2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否为易TV：</td>
          <td width="88%"><label for="is_ytv1" style="width:80px;">
              <html-el:radio property="is_ytv" styleClass="is_ytv" styleId="is_ytv1" value="0" style="width:50px;">否</html-el:radio>
            </label>
            <label for="is_ytv2" style="width:80px;">
              <html-el:radio property="is_ytv" styleClass="is_ytv" styleId="is_ytv2" value="1" style="width:50px;">是</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="left"><font color="red">[必填]</font>规格段：</td>
          <td width="88%" align="left"><html-el:select property="size_sec" styleId="size_sec" style="width:200px;" >
              <html-el:option value="">请选择</html-el:option>
              <c:forEach items="${sizeSecList}" var="cur" varStatus="vs">
				 <html-el:option value="${cur.field1}">${cur.type_name}</html-el:option>
			  </c:forEach>
            </html-el:select></td>
        </tr>
        <tbody id="is_sell_tbody" style="display:none;">
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>上架时间：</td>
            <td width="88%"><fmt:formatDate value="${af.map.up_date}" pattern="yyyy-MM-dd" var="_up_date"/>
              <html-el:text property="up_date" styleId="up_date" value="${_up_date}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              <html-el:select property="up_date_hour" styleId="up_date_hour" style="width:60px;">
                <html-el:option value="">-小时-</html-el:option>
                <c:forEach begin="1" end="24" varStatus="vs">
                  <html-el:option value="${vs.count}">${vs.count}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </tr>
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>下架时间：</td>
            <td width="88%"><fmt:formatDate value="${af.map.down_date}" pattern="yyyy-MM-dd" var="_down_date"/>
              <html-el:text property="down_date" styleId="down_date" value="${_down_date}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              <html-el:select property="down_date_hour" styleId="down_date_hour" style="width:60px;">
                <html-el:option value="">-小时-</html-el:option>
                <c:forEach begin="1" end="24" varStatus="vs">
                  <html-el:option value="${vs.count}">${vs.count}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </tr>
          <tr style="display:none;">
            <td width="12%" nowrap="nowrap" class="title_item">特价标识：</td>
            <td width="88%"><label for="is_spec_price1" style="width:80px;">
                <html-el:radio property="is_spec_price" styleId="is_spec_price1" styleClass="is_spec_price" value="0" style="width:50px;">否</html-el:radio>
              </label>
              <label for="is_spec_price2" style="width:80px;">
                <html-el:radio property="is_spec_price" styleId="is_spec_price2" styleClass="is_spec_price" value="1" style="width:50px;">是</html-el:radio>
              </label></td>
          </tr>
          <tr id="spec_price_tr" style="display:${af.map.is_spec_price eq 1 ? '' : 'none'};">
            <td width="12%" nowrap="nowrap" class="title_item">特价价格：</td>
            <td colspan="3"><html-el:text property="spec_price" styleId="spec_price" maxlength="15" size="12"/>
              元</td>
          </tr>
          <tr style="display:none;">
            <td width="12%" nowrap="nowrap" class="title_item">启用销售区域：</td>
            <td width="88%"><label for="enable_sellarea1" style="width:80px;">
                <html-el:radio property="enable_sellarea" styleId="enable_sellarea1" styleClass="enable_sellarea" value="0" style="width:50px;">否</html-el:radio>
              </label>
              <label for="enable_sellarea2" style="width:80px;">
                <html-el:radio property="enable_sellarea" styleId="enable_sellarea2" styleClass="enable_sellarea" value="1" style="width:50px;">是</html-el:radio>
              </label></td>
          </tr>
          <tr id="enable_sellarea_tr" style="display:${af.map.enable_sellarea eq 1 ? '' : 'none'};">
            <td width="12%" nowrap="nowrap" class="title_item">销售区域：</td>
            <td width="88%"><c:forEach var="cur" items="${baseProvinceList}" varStatus="vs1"> <span style="width:200px;overflow:hidden;float:left;">
                <c:if test="${empty cur.map.select }">
                  <input name="pks" type="checkbox" id="pks" value="${cur.p_index}" />
                </c:if>
                <c:if test="${not empty cur.map.select}">
                  <input name="pks" type="checkbox" id="pks" checked="checked" value="${cur.p_index}" />
                </c:if>
                <c:out value="${cur.p_name}" />
                </span> </c:forEach></td>
          </tr>
        </tbody>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="btn_reset" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>

<!-- ****** Main Frame End ****** --> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#price_ref").attr("datatype", "PositiveDouble").attr("msg", "请参考价格为空或格式不正确！");
	
	$("#cls_id").attr("dataType", "Require").attr("msg", "请选择产品类别！");
	$("#md_name").attr("dataType", "Require").attr("msg" , "请填写产品型号！");
	//$("#mat_num").attr("dataType", "Require").attr("msg" , "请填写物料编号！");
	$("#order_value").attr("focus",setOnlyNum);
	$("#pd_desc").focus(setLength).attr("len",125);
	$("#price_ref").attr("dataType", "Require").attr("msg", "请填写参考价格！");
	$("#price_ref").attr("focus",setOnlyNum);
	$("#order_value").attr("dataType", "Require").attr("msg", "请填写排序值！");
	$("#md_size").attr("dataType", "Require").attr("msg", "请填写尺寸！");
	$("#md_serise").attr("dataType", "Require").attr("msg", "请填写系列！");
	$("#size_sec").attr("dataType", "Require").attr("msg", "请选择产品规格！");
	$("#md_size").attr("focus",setOnlyNum);
	document.all("is_sell")[1].checked=true;
	
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 2);
			var entp_name = $("#entp_name").val();
			if(entp_name == "请输入企业名称" || entp_name == ""){
				$("#entp_name_msg").show();
				return;
			}else{
				$("#entp_name_msg").hide();
			}
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
		
	var full_name = $("#cls_id option:selected").attr("id").split('_')[0];
	$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
	
	$("#cls_id").change(function(){
		var is_leaf = $("#cls_id option:selected").attr("id").split('_')[1];
		var full_name = $("#cls_id option:selected").attr("id").split('_')[0];
		if (0 == is_leaf) {
			alert("请选择具体的子类别！");
			$("#md_name").attr("disabled",true);
		}else{
			$("#md_name").removeAttr("disabled");
		}		
		$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
	});
	
	$("#md_name").blur(function(){
		if($.trim(this.value).length == 0){
			this.value = $.trim(this.value);
			return false;
		}

		$("#md_name_err").remove();
		$("#md_name_repeat").remove();
		$("#md_name_delete").remove();
		
		this.value = $.trim(this.value);

		//默认带出型号
		var md_size = (this.value).toUpperCase().replace("L","").replace("C","").replace("E","").replace("M","").replace("D","");
		
		if(md_size.length > 2){
			if(!isNaN(md_size.substring(0,2))){
				$("#md_size").val(md_size.substring(0,2));
				$("#md_size").html(md_size.substring(0,2));
			}
		}
		
		if (/(\s)+/g.test(this.value)) {
			$(this).css({"background-color":"#FEF5CA"}).after("<span id='md_name_err' style='color:#F00;margin-left:1em;'>* 产品型号名称\"" + this.value + "\"不能含有空白字符</span>");
			$("#md_name_repeat").hide();
			$("#send").attr("disabled", "true");
			return false;
		} else {
			$("#send").removeAttr("disabled");
		}
		
		//如果修改时没有改变型号名称，不验证型号的可重复
		if(this.value == "${af.map.md_name}"){
			return false;
		}

		$.ajax({
			type: "POST",
			url: "PePdModel.do",
			data: "method=getMdName&md_name=" + this.value,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！");},
			success: function(isExist) {
				if(2 == isExist ){
					$("#md_name").css({"background-color":"#FEF5CA"}).after("<span id='md_name_delete' style='color:#F00;margin-left:1em;'>* 产品型号名称已经\"" + $("#md_name").val() + "\"已经被删除，请联系管理员！</span>");
					$("#send").attr("disabled", "true");
				}
				else if(1 == isExist){
					$("#md_name").css({"background-color":"#FEF5CA"}).after("<span id='md_name_repeat' style='color:#F00;margin-left:1em;'>* 产品型号名称已经\"" + $("#md_name").val() + "\"已经存在！</span>");
					$("#send").attr("disabled", "true");
				}else {
					$("#md_name").css({"background-color":"#FFF"});
					$("#send").removeAttr("disabled");
				}
				return false;
			}
		});
	});
	
	$(".is_sell").click(function(){
		
		var is_sell = $(this).val();
		
		switch(is_sell){
		case '1' :
			$("#is_sell_tbody").show();
			$("#up_date").attr("datatype", "Require").attr("msg", "请选择上架时间！");
			$("#down_date").attr("datatype", "Require").attr("msg", "请选择下架时间！");
			$("#up_date_hour").attr("datatype", "Require").attr("msg", "小时不能为空！");
			$("#down_date_hour").attr("datatype", "Require").attr("msg", "小时不能为空！");
			break;
		case '0' :
			$("#is_sell_tbody").hide();
			$("#up_date").val("");
			$("#down_date").val("");
			$("#up_date_hour").val("");
			$("#down_date_hour").val("");
			$("#spec_price").val("");
			$(".is_spec_price").eq(0).attr("checked",true);
			$(".enable_sellarea").eq(0).attr("checked",true);
			$("#up_date").removeAttr("datatype");
			$("#down_date").removeAttr("datatype");
			$("#up_date_hour").removeAttr("datatype");
			$("#down_date_hour").removeAttr("datatype");
			$("#spec_price").removeAttr("datatype");
			break;
		}
	});
	$(".audit_state").eq(1).attr("checked",true);
	var pd_id=$("#pd_id").val();
	if(pd_id==null||pd_id==''){
		$(".is_locked").eq(0).attr("checked",true);
		$(".label_3d").eq(0).attr("checked",true);
	}
	
	$(".is_spec_price").click(function(){
			
		var is_spec_price = $(this).val();
		
		switch(is_spec_price){
		case '1' :
			$("#spec_price_tr").show();
			$("#spec_price").attr("datatype", "PositiveDouble").attr("msg", "参考价格为空或格式不正确！");
			break;
		case '0' :
			$("#spec_price_tr").hide();
			$("#spec_price").val("");
			$("#spec_price").removeAttr("datatype");
			break;
		}
	});
	
	$(".enable_sellarea").click(function(){
		var enable_sellarea = $(this).val();
		
		switch(enable_sellarea){
		case '1' :
			$("#enable_sellarea_tr").show();
			break;
		case '0' :
			$("#enable_sellarea_tr").hide();
			break;
		}
	});
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function setLength(){
	$(this).keypress(function (){
		if(this.value.length >  this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	});
}


function setLength(){
	$(this).keypress(function (){
		if(this.value.length > 125){
			alert("长度超出了125");
		}
	});
}


// function openChild(number){
// 	var dateTime = new Date().getTime();
// 	if(number == 0){
// 		var entp_prod_id = $("#entp_prod_id").val();
// 		if($.trim(entp_prod_id).length == 0){
// 			alert("请先选择买卖提生产企业");
// 			return false;
// 		}
// 		 var returnValue = window.showModalDialog("PePdModel.do?method=listEntpMain&entp_prod_id=" + entp_prod_id + "&dateTime=" + dateTime, window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
// 		    if(returnValue != null) {
// 		    	var entp_info = returnValue.split(",");
// 		    	var entp_name = entp_info[0];
// 		    	var entp_id = entp_info[1];
// 		    	$("#entp_name").val(entp_name);
// 		    	$("#entp_id").val(entp_id);
// 		    } 
// 	}
// 	if(number == 1){
// 		 var returnValue = window.showModalDialog("PePdModel.do?method=listEntpProd&dateTime=" + dateTime, window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
// 		 if(returnValue != null) {
// 		    	var entp_info = returnValue.split(",");
// 		    	var entp_name = entp_info[0];
// 		    	var entp_id = entp_info[1];
// 		    	$("#entp_prod_name").val(entp_name);
// 		    	$("#entp_prod_id").val(entp_id);
// 		    } 
// 	}
// }
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
