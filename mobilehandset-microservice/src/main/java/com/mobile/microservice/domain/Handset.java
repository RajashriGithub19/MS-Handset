package com.mobile.microservice.domain;

public class Handset {
	private int id;

	private String brand;

	private String phone;

	private String picture;

	private Release release;

	private String sim;

	private String resolution;

	private Hardware hardware;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public Release getRelease() {
		return this.release;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getSim() {
		return this.sim;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	public Hardware getHardware() {
		return this.hardware;
	}

	@Override
	public String toString() {
		return "Handset [id=" + id + ", brand=" + brand + ", phone=" + phone + ", picture=" + picture + ", release="
				+ release + ", sim=" + sim + ", resolution=" + resolution + ", hardware=" + hardware + "]";
	}
	
}
