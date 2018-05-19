package cn.bsnt.web.order.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 权限分配实体类
 * @author xiaoqiang
 *
 */
public class RolePermission implements Serializable {

	private static final long serialVersionUID = 859353838312248542L;

	private Integer id;
	
	private Role role;
	
	private List<Permission> permission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "RolePermission [id=" + id + ", role=" + role + ", permission=" + permission + "]";
	}
	
	
}
