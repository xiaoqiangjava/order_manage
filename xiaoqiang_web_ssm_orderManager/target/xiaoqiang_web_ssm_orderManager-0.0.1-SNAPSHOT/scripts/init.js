//该js是初始化js

//model模型封装浏览器端用到的方法和属性
model = {};

$(function(){
	var code = getCookie("code");
	$('#login_info').html(code);
	var nick = getCookie("nick");
	$('#login_nick').html(nick);
	//console.log("定义搜索功能!");
	$('#find_button').click(selectAction);
	$('#refresh').click(refreshAction);
	
	$('#menu_click').on('click','.menu_button',addClassAction);
	
	//监听订单编辑的单击事件
	$('#order_table').on('click','.edit_a',editOrderAction);
	
	//定义订单修改按钮的单击事件
	$('#modify_order').click(modifyOrderAction);
	
	//定义商品修改按钮的单击事件
	$('#modify_shop').click(modifyShopAction);
	
	$('#order_table').on('click','.order_detail',editDetailAction);
	
	$('#shop_find_button').click(findProductAction);
	
	//商品下架
	$('#shop_table').on('click','.shop_shelf_edit',editShopInfoAction);
	
	//定义商品下架返回列表的单击事件
	$('#cancle_edit').click(cancleEditAction);
	
	//定义商品下架修改按钮的单击事件
	$("#update_button").click(updateProductAction);
	
	//定义商品下架删除商品信息按钮的单击事件
	$('#delete_shop').click(deleteProductInfoAction);
	
	//定义批量删除商品时的复选框选中函数
	$('#shop_thead input').click(checkedProductAction);
	
	//商品下架刷新按钮
	$('#refresh_shop').click(refreshProductAction);
	
	//退出按钮的单击事件
	$('#exit').click(exitAction);
	
	//定义新品上架提交按钮的单击事件
	$('#new_submit').click(newProductArrivalAction);
	
	//定义品质惠购提交按钮的单击事件
	$('#quality_submit').click(qualityPruchasingAction);
	
//
	//账号列表按钮的单击事件
	$('#code_all').click(selectCodeInfoAction);
	
	//角色列表按钮的单击事件
	$('#role_all').click(selectRoleInfoAction);
	
	//权限列表按钮的单击事件
	$('#permission_all').click(selectPermissionInfoAction);
	
	//角色分配按钮的单击事件
	$('#code_role').click(selectCodeRoleInfoAction);
	
	//权限分配按钮的单击事件
	$('#role_permission').click(selectRolePermissionInfoAction);
	
	
	//账号总览中的搜索事件
	$('#code_find').click(codeFindAction);
	
	//角色列表中的搜索事件
	$('#role_find').click(roleFindAction);
	
	//权限列表中的搜索事件
	$('#permission_find').click(permissionFindAction);
	
	//监听账号总览中的修改按钮的单击事件
	$('#code_table').on('click', '.code_modify',showUpdateCodeInfoAction);
	
	//账号总览中修改账号信息确认按钮的单击事件
	$('#update_code_button').click(updateCodeInfoAction);
	
	//监听账号总览中的删除按钮的单击事件
	$('#code_table').on('click','.code_delete',deleteCodeInfoAction);
	
	//监听角色列表中修改按钮的单击事件
	$('#role_table').on('click', '.role_modify',showUpdateRoleInfoAction);
	
	//角色列表中修改角色信息确认修改按钮的单击事件
	$('#update_role_button').click(updateRoleInfoAction);
	
	//角色列表中增加角色按钮单击事件
	$("#add_role").click(showAddRoleInfoAction);
	
	//角色列表中增加角色信息确认按钮的单击事件
	$("#add_role_button").click(addRoleInfoAction);
	
	//监听角色列表中删除按钮的单击事件
	$('#role_table').on('click', '.role_delete',deleteRoleInfoAction);
	
	//权限列表中修改权限单击事件
	$('#permission_table').on('click', '.permission_modify', showUpdatePermissionInfoAction);
	
	//权限修改中的确认修改单击事件
	$('#update_permission_button').click(updatePermissionInfoAction);
	
	//权限列表中删除按钮的单击事件
	$('#permission_table').on('click', '.permission_delete', deletePermissionInfoAction);
	
	//权限列表中的新增权限按钮的单击事件
	$("#add_permission").click(showAddPermissionInfoAction);
	
	//权限列表中新增权限的确认按钮的单击事件
	$('#add_permission_button').click(addPermissionInfoAction);
	
	//角色分配中角色管理按钮的单击事件
	$('#code_role_table').on('click','.code_role_modify', codeRoleManagerAction);
	
	//角色分配中新增角色按钮的单击事件
	$('#userRole_add_button').click(addUserRoleAction);
	
	//角色分配中角色限制按钮的单击事件
	$('#userRole_delete_button').click(deleteUserRoleAction);
	
	//权限分配中权限管理按钮的单击事件
	$('#role_permission_table').on('click','.role_permission_modify', rolePermissionManagerAction);
	
	//权限分配中分配权限按钮的单击事件
	$('#rolePermission_add_button').click(addRolePermissionAction);
	
	//权限分配中权限限制按钮的单击事件
	$('#rolePermission_delete_button').click(deleteRolePermissionAction);
	
	//账号审核按钮的单击事件
//	$('#code_check').click(codeCheckAction);
	
	//待审核账号数目记录
//	showCodeCheckNumber();
	
	//监听账号审核中的修改按钮的单击事件
//	$('#check_table').on('click','.code_modify',showModifyCodeInfoAction);
	
	//账号审核中修改账号信息确认按钮的单击事件
//	$('#code_modify_confirm').click(modifyCodeInfoAction);
	
	//监听账号审核中的删除按钮的单击事件
//	$('#check_table').on('click','.code_delete',deleteCodeInfoAction);
	
	//功能按钮点击时账号管理中的修改页面隐藏
	$('.menu_button').click(function(){
		$('.code_update').fadeOut();
	});

//
	//网络爬虫功能
	$('#gecco_crawler').click(geccoCrawlerAction);
	
	//下载爬取的图片信息
	$('#gecco_download').click(geccoDownloadAction);
	
	//监听订单查询中的分页点击事件
	$('#page_view').on('click','li',loadOrderInfoAction);
	
	//监听商品信息中的分页点击事件
	$('#proPage_view').on('click','li',loadProductInfoAction);
});
//商品下架返回列表的单击事件
function cancleEditAction(){
	$('#update_shop_info').fadeOut();
	$('#delete_div').fadeIn();
	$('#shop_info').fadeIn();
	$('#proPages').fadeIn();
}
//批量删除商品复选框选中方法
function checkedProductAction(){
	//console.log("复选框按钮的选中效果!");
	var flagBody = $('#shop_tbody input').is(":checked");
	var flagHead = $('#shop_thead input').is(":checked");
	if(flagHead){
		//console.log("全部选中!");
		$('#shop_tbody input').not(":checked").click();	
	}else{
		//console.log("全部未选中");
		$('#shop_tbody input:checked').click();	
	}
}

//退出
function exitAction(){
	delCookie("userId");
	delCookie("nick");
	delCookie("token");
	delCookie("code");
	location.href = "login.html";
	console.log("退出登录!");
	location.reload();	
}

//显示待审批的账号条目数轮询

//function showCodeCheckNumber(){
//	codeCheckAction();
//	setTimeout('showCodeCheckNumber()',300000000);
//}






