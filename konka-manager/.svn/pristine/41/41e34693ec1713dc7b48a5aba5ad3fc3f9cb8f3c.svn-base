(function($) {// Compliant with jquery.noConflict()
$.fn.jCarouselLite = function(o) {
    o = $.extend({
        btnPrev: null,
        btnNext: null,
        btnGo: null,
		btnPlay: null,
		btnStop: null,
        mouseWheel: false,
        auto: null,
        hoverPause: true,
        speed: 200,
        easing: null,
        vertical: false,
        circular: true,
        visible: 1,
        start: 0,
        scroll: 1,
        beforeStart: null,
        afterEnd: null
    }, o || {});

    return this.each(function() {
		// Returns the element collection. Chainable.

        var running = false, animCss=o.vertical?"top":"left", sizeCss=o.vertical?"height":"width";
        var div = $(this), ul = $("ul", div), tLi = $("li", ul), tl = tLi.size(), v = o.visible;

        if(o.circular) {
            ul.prepend(tLi.slice(tl-v+1).clone())
              .append(tLi.slice(0,o.scroll).clone());
            o.start += v-1;
        }

        var li = $("li", ul), itemLength = li.size(), curr = o.start;
        div.css("visibility", "visible");

        li.css({overflow: "hidden", float: o.vertical ? "none" : "left"});
        ul.css({margin: "0", padding: "0", position: "relative", "list-style-type": "none", "z-index": "1"});
        div.css({overflow: "hidden", position: "relative", "z-index": "2", left: "0px"});

        var liSize = o.vertical ? height(li) : width(li);   // Full li size(incl margin)-Used for animation
        var ulSize = liSize * itemLength;                   // size of full ul(total length, not just for the visible items)
        var divSize = liSize * v;                           // size of entire div(total length for just the visible items)

        li.css({width: li.width(), height: li.height()});
        ul.css(sizeCss, ulSize+"px").css(animCss, -(curr*liSize));

        div.css(sizeCss, divSize+"px");                     // Width of the DIV. length of visible images

        if(o.btnPrev) {
            $(o.btnPrev).click(function() {
                return go(curr-o.scroll);
            });
            if(o.hoverPause && (o.auto) > 0) {
               $(o.btnPrev).hover(function(){stopAuto();}, function(){startAuto();});
            }
        }

        if(o.btnStop) {
            $(o.btnStop).click(function() {
				stopAuto();
            });
        }

        if(o.btnNext) {
            $(o.btnNext).click(function() {
                return go(curr+o.scroll);
            });
            if(o.hoverPause && (o.auto) > 0) {
                $(o.btnNext).hover(function(){stopAuto();}, function(){startAuto();});
            }
        }

        if(o.btnPlay) {
            $(o.btnPlay).click(function() {
				startAuto();
            });
        }

        if(o.btnGo)
            $.each(o.btnGo, function(i, val) {
                $(val).click(function() {
                    //return go(o.circular ? o.visible+i : i);
					return go(o.circular ? i: i);
                });
            });

        if(o.mouseWheel && div.mousewheel)
            div.mousewheel(function(e, d) {
                return d>0 ? go(curr-o.scroll) : go(curr+o.scroll);
            });

        var autoInterval;

        function startAuto() {
          stopAuto();
          autoInterval = setInterval(function() {
                  go(curr+o.scroll);
              }, o.auto+o.speed);
        };

        function stopAuto() {
            clearInterval(autoInterval);
        };

        if(o.auto) {
            if(o.hoverPause && (o.auto) > 0) {
                div.hover(function(){stopAuto();}, function(){startAuto();});
            }
            startAuto();
        };

        function vis() {
            return li.slice(curr).slice(0,v);
        };

        function go(to) {
            if(!running) {

                if(o.beforeStart)
                    o.beforeStart.call(this, vis());

                if(o.circular) {            // If circular we are in first or last, then goto the other end
                    if(to<0) {           // If before range, then go around
                        ul.css(animCss, -( (curr + tl) * liSize)+"px");
                        curr = to + tl;
                    } else if(to>itemLength-v) { // If beyond range, then come around
                        ul.css(animCss, -( (curr - tl) * liSize ) + "px" );
                        curr = to - tl;
                    } else curr = to;
                } else {                    // If non-circular and to points to first or last, we just return.
                    if(to<0 || to>itemLength-v) return;
                    else curr = to;
                }
				// If neither overrides it, the curr will still be "to" and we can proceed.

                running = true;

                ul.animate(
                    animCss == "left" ? { left: -(curr*liSize) } : { top: -(curr*liSize) } , o.speed, o.easing,
                    function() {
                        if(o.afterEnd)
                            o.afterEnd.call(this, vis());
                        running = false;
                    }
                );
                // Disable buttons when the carousel reaches the last/first, and enable when not
                if(!o.circular) {
                    $(o.btnPrev + "," + o.btnNext).removeClass("disabled");
                    $( (curr-o.scroll<0 && o.btnPrev)
                        ||
                       (curr+o.scroll > itemLength-v && o.btnNext)
                        ||
                       []
                     ).addClass("disabled");
                }
            }
            return false;
        };
    });
};

function css(el, prop) {
    return parseInt($.css(el[0], prop)) || 0;
};
function width(el) {
    return  el[0].offsetWidth + css(el, 'marginLeft') + css(el, 'marginRight');
};
function height(el) {

    return el[0].offsetHeight + css(el, 'marginTop') + css(el, 'marginBottom');
};

})(jQuery);
//===========================================================================================
$(document).ready(function() {
	//========================================
	$(".RollingDot").each(function(){
		jObj = $(this);
		tObj_id = '#' + $(this).attr("target");
		tObj = $(tObj_id);

		$(this).jCarouselLite({
			btnNext: $(tObj).find(".next"),
			btnPrev: $(tObj).find(".prev"),
			btnPlay: $(tObj).find(".play"),
			btnStop: $(tObj).find(".stop"),
			btnGo: [
				$(tObj).find(".0"),
				$(tObj).find(".1"),
				$(tObj).find(".2"),
				$(tObj).find(".3"),
				$(tObj).find(".4"),
				$(tObj).find(".5"),
				$(tObj).find(".6"),
				$(tObj).find(".7"),
				$(tObj).find(".8"),
				$(tObj).find(".9")
			],
			mouseWheel : true,
			vertical: ($(this).attr('vertical') == ("true"))?true:false,
			visible: parseInt($(this).attr('visible')),
			start: parseInt($(this).attr('start')),
			auto: parseInt($(this).attr('interval')),
			speed: parseInt($(this).attr('speed')),
			afterEnd: function(a) {

				var index = a.index();
				var target = '#' + $(a).parent().parent().parent().find(".RollingDot").attr("target");
				var li_size = $(this).find("li").size() - 1;
				//console.log(target);
				//console.log(index);
				index = (li_size <= index || li_size < 0)?0:index;

				$(target + " img").each(function(){ 

					if($(this).hasClass(index)){
						$(this).attr("src", $(this).attr("src").replace("_off", "_on"));
					}else{
						$(this).attr("src", $(this).attr("src").replace("_on", "_off"));
					}
				});
			}
		});
	});

	

	//=========== [S] ????? menu1_ ===========
	var menu1_XMin = function(){ return ((document.body.clientWidth-1000) + Math.abs(document.body.clientWidth-1000))/4 + 1020; }; //?? : ??
	var menu1_YMin = 140; //?? : ??
	var menu1_Name = "#floating";
	var moveYMin = function(){ return (parseInt(document.body.clientHeight) + parseInt(document.body.scrollTop) - 144); }; //?? : ?

	if ($(menu1_Name).length > 0){

		$(menu1_Name).show();

		$(menu1_Name).css("left", menu1_XMin); 
		$(menu1_Name).css("top", menu1_YMin);
		menu1_Yloc = parseInt($(menu1_Name).css("top").substring(0,$(menu1_Name).css("top").indexOf("px")));
		
		$(window).scroll(function () {
			offset = ($(document).scrollTop() > menu1_YMin)?$(document).scrollTop():menu1_YMin;
			//$(menu1_Name).animate({top:offset + 'px'},{duration:500,queue:false});
			$(menu1_Name).animate({top:offset + 'px'},{duration:800,queue:false});
		});

		$(window).resize(function () { $(menu1_Name).css("left", menu1_XMin); });
	}
	//===========[E] ????? menu1_ ===========
	//=========== [S] ????? menu2_ ===========
	var menu2_XMin = function(){ return ((document.body.clientWidth-1000) + Math.abs(document.body.clientWidth-1000))/4 - 320; }; //?? : ??
	var menu2_YMin = 40; //?? : ??
	var menu2_Name = "#floating2";
	var moveYMin = function(){ return (parseInt(document.body.clientHeight) + parseInt(document.body.scrollTop) - 144); }; //?? : ?

	if ($(menu2_Name).length > 0){

		$(menu2_Name).show();

		$(menu2_Name).css("left", menu2_XMin); 
		$(menu2_Name).css("top", menu2_YMin);
		menu2_Yloc = parseInt($(menu2_Name).css("top").substring(0,$(menu2_Name).css("top").indexOf("px")));
		
		$(window).scroll(function () {
			offset = ($(document).scrollTop() > menu2_YMin)?$(document).scrollTop():menu2_YMin;
			//$(menu2_Name).animate({top:offset + 'px'},{duration:500,queue:false});
			$(menu2_Name).animate({top:offset + 'px'},{duration:800,queue:false});
		});

		$(window).resize(function () { $(menu2_Name).css("left", menu2_XMin); });
	}
	//===========[E] ????? menu2_ ===========
});