<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110" align="right">网点转化方向：</td>
          <td><html-el:select property="convertShopDirection"
			styleId="convertShopDirection" onchange="changeDirection(this)">
              <html-el:option value="0">代理商网点</html-el:option>
              <html-el:option value="1">R3用户</html-el:option>
            </html-el:select></td>
        </tr>
        <tr id="dls_tr">
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110" align="right">请选择经销商：</td>
		  <td><html-el:text property="konka_r3_name" styleId="konka_r3_name" readonly="true" /> 
		    <html-el:hidden property="konka_r3_id" styleId="konka_r3_id" />
            <input type='button' value='选择' onclick="getAgentsList();" /></td>
        </tr>
        <tr id="r3_tr" style="display: none;">
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110">
             <table width="100%" style="height:327px;padding-left:15px;border-left:1px solid #CCCCCC;border-top:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC">      
               <tbody>
                <tr><td align="right"> 创建R3用户：</td>
                </tr>
              </tbody>
             </table>
          </td>       
          <td><table style="padding-left:15px;border:1px solid #CCCCCC">
              <tbody>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所在区域名称：</td>
                  <td width="88%" align="left"><html-el:select property="area_name" styleId="area_name">
                      <html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="华东">华东</html-el:option>
		                <html-el:option value="山东">山东</html-el:option>
						<html-el:option value="东北">东北</html-el:option>
						<html-el:option value="华北">华北</html-el:option>
						<html-el:option value="华南">华南</html-el:option>
						<html-el:option value="西南">西南</html-el:option>
						<html-el:option value="华中">华中</html-el:option>
						<html-el:option value="西北">西北</html-el:option>
                    </html-el:select>
                  </td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司所在地名称：</td>
                  <td width="88%" align="left"><html-el:text property="branch_area_name" size="40" maxlength="30" styleId="branch_area_name" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户群类型：</td>
                  <td width="88%" align="left"><html-el:text property="customer_type" size="40" maxlength="30" styleId="customer_type" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right">交易状态 ：</td>
                  <td width="88%" align="left"><html-el:select property="status" styleId="status">
                      <html-el:option value="1">有交易</html-el:option>
                      <html-el:option value="2">无交易</html-el:option>
                    </html-el:select></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>R3编码：</td>
                  <td width="88%" align="left"><html-el:text property="r3_code" size="40" maxlength="30" styleId="r3_code" />
                   &nbsp;<div style="color:#f00;display:none;" id="r3_code_exist_error"><span style="color:red">*</span>该R3编码已存在，请重新输入！</div></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>经办名称：</td>
                  <td width="88%" align="left"><html-el:text property="handle_name" size="40" maxlength="30" styleId="handle_name" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
                  <td width="88%" align="left"><html-el:text property="customer_code" size="40" maxlength="30" styleId="customer_code" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户名称：</td>
                  <td width="88%" align="left"><html-el:text property="customer_name" size="40" maxlength="30" styleId="customer_name" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
                  <td width="88%" align="left"><html-el:text property="r3_desc" size="40" maxlength="30" styleId="r3_desc" /></td>
                </tr>
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right">2010合并编码：</td>
                  <td width="88%" align="left"><html-el:text property="merge_code_2010" size="40" maxlength="30" styleId="merge_code_2010" /></td>
                </tr>
                <tr>
					<td>&nbsp;</td>
					<td>
						<html-el:button property="" value="创建" styleClass="but4" styleId="btn_submit2" />
					</td>
			   </tr>
              </tbody>
            </table></td>
        </tr>
      </table>
       <script type="text/javascript">//<![CDATA[    
//验证R3编码是否存在
  $("#r3_code").blur(function(){
  	$("#btn_submit2").attr("disabled", "disabled");
  	var r3_code = $("#r3_code").val();
  	if(null == $(this).val() || $(this).val() == ''){
  		$("#r3_code_exist_error").hide();
  		$(this).css("background-color", "#fff");
  		return ;
  	}
  	if($(this).val().indexOf(' ')>-1){
  		$("#r3_code_exist_error").hide();
  		return;
  	}

  	$("#r3_code_exist_error").hide();
  	$(this).css("background-color", "#fff");
  	
  	$.ajax({
  		type: "POST",
  		url: "KonkaR3Shop.do",
  		data: "method=validateR3Code&r3_code=" + $(this).val(),
  		dataType: "json",
  		error: function(request, settings) {
  			alert("检查用户名重复失败，请稍候再次尝试。");
  			$("#r3_code_exist_error").show();
  			$(this).css("background-color", "#ddcc00");
  			$(this).focus();
  		},
  		success: function(oper) {
  			if (oper.result) {
  				$("#r3_code_exist_error").show();
  				$("#btn_submit2").attr("disabled", "disabled");
  				$("#r3_code").css("background-color", "#ddcc00");
  				$("#r3_code").focus();
  			} else {
  				$("#r3_code_exist_error").hide();
  				$("#r3_code").css("background-color", "#fff");
  				$("#btn_submit2").removeAttr("disabled");
  			}
  		}
  	});
  });
//]]></script>