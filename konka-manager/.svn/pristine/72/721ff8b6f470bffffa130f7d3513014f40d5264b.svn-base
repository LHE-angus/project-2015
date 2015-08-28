<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"><\/script >'); </script>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"><\/script >'); </script>

<div class="viewleft1">
  <div class="changeBox_a1">
    <div id="img_show">
      <div class="middle_img box_350_350">
       <c:if test="${not empty konkaBcompPd.picArray}">
     <c:forEach items="${konkaBcompPd.picArray}" var="cur" varStatus="vs" begin="1" end="1">
      <img src="${ctx}/${fn:substringBefore(cur, '.')}_350.jpg" data-cloudzoom="'zoomImage': '${ctx}/${fn:substringBefore(cur, '.')}_800.jpg', 'zoomPosition':'inside'" id="zoom1" class="cloudzoom" width="350" /> 
     </c:forEach></c:if></div>
      <div class="small_imgs">
        <div class="small_imgs_fixed">
          <ul>
             <c:if test="${not empty konkaBcompPd.picArray}">
              <c:forEach items="${konkaBcompPd.picArray}" var="cur" varStatus="vs" begin="1">
                <li> <a href="${ctx}/${cur}" class="cloudzoom-gallery" title="" data-mimg ="${ctx}/${fn:substringBefore(cur, '.')}_350.jpg" data-bimg="${ctx}/${fn:substringBefore(cur, '.')}_800.jpg"> <img src="${ctx}/${fn:substringBefore(cur, '.')}_120.jpg" alt="" width="60" height="60" /> </a> </li>
              </c:forEach>
            </c:if>
          </ul>
        </div>
      </div>
      <div id="zomm_prev"><a href="javascript:void(0);">后退</a></div>
      <div id="zomm_next"><a href="javascript:void(0);">前进</a></div>
      <div id="zoom_hover"></div>
    </div>
  </div>
</div>
<c:if test="${not empty konkaBcompPd.picArray}"> <c:forEach items="${konkaBcompPd.picArray}" var="cur" varStatus="vs" begin="1" end="1">
<script src="${ctx}/styles/customer/cloud-zoom/scripts/cloudzoom.js"></script> 
<script type="text/javascript">
jQuery(function($){
	//放大镜效果
	var options = {}; 
	var myInstance = new CloudZoom($('#zoom1'),options);
	
	//滑动门
	var _index = 0;
	var _per = 5;
	var _len = $('#img_show .small_imgs ul li').length;
				
	if(_len>_per){
		$('#img_show .small_imgs ul').width(_len*65); //改变UL宽度	
	}
	
	mySlider(_index); //初始化
	
	//Next
	$('#zomm_next').click(function(){
		_index = (_index>=(_len-1))? (_len-1) : (_index+1);
		mySlider(_index);
	});
	//Prev
	$('#zomm_prev').click(function(){
		_index = (_index<=0)? 0 : (_index-1);
		mySlider(_index);
	});
	//点击小图换大图
	$('#img_show .small_imgs li a').bind('click',function(){
		_index = $('#img_show .small_imgs li a').index(this);
		mySlider(_index);
		return false;
	});
	
	//滑动门函数
	function mySlider(i){
		myButtons(i);
		sliderUl(i);
		myCurrslider(i);
		chageZoom(i);
		sliderCurr(i);
	}
	
	//UL滑动,添加当前样式
	function sliderUl(i){
		var _o  = $('#img_show .small_imgs ul');
		if (_len > _per) {
			if((_len - i) >= _per){
				_o.animate({
					left:-(i*65)
				}, 300);
			} else { // add Ren,zhong 2013-07-17 解决bug
				_o.animate({
					left:-(_len-_per) * 65
				}, 300);
			}
		}
	}
	
	//当前状态
	function sliderCurr(i){
		var _o  = $('#img_show .small_imgs li');
		_o.eq(i).find('img').animate({
			opacity: 1
		}, 300).parent().parent().siblings('li').find('img').animate({
			opacity: 0.5	
		});	
	}
	
	//控制按钮显隐函数
	function myButtons(i){
		(i >= (_len-1)) ? $('#zomm_next').hide() : $('#zomm_next').show(); //下一个
		(i <= 0) ? $('#zomm_prev').hide() : $('#zomm_prev').show(); //上一个	
	}
	
	//滑动块函数
	function myCurrslider(i){
		var _o = $('#zoom_hover');
		var _o_left = 0;
		if(_len > _per){
			if((_len - i) < _per){
				//alert(_len + '-' + i + '<' + _per);
				_o_left = ((_per - _len + i) * 65) + 15;
			} else {
				//alert(_len + '-' + i + '>=' + _per);
				_o_left = 15;
			}
		} else {
			_o_left = (i * 65) + 15;
		}
		_o.animate({
			left: _o_left
		}, 300);
	}
	
	//改变放大图片函数
	function chageZoom(i){
		var _o = $('#img_show .small_imgs ul li a').eq(i);
		var _simg = _o.data('mimg');
		var _bimg = _o.data('bimg');
		myInstance.loadImage(_simg, _bimg);
		$('#curr_bigimg_eq').val(i);
	}			
});
</script></c:forEach></c:if>