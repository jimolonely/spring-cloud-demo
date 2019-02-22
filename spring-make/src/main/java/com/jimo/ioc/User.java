package com.jimo.ioc;

import java.io.Serializable;

/**
 * @author jimo
 * @date 19-2-22 上午9:37
 */
@Component
public class User implements Serializable {

	private Integer id;

	private String name;

	private String password;

	public User() {
		System.out.println("user default constructor...");
	}

	public User(String name) {
		this.name = name;
		System.out.println("constructor with name...");
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
}
