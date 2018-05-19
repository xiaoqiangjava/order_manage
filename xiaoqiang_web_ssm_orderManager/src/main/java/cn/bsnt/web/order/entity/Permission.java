package cn.bsnt.web.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 权限实体类
 * @author xiaoqiang
 *
 */
public class Permission implements Serializable {
	private static final long serialVersionUID = 8522951667839477782L;

	private Integer permissionId;
	private String permissionName;
	private String permissionDesc;
	private Timestamp permissionCreateTime;
	private Timestamp permissionUpdateTime;
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionDesc() {
		return permissionDesc;
	}
	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}
	public Timestamp getPermissionCreateTime() {
		return permissionCreateTime;
	}
	public void setPermissionCreateTime(Timestamp permissionCreateTime) {
		this.permissionCreateTime = permissionCreateTime;
	}
	
	public Timestamp getPermissionUpdateTime() {
		return permissionUpdateTime;
	}
	public void setPermissionUpdateTime(Timestamp permissionUpdateTime) {
		this.permissionUpdateTime = permissionUpdateTime;
	}
	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionDesc="
				+ permissionDesc + ", permissionCreateTime=" + permissionCreateTime + ", permissionUpdateTime="
				+ permissionUpdateTime + "]";
	}
	
	
	
}
