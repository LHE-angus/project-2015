<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt; 销售记录</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：产品销售 &gt; 销售记录 &gt; 上传附件</div>
  <div class="rtabcont1">
  <html-el:form action="/JxcJnhmSellBill.do" enctype="multipart/form-data">
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="keySeq" />
    <html-el:hidden property="sell_bill_id" value="${sellBill.id}"/>
    <html-el:hidden property="queryString" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="4" align="center">销售明细 </th>
      </tr>
      <tr>
        <td class="title_item">销售日期：</td>
        <td><fmt:formatDate value="${sellBill.sell_date}" pattern="yyyy-MM-dd" /></td>
        <td class="title_item">销售编号：</td>
        <td><strong class="fb"><font color="red">NO:</font></strong><font color="red">${sellBill.sn}</font></td>
      </tr>
      
      <tr>
        <th colspan="4">客户信息</th>
      </tr>
      <tr>
        <td class="title_item" >消费者姓名：</td>
        <td colspan="3" align="left">${fn:escapeXml(cust.name)}</td>
      </tr>
      <!--
      <tr>
        <td class="title_item" >联系人姓名：</td>
        <td colspan="3" align="left">${fn:escapeXml(cust.link_name)}</td>
      </tr>
      -->
      <tr>
        <td class="title_item">联系电话：</td>
        <td colspan="3" align="left">${fn:escapeXml(cust.tel)}</td>
      </tr>
      <tr>
        <td class="title_item">地址：</td>
        <td colspan="3" align="left">${fn:escapeXml(cust.addr)}</td>
      </tr>
      <tr>
        <td class="title_item">备注：</td>
        <td colspan="3" align="left">${fn:escapeXml(cust.remarks)}</td>
      </tr>
      <tr>
        <th colspan="4">产品信息</th>
      </tr>
      <c:forEach items="${detailList}" var="cur" varStatus="status">
        <tr>
          <td class="title_item">出货开发票日期：</td>
          <td><fmt:formatDate value="${cur.ch_invoice_date}" pattern="yyyy-MM-dd" /></td>
          <td class="title_item">增值税（销售）发票号：</td>
          <td>${cur.invoice_bh}</td>
        </tr>
        <tr>
          <td class="title_item">产品类型：</td>
          <td colspan="3" align="left">${fn:escapeXml(cur.pd_type_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品品牌：</td>
          <td colspan="3" align="left">${fn:escapeXml(cur.brand_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品型号：</td>
          <td colspan="3" align="left">${fn:escapeXml(cur.pd_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品唯一编码：</td>
          <td colspan="3">${fn:escapeXml(cur.pd_unique_code)}</td>
        </tr>
        <tr>
          <th colspan="4">销售信息</th>
        </tr>
        <tr>
          <td class="title_item">销售数量：</td>
          <td colspan="3" align="left">${cur.count}</td>
        </tr>
        <tr>
          <td class="title_item">销售单价：</td>
          <td colspan="3" align="left"><fmt:formatNumber type="currency" value="${cur.price}" /></td>
        </tr>
        <tr>
          <td class="title_item">单台补贴金额￥：</td>
          <td colspan="3"><html-el:text property="allowance" styleId="allowance" value="${cur.allowance}" maxlength="4" size="10"></html-el:text></td>
        </tr>
        <tr>
          <td class="title_item">优惠：</td>
          <td colspan="3">${fn:escapeXml(cur.favorable)}</td>
        </tr>
        <tr>
          <td class="title_item">销售金额￥：</td>
          <td colspan="3" align="left"><fmt:formatNumber type="currency" value="${cur.count*cur.price}" /></td>
        </tr>
        <!-- konka -->
        <c:if test="${cur.brand_id eq KONKA_BRAND_ID}">
          <tr>
            <td class="title_item">产品编号：</td>
            <td colspan="3" align="left">${fn:escapeXml(cur.pd_bh)}</td>
          </tr>
          <tr>
            <td class="title_item">产品串号：</td>
            <td colspan="3" align="left">${fn:escapeXml(cur.pd_ch)}</td>
          </tr>
        </c:if>
      </c:forEach>
      <tr>
        <td class="title_item">说明：</td>
        <td colspan="3" align="left">${fn:escapeXml(sellBill.remarks)}</td>
      </tr>
      <tr>
        <td class="title_item">经办人：</td>
        <td colspan="3" align="left">${fn:escapeXml(sellBill.opr_man)}</td>
      </tr>
      <tr>
        <td class="title_item">应收金额(￥)：</td>
        <td align="left"><fmt:formatNumber type="currency" value="${sellBill.money}" /></td>
        <td class="title_item">本次收款(￥)：</td>
        <td  align="left"><fmt:formatNumber type="currency" value="${sellBill.pay_money}" /></td>
      </tr>
      <tr>
         <td class="title_item" title="请上传身份证复印件、发票复印件和《“节能产品惠民工程”补贴确认函》3个文件">上传附件：</td>
         <td colspan="3"><div id="divFileHidden" style="display: none;">
             <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
             <img src="../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
           <div id="divFile">
             <input name="file_show" type="file" id="file_show" style="width: 250px;" />
             <img src="../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
           	<c:if test="${not empty attachmentList}">
		       <tr>
		         <td height="28" class="title_item">已上传的附件 ：</td>
		         <td colspan="3"><ol>
		             <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
		               <li>
		               	<c:if test="${not empty cur.save_path and cur.source eq 2}"><img src="${ctx}/${fn:substringBefore(cur.save_path, '.')}_240.jpg" /></c:if>
		               	&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="del_${cur.id}" onclick="deleteFile('${cur.id}','${cur.save_path}');">删除</a></li>
		             </c:forEach>
		           </ol></td>
		       </tr>
		    </c:if>
         </td>
       </tr>
      <tr>
        <td colspan="4" align="center"><input class="bgButtonSave" type="button" name="btn_submit" id="btn_submit"  value="保 存" />
            &nbsp;<input class="bgButtonBack" type="submit" name="btn_back" id="btn_back" value="返回" onclick="history.back();return false;" /></td>
      </tr>
    </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"><!--//<![CDATA[
                                            
$(document).ready(function(){
	var f = document.forms[0];
	
	$("#allowance"   ).attr("dataType", "Require" ).attr("msg", "单台补贴金额不能为空");
	
	var acceptUploadFileExts = "gif, jpeg, jpg, png";
	$("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持。支持格式：" + acceptUploadFileExts).attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持。支持格式：" + acceptUploadFileExts).attr("require", "false").attr("accept", acceptUploadFileExts);
	
	$("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
            });
        });
    });

	$("#btn_submit").click(function(){//提交
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit) {
			
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			f.submit();
		}
		
	});

});

function deleteFile(id, file_path){
	 var k = window.confirm("您确定要删除吗?");
	 if (k) {
			$.post("JxcJnhmSellBill.do?method=deleteFile" , {
				id : id , file_path : file_path
			}, function(d){
				if(d == "success"){
					$("#del_" + id).parent().remove();
				}
			});
	 } 
}


//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>