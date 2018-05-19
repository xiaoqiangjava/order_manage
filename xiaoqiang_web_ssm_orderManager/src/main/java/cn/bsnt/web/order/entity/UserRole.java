package cn.bsnt.web.order.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 角色分配实体类
 * @author xiaoqiang
 *
 */
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1056333298578177657L;

	private Integer id;

	//用户信息
	private User user;
	
	//角色信息
	private List<Role> role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user=" + user + ", role=" + role + "]";
	}
	
	
}
