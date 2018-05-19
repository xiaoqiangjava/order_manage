$(function(){
	//console.log("页面加载之后执行的函数!")
	$('#sig_in').click(sigButton);
	
	$('#count').focus().blur(checkLoginCode);
	$('#password').blur(checkLoginPassword);
	$('#login').click(loginAction);
	
	$('#regist_button').click(registAction);
	$('#regist_code').blur(checkRegistCode);
	$('#nickname').blur(checkNickname);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkRegistFinalPassword)
	$('#back').click(back);
});
/**
 * 注册方法
 * @returns
 */
function registAction(){
	if(checkRegistCode()+checkRegistPassword()+checkRegistFinalPassword()!=3){
		return ;
	}
	//console.log("注册!");
	var url = "user/regist.bsnt";
	var param = {
		username:$('#regist_code').val(),
		nick:$('#nickname').val(),
		password:$('#regist_password').val(),
		confirm:$('#final_password').val(),
		area:$('#area').val()
	};
	$.post(url,param,function(jsonResult){
		if(jsonResult.status==0){
			//console.log("注册成功!");
			var flag = confirm("注册成功,请登录!");
			if(flag){
				var username = $('#regist_code').val();
				$('#count').val(username);
				$('#back').click();
			}
		}else if(jsonResult.status==1){
			//console.log(jsonResult.message);
			$('#warning_1').html(jsonResult.message).css("color","red");
		}else if(jsonResult.status==2){
			//console.log(jsonResult.message);
			$('#warning_2').html(jsonResult.message).css("color","red");
		}else{
			alert(jsonResult.message);
		}
	});
}
/**
 * 登录方法
 * @returns
 */
function loginAction(){
	if(checkLoginCode()+checkLoginPassword()!=2){
		return;
	}
	//console.log("登录!");
	var url = "user/login.bsnt?date="+new Date().getTime();
	var param = {
			username:$('#count').val(),
			password:$('#password').val()
	};
	$.get(url,param,function(jsonResult){
		if(jsonResult.status==0){
			console.log("登录成功!");
			//console.log(jsonResult.data);
			//跳转页面
			var user = jsonResult.data;
			setCookie("userId",user.id);
			setCookie("token",user.token);
			setCookie("nick",user.nick);
			setCookie("code",user.name);
			var role = user.role;
			
//			if(role == "管理员"){
				location.href = "manager.jsp";				
//			}else if(role == "VIP会员"){
//				location.href = "bsntVIP.html";
//			}else if(role == "普通会员"){
//				location.href = "bsnt.html";
//			}else{
//				alert("请以正确的身份登录!");
//			}
		}else if(jsonResult.status==1){
			//console.log("账号错误!");
			$('#count-msg').html(jsonResult.message);
		}else if(jsonResult.status==2){
			//console.log("密码错误!");
			$('#password-msg').html(jsonResult.message);
		}else{
			//console.log("其他错误!");
			alert(jsonResult.message);
		}
	});
}
/**
 * 检查注册账号是否合法
 * @returns boolean
 * 			true:账号合法
 * 			false:账号不合法
 */
function checkRegistCode(){
	//console.log("检查注册账号是否合法!");
	var username = $('#regist_code').val();
	var reg = /^\w{3,16}$/;
	if(reg.test(username)){
		$('#warning_1').html("<img src='images/ok.png'>").css("color","");
		return true;
	}else{
		$('#warning_1').html("必须为3~16位字符!").css("color","red");
		return false;
	}
}
/**
 * 检查昵称是否为空
 */
function checkNickname(){
	//console.log("检查昵称!");
	var nickname = $("#nickname").val();
	if(nickname){
		$('#warning_4').html("<img src='images/ok.png'>");
	}
}
/**
 * 检查注册密码是否合法
 * @returns boolean
 * 			true:密码合法
 * 			false:密码不合法
 */
function checkRegistPassword(){
	//console.log("检查注册密码是否合法!");
	var password = $('#regist_password').val();
	var reg = /^\w{6,16}$/;
	if(reg.test(password)){
		$('#warning_2').html("<img src='images/ok.png'>").css({"color":""});
		return true;
	}else{
		$('#warning_2').html("必须为6~16位字符!").css("color","red");
		return false;
	}
}

/**
 * 检查注册确认密码是否合法
 * @returns boolean
 * 			true:密码合法
 * 			false:密码不合法
 */
function checkRegistFinalPassword(){
	//console.log("检查确认密码是否合法!");
	var registPassword = $('#final_password').val();
	var password = $('#regist_password').val();
	var reg = /^\w{6,16}$/;
	if(reg.test(registPassword)){
		if(registPassword.length == password.length){
			$('#warning_3').html("<img src='images/ok.png'>").css({"color":""});
			return true;
		}else{
			$('#warning_3').html("两次密码长度不一致!").css("color","red");
			return false;
		}
	}else{
		$('#warning_3').html("必须为6~16位字符!").css("color","red");
		return false;
	}
}
/**
 * 检查登录账号是否合法
 * @returns boolean 
 * 			true:账号合法
 * 			false:账号不合法
 */
function checkLoginCode(){
	//console.log("检查登录账号!");
	var username = $('#count').val();
	//console.log(username);
	var reg = /^\w{3,16}$/;
	if(reg.test(username)){
		//console.log("账号合法!");
		$('#count-msg').html("");
		return true;
	}else{
		//console.log("账号必须由3~16位字母组成!");
		$('#count-msg').html("必须为3~16位字符!");
		return false;
	}
}
/**
 * 检查登录密码是否合法
 * @returns boolean
 * 			true:密码合法
 * 			false:密码不合法
 */
function checkLoginPassword(){
	//console.log("检查登录密码!");
	var password = $('#password').val();
	var reg = /^\w{6,16}$/;
	if(reg.test(password)){
		//console.log("密码合法!");
		$('#password-msg').html("");
		return true;
	}else{
		//console.log("密码必须有6~16位字母组成!");
		$('#password-msg').html("必须为6~16位字符!");
		return false;
	}
}
/**
 * 定义登录页面注册按钮的单击事件
 * @returns
 */
function sigButton(){
	//console.log("注册按钮的单击事件!");
	$('#regist_code').focus();
	$('#count-msg').html("");
	$('#password-msg').html("");
}
/**
 * 注册页面返回按钮的单击事件
 */
function back(){
	$('#warning_1').html("* 由3~16位字符组成!").css("color","");
	$('#regist_code').val("");
	$('#nickname').val("");
	$('#warning_4').html("昵称(可不填)");
	$('#regist_password').val("");
	$('#warning_2').html("* 由6~16位字符组成!").css("color","");
	$('#final_password').val("");
	$('#warning_3').html("* 由6~16位字符组成!").css("color","");
}

