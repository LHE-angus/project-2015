<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/manager/ImportCustomerPdData" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="imp_rec_id" value="${af.map.imp_rec_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>文件：</td>
          <td>
          	<input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
          </td>
        </tr>
        <tr>
          <td><html-el:button property="btn_submit" styleId="btn_submit" styleClass="but_excel" value="导入" /></td>
          <td>
          <input type="button" name="" value="返回" onclick="location.href='${ctx}/customer/manager/JBaseGoods.do?mod_id=${af.map.mod_id}'" id="btn_reset" class="but5">
          </td>
        </tr>
        <tr>
      		<td colspan="2"><img src="${ctx}/customer/ImportCustomerPdData/TPL.jpg" alt="sry,load fail" border="1"></img></td>
      	</tr>
      	<tr></tr>
        <tr>
      		<td colspan="2">
      			<ul>
      				<li><font color="red" style="font-weight: bold;">1.下载Excel2003模板,导入内容放在第一个工作表中!</font>&nbsp;<a style="cursor:pointer;color:blue;"  href="${ctx}/files/template/excel/customer-goods-import-templete.xls">Excel2003模板下载</a></i><br/>
      				<li>2. 参照上图样例,字段顺序不能调整,红色区内字段为必填项</i><br/>
      				<li>3.“康佳商品标识”列请填写“是”或者“否”，“是”表示为康佳产品，“否”表示为非康佳产品</i><br/>
      				<li>4.“商品名称/规格型号”列可填写康佳产品规格型号，例如“LED47X8100PDE”</i><br/>
      				<li>5.“商品状态”列请填写“上架”或“下架”，下架的商品在录单时不可见，可在系统中修改商品状态为上架</i><br/>
<!--       				<li>6.“初始化库存”请如实填写客户截至到“统计时间”的产品库存余量</i><br/> -->
<!--       				<li>7.“进货单价”列填写初始化库存余量产品的进货成本价，单位为“元”</i><br/> -->
      				<li>6.“统计时间”请按格式“yyyy-MM-dd HH:mm”，例如：2014-09-09 09:09</i><br/>
      			</ul>
      		</td>
      	</tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请上传数据Excel文件");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
});
//]]></script>
</body>
</html>