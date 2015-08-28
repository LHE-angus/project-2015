<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/shoppingcar.css" />
<style type="text/css">
	.bank {float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/pay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}
	.bank--alipay {background-position: 0 -1023px;}	
	.bank--tenpay {background-position: 0 -1062px;}	
	.bank--unionpay{background-position: 0 -1333px;}
	.bank--epay{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/epay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--credit_card{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/credit_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--bank_card{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/bank_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--hdfk{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/hdfk.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}
 	
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:110%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:30%;width:550px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
fieldset p {line-height:24px;}	
 </style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jmpopups-0.5.1.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />

<!-- buy start -->
<form action="<c:url value='/zxmall/ShoppingCar.do' />" method="post" id="step_two_form">
<input type="hidden" name="method" value="createOrder" />
<input type="hidden" name="p_index" value="${p_index}" id="p_index" />
<input type="hidden" name="buy_json_object" value="${buy_json_object}" id="buy_json_object" />
<div class="card_center">
  <div class="card_a">我 的 购 物 车</div>
  <div class="card_b">
    <div class="card_dd">
      <ul>
        <li>1、我的购物车</li>
        <li><b>2、确认订单信息</b></li>
        <li>3、提交订单</li>
      </ul>
    </div>
  </div>
  <div class="shoppingcart_c">
    <h3 class="TitleName"><b>友情提示</b></h3>
    <div class="HideBox">
      <div style="font-size:14px; padding:10px 0px;">此地址同时用于邮寄发票，请务必认真填写，发票在一个月内寄出。</div>
    </div>
     <h3 class="TitleName"> <b>配送方式</b></h3> 
    <div class="HideBox" >
      <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right"  height="30" style="padding-left:30px;"><font color="red">*</font>配送方式： </td>
            <td align="left"><input name="is_zt" type="radio" value="0" id="is_zt_0" checked="checked" />
              <label for="is_zt_0" style="cursor:pointer;">送货上门</label>
              <input name="is_zt" type="radio" value="1" id="is_zt_1"  />
              <label for="is_zt_1" style="cursor:pointer;">自提</label>
            </td>
          </tr>
    </table>
    </div>
    <div id="self_div" style="margin-left:45px;display:none" >
        <c:if test="${empty esaList}">
        	<span style="color: red;">  对不起，该区域顺丰没有自提点，请联系客服！</span> 
        </c:if> 
    	<c:forEach items="${esaList}" var="cur" varStatus="vs">
    		<input id="self_way_${vs.count}" type="radio" name="self_way" value="${cur.id}"  style="margin-top:8px;"/>${cur.self_name} &nbsp;&nbsp;地址：${cur.self_addr}&nbsp;${cur.self_tel}<br/>
    	</c:forEach>
    </div>
    <h3 class="TitleName"><b>收货人信息</b><span><a style="cursor:pointer;display:${empty ecUserAddrs ? 'none' : ''}" id="rel_modify_btn">[修改]</a></span></h3>
   
    <c:forEach items="${ecUserAddrsList}" var="cur">
   	  <input type="radio" name="ecUserAddrsList" value="${cur.id }" onclick="ajaxAddr(${cur.id });"  ${ecUserAddrs.id eq cur.id ?'checked':'data=""'} /> <b>${cur.rel_name }</b>&nbsp; ${cur.map.full_name }&nbsp;${cur.rel_addr }&nbsp;${cur.rel_zip } 手机电话 ：${cur.rel_phone }&nbsp;${cur.rel_tel } 
   	  <br/> </c:forEach>	
    <div class="HideBox"> 
      <table border="0" cellspacing="0" cellpadding="0" class="mytable" width="80%" >
        <tbody>
          <tr>
            <td class="item" width="15%" ><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_name ? '' : 'none'};">*</span>收货人姓名： </td>
            <td  width="85%" >
            	<span class="modify_class_text" id="rel_name_txt"><c:out value="${ecUserAddrs.rel_name}" /></span>
            	<input id="rel_name" name="rel_name" type="text" maxlength="20" id="rel_name" class="input modify_class" value="${ecUserAddrs.rel_name}" style="display:${empty ecUserAddrs.rel_name ? '' : 'none'};" />
            </td>
          </tr>
          <tr>
            <td class="item" ><span class="modify_class" style="color:red;">*</span>收货人所在地区： </td>
            <td><span id="p_name_txt">${p_name} </span> &nbsp;           
              <select name="p_index1" id="p_index1" onchange="checkExpress();" class="input_txt" style="width:100px;">
                <option value="">-请选择-</option>
              </select>
              <select name="p_index2" id="p_index2" onchange="checkExpress();"  class="input_txt" style="width:100px;">
                <option value="">-请选择</option>
              </select>
              <font id="showmsg" color="red"></font> <font color="#cccccc"> 如果 所在地区不一致，请到商品页面重新选择配送区域</font>
              </td>
          </tr>
          <!--   
           <tr class="bill_top" id="t1" style="display:none;">
            <td align="right">              
             	<input type="checkbox" id="dispatch" name="is_ps" value="1"/>
             </td>
            <td align="left" > &nbsp;&nbsp;如果需要二次配送，请勾选（备注：二次配送需要额外收取配送费用，配送费用由服务商收取）  </td>
          </tr>
           <c:forEach items="${psList}" var="cur" varStatus="vs">
           <tr class="t2"  style="display:none;">
            <td align="left" style="padding-left: 122px;" colspan="2">              
             	<input id="ps_${vs.count}" type="radio" name="ps_id" value="${cur.id}" />${cur.self_name} &nbsp;&nbsp;地址：${cur.self_addr}&nbsp;${cur.self_tel}&nbsp;配送费用：${cur.self_money}<br/>
             </td>
          </tr>
          </c:forEach>
           -->
          <tr>
            <td class="item"><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_addr ? '' : 'none'};">*</span>收货人详细地址： </td>
            <td>${p_name}
            	<span class="modify_class_text" id="rel_addr_txt"><c:out value="${ecUserAddrs.rel_addr}" /></span>
            	<input name="rel_addr" type="text" maxlength="30" id="rel_addr" class="input modify_class" style="width:300px;display:${empty ecUserAddrs.rel_addr ? '' : 'none'};" value="${ecUserAddrs.rel_addr}" />
            </td>
          </tr>
          <tr>
            <td class="item"><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_phone ? '' : 'none'};">*</span>收货人手机号码： </td>
            <td>
            	<span class="modify_class_text" id="rel_phone_txt"><c:out value="${ecUserAddrs.rel_phone}" /></span>
            	<input name="rel_phone" type="text" maxlength="11" id="rel_phone" class="input modify_class" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:130px;ime-mode:Disabled;display:${empty ecUserAddrs.rel_phone ? '' : 'none'};" value="${ecUserAddrs.rel_phone}" />
              	<span class="modify_class" style="display:${empty ecUserAddrs.rel_phone ? '' : 'none'};">或者</span> 固定电话：
              	<span class="modify_class_text" id="rel_tel_txt" ><c:out value="${ecUserAddrs.rel_tel}" /></span>
                <input name="rel_tel" type="text" maxlength="20" id="rel_tel" class="input modify_class" style="width:130px;display:${empty ecUserAddrs.rel_tel ? '' : 'none'};" value="${ecUserAddrs.rel_tel}" />
                <span class="modify_class" style="display:${empty ecUserAddrs ? '' : 'none'}">用于接收发货通知短信及送货前确认</span></td>
          </tr>
          <tr>
            <td class="item"> 邮政编码： </td>
            <td>
            	<span class="modify_class_text" id="rel_zip_txt"><c:out value="${ecUserAddrs.rel_zip}" /></span>
            	<input name="rel_zip" type="text" maxlength="6" id="rel_zip" class="input modify_class" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:80px;ime-mode:Disabled;display:${empty ecUserAddrs.rel_zip ? '' : 'none'};" value="${ecUserAddrs.rel_zip}" />
              	<span class="modify_class" style="display:${empty ecUserAddrs ? '' : 'none'}">有助于快速确定送货地址</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <h3 class="TitleName"> <b>支付配送信息</b></h3>
    <div class="HideBox">
      <table border="0" cellspacing="0" cellpadding="0" class="mytable">
        <tbody>
          <tr>
            <td class="item"><font color="red">*</font>在线支付： </td>
            <td><ul>
                <table border="0"> 
                    <tr>
                      <td align="center" valign="middle" width="18"><input type="radio" name="pay_way" value="2" id="pay_way_2" /></td>
                      <td align="left" valign="middle"><label class="bank bank--alipay"for="pay_way_2" style="cursor:pointer;">支付宝</label></td>
                      <td align="center" valign="middle" width="22"><input type="radio" name="pay_way" value="5" id="pay_way_5" /></td>
                      <td align="left" valign="middle"><label class="bank--credit_card" for="pay_way_5" style="cursor:pointer;">民生e支付</label></td>
                      <td align="center" valign="middle" width="22"><input type="radio" name="pay_way" value="5" id="pay_way_6" /></td>
                      <td align="left" valign="middle"><label class="bank--bank_card" for="pay_way_6" style="cursor:pointer;">民生e支付 </label>&nbsp;&nbsp;<div style="margin-top:0px;width:550px;">&nbsp;&nbsp;即时到账，支持绝大数银行借记卡和部分银行信用卡&nbsp;&nbsp;<a href="${ctx}/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=837917" style="color: #08C;">查看银行及限额</a></div></td>
                     </tr> 
                </table>
              </ul> 
              </td>
          </tr>
           <tr>
            <td class="item"><font color="red">*</font>银行转账： </td>
            <td><ul>
                <table border="0">
                   <tr>
                      <td align="center" valign="middle" width="18"><input type="radio" name="pay_way" value="1" id="pay_way_1" /></td>
                      <td align="left" valign="middle" colspan="5"><label for="pay_way_1" style="cursor:pointer;">户名：康佳集团股份有限公司
						帐号：744557963343
						开户行：中国银行深圳福田沙河支行   （银行转账时请备注：康佳会员直销平台购机款 ,收款人手机:18207556878）</label>
						</td>
                     </tr> 
                </table>
              </ul> 
              </td>
          </tr>  
          <tr>
            <td class="item"><font color="red">*</font>送货日期： </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td><input id="deliver_time_1" type="radio" name="deliver_time" value="1" />
                        <label for="deliver_time_1"  style="cursor:pointer;">工作日、双休日与假日均可送货</label></td>
                      <td><input id="deliver_time_0" type="radio" name="deliver_time" value="0" />
                        <label for="deliver_time_0"  style="cursor:pointer;">只双休日、假日送货（工作日不用送）</label></td>
                      <td><input id="deliver_time_2" type="radio" name="deliver_time" value="2" />
                        <label for="deliver_time_2"  style="cursor:pointer;">只工作日送货（双休日、假日不用送）</label></td>
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
          <tr>
            <td class="item"> </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td>发货前电话确认：<input id="deliver_is_call_1" type="radio" name="deliver_is_call" value="1"  checked="true"/>
                        <label for="deliver_is_call_1"  style="cursor:pointer;">是</label></td>
                      <td><input id="deliver_is_call_0" type="radio" name="deliver_is_call" value="0" />
                        <label for="deliver_is_call_0"  style="cursor:pointer;">否</label></td> 
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
           <c:if test="${zxmall.user_type eq 2 && is_az eq 0 }">
          <tr>
            <td class="item"> </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td>是否需要调试/安装普通挂架/安装底座：<input id="deliver_way_1" type="radio" name="deliver_way" value="1"  />
                        <label for="deliver_way_1"  style="cursor:pointer;">是</label></td>
                      <td><input id="deliver_way_0" type="radio" name="deliver_way" value="0" checked="checked"/>
                        <label for="deliver_way_0"  style="cursor:pointer;">否 <span style="color: red;margin-left:10px; ">上门安装普通挂架/底座需要另外收费，具体费用请拨打400-880-0016咨询<a href="${ctx}/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=838940" style="color: #08C;">（参考费用）</a></span></label></td>  
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
          </c:if>
        </tbody>
      </table>
    </div>
    <h3 class="TitleName"> <b>发票信息</b></h3> 
    <div class="HideBox" >
      <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right"  height="30"><font color="red">*</font>是否开具发票： </td>
            <td align="left"><input name="bill_is_add" type="radio" value="1" id="bill_is_add_1" />
              <label for="bill_is_add_1" style="cursor:pointer;">是</label>
              <input name="bill_is_add" type="radio" value="0" id="bill_is_add_0"  />
              <label for="bill_is_add_0" style="cursor:pointer;">否</label>
            </td>
          </tr>
          <tr class="bill_top" style="display:none;">
            <td align="right" height="30"> 发票类型：  </td>
            <td align="left">              
             <span style="padding-left:180px;font-size:11px;color:#cccccc;width:200px;">按照国家规定，开具增值税发票，需要提供以下材料：</span><br/>
             <input name="bill_type" type="radio" id="bill_type_0" value="0" checked="checked" /> <label for="bill_type_0" style="cursor:pointer;">普通发票</label>
             <input type="radio" name="bill_type" value="1" id="bill_type_1" /><label for="bill_type_1" style="cursor:pointer;">增值税发票</label>
             <span style="padding-left:20px;font-size:11px;color:#cccccc;">1.开票函 ;2.营业执照复印件 ;3.组织机构代码证复印件; 4.国税副本复印件 ;5.地税副本复印件; 6.开户许可证 </span><br/>
             <span style="padding-left:180px;font-size:11px;color:#cccccc;width:200px;">7.一般纳税人资格证书复印件，   以上证件需加盖公章，具体事宜请联系您的专属客服 .</span>
              </td>
          </tr>
          <tr class="bill_top" style="display:none;">
            <td align="right" height="30"> 发票抬头： </td>
            <td align="left"><input name="bill_head" type="radio" id="bill_head_0" value="0" checked="checked" />
              <label for="bill_head_0" style="cursor:pointer;">个人</label>
              <input type="radio" name="bill_head" value="1" id="bill_head_1" />
              <label for="bill_head_1" style="cursor:pointer;">单位 </label></td>
          </tr>
          <tr id="trCompanyName" class="bill_top_1" style="display:none;">
            <td align="right" height="30"><font color="red">*</font>单位： </td>
            <td align="left"><input class="input" type="text" name="bill_company" id="bill_company" style="width:200px" maxlength="50" /></td>
          </tr>
          <!--  tr class="bill_top" style="display:none;">
            <td align="right" height="30"> 发票内容： </td>
            <td align="left"><input name="bill_content" type="radio" id="bill_content_0" value="0" checked="checked" />
             <label for="bill_content_0" style="cursor:pointer;">明细 </label>
             <input name="bill_content" type="radio" id="bill_content_1" value="1" />
             <label for="bill_content_1" style="cursor:pointer;">办公用品 </label>
             <input name="bill_content" type="radio" id="bill_content_3" value="3" />
             <label for="bill_content_3" style="cursor:pointer;">耗材 </label></td>
          </tr-->
      </table>
      </div>
    <h3 class="TitleName"> <b>备注信息</b></h3> 
    <div class="HideBox" >
      <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right"  height="30">备注信息： </td>
            <td align="left">
            	<input name="logistic_sn" type="text" maxlength="80" id="logistic_sn" class="input modify_class" style="width:600px;height:20px;" />
            </td>
          </tr>
      </table>
      </div>
      <div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
		<fieldset>		
			<h3 style="font-size:30px;text-align:center;font-weight:900;">温馨提示<span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
			<h1 style="color: red;font-size: 40px;">为保障您的权益：签收时请您一定开箱验机！如遇到碎屏或物品损坏请您直接拒收！</h1>
			<input name="chk_pic_print" type="checkbox" id="chk_pic_print" style="margin-top:5px;"></input> <label class="label_pic_print" for="chk_pic_print" style="margin-left:176px;"><b>选中查看详情</b></label>
			<div style="display: none" id="show_1">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;您收货时请要求开箱验货（检查产品外观、数量等，不接受通电检验，请谅解），若出现货损请当即拒收。若有要求先签收后开箱的，可以先签收，并当面开箱，具体如下：</p>
				<p>1、 若电视配件损失或破损，您可在运单上注明情况后，联系售后，或致电0755-61368827，提供外</p>
				<p>包装指定位置的照片，我们将尽快补发缺失配件。</p>
				<p>2、若电视本身缺失或破损，您可在运单上注明情况后，直接拒收。</p>
				<p>3、配送人员不得提供开箱验货服务，请直接拒收，并联系售后或致电 0755-61368827，对配送方</p>
				<p>进行投诉，我们会经过内部协调，并在下一个工作日二次配送。</p>
			</div>
			<div class="cookiechk">
				<input name="cancel" type="button" id="login_close" class="buttom"  style="margin-left: 225px"  value="确认" />
			</div>
		</fieldset>
	  </div>
      <h3 class="TitleName"> <b>验货与签字</b></h3>
      <div class="HideBox" >
       <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right"  height="30"><font color="red">*</font>验货与签字： </td>
            <td align="left"><label class="re_agreed"><input class="xz" id="xycheck" type="checkbox"  />&nbsp;为保障您的权益：签收时请您一定开箱验机！如遇到碎屏或物品损坏请您直接拒收！ </label> 
            </td>
          </tr>
        </table>
    </div>  
      
    <h3 class="TitleName"><b>商品清单</b></h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mytable3">
      <tbody>
        <tr class="item">
          <td width="15%">商品编号</td>
          <td>商品名称</td>
          <td width="10%">商品数量</td>
          <td width="10%"><c:if test="${zxmall.user_type eq 2 }">价格</c:if><c:if test="${zxmall.user_type eq 22 }">结算价</c:if></td>
          <td width="10%">物流费</td>
          <td width="10%">优惠</td>
          <td width="30%">增值服务</td>
        </tr><c:forEach items="${konkaBcompPdList}" var="cur">
	    <tr>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur.id}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;"><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" target="_blank">康佳${cur.pd_sn}</a></td>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur.map.buy_num}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;">￥<fmt:formatNumber value="${cur.map.price}" pattern="#,#00" /></td>
	          <td style="border-bottom:solid 1px #E6E6E6;">
	          <c:if test="${cur.map.rule_price eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price}" pattern="#,#00" />/台
	      	  </c:if>
	          </td>
	          <td style="border-bottom:solid 1px #E6E6E6;">
	          <c:if test="${cur.map.rule_price_2 eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price_2 ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price_2}" pattern="#,#00" />/台
	      	  </c:if>
	          </td>
	          <td valign="middle" style="text-align:left;border-bottom:solid 1px #E6E6E6;">
	          	 <c:if test="${empty cur.serviceIds}">没有选择增值服务</c:if>
	          	 <c:forEach items="${cur.ecBindingPdListForService}" var="cur1">
	          	 	<c:forEach items="${cur.serviceIds}" var="cur2">
	          	 		<c:if test="${cur1.id eq cur2}">${cur1.goods_name} ￥<fmt:formatNumber value="${cur1.price}" pattern="#0.00" />*${cur.map.buy_num}<br /></c:if>
	          		 </c:forEach>
	          	 </c:forEach>
	          </td>
	        </tr>
        </c:forEach>
      </tbody>
    </table><c:if test="${touch eq 1 }">
    <c:if test="${is_rebate eq 1 }"><p style="margin-left:20px;margin-top:10px;line-height:24px;">
    	商品佣金 ：￥<font color="red"> <fmt:formatNumber value="${all_rebates}" pattern="#,#00.00" /></font><br/>
    <c:forEach items="${konkaBcompPdList}" var="cur"><c:if test="${not empty cur.ecBcompPdRebates}">
 	   康佳${cur.pd_sn} ￥ <fmt:formatNumber value="${cur.map.price}" pattern="#,#00.00" />,佣金：<font color="red"><c:if test="${not empty cur.ecBcompPdRebates and cur.ecBcompPdRebates.b_value>0}">
              <c:if test="${cur.ecBcompPdRebates.b_type eq 0}"><fmt:formatNumber value="${cur.ecBcompPdRebates.b_value*cur.map.price/100}" pattern="#0.00" />
              </c:if><c:if test="${cur.ecBcompPdRebates.b_type eq 1}"><fmt:formatNumber value="${cur.ecBcompPdRebates.b_value}" pattern="#0.00" />
              </c:if></c:if><c:if test="${empty cur.ecBcompPdRebates}">0</c:if></font>* ${cur.map.buy_num}<br/>
    </c:if></c:forEach></p>
    <p style="margin-left:20px;margin-top:10px;color:#FF2200;font-weight:bold;line-height:24px;">     	
    	佣金抵扣：<input type="radio" name="is_deduction" id="is_deduction1" value="1" /><label for="is_deduction1" style="cursor:pointer;">使用佣金抵扣货款</label>
    	   <input type="radio" name="is_deduction" id="is_deduction0" value="0" /><label for="is_deduction0" style="cursor:pointer;">不使用佣金抵扣货款</label>
    </p></c:if>
    <p style="margin-left:20px;margin-top:10px;color:#FF2200;font-weight:bold;line-height:24px;">
    	购物券：<input type="radio" name="is_v" id="is_v1" onclick="showDialog();" value="1" /><label for="is_v1" style="cursor:pointer;">使用购物券</label>
    	 <input type="radio" name="is_v" id="is_v0" onclick="hideDialog();" value="0" /><label for="is_v0" style="cursor:pointer;">不使用购物券</label>
    	 <br/> <span style="margin-left: 10px;">（注：1.每张购物券只可使用一次，如购买商品金额少于兑换券面值，剩余面值将不予退回;2.使用购物券抵扣后，当商品应付金额小于1元时,需要支付1元作为开发票、保修凭证）</span>
    </p>
    <div id="val_div" style="margin-left:20px;display:none" ></div>
    <div id="vouchers_div" style="margin-top:10px;margin-left:20px;display:none"  >
     	请选择购物券
     	  <p style="margin-left:30px;margin-top:10px;line-height:24px;" id="vouchers_p"></p>
    </div></c:if> 
  </div>
  <div class="clear"></div>
  <div class="but_ca">
    <ul>
      <li><a href="<c:url value='/zxmall/ShoppingCar.do' />" id="step_one_go_shopping">返回购物车</a></li>
      <li><a style="cursor:pointer;" id="step_two_btn">提交订单</a></li>
    </ul>
  </div>
</div>
</form>
<!-- buy end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
var express_flg=0;        
var is_v=0;
$(document).ready(function(){
	is_v=0;
	
	// 区域 
		$("#p_index1").attr({"subElement": "p_index2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${p_index1}"});
		$("#p_index2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${p_index2}"});
		
		$("#p_index1").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "${p_index}", true,"p_index_join","");
		$("#p_index1").change();

		$("#xycheck").click(function(){
			if($(this).attr("checked")=="checked"){
				$("#login_div").fadeIn(500);
			}else{
				$("#chk_pic_print").removeAttr("checked");
				$("#show_1").css("display",'none');
				$("#login_div").fadeOut(500);
			}
		});
		$("#login_close").click(function(){$("#chk_pic_print").removeAttr("checked");$("#show_1").css("display",'none');$("#login_div").fadeOut(500);});  
		$("#x_close").click(function(){$("#chk_pic_print").removeAttr("checked");$("#show_1").css("display",'none');$("#login_div").fadeOut(500);}); 
		$("#chk_pic_print").click(function(){
			if($(this).attr("checked")=="checked"){
					$("#show_1").css("display",'block');
			}else{
					$("#show_1").css("display",'none');
			}
		});	
		
	   
	// 收货地址点击修改
	$(document).delegate("#rel_modify_btn", "click", function(){
		$(".modify_class").show();
		$(".modify_class_text").hide();
		$(".rel_modify_btn").hide();
	});


	//$("#dispatch").click(function(){
		//var len = "${fn:length(psList)}";
		//if(len==0){
			//alert("对不起，该区域没有二次配送点，请联系客服");
			//$("#dispatch").removeAttr("checked");
			//return;
		//}
		//if($(this).attr("checked")=="checked"){
			//$(".t2").show();
		//}else{
			//$(".t2").hide();
		//}
	//});
	
	$("#rel_name" ).attr("datatype", "Require").attr("msg", "请填写收货人姓名！");
	$("#rel_addr" ).attr("datatype", "Require").attr("msg", "请填写详细地址！");
	$("#rel_phone").attr("datatype", "Require").attr("msg", "请填写联系方式！");
	
	// 点击提交订单
	$(document).delegate("#step_two_btn", "click", function(){

		if($("#xycheck").attr("checked") != "checked"){
			alert("请您仔细阅读验货与签字，并选中");
			return false;
		}
		
		checkExpress();
		var bill_head = $("input[name='bill_head']:checked").val();
		if('1' == bill_head){
			$("#bill_company").attr("datatype", "Require").attr("msg", "请填写单位名称！");
		} else {
			$("#bill_company").removeAttr("datatype");
		} 

		var is_zt = $("input[name='is_zt']:checked").val();
		var ss_way = $("input[name='self_way']:checked").val();
		//var dispatch = $("input[name='is_ps']:checked").val();
		//var ps_id = $("input[name='ps_id']:checked").val();

		if('0'== is_zt){
			 if($("#p_index1 option").length>1){  
				 $("#p_index1").attr("datatype", "Require").attr("msg", "请选择收货地址！");
			 }else if($("#p_index2 option").length>1){
				 $("#p_index2").attr("datatype", "Require").attr("msg", "请选择收货地址！");
			 }
			 if(express_flg==1){
				// if(undefined == dispatch || undefined == ps_id){
					// alert("二次配送请选择配送点!");
					// return;
				// }
				alert("请重新选择收货地址!");
				return;	
			 }
		}else if('1' == is_zt && undefined != ss_way){
			$("#p_index1").removeAttr("dataType").removeAttr("msg").val("");
			$("#p_index2").removeAttr("dataType").removeAttr("msg").val("");
		}	


		 var is_self = $("input[name='is_zt']:checked").val();
			if('1' == is_self){
				var self_way = $("input[name='self_way']:checked").val();
				if(undefined == self_way){
					alert("提示，自提请选择自提点,或者选择其他配送方式！");
					return;
				}
			} 
		 
		if(Validator.Validate($("#step_two_form")[0], 3)) {
			var pay_way = $("input[name='pay_way']:checked").val();
			if(undefined == pay_way){
				alert("提示，请选择支付方式！");
				return;
			}

			var bill_is_add = $("input[name='bill_is_add']:checked").val();
			if(undefined == bill_is_add){
				alert("提示，请选择是否索要发票！");
				return;
			}
			
			var deliver_time = $("input[name='deliver_time']:checked").val();
			if(undefined == deliver_time){
				alert("提示，请选择送货日期！");
				return;
			}


			var msg = '${msg}';
			if(null!=msg&&""!=msg){
				if(!confirm(msg)){
					return false;
				}
			}
			
			
			$("#step_one_go_shopping").attr("disabled", true).html("提交中...");
			$("#step_two_btn").attr("disabled", true).html("提交中...");
			$("#step_two_form").submit();
		}
	});
	
	// 是否需要发票点击选择
	$(document).delegate("#bill_is_add_1, #bill_is_add_0", "click", function(){
		var value = $(this).val();
		if("1" == value){
			$(".bill_top").show();
			var bb = $("input[name='bill_head']:checked").val();
			if(bb == "1"){
				$(".bill_top_1").show();
			}
			$("#bill_div").css({"height":"180px"});
		} else {
			$(".bill_top").hide();
			$(".bill_top_1").hide();
			$("#bill_div").css({"height":"50px"});
		}
	});

	// 配送方式选择
	$(document).delegate("#is_zt_0, #is_zt_1", "click", function(){
		var value = $(this).val();
		if("1" == value){
			$("#self_div").show();
			//$("#dispatch").removeAttr("checked");
			//$("input[name='ps_id']").removeAttr("checked");
			//$("#t1").hide();
			//$(".t2").hide();
		} else {
			$("input[name='self_way']").removeAttr("checked");
			$("#self_div").hide();
			//if(express_flg =="1"){
				//$("#dispatch").removeAttr("checked");
				//$("#t1").show();
				//$(".t2").hide();
			//}
		}
	});

	
	
	// 发票抬头
	$(document).delegate("#bill_head_0, #bill_head_1", "click", function(){
		var value = $(this).val();
		if("1" == value){
			$(".bill_top_1").show();
		} else {
			$(".bill_top_1").hide();
		}
	});
	
	 if($("#p_index1 option").length<2){ 
		 $("#p_index1").hide();
	     $("#p_index2").hide();
	 }else  if($("#p_index2 option").length<2){ 
		 $("#p_index2").hide();
	 }
});

function checkExpress(){
	var stores="${stores}";
	var p_index="${p_index}";
	if($("#p_index2").val()!=""){
		p_index=$("#p_index2").val();
	}else if($("#p_index1").val()!=""){
		p_index=$("#p_index1").val();
	} 
	
	if(p_index!=""){
	$.ajax({
		type: "POST",
		url: "<c:url value='/zxmall/ShoppingCar.do' />",
		data: { "method":"ajaxExpress", "p_index":p_index,"stores":stores,"timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
			if(result.status == '0'){
				express_flg=1; 
				$("#showmsg").html(result.msg);
				
				//var is_zt = $("input[name='is_zt']:checked").val();
				//var ss_way = $("input[name='self_way']:checked").val();

				//if('0'== is_zt){
					//$("#dispatch").removeAttr("checked");
					//$("#t1").show();
					//$(".t2").hide();
				//}else if('1' == is_zt){
					//$("#dispatch").removeAttr("checked");
					//$("input[name='ps_id']").removeAttr("checked");
					//$("#t1").hide();
					//$(".t2").hide();
				//}	
				
			}else{
				var msg="";
				express_flg=0;
				if(result.maxDay!='0'){
				//msg="预计"+result.maxDay+"日内可送达！";
					msg="该区域可送达";
				}else if(result.express_desc!=null){
				msg="提示:"+result.express_desc;	
				}
				$("#showmsg").html(msg);
			}
		}
	});
	}else{
		express_flg=1;	
		$("#showmsg").html("对不起，送货区域加载错误，请刷新页面重试...");
	}
}
//切换送货地址
function ajaxAddr(addr_id){
	 $("#p_index1").val();
     $("#p_index2").val(); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/zxmall/ShoppingCar.do' />",
		data: { "method":"ajaxAddr", "addr_id":addr_id ,"timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
			if(result.status == '1'){
				$("#rel_name").val(result.rel_name);
				$("#rel_name_txt").html(result.rel_name); 
				
				$("#rel_addr").val(result.rel_addr);
				$("#rel_addr_txt").html(result.rel_addr); 
				
				$("#rel_phone").val(result.rel_phone);
				$("#rel_phone_txt").html(result.rel_phone); 
				
				$("#rel_tel").val(result.rel_tel);
				$("#rel_tel_txt").html(result.rel_tel); 
				
				$("#rel_zip").val(result.rel_zip);
				$("#rel_zip_txt").html(result.rel_zip); 
				
				$("#p_name_txt").html(result.p_name);
				$("#p_index").val(result.region);
				
				$("#p_index1").attr({"subElement": "p_index2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": result.country});
				$("#p_index2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": result.region});
				
				$("#p_index1").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", result.city, true,"p_index_join","");
				$("#p_index1").change();
				
				 $("#p_index1").show();
			     $("#p_index2").show(); 

				 $("#showmsg").html("");
				 // 更新送达天数
				 checkExpress();
				 if($("#p_index1 option").length<2){ 
					 $("#p_index1").hide();
				     $("#p_index2").hide();
				 }else  if($("#p_index2 option").length<2){ 
					 $("#p_index2").hide();
				 }
			}
		}
	}); 
}

function showDialog(){   
	 if(is_v==0){
		$("#val_div").show();
		$("#val_div").html("<font color='red'>验证支付密码...</font>");
	  	var url="<c:url value='/zxmall/ShoppingCar.do' />?method=checkPayPwd&time="+Math.random();  
	 	var str =window.showModalDialog(url,window,"dialogWidth=390px;dialogHeight=220px");   
	 	if (str == undefined) {  
	 		str = window.returnValue;  
	 	}  
	    if(str==1){
		  $("#val_div").html("<font color='red'>密码验证成功...正在获取购物券信息</font>");
		  //加载购物券			  
		  var ipt_html="<font color='red'>暂无可以使用的购物券</font>";
		  var buy_json_object=$("#buy_json_object").val();
		  $.ajax({
				type: "POST",
				url: "<c:url value='/zxmall/ShoppingCar.do' />",
				data: { "method":"ajaxVouchers","buy_json_object":buy_json_object,"timestamp":new Date().getTime()},
				dataType: "json",
				sync: true,
				error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
				success: function(result) {
					if(result.length>0){
						 ipt_html1="";
						 ipt_html2="";
						 for(var z=0;z<result.length;z++){
							 var id=result[z].id;
							 var title=result[z].title;
							 var price=result[z].price;
							 var is_other=result[z].is_other;
							 if(is_other!=1){							
								 var ipt1="<input type='hidden' value='"+price+"' id='vouchers_"+id+"' /><input type='checkbox' name='vouchers' value='"+id+"' id='vouchers_value' onclick='getVouchersValue();' /> "+title+",可抵扣金额： <font color='red'  >"+price+"元 </font><br/>";
								 ipt_html1=ipt_html1+ipt1;
							 }else{
								 var ipt2="<input type='hidden' value='"+price+"' id='vouchers_"+id+"' /><input type='radio' name='vouchers' value='"+id+"' id='vouchers_value' onclick='getVouchersValue();' /> "+title+",可抵扣金额： <font color='red'  >"+price+"元 </font><br/>";
								 ipt_html2=ipt_html2+ipt2;
							 }
						 } 						
						 if(""!=ipt_html1){
						 	ipt_html="<font color='red'>可以叠加使用的购物券：</font><br/>"+ipt_html1;
						 }
						 if(""!=ipt_html2){
							 ipt_html= ipt_html+"<font color='red'>只能使用一张的购物券：</font><br/>"+ipt_html2;
						 }
					}
				}
		 });
		  
		  $("#val_div").html("");
		  $("#val_div").hide();
		  $("#vouchers_div").show();
		  $("#vouchers_p").html(ipt_html); 
		  ipt_html="";
		  str=null;
		  window.returnValue=null;
		  is_v=1;
	  }else{
		  $("#val_div").html("<font color='red'>支付密码验证失败。</font>");
		  is_v=0;
	  }
	 }else{
		 $("#val_div").html("");
		 $("#val_div").hide();  
		 $("#vouchers_div").show(); 
	 }
}

function hideDialog(){  
	 is_v=0;
	 $("#val_div").html("");
	 $("#val_div").hide();   
	 $("#vouchers_div").hide(); 
	 $("#vouchers_p").html(""); 
}

function getVouchersValue(){
	var v=0;
	if($('#vouchers_value').length>0){
		var vouchers=document.getElementsByName("vouchers");
		for(var i=0;i<vouchers.length;i++){ 
			if(vouchers[i].checked==true){
				var	oid=vouchers[i].value; 
				v=v+parseInt($('#vouchers_'+oid).val());
			}
		}
		var vhtml="已选购物券金额合计：<font color='red'>￥"+v+"元<font>";
		$("#val_div").html(vhtml);
		$("#val_div").show();  
	}
}


//]]></script>
</body>
</html>