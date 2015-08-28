/*左侧菜单效果*/
function async_topMenu(object) {
    //避免鼠标触发时，前面的ajax尚未执行完毕
    $(object).children("div").html('<img src="/images/default/icon_loading.gif" title="数据加载中" style="width:32px; height:32px; padding:20px 0 20px 240px;">');
    if (running) {
        return false;
    }
    running = true;
    //var obj = obj;
    $.ajax({
        type: "GET",
        url : "/async_topMenu.php",
        dataType :"json",
        success: function(cateList){
            if(cateList) {
                //把数据innerHTML到所有标签内
                $("div.nav > ul > li").each(function(i,obj){
                    var key = $(obj).attr('id');
                    if(key == 'lastSelected') {
                        //有key的标签切换，无则是所有分类
                        var innerHTML = innerAll(cateList['all']);
                    }else{
                        var innerHTML = innerTag(cateList['tag'][i]);
                    }
                    $(obj).children("div").html(innerHTML);
                });
            }
        }
    });
}

//获取数据，并赋予html
function innerTag(cateList)
{
    //左边html
    var innerHTML = '<div class="subcate">';
	//分类
	if(cateList.category)
	{
		innerHTML += '<ul>';
		var flagMore = 0;
		var subList = cateList.category;
		for(var skey in subList)
		{							
			if(subList[skey].attributes.hidden != "yes" && flagMore < 12)
			{
				innerHTML += '<li>';
				innerHTML += '<a  href="'+subList[skey].url+'"';
				if(subList[skey].attributes.nofollow == "yes")
				{
					innerHTML += ' rel="nofollow"';
				}
				innerHTML += '>';
				
				if(subList[skey].attributes.strong == "yes")
				{
					innerHTML += '<strong>'+subList[skey].name+'</strong>';
				}else{
					innerHTML += subList[skey].name;
				}
				innerHTML += '</a>';
				innerHTML += '</li>';
				flagMore ++;
			}
		}
		innerHTML += '</ul>';
	}
	innerHTML += '</div>';

    //右边html
    innerHTML += '<div class="colright">';
    //推荐品牌
    innerHTML += '<div class="featuredbrand">';
    innerHTML += '<h3>推荐商品</h3>';
    //推荐品牌，只取6个
    if (cateList.BrandList) {
        var brandList = cateList.BrandList;
        innerHTML += '<ul>';
		for(var bkey in brandList) {
			if(bkey < 6) {
				
			    innerHTML += '<li>';
				innerHTML += '<a href="'+brandList[bkey].Url+'" title="'+brandList[bkey].BrandName+'"><img src="'+brandList[bkey].ImageUrl+'" alt="'+brandList[bkey].BrandName+'" /></a>';
				innerHTML += '</li>';
			}
		}
		innerHTML += '</ul>';
        innerHTML += '<div class="clear"></div>';
	}
    innerHTML += '</div>';
    //推荐商家
    innerHTML += '<div class="merchantpromotion">';
	innerHTML += '<h3>推荐商家</h3>';

	if(cateList.MerList) {
		var merList = cateList.MerList;
		innerHTML += '<ul>';
		for(var mkey in merList)
		{
			innerHTML += '<li><a href="'+merList[mkey].Url+'" title="'+merList[mkey].MerchantName+'">'+merList[mkey].MerchantName+'</a></li>';
		}
		innerHTML += '</ul>';
	}
	innerHTML += '</div>';

	innerHTML += '</div>';//colright end
	innerHTML += '<div class="clear"></div>';
    innerHTML += '</div>';
    return innerHTML;
}

function innerAll(cateList)
{
	var innerHTML = '<div class="subcate"><ul>';
	for(var key in cateList)
	{
		innerHTML  += '<li><a href='+key+' title='+cateList[key]+'>'+cateList[key]+'</a></li>';
	}
	innerHTML  += '</ul></div>';
	return innerHTML;
}

var running = false;//是否在运行中
var delay = 200;
var allCateTimer = null;

//当菜单html刚载入完成开始监控菜单是否展示
$('div#nav:not(.homenav)').hover(
    function(){
        var $this = this;
        allCateTimer = setTimeout(function() { 
            $($this).find('div.allcate > a').addClass('hover');
            $($this).children('ul').removeClass('disn');
        }, delay);
    },
    function(){
        var $this = this
        allCateTimer = setTimeout(function() {
            $($this).find('div.allcate > a').removeClass('hover');
            $($this).children('ul').addClass('disn');
            //兼容IE6显示所有select 元素
            $("select.menuVisible").each(function() {
              if ($(this).css("visibility") == 'hidden') {
                $(this).removeClass('menuVisible').css('visibility', 'visible');
              }
            });
        }, delay);
    }
);

$('div#nav > ul > li').hover(
    function(ev){
        var $this = this;
        allCateTimer = setTimeout(function() {
            var bottomHeight = document.documentElement.clientHeight - ev.clientY;
            
            if (bottomHeight <= 250) {
                $($this).addClass('over').find('div.submenubox').addClass('submenuboxBottom').removeClass('disn');
            } else {
                $($this).addClass('over').find('div.submenubox').removeClass('submenuboxBottom').removeClass('disn');
            }
            //兼容IE6隐藏所有select 元素
            $("select").each(function() {
              if ($(this).css('visibility') != 'hidden') {
                $(this).addClass('menuVisible').css('visibility', 'hidden');
              }
            });
            if ($($this).find("div.subcate").size()<= 0) {
                async_topMenu($this);
            }
        }, delay);
    },
    function(){
        var $this = this;
        if (allCateTimer) {
            clearTimeout(allCateTimer);
        }
        allCateTimer = setTimeout(function() { 
            $($this).removeClass('over').find('div.submenubox').addClass('disn');}, delay);
    }
);


/*首页图片轮播效果*/
$(function(){
	$('#number li').mouseover(function(){
		$("#"+this.id).addClass("selected").siblings().removeClass("selected");
		$('.slider1 li img').fadeIn(4000).attr("src","images/"+(this.id)+".jpg");
		});
	});
	

$(function(){
     var len  = $(".num > li").length;
	 var index = 0;
	 var adTimer;
	 $(".num li").mouseover(function(){
		index  =   $(".num li").index(this);
		showImg(index);
	 }).eq(0).mouseover();	
	 //滑入 停止动画，滑出开始动画.
	 $('.ad').hover(function(){
			 clearInterval(adTimer);
		 },function(){
			 adTimer = setInterval(function(){
			    showImg(index)
				index++;
				if(index==len){index=0;}
			  } , 2000);
	 }).trigger("mouseleave");
})
// 通过控制top ，来显示不同的幻灯片
function showImg(index){
        var adHeight = $(".content_right .ad").height();
		$(".slider").stop(true,false).animate({bottom : -adHeight*index},1000);
		$(".num li").removeClass("on")
			.eq(index).addClass("on");
}
	
/*标签切换*/
(function($){
	$.fn.extend({ 
		"tabs":function(options){    
			options=$.extend({
				_event:0,
				index:0,
				animateSpeed:500,
				tabWidth:997,
				tabHeight:350,
				opacity:false,
				xScroll:false,
				yScroll:false
		    },options);	
		    
			if(!$(this).hasClass("tabs")){
				$(this).addClass("tabs")
			};
			
			//对象函数
			var obj = $(this),
				  list = $(".tabs-list",obj),
				  _option = $(".tabs-option",list),
				  box = $(".tabs-box",obj),
				  content = $(".tabs-content",box);
			
			//参数	  
			var index = options.index,
				 tabWidth = options.tabWidth,
				 tabHeight = options.tabHeight,
				 listHeight = options.listHeight;
			
			//样式构造
			obj.css({"width":tabWidth,"height":tabHeight});
			list.css("width",tabWidth-8);
			box.css({"width":tabWidth-2});
			content.css({"width":tabWidth-2,"display":"none"}).eq(index).css("display","block");
			_option.eq(index).addClass("selected");
			
			//条件判断
			if(options._event==1){
				_option.click(function(){
					index=_option.index(this);
					_animate(index);
				});
			}else{
				_option.mouseenter(function(){
					index=_option.index(this);
					_animate(index);
				});	
			}
			
			if(options.xScroll){
				content.css({"display":"block","float":"left"});
				$(".fatbox",box).css({"width":(tabWidth-2)*content.length});
			}else if(options.yScroll){
				content.css({"display":"block","height":tabHeight-listHeight});
				box.css({"height":tabHeight-listHeight-1});
				$(".fatbox",box).css({"width":(tabWidth-2),"height":(tabHeight-listHeight-1)*content.length});
			}
			
			
			function _animate(index){
				if(options.opacity){
					opacityPlay(index);
				}else if(options.xScroll){
					xScrollPlay(index);
				}else if(options.yScroll){
					yScrollPlay(index);
				}else{
					Play(index);
				};
			};
			
			function Play(index){
				_option.removeClass("selected").eq(index).addClass("selected");
				content.css("display","none").eq(index).css("display","block");
			};
			
			function opacityPlay(index){
				_option.removeClass("selected").eq(index).addClass("selected");
				if(content.eq(index).css("display")== "none"){
					content.css("display","none").eq(index).fadeIn(options.animateSpeed);
				}
			};
			
			function xScrollPlay(index){
				_option.removeClass("selected").eq(index).addClass("selected");
				$(".fatbox",box).animate({
						marginLeft:-(tabWidth-2)*index
				},options.animateSpeed)
			}
			
			function yScrollPlay(index){
				_option.removeClass("selected").eq(index).addClass("selected");
				$(".fatbox",box).animate({
						marginTop:-(tabHeight-listHeight)*index
				},options.animateSpeed)
			}
			
			
			
		}
	})
})(jQuery)