package cn.bsnt.web.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 用户实体类
 * @author xiaoqiang
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//用户ID
	private String name;//用户名
	private String password;//用户密码
	private String nick;//昵称
	private String token;//身份证
	private String area;//所属区域
	private Timestamp creatTime;
	public User() {
	}
	public User(Integer id, String name, String password,String nick,String token,String area,Timestamp creatTime) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.nick = nick;
		this.token = token;
		this.area = area;
		this.creatTime = creatTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", nick=" + nick + ", token=" + token
				+ "]";
	}
	
	
}
