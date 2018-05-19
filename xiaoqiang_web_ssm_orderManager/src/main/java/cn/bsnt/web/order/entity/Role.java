package cn.bsnt.web.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 角色信息实体类
 * @author xiaoqiang
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 3258423841086621085L;

	private Integer roleId;
	private String roleName;
	private String roleDesc;
	private Timestamp roleCreateTime;
	private Timestamp roleUpdateTime;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Timestamp getRoleCreateTime() {
		return roleCreateTime;
	}
	public void setRoleCreateTime(Timestamp roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}
	
	public Timestamp getRoleUpdateTime() {
		return roleUpdateTime;
	}
	public void setRoleUpdateTime(Timestamp roleUpdateTime) {
		this.roleUpdateTime = roleUpdateTime;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleCreateTime="
				+ roleCreateTime + ", roleUpdateTime=" + roleUpdateTime + "]";
	}
	
	
	
	
}
