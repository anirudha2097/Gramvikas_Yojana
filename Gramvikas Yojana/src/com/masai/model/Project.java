package com.masai.model;

public class Project {
	
	private int pid;
	private String name;
	private String amount;
	private String location;
	private int duration;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(String name, int pid, String amount, String location, int duration) {
		super();
		this.pid = pid;
		this.name = name;
		this.amount = amount;
		this.location = location;
		this.duration = duration;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Projects [pid=" + pid + ", name=" + name + ", amount=" + amount + ", location=" + location
				+ ", duration=" + duration + "]";
	}

	
	
}
