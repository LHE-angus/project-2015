<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:0px;">
        <tr>
           <td width="1%" class="oartop">&nbsp;</td>
           <td width="10%" valign="bottom" bgcolor="#FFFFFF" nowrap="nowrap" valign="middle">
             <table width="100%" border="0" cellspacing="0" cellpadding="5">
               <tr>
                 <td height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF" valign="bottom">
                    <span id="current_city" style="span-weight:700;">
                      <img src="${ctx}/images/ajax-loader.gif" />
                    </span>
                 </td>
               </tr>
            </table>
           </td>
           <td class="oartop" align="left" style="padding-left:20px;" valign="middle">共找到
               <span id="count_sum">-</span> 个网点 
               (当前页R3用户<span id="count_A" style="color:red;">-</span>个,
                                          经销商<span id="count_B" style="color:red;">-</span>个,
                                          待开拓、开拓中网点<span id="count_C" style="color:red;">-</span>个
                                         其他网点<span id="count_D" style="color:red;">-</span>个).
               <span id="exc_time"></span>
           </td>
            <td class="oartop" align="right" style="padding-right:20px;" valign="middle">
               <span onclick="hidchart(this);" style="cursor:pointer;">隐藏销售额柱状图</span>
           </td>          
        </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:1px;margin-bottom:0;">
	   <tr>
		<td align="left" valign="top"><!-- 地图 -->
		<div id="map" style="width: 100%; background: #f1f1f1; margin-right: 7px;"></div>
		</td>
		<td width="6" valign="top" style="cursor:pointer;" onclick="hidList()">
           <img src="../../images/manager/arrow_right.gif" width="6" height="27" name="imgMap" id="imgMap" alt="显示列表"/>
		</td>
		<td id="shop_disp_td" width="225" align="right" valign="top" style="padding-right: 1px;margin-right:0px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="225" height="27" align="center" valign="top"
					style="background-image:url('${ctx}/images/entpshopgsite/images/ssuo_right.gif');">
				<span class="sbrown">网点展示</span></td>
			</tr>
			<tr>
				<td align="left" valign="top" class="sbox">
				<div id="shop_disp_div" class="listDiv" style="position: absolute;">
				<ul id="shop_disp" class="pro_list lh30" style="overflow-y: auto; width: 213px;">

				</ul>
				<div id="pager_control"
					style="height: 32px; text-align: center; color: #666; position: absolute; bottom: 0; width: 100%;"></div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
    <tr id="chart_3D_tr">
    <td colspan ="3"><div id="chartdiv"></div></td>
    </tr>
</table>
<div id="loading" style="display:none;text-align:center;"><img src="${ctx}/images/ajax-loader.gif" /> <span style="color:#999;">正在查询,请稍后...</span></div>
<div id="box">
  <table width="300" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="34">
      <img style="padding-left:8px;vertical-align:middle;" src="${ctx}/images/entpshopgsite/images/owin/jinpai.jpg" width="24" height="27" />
      <span style="padding-left:4px;vertical-align:middle;"><a id="shop_name" title="点击快速进入商铺" href="javascript:void(0);"></a></span>
      </td>
    </tr>
    <tr>
      <td valign="top">
           <table width="295" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
        	   <td colspan="4"></td>
            </tr>
            <tr>
              <td colspan="4" style="padding-left:8px;padding-top:4px;text-align:left;" id="qq_list">
          	    <a class="qq_a" href="tencent://message/?uin=qq_code" style="display:none;">
          	      <img src="${ctx}/images/entpshopgsite/images/owin/QQonline.jpg" width="74" height="22" />
          	   </a>
              </td>
            </tr>
            <tr id="onsaleInfoBox" style="display:none;">
               <td colspan="4" style="text-align:center;" id="onsale_info" title=""></td>
            </tr>
            <tr id="productBox" style="display:none;">
              <td width="50" class="product">
                 <a class="imgWrap" href="javascript:void(0);">
                   <img id="img_0" width="46" height="45" />
                 </a>
              </td>
              <td width="95" class="product" style="color:#555;padding-left:5px;">
          		<a id="md_name_0" href="javascript:void(0);">&nbsp;</a>
          		<br />
          		<a id="price_0" href="javascript:void(0);">&nbsp;</a> 元
          	  </td>
              <td width="50" class="product" id="product3">
                <a class="imgWrap" href="javascript:void(0);">
                <img id="img_1" width="46" height="45" /></a>
              </td>
              <td width="95" class="product" style="color:#555;padding-left:5px;" id="product4">
          		<a id="md_name_1" href="javascript:void(0);">&nbsp;</a><br />
          		<a id="price_1" href="javascript:void(0);">&nbsp;</a> 元
          	  </td>
             </tr>
           </table>
      </td>
    </tr>
  </table>
</div>
<ul style="display:none;">
	<li class="templete" style="display:none;">
		<span class="sbluer"></span>
		<div class="img_wrap" style="display:none;"><img width="140" height="97" /></div>
	</li>
</ul>