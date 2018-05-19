$(function(){
	//订单管理按钮的单击事件
	$('#order_menu').click(orderMenuClick);
	//商品管理按钮的单击事件
	$('#shop_menu').click(shopMenuClick);
	$('#new_arrival').click(shopArrivalClick);
	$('#product_shelf').click(shopShelfClick);
	
	$('#code_menu').click(codeManagerClick);
	$('#code_all').click(codeAllClick);
	$('#role_all').click(roleAllClick);
	$('#permission_all').click(permissionAllClick);
	$('#code_role').click(codeRoleClick);
	$('#role_permission').click(rolePermissionClick);
	
	//账号管理模块中修改按钮的单击事件
	$('.code_modify_button').click(codeModifyButtonClick);
	
	$('#gecco_menu').click(geccoClick);
});
//账号管理中修改按钮的单击事件
function codeModifyButtonClick(){
	$('#code_info').fadeOut();
	$('#updateCodeInfo').fadeIn();
}


//网络爬虫模块加载
function geccoClick(){
	$('.edit_plate').fadeOut();
	$('.menu_child_list').fadeOut();
	$('#gecco').fadeIn();
}

//账号管理模块加载
function codeManagerClick(){
	//console.log("账号管理模块!");
	$('#code_code').fadeIn();
	$('#shop_list').fadeOut();
}
//账号总览模块加载
function codeAllClick(){
	//console.log("账号总览模块加载!");
	$('.edit_plate').fadeOut();   
	$('.sub_code_list').fadeOut();
	$('#updateCodeInfo').fadeOut();
	$('#code_all_list').fadeIn();
	$('#code_info').fadeIn();
}
//角色列表模块加载
function roleAllClick(){
	//console.log("账号审批模块加载!");
	$('.edit_plate').fadeOut();
	$('.sub_code_list').fadeOut();
	$('#updateRoleInfo').fadeOut();
	$('#role_all_list').fadeIn();
	$('#role_info').fadeIn();
}
//权限列表模块加载
function permissionAllClick(){
	$('.edit_plate').fadeOut();
	$('.sub_code_list').fadeOut();
	$('#updatePermissionInfo').fadeOut();
	$('#permission_all_list').fadeIn();
	$('#permission_info').fadeIn();
	
}
//角色分配列表模块加载
function codeRoleClick(){
	$('.edit_plate').fadeOut();
	$('.sub_code_list').fadeOut();
	$("#updateUserRoleInfo").fadeOut();
	$('#user_role_list').fadeIn();
	$('#code_role_info').fadeIn();
}
//权限分配列表模块加载
function rolePermissionClick(){
	$('.edit_plate').fadeOut();
	$('.sub_code_list').fadeOut();
	$('#updateRolePermissionInfo').fadeOut();
	$('#role_permission_list').fadeIn();
	$('#role_permission_info').fadeIn();
}


//订单模块加载
function orderMenuClick(){
	//console.log("double");
	$('.edit_plate').fadeOut();
	$('.menu_child_list').fadeOut();
	$('#order').fadeIn();
	
}
//商品模块加载
function shopMenuClick(){
	//console.log("shop");
	$('#shop_list').fadeIn();
	$('#code_code').fadeOut();
}

//新品上架模块加载(注意加载模块的顺序)
function shopArrivalClick(){
	$('.edit_plate').fadeOut();
	$('#shop_shelf').fadeOut();
	$('#shop_arrival').fadeIn();	
}
//商品下架模块加载
function shopShelfClick(){
	$('.edit_plate').fadeOut();
	$('#shop_arrival').fadeOut();
	$('#shop_shelf').fadeIn();
}