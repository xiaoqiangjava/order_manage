<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>百事农通管理页面</title>
<link rel="stylesheet" href="styles/manager.css?time=Math.random()">
<link rel="stylesheet" href="styles/shop.css">
<link rel="stylesheet" href="styles/arrival.css">
<script type="text/javascript" src="scripts/date.js"></script>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<script type="text/javascript" src="scripts/init.js"></script>
<script type="text/javascript" src="scripts/laydate/laydate.js"></script>
<script type="text/javascript" src="scripts/order_date.js"></script>
<script type="text/javascript" src="scripts/product_date.js"></script>
<script type="text/javascript" src="scripts/edit_plate.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/cookie_util.js"></script>
<script type="text/javascript" src="scripts/orderInfo.js"></script>
<script type="text/javascript" src="scripts/edit_order.js"></script>
<script type="text/javascript" src="scripts/order_detail.js"></script>
<script type="text/javascript" src="scripts/refresh.js"></script>
<script type="text/javascript" src="scripts/shelf_product.js"></script>
<script type="text/javascript" src="scripts/refresh_shop.js"></script>
<script type="text/javascript" src="scripts/arrival_products.js"></script>
<script type="text/javascript" src="scripts/code.js"></script>
<script type="text/javascript" src="scripts/gecco.js"></script>
</head>
<body>
<div id="global">
	<!-- logo区域 -->
	<div id="logo">
		<img src="images/logo(1).png">
		<div style="height:120px;">
		<h1>甘肃百事农通电子商务有限公司后台管理系统</h1></div>
		<span id="time"></span>
		<span id="login_code"><span><img src="images/admin.png"></span><span id="login_info"></span></span>
		<a href="javascript:;" id="exit" style="text-decoration: none">[退出]</a>
	</div>
	<!-- 菜单区域 -->
	<div id="menu">
		<div id="menu_list">
			<div id="menu_list_1">功能列表☟</div>
		</div>
		<div id="menu_click">
		<shiro:hasPermission name="code:view">
			<button class="menu_button font" id="code_menu">账号管理</button>
			<div id="code_code" class="menu_child_list">
			<shiro:hasPermission name="user:view">
				<button class="menu_button sub_menu" id="code_all"><span style="margin-left: 25%;">▶账号列表</span></button><br>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:view">
				<button class="menu_button sub_menu" id="role_all"><span style="margin-left: 25%;">▶角色列表</span></button>
			</shiro:hasPermission>
			<shiro:hasPermission name="permission:view">
				<button class="menu_button sub_menu" id="permission_all"><span style="margin-left: 25%;">▶权限列表</span></button>			
			</shiro:hasPermission>
			<shiro:hasPermission name="userRole:view">
				<button class="menu_button sub_menu" id="code_role"><span style="margin-left: 25%;">▶角色分配</span></button>			
			</shiro:hasPermission>
			<shiro:hasPermission name="rolePermission:view">
				<button class="menu_button sub_menu" id="role_permission"><span style="margin-left: 25%;">▶权限分配</span></button>			
			</shiro:hasPermission>
			</div>
		</shiro:hasPermission>
		<shiro:hasPermission name="order:view">
			<button class="menu_button font" id="order_menu">订单管理</button><br>
		</shiro:hasPermission>
		<shiro:hasPermission name="productInfo:view">
			<button class="menu_button font" id="shop_menu">商品管理</button>		
		</shiro:hasPermission>
			<div id="shop_list" class="menu_child_list">
			<shiro:hasPermission name="productInfo:view">
				<button class="menu_button" id="product_shelf">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶商品信息</button><br>			
			</shiro:hasPermission>
			<shiro:hasPermission name="productInfo:create">
				<button class="menu_button" id="new_arrival">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶新品上架</button>			
			</shiro:hasPermission>
			</div>
		<shiro:hasPermission name="productInfo:gecco">
			<button class="menu_button font" id="gecco_menu">信息采集</button>		
		</shiro:hasPermission>
		</div>
	</div>
<!-- 编辑区域 -->
	<div id="edit" class="edit_plate">
		<div>
			<span style="font-style: italic;font-weight: bold;" id="login_nick"></span> &nbsp;您好!欢迎登录百事农通后台管理界面!
		</div>
	</div>
<!-- 账号管理 -->
	<!-- 账号列表 -->
	<div id="code_all_list" class="edit_plate sub_code_list">
		<div style="margin: 20px 30px;">
			<div class="code_template_title">
				账号列表
			</div>
			<div class="code_input">
				<input id="code_find_input" type="text" value="请输入关键字..." style="border-radius: 4px;width:120px;height:30px;" onfocus="if(this.value=='请输入关键字...')this.value=''; " onblur=" if(this.value=='')this.value='请输入关键字...'; " onkeydown="if(event.keyCode==13){codeFindAction();}">
				<a id="code_find" href="javascript:;" style="cursor: pointer;text-decoration: none;font-size: medium;">搜&nbsp;索</a>
			</div>
			<div id="code_info" class="codeInfo" style="text-align: center;">
				<table id="code_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="10%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="10%">注册账号</th>
							<th width="10%">注册昵称</th>
							<th width="15%">注册时间</th>
							<th width="15%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<!-- 修改账号信息 -->
			<div id="updateCodeInfo" class="updateCodeInfo">
				账号名称: <input id="code_name" readonly="readonly" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				账号昵称: <input id="code_nick" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				<button id="update_code_button" class="code_modify_button">修&nbsp;&nbsp;改</button>
			</div>
		</div>
		
	</div>
	
	<!-- 角色列表 -->
	<div id="role_all_list" class="edit_plate sub_code_list">
		<div style="margin: 20px 30px;">
			<div class="code_template_title">
				角色列表
			</div>
			<div class="code_input">
				<input id="role_find_input" type="text" value="请输入关键字..." style="border-radius: 4px;width:120px;height:30px;" onfocus="if(this.value=='请输入关键字...')this.value=''; " onblur=" if(this.value=='')this.value='请输入关键字...'; " onkeydown="if(event.keyCode==13){roleFindAction();}">
				<a id="role_find" href="javascript:;" style="cursor: pointer;text-decoration: none;font-size: medium;">搜&nbsp;索</a>
				<shiro:hasPermission name="role:create">
				<button id="add_role" style="border-radius: 8px; width:120px;height:40px;background-color: #0e2;margin-left: 50px;font-size: medium;">添加角色</button>				
				</shiro:hasPermission>
			</div>
			<div id="role_info" class="codeInfo" style="text-align: center;">
				<table id="role_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="9%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="10%">角色名称</th>
							<th width="25%">角色描述</th>
							<th width="18%">创建时间</th>
							<th width="18%">修改时间</th>
							<th width="20%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<!-- 修改角色信息 -->
			<div id="updateRoleInfo" class="updateCodeInfo">
				<span class="role_span">角色编号: </span><input id="role_id" readonly="readonly" class="view_button role_span" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				角色名称: <input id="role_name" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				角色描述: <input id="role_desc" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				<button id="update_role_button" class="code_modify_button">修&nbsp;&nbsp;改</button>
				<button id="add_role_button" class="code_modify_button">添&nbsp;&nbsp;加</button>
			</div>
		</div>
	</div>
	<!-- 权限列表 -->
	<div id="permission_all_list" class="edit_plate sub_code_list">
		<div style="margin: 20px 30px;">
			<div class="code_template_title">
				权限列表
			</div>
			<div class="code_input">
				<input id="permission_find_input" type="text" value="请输入关键字..." style="border-radius: 4px;width:120px;height:30px;" onfocus="if(this.value=='请输入关键字...')this.value=''; " onblur=" if(this.value=='')this.value='请输入关键字...'; " onkeydown="if(event.keyCode==13){permissionFindAction();}">
				<a id="permission_find" href="javascript:;" style="cursor: pointer;text-decoration: none;font-size: medium;">搜&nbsp;索</a>
				<shiro:hasPermission name="permission:create">
				<button id="add_permission" style="border-radius: 8px; width:120px;height:40px;background-color: #0e2;margin-left: 50px;font-size: medium;">添加权限</button>				
				</shiro:hasPermission>
			</div>
			<div id="permission_info" class="codeInfo" style="text-align: center;">
				<table id="permission_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="6%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="15%">权限名称</th>
							<th width="30%">权限描述</th>
							<th width="17%">创建时间</th>
							<th width="17%">修改时间</th>
							<th width="15%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<!-- 修改权限信息 -->
			<div id="updatePermissionInfo" class="updateCodeInfo">
				<span class="permission_span">权限编号: </span> <input id="permission_id" readonly="readonly" class="view_button permission_span" type="text">
				权限名称: <input id="permission_name" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				权限描述: <input id="permission_desc" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
				<button id="update_permission_button" class="code_modify_button">修&nbsp;&nbsp;改</button>
				<button id="add_permission_button" class="code_modify_button">添&nbsp;&nbsp;加</button>
			</div>
		</div>
	</div>
	<!-- 角色分配 -->
	<div id="user_role_list" class="edit_plate sub_code_list">
		<div style="margin: 20px 30px;">
			<div class="code_template_title">
				角色分配
			</div>
			<div class="code_input">
				<input id="code_role_find_input" type="text" value="请输入关键字..." style="border-radius: 4px;width:120px;height:30px;" onfocus="if(this.value=='请输入关键字...')this.value=''; " onblur=" if(this.value=='')this.value='请输入关键字...'; " onkeydown="if(event.keyCode==13){selectCodeRoleInfoAction();}">
				<a id="code_role_find" href="javascript:;" style="cursor: pointer;text-decoration: none;font-size: medium;">搜&nbsp;索</a>
			</div>
			<div id="code_role_info" class="codeInfo" style="text-align: center;">
				<table id="code_role_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="10%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="15%">账号名称</th>
							<th width="15%">账号昵称</th>
							<th width="15%">拥有角色</th>
							<th width="30%">角色描述</th>
							<th width="20%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<!-- 角色分配 -->
			<div id="updateUserRoleInfo" class="updateCodeInfo">
			账号名称: <input id="userRole_user_name" readonly="readonly" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
			账号昵称: <input id="userRole_user_nick" readonly="readonly" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
			角色名称: <input id="userRole_role_name" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
			<shiro:hasPermission name="userRole:create">
			<button id="userRole_add_button" class="code_modify_button">分配角色</button> &nbsp;&nbsp;&nbsp;&nbsp; 			
			</shiro:hasPermission>
			<shiro:hasPermission name="userRole:delete">
			<button id="userRole_delete_button" class="code_modify_button">角色限制</button>			
			</shiro:hasPermission>
			</div>
		</div>
	</div>
	<!-- 权限分配 -->
	<div id="role_permission_list" class="edit_plate sub_code_list">
		<div style="margin: 20px 30px;">
			<div class="code_template_title">
				权限分配
			</div>
			<div class="code_input">
				<input id="role_permission_find_input" type="text" value="请输入关键字..." style="border-radius: 4px;width:120px;height:30px;" onfocus="if(this.value=='请输入关键字...')this.value=''; " onblur=" if(this.value=='')this.value='请输入关键字...'; " onkeydown="if(event.keyCode==13){selectRolePermissionInfoAction();}">
				<a id="role_permission_find" href="javascript:;" style="cursor: pointer;text-decoration: none;font-size: medium;">搜&nbsp;索</a>
			</div>
			<div id="role_permission_info" class="codeInfo" style="text-align: center;">
				<table id="role_permission_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="4%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="8%">角色名称</th>
							<th width="10%">角色描述</th>
							<th width="64%">拥有权限</th>
							<th width="14%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div id="updateRolePermissionInfo" class="updateCodeInfo">
			角色名称: <input id="rolePermission_role_name" readonly="readonly" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
			角色描述: <input id="rolePermission_role_desc" readonly="readonly" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
			权限名称: <input id="rolePermission_permission_name" class="view_button" type="text"> &nbsp;&nbsp;&nbsp;&nbsp;
			<shiro:hasPermission name="rolePermission:create">
			<button id="rolePermission_add_button" class="code_modify_button">分配权限</button> &nbsp;&nbsp;&nbsp;&nbsp; 			
			</shiro:hasPermission>
			<shiro:hasPermission name="rolePermission:delete">
			<button id="rolePermission_delete_button" class="code_modify_button">权限限制</button>			
			</shiro:hasPermission>
			</div>
			<!--  
			<div id="role_permission_update" class="code_update">
				会员账号: <input readonly type="text" id="code_all_code">&nbsp;&nbsp;&nbsp;
				会员角色: <input type="text" id="code_role">&nbsp;&nbsp;&nbsp;
				所属区域: <input type="text" id="code_all_area">
				&nbsp;&nbsp;&nbsp;<button class="code_confirm" id="code_update_confirm">确&nbsp;&nbsp;认</button><br>
				<span><br></span>
				<span>备注:</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;1>本公司内部人员全部是管理员,享有该系统的最高权限;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;2>本公司内部人员所属区域为公司简称'百事农通',可以操作所有订单;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;3>本公司新加入员工需要其他管理员将其角色修改为管理员才能正常使用,本软件不提供VIP会员到管理员的授权操作;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;4>未审核状态的用户为新注册的用户,需要提交申请成为VIP会员才可享有该系统的使用权.</span>
			</div>
			-->
		</div>
	</div>
	<!-- 账号审批 -->
	<!--  
	<div id="code_check_list" class="edit_plate">
		<div style="margin:20px 30px;">
			<div id="code_check_title">
				账号审核
			</div>
			<div id="check_info" style="text-align: center;">
				<table id="check_table" style="font-size: medium;">
					<thead>
						<tr>
							<th width="10%">编&nbsp;&nbsp;&nbsp;号</th>
							<th width="15%">会员账号</th>
							<th width="15%">会员昵称</th>
							<th width="15%">会员角色</th>
							<th width="15%">审核状态</th>
							<th width="15%">会员区域</th>
							<th width="15%">操&nbsp;&nbsp;&nbsp;作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div id="code_modify" class="code_update">
				会员账号: <input type="text" readonly id="code_check_code">&nbsp;&nbsp;&nbsp;
				请选择审核状态:&nbsp;&nbsp;
				<select id="check_status">
					<option value="1">审核通过</option>
					<option value="2">拒绝</option>
					<option value="4">审核中</option>
				</select>&nbsp;&nbsp;&nbsp;
				会员所属区域:&nbsp;&nbsp;<input type="text" id="code_area">
				&nbsp;&nbsp;&nbsp;<button class="code_confirm" id="code_modify_confirm">确&nbsp;&nbsp;认</button><br>
				<span><br></span>
				<span>备注:</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;1>审核状态是普通会员申请成为VIP会员的标志,提交后不能修改;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;2>审核通过意味着该用户已经成为VIP会员,将享有相应的操作权限;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;3>用户的访问权限由该用户对应的会员角色和所属区域决定,提交审核前应该核对该用户所属的区域;</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;4>用户所属区域应当与该用户可以操作的订单区域相同,格式为xxx市(州)xxx县(区),如:兰州市榆中县.</span>
			</div>
		</div>
	</div>
	-->
<!-- 订单区域 -->
	<div id="order" class="edit_plate">
		<div id="select_list">
			<div id="select_title">
				订单列表
			</div>
			<div class="select_1">
				<div>订&nbsp;单&nbsp;号&nbsp;: <input type="text" name="" id="order_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<shiro:hasPermission name="aliCode:view">
				<div>阿里单号: <input type="text" name="" id="ali_order">&nbsp;&nbsp;&nbsp;&nbsp;</div>				
				</shiro:hasPermission>
				<div>订单日期: <input type="text" name="" class="date" id="order_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>买家姓名: <input type="text" name="" id="receiver_name">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>产品名称: <input type="text" name="" id="product_name">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<shiro:hasPermission name="aliCode:view">
				<div>厂家地址: <input type="text" name="" id="vender_url">&nbsp;&nbsp;&nbsp;&nbsp;</div>				
				</shiro:hasPermission>
				<div>物&nbsp;流&nbsp;号&nbsp;: <input type="text" name="" id="logistics_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>支付方式: <input type="text" name="" id="pay_chnl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<shiro:hasPermission name="aliCode:view">
				<div>商品编号: <input type="text" name="" id="product_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>				
				</shiro:hasPermission>
				<div>买家地址: <input type="text" name="" id="receiver_add">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>开始日期: <input type="text" name="" class="date" id="start_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>结束日期: <input type="text" name="" class="date" id="end_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			</div>
			<div id="pay_status">
				支付状态: <input class="checkbox" name="pay_status" type="checkbox" value="0">待付款&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="1">已付款&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="2">待发货&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="3">待收货&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="4">待评价&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="5">退款/售后&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="6">退款成功&nbsp;
				<input class="checkbox" name="pay_status" type="checkbox" value="7">作废&nbsp;
			<shiro:hasPermission name="order:view">
				<button id="find_button" class="find_button">搜&nbsp;&nbsp;索</button><button id="refresh">刷&nbsp;&nbsp;新</button>			
			</shiro:hasPermission>
			</div>
		</div>
		<div id="select_info">
			<table id="order_table" class="table_fix">
				<thead>
					<tr>
						<th width="2%">编号</th>
						<th width="6.5%">订单号</th>
						<shiro:hasPermission name="aliCode:view">
						<th width="6.5%">阿里单号</th>						
						</shiro:hasPermission>
						<th width="4%">支付状态</th>
						<th width="7%">产品名称</th>
						<th width="5%">规格(尺寸)</th>
						<th width="3%">颜色</th>
						<th width="3%">购买数量</th>
						<th width="3%">单价</th>
						<shiro:hasPermission name="firstCost:view">
						<th width="5%">成本价</th>						
						</shiro:hasPermission>
						<th width="4%">收件人姓名</th>
						<th width="5%">收件人电话</th>
						<th width="8%">收件人地址</th>
						<th width="5%">物流公司</th>
						<th width="6%">物流编号</th>
						<shiro:hasPermission name="firstCost:view">
						<th width="7%">厂家地址</th>						
						</shiro:hasPermission>
						<shiro:hasPermission name="firstCost:view">
						<th width="6%">编辑</th>						
						</shiro:hasPermission>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>		
		</div>
		<div id="pages" style="text-align: center">
			<ul id="page_view">
			<!--  
				<li><a href="#">&laquo; 上一页</a></li>
				<li class="page"><a href="#">1</a></li>
				<li class="page active"><a href="#">2</a></li>
				<li class="page"><a href="#">3</a></li>
				<li class="page"><a href="#">4</a></li>
				<li class="page"><a href="#">5</a></li>
				<li class="page"><a href="#">6</a></li>
				<li><a href="#">下一页 &raquo;</a></li> 
				<li style="border:0;">总共<span id="total_count">13</span>条记录</li>
			-->
			</ul>
		</div>
	</div>
	
	<!-- 编辑定单 -->
	<div id="edit_order" class="edit_plate">
		<div class="info_plate">
			<div class="info_title">
				订单信息
			</div>
			<div class="info_list">
				<div>&nbsp;订单编号: <input readonly="readonly" type="text" name="" id="edit_order_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;订&nbsp;单&nbsp;号&nbsp;: <input readonly="readonly" type="text" name="" id="edit_order_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;支付方式: <input readonly="readonly" type="text" name="" id="edit_pay_chnl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;支付状态: <input type="text" name="" id="edit_status">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家姓名: <input type="text" name="" id="edit_receiver_name">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家电话: <input type="text" name="" id="edit_receiver_phone">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家地址: <input type="text" name="" id="edit_receiver_add" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>
				<shiro:hasPermission name="order:update">
				<button class="modify_button" id="modify_order">修&nbsp;&nbsp;改</button>			
				</shiro:hasPermission>
				</div>	
			</div>
		</div>
		<div class="info_plate">
			<div class="info_title">
				商品信息
			</div>
			<div class="info_list">
				<div>&nbsp;商品编号: <input readonly="readonly" type="text" name="" id="edit_shop_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品编码: <input readonly="readonly" type="text" name="" id="edit_shop_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品名称: <input readonly="readonly" type="text" name="" id="edit_shop_name" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;阿里单号: <input type="text" name="" id="edit_ali_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物&nbsp;流&nbsp;号&nbsp;: <input type="text" name="" id="edit_logistics_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物流公司: <input type="text" name="" id="edit_logistics_company">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物流中转: <input type="text" name="" id="edit_transfer">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>
				<shiro:hasPermission name="product:update">
				<button class="modify_button" id="modify_shop">修&nbsp;&nbsp;改</button>			
				</shiro:hasPermission>
				</div>
			</div>
		</div>
		
		<div id="cancle_plate">
			<button class="cancle_button">返回列表</button>
		</div>
	</div>
	
	<!-- 订单详情 -->
	<shiro:hasPermission name="order:view">
	<div id="order_detail" class="edit_plate">
		<div class="info_plate">
			<div class="info_title">
				订单详情
			</div>
			<div class="info_list">
				<div>&nbsp;订单编号: <input readonly type="text" name="" id="detail_order_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;订&nbsp;单&nbsp;号&nbsp;: <input readonly type="text" name="" id="detail_order_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;支付状态: <input readonly type="text" name="" id="detail_status">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;支付方式: <input readonly type="text" name="" id="detail_pay_chnl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家姓名: <input readonly type="text" name="" id="detail_receiver_name">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家电话: <input readonly type="text" name="" id="detail_receiver_phone">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家地址: <input readonly type="text" name="" id="detail_receiver_add" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;订单日期: <input readonly type="text" name="" id="detail_order_date"></div>
			</div>
		</div>
		<div class="info_plate">
			<div class="info_title">
				商品详情
			</div>
			<div class="info_list">
				<div>&nbsp;商品编号: <input readonly type="text" name="" id="detail_shop_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;订&nbsp;单&nbsp;号&nbsp;: <input readonly type="text" name="" id="detail_sorder_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品编码: <input readonly type="text" name="" id="detail_shop_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品名称: <input readonly type="text" name="" id="detail_shop_name" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;阿里单号: <input readonly type="text" name="" id="detail_ali_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物&nbsp;流&nbsp;号&nbsp;: <input readonly type="text" name="" id="detail_logistics_code">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物流公司: <input readonly type="text" name="" id="detail_logistics_company">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;物流中转: <input readonly type="text" name="" id="detail_transfer">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品颜色: <input readonly type="text" name="" id="detail_color">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品尺寸: <input readonly type="text" name="" id="detail_size">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品单价: <input readonly type="text" name="" id="detail_price">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品数量: <input readonly type="text" name="" id="detail_count">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品总价: <input readonly type="text" name="" id="detail_total_money">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;买家备注: <input readonly type="text" name="" id="detail_remark">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			</div>

		</div>	
		<div id="cancle_plate">
			<button class="cancle_button">返回列表</button>
		</div>
	</div>
	</shiro:hasPermission>
<!-- 商品区域 -->
	<!-- 新品上架 -->
	<shiro:hasPermission name="productInfo:create">
	<div id="shop_arrival" class="edit_plate">
	<div id="new_plate">
		<div id="new_product_arrival">
		<!-- 新品上架 -->
			<div class="new">新品上架</div>
			<div class="new_option">
				<div class="new_float">
					商品名称: <input type="text" name="productName" id="new_product_name" style="width:403px" autocomplete="on">
				</div>
				<div class="new_float">
					商品标签: <select id="new_lable" name="new_lable">
								<option value="0">请选择</option>
								<option value="1">新品上架</option>
								<option value="3">特价商品</option>
								<option value="4">百事农家乐</option>
								<option value="5">品牌专卖</option>
								<option value="6">批发专卖</option>
								<option value="7">商家入驻</option>
								<option value="8">商家服务</option>
								<option value="9">热销商品</option>
								<option value="10">买就送</option>
							</select>
				</div>
				<div class="new_float">
					商品类别: <input type="text" name="productClass" id="new_product_class" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					商品品牌: <input type="text" name="brand" id="new_brand" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					商品型号: <input type="text" name="artNumber" id="new_art_number" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					渠道价格: <input type="text" name="channelPrice" id="new_channel_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					运费价格: <input type="text" name="farePrice" id="new_fare_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					单件售价: <input type="text" name="price" id="new_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					单件原价: <input type="text" name="oldPrice" id="new_old_price" style="width:100px">
				</div>
				<div class="new_no_float" id="textarea">
					尺寸规格: <textarea name="size" id="new_size"></textarea>
					颜色样式: <textarea name="color" id="new_color"></textarea>
				</div>

				<div id="picture_float">
					<div>厂家地址: <input type="text" name="venderUrl" id="new_vender_url" style="width:403px"></div>
					<div>上传主图: <input type="file" name="file" id="new_picture_0" multiple="multiple" style="width:403px"></div>
					<div>上传副图: <input type="file" name="file" id="new_picture_1" multiple="multiple" style="width:403px"></div>
					<div>上传副图: <input type="file" name="file" id="new_picture_2" multiple="multiple" style="width:403px"></div>
					<div>上传副图: <input type="file" name="file" id="new_picture_3" multiple="multiple" style="width:403px"></div>
					<div>上传副图: <input type="file" name="file" id="new_picture_4" multiple="multiple" style="width:403px"></div>
					<div>上传详图: <input type="file" name="file" id="new_picture_5" multiple="multiple" style="width:403px"></div>
				</div>
			</div>
				<div align="center">
					<button id="new_submit" style="width:100px;height:40px;">提&nbsp;&nbsp;交</button>
				</div>
				<div id="parent" style="width=100%;">
				<div id="son" style="text-align:center;background-color: #13a8ee;"></div>
			</div>
		</div>
		<div id="quality_pruchasing">
		<!-- 品质惠购 -->
			<div class="new">品质惠购</div>
			<div class="new_option">
				<div class="new_float">
					商品名称: <input type="text" id="quality_product_name" style="width:403px" autocomplete="on">
				</div>
				<div class="new_float">
					商品标签: <select id="quality_lable" name="quality_lable">
								<option value="0">请选择</option>
								<option value="1">新品上架</option>
								<option value="2">品质惠购</option>
								<option value="3">特价商品</option>
								<option value="4">百事农家乐</option>
								<option value="5">品牌专卖</option>
								<option value="6">批发专卖</option>
								<option value="7">商家入驻</option>
								<option value="8">商家服务</option>
								<option value="9">热销商品</option>
								<option value="10">买就送</option>
							</select>
				</div>
				<div class="new_float">
					商品类别: <input type="text" id="quality_product_class" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					成本价格: <input type="text" id="quality_first_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					单件售价: <input type="text" id="quality_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float">
					单件原价: <input type="text" id="quality_old_price" style="width:100px" autocomplete="on">
				</div>
				<div class="new_float" >
					淘宝链接: <input type="text" style="width:403px" id="quality_taobao_url">
				</div>
				<div class="new_float" >
					返券链接: <input type="text" id="quality_king_url" style="width:403px">
				</div>
				<div class="new_float">
					上传主图: <input type="file"  style="width:403px" multiple="multiple" id="quality_picture_0">
				</div>
				<div class="new_float">
					上传副图: <input type="file" style="width:403px" multiple="multiple" id="quality_picture_1">
				</div>
				<div class="new_float">
					上传副图: <input type="file" style="width:403px" multiple="multiple" id="quality_picture_2">
				</div>
				<div class="new_float">
					上传副图: <input type="file" style="width:403px" multiple="multiple" id="quality_picture_3">
				</div>
				<div class="new_float">
					上传副图: <input type="file" style="width:403px" multiple="multiple" id="quality_picture_4">
				</div>
				<div class="new_float">
					上传详图: <input type="file" style="width:403px" multiple="multiple" id="quality_picture_5">
				</div>
			</div>
			<div align="center">
				<button id="quality_submit" style="width:100px;height:40px;">提&nbsp;&nbsp;交</button>
			</div>
		</div>
	</div>
	</div>
	</shiro:hasPermission>
	<!-- 商品下架 -->
	<div id="shop_shelf" class="edit_plate">
		<div id="select_list">
			<div id="select_title">
				商品列表
			</div>
			<div class="select_1">
				<div>商品编号: <input type="text" name="" id="shelf_product_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>商品名称: <input type="text" name="" id="shelf_product_name">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>上架时间: <input type="text" class="date" name="" id="shelf_add_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>商品品牌: <input type="text" name="" id="shelf_brand">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>商品型号: <input type="text" name="" id="shelf_art_number">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>商品库存: <input type="text" name="" id="shelf_store_count">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>厂家地址: <input type="text" name="" id="shelf_vender_url">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>开始日期: <input type="text" class="date" name="" id="shelf_start_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>结束日期: <input type="text" class="date" name="" id="shelf_end_date">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<shiro:hasPermission name="productInfo:view">
				<button id="shop_find_button" class="find_button">搜&nbsp;&nbsp;索</button><button id="refresh_shop">刷&nbsp;&nbsp;新</button>			
			</shiro:hasPermission>
			</div>
		</div>
		<div id="delete_div">
		<shiro:hasPermission name="productInfo:delete">
		<button id="delete_shop">删除<img src="images/cha.png"></button>
		</shiro:hasPermission>
		</div>
		<!-- 商品信息表 -->
		<div id="shop_info">	
			<table id="shop_table" class="table_fix">
				<thead id="shop_thead">
					<tr>
						<th width="3%"><input type="checkbox" name="shop_box"></th>
						<th width="4%">编号</th>
						<th width="10%">商品名称</th>
						<th width="9%">上架时间</th>
						<th width="6%">商品品牌</th>
						<th width="6%">商品型号</th>
						<th width="8%">商品尺寸</th>
						<th width="12%">商品颜色</th>
						<th width="6%">商品价格</th>
						<th width="4%">库存</th>
						<th width="16%">商家地址</th>
						<th width="5%">编辑</th>
					</tr>
				</thead>
				<tbody id="shop_tbody">
					
				</tbody>
			</table>
		</div>
		<div id="proPages" style="text-align: center">
			<ul id="proPage_view">
			<!--  
				<li><a href="#">&laquo; 上一页</a></li>
				<li class="page">1</li>
				<li class="page active">2</li>
				<li class="page">3</li>
				<li class="page">4</li>
				<li class="page">5</li>
				<li class="page">6</li>
				<li><a href="#">下一页 &raquo;</a></li> 
				<li style="border:0;">总共<span id="total_count">13</span>条记录</li>
			-->
			</ul>
		</div>
		<!-- 修改商品库存 -->
		<div id="update_shop_info">
			<div class="info_title">
				商品详情
			</div>
			<div class="info_list">
				<div>&nbsp;商品编号: <input readonly type="text" name="" id="update_shop_id">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品名称: <input type="text" name="" id="update_shop_name" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品品牌: <input type="text" name="" id="update_brand">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品型号: <input type="text" name="" id="update_art_number">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品颜色: <input type="text" name="" id="update_color">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品尺寸: <input type="text" name="" id="update_size">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品价格: <input type="text" name="" id="update_price">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;渠道价格: <input type="text" name="" id="update_channel_price">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;运费价格: <input type="text" name="" id="update_fare_price">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商品库存: <input type="text" name="" id="update_store_count">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;商家地址: <input type="text" name="" id="update_vender_url" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;淘宝链接: <input type="text" name="" id="update_taobao_url" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>&nbsp;领券链接: <input type="text" name="" id="update_king_url" style="width:535px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			</div>
			<div style="clear: both;" align="center">
			<div style="text-align:center;margin:8px 5px;">
			<shiro:hasPermission name="product:update">
				<button style="background-color: #13a8ee;width:100px;height:40px;border:2px solid #aaa;
				border-radius: 4px;margin-right: 50px;font-size: large;" id="update_button">修&nbsp;&nbsp;改</button>			
			</shiro:hasPermission>
				<button style="background-color: #13a8ee;width:100px;height:40px;border:2px solid #aaa;
				border-radius: 4px;font-size: large;" id="cancle_edit">返回列表</button>
			</div>
			</div>
		</div>
	</div>
<!-- 爬虫区域 -->
	<!-- 网络爬虫 -->
	<div id="gecco" class="edit_plate">
		<div id="gecco_plate" style="border: 2px solid #999;width: 95%;margin: 15px auto;">
		<div class="new">商品抓取</div>
		<div style="margin-top:20px;margin-left: 40px;margin-bottom: 40px">
			请输入抓取商品的详情页地址: <input id="gecco_url" type="text" style="width:500px; height:27px; border:2px solid #aaa;border-radius: 4px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<shiro:hasPermission name="productInfo:gecco">
			<button style="background-color: #13a8ee;border: 1px solid #eee;margin: 10px auto;font-size:14pt;
			border-radius: 8px;cursor: pointer;width:100px;height:40px;" id="gecco_crawler">抓&nbsp;&nbsp;&nbsp;取</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
		</shiro:hasPermission>
		</div>
		<form action="gecco/download.bsnt" id="gecco_submit" method="post" target="_blank">
		<div style="margin-top:20px;margin-left: 40px;margin-bottom: 40px">
			请输入需要下载的商品的名称: <input id="gecco_title" name="title" type="text" style="width:500px; height:27px; border:2px solid #aaa;border-radius: 4px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<shiro:hasPermission name="productInfo:download">
			<button style="background-color: #13a8ee;border: 1px solid #eee;margin: 10px auto;font-size:14pt;
			border-radius: 8px;cursor: pointer;width:100px;height:40px;" id="gecco_download">下&nbsp;&nbsp;&nbsp;载</button>			
		</shiro:hasPermission>
		</div>
		</form>
		</div>
	</div>
</div>
</body>
</html>