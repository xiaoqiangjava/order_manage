/**
 * 列出所有已经注册的账户信息
 * @returns
 */
function selectCodeInfoAction(){
	//console.log("账号总览!");
	$('#code_find_input').val("请输入关键字...");
	var url = "code/listCode.bsnt?bsnt="+new Date().getTime();
	$.post(url,function(result){
		if(result.status==0){
			//console.log("账号总览请求成功!");
			var codes = result.data;
			$('#code_table tbody').empty();
			for(var i=0;i<codes.length;i++){
				var createDate = new Date(codes[i].createTime);
				createDate = formatDate(createDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+codes[i].name+"</td><td>"+codes[i].nick+"</td>" +
						       "<td>"+createDate+"</td><td><a class='code_modify' href='javascript:;'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='code_delete' href='javascript:;'>删除</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("codeIndex",i);
				$('#code_table tbody').append(tr);
				$('#code_table tbody tr:odd').addClass("highlighting");
			}
			model.codeAllList = codes;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}
/**
 * 列出所有的角色信息
 * @returns
 */
function selectRoleInfoAction(){
//	console.log("角色列表!");
	$('#role_find_input').val("请输入关键字...");
	var url = "role/listRole.bsnt?bsnt="+new Date().getTime();
	$.post(url,function(result){
		if(result.status==0){
			//console.log("账号总览请求成功!");
			var roles = result.data;
			$('#role_table tbody').empty();
			for(var i=0;i<roles.length;i++){
				var createDate = new Date(roles[i].createTime);
				createDate = formatDate(createDate);
				var updateDate = new Date(roles[i].updateTime);
				updateDate = formatDate(updateDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+roles[i].roleName+"</td><td>"+roles[i].roleDesc+"</td>" +
						       "<td>"+createDate+"</td><td>"+ updateDate + "</td><td><a class='role_modify' href='javascript:;'>修改角色</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='role_delete' href='javascript:;'>删除角色</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("roleIndex",i);
				$('#role_table tbody').append(tr);
				$('#role_table tbody tr:odd').addClass("highlighting");
			}
			model.roleAllList = roles;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}
/**
 * 列出所有的权限信息
 * @returns
 */
function selectPermissionInfoAction(){
//	console.log("权限列表!");
	$('#permission_find_input').val("请输入关键字...");
	var url = "permission/listPermission.bsnt?bsnt="+new Date().getTime();
	$.post(url,function(result){
		if(result.status==0){
			//console.log("账号总览请求成功!");
			var permissions = result.data;
			$('#permission_table tbody').empty();
			for(var i=0;i<permissions.length;i++){
				var createDate = new Date(permissions[i].createTime);
				createDate = formatDate(createDate);
				var updateDate = new Date(permissions[i].updateTime);
				updateDate = formatDate(updateDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+permissions[i].permissionName+"</td><td>"+permissions[i].permissionDesc+"</td>" +
						       "<td>"+createDate+"</td><td>"+updateDate+"</td><td><a class='permission_modify' href='javascript:;'>修改权限</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='permission_delete' href='javascript:;'>删除权限</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("permissionIndex",i);
				$('#permission_table tbody').append(tr);
				$('#permission_table tbody tr:odd').addClass("highlighting");
			}
			model.permissionAllList = permissions;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}
/**
 * 查询角色分配列表
 * @returns
 */
function selectCodeRoleInfoAction(){
//	console.log("角色分配!");
	$('#permission_find_input').val("请输入关键字...");
	var url = "userRole/listUserRole.bsnt?bsnt=" + new Date().getTime();
	var param = {
		userRoleInfo:$('#code_role_find_input').val()	
	};
	$.post(url, param, function(result){
		if(result.status==0){
//			console.log("查询角色列表信息成功!");
			var userRoles = result.data;
//			console.log("用户角色信息:" + userRoles);
			$('#code_role_table tbody').empty();
			for(var i=0;i<userRoles.length;i++){
				var user = userRoles[i].user;
//				console.log("用户信息: " + user);
				var userId = user.id;
				var username = user.name;
				var usernick = user.nick;
//				console.log("用户名称: " + username);
				var roles = userRoles[i].role;
//				console.log("角色信息统计: " + roles.length);
//				console.log("角色信息: " + roles);
				//在表格中显示的角色名称
				var roleViewName = "";
				var roleViewDesc = "";
				for(var j=0;j<roles.length;j++){
					var role = roles[j];
//					console.log("角色信息: " + role);
					var roleId = role.roleId;
					var roleName = role.roleName;
					roleViewName = roleViewName.concat(roleName, "; ");
//					console.log("角色名称: " + roleName);
					var roleDesc = role.roleDesc;
					roleViewDesc = roleViewDesc.concat(roleDesc,"; ");
				}
				roleViewDesc = roleViewDesc.substring(0, roleViewDesc.length-2);
				roleViewName = roleViewName.substring(0, roleViewName.length-2);
//				console.log("在列表中显示的角色名称: " + roleViewName);
				var template = "<tr><td>"+(i+1)+"</td><td>"+username+"</td><td>"+usernick+"</td>" +
			       "<td>"+roleViewName+"</td><td>" + roleViewDesc + "</td><td><a class='code_role_modify' href='javascript:;'>角色管理</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
			       "</td></tr>";
				var tr = $(template);
				tr.data("userRoleIndex", i);
				$("#code_role_table tbody").append(tr);
				$('#code_role_table tbody tr:odd').addClass("highlighting");
			}
			model.userRoles = userRoles;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}
/**
 * 查询权限分配信息
 * @returns
 */
function selectRolePermissionInfoAction(){
//	console.log("权限分配!");
	$('#permission_find_input').val("请输入关键字...");
	var url = "rolePermission/listRolePermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
			rolePermissionInfo:$('#role_permission_find_input').val()
	};
	$.post(url, param, function(result){
		if(result.status==0){
//			console.log("查询角色权限信息成功!");
			var rolePermissions = result.data;
//			console.log("角色权限信息: " + rolePermissions);
			$("#role_permission_table tbody").empty();
			for(var i=0;i<rolePermissions.length;i++){
				//角色信息
				var role = rolePermissions[i].role;
				var roleName = role.roleName;
//				console.log("角色名称: " + roleName);
				var roleDesc = role.roleDesc;
//				console.log("角色描述: " + roleDesc);
				//权限信息
				var permissions = rolePermissions[i].permission;
//				console.log("权限数目: " + permissions.length);
				var permissionViewName = "";
				for(var j=0;j<permissions.length;j++){
					var permission = permissions[j];
					var permissionName = permission.permissionName;
					permissionViewName = permissionViewName.concat(permissionName, "; ");
				}
				permissionViewName = permissionViewName.substring(0, permissionViewName.length-2);
				var template = "<tr><td>"+(i+1)+"</td><td>"+roleName+"</td><td>"+roleDesc+"</td>" +
			       "<td>"+permissionViewName+"</td><td><a class='role_permission_modify' href='javascript:;'>权限管理</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
			       "</td></tr>";
				var tr = $(template);
				tr.data("rolePermissionIndex", i);
				$("#role_permission_table tbody").append(tr);
				$('#role_permission_table tbody tr:odd').addClass("highlighting");
			}
			model.rolePermissions = rolePermissions;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 搜索账号信息
 * @returns
 */
function codeFindAction(){
	//console.log("搜索账号信息!");
	var url = "code/findCode.bsnt";
	var param = {
		codeInfo:$('#code_find_input').val()
	};
	$.post(url,param,function(result){
		if(result.status==0){
			//console.log("搜索账号信息成功!");
			var codes = result.data;
			$('#code_table tbody').empty();
			for(var i=0;i<codes.length;i++){
				var createDate = new Date(codes[i].createTime);
				createDate = formatDate(createDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+codes[i].name+"</td><td>"+codes[i].nick+"</td>" +
						       "<td>"+ createDate +"</td><td><a class='code_modify' href='javascript:;'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='code_delete' href='javascript:;'>删除</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("codeIndex",i);
				$('#code_table tbody').append(tr);
				$('#code_table tbody tr:odd').addClass("highlighting");
			}
			model.codeAllList = codes;
		}else{
			alert(result.message);
		}
	});
}
/**
 * 搜索角色信息
 * @returns
 */
function roleFindAction(){
	console.log("搜索角色信息!");
	var url = "role/findRole.bsnt?bsnt=" + new Date().getTime();
	var roleInfo = $('#role_find_input').val();
	var param = {
			roleInfo:roleInfo
	};
	$.post(url, param, function(result){
		if(result.status==0){
			console.log("搜索角色信息请求成功!");
			var roles = result.data;
			$('#role_table tbody').empty();
			for(var i=0;i<roles.length;i++){
				var createDate = new Date(roles[i].createTime);
				createDate = formatDate(createDate);
				var updateDate = new Date(roles[i].updateTime);
				updateDate = formatDate(updateDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+roles[i].roleName+"</td><td>"+roles[i].roleDesc+"</td>" +
						       "<td>"+createDate+"</td><td>"+updateDate+"</td><td><a class='role_modify' href='javascript:;'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='role_delete' href='javascript:;'>删除</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("roleIndex",i);
				$('#role_table tbody').append(tr);
				$('#role_table tbody tr:odd').addClass("highlighting");
			}
			model.roleAllList = roles;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}
/**
 * 搜索权限信息
 * @returns
 */
function permissionFindAction(){
	console.log("搜索权限信息!");
	var url = "permission/findPermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
			permissionInfo:$('#permission_find_input').val()
	};
	$.post(url, param, function(result){
		if(result.status==0){
			//console.log("搜索权限请求成功!");
			var permissions = result.data;
			$('#permission_table tbody').empty();
			for(var i=0;i<permissions.length;i++){
				var createDate = new Date(permissions[i].createTime);
				createDate = formatDate(createDate);
				var updateDate = new Date(permissions[i].updateTime);
				updateDate = formatDate(updateDate);
				var template = "<tr><td>"+(i+1)+"</td><td>"+permissions[i].permissionName+"</td><td>"+permissions[i].permissionDesc+"</td>" +
						       "<td>"+createDate+"</td><td>"+updateDate+"</td><td><a class='permission_modify' href='javascript:;'>修改权限</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='permission_delete' href='javascript:;'>删除权限</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("permissionIndex",i);
				$('#permission_table tbody').append(tr);
				$('#permission_table tbody tr:odd').addClass("highlighting");
			}
			model.permissionAllList = permissions;
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 显示修改账号信息(审核)
 * @returns
 */
function showModifyCodeInfoAction(){
	$('#code_modify').fadeIn();
	var codeIndex = $(this).parent().parent().data("codeIndex");
	var codeId = model.codeList[codeIndex].id;
	var codeArea = model.codeList[codeIndex].area;
	var code = model.codeList[codeIndex].name;
	$('#code_check_code').val(code);
	$('#code_area').val(codeArea);
	model.codeId = codeId;
	
	//console.log("显示中的账号id:"+codeId);
}
/**
 * 账号总览中修改账号信息显示
 * @returns
 */
function showUpdateCodeInfoAction(){
	//console.log("账号总览中显示账号信息!");
	$('#code_info').fadeOut();
	$('#updateCodeInfo').fadeIn();
	$('#update_code_button').fadeIn();
	var codeIndex = $(this).parent().parent().data("codeIndex");
	//console.log(codeIndex);
	var codeName = model.codeAllList[codeIndex].name;
	var codeNick = model.codeAllList[codeIndex].nick;
	$('#code_name').val(codeName);
	$('#code_nick').val(codeNick);
	var codeId = model.codeAllList[codeIndex].id;
	model.codeAllId = codeId;
}
/**
 * 账号总览中修改账号信息
 * @returns
 */
function updateCodeInfoAction(){
	console.log("账号总览中修改账号信息!");
	var url = "code/updateCode.bsnt?bsnt="+new Date().getTime();
	var codeId = model.codeAllId;
	//console.log("修改的账号id:"+codeId);
	var param = {
			codeId:codeId,
			codeNick:$('#code_nick').val()
	};
	$.post(url,param,function(result){
		if(result.status==0){
			alert("账号信息修改成功!");
			$('#updateCodeInfo').fadeOut();
			$('#update_code_button').fadeOut();
			$('#code_info').fadeIn();
			selectCodeInfoAction();
		}else if(result.status==1){
			alert("账号信息修改失败!");
		}else{
			alert(result.message);
		}
	});
}
/**
 * 修改账号信息(审核状态)
 * @returns
 */
function modifyCodeInfoAction(){
	//console.log("修改账号信息!");
	var url = "code/updateCode.bsnt?bsnt="+new Date().getTime();
	var codeId = model.codeId;
	//console.log("修改的账号id:"+codeId);
	var param = {
			codeId:codeId,
			codeArea:$('#code_area').val(),
			flag:$('#check_status option').val()
	};
	$.post(url,param,function(result){
		if(result.status==0){
			alert("审核状态提交成功!");
			$('#code_modify').fadeOut();
		}else if(result.status==1){
			alert("审核状态提交失败!");
		}else{
			alert(result.message);
		}
	});
}

/**
 * 删除账号信息
 * @returns
 */
function deleteCodeInfoAction(){
	//console.log("删除账号信息!");
	var flag = confirm("您确定要删除该用户记录吗?");
	if(flag==false){
		return;
	}
	var codeIndex = $(this).parent().parent().data("codeIndex");
	//console.log(codeIndex);
	var codeId = model.codeAllList[codeIndex].id;
	var url = "code/deleteCode.bsnt?bsnt="+new Date().getTime();
	var param = {codeId:codeId};
	$.post(url,param,function(result){
		if(result.status==0){
			//console.log("删除账号信息成功!");
			alert("该账号已成功删除!");
			//删除成功后刷新账号列表
			selectCodeInfoAction();
		}else{
			alert(result.message);
		}
	});
}
/**
 * 显示修改角色信息的输入框
 * @returns
 */
function showUpdateRoleInfoAction(){
//	console.log("修改角色信息显示!");
	$('#role_info').fadeOut();
	$('#add_role_button').fadeOut();
	$('#updateRoleInfo').fadeIn();
	$('#update_role_button').fadeIn();
	$('.role_span').fadeIn();
	var roleIndex = $(this).parent().parent().data("roleIndex");
//	console.log("角色列表中的序号: " + roleIndex);
	var roleName = model.roleAllList[roleIndex].roleName;
	var roleDesc = model.roleAllList[roleIndex].roleDesc;
	var roleId = model.roleAllList[roleIndex].id;
	$('#role_name').val(roleName);
	$('#role_desc').val(roleDesc);
	$('#role_id').val(roleId);
	model.roleAllId = roleId;
//	console.log("角色id: " + model.roleAllId);
}

/**
 * 修改角色信息
 */
function updateRoleInfoAction(){
//	console.log("修改角色信息!");
	var flag = confirm("您确定要修改角色信息吗?");
	if(flag == false){
		return;
	}
	var url = "role/updateRole.bsnt?bsnt="+ new Date().getTime();
	var param = {roleId:model.roleAllId, roleName:$('#role_name').val(), roleDesc:$('#role_desc').val()};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("角色信息修改成功!");
//			$('#role_all').click();
			$('#updateRoleInfo').fadeOut();
			$('#role_info').fadeIn();
			selectRoleInfoAction();
			
		}else if(result.status==6){
			alert(result.message);
			location.href  = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 删除角色信息
 */
function deleteRoleInfoAction(){
//	console.log("删除角色信息!");
	var flag = confirm("您确定要删除该角色信息吗?");
	if(flag==false){
		return;
	}
	var roleIndex = $(this).parent().parent().data("roleIndex");
	var roleId = model.roleAllList[roleIndex].id;
	var param = {roleId:roleId};
	var url = "role/deleteRole.bsnt?bsnt=" + new Date().getTime();
	$.post(url, param, function(result){
		if(result.status==0){
			alert("该角色信息删除成功!");
			selectRoleInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 增加角色信息页面
 */
function showAddRoleInfoAction(){
//	console.log("增加角色信息页面!");
	$('#role_info').fadeOut();
	$('#update_role_button').fadeOut();
	$('.role_span').fadeOut();
	$('#updateRoleInfo').fadeIn();
	$('#add_role_button').fadeIn();
	$('#role_name').val("");
	$('#role_desc').val("");
}

/**
 * 增加角色信息
 */
function addRoleInfoAction(){
//	console.log("增加角色信息!");
	var url = "role/createRole.bsnt?bsnt=" + new Date().getTime();
	var param = {
		roleName:$('#role_name').val(),
		roleDesc:$('#role_desc').val()
	};
	$.post(url, param, function(result){
		if(result.status == 0){
			alert("角色信息添加成功!");
//			$('#role_all').click();
			$('#updateRoleInfo').fadeOut();
			$('#role_info').fadeIn();
			selectRoleInfoAction();
		}else if(result.status == 6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 修改权限信息页面
 * @returns
 */
function showUpdatePermissionInfoAction(){
//	console.log("修改权限信息页面!");
	$('#permission_info').fadeOut();
	$('#add_permission_button').fadeOut();
	$('#updatePermissionInfo').fadeIn();
	$('.permission_span').fadeIn();
	$('#update_permission_button').fadeIn();
	var permissionIndex = $(this).parent().parent().data("permissionIndex");
//	console.log("权限列表中的行号: " + permissionIndex);
	var permissionId = model.permissionAllList[permissionIndex].id;
//	console.log("权限id: " + permissionId);
	var permissionName = model.permissionAllList[permissionIndex].permissionName;
	var permissionDesc = model.permissionAllList[permissionIndex].permissionDesc;
	$('#permission_id').val(permissionId);
	$('#permission_name').val(permissionName);
	$('#permission_desc').val(permissionDesc);
	model.permissionAllId = permissionId;
}

/**
 * 修改权限信息
 */
function updatePermissionInfoAction(){
//	console.log("修改权限信息!");
	var flag = confirm("您确定要修改该权限信息吗?");
	if(flag==false){
		return;
	}
	var url = "permission/updatePermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
		permissionId: $('#permission_id').val(),
		permissionName: $('#permission_name').val(),
		permissionDesc: $('#permission_desc').val()
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("修改权限信息成功!");
//			$('#permission_all').click();
			$('#updatePermissionInfo').fadeOut();
			$('#permission_info').fadeIn();
			selectPermissionInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 删除权限信息
 * @returns
 */
function deletePermissionInfoAction(){
//	console.log("删除权限信息!");
	var flag = confirm("您确定要删除该权限信息吗?");
	if(flag==false){
		return;
	}
	var url = "permission/deletePermission.bsnt?bsnt=" + new Date().getTime();
	var permissionIndex = $(this).parent().parent().data("permissionIndex");
	var permissionId = model.permissionAllList[permissionIndex].id;
	var param = {
		permissionId: permissionId
	};
	$.post(url, param ,function(result){
		if(result.status==0){
			alert("权限信息删除成功!");
			selectPermissionInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 增加权限信息页面
 * @returns
 */
function showAddPermissionInfoAction(){
//	console.log("增加权限信息页面!");
	$('#permission_info').fadeOut();
	$('#update_permission_button').fadeOut();
	$('.permission_span').fadeOut();
	$('#updatePermissionInfo').fadeIn();
	$('#add_permission_button').fadeIn();
	$('#permission_name').val("");
	$('#permission_desc').val("");
}

/**
 * 增加权限信息
 */
function addPermissionInfoAction(){
//	console.log("增加权限信息!");
	var url = "permission/createPermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
		permissionName: $('#permission_name').val(),
		permissionDesc: $('#permission_desc').val()
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("新增权限信息成功!");
//			$('#permission_all').click();
			$('#updatePermissionInfo').fadeOut();
			$('#permission_info').fadeIn();
			selectPermissionInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 角色管理按钮的单击事件即角色管理页面显示
 * @returns
 */
function codeRoleManagerAction(){
//	console.log("角色管理页面!");
	$('#code_role_info').fadeOut();
	$('#updateUserRoleInfo').fadeIn();
	$('#userRole_add_button').fadeIn();
	$('#userRole_delete_button').fadeIn();
	var userRoleIndex = $(this).parent().parent().data("userRoleIndex");
//	console.log("编号: " + userRoleIndex);
	var userRoles = model.userRoles;
	var user = userRoles[userRoleIndex].user;
	var username = user.name;
	var usernick = user.nick;
	var userId = user.id;
//	console.log("用户id: " + userId);
	$('#userRole_user_name').val(username);
	$('#userRole_user_nick').val(usernick);
	model.userRoleUserId = userId;
	
}

/**
 * 分配角色
 */
function addUserRoleAction(){
//	console.log("分配角色!");
	var userId = model.userRoleUserId;
//	console.log("分配角色中的userId: " + userId);
	var roleName = $('#userRole_role_name').val();
	var url = "userRole/addUserRole.bsnt?bsnt=" + new Date().getTime();
	var param = {
		userId:userId,
		roleName:roleName
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("角色分配成功,该用户已经拥有该角色的全部权限!");
			$('#updateUserRoleInfo').fadeOut();
			$('#code_role_info').fadeIn();
			selectCodeRoleInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 角色限制
 */
function deleteUserRoleAction(){
//	console.log("角色限制!");
	var userId = model.userRoleUserId;
//	console.log("角色限制时的userId: " + userId);
	var roleName = $('#userRole_role_name').val();
	var url = "userRole/deleteUserRole.bsnt?bsnt=" + new Date().getTime();
	var param = {
		userId:userId,
		roleName:roleName
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("角色限制成功,该用户已经不具有该角色的全部权限!");
			$('#updateUserRoleInfo').fadeOut();
			$('#code_role_info').fadeIn();
			selectCodeRoleInfoAction();
		}else if(result.status==6){
			alert(result.message);
		}else{
			alert(result.message);
		}
	});
}

/**
 * 权限管理按钮的单击事件即权限管理页面显示
 * @returns
 */
function rolePermissionManagerAction(){
//	console.log("权限管理页面!");
	$('#role_permission_info').fadeOut();
	$('#updateRolePermissionInfo').fadeIn();
	$('#rolePermission_add_button').fadeIn();
	$('#rolePermission_delete_button').fadeIn();
	var rolePermissionIndex = $(this).parent().parent().data("rolePermissionIndex");
	var rolePermissions = model.rolePermissions;
	var role = rolePermissions[rolePermissionIndex].role;
	var roleName = role.roleName;
	var roleDesc = role.roleDesc;
	$('#rolePermission_role_name').val(roleName);
	$('#rolePermission_role_desc').val(roleDesc);
	model.rolePermissionRoleId = role.roleId;
}

/**
 * 权限分配
 */
function addRolePermissionAction(){
//	console.log("权限分配!");
	var roleId = model.rolePermissionRoleId;
//	console.log("权限分配中的roleId: " + roleId);
	var permissionName = $('#rolePermission_permission_name').val();
	var url = "rolePermission/addRolePermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
			roleId:roleId,
			permissionName:permissionName
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("权限分配成功,该角色已经拥有对应的资源访问权限!");
			$('#updateRolePermissionInfo').fadeOut();
			$('#role_permission_info').fadeIn();
			selectRolePermissionInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	});
}

/**
 * 权限限制
 */
function deleteRolePermissionAction(){
//	console.log("权限限制!");
	var roleId = model.rolePermissionRoleId;
//	console.log("权限限制中的roleId: " + roleId);
	var url = "rolePermission/deleteRolePermission.bsnt?bsnt=" + new Date().getTime();
	var param = {
			roleId:roleId,
			permissionName:$('#rolePermission_permission_name').val()
	};
	$.post(url, param, function(result){
		if(result.status==0){
			alert("权限限制成功,该角色已经不拥有对应的资源访问权限!");
			$('#updateRolePermissionInfo').fadeOut();
			$('#role_permission_info').fadeIn();
			selectRolePermissionInfoAction();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert("权限限制失败!");
		}
	});
	
}


/**
 * 账号审批
 * @returns
 */
function codeCheckAction(){
	//console.log("账号审核!");
	var url = "code/listCode.bsnt?bsnt="+new Date().getTime();
	var param = {flag:"0"}
	$.post(url,param,function(result){
		if(result.status==0){
			//console.log("账号总览请求成功!");
			var codes = result.data;
			$('#check_table tbody').empty();
			for(var i=0;i<codes.length;i++){
				var flag;
				if(codes[i].flag==0){
					flag = "待审核";
				}else if(codes[i].flag==1){
					flag = "审核通过";
				}else if(codes[i].flag==2){
					flag = "拒绝";
				}else if(codes[i].flag==3){
					flag = "未审核";
				}else{
					flag = "审核中";
				}
				if(codes[i].area==null){
					codes[i].area = "";
				}
				var template = "<tr><td>"+(i+1)+"</td><td>"+codes[i].name+"</td><td>"+codes[i].nick+"</td>" +
						       "<td>"+codes[i].role+"</td><td>"+flag+"</td><td>"+codes[i].area+"</td><td><a class='code_modify' href='javascript:;'>审批</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						       "<a class='code_delete' href='javascript:;'>删除</a>" +
						       "</td></tr>";
				var tr = $(template);
				tr.data("codeIndex",i);
				$('#check_table tbody').append(tr);
			}
			model.codeList = codes;
			var number = codes.length;
			$('.code_number').html("");
			if(number!=0){
				$('.code_number').html("&nbsp;"+number+"&nbsp;");
			}
		}else{
			alert(result.message);
		}
	});
}
