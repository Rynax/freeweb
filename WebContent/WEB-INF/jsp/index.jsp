<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录：沐田园丁</title>
<meta name="robots" content="noindex">
<meta name="googlerobot" content="noindex">
<meta name="baiduspider" content="noindex">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, no-store">
<meta http-equiv="Expires" content="0">
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="font/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/login.css" type="text/css">
<script language="javascript" type="text/javascript" charset="UTF-8" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/slide.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".slideInner").slide({
            slideContainer: $('.slideInner a'),
            effect: 'easeOutCirc',
            autoRunTime: 5000,
            slideSpeed: 1000,
            nav: true,
            autoRun: true,
            prevBtn: $('a.prev'),
            nextBtn: $('a.next')
        });
    });
    
    function submit(){
    	var username = $('#userId').val();
    	var password = $('#password').val();
    	var checkcode = $('#checkCode').val();
    	var login = { 
			"username" : username,
			"password" : password,
			"checkcode" : checkcode
		}
    	$.ajax({
    		type: "post",
    		url: "login.do",
    		data: {"data": JSON.stringify(login)},
    		datatype:"json",
    		async:true,
    		success:function( obj ){
    			if(obj == "登录成功") {
    				window.location.href = "user_index.do";
    			}else {
    				$('div.err').text(obj);
    			}
    		},
    		error:function( obj ){
	    		$('div.err').text("连接服务器失败");
    	}});
    }
    
</script>
<script>
$(function(){
    //点击关闭登录失败
    clickClose($('#login-close'), [$('.bgdiv'), $('#login-pp')]);

    //点击取消登录失败
	clickClose($('#login-cancel'), [$('.bgdiv'), $('#login-pp')]);
	
	//登录框焦点
	$('.editors .e2 input,.editors .e3 input').focus(function(){
		$(this).addClass('focus');
		})
	$('.editors .e2 input,.editors .e3 input').blur(function(){
		$(this).removeClass('focus');
		})
	$('.editors .cc input').focus(function(){
		$(this).addClass('focus');
		})
	$('.editors .cc input').blur(function(){
		$(this).removeClass('focus');
		})
	})

</script>
</head>
<body scroll="no" class="d-chrome d-chrome42">
<div id="header"> <span class="logo"></span> </div>
<div class="container">
  <div class="slides">
    <div class="slideInner"> <a href="#" style="background: url(images/erp_login.jpg) no-repeat;"> </a> <a href="#" style="background: url(images/erp_login2.jpg) no-repeat"> </a> <a href="#" class="erp_focus_150416" style="background: url(images/erp_focus_150416.jpg) no-repeat;"> </a> <a href="#" class="erp_focus_150416" style="background: url(images/erp_focus_150417.jpg) no-repeat;"> </a> </div>
    <div class="nav"> <a class="prev" href="javascript:;"></a> <a class="next" href="javascript:;"></a> </div>
  </div>
  <div class="form" id="form" method="post" action="login.do">
  <!--<div class="form" id="form">-->
    <div class="wrap">
      <div class="title">欢迎使用沐田园丁CRM！</div>
      <div class="err" id="err"><span></span></div>
      <div class="editors"> 
        <p class="e2">
          <input name="username" id="userId" placeholder="账号">
        </p>
        <p class="e3">
          <input name="password" type="password" id="password" placeholder="密码">
        </p>
        <div class="cc">
          <label>验证码：</label>
          <input name="checkcode" id="checkCode">
          <span class="cappic"><img id="checkCode" title="点击换一个验证码" style="cursor:pointer" src="images/security.jpg"></span> <span>换一个</span> </div>
      </div>
      <!--<div class="checkCode"> <a class="pwd" href="http://erp.hupun.com/sys.password.d" target="_blank"></a></div>-->
        <!--<a href="index-iframe.html">-->
        <button type="submit" class="button" onclick="submit()"><span>登 录</span></button>
        <!--</a>-->
    </div>
  </div>
  <!-- form --> 
</div>
<!-- container -->
<div class="footer">
  <p>版权所有©2015-2016 英特华(北京)国际文化交流中心</p>
</div>
<!--登录失败-->
<div class="PopLayer">
  <div class="bgdiv" style="z-index:9999; display:none;"></div>
  <div class="PopLayer-conw PopLayer-posa1 w400" style="z-index:10000; display:none;" id="login-pp">
    <h4 class="modal-title">登录失败<i class="fa  fa-remove posab-abs posab-r10 posab-t14 cup" id="login-close"></i></h4>
    <form class="regForm" method="get" action="">
      <div class="modal-body">
      <table cellpadding="3px" width="100%" border="0">
        <tbody>
          <tr>
            <td><i class="pr20"><img src="images/icon-error.gif" width="48" height="48"></i><span class="delete-text">登录失败,请确认登录信息是否正确!</span></td>
          </tr>
        </tbody>
      </table>
    </div>
      <div class="modal-footer text-al-r">
        <button type="button" class="form-btn-default btn-bg-337"  id="login-cancel">知道了</button>
      </div>
    </form>
  </div>
</div>
<!--end-->
</body>
</html>