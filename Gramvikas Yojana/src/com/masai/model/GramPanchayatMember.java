package com.masai.model;

public class GramPanchayatMember {
	
	private int gpmid;
	private String name;
	private String address;
	private String email;
	private String password;
	
	public GramPanchayatMember() {
		// TODO Auto-generated constructor stub
	}

	public GramPanchayatMember(int gpmid, String name, String address, String email, String password) {
		super();
		this.gpmid = gpmid;
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGpmid() {
		return gpmid;
	}

	public void setGpmid(int gpmid) {
		this.gpmid = gpmid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GramPanchayatMember [gpmid=" + gpmid + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", password=" + password + "]";
	}

	
	
	
}
