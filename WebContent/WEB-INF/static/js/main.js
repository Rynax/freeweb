
$(function() {
    
    //头部鼠标经过显示菜单
    $('.username_down').hover(function() {
        $(this).addClass('active');
        $(this).find('.Modify-pd').show();
    
    }
    , function() {
        $(this).removeClass('active');
        $(this).find('.Modify-pd').hide();
    
    }
    )
    $('.help_down').hover(function() {
        $(this).addClass('active');
        $(this).find('.top_drop').show();
    
    }
    , function() {
        $(this).removeClass('active');
        $(this).find('.top_drop').hide();
    
    }
    )
    //左侧菜单
    
    $('.leftnav-cA > ul > li > a').click(function() {
        var childCb = $(this).siblings('.leftnav-cB');
        if (childCb.length > 0) {
            if (childCb.is(':visible')) {
                childCb.slideUp('fast');
                $(this).removeClass('active');
                $(this).children('.fa-angle-right').removeClass('fa-angle-down').css('color', '#585858');
            } 
            else {
                childCb.slideDown('fast');
                $(this).parent().siblings('li').children('.leftnav-cB').slideUp('fast');
                $(this).addClass('active');
                $(this).parent().siblings('li').children('a:first-child').removeClass('active');
				$(this).parent().siblings('li').find('.fa-angle-right').removeClass('fa-angle-down').css('color', '#585858');
                $(this).children('.fa-angle-right').addClass('fa-angle-down').css('color', '#428bcb');
            }
            
            return false;
        }
    
    }
    )
    
    
    $('.leftnav-cB > ul > li > a').click(function() {
        var childCc = $(this).siblings('.leftnav-cC');
        if (childCc.length > 0) {
            if (childCc.is(':visible')) {
                childCc.slideUp('fast');
                $(this).removeClass('active');
                $(this).children('.fa-angle-right').removeClass('fa-angle-down').css('color', '#585858');
            } 
            else {
                childCc.slideDown('fast');
                $(this).parent().siblings('li').children('.leftnav-cC').slideUp('fast');
                $(this).addClass('active');
                $(this).parent().siblings('li').children('a:first-child').removeClass('active');
				$(this).parent().siblings('li').find('.fa-angle-right').removeClass('fa-angle-down').css('color', '#585858');
                $(this).children('.fa-angle-right').addClass('fa-angle-down').css('color', '#428bcb');
            }
            
            return false;
        } 
        else {
            $(this).parents('.leftnav-cA').find('.leftnav-cB').find('a').removeClass('a_active');
            $(this).addClass('a_active');
            var this_text = $(this).text();
			var this_href = $(this).attr('href');
            var this_target = $(this).attr('target');
            addTab(this_text,this_href, this_target);
        
        
        }
    }
    )
    //点击孙菜单连接高亮
    $('.leftnav-cC > ul > li > a').click(function() {
        $(this).parents('.leftnav-cA').find('.leftnav-cC').find('a').removeClass('a_active');
        $(this).addClass('a_active');
        var this_text = $(this).text();
		var this_href = $(this).attr('href');
        var this_target = $(this).attr('target');
        addTab(this_text, this_href,this_target);
    
    }
    )
    
    
    
    //添加tab菜单function
    function addTab(obj, ohref , target) {
        var ohUl = $('.erp-con-nav-oh ul');
        var ohLi = $('.erp-con-nav-oh ul li');
        var ohLiA = $('.erp-con-nav-oh ul li a');
        var ohLiI = $('.erp-con-nav-oh ul li .fa-times');
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        var outer = $('.erp-con-nav-oh');
        var inner = $('.erp-con-nav-ul');
        var outerW = outer.width();
        var erpRight = $('.erp_right');
        var liW = 0;
        var liW_last = 0;
        liW = 0;
        var ulArr = [];
        
        ohLi.each(function() {
            ulArr.push($(this).text());
        }
        )
        if (ulArr.indexOf(obj) == -1) {
            ohLi.removeClass('active');
            ohLi.find('i.fa-times').addClass('opacity-1');
            ohUl.append('<li class="active"><a href="javascript:void(0)">' + obj + '</a><i class="fa fa-times"></i></li>');
            erpRight.children('iframe').hide();
            
            erpRight.append('<iframe src="' + ohref + '" width="100%" height="100%" nocheckframe="true" name="' + target + '"  frameborder="no" scrolling="auto" hidefocus=""></iframe>')
            
            for (var i = 0; i < $('.erp-con-nav-oh ul li').length; i++) 
            {
                liW += $('.erp-con-nav-oh ul li').eq(i).outerWidth(true)+1;
            }
             for (var i = 1; i < $('.erp-con-nav-oh ul li').length; i++) 
            {
                liW_last += $('.erp-con-nav-oh ul li').eq(i - 1).outerWidth(true);
            }
            
            inner.css('width', liW + 'px');
            var thisW = $('.erp-con-nav-oh ul li:last').outerWidth(true);
            //当前插入的元素的宽度
            if (liW > outerW) {
                if (navLeft.is(':visible')) {
                    inner.stop(false, true).animate({
                        'left': '-=' + thisW + 'px'
                    }, 500);
                } 
                else {
                    navLeft.show();
                    navRight.show().addClass('active');
                    inner.stop(false, true).animate({
                        'left': '-=' + (thisW - parseInt(outerW - liW_last)) + 'px'
                    }, 500);
                }
            
            }
        } 
        else {
            $('.erp-con-nav-oh ul li').find('i.fa-times').addClass('opacity-1');
            ohLi.removeClass('active');
            ohLi.eq(ulArr.indexOf(obj)).addClass('active');
            ohLi.eq(ulArr.indexOf(obj)).find('i.fa-times').show();
            ohLi.eq(ulArr.indexOf(obj)).find('i.fa-times').removeClass('opacity-1');
            $('.erp_right').children('iframe').hide();
            $('.erp_right').children('iframe').eq(ulArr.indexOf(obj)).show();
			//如果超出可显示区域
			var index_left=ohLi.eq(ulArr.indexOf(obj)).offset().left;
			var index_width=ohLi.eq(ulArr.indexOf(obj)).width();
			if(index_left-200<0){
				inner.stop(false, true).animate({
                       'left': '-=' + (parseInt(index_left-200)-5) + 'px'
                    }, 500);
				}
			if((index_left+index_width)>(outerW+200)){
				inner.stop(false, true).animate({
                       'left': '-=' + (parseInt(index_left+index_width-(outerW+200)+20)) + 'px'
                    }, 500);
				}
			
        }
    
    }
    
    //主页面右侧关闭tab
    
    $('.erp-con-nav-oh ul li .fa-times').live("click", function() {
        var a_index = $(this).parent().index();
        
        var outer = $('.erp-con-nav-oh')
        var inner = $('.erp-con-nav-ul');
        var outer_w = outer.width();
        var inner_w = inner.width();
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        var this_w = $(this).parent().outerWidth(true);
        
        if ($(this).parents('li').hasClass('active')) {
            $(this).parents('li').prev().addClass('active');
            $(this).parents('li').prev().find('i.fa-times').removeClass('opacity-1')
            $('.erp_right').children('iframe').eq(a_index).prev().show();
            $('.erp_right').children('iframe').eq(a_index).remove();
            $(this).parents('li').remove();
        } 
        else {
            $(this).parents('li').remove();
            
            $('.erp_right').children('iframe').eq(a_index).remove();
        }
        
        
        var oh_w = 0;
        for (var i = 0; i < $('.erp-con-nav-oh ul li').length; i++) {
            oh_w += $('.erp-con-nav-oh ul li').eq(i).outerWidth(true);
        }
        oh_w += 1;
        //有时候会少1像素而导致长度不够
        inner.css('width', oh_w + 'px');
        if (parseInt(inner.css('width')) <= outer_w) {
            navLeft.hide();
            navRight.hide();
            inner.animate({
                'left': '0'
            }, 400);
        }
        
        if ((parseInt(inner.css('width')) - outer_w) >= $(this).parent().outerWidth(true)) {
            inner.animate({
                'left': '+=' + this_w + 'px'
            }, 400);
        }
    }
    );
    
    //主页面右侧tab切换
    $('.erp-con-nav-oh ul li a').live("click", function() {
        $('.erp-con-nav-oh ul li').find('i.fa-times').addClass('opacity-1');
        $(this).next('i.fa-times').show();
        $(this).parent().addClass('active').siblings('li').removeClass('active');
        $(this).next().removeClass('opacity-1');
        
        var a_index = $(this).parent().index();
        $('.erp_right').children('iframe').hide();
        $('.erp_right').children('iframe').eq(a_index).show();
        //有左右按钮时的情况左侧向左滚动
        var ulLeft = $('.erp-con-nav-ul').css('left');
        var lilW = 0;
        var lirW = 0;
        var erpLi = $('.erp-con-nav-ul li');
        var tindex = $(this).parent().index();
        var outW = $('.erp-con-nav-oh').width();
        var innW = $('.erp-con-nav-ul').width()
        for (var i = 0; i < tindex; i++) {
            lilW += erpLi.eq(i).outerWidth(true);
        }
        for (var i = tindex + 1; i < erpLi.length; i++) {
            lirW += erpLi.eq(i).outerWidth(true);
        }
        var leftw = lilW - Math.abs(parseInt(ulLeft));
        var rightw = innW - Math.abs(parseInt(ulLeft)) - parseInt(outW) - lirW;
        
        //当左边菜单有一半没有显示时
        if (leftw < 0) {
            $('.erp-con-nav-ul').stop(false, true).animate({
                'left': '-=' + leftw + 'px'
            }, 500);
        }
        //当右边菜单有一半没有显示时
        if (rightw > 0) {
            $('.erp-con-nav-ul').stop(false, true).animate({
                'left': '-=' + rightw + 'px'
            }, 500);
        }
    
    }
    );
    //当浏览器窗口缩放时左右按钮消失或出现
    $(window).resize(function() {
        var outW = $('.erp-con-nav-oh').width();
        var innW = $('.erp-con-nav-ul').width();
        var innL = $('.erp-con-nav-ul').css('left');
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        var a_index;
        var a_indexW = 0;
        var outwArr = [];
        
        $('.erp-con-nav-ul li').each(function() {
            if ($(this).hasClass('active')) {
                a_index = $(this).index();
            }
        }
        )
        for (var i = 0; i <= a_index; i++) {
            a_indexW += $('.erp-con-nav-ul li').eq(i).outerWidth(true);
        }
        
        if (innW <= outW) {
            navLeft.hide();
            navRight.hide();
            $('.erp-con-nav-ul').css('left', '0')
        } 
        else {
            outwArr.push(outW)
            //console.log(outwArr[0])
            navLeft.show().removeClass('active');
            navRight.show().removeClass('active');
            /*if(a_indexW>(parseInt(innL)+outW)){
			$('.erp-con-nav-ul').css('left',-(a_indexW-(parseInt(innL)+outW))+'px');
			}*/
        }
    }
    )
    
    //点击菜单左右按钮function
    function navBut() {
        var ooh = $('.erp-con-nav-oh');
        var oul = $('.erp-con-nav-ul');
        var ouli = $('.erp-con-nav-ul li');
        var oohW = ooh.width();
        var speed = 4;
        var otime = null ;
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        
        //右按钮
        navRight.mouseover(function() {
            oohW = ooh.width();
            var innW = $('.erp-con-nav-ul').outerWidth(true);
            
            otime = setInterval(function() {
                if (oul.position().left <= -(innW - oohW)) {
                    navRight.addClass('active');
                    clearInterval(otime);
                } 
                else {
                    
                    navLeft.removeClass('active');
                    oul.css({
                        left: '-=' + speed + 'px'
                    });
                }
            }
            , 40)
        }
        )
        navRight.mouseout(function() {
            
            clearInterval(otime);
        
        }
        )
        //左按钮
        navLeft.mouseover(function() {
            otime = setInterval(function() {
                if (oul.position().left >= 0) {
                    navLeft.addClass('active');
                    clearInterval(otime);
                } 
                else {
                    navRight.removeClass('active');
                    oul.css({
                        left: '+=' + speed + 'px'
                    });
                }
            }
            , 40)
        }
        )
        navLeft.mouseout(function() {
            
            clearInterval(otime);
        
        }
        )
    }
    navBut()
    
    //鼠标经过tab关闭按钮高亮
    $('.erp-con-nav-ul li').live('mouseover', function() {
        var thisI = $(this).find('i.fa-times')
        if (thisI.hasClass('opacity-1')) {
            $(this).find('i.fa-times').addClass('opacity-10');
        }
    }
    )
    //鼠标离开tab关闭按钮移除高亮
    $('.erp-con-nav-ul li').live('mouseout', function() {
        var thisI = $(this).find('i.fa-times')
        if (thisI.hasClass('opacity-10')) {
            $(this).find('i.fa-times').removeClass('opacity-10');
        }
    }
    )
    
    //左侧框架和右侧框架高度
    var wenH = $(window).height();
    var headH = $('.erp_header').outerHeight(true);
    var footH = $('.erp_footer').outerHeight(true);
    var navH = $('.erp_nav').outerHeight(true);
    var leftCdH = $('.left_cd').outerHeight(true);
    var leftCdW = $('.left_cd').outerWidth(true);
    //左侧菜单的宽度
    
    $('.erp_right').height(wenH - footH);
    $('.erp_left').height(wenH - headH - footH - leftCdH);
    $(window).resize(function() {
        var wenH = $(window).height();
        $('.erp_right').height(wenH - footH);
        $('.erp_left').height(wenH - headH - footH - leftCdH);
    }
    )
    //点击左侧框架收缩展开
    $('.left_cd').click(function() {
        var erp_left = $('.erp_left');
        var erp_nav = $('.erp_nav');
        var erp_right = $('.erp_right');
        
        var inn = $('.erp-con-nav-ul');
        var innLi = $('.erp-con-nav-ul li');
        var outW = $('.erp-con-nav-oh').width();
        var innW = $('.erp-con-nav-ul').width();
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        
        $(this).animate({
            'left': '-200px'
        }, 400);
        erp_left.animate({
            'left': '-200px'
        }, 400, function() {
            
            $('.left_back').fadeIn();
        
        }
        );
        erp_nav.animate({
            'padding-left': '0'
        }, 400);
        
        erp_right.animate({
            'padding-left': '0'
        }, 400);
        
        
        if (innW <= (outW + leftCdW)) {
            navLeft.hide();
            navRight.hide();
            
            inn.animate({
                'left': '0'
            }, 400);
        
        } 
        else {
            var erp_left = inn.css('left');
            var a_index = 0;
            var leftliW = 0;
            
            
            
            innLi.each(function() {
                if ($(this).hasClass('active')) {
                    a_index = $(this).index();
                }
            }
            )
            for (var i = 0; i < a_index; i++) {
                leftliW += innLi.eq(i).outerWidth(true);
            }
            
            if (Math.abs(parseInt(erp_left)) < leftCdW) {
                inn.animate({
                    'left': 0
                }, 400);
                navLeft.addClass('active');
            } 
            else {
                inn.animate({
                    'left': parseInt(erp_left) + leftCdW + 'px'
                }, 400);
            }
        }
    
    
    }
    )
    $('.left_back').click(function() {
        var erp_left = $('.erp_left');
        var erp_nav = $('.erp_nav');
        var erp_right = $('.erp_right');
        
        var inn = $('.erp-con-nav-ul');
        var innLi = $('.erp-con-nav-ul li');
        var outW = $('.erp-con-nav-oh').width();
        var innW = $('.erp-con-nav-ul').width();
        var navLeft = $('.nav-left-button');
        var navRight = $('.nav-right-button');
        
        erp_left.animate({
            'left': '0'
        }, 400);
        erp_nav.animate({
            'padding-left': '200px'
        }, 400);
        
        erp_right.animate({
            'padding-left': '200px'
        }, 400);
        $('.left_cd').animate({
            'left': '0'
        }, 400);
        $(this).fadeOut();
        
        var t_index = 0;
		var t_width=0;
        //当前高亮li的索引
        $('.erp-con-nav-oh ul li').each(function() {
            if ($(this).hasClass('active')) {
                t_index = $(this).index();
            }
        })
		
		for(var i=0;i<t_index;i++){
			t_width+=$('.erp-con-nav-oh ul li').eq(i).outerWidth(true);
			}
		
        if (innW > (outW - leftCdW)) {
            navLeft.show().removeClass('active');
            navRight.show().removeClass('active');
        }



        //如果超出可显示区域
			var index_left=innLi.eq(t_index).offset().left;
			var index_width=innLi.eq(t_index).width();
			//console.log(t_width)
			//console.log(index_left)
			//console.log(inn.css('left'))
			if(index_left>200&&parseInt(inn.css('left'))<0){
				inn.stop(false, true).animate({
                       'left': '-=' + 200 + 'px'
                    }, 500);
				}
			else if((innW+200)>outW&&parseInt(inn.css('left'))>=-200&&index_left>=200){
				inn.stop(false, true).animate({
                       'left': '-=' + Math.abs((outW-200-innW)) + 'px'
                    }, 500);
				}
			/*else if((innW+200)>outW&&parseInt(inn.css('left'))>=-200&&index_left<outW-200){
				inn.stop(false, true).animate({
                       'left': '=' + (t_width-(outW-200)) + 'px'
                    }, 500);
				}*/
			/*if((index_left+index_width)>(outerW+200)){
				inn.stop(false, true).animate({
                       'left': '-=' + (parseInt(index_left+index_width-(outerW+200)+20)) + 'px'
                    }, 500);
				}*/
		//console.log(t_width)
		//console.log(inn.css('left'))
		//console.log(t_width)
		//console.log(leftCdW)
		/*if(parseInt(inn.css('left'))<=0 && (t_width>leftCdW)){
			inn.animate({
                left: '-=' + (t_width-leftCdW) + 'px'
            }, 400);
			}
		else if(parseInt(inn.css('left'))<=0 && (t_width<=leftCdW)){
			inn.animate({
                left: '-=' + Math.abs(leftCdW-t_width) + 'px'
            }, 400);
			
			}*/
		
		
		
		//console.log(inn.css('left'))
		//console.log(t_width)
		//console.log(leftCdW)
       /* if(parseInt(inn.css('left'))<0 && (t_width>leftCdW)){
			//console.log(1)
			inn.animate({
                left: '-=' + leftCdW + 'px'
            }, 400);
			}
		else if(parseInt(inn.css('left'))<0 && (t_width<=leftCdW)){
			//console.log(2)
			inn.animate({
                left: '-=' + Math.abs(leftCdW-t_width) + 'px'
            }, 400);
			
			}*/
    }
    )

}
);



//点击显示弹出框
function clickShow(oclick, oarr) {
    oclick.click(function() {
        for (var i = 0; i < oarr.length; i++) {
            oarr[i].show();
        }
    
    }
    )
}

//点击关闭弹出框
function clickClose(oclick, oarr) {
    oclick.click(function() {
        for (var i = 0; i < oarr.length; i++) {
            oarr[i].hide();
        }
    
    }
    )
}

//刷新
function reFresh() {
    
    $('.erp_right iframe').each(function() {
        //alert($(this).attr('src'))
        //window.parent.frames["bottom"].href = $(this).attr('src')
        if ($(this).is(':visible')) 
        {
            window.parent.frames["bottom"].href = $(this).attr('src')
        }
    }
    )


}




//点击关闭弹出提示框

$(function() {
    var tipClose = $('.tip-arrow-close');
    tipClose.click(function() {
        $(this).parent().fadeOut();
    
    }
    )

}
)

//拖动函数




