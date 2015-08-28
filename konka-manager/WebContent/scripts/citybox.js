/*
 * 四级页面通用方法
 * based on jQuery
 * Author: 12050231
 */

// 根据页面需求构建简易对象
var SNProduct = SNProduct || {};

/**
 * [城市切换 V0.7]
 * 
 * example :
 * $PV.cityBox.init(function(){
 * 		//TO DO 
 * })
 * 		OR
 * $PV.cityBox.init();
 * 
 * @return {[type]} [description]
 */

SNProduct.cityBox = function() {
    //城市切换总控制元素
    var city = $("#citybox"), //初始不可见包裹容器
        cityBtn = $("#citybox_btn"), //初始可见城市选择
        cityData = $("#cityData"), //省、市、区数据载入容器
        pArea = city.find(".chooseArea").find("p"); //选择省市元素
    var ref; //this
    var cb = null;
    return {
    	//
        init: function(callback) {
        	if(arguments.length > 0){
        		cb = callback;
        	}
            ref = this;
            //激活选择省面板，toggle功能
            cityBtn.click(function(e) {
                if(city.css('display')=="none" || city.hasClass("hide")){
                    $(this).addClass('select');
                    city.removeClass("hide").show();
                    //fix for ie6
                    if($.browser.msie && ($.browser.version == "6.0")){
                    	cityData.css("zoom", 1)
                    }    
                }else{
                    $(this).removeClass('select');  
                    city.hide().addClass("hide");
                }
                e.stopPropagation();                           
                e.preventDefault();
            })
			city.click(function(e){
				e.stopPropagation();
			})
			$("body").click(function(){
				if(city.css('display') == "block"){
					ref.reset();
				}
	            return;
			});
			//内部省市区切换
            pArea.click(function(){
            	var index = $(this).index();
            	if($(this).hasClass("disable")){
            		return;
            	}
            	$(this).addClass("cur").siblings().removeClass("cur");
            	city.find(".arriveBox").show();
            	cityData.find("table").eq(index).show().siblings().hide();
            	if($("#temp_iframe").length > 0){
            		$("#temp_iframe").height(city[0].offsetHeight);
            		//return;
            	}
            	//fix for ie6 select
            	if($.browser.msie && ($.browser.version == "6.0") && $("#temp_iframe").length < 1){
                    var iframe = document.createElement("iframe");
                    iframe.id = "temp_iframe";
                    city.after(iframe);
                    $(iframe).css({
                        width : city.width(),
                        height : city[0].offsetHeight,
                        position : "absolute",
                        "z-index" : 10,
                        opacity : 0,
                        top : 25, // 微调数据
                        left : 75  //微调数据
                    });
                }
                if($(this).hasClass("loaded")){
                	return;
                }
                ref.getAjaxData($(this).attr("data-url"));
            });
            this.choose();
        },
        /**
         * [choose description]
         * 选择省市区
         */
        choose: function(){
        	var pCur;
        	var p1 = pArea.eq(0), p2 = pArea.eq(1), p3 = pArea.eq(2);
        	//绑定方式后期优化
        	cityData.find("a").click(function(e){
	        	pCur = city.find(".chooseArea").find("p.cur").index();
            	e.preventDefault();
            	if(this.href.indexOf("javascript") > -1){
            		alert("很抱歉，西藏目前无法送达");
            		return;
            	}
            	//直辖市
            	if($(this).attr("data-city") == "true"){
            		p1.text($(this).text());
            		cookieObj.setCookie("province",$(this).attr("name"));
            		p2.text($(this).text()).addClass("disable");
            		var url = "getDistrict_"+$(this).attr("name")+".html";
            		cookieObj.setCookie("cityId",$(this).attr("name"));
            		p3.addClass("cur").text('请选择区县').siblings().removeClass("cur").attr("data-url", url);
            		cityData.find("table").eq(2).show().siblings().hide();
            		cookieObj.setCookie("districtId",$(this).attr("name"));
            		ref.getAjaxData(url);
            		ajaxObj.checkCityCanSale();
            		return;
            	}
            	//其他省条件判断
            	if(pCur == 0){
            		p2.text('请选择市').addClass("disable").attr("data-url", $(this).attr("href"));
            		p3.text('请选择区县').addClass("disable");
                	cookieObj.setCookie("province",$(this).attr("name"));
            	}
            	//市条件判断
            	if(pCur == 1){
            		p3.attr("data-url", $(this).attr("href"));
                	cookieObj.setCookie("cityId",$(this).attr("name"));
            	}
            	//最后一步区县
            	if(pCur == 2){
            		/**
            		 * 最后执行回调作用域
            		 */
            		p3.text($(this).text());
            		if(pArea.eq(1).hasClass("disable")){
            			cityBtn.find(".ctext").text('');
            		}else{
            			cityBtn.find(".ctext").text(p2.text());
            		}
            		cityBtn.find(".ptext").text(p1.text());
			        cityBtn.find(".atext").text(p3.text());
			        cookieObj.setCookie("districtId",$(this).attr("name"));
            		ref.reset();
            		if(typeof cb == "function"){
            			//内可以传参
            			cb(this.innerHTML);
            		}
            		ajaxObj.checkCityCanSale();
            		return;
            	}
            	ref.getStatus(pArea,pCur,$(this).text());
            	ref.getAjaxData(this.href);
            });
        },
        //省市区层级自动切换
        getStatus: function(_this,i,text){
			_this.eq(i+1).addClass("cur").siblings().removeClass("cur");
        	cityData.find("table").eq(i+1).show().siblings().hide();
        	_this.eq(i).text(text);
    	},
    	//异步获取省市区的数据
    	//数据源格式定义为
    	//C9183常州市||H9181淮安市||L9182连云港市||N9173南京市||N9177南通市||S9176苏州市||S9185宿迁市||T9184泰州市||W9174无锡市||X9180徐州市||Y9178扬州市||Y9179盐城市||Z9175镇江市 
        getAjaxData: function(url) {
        	var index = city.find(".chooseArea").find("p.cur").index();
        	if(index == 0){
		    	return;
		    }
	        var re = /([A-Za-z]+)([0-9]+)(.[^\|]+)()/g;
	        var arr = []  //
	        var s = null; //
	        var template = '<tr>';
		    cityData.find("table").eq(index).html('<tr><td>加载中...</td></tr>');
		    var randomparam = Math.floor(Math.random() * 1000000);
	        $.get(url+"?param="+randomparam, function(data) {
	        	while(s = re.exec(data)) {
	                //print_r >> ["Z9175镇江市", "Z", "9175", "镇江市", ""]
	                arr.push([s[2],s[3]]);
	            }
	            for(var i = 0;i<arr.length; i++){
	            	if(i%4 == 0 && i != 0){
	            		template = template + '</tr><tr>';
	            	}
	                template += '<td><a name="'+arr[i][0]+'" href="getDistrict_'+arr[i][0]+'.html">' + arr[i][1] + '</a></td>';
	            }
	            template = template + '</tr>';
	    		cityData.find("table").eq(index).html(template);
	    		cityData.find("a").unbind("click");
	    		ref.choose();
	           	pArea.eq(index).removeClass("disable").addClass("loaded");
	           	$("#temp_iframe").height(city[0].offsetHeight);
	        });
        },
        //初始化
        reset: function(){
            city.hide();
	        $("#temp_iframe").remove();
	        cityBtn.removeClass('select');
	        pArea.removeClass("cur");
	        cityData.hide();
	        cityData.find("table").hide();
	        //fix for ie6
	        if($.browser.msie && ($.browser.version == "6.0")){
            	cityData.css("zoom", 0)
            }
        }
    };
};

SNProduct.Util = {
		// 四级页面
		lazyload: function(box){
			showFootImg();
			$(window).scroll(function(){
				$(box).find("img").each(function(){
					if($(window).scrollTop() > $(this).offset().top - $(window).height() && $(this).attr("src2")){
						$(this).attr("src", $(this).attr("src2")).removeAttr("src2");
					}
				});
				showFootImg();
			});
		},
		alertBox: function(){
			
		}
	};

SNProduct.Page = {
		pro_imgzoom : function(w, h) {
			var zoompos = {x:0,y:0};//定义一个对象，缓存x,y变量.
			var $pre = $("#preview");
			var $preimg = $pre.find("img");
			var p_w  = w;
			var p_h  = h;
			var viewBox = '<div id="preview" class="viewBox"><img /></div>';
			var zoomMove = '<a class="zoomplePopup" target="_blank"></a>';
			$("#PicView").find("img").load(function(){
				if($(this).height() < 400){
					$(this).animate({"margin-top" : 30}, 1000);
					p_w = 612;
					p_h = 520;
					$("#preView_box").find("img").css({"margin-top" : 8})
				}
			});
			var delay;
			if(!!window.ActiveXObject&&!window.XMLHttpRequest){
				$pre.after("<iframe id='maskA' style='position:absolute;border:none;opacity:0;filter:alpha(opacity=0);z-index:1;display:none;'></iframe>");
			}
			$("#PicView").bind("mouseover",function(e){
				var _e = e;
				var $this  = $(this);
				var img = new Image();

				if( $("#preview").length == 0 ){
					$this.after(viewBox);
				}
				
				if( $this.find(".zoomplePopup").length == 0 ){
					$this.append(zoomMove);
				}
				$("#preview").find("img").attr("src",$this.find("img").attr("src2"))
				delay = setTimeout(function(){
					$(img).load(function(){
						$pre = $("#preview");
						$preimg = $pre.find("img");
						var $zoom = $this.find(".zoomplePopup");
						$zoom.attr("href", $this.find(".view-img").attr("href"));
						$zoom.show();
						$pre.fadeIn();
						// $pre.show();
						$("#maskA").css({top:0,left:402,opacity:0,width:$pre.width(),height:$pre.height()}).show();
						// $preimg.attr("src", $preimg.attr("src2"));
						PositionPopupZoom( $this , $zoom , _e.pageX , _e.pageY , p_w , p_h );
						$this.bind("mousemove",function(e){
							setTimeout(function(){
								PositionPopupZoom( $this , $zoom , e.pageX , e.pageY , p_w , p_h);
							},10);
						});
					}).attr("src", $this.find("img").attr("src2"));
				},200);
				
			}).bind("mouseleave",function(){
				if(!!window.ActiveXObject&&!window.XMLHttpRequest){
					$("#maskA").hide();
				}
				var $this  = $(this);
				var $zoom = $this.find(".zoomplePopup");
				$zoom.hide();
				$pre.fadeOut();
				$this.unbind("mousemove");
			}).mouseout(function(){
				clearTimeout(delay);
			});
			/*
				wrap : 当前绑定的元素
				zoom : 当前绑定的元素里的popupzoom元素
				x, y : 当前鼠标在页面上的位置
				w, h : 放大的图片的高度和宽度,用来计算比例
			*/
			function PositionPopupZoom( wrap , zoom , x , y , w , h){
				var wrapLeft = wrap.offset().left;
				var wrapTop =  wrap.offset().top;
				var zoomWidth = zoom.width();
				var zoomHeight = zoom.height();
				var wrapWidth = wrap.width();
				var wrapHeight = wrap.height();
				
				zoompos.x =  x -wrapLeft - (zoomWidth/2);
				zoompos.y =  y -wrapTop- (zoomHeight/2);
				if( zoompos.x <= 0 ){
					zoompos.x  =  0;
				}
				if( zoompos.y <= 0 ){
					zoompos.y  =  0;
				}
				if( zoompos.x + zoomWidth >= wrapWidth){
					zoompos.x  = wrapWidth  -  zoomWidth; 
				}
				if( zoompos.y + zoomHeight >= wrapHeight){
					zoompos.y  = wrapHeight  -  zoomHeight; 
				}
				//放大比例
				var xRatio = w / wrapWidth;  
				var yRatio = h / wrapHeight;
				//设置位置
				zoom.css({left:zoompos.x,top:zoompos.y});
				$preimg.css({left: -(zoompos.x * parseFloat(xRatio)) , top:  -(zoompos.y * parseInt(yRatio)) }); 
			}		

		},
		IE6 : function(callback){
			var isIE=!!window.ActiveXObject; 
			var isIE6=isIE&&!window.XMLHttpRequest;
			if(isIE6){
				callback();
			}
		},
		pro_view_slide : function(){
			var delay;
			var i = 0;
			var box = $("#preView_box");
			box.find("li").mouseover(function(){
				var _this = $(this);
				delay = setTimeout(function(){
					_this.addClass("cur").siblings().removeClass("cur");
					$("#PicView").find("img").attr("src",_this.find("img").attr("src2"));
					$("#PicView").find("img").attr("src2",_this.find("img").attr("src2"));
				},200);
				
			}).mouseout(function(){
				clearTimeout(delay);
			});

			var up = $("#preView_box").find("p.prev");
			var down = $("#preView_box").find("p.next");
			var len = $("#preView_box").find("li").length;
			if(len < 5){up.addClass("false")};
			var ul = $("#preView_box").find("ul");
			if(len <= 5){
				len = 5;
				down.find("a").addClass("false");
				up.find("a").addClass("false");
				return;
			}
			down.click(function(){
				i++;
				up.find("a").removeClass("false");
				if(i >= len - 5){
					i = len - 5;
					$(this).find("a").addClass("false");
				}
				move(i);
				
				/*zhangweiwei start*/
				var lazyImgs=$("#preView_box").find("li").find("img[lazysrc]");
				var lazyLen=lazyImgs.length;
				if(lazyLen>0){
					var img=lazyImgs.eq(0);
					img.attr("src",img.attr("lazysrc")).removeAttr("lazysrc");
				
				}
				/*zhangweiwei end*/
			});

			up.click(function(){
				down.find("a").removeClass("false");
				i--;
				if(i <= 0){
					i = 0;
					$(this).find("a").addClass("false");
				}
				move(i);
			})
			
			function move(i){
				ul.stop(true).animate({"margin-left" : -67 * i});
			}

		},
		pro_video_show : function(){
			$("#view_video").click(function(){
				if($("#video_area").find("a").length > 0){
					return;
				}
				$("#video_area").addClass("video_mask loading");
				var tmp_html = $("#video_html").html();
				$("#video_area").append(tmp_html);
			});
			$("#video_area").find("a.close").live("click", function(){
				$(this).prev().remove();
				$(this).remove();
				$("#video_area").removeClass("video_mask loading");
			})
		},
		pro_Num : function(min, max, one){

			var mins = $("#pro_count").find("li.mins");
			var add = $("#pro_count").find("li.add");
			var input = $("#pro_count").find("input");
			var i = min;
			input.val(min);
			input.keyup(function(){
				this.value = this.value.replace(/\D/g, "");
				if(parseInt($(this).val()) > max){
					$(this).val(max)
				}
				if(isNaN($(this).val()) || parseInt($(this).val()) == 0 || parseInt($(this).val()) < min || $(this).val() == ""){
					$(this).val(min)
				}
				i = $(this).val();
			});
			mins.click(function(){
				i--;
				if(i <= min){
					input.val(min);
					i = min;
				}else{
					input.val(i)
				}
			});
			add.click(function(){
				i++;
				if(i > max){
					i = max;
				}
				input.val(i);

				if(one){
					//i = 1;
					//input.val(i);
					$("#jnbt").fadeIn().delay(5000).fadeOut(500, function(){
						$("#jnbt").remove();
					});
				};
			});
			
			this.IE6(ie6Hover);
			function ie6Hover(){
				$("#pro_style").find("li").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				})
				$("#pro_color").find("li").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				})
				$(mins).hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				$(add).hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
			}
				
		},
		// 旧版选项卡
		pro_tab : function(opt){
			var def = {
				btn : "",  
				btnClass : "cur",
				btnTag: "li",
				box : "",
				boxClass : ".pro-tab-box",
				type : "click",
				more : "",
				callback : "",
				ajax_data : ""
			}
			$.extend(def, opt);
			$(def.btn).find(def.btnTag).bind(def.type, function(){
				var index = $(this).index();
				if($(def.box).find(def.boxClass).eq(index).length < 1){
					$(this).removeClass(def.btnClass);
					return;
				}
				$(this).addClass(def.btnClass).siblings().removeClass(def.btnClass);
				$(def.box).find(def.boxClass).eq(index).show().siblings(def.boxClass).hide();
				if(typeof def.callback == "function"){
					def.callback(index, def);
				}
				if(typeof def.ajax_data == "object" && $(def.box).find(def.boxClass).eq(index).html().length < 20){
					$(def.box).find(def.boxClass).eq(index).html("<div class='loading' style='padding:100px 0'></div>");
					$.get(def.ajax_data[index-1].url, function(data, status, xhr){
						$(def.box).find(def.boxClass).eq(index).html(data);
						if(typeof def.ajax_data[index-1].callback == "function"){
							def.ajax_data[index-1].callback(index, def);
						}
					})
				}

				// //only fix for safari scroll
				// if(def.btn == "#pro_detail_tab" && window.navigator.userAgent.indexOf("AppleWebKit") > -1){
				// 	$(window).scrollTop($("#placeHolder_fixed").offset().top);
				// }
			});
		},
		//简易toggle方法
		pro_toggle:function(ctrl, box, type, ctrlClassName){
			$(ctrl).bind(type, function(){
				if(typeof box == "object"){
					$(box.el).toggleClass(box.clsName);
				}else{
					$(box).toggle();
				}
				if(ctrlClassName){
					$(ctrl).toggleClass(ctrlClassName);
				}
				
			});
		},
		pro_slide : function(box, moveBox, px, step, check){
			var i = 0;
			var li = check ? "li:visible" : "li";
			var stepTemp = step ? $(moveBox).find(li).length / step : $(moveBox).find(li).length;

			var lenTemp = stepTemp == Math.floor(stepTemp) ? Math.floor(stepTemp) - 1 : Math.floor(stepTemp);
			var len = lenTemp ;
			$(box).find(".next").click(function(){
				i++;
				if(i > len){
					i = 0;
				}
				move(i);
			});
			$(box).find(".prev").click(function(){
				i--;
				if(i <= 0){
					i = 0;
				}
				move(i);
			});
			function move(i){
				$(moveBox).stop().animate({left : -i * px});
			}
			this.IE6(ie6Hover);
			function ie6Hover(){

				$(box).find(".next").hover(function(){
					$(this).toggleClass("hover");
				});

				$(box).find(".prev").hover(function(){
					$(this).toggleClass("hover");
					// $(this).find("span").toggleClass("leftfix");
				});

			}
		},
		pro_dapei : function(i, def){
			console.log(i)
			var len1 = 4, len2 = 7, width1000 = 464, step1 = 6, step2 = 3;
			if($("body").hasClass("root1000")){
				len1 = 3, len2 = 5, width1000 = 310, step1 = 6, step2 = 2;
			}
			$("#dapei_tab").find("li").click(function(){
				var index = $(this).index();
				$("#dapei_slide").find("li").each(function(){
					$("#dapei_slide").find("li").hide();
					$("#dapei_slide").find("li.type" + index ).show();
				});
				if(index == 0){
					$("#dapei_slide").find("li").show();
				}
				$(this).addClass(def.btnClass).siblings().removeClass(def.btnClass);
				$("#dapei_slide").css("left",0);

			});
			if($("#dapei_slide").find("li:visible").length < len1 ){
					$("#dapei_slide_box").find(".ctrl-btn").unbind("click");
				}else{
					SNProduct.Page.pro_slide("#dapei_slide_box", "#dapei_slide", width1000, step2, true);
				}
			
			$("#dapei_slide").find("li").find("input").click(function(){
				var parent = $(this).parent().parent();
				var yhj = parseFloat($("#yhj").text());
				var yuanjia = parseFloat($("#yuanjia").text());
				var pro_price = parseFloat($("#pro_jiage").text());
				var jiesheng = parseFloat($("#jiesheng").text());
				var ygj = parseFloat(parent.find(".ygj").text());
				var tcj = parseFloat(parent.find(".tcj").text());
				if($(this)[0].checked){
					
					parent.addClass("cur");
					$("#yhj").text(parseFloat(tcj + yhj).toFixed(2));
					$("#yuanjia").text(parseFloat(ygj + yuanjia).toFixed(2));
					yuanjia = parseFloat($("#yuanjia").text());
					yhj = parseFloat($("#yhj").text());
					$("#jiesheng").text(parseFloat(yuanjia - yhj).toFixed(2));
					$("#peijian_num").text(parent.parent().find("input:checked").length);

				}else{
					yuanjia = parseFloat($("#yuanjia").text());
					yhj = parseFloat($("#yhj").text())
					$("#yhj").text(parseFloat(yhj - tcj).toFixed(2));
					$("#yuanjia").text(parseFloat(yuanjia - ygj).toFixed(2));
					yuanjia = parseFloat($("#yuanjia").text());
					yhj = parseFloat($("#yhj").text())
					$("#jiesheng").text(parseFloat(yuanjia - yhj).toFixed(2));
					$("#peijian_num").text(parent.parent().find("input:checked").length);
					parent.removeClass("cur");	
				}
				
			});

		},
		// 旧
		pro_fixed : function(elem, wrapWidth){
			var _this = this;
			var top = $(elem).offset().top;
			var oH = $(elem).height() + 2;
			var isIE=!!window.ActiveXObject; 
			var isIE6=isIE&&!window.XMLHttpRequest;
			$(elem).after("<div id='placeHolder_fixed' class='cl'>&nbsp;</div>");
			$("#placeHolder_fixed").height(oH).hide();
			checkWidth();
			checkPosition();

			$(window).scroll(function(){
				checkWidth();
				checkPosition();
			});
			$(window).resize(function(){
				checkWidth();
				checkPosition();
			});
			var fix = 200;
			if($("body").hasClass("gift_fix")){
				fix = 0
			}
			function checkPosition(){
				if($(window).scrollTop() > top -10){
					$("#placeHolder_fixed").show();
					if(isIE6){
						ie6fixed();
					}else{
						$(elem).css({"position" : "fixed", left : $(window).width() / 2 - wrapWidth / 2 + fix,  top : 0, width : $(elem).width(), "z-index" : "2000"});
					}

				}else{
					$("#placeHolder_fixed").hide();
					$(elem).removeAttr("style");
				}

				function ie6fixed(){
					$(elem).css({"position" : "absolute", left : $(window).width() / 2 - wrapWidth / 2 + fix, top : $(window).scrollTop(), width : $(elem).width(), "z-index" : "2000"});
					
				}
			}
			function checkWidth(){
				if($(window).width() < 1190){
					wrapWidth = $(window).width();
				}else{
					wrapWidth = $("body").hasClass("root1000") ? 990 : 1190;
				}
			}
		},
		pro_manyidu : function(Fn){
			return {
				init: function(Fn){
					Fn(this);
				},
				showWidth: function(o){
					var i = 0;
					var move = setInterval(function(){
						//var d = Math.ceil((100 - i)/30);
						if(parseInt($(o).parent().attr("w")) == 0){
							i = 0;
						}else{
							i = i + 1;
						}
						$(o).width(i + "px");
						if(i >= parseInt($(o).parent().attr("w"))){
							//alert(0)
							clearInterval(move);
						}
					},15);
				}
			}
		},
		shop_list_show: function(){
			var _list = $("#c_shop_list").find(".list-show");
			var delay = null;
			var t = null;
			$("#c_shop_list").find("li").hover(function(){
				var $this = $(this);
				delay = setTimeout(function(){
					clearTimeout(t);
					if($this.index() > 0){
						_list.show();
						setData($this);
						_list.stop().animate({
							top: $this.position().top - 1
						});
					}else if($this.index() == $("#c_shop_list").find("li").length - 1){
						_list.show();
						setData($this);
						_list.stop().animate({
							top: $this.position().top - 1
						});
					}
				},200);
			},function(){
				t = setTimeout(function(){
					_list.hide();
				},210)
				
				clearTimeout(delay);
			});
			function setData(_li){
				_list.find(".shop_name").html(_li.find(".shop_name").text());
				_list.find(".data-star").animate({
					width: _li.find(".shop_name").attr("data-star") + "px"
				}, 800);
				_list.find(".data-percent").html(_li.find(".shop_name").attr("data-percent"));
			}
		},
		checkwords: function(box){
			box.find("textarea").unbind("keyup");
			box.find("textarea").keyup(function(){
				$(this).parent().find(".wordsconut").text(500 - this.value.length);
				if(this.value.length > 499){
					this.value = this.value.toString().substr(0, 499);
				}
			});
		},
		pinglunFn: function(box){
			// 评论字数
			this.checkwords(box);
			// 评论留言
			$(".comment-item").find(".reply-list").find("li").hover(function(){
				$(this).addClass("cur").siblings().removeClass("cur");
			},function(){
				$(this).removeClass("cur");
				$(this).find(".replybox").hide();
			});
			box.find('.comment-item').each(function(){
				var $this = $(this);
				$this.find(".optionbox").find(".replybtn").click(function(){
					$this.find(".replybox-main").toggle();
					$this.find(".reply-list").toggle();
					$this.find(".view-more-comment").toggle();
				});
				$(this).find(".single-reply").click(function(){
					$(this).parent().parent().find('.replybox').toggle();
				})
			})
		},
		commentJump: function(){
			$("#jump_to_comment").click(function(){
				$("#pro_detail_tab").find('.pro-tab-a').find('li').eq(2).click();
			})
		},
		/**
		 * 商品详情新功能 update 20130329 by 12050231
		 */
		pro_tab_jump: function(){
			var that = this;
			var trigger = $("#pro_detail_tab");
			var detailBox = $("#detail_content"),
				canshuBox = $("#canshu_box"),
				zxqdBox = $("#zxqd_box"),
				shohouBox = $("#shouhou_box"),
				pingjiaBox = $("#pingjia_box"),
	            ruleBox = $("#rule_box");
			return {
				init: function(){
					//点击跳转
					this.jump();
					//跟随购物车
					this.addCart();
					//异步载入评论数据
					//this.loadDataScroll(this.pinglunData);
					//异步载入咨询数据
					//this.loadDataScroll(this.zixunData);
					//异步载入讨论数据
					//this.loadDataScroll(this.discussData);
				},
				/**
				 * 购物车跟随
				 */
				addCart: function(){
					var cur = null;
					trigger.find(".add-to-cart-fix").hover(function(){
						var $this = $(this);
						cur = setTimeout(function(){
							$this.find(".cur-product").show();
						}, 200);
					},function(){
						clearTimeout(cur);
						$(this).find(".cur-product").hide();
					});
				},
				/**
				 * 模拟选项卡跳转
				 */
				jump: function(){
					var _this = this;
					trigger.find("li").click(function(){
						var _index = $(this).index();
						// if(window.navigator.userAgent.indexOf("AppleWebKit") > -1){
						// };
						if($("#placeHolder_fixed").css("display") == "block"){
	                    	$(window).scrollTop($("#placeHolder_fixed").offset().top + 2);
	                    }
						pingjiaBox.show();
						$(this).addClass("cur").siblings().removeClass("cur");
						switch (_index) {
			                case 0:
			                	canshuBox.hide();
			                    detailBox.show();
			                    zxqdBox.hide();
			                    shohouBox.show();
	                            ruleBox.hide();
			                    break;
			                case 1:
			                	canshuBox.show();
			                    detailBox.hide();
			                    zxqdBox.hide();
			                    shohouBox.hide();
	                            ruleBox.hide();
			                    //_this.isVisible(_this.pinglunData);
			                    break;
			                case 2:
			                	canshuBox.hide();
			                    detailBox.hide();
			                    zxqdBox.hide();
			                    ruleBox.show();
			                    // if($("#placeHolder_fixed").css("display") == "block"){
			                    // 	$(window).scrollTop($("#placeHolder_fixed").offset().top + 43);
			                    // }
			                   // _this.isVisible(_this.pinglunData);
			                    break;
			                case 3:
			                	canshuBox.hide();
			                    detailBox.hide();
			                    zxqdBox.show();
			                    shohouBox.show();
			                    break;
			                case 4:
			                	canshuBox.hide();
			                    detailBox.hide();
			                    zxqdBox.hide();
			                    shohouBox.show();
			                    break;
			                case 5:
			                	canshuBox.hide();
			                    detailBox.hide();
			                    zxqdBox.hide();
			                    shohouBox.hide();
			                    pingjiaBox.hide();
			                    _this.isVisible(_this.zixunData);
			                    break;
			                default:
			                    return;
			            }
					});
				},
				loadDataScroll: function(ajaxPara){
					var _this = this;
					$(window).scroll(function(){
						_this.loadData(ajaxPara);
					});
				},
				isVisible: function(ajaxPara){
					this.loadData(ajaxPara);
				},
				loadData: function(_ajaxPara){
					if( $(window).scrollTop() + $(window).height() > _ajaxPara.el.offset().top + 10 && _ajaxPara.el.attr("load") ){
						$.get(_ajaxPara.url, function(data){
							_ajaxPara.el.html(data);
							_ajaxPara.el.removeAttr("load");
							if(typeof _ajaxPara.Fn === "function"){
								_ajaxPara.Fn();
							}
						});
					}
				},
				/**
				 * 异步载入的数据
				 * @type {Object}
				 * @param {String} [url] [异步评论数据地址]
				 */
				pinglunData: {
					el: $("#pingjia_box"),
					url: 'data/pingjia2013.html',
					Fn: function(){
						//满意度
						that.pro_manyidu().init(function(_this){
							for(var i = 0; i < $(".stat_pgs").length; i++){
								_this.showWidth($(".stat_pgs").find("span")[i]);
							}
						});
						// 商品属性
						that.pro_toggle("#comment_pro_attr", "#comment_pro_attr_box", "click", "open");
						// 会员积分列表
						that.pro_toggle("#comment_name_list", {
							el: "#comment_name_list_box",
							clsName: "more-full"
						}, "click", "open");
						/**
						 * 内部选项卡
						 */
						that.pro_tab({
							btn : "#pingjia_tab",
							box : "#pingjia_tab_box",
							boxClass: '.comment-item-box',
							/**
							 * [选项卡数据]
							 * @param {String} [url] [选项卡异步数据地址]
							 * @param {Function} [callback] [回掉方法]
							 */
							ajax_data: [{
								url: 'data/pl.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							}]
						});
						that.pinglunFn($("#pingjia_tab_box"));

					}

				},
				zixunData: {
					el: $("#pro_zixun_tab_box"),
					url: 'data/zixun2013.html',
					Fn: function(){
						that.pro_tab({
							btn : "#zixun_tab",
							box : "#zixun_tab_box",
							boxClass: '.tab-box-c',
							ajax_data: [{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							},{
								url: 'data/data.html'
							}]
						});
					}
				},
				discussData: {
					el: $("#discuss_tab_box"),
					url: 'data/discuss2013.html',
					Fn: function(){
						that.checkwords($("#discuss_tab_box"));
						$("#discuss_tab_box").find(".wenda-list").find("li").find('.replybtn').click(function(){
							$(this).parent().next().toggle();
						});
						that.pro_tab({
							btn : "#wenda_tab",
							box : "#wenda_tab_box",
							boxClass: ".tab-box-c",
							ajax_data: [{
								url: 'data/shaidan.html'
							}]
						});
					}
				}
			}

		},
		init: function(){
			// 图片懒加载
			SNProduct.Util.lazyload(".detail-show");
			SNProduct.Util.lazyload(".detail-side");
			SNProduct.Util.lazyload("#detail_content");
			//var catalogId=$("#catalogId").val();
			//if(catalogId!='22001'){
				// 放大镜			
			//	this.pro_imgzoom(800,800);
			//}
			// 优质商家列表
			this.shop_list_show();
			// 图片展示滚动
			this.pro_view_slide();
			// 观看视频
			this.pro_video_show();
			//赠品选项卡
			this.pro_tab({
				btn: "#promotion_tab",
				btnTag: "p",
				box: "#promotion_tab_box",
				boxClass: ".promotion-content"
			});
			// 数量计算
			this.pro_Num(1,99, true);
			// c店详情展开
			this.pro_toggle("#c_shop_info", "#c_shop_info_box", "click", "arrowAup");
			// 套餐选项卡
			this.pro_tab({
				btn: "#taocan_tab",
				box: "#taocan_tab_box",
				boxClass: ".tab-box-a",
				callback: this.pro_dapei
			});
			// 组合套餐内部选项卡
			this.pro_tab({
				btn: "#zhtaocan_tab",
				box: "#zhtaocan_tab_box",
				boxClass: ".tab-box-b"
			});
			// 选项跟随
			this.pro_fixed("#pro_detail_tab",1190);
			this.commentJump();
			this.pro_tab_jump().init();
			this.pro_manyidu().init(function(that){
				that.showWidth($("#comment_show").find("em"));
			});

		}
	};
	
	/*UI提供方法结束
	     业务页面修改cityBox对象，添加选择区域的链接，添加切换城市的状态检查
	     计时功能不使用countDown方法
	 */
	
	/*业务页面使用方法
	 */
	var ajaxObj = {
		
		checkCityCanSale : function(){

			var actId=$("#actId").val();
			var randomparam = Math.floor(Math.random() * 1000000);
			var switchURL="checkCanSale_"+actId+".htm?param="+randomparam;
			$.get(switchURL, function(data) {
				var esayPrice = data.split("_")[0];
				var isSale = data.split("_")[1];	
				var currentTime=new Date();
				if(currentTime>startTime&&currentTime<endTime){
					if(isSale=='-1' || isSale=='0'){
						$("#actStatus").val(98);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}else if(isSale == '1'){
						$("#actStatus").val(1);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}else if(isSale == '2'){
						$("#actStatus").val(97);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}else if(isSale == '3'){
						$("#actStatus").val(96);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}else if(isSale == '5'){
						$("#actStatus").val(95);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}
					else if(isSale == '4'){
						$("#actStatus").val(4);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}
					else if(isSale == '9'){
						$("#actStatus").val(9);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}
					else if(isSale == '99'){
						$("#actStatus").val(99);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}
					else if(isSale == '92'){
						$("#actStatus").val(92);
						if(esayPrice =="-1"){
							$("#easyPrice").html("");
						}else{
							$("#easyPrice").html(esayPrice);
						}
					}
					rpButtons.btnStatusChange();
		     	}
		    });
		},
		goRush : function(){
        	var woyaoqiang=$("#rpValid");
            var $actId=$("#actId").val();
            var vendorCode = $("#vendorCode").val();
           		var randomparam = Math.floor(Math.random() * 1000000);
           		var cartReturnCode=null;
           		//start   add by gh 4/3
           		woyaoqiang.id = "rps_dorush";
           		woyaoqiang.name = "rps_dorush";
           		sendDatasIndex(woyaoqiang);
           		//end
           		

           		$("div[id='rpValid']").hide();				
				$("div[id='rpClicked']").show();
        	    $.get('/rps-web/rp/doPurchaseresult_'+$actId+'.htm?param='+randomparam, function(data) {
                    if(data.returnCode=="-1"){
                    	ajaxObj.logonurl("dorush");
                    }else{
                		var $popBox;
                		var $fullBg=$("#fullBg");
                    	if(data.returnCode=="0"){
                    		var addcartaddr = $("#rushcartAddrPR").val()+$("#rushcartAddrPS").val();
                			jQuery.getJSON(addcartaddr+"rushCityId="+cookieObj.getCookie('cityId')+"&rushActId="+data.activityId+"&rushMemberId="+data.usersId+"&catEntryId="+data.catentryId+
                							"&vendorCode="+vendorCode+"&callback=?&param="+randomparam, function(cartdata) {
                				if(cartdata.result == null){
    								//加入购物车没有返回
    								cartReturnCode = 5;
                				}else if(cartdata.result == 0){
                					//加入购物车成功
                					$.get('/rps-web/rp/updateRushPurchased_'+data.rushPurProcId+'_'+data.usersId+'_'+$actId+'.htm?param='+randomparam, function(purdata) {
                						if(purdata.returnCode == "0"){
                							$("#quantity").find("em").eq(0).html(data.remainQty);
    		            					cartReturnCode = 0;
    		            					$popBox=$("#popBox"+cartReturnCode);
    		            					$("#periodNo").html($("#releasePeriod").val());
    		            					$popBox.show();
    				                		$popBox.find("a").click(function(e) {
    				                			//start   add by gh 4/3
    				                			e.id = "rps_gopay";
    				                			e.name = "rps_gopay";
    				                			sendDatasIndex(e);
    				                			//end
    				                			
    											window.location.href=$("#cartAddrPR").val()+$("#cartAddrPS").val();
    											e.preventDefault();
    										});
    										$popBox.find("span").click(function(e) {
    					            			$popBox.hide();
    					            			$fullBg.hide();
    					            		});
                						}
                					});
                				}else{
                					var remarks;
                					//加入购物车失败
                					switch (cartdata.failCode) {
    					                case "-1":
    					                    remarks = "系统异常";
    					                    cartReturnCode = 5;
    					                    break;
    					                case "-2":
    					                    remarks = "参数错误";
    					                    cartReturnCode = 5;
    					                    break;
    					                case "0":
    					                    remarks = "您的会员卡已冻结，请拨打4008-198-198或在线客服处理";
    					                    cartReturnCode = 3;
    					                    break;
    					                case "1":
    					                    remarks = "用户的会员卡状态不正确,请咨询客服！";
    					                    cartReturnCode = 4;
    					                    break;
    					                case "2":
    					                    remarks = "对不起,此商品无销售组织，加入购物车失败";
    					                    cartReturnCode = 10;
    					                    break;
    					                case "3":
    					                    remarks = "该商品已售完，暂无法购买！";
    					                    cartReturnCode = 6;
    					                    break;
    					                case "4":
    					                    remarks = "此商品库存不足,请重新添加！  ";
    					                    cartReturnCode = 6;
    					                    break;
    					                case "5":
    					                    remarks = "资格不匹配 ";
    					                    cartReturnCode = 5;
    					                    break;
    					                default:
    					                    remarks = "";
    					                    cartReturnCode = 5;
    					                    break;
                					}
                				}
                				$popBox=$("#popBox"+cartReturnCode);
    	                	$popBox.show();
    	                	$popBox.find("span").click(function(e) {
                			$popBox.hide();
                			$fullBg.hide();
                		});
                			});
                    	}else{
                    		$popBox=$("#popBox"+data.returnCode);
                    		$popBox.show();
                    		$popBox.find("span").click(function(e) {
                			$popBox.hide();
                			$fullBg.hide();
                		});
                    	}
                		$fullBg.show();
                		
                    } 
        	    });
        	   $("div[id='rpClicked']").hide();
				$("div[id='rpValid']").show();        	   
        },
        
        logonurl :function (typeTemp){
        	var type = "default";
        	var province = "default";//为电视购物添加的参数
        	var channelType = 1;
            var $actId=$("#actId");
            if(typeTemp){
            	type = typeTemp;
            }
            window.location.href="logonUrl_"+$actId.val()+'_'+type+'_'+channelType+'_'+province+'.htm';
         },
         
         getCShopInfo : function(){
        	
    		 var cShopaddr = $("#cShopInfo").val();
    		 var catalogid = $("#catalogId").val();
    		 var catentryId = $("#catentry").val();
    		 var partNumber = $("#partNumber").val();
    		 if(partNumber.substring(0,9) != "000000000"){
    			 partNumber = "000000000" + partNumber;
    		 }
    		 
    		 var randomparam = Math.floor(Math.random() * 1000000);
    		 
    		 var cStoreInfoUrl = cShopaddr+catalogid+"_"+catentryId+"_"+partNumber+"_"+cookieObj.getCookie('cityId')+"_callback_.html?param="+randomparam;
    		 $.ajax({
				url : encodeURI(cStoreInfoUrl),
				type : "GET",
				dataType : 'jsonp',
				jsonp : 'callback',
				jsonpCallback : "callback",
				success : function(data) {
				},
				error : function(xhr) {
					
				}
		    });
	    		 
         }
	};
	
	function callback(data){
		
		 var vendorCode =  $("#vendorCode").val();
    	 
    	 
    	 if(vendorCode == null || vendorCode == undefined || vendorCode == "" ){
    		 vendorCode = "SN_001";
    	 }
		if(data != null && data != undefined){
			var shopList = data.shopList;
			 for(var i=0;i<shopList.length;i++){
    			 if(shopList[i].shopCode == vendorCode){
		        	 $("#c_shop_name").find("p").eq(0).text(shopList[i].shopName);
		        	 $("#c_shop_name").find("em").eq(0).get(0).style.width = shopList[i].shopGrade*13.8+"px";
		        	 $("#c_shop_info_box").find("span").eq(1).text(shopList[i].productSatisfy);
		        	 $("#c_shop_info_box").find("span").eq(3).text(shopList[i].serviceAttitude);
		        	 $("#c_shop_info_box").find("span").eq(5).text(shopList[i].deliverSpeed);
		        	
		        	 if(vendorCode == "SN_001"){
		        		 $(".suning-contact").show();
		        		 $(".c-shop-contact").hide();
		        	 }else{
		        		 $(".suning-contact").hide();
		        		 $(".c-shop-contact").show();
		        	 }
		        	 break;
    			 }
			 }
		 }
	}
	
	var cookieObj = {
		init : function(){
			var cityId = cookieObj.getCookie('cityId');
			var districtId = cookieObj.getCookie('districtId');
			if(cityId != null && districtId == null){
				var re = /([A-Za-z]+)([0-9]+)(.[^\|]+)()/g;
				$.get("getDistrict_"+cityId+".html",function(data){
					$.get("getcdn_"+cityId+"_"+(re.exec(data)[2])+".html", function(data) {
						cookieObj.setCityBox(data,cityId);
					});
				});
			}else{
				
				$.get("getcdn_"+cityId+"_"+districtId+".html", function(data) {
					cookieObj.setCityBox(data,cityId);
				});
			}
		},
		
		setCityBox : function (data,cityId){
			//data 格式  100||南京市||玄武区||10000
			var cityId = cookieObj.getCookie('cityId');
			var cityBtn = $("#citybox_btn");
			var city = $("#citybox");
			if(data=="" || data ==null){
				data = "100||南京市||玄武区||10000";
			}
			if(cityId == 9017 || cityId == 9325 || cityId == 9264 || cityId == 9281){
				cityBtn.find(".ptext").text($("a[name="+data.split("||")[0]+"]").text());
		        cityBtn.find(".ctext").text(data.split("||")[1]);
		        cityBtn.find(".atext").text(data.split("||")[2]);
		        city.find(".chooseArea").find("p").eq(0).text(data.split("||")[1])
		        .attr("data-url","getCityByProvCode_"+data.split("||")[0]+".html");
		        city.find(".chooseArea").find("p").eq(1).text(data.split("||")[1])
		        .addClass("disable");
		        city.find(".chooseArea").find("p").eq(2).text(data.split("||")[2])
		        .attr("data-url","getDistrict_"+cityId+".html");
			}else{
			//填充cityBox
				cityBtn.find(".ptext").text($("a[name="+data.split("||")[0]+"]").text());
		        cityBtn.find(".ctext").text(data.split("||")[1]);
		        cityBtn.find(".atext").text(data.split("||")[2]);
		        city.find(".chooseArea").find("p").eq(0).text($("a[name="+data.split("||")[0]+"]").text());
		        city.find(".chooseArea").find("p").eq(1).text(data.split("||")[1])
		        .attr("data-url","getCityByProvCode_"+data.split("||")[0]+".html");
		        city.find(".chooseArea").find("p").eq(2).text(data.split("||")[2])
		        .attr("data-url","getDistrict_"+cityId+".html");
			}
		},
		//写cookies 
	    setCookie : function(name,value){ 
			 document.cookie = name + "="+ escape (value) +";domain="+$("#cDomain").val()+";path=/"; 
		}, 
		
		//读取cookies 
	    getCookie : function(name) { 
			var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");   
			if(arr=document.cookie.match(reg))
			  return decodeURIComponent(arr[2]); 
			else 
			  return null; 
		},

		//获取cookie
	    getCookieIndex : function(name) {
			var arrStr = document.cookie.split("; ");
			for ( var i = 0; i < arrStr.length; i++) {
				var temp = arrStr[i].split("=");
				if (temp[0] == name)
					return unescape(temp[1]);
			}
		}
	};
	/*
	 timer对象
	 */
	var startTime= new Date($("#startTime").val().replace(/-/g,"/"));
	var endTime=new Date($("#endTime").val().replace(/-/g,"/"));
	var curTime=new Date($("#curTime").val().replace(/-/g,"/"));
	var timer = {
		/*
		 @countDown 页面计时方法
		 @param  {[Number]}   time        [时间，单位为毫秒]
	     @param  {[Object]}   func     [时间innerHTML]
	     @param  {[Function]} callback [回调函数，可选]
		 */   	
			pro_time : function(t,func,obj,callback,showBox){
				var self=this;
				this.time = t;
				this.start=function(){
					var tt=self.time/1000;
					var o={};
					o.time = self.time;
					o.seconds = parseInt(tt%60);
					o.minutes = parseInt((tt/60)%60);
					o.hours = parseInt((tt/60/60)%24);
					o.days = parseInt(tt/60/60/24);
					
					if(o.seconds < 10){o.seconds="0"+o.seconds;}
					if(o.minutes < 10){o.minutes="0"+o.minutes;}
					func(o,obj, callback,showBox);
					if(self.time >= 0){
						setTimeout(self.onRetime, 1000);
					}
				};
				this.onRetime = function(){
					self.time -= 1000;
					self.start();
				};	
			},
			rp_end_callback : function(showBox){
				$("#actStatus").val(4);
				showBox();
			},
			rp_begin_callback : function(showBox){	
				$("#startTimeDIV").hide();
				$("#endTimeDIV").show();
				$("#actStatus").val(1);
				showBox();
			},
			button_display : function(){
				rpButtons.btnStatusChange();
			},
			pro_reTime : function(time, obj, callback,showBox){
				if (time.time >= 0) {
					//$(obj).html('剩<em>' + time.days + '</em>天<em>' + time.hours + '</em>小时<em>' + time.minutes + '</em>分<em>' + time.seconds + '</em>秒');
					time.hours=time.hours+time.days*24;
					$(obj).find(".hour").html(time.hours);
					$(obj).find(".minute").html(time.minutes);
					$(obj).find(".second").html(time.seconds);

				}else{
					callback(showBox);
				}
			}
	};
	
	/*
	 按钮常量 
	 */
	var buttons = ["rpValid","rpOver","rpOut","rpWait","rpLimited","rpCansale","rpNotinsale","rpPrepare","rpClicked","rpNotClick","rpValidWithOutLogon"];

	var statusmappingbtn = {
			  1     :   "rpValid"    ,
			  96    :   "rpNotinsale",
			  9     :   "rpOut"      ,
			  4     :   "rpOver"  ,   
			  99    :   "rpWait"  ,   
			  98    :   "rpLimited" , 
			  97    :   "rpCansale" ,
			  92    :   "rpPrepare",
			  94    :   "rpClicked",
	          95    :   "rpNotClick",
	          93    :   "rpValidWithOutLogon"
		};


	/*
	按钮
	*/
	var rpButtons = {
			btnDisplayChange : function(obj){
				$(".buy-btn.fix").hide();				
				$("div[id='"+obj+"']").show();
			},
			btnStatusChange : function (){				
				//先隐藏所有按钮	
				$(".buy-btn.fix").hide();
				
				var logonStatus=cookieObj.getCookie("logonStatus");
				
				//获取活动状态，根据活动状态判断按钮状态
				var actStatus=$("#actStatus").val();
				
				//展示需要展示的按钮
				$("div[id='"+statusmappingbtn[actStatus]+"']").show();	
				
				// 如果活动已结束，隐藏离结束时间和剩余数量
				if(actStatus == 4){
	        		$("#endTimeDIV").hide();
	        		$("#qtyDiv").hide();
				}
				if(logonStatus==null && (actStatus=='1' || actStatus == '99' || actStatus == '92')){
					$("#loginContent").show();
				}
			}
	};

	
	//数据采集方法开始
	var rp_prd_reg = /^\w*?.suning.com$/; // prd host
	var rp_pre_reg = /^\w*?pre.cnsuning.com$/; // pre host
	var rp_sit_reg = /^\w*?sit.cnsuning.com$/;
	var _hostName = document.location.hostname;
	var server = getServer();
	_tag = "|";

	//加载da_opt.js
	function lazyLoadFunction() {
		var da_opt_src = getJsFilePath("da_opt.js");
		jQuery.getScript(da_opt_src);
	}

	//获取js文件路径
	function getJsFilePath(js_file) {
		var sa_src = "";
		if (rp_prd_reg.test(_hostName)) {//prd
			sa_src = "http://script.suning.cn";
		} else if (rp_pre_reg.test(_hostName)) {//pre
			sa_src = "http://prescript.suning.cn";
		} else if (rp_sit_reg.test(_hostName)) {// sit系统
			sa_src = "http://sit1script.suning.cn";
		} else {
			sa_src = "http://prescript.suning.cn";
		}
		sa_src = sa_src + "/javascript/sn_da/" + js_file;
		return sa_src;
	}

	function sendDatasIndex(data) {
		var name = data.name;
		var id = data.id;
		var Datas = id + "|" + name;
		var clickUrl = "http://" + server + "/ajaxClick.gif";
		var oId = getOnlyIdIndex();
		var pvId = getCookieIndex("_snmp"); // 访问数据唯一标识
		var cDatas = oId + _tag + pvId + _tag + Datas;
		var url = clickUrl + "?" + "_snmk=" + cDatas;
		httpGifSendIndex(url);
	}

	//获取唯一标识
	function getOnlyIdIndex() {
		var now = new Date();
		var m = Math.round(100000 * Math.random());
		var onlyId = now.getTime().toString().concat(m);
		return onlyId;
	}

	//获取cookie
	function getCookieIndex(name) {
		var arrStr = document.cookie.split("; ");
		for ( var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == name)
				return unescape(temp[1]);
		}
	}

	//创建一个img标签,发送数据
	function httpGifSendIndex(strURL) {
		var img_src = strURL;
		var imgTag = document.createElement('img');
		imgTag.src = img_src;
	}

	//判断环境
	function getServer() {
		if (rp_prd_reg.test(_hostName)) {// 生产环境
			return "click.suning.cn/sa";
		} else {// pre、sit环境
			return "clicksit.suning.cn/sa";
		}
	}
	//数据采集结束
	
	//zhangweiwei  start
	function showFootImg(){
		//判断窗体高度与竖向滚动位移大小相加 是否 超过内容页高度,然后显示尾部
		if(($(window).height()+$(window).scrollTop())>=$("body").height()){
			var footer=$("#footer");
			var footerHtml=$("#footerHtml").val();
			footer.html(footerHtml);
		}
	}
	//zhangweiwie end
	
	//分享到...
	var _url = encodeURI(window.location);
	var _t = encodeURI(document.title);
	var _desc=encodeURI(" : 发现一个非常不错的商品"+$("#catEntry").html()+"，抢购价只要¥"+$("#rpPrice").html()+"元，还等什么，大家快来抢吧！");

	function shareWb() {
		$(".kaixin").attr(
				"href",
				"http://www.kaixin001.com/repaste/bshare.php?rtitle=" + _t+_desc
						+ "&rurl=" + _url + "&from=maxthon");
		$(".sina").attr(
				"href",
				"http://v.t.sina.com.cn/share/share.php?url=" + _url
						+ "&appkey=400813291&title=" + _t+_desc + "&pic=");
		var k = "推荐苏宁电器网上商城(suning.cn) " + document.title
				+ "价格便宜，评价也不错，快去看看详细介绍吧\n" + window.location
				+ "\n苏宁承诺：所售商品均为正品行货，带发票，凭质保证书及发票可全国联保";
		$(".douban").attr(
				"href",
				"http://www.douban.com/recommend/?url=" + _url + "&title=" + _t+_desc
						+ "&comment=" + encodeURI(k));
		$(".renren").attr(
				"href",
				"http://share.renren.com/share/buttonshare.do?link=" + _url
						+ "&title=" + _t+_desc);
		var m = encodeURI("65e3731f449e42a484c25c668160b355");
//		var p = encodeURI(sn.pic);
		var r = encodeURI("http://www.suning.com");
		var n = "http://v.t.qq.com/share/share.php?title=" + _t+_desc + "&url=" + _url
				+ "&appkey=" + m + "&site=" + r + "&pic=";
		$(".tengxun").attr("href", n);
		$(".souhu").attr(
				"href",
				"http://t.sohu.com/third/post.jsp?&url=" + _url + "&title=" + _t+_desc
						+ "&content=utf-8&pic=");
		var l = {
			url : location.href,
			desc : "",
			summary : "",
			title : document.title,
			site : "苏宁易购"
//			pics : sn.pic
		};
		var o = [];
		for ( var q in l) {
			o.push(q + "=" + encodeURIComponent(l[q] || ""));
		}
		$(".qzone").attr(
				"href",
				"http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?"
						+ o.join("&"));
	}