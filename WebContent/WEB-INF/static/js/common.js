 //checkbox全选全部选
function selectCheck(all, sub) {
    all.click(function() {
        sub.prop("checked", this.checked);
    });
    
    sub.click(function() {
        var $subs = sub;
        all.prop("checked", $subs.length == $subs.filter(":checked").length ? true : false);
    });

}
//checkbox点击父节点子节点全部选中
function selectPcheck(all, sub) {
    all.click(function() {
		$(this).parent('li').find('input').prop("checked", this.checked);
    });
    
    sub.click(function() {
        
        var $subs = sub;
        $(this).parent('li').parent('ul').siblings('input').prop("checked", $subs.length == $subs.filter(":checked").length ? true : false);
		$(this).parent('li').parent('ul').parent('li').parent('ul').siblings('input').prop("checked", $subs.length == $subs.filter(":checked").length ? true : false);
		
    });
	
	 

}
//点击显示弹出框
function clickShow(oclick, oarr,fn) {
    
    oclick.click(function() {
		
		if(fn){
			fn();
			}
			for (var i = 0; i < oarr.length; i++) {
            oarr[i].show();
        }
		//return false;
    })
}

//点击关闭弹出框
function clickClose(oclick, oarr,fn) {
    oclick.click(function() {
		if(fn){
			fn();
			}
		for (var i = 0; i < oarr.length; i++) {
            oarr[i].hide();
        }
		//return false;
    
    })
}

//点击删除按钮时文字变化
function delCheck(obj, osub, otext) {
	var itext=otext.attr('alt');
	
	var flag = 0;
    obj.live('click', function() {
		for(var i=0;i<osub.length;i++){
			if (osub[i].checked) {
                flag = 1;
				break;
            }
			else{
				flag = 0;
				}
			}
			console.log(flag)
        if (flag == 0) {
            otext.text('请至少要选择一条信息!');
        } 
        else {
            otext.text(itext);
        }
    })

}

//无限树状结构
function tree(obj) {
    obj.each(function() {
        if ($(this).children('ul').length != 0) {
            $(this).children('a').prepend('<i class="fa fa-caret-right mr6"></i>')
            $(this).children('ul').addClass('pl15').hide();
            $(this).children('a').click(function() {
                if ($(this).next('ul').is(':visible')) {
                    $(this).next('ul').hide();
                    $(this).children('i.fa').removeClass('fa-caret-down').addClass('fa-caret-right');
                } 
                else {
                    $(this).next('ul').show();
                    $(this).children('i.fa').removeClass('fa-caret-right').addClass('fa-caret-down');
                }
                return false;
            })
        }
    })

    //树状结构第一个显示
    obj.first().children('ul').show();
    obj.first().children('a').children('i.fa').removeClass('fa-caret-right').addClass('fa-caret-down');
}


//无限树状结构有复选框
function treeCheckbox(obj) {
    obj.each(function() {
        if ($(this).children('ul').length != 0) {
            $(this).prepend('<i class="fa fa-caret-right fa-lg cup mr5"></i>')
            $(this).children('ul').addClass('pl15').hide();
			if($(this).find('ul').children('li').children('ul').length==0){
				$(this).find('ul').children('li').css('display','inline-block');
				}
            $(this).children('i.fa').click(function() {
                if ($(this).parent('li').children('ul').is(':visible')) {
                    $(this).parent('li').children('ul').hide();
                    $(this).removeClass('fa-caret-down').addClass('fa-caret-right');
                } 
                else {
                    $(this).parent('li').children('ul').show();
                    $(this).removeClass('fa-caret-right').addClass('fa-caret-down');
                }
                return false;
            })
        }
    })

   //树状结构第一个显示
   //obj.first().children('ul').show();
   //obj.first().children('i.fa').removeClass('fa-caret-right').addClass('fa-caret-down');

}


//设置元素的高度随屏幕大小
function setHeight(obj) {
    
    obj.css('height', $(window).height() - 80 + 'px');

}


$(function() {
    //设置弹出框的正中间位置
    
    var wenH = $(window).height();
    var wenW = $(window).width();
    $('.PopLayer-conw').each(function() {
        
        var popW = $(this).outerWidth();
        var popH = $(this).outerHeight();
        
        $(this).css({'left': '50%','top': '50%','margin-left': -(popW / 2) + 'px','margin-top': -(popH / 2) + 'px'})
    
    })



    //鼠标经过表格高亮
    $('table tr').hover(function() {
        $(this).children('td').css('background', '#f1f1f1')
    
    }, function() {
        $(this).children('td').css('background', 'none')
    
    })

    //调用树
    tree($('.i-erpcon-nav-a ul li'));
    treeCheckbox($('.Pop-Supplement-con ul li'));



    //树状高度
    setHeight($('.i-erpcon-nav-a'));
    setHeight($('#Pop-height'));
    $(window).resize(function() {
        setHeight($('.i-erpcon-nav-a'));
        setHeight($('#Pop-height'));
    })

	
})
//tab切换
function Tab(oclick,obj,oclass){
	oclick.click(function(){
		var _index=$(this).index();
		$(this).addClass(oclass).siblings().removeClass(oclass);
		$(this).parent().siblings(obj).hide()
		$(this).parent().siblings(obj).eq(_index).show();
		})
	}



//点击关闭弹出提示框

$(function(){
	var tipClose=$('.tip-arrow-close');
	tipClose.click(function(){
		$(this).parent().fadeOut();
		
		})
//点击弹出详情伸缩(表格)
$('.tr_down').click(function(){
	var this_next=$(this).parent().next('.details_more');
	if(this_next.is(':visible')){
		$(this).parent().next('.details_more').hide();
		$(this).children('.fa-lg').removeClass('fa-angle-up').addClass('fa-angle-down');
		
		}
	else{
		$(this).parent().siblings().find('.fa-lg').removeClass('fa-angle-up').addClass('fa-angle-down');
		$(this).parent().siblings('.details_more').hide();
		$(this).parent().next('.details_more').fadeIn('fast');
		$(this).children('.fa-lg').removeClass('fa-angle-down').addClass('fa-angle-up');
		}
	
	
	})	
//点击关闭弹出提示
$('.alert .close').click(function(){
	$(this).parent().fadeOut('fast');
	
	})
//系统订单模版配置
//移到右边
	$('.add').click(function(){
		//先判断是否有选中
		if(!$(this).parents('.selectbox').find('.select1').find('option').is(":selected")){			
			alert("请选择需要移动的选项")
		}
		//获取选中的选项，删除并追加给对方
		else{
			
			$(this).parents('.selectbox').find('.select1').find('option:selected').appendTo($(this).parents('.selectbox').find('.select2'));
		}	
	});
	
	//移到左边
	$('.remove').click(function(){
		//先判断是否有选中
		if(!$(this).parents('.selectbox').find('.select2').find('option').is(":selected")){			
			alert("请选择需要移动的选项")
		}
		else{
			$(this).parents('.selectbox').find('.select2').find('option:selected').appendTo($(this).parents('.selectbox').find('.select1'));
		}
	});
	
	//全部移到右边
	$('.add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$(this).parents('.selectbox').find('.select1').find('option').appendTo($(this).parents('.selectbox').find('.select2'));
	});
	
	//全部移到左边
	$('.remove_all').click(function(){
		$(this).parents('.selectbox').find('.select2').find('option').appendTo($(this).parents('.selectbox').find('.select1'));
	});
	
	//双击选项
	$('.select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo($(this).parents('.selectbox').find('.select2')); //追加给对方
	});
	
	//双击选项
	$('.select2').dblclick(function(){
		$("option:selected",this).appendTo($(this).parents('.selectbox').find('.select1'));
	});
	//tab切换
	$('.tab_ul li').click(function(){
		var _index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.selectbox').hide()
		$('.selectbox').eq(_index).show();
		})
//弹出框拖动
 /*var dragging = false;
            var iX, iY;
            $(".modal-title").mousedown(function(e) {
                dragging = true;
                iX = e.clientX - this.offsetLeft;
                iY = e.clientY - this.offsetTop;
                $(this).parent().setCapture &&  $(this).parent().setCapture();
                return false;
            });
            document.onmousemove = function(e) {
                if (dragging) {
                var e = e || window.event;
                var oX = e.clientX - iX;
                var oY = e.clientY - iY;
                 $(this).parent().css({"left":oX + "px", "top":oY + "px"});
                return false;
                }
            };
            $(document).mouseup(function(e) {
                dragging = false;
                 $(this).parent()[0].releaseCapture();
                e.cancelBubble = true;
            })*/
 	})
